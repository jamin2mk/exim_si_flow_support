package com.si.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.DatatypeConverter;

import com.si.consts.DATE_PATTERN;

public class DateHelper {
	
	public static Date convertStringToDate(String date, String format) throws ParseException {

		Date dateResult = null;
		String dateString = null;

		if (date != null & !date.trim().isEmpty() && !date.equalsIgnoreCase("0")) {

			DateFormat simpleDateFormat = new SimpleDateFormat(format);

			if (format.equalsIgnoreCase(DATE_PATTERN.SHORT_DATE_6)) {
				dateString = date.length() == 6 ? date : ("0" + date);
			} else {
				dateString = date;
			}

			dateResult = simpleDateFormat.parse(dateString);
		}

		return dateResult;
	}

	public static Calendar convertStringToCalendar(String date, String format) throws ParseException {

		Calendar result = Calendar.getInstance();

		Date dateResult = null;
		String dateString = null;

		if (date != null & !date.trim().isEmpty() && !date.equalsIgnoreCase("0")) {

			DateFormat simpleDateFormat = new SimpleDateFormat(format);

			if (format.equalsIgnoreCase(DATE_PATTERN.SHORT_DATE_6)) {
				dateString = date.length() == 6 ? date : ("0" + date);
			} else {
				dateString = date;
			}
			dateResult = simpleDateFormat.parse(dateString);
		}
		result.setTime(dateResult);

		return result;
	}

	public static Calendar convertBpmDateStringToCalendar(String date) throws ParseException {

		Calendar result = Calendar.getInstance();

		Date dateResult = null;

		if (date != null & !date.trim().isEmpty() && !date.equalsIgnoreCase("0")) {

			LocalDateTime ldt = LocalDateTime.parse(date.endsWith(".0") ? date.replace(".0", "") : date, DateTimeFormatter.ofPattern(DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET));
			Instant instant = ldt.atZone(ZoneId.of("UTC-0")).toInstant();
			dateResult = Date.from(instant);
		}
		result.setTime(dateResult);
		return result;
	}

	public static String convertDateToString(Date date, String format) {

		String result = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		result = simpleDateFormat.format(date);

		return result;
	}

	public static String convertDateString(String dateString, String originalFormat, String targetFormat) throws ParseException {

		String result = null;

		if (dateString != null && !dateString.trim().isEmpty()) {

			Date date = convertStringToDate(dateString, originalFormat);
			DateFormat dateFormat = new SimpleDateFormat(targetFormat);
			result = dateFormat.format(date);
		}

		return result;
	}

	public static String convertBpmDateString(String dateString, String targetFormat) throws ParseException {

		String result = null;

		if (dateString != null && !dateString.trim().isEmpty()) {

			LocalDateTime ldt = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
			System.out.println(ldt);
			ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC-0"));
			System.out.println(zdt);

			DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetFormat).withZone(ZoneId.of("UTC+7"));
			result = targetFormatter.format(zdt);
		}

		return result;
	}

	public static String convertDateToBpmDateString(String dateString, String originFormat, String targetFormat) throws ParseException {

		String result = null;

		if (dateString != null && !dateString.trim().isEmpty()) {

			LocalDateTime ldt = LocalDateTime.parse(dateString.endsWith(".0") ? dateString.replace(".0", "") : dateString, DateTimeFormatter.ofPattern(originFormat));
			ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC+7"));

			DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetFormat).withZone(ZoneId.of("UTC+0"));
			result = targetFormatter.format(zdt);
		}

		return result;
	}

	public static String getCurrentDate(String format) {
		return DateHelper.convertDateToString(new Date(), format);
	}

	public static String convertDateToLastDayOfMonth(String dateString, String originalFormat, String targetFormat) throws ParseException {

		String result = null;

		Date date = new SimpleDateFormat(originalFormat).parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, lastDayOfMonth);

		result = DateHelper.convertDateToString(calendar.getTime(), targetFormat);
		return result;
	}

	public static String addingDate(String dateString, String format, int field, int value) throws ParseException {

		String result = null;

		Date date = new SimpleDateFormat(format).parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, value);

		result = DateHelper.convertDateToString(calendar.getTime(), format);
		return result;
	}

	public static Date addingDate_AsDate(String dateString, String format, int field, int value) throws ParseException {

		Date date = new SimpleDateFormat(format).parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, value);

		return calendar.getTime();
	}

	public static Date addingDate_AsDate(Date date, int field, int value) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, value);

		return calendar.getTime();
	}

	public static int getNumberOfYear(String dateString, String format) throws ParseException {

		int numberOfYears;

		Calendar now = Calendar.getInstance();

		Date date = new SimpleDateFormat(format).parse(dateString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		numberOfYears = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		if (cal.get(Calendar.MONTH) > now.get(Calendar.MONTH) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DATE) > now.get(Calendar.DATE))) {
			numberOfYears--;
		}

		return numberOfYears;
	}

	public static long getNumberOfDaysBetweenTwoDate(Date dateBefore, Date dateAfter) {

		long dateBeforeInMs = dateBefore.getTime();
		long dateAfterInMs = dateAfter.getTime();

		long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
		long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

		return daysDiff;
	}

	// 30.Dec.2022
	public static int getNumberOfYearsBetweenTwoDate(Date dateBefore, Date dateAfter) {

		int numberOfYears;

		Calendar now = Calendar.getInstance();
		now.setTime(dateAfter);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateBefore);

		numberOfYears = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		return numberOfYears;
	}

	public static int getNumberOfMonthsBetweenTwoDate(Date dateBefore, Date dateAfter) {

		int numberOfMonths;

		Calendar now = Calendar.getInstance();
		now.setTime(dateAfter);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateBefore);

		numberOfMonths = now.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
		return numberOfMonths;
	}
	// new Convert by Vuongnht
	public static String convertDateString(String dateString, String targetFormat)
			throws ParseException {
		String result = null;
		if (dateString != null && !dateString.trim().isEmpty()) {
			Calendar parseDateTime = DatatypeConverter.parseDateTime(dateString);
			Date date = parseDateTime.getTime();
			DateFormat dateFormat = new SimpleDateFormat(targetFormat);
			result = dateFormat.format(date);
		}
		return result;
	}
	public static void main(String[] args) throws ParseException {
		System.out.println(convertBpmDateString("2023-05-29T17:55:57.081+07:00", "dd-MM-YYYY"));
		System.out.println(convertDateString("2023-05-29T17:55:57.081+07:00", "dd-MM-YYYY"));
	}
}
