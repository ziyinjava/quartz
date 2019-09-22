package com.lidahai.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String dateToString(Date date, String string) {
		DateFormat dateFormat = new SimpleDateFormat(string);
		String result = dateFormat.format(date);
		return result;
	}

	public static String dateToString(Date date) {
		dateToString(date,"yyyy-MM-dd hh:mm:ss:SSS");
		return null;
	}

}
