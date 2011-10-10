package com.accommodationmatches.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Booking;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.impl.AccommodationBookingServiceImpl;

public class AccommodationBookingServiceTest {
	private AccommodationBookingService accommodationBookingService;
	private Booking booking;
	@Before
	public void setupt(){
		setupBooking();
		accommodationBookingService = new AccommodationBookingServiceImpl(booking);
	}
	
	@Test
	public void mustGetTravellersByAccommodation(){
		Accommodation accommodation = new Accommodation();
		accommodation.setId(1);
		List<Traveller> travellers = accommodationBookingService.getTravellerByAccommodation(accommodation);
		assertEquals(2, travellers.size());
	}
	
	@Test
	public void mustGetAccommodationByTraveller(){
		Traveller traveller = new Traveller();
		traveller.setId(0);
		Accommodation accommodation = accommodationBookingService.getAccommodationByTraveller(traveller);
		assertEquals(2, accommodation.getId());
	}
	
	private void setupBooking(){
		booking = new Booking();
		Traveller traveller = new Traveller();
		traveller.setId(0);
		Traveller travellerOne = new Traveller();
		travellerOne.setId(1);
		Traveller travellerTwo = new Traveller();
		travellerTwo.setId(2);
		
		Accommodation accommodation = new Accommodation();
		accommodation.setId(0);
		Accommodation accommodationOne = new Accommodation();
		accommodationOne.setId(1);
		Accommodation accommodationTwo = new Accommodation();
		accommodationTwo.setId(2);
		
		List<Traveller> onAccommodationOne = new ArrayList<Traveller>();
		List<Traveller> onAccommodationTwo = new ArrayList<Traveller>();
		
		onAccommodationOne.add(travellerOne);
		onAccommodationOne.add(travellerTwo);
		booking.getAccommodationTravellerMap().put(accommodationOne, onAccommodationOne);
		
		onAccommodationTwo.add(traveller);
		booking.getAccommodationTravellerMap().put(accommodationTwo, onAccommodationTwo);
		
	}
}
