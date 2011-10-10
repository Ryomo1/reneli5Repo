package com.accommodationmatches.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReaderUtil {
	public static BufferedReader getBufferedReaderFromFile(String filePath) throws FileNotFoundException{
		BufferedReader reader = null;
		File file = new File(filePath);
		if(file.exists()){
			reader = new BufferedReader(new FileReader(filePath));
		}
		return reader;
	}
}
