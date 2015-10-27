package agency;

import java.util.Date;
import java.util.List;

import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationException;
import client.ReservationConstraints;

public class ReservationSession extends Session implements IReservationSession{
	
	String name;
	
	public ReservationSession(String name){
		this.name = name;
	}

	
	public Quote createQuote(ReservationConstraints constraints, String client)
			throws ReservationException {
		//TODO 
		//-zet quote bij Company
		//-vraag aan company om Quote aan te maken, Company geeft Quote terug
		//-voeg Quote die teruggegeven wordt toe aan lijst die hier in de sessie bijgehouden wordt 
		//-return de quote
		//String carRenter, Date start, Date end, String rentalCompany, String carType, double rentalPrice
		return null;
	}
	
	public List<Quote> getCurrentQuotes(){
		return null;
	}
	
	public List<Reservation> confirmQuotes(){
		return null;
	}
	
	public List<CarType> getAvailableCarTypes(){
		return null;
	}
	
	public String getCheapestCarType(){
		return null;
	}
	
}
