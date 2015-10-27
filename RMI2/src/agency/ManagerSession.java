package agency;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rental.ICarRentalCompany;

public class ManagerSession extends Session implements IManagerSession{
	
	String name;
	String companyName;
	
	public ManagerSession(String name, String companyName){
		this.name = name;
		this.companyName = companyName;
	}
	
	public void register(ICarRentalCompany stub) throws RemoteException{
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(stub.getName(), stub);
	}

}
