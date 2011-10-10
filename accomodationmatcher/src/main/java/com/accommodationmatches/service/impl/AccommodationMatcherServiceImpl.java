package com.accommodationmatches.service.impl;

import java.io.FileNotFoundException;
import java.util.List;

import com.accommodationmatches.dao.DomainObjectDao;
import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Capacity;
import com.accommodationmatches.domain.PriceRange;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.service.AccommodationMatcherService;

public class AccommodationMatcherServiceImpl implements
		AccommodationMatcherService {
	private DomainObjectDao domainObjectDao;

	public AccommodationMatcherServiceImpl(DomainObjectDao domainObjectDao){
		this.domainObjectDao = domainObjectDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.service.AccommodationMatcherService#findAccommodationLocation(com.accommodationmatches.domain.Traveller)
	 */
	public Accommodation findAccommodationLocation(Traveller traveller) throws FileNotFoundException {
		Accommodation cheapestAccommodation = null;
		for(int i = 0; i < domainObjectDao.getAccommodationList().size(); i++){
			Accommodation currentAccommodation = domainObjectDao.getAccommodationList().get(i);
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
	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.service.AccommodationMatcherService#adjustFreeCapacityForAccommodation(com.accommodationmatches.domain.Accommodation)
	 */
	public void adjustFreeCapacityForAccommodation(Accommodation accommodation) {
		accommodation.getCapacity().setFree(accommodation.getCapacity().getFree() - 1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.service.AccommodationMatcherService#getNextTravellerId()
	 */
	public long getNextTravellerId() throws FileNotFoundException {
		return domainObjectDao.getTravellersList().size() + 1;
	}
	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.service.AccommodationMatcherService#getAllAccommodations()
	 */
	public List<Accommodation> getAllAccommodations()
			throws FileNotFoundException {
		return domainObjectDao.getAccommodationList();
	}

	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.service.AccommodationMatcherService#getAllTravellers()
	 */
	public List<Traveller> getAllTravellers() throws FileNotFoundException {
		return domainObjectDao.getTravellersList();
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
