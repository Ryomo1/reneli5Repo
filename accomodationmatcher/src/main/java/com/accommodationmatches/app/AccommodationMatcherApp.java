package com.accommodationmatches.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.accommodationmatches.app.delegate.AccommodationMatcher;
import com.accommodationmatches.app.delegate.AccommodationMatcherDelegate;
import com.accommodationmatches.dao.DomainObjectDao;
import com.accommodationmatches.dao.impl.JsonDomainObjectDaoImpl;
import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Booking;
import com.accommodationmatches.domain.PriceRange;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.AccommodationBookingService;
import com.accommodationmatches.service.AccommodationMatcherService;
import com.accommodationmatches.service.ScreenReaderService;
import com.accommodationmatches.service.impl.AccommodationBookingServiceImpl;
import com.accommodationmatches.service.impl.AccommodationMatcherServiceImpl;
import com.accommodationmatches.service.impl.ScreenReaderServiceimpl;

public class AccommodationMatcherApp {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//load travellers and accommodation
		AccommodationMatcherApp app = new AccommodationMatcherApp();
		DomainObjectDao jsonToDomain = new JsonDomainObjectDaoImpl("/src/main/resources/data/travellers.json", "/src/main/resources/data/accommodation.json");
		AccommodationMatcherService accommodationMatcherService = new AccommodationMatcherServiceImpl(jsonToDomain);
		AccommodationBookingService accommodationBookingService = new AccommodationBookingServiceImpl(new Booking());
		System.out.println("Allocating accommodation to travellers.... please wait....");

		AccommodationMatcher delegate = new AccommodationMatcherDelegate(accommodationMatcherService, accommodationBookingService);
		
		//prompt the user for imput and deal with user interaction
		ScreenReaderService screenReader = new ScreenReaderServiceimpl();
		System.out.print("Accommodation Matcher is ready. \n- Enter an option, then enter to continue\n- Enter exit, then press enter to stop\n");
		app.manageUserInteraction(screenReader, delegate);
		System.out.println("Thank you and good bye.");
	}
	public void manageUserInteraction(ScreenReaderService screenReader, AccommodationMatcher delegate)
			throws IOException {
		
		String data = "start";
		
		while(!data.equalsIgnoreCase("exit")){
			System.out.println("*****************************************************");
			System.out.println("1. Enter 1 to list travellers by accommodation id,");
			System.out.println("2. Enter 2 to get accommodation by travel id,");
			System.out.println("3, Enter 3 to add new traveller");
			System.out.println("*****************************************************");
			System.out.print("\nacomcommand-->");
			data = screenReader.readLine();
			try{
				int choice = Integer.parseInt(data.trim());
				switch(choice){
					case 1 : System.out.print("Now Enter accommodation id :");
							 String id = screenReader.readLine();
							 try{
								 int accommId = Integer.parseInt(id.trim());
								 List<Traveller> travellers = delegate.listTravellerByAccommodationId(accommId);
								 if(travellers != null){
										System.out.print("\t\t\t Traveller booked at accommodation " + accommId + " are : " + travellers.size() + "\n" );
										for(Traveller traveller : travellers){
											System.out.print("\t" + traveller.toString() + "\n");
										}
									}else{
										System.out.print("\t No traveller is booked in this accommodation.\n");
									}
							 }catch(Exception e){
								 System.out.print("Wrong accommodation id, try again.");
							 }
							 break;
					case 2 : System.out.println("Now Enter Traveller id :");
							 System.out.print("\nacomcommand-->");
							 String travelId = screenReader.readLine();
							 try{
								 int travellerId = Integer.parseInt(travelId.trim());
								 Accommodation bookedAccommodation = delegate.getAccommodationByTravellerId(travellerId);
								 if(bookedAccommodation != null){
									System.out.print("\t\t\t Traveller : " + travellerId + " is booked at: \n" );
									System.out.print("\t" + bookedAccommodation.toString() + "*\n");
								}else{
									System.out.println("No accommodation found.");
								}
							 }catch(Exception e){
								 System.out.println("Wrong accommodation id, try again.");
							 }
							 break;
					case 3 : System.out.println("Now Enter new Traveller details:");
							 System.out.println("Enter Traveller Name:");
							 System.out.print("\nacomcommand-->");
							 String name = screenReader.readLine();
							 System.out.println("Enter Minimum Price:");
							 System.out.print("\nacomcommand-->");
							 String minPrice = screenReader.readLine();
							 System.out.println("Enter Maximum Price:");
							 System.out.print("\nacomcommand-->");
							 String maxPrice = screenReader.readLine();
							 System.out.println("Enter one requirements a time, press enter to move to next requirement; enter x and press enter to finish requirement: ");
							 System.out.print("\nacomcommand-->");
							 String requirement = screenReader.readLine();
							 List<String> requirementList = new ArrayList<String>();
							 while(!requirement.trim().equalsIgnoreCase("x")){
								 requirementList.add(requirement);
								 System.out.println("Enter next requirements:");
								 System.out.print("\nacomcommand-->");
								 requirement = screenReader.readLine();
							 }
							 Traveller newTraveller = new Traveller();
							 newTraveller.setName(name);
							 PriceRange priceRange = new PriceRange();
							 priceRange.setMax(Integer.parseInt(maxPrice.trim()));
							 priceRange.setMin(Integer.parseInt(minPrice.trim()));
							 newTraveller.setPriceRange(priceRange);
							 newTraveller.setRequirements(requirementList);
							 Accommodation newTravellerAccom = delegate.addNewTraveller(newTraveller);
							 if(newTravellerAccom != null){
								delegate.updateAccommodationBookings(newTravellerAccom, newTraveller);
								System.out.println("best price accommodation = " + newTravellerAccom.toString());
							 }else{
								System.out.println("No accommodation found.");
							 }
							 break;
					default : System.out.println("No command match. Try again.");
						     break;
				}
			}catch(Exception e){
				if(!data.equalsIgnoreCase("exit")){
					System.out.println("Invalid choice, please try again:");
				}
			}
		}
	}
	
	
}
