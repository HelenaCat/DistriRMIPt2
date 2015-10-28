package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rental.ICarRentalCompany;

public interface IManagerSession extends Remote {
	
	public void registerCompany(ICarRentalCompany stub) throws RemoteException;

}
