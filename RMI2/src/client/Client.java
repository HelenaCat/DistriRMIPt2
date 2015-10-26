package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import rental.Reservation;
import agency.ReservationSession;
import agency.ManagerSession;

public class Client extends AbstractTestManagement<ReservationSession, ManagerSession>{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String carRentalCompanyNameHertz = "Hertz";
		String carRentalCompanyNameDockx = "Dockx";

		Registry registry = LocateRegistry.getRegistry(); //args[0]
		
		Client client = new Client("trips");
		client.registerInitialCompany(carRentalCompanyNameHertz, registry);
		client.registerInitialCompany(carRentalCompanyNameDockx, registry);
		client.run();

	}

	private void registerInitialCompany(String carRentalCompanyNameHertz, Registry registry) {
		// TODO Auto-generated method stub
		
	}

	public Client(String scriptFile) {
		super(scriptFile);
	}
	
	@Override
	protected String getCheapestCarType(ReservationSession session, Date start,
			Date end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMostPopularCarRentalCompany(ManagerSession ms)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ReservationSession getNewReservationSession(String name)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ManagerSession getNewManagerSession(String name,
			String carRentalName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkForAvailableCarTypes(ReservationSession session,
			Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addQuoteToSession(ReservationSession session, String name,
			Date start, Date end, String carType, String carRentalName)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<Reservation> confirmQuotes(ReservationSession session,
			String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getNumberOfReservationsBy(ManagerSession ms, String clientName)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getNumberOfReservationsForCarType(ManagerSession ms,
			String carRentalName, String carType) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
