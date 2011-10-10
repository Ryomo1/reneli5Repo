package com.accommodationmatches.service.impl;

import java.util.List;

import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Capacity;
import com.accommodationmatches.domain.PriceRange;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.AccommodationMatcherService;

public class AccommodationMatcherServiceImpl implements
		AccommodationMatcherService {

	public Accommodation findAccommodationLocation(Traveller traveller, List<Accommodation> accommodationList) {
		Accommodation cheapestAccommodation = null;
		for(int i = 0; i < accommodationList.size(); i++){
			Accommodation currentAccommodation = accommodationList.get(i);
			if(i == 0){
				cheapestAccommodation = currentAccommodation;
			}
			if(isWithinPriceRange(traveller, currentAccommodation) && meetRequirements(traveller, currentAccommodation) && hasFreeCapacity(currentAccommodation)){
				if(currentAccommodation.getPrice() <= cheapestAccommodation.getPrice()){
					cheapestAccommodation = currentAccommodation;
				}
			}
		}
		return cheapestAccommodation;
	}

	public void adjustFreeCapacityForAccommodation(Accommodation accommodation) {
		accommodation.getCapacity().setFree(accommodation.getCapacity().getFree() - 1);
	}
	
	private boolean isWithinPriceRange(Traveller traveller, Accommodation accommodation){
		PriceRange priceRange = traveller.getPriceRange();
		int price = accommodation.getPrice();
		if(price >= priceRange.getMin() && price <= priceRange.getMax()){
			return true;
		}
		return false;
	}
	
	private boolean meetRequirements(Traveller traveller, Accommodation accommodation){
		boolean requirementSatisfied = false;
		for(String requirement : traveller.getRequirements()){
			if(accommodation.getAttributes().contains(requirement)){
				requirementSatisfied = true;
			}else{
				return false;
			}
		}
		return requirementSatisfied;
	}
	
	private boolean hasFreeCapacity(Accommodation accommodation){
		Capacity capacity = accommodation.getCapacity();
		if(capacity.getFree() > 0){
			return true;
		}
		return false;
	}

}
