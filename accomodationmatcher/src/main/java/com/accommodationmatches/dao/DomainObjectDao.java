package com.accommodationmatches.dao;

import java.io.FileNotFoundException;
import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;

public interface DomainObjectDao {
	List<Traveller> getTravellersList()throws FileNotFoundException;
	List<Accommodation> getAccommodationList()throws FileNotFoundException;
}
