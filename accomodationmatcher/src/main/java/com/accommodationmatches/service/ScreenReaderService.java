package com.accommodationmatches.service;

import java.io.IOException;

public interface ScreenReaderService {
	/*
	 * read one line from screen
	 */
	String readLine()throws IOException;
}
