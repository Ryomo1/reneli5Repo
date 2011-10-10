package com.accommodationmatches.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking {
	private int id;
	private Map<Accommodation, List<Traveller>> accommodationTravellerMap;

	public Booking(){
		accommodationTravellerMap = new HashMap<Accommodation, List<Traveller>>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Accommodation, List<Traveller>> getAccommodationTravellerMap() {
		return accommodationTravellerMap;
	}

	public void setAccommodationTravellerMap(
			Map<Accommodation, List<Traveller>> accommodationTravellerMap) {
		this.accommodationTravellerMap = accommodationTravellerMap;
	}

}
