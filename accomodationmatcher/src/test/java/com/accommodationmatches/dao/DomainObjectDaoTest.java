package com.accommodationmatches.dao;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.accommodationmatches.dao.impl.JsonDomainObjectDaoImpl;
import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;

public class DomainObjectDaoTest {
	private DomainObjectDao domainObjectDao;
	
	@Before
	public void setup(){
		domainObjectDao = new JsonDomainObjectDaoImpl("/src/test/resources/data/travellers.json", "/src/test/resources/data/accommodation.json");
	}
	
	@Test
	public void mustGetTravellersList() throws FileNotFoundException{
		List<Traveller> travellers = domainObjectDao.getTravellersList();
		assertEquals(2, travellers.size());
	}
	
	@Test
	public void mustGetAccommodationsList() throws FileNotFoundException{
		List<Accommodation> accommodationList = domainObjectDao.getAccommodationList();
		assertEquals(2, accommodationList.size());
	}
}
