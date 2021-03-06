package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import agency.ReservationConstraints;




public interface ICarRentalCompany extends Remote{
	
	public String getName() throws RemoteException;

	public boolean isAvailable(String carTypeName, Date start, Date end) throws RemoteException;
	
	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
	
	public Quote createQuote(ReservationConstraints constraints, String client) throws ReservationException, RemoteException;
	
	public Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException;
	
	public List<Reservation> getAllReservationsByClient(String client) throws RemoteException;
	
	public int getNumberOfReservationsForCarType(String carType) throws RemoteException;

	public CarType getCheapestCarType(Date start, Date end) throws RemoteException;

	public int getTotalNbReservations() throws RemoteException;
	
	public void cancelReservation(Reservation res) throws RemoteException;
}