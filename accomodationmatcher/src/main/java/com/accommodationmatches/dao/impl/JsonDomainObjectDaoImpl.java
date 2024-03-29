package com.accommodationmatches.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.accommodationmatches.dao.DomainObjectDao;
import com.accommodationmatches.domain.Accommodation;
import com.accommodationmatches.domain.Traveller;
import com.accommodationmatches.util.ReaderUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonDomainObjectDaoImpl implements DomainObjectDao {
	private String travellerFile;
	private String accommodationFile;
	private static LinkedList<Traveller> travellers;
	private static LinkedList<Accommodation> accommodations;
	
	public JsonDomainObjectDaoImpl(String travellerFile, String accommodationFile){
		this.travellerFile = travellerFile;
		this.accommodationFile = accommodationFile;
	}
	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.dao.DomainObjectDao#getTravellersList()
	 */
	public List<Traveller> getTravellersList() throws FileNotFoundException {
		if(travellers == null){
			Gson gson = new Gson();
			String travellerFilePath = getFileFromClassPath(travellerFile);
			BufferedReader travellerReader = ReaderUtil.getBufferedReaderFromFile(travellerFilePath);
			Type collectionType = new TypeToken<LinkedList<Traveller>>(){}.getType();
			travellers = gson.fromJson(travellerReader, collectionType);
		}
		return travellers;
	}

	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.dao.DomainObjectDao#getAccommodationList()
	 */
	public List<Accommodation> getAccommodationList() throws FileNotFoundException {
		if(accommodations == null){
			Gson gson = new Gson();
			String accommodationFilePath = getFileFromClassPath(accommodationFile);
			BufferedReader accommodationReader = ReaderUtil.getBufferedReaderFromFile(accommodationFilePath);
			Type collectionType = new TypeToken<LinkedList<Accommodation>>(){}.getType();
			accommodations = gson.fromJson(accommodationReader, collectionType);
		}
		return accommodations;
	}
	
	/*
	 * 
	 */
	public String getFileFromClassPath(String fileName) {
		String projectRoot = new java.io.File("").getAbsolutePath();
		String filePath =  projectRoot + fileName;
		File file = new File(filePath);
		if(file.exists()){
			return filePath;
		}else{
			return projectRoot + "/resources/data/" + file.getName();
		}
		
	}
}
