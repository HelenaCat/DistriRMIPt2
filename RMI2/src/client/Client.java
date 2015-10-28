package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rental.ICarRentalCompany;
import rental.Reservation;
import agency.IManagerSession;
import agency.IReservationSession;
import agency.ISessionManager;
import agency.ReservationSession;
import agency.ManagerSession;

public class Client extends AbstractTestManagement<IReservationSession, IManagerSession>{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
				
		Client client = new Client("trips");
		client.run();

	}
	
	
	public Client(String scriptFile) {
		super(scriptFile);
	}
	
	

	@Override
	protected String getCheapestCarType(IReservationSession session, Date start, Date end) throws Exception {
		return session.getCheapestCarType(start,end);
	}

	@Override
	protected String getMostPopularCarRentalCompany(IManagerSession ms)
			throws Exception {
		return ms.getMostPopularCarRentalCompany();
	}

	@Override
	protected IReservationSession getNewReservationSession(String name) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManager");
		IReservationSession iReservationSession = sessionManager.getNewReservationSession(name);
		return iReservationSession;
	}

	@Override
	public IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManager");
		IManagerSession iManagerSession = sessionManager.getNewManagerSession(name, carRentalName);
		return iManagerSession;
	}

	@Override
	protected void checkForAvailableCarTypes(IReservationSession session, Date start, Date end) throws Exception {
		ArrayList<String> availableCarTypes = session.checkForAvailableCarTypes(start, end);
		
		String available = "Available CarTypes: ";
		for(String type: availableCarTypes){
			available += type + " ";
		}
		System.out.println(available);
	}

	@Override
	protected void addQuoteToSession(IReservationSession session, String name,
			Date start, Date end, String carType, String carRentalName)
			throws Exception {
		session.addQuote(start, end, carType, carRentalName);
		//TODO ReservationException wordt nu nergens (bij ons) gecatched, is dat de bedoeling?
		//		--> wordt mss ergens in hun code gecatched, waardoor ze dan de boodschap die erin staat weergeven
	}

	@Override
	protected List<Reservation> confirmQuotes(IReservationSession session, String name) throws Exception {
		// TODO Hier ook ReservationException doorthrowen, zodat hun code weet dat het is misgelopen en iets weergeeft ofzo?
		return session.confirmQuotes();
	}

	@Override
	protected int getNumberOfReservationsBy(IManagerSession ms, String clientName) throws Exception {
		return ms.getNbOfReservationsBy(clientName);
	}

	@Override
	protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType) throws Exception {
		return ms.getNbOfReservationsForCarType(carType);
	}


}
