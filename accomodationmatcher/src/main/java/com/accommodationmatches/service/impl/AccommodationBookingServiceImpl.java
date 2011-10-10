package com.accommodationmatches.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Booking;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.AccommodationBookingService;

public class AccommodationBookingServiceImpl implements	AccommodationBookingService {
	private Booking booking;

	public AccommodationBookingServiceImpl(Booking booking) {
		this.booking = booking;
	}

	public Booking getAccommodationBooking() {
		return booking;
	}

	public List<Traveller> getTravellerByAccommodation(Accommodation accommodation) {
		return booking.getAccommodationTravellerMap().get(accommodation);
	}

	public Accommodation getAccommodationByTraveller(Traveller traveller) {
		Accommodation bookedAccommodation = null;
		Map<Accommodation, List<Traveller>> bookingMap = booking.getAccommodationTravellerMap();
		Set<Accommodation> bookingKeys = bookingMap.keySet();
		for(Accommodation accommodation : bookingKeys){
			List<Traveller> travellers = bookingMap.get(accommodation);
			if(travellers.contains(traveller)){
				bookedAccommodation = accommodation;
				break;
			}
		}
		return bookedAccommodation;
	}
	
}
