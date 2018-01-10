package com.github.przemyslawkonik.bean;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class Time {

	DateTime dateTime;

	public Time() {
		dateTime = new DateTime();
	}

	public int getYear() {
		return dateTime.getYear();
	}

	public int getMonth() {
		return dateTime.getMonthOfYear();
	}

}
