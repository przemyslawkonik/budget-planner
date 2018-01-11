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

	public int getNextYear(int year, int month) {
		if (month + 1 > 12) {
			return year + 1;
		}
		return year;
	}

	public int getNextMonth(int month) {
		if (month + 1 > 12) {
			return 1;
		}
		return month + 1;
	}

}
