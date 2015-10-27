package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import rental.ICarRentalCompany;
import rental.Reservation;
import agency.IManagerSession;
import agency.IReservationSession;
import agency.ISessionManager;
import agency.ReservationSession;
import agency.ManagerSession;

public class Client extends AbstractTestManagement<IReservationSession, IManagerSession>{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
				
		Client client = new Client("trips");
		client.run();

	}
	
	
	public Client(String scriptFile) {
		super(scriptFile);
	}
	
	

	@Override
	protected String getCheapestCarType(IReservationSession session, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMostPopularCarRentalCompany(IManagerSession ms)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IReservationSession getNewReservationSession(String name) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManager");
		IReservationSession iReservationSession = sessionManager.getNewReservationSession(name);
		return iReservationSession;
	}

	@Override
	public IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManager");
		IManagerSession iManagerSession = sessionManager.getNewManagerSession(name, carRentalName);
		return iManagerSession;
	}

	@Override
	protected void checkForAvailableCarTypes(IReservationSession session, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addQuoteToSession(IReservationSession session, String name,
			Date start, Date end, String carType, String carRentalName)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<Reservation> confirmQuotes(IReservationSession session,
			String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getNumberOfReservationsBy(IManagerSession ms,
			String clientName) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getNumberOfReservationsForCarType(IManagerSession ms,
			String carRentalName, String carType) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


}
