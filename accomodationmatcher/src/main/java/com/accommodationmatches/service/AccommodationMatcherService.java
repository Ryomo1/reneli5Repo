package com.accommodationmatches.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;

public interface AccommodationMatcherService {
	/*
	 * return the best cost effective value accommodation for a traveller
	 */
	Accommodation findAccommodationLocation(Traveller traveller)throws FileNotFoundException;
	/*
	 * reduces the capacity for this accommodation by one
	 */
	void adjustFreeCapacityForAccommodation(Accommodation accommodation);
	/*
	 * returns the next available traveller id
	 */
	long getNextTravellerId()throws FileNotFoundException;
	
	/*
	 * returns all accommodations
	 */
	public List<Accommodation> getAllAccommodations()throws FileNotFoundException;
	
	/*
	 * return all travellers
	 */
	public List<Traveller> getAllTravellers()throws FileNotFoundException;
}
