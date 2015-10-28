package agency;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationException;

public class Agency {
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		Agency agency = null;
		try {
			agency = new Agency();
		} catch (RemoteException e) {
			System.err.println("Error locating registry while creating agency");
			e.printStackTrace();
		}
		
		SessionManager sessionManager = new SessionManager(agency);
		
		try {
			ISessionManager stub =(ISessionManager) UnicastRemoteObject.exportObject(sessionManager, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("sessionManager", stub);
			System.out.println("SessionManager bound");
		} catch (RemoteException e1) {
			System.err.println("SessionManager exception");
			e1.printStackTrace();
		}
		System.out.println("Agency is ready");
	}
	
	
	private ArrayList<String> companies;
	private Registry registry;
	
	public Agency() throws RemoteException{
		this.companies = new ArrayList<String>();
		this.registry = LocateRegistry.getRegistry();
	}
	
	public void registerCompany(String companyName){
		this.companies.add(companyName);
	}

	public String getCheapestCarType(Date start, Date end) {
		CarType cheapestCarType = null;
		for(String companyString : companies){
			try {
				ICarRentalCompany company = (ICarRentalCompany) registry.lookup(companyString);
				
				CarType cheapestType = company.getCheapestCarType(start,end);
				if(cheapestCarType == null || cheapestType.getRentalPricePerDay() < cheapestCarType.getRentalPricePerDay()){
					cheapestCarType = cheapestType;
				}
				
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Error looking up company.");
				e.printStackTrace();
			}
		} 
		
		return cheapestCarType.getName();
	}

	public String getMostPopularCarRentalCompany() {
		String popularCompany = "";
		int nbReservations = 0;
		
		for(String companyString : companies){
			try {
				ICarRentalCompany company = (ICarRentalCompany) registry.lookup(companyString);
				
				int reservationCount = company.getTotalNbReservations();
				if( reservationCount > nbReservations){
					nbReservations = reservationCount;
					popularCompany = companyString;
				}
				
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Error looking up company.");
				e.printStackTrace();
			}
		}  
		return popularCompany;
	}

	public ArrayList<String> checkForAvailableCarTypes(Date start, Date end) {
		ArrayList<String> availableCarTypes = new ArrayList<>();
		for(String companyString : companies){
			try {
				ICarRentalCompany company = (ICarRentalCompany) registry.lookup(companyString);
				
				Set<CarType> companyCarTypes = company.getAvailableCarTypes(start, end);
				for(CarType carType: companyCarTypes){
					availableCarTypes.add(carType.getName());
				}
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Error looking up company.");
				e.printStackTrace();
			}
		}  
		return availableCarTypes;
	}

	public Quote createQuote(Date start, Date end, String carType, String carRentalName, String renterName) throws ReservationException {
		Quote quote = null;
		ReservationConstraints constraints = new ReservationConstraints(start, end, carType);
		try {
			ICarRentalCompany company = (ICarRentalCompany) registry.lookup(carRentalName);
			quote = company.createQuote(constraints, renterName);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Error looking up company.");
			e.printStackTrace();
		}
		return quote;
	}

	public Reservation confirmQuote(Quote quote) throws ReservationException{
		Reservation reservation = null;
		try {
			ICarRentalCompany company = (ICarRentalCompany) registry.lookup(quote.getRentalCompany());
			reservation = company.confirmQuote(quote);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Error looking up company.");
			e.printStackTrace();
		}
		return reservation;
	}

	public void cancelReservation(Reservation reservation) {
		try {
			ICarRentalCompany company = (ICarRentalCompany) registry.lookup(reservation.getRentalCompany());
			company.cancelReservation(reservation);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Error looking up company.");
			e.printStackTrace();
		}		
	}

	public int getNbOfReservationsBy(String clientName) {
		int nbReservations = 0;
		for(String companyString : companies){
			try {
				ICarRentalCompany company = (ICarRentalCompany) registry.lookup(companyString);
				nbReservations += company.getAllReservationsByClient(clientName).size();
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Error looking up company.");
				e.printStackTrace();
			}
		}  
		
		return nbReservations;
	}

	public int getNbOfReservationsForCarType(String carType) {
		int nbReservations = 0;
		for(String companyString : companies){
			try {
				ICarRentalCompany company = (ICarRentalCompany) registry.lookup(companyString);
				nbReservations += company.getNumberOfReservationsForCarType(carType);
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Error looking up company.");
				e.printStackTrace();
			}
		}  
		
		return nbReservations;
	}

}
