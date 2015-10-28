package agency;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SessionManager implements ISessionManager {
	
	private Agency agency;
	
	public SessionManager(Agency agency){
		this.agency = agency;
	}

	public IManagerSession getNewManagerSession(String name, String companyName) throws RemoteException {
		ManagerSession session = new ManagerSession(name,companyName, agency);
		IManagerSession stub = (IManagerSession) UnicastRemoteObject.exportObject(session, 0);
		return stub;
	}

	public IReservationSession getNewReservationSession(String name) throws RemoteException{
		ReservationSession session = new ReservationSession(name, agency);
		IReservationSession stub = (IReservationSession) UnicastRemoteObject.exportObject(session, 0);
		return stub;
	}

}
