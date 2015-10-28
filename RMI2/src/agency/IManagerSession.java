package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rental.ICarRentalCompany;

public interface IManagerSession extends Remote {
	
	public void registerCompany(ICarRentalCompany stub) throws RemoteException;

	public String getMostPopularCarRentalCompany() throws RemoteException;

	public int getNbOfReservationsBy(String clientName) throws RemoteException;

	public int getNbOfReservationsForCarType(String carType) throws RemoteException;

}
