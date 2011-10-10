package com.accommodationmatches.service;

import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;

public interface AccommodationMatcherService {
	Accommodation findAccommodationLocation(Traveller traveller, List<Accommodation> accommodationList);
	void adjustFreeCapacityForAccommodation(Accommodation accommodation);
}
