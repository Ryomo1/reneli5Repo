package com.accommodationmatches.app.delegate;

import java.io.FileNotFoundException;
import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;

public interface AccommodationMatcher {

	/*
	 * handles accommodation allocation to travellers
	 */
	public abstract void updateAccommodationBookings(
			Accommodation accommodation, Traveller traveller);

	/*
	 * return traveller given accommodationId
	 */
	public abstract List<Traveller> listTravellerByAccommodationId(
			int accommodationId);

	/*
	 * returns accommodation traveller id
	 */
	public abstract Accommodation getAccommodationByTravellerId(int travellerId);

	/*
	 * add a new traveller to accomoodation
	 */
	public abstract Accommodation addNewTraveller(Traveller newTraveller)
			throws FileNotFoundException;

}