package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISessionManager extends Remote {

	public IManagerSession getNewManagerSession(String name, String companyName) throws RemoteException;
	
	public IReservationSession getNewReservationSession(String name) throws RemoteException;
	
}
