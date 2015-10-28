package agency;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rental.ICarRentalCompany;

public class ManagerSession implements IManagerSession{
	
	String name;
	String companyName;
	Agency agency;
	
	public ManagerSession(String name, String companyName, Agency agency){
		this.name = name;
		this.companyName = companyName;
		this.agency = agency;
	}
	
	public void registerCompany(ICarRentalCompany stub) throws RemoteException{
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(stub.getName(), stub);
		agency.registerCompany(stub.getName());
	}

}
