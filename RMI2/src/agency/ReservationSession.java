package agency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rental.Quote;
import rental.Reservation;
import rental.ReservationException;


public class ReservationSession implements IReservationSession{
	
	String name;
	Agency agency;
	ArrayList<Quote> currentQuotes;
	
	public ReservationSession(String name, Agency agency){
		this.name = name;
		this.agency = agency;
		this.currentQuotes = new ArrayList<Quote>();
	}

	public String getCheapestCarType(Date start, Date end){
		return agency.getCheapestCarType(start,end);
	}


	@Override
	public ArrayList<String> checkForAvailableCarTypes(Date start, Date end) {
		return agency.checkForAvailableCarTypes(start, end);
	}


	@Override
	public void addQuote(Date start, Date end, String carType, String carRentalName) throws ReservationException {
		currentQuotes.add(agency.createQuote(start, end, carType, carRentalName, this.name));
	}

	@Override
	public List<Reservation> confirmQuotes() throws ReservationException {
		ArrayList<Reservation> reservations = new ArrayList<>();
		
		for(Quote quote: currentQuotes){
			try {
				reservations.add(agency.confirmQuote(quote));
			} catch (ReservationException r) {
				for(Reservation reservation: reservations){
					agency.cancelReservation(reservation);
					currentQuotes.clear();
					throw r;
				}
			}
		}
		
		currentQuotes.clear();
		return reservations;
	}
	
}
