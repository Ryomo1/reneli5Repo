package com.accommodationmatches.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Capacity;
import com.accommodationmatches.domain.PriceRange;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.impl.AccommodationMatcherServiceImpl;

public class AccommodationMatcherServiceTest {
	private AccommodationMatcherService accommodationMatcherService;
	private List<Accommodation> accommodationList;
	private Traveller traveller;
	private PriceRange priceRange;
	@Before
	public void setup() {
		accommodationMatcherService = new AccommodationMatcherServiceImpl();
		setupAccommodations();
	}
	
	@Test
	public void mustFindAccommodationOne(){
		traveller = new Traveller();
		traveller.setId(0);
		traveller.setName("testName");
		priceRange = new PriceRange();
		priceRange.setMax(200);
		priceRange.setMin(100);
		traveller.setPriceRange(priceRange);
		String[] requirements = {"bath", "phone"};
		traveller.setRequirements(Arrays.asList(requirements));
		
		Accommodation accommodationOne = accommodationMatcherService.findAccommodationLocation(traveller, accommodationList);
		
		assertEquals(120, accommodationOne.getPrice());
		
	}
	
	@Test
	public void mustRedureAccommodationCapacityByOne(){
		accommodationMatcherService.adjustFreeCapacityForAccommodation(accommodationList.get(0));
		assertEquals(0, accommodationList.get(0).getCapacity().getFree());
		
	}

	private void setupAccommodations() {
		
		Accommodation accommodation = new Accommodation();
		accommodation.setId(0);
		accommodation.setName("test accommodation");
		accommodation.setPrice(150);
		Capacity accomCapacity = new Capacity();
		accomCapacity.setFree(1);
		accomCapacity.setTotal(2);
		accommodation.setCapacity(accomCapacity);
		String[] accommodationAtt = { "phone", "internet", "bath" };
		accommodation.setAttributes(Arrays.asList(accommodationAtt));
		
		Accommodation accommodationOne = new Accommodation();
		accommodationOne.setId(1);
		accommodationOne.setName("test accommodation one");
		accommodationOne.setPrice(120);
		Capacity accomOneCapacity = new Capacity();
		accomOneCapacity.setFree(5);
		accomOneCapacity.setTotal(10);
		accommodationOne.setCapacity(accomOneCapacity);
		String[] accommodatioOneAtt = { "internet", "bath", "phone", "parking" };
		accommodationOne.setAttributes(Arrays.asList(accommodatioOneAtt));
		
		Accommodation accommodationTwo = new Accommodation();
		accommodationTwo.setId(2);
		accommodationTwo.setName("test accommodation two");
		accommodationTwo.setPrice(100);
		Capacity accomTwoCapacity = new Capacity();
		accomTwoCapacity.setFree(20);
		accomTwoCapacity.setTotal(30);
		accommodationTwo.setCapacity(accomTwoCapacity);
		String[] accommodatioTwoAtt = { "internet", "bath" };
		accommodationTwo.setAttributes(Arrays.asList(accommodatioTwoAtt));
		accommodationList = new LinkedList<Accommodation>();
		accommodationList.add(accommodation);
		accommodationList.add(accommodationOne);
		accommodationList.add(accommodationTwo);
	}

}
