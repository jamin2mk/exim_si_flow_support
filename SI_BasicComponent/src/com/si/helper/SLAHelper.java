package com.si.helper;

//import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.si.consts.DATE_PATTERN;
//import com.si.consts.DATE_PATTERN;
import com.si.consts.Error;
import com.si.exception.SIException;

public class SLAHelper {
	public static Date caculateTimeSlaCalendar(int time, Calendar calendar, String dataSource)
			throws SIException {
		JsonArray array;
		int timeRest = 30; // set cung tam thoi 30p
		long timeSLA = calendar.getTimeInMillis();
		String query = "SELECT * FROM adm_config_working_day WHERE year_in_date=? AND month_in_date=? AND day_in_date=? AND date_working_type=1 AND ROWNUM = 1";
		String params = calendar.get(Calendar.YEAR) + "|"
				+ (calendar.get(Calendar.MONTH) + 1) + "|"
				+ calendar.get(Calendar.DATE);
		try {
			array = JDBC_Helper.queryByPrepareStatement(query, params,
					dataSource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SIException(Error.DB_99, "Query Database Error");
		}
		if (array.size() == 0) {
			checkDay(calendar,dataSource);
			// date.setDate(date.getDate()+restDays);
			timeSLA = calendar.getTimeInMillis() + time * 60 * 1000;
			return new java.util.Date(timeSLA);
//			throw new SIException(Error.DB_99, "Data Error");
		}
		JsonObject obj = array.get(0).getAsJsonObject();
		int timeEndMorningMinute = (int)(obj.get("TIME_END_MORNING_INT").getAsDouble() * 60);
		int timeEndAlternoonMinute = (int)(obj.get("TIME_END_ALTERNOON_INT").getAsDouble() * 60);
		int timeStartMorningMinute = (int)(obj.get("TIME_START_MORNING_INT").getAsDouble() * 60);
		int timeStartAlternoonMinute = (int)(obj.get("TIME_START_ALTERNOON_INT").getAsDouble() * 60);

		if ((calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar
				.get(Calendar.MINUTE)) >= (timeEndMorningMinute - timeRest)) {
			if ((calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar
					.get(Calendar.MINUTE)) >= (timeEndAlternoonMinute - timeRest)) {
				calendar.set(Calendar.HOUR_OF_DAY, timeStartMorningMinute / 60);
				calendar.set(Calendar.MINUTE, timeStartMorningMinute % 60);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
				checkDay(calendar,dataSource);
				// date.setDate(date.getDate()+restDays);
				timeSLA = calendar.getTimeInMillis() + time * 60 * 1000;
			} else {
				if ((calendar.get(Calendar.HOUR_OF_DAY) * 60 + time + calendar
						.get(Calendar.MINUTE)) >= timeEndAlternoonMinute) {
					timeSLA = ((calendar.get(Calendar.HOUR_OF_DAY) * 60 + time + calendar
							.get(Calendar.MINUTE)) - timeEndAlternoonMinute) * 60 * 1000;
					calendar.set(Calendar.HOUR_OF_DAY,
							timeStartMorningMinute / 60);
					calendar.set(Calendar.MINUTE, timeStartMorningMinute % 60);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					checkDay(calendar,dataSource);
					// date.setDate(date.getDate()+restDays);
					timeSLA += calendar.getTimeInMillis();

				} else {
					if (calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE) < timeEndMorningMinute) {
						calendar.set(Calendar.HOUR_OF_DAY,
								timeStartAlternoonMinute / 60);
						calendar.set(Calendar.MINUTE, timeStartAlternoonMinute %60);
						calendar.set(Calendar.SECOND, 0);
						timeSLA = calendar.getTimeInMillis() + time * 60 * 1000;
					} else {
						timeSLA += time * 60 * 1000;
					}
				}
			}
		} else {
			if ((calendar.get(Calendar.HOUR_OF_DAY) * 60 + time + calendar
					.get(Calendar.MINUTE)) >= timeEndMorningMinute) {
				timeSLA = (time + calendar.get(Calendar.HOUR_OF_DAY) * 60
						+ calendar.get(Calendar.MINUTE) - timeEndMorningMinute) * 60 * 1000;
				calendar.set(Calendar.HOUR_OF_DAY,
						timeStartAlternoonMinute / 60);
				calendar.set(Calendar.MINUTE, timeStartAlternoonMinute % 60);
				calendar.set(Calendar.SECOND, 0);

				timeSLA += calendar.getTimeInMillis();
			} else {
				timeSLA += time * 60 * 1000;
			}
		}

		

		return new java.util.Date(timeSLA);
	}

	private static int checkDay(Calendar calendar, String dataSource) throws SIException {
		// TODO Auto-generated method stub
//		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN.HYPHEN_DATETIME);
		JsonArray array;
		int restDays = 0;
			String query = "SELECT * FROM adm_config_working_day WHERE full_date >= TO_DATE('?/?/?','dd/mm/yyyy') AND date_working_type=1 ORDER BY full_date FETCH FIRST 1 ROWS ONLY";
			String params = (calendar.get(Calendar.DATE)) + "/"
					+ (calendar.get(Calendar.MONTH) + 1) + "/"
					+ calendar.get(Calendar.YEAR);
			query=query.replace("?/?/?", params);
			try {
				array = JDBC_Helper.queryByPrepareStatement(query, null,
						dataSource);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new SIException(Error.DB_99, "Query Database Error");
			}
			JsonObject obj = array.get(0).getAsJsonObject();
			calendar.set(Calendar.DATE, obj.get("DAY_IN_DATE").getAsInt());
			calendar.set(Calendar.MONTH, obj.get("MONTH_IN_DATE").getAsInt()-1);
			calendar.set(Calendar.YEAR, obj.get("YEAR_IN_DATE").getAsInt());
			Calendar calendar2 = null;
			try {
				calendar2 = DateHelper.convertStringToCalendar(obj.get("TIME_START_MORNING").getAsString(),DATE_PATTERN.HYPHEN_DATETIME);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			calendar.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, 0);
		return restDays;
	}
	
	public static int caculateTotalTime(long miliseconds, boolean isRoundUp){
		
		long second = miliseconds/1000;
		
		long minute = second/60;
		second = second%60;
		
		if(isRoundUp && second>=30){
			minute+=1;
		}
		return (int) minute;
		
	}

}
