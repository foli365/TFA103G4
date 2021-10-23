package com.campsite.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSQLDate {
	public static Date convert(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = null;
		try {
			utilDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
}
