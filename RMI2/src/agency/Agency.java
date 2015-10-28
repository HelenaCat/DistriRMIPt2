package agency;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import rental.ICarRentalCompany;

public class Agency {
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		Agency agency = new Agency();
		SessionManager sessionManager = new SessionManager(agency);
		
		try {
			ISessionManager stub =(ISessionManager) UnicastRemoteObject.exportObject(sessionManager, 0);
			System.out.println("SessionManager stub created"); //TODO
			Registry registry = LocateRegistry.getRegistry();
			System.out.println("Registry found"); //TODO
			registry.rebind("sessionManager", stub);
			System.out.println("sessionmanager stub bound to registry");//TODO
		} catch (RemoteException e) {
			System.err.println("SessionManager exception");
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> companies;
	
	public Agency(){
		this.companies = new ArrayList<String>();
	}
	
	public void registerCompany(String companyName){
		this.companies.add(companyName);
	}

}
