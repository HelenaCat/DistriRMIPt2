package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rental.Reservation;
import rental.ReservationException;

public interface IReservationSession extends Remote {
	
	public String getCheapestCarType(Date start, Date end) throws RemoteException;

	public ArrayList<String> checkForAvailableCarTypes(Date start, Date end) throws RemoteException;

	public void addQuote(Date start, Date end, String carType, String carRentalName) throws RemoteException, ReservationException;

	public List<Reservation> confirmQuotes() throws RemoteException, ReservationException;

}
