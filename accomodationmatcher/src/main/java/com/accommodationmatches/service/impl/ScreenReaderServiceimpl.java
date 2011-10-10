package com.accommodationmatches.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.accommodationmatches.service.ScreenReaderService;

public class ScreenReaderServiceimpl implements ScreenReaderService {
	private BufferedReader reader;
	
	public ScreenReaderServiceimpl(){
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	/*
	 * (non-Javadoc)
	 * @see com.accommodationmatches.service.ScreenReaderService#readLine()
	 */
	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			//bad -- log it or do something
			e.printStackTrace();
		}
		return null;
	}

}
