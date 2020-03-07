package com.allianz.tracker.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.allianz.tracker.exception.TimeTrackerBadDataException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateUtils {

	/**
	 * @param date
	 * @param field
	 * @return
	 */
	public String parseToUIDateFormat(String dateValue, String field) {
		String uiDateFormat = "dd.MM.yyyy hh:mm";
		String backendDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		Date date = parseFromBackendDateField(dateValue, field, backendDateFormat);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(uiDateFormat);
		return simpleDateFormat.format(date);
	}

	/**
	 * @param dateValue
	 * @param field
	 * @return
	 */
	public String parseToBackendDateFormat(String dateValue, String field) {
		String uiDateFormat = "yyyy-MM-dd'T'hh:mm";
		String backendDateFormat = "dd.MM.yyyy hh:mm";
		Date date = parseFromBackendDateField(dateValue, field, uiDateFormat);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(backendDateFormat);
		return simpleDateFormat.format(date);
	}

	
	/**
	 * @param dateValue
	 * @param field
	 * @return
	 */
	public Date parseToBackendDate(String dateValue, String field) {
		return parseFromBackendDateField(dateValue, field, "dd.MM.yyyy hh:mm");
	}
	
	/**
	 * @param dateValue
	 * @param field
	 * @param format
	 * @return
	 */
	private Date parseFromBackendDateField(String dateValue, String field, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(dateValue);
		} catch (ParseException e) {
			throw new TimeTrackerBadDataException("Please enter " + field + " in valid date format " + format);
		}

		return date;
	}

}
