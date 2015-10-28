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
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("sessionManager", stub);
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
