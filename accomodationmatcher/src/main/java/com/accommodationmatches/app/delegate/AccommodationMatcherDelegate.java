package com.accommodationmatches.app.delegate;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import com.accommodationmatches.app.delegate.AccommodationMatcher;
import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.AccommodationBookingService;
import com.accommodationmatches.service.AccommodationMatcherService;

public class AccommodationMatcherDelegate implements AccommodationMatcher {
	private AccommodationMatcherService accommodationMatcherService;
	private AccommodationBookingService accommodationBookingService;
	
	public AccommodationMatcherDelegate(AccommodationMatcherService accommodationMatcherService, AccommodationBookingService accommodationBookingService) throws FileNotFoundException{
		this.accommodationMatcherService = accommodationMatcherService;
		this.accommodationBookingService = accommodationBookingService;
		init();
	}
	
	private void init() throws FileNotFoundException{
		for(Traveller traveller : accommodationMatcherService.getAllTravellers()){
			Accommodation accommodation = accommodationMatcherService.findAccommodationLocation(traveller);
			updateAccommodationBookings(accommodation, traveller);
		}
	}
	
	/*
	 * handles accommodation allocation to travellers
	 */
	/* (non-Javadoc)
	 * @see com.accommodationmatches.app.delegate.AccommodationMatcher#updateAccommodationBookings(com.accommodationmatches.domain.Accommodation, com.accommodationmatches.domain.Traveller)
	 */
	public void updateAccommodationBookings(Accommodation accommodation, Traveller traveller){
		if(accommodation != null){
			List<Traveller> bookedTravellers  = accommodationBookingService.getAccommodationBooking().getAccommodationTravellerMap().get(accommodation);
			
			if(bookedTravellers != null){
				bookedTravellers.add(traveller);
			}else{
				bookedTravellers = new LinkedList<Traveller>();
				bookedTravellers.add(traveller);
				accommodationBookingService.getAccommodationBooking().getAccommodationTravellerMap().put(accommodation, bookedTravellers);
			}
			
			accommodationMatcherService.adjustFreeCapacityForAccommodation(accommodation);
		}
	}
	
	/*
	 * return traveller info given accommodationId
	 */
	/* (non-Javadoc)
	 * @see com.accommodationmatches.app.delegate.AccommodationMatcher#listTravellerByAccommodationId(int)
	 */
	public List<Traveller> listTravellerByAccommodationId(int accommodationId){
		Accommodation accommodation = new Accommodation();
		accommodation.setId(accommodationId);
		List<Traveller> travellers = accommodationBookingService.getTravellerByAccommodation(accommodation);
		return travellers;
	}
	
	/*
	 * prints out accommodation info on the screen given traveller id
	 */
	/* (non-Javadoc)
	 * @see com.accommodationmatches.app.delegate.AccommodationMatcher#getAccommodationByTravellerId(int)
	 */
	public Accommodation getAccommodationByTravellerId(int travellerId){
		Traveller traveller = new Traveller();
		traveller.setId(travellerId);
		Accommodation bookedAccommodation = accommodationBookingService.getAccommodationByTraveller(traveller);
		return bookedAccommodation;
	}
	
	/*
	 * add a new traveller to accomoodation
	 */
	/* (non-Javadoc)
	 * @see com.accommodationmatches.app.delegate.AccommodationMatcher#addNewTraveller(com.accommodationmatches.domain.Traveller)
	 */
	public Accommodation addNewTraveller(Traveller newTraveller) throws FileNotFoundException{
		newTraveller.setId(accommodationMatcherService.getNextTravellerId());
		Accommodation newTravellerAccom = accommodationMatcherService.findAccommodationLocation(newTraveller);
		return newTravellerAccom;
	}
}
