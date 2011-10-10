package com.accommodationmatches.dao;

import java.io.FileNotFoundException;
import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;

public interface DomainObjectDao {
	/*
	 * returns all travellers
	 */
	List<Traveller> getTravellersList()throws FileNotFoundException;
	/*
	 * returns all accommodation
	 */
	List<Accommodation> getAccommodationList()throws FileNotFoundException;
}
