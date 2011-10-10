package com.accommodationmatches.service;


import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Booking;
import com.accommodationmatches.domain.Traveller;

public interface AccommodationBookingService {
	Booking getAccommodationBooking();
	List<Traveller> getTravellerByAccommodation(Accommodation accommodationId);
	Accommodation getAccommodationByTraveller(Traveller traveller);
}
