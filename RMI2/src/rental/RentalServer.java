package rental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import agency.IManagerSession;

import client.Client;

public class RentalServer {

	public static void main(String[] args) throws ReservationException,
	NumberFormatException, IOException {
		//TODO Company-objecten aanmaken, 
		//		en dan managersession aanmaken, stub meegeven en zo company registreren
		
		List<Car> carsHertz = loadData("hertz.csv");

		try {
			CarRentalCompany company = new CarRentalCompany("Hertz", carsHertz);
			ICarRentalCompany stub =(ICarRentalCompany) UnicastRemoteObject.exportObject(company, 0);

			Client client = new Client("trips");
			IManagerSession session = client.getNewManagerSession("H", "Hertz");
			session.registerCompany(stub);
			
			System.out.println("Hertz bound");
		} catch (Exception e) {
			System.err.println("Hertz exception:");
			e.printStackTrace();
		}
		
		List<Car> carsDockx = loadData("dockx.csv");

		try {
			CarRentalCompany company = new CarRentalCompany("Dockx", carsDockx);
			ICarRentalCompany stub =(ICarRentalCompany) UnicastRemoteObject.exportObject(company, 0);

			
			Client client = new Client("trips");
			IManagerSession session = client.getNewManagerSession("F", "Dockx");
			session.registerCompany(stub);
			System.out.println("Dockx bound");
		} catch (Exception e) {
			System.err.println("Dockx exception:");
			e.printStackTrace();
		}
	}

	public static List<Car> loadData(String datafile)
			throws ReservationException, NumberFormatException, IOException {

		List<Car> cars = new LinkedList<Car>();

		int nextuid = 0;

		// open file
		BufferedReader in = new BufferedReader(new FileReader(datafile));

		try {
			// while next line exists
			while (in.ready()) {
				// read line
				String line = in.readLine();
				// if comment: skip
				if (line.startsWith("#"))
					continue;
				// tokenize on ,
				StringTokenizer csvReader = new StringTokenizer(line, ",");
				// create new car type from first 5 fields
				CarType type = new CarType(csvReader.nextToken(),
						Integer.parseInt(csvReader.nextToken()),
						Float.parseFloat(csvReader.nextToken()),
						Double.parseDouble(csvReader.nextToken()),
						Boolean.parseBoolean(csvReader.nextToken()));
				System.out.println(type);
				// create N new cars with given type, where N is the 5th field
				for (int i = Integer.parseInt(csvReader.nextToken()); i > 0; i--) {
					cars.add(new Car(nextuid++, type));
				}
			}
		} finally {
			in.close();
		}

		return cars;
	}
}