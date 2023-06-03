package com.si.helper;

import java.text.MessageFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.si.config.SI;
import com.si.consts.Log;
import com.si.model.SI_Log;

public class LogHelper {

//	public static void writeLogInput(int logLevel, String... inputs) {
//
//		StringBuilder inputValue = new StringBuilder(inputs[0]);
//		for (int i = 1; i < inputs.length; i++) {
//			inputValue.append("::").append(inputs[i]);
//		}
//
//		LogHelper.writeLog(logLevel, Log.INFO, Log.FROM_INPUT, inputValue.toString());
//	}

	public static void writeLog(String logType, String logger, String... values) {
		
		switch (logger) {
//		switch ("") {

		/* LOG LEVEL 1 - ALWAYS DISPLAY */
		case Log.FROM_INPUT:
		case Log.FROM_OUTPUT:
//		case Log.SP_RESULT:
//		case Log.RULE_RESULT:
//		case Log.CALLING_ODM_REST_API:
			System.out.println(Log.LINE_HYPHEN);
			System.out.println(String.format("[%s] %s", logType, logger));
			for (String value : values) {
				System.out.println(String.format("[%s] %s", logType, value));
			}
			break;

		/* LOG LEVEL 2 - DISPLAY WHEN [logLevel >= 2] */
		case Log.TO_INPUT:
		case Log.TO_OUTPUT:
			if (SI.LOG_LEVEL >= 2) {
				System.out.println(Log.LINE_HYPHEN);
				System.out.println(String.format("[%s] %s", logType, logger));
				for (String value : values) {
					if (!value.isEmpty()) {
						System.out.println(String.format("[%s] %s", logType, value));
					}
				}
			}
			break;

		/* LOG LEVEL 3 - DISPLAY WHEN [logLevel >= 3] */
		case Log.MAP_INFO:
		case Log.CONVERTED_INFO:
		case Log.CDATA_RESULT:
		case Log.CDATA_CALL_FROM_DB:
		case Log.BEFORE_CONVERTING_CODE:
		case Log.AFTER_CONVERTING_CODE:
			if (SI.LOG_LEVEL >= 3) {
				System.out.println(Log.LINE_HYPHEN);
				System.out.println(String.format("[%s] %s", logType, logger));
				for (String value : values) {
					if (!value.isEmpty()) {
						System.out.println(String.format("[%s] %s", logType, value));
					}
				}
			}
			break;

		/* LOG LEVEL 4 - DISPLAY WHEN [logLevel >= 4] */
//		case Log.PROPERTIES_READING:		
		case Log.DB_SQL:
		case Log.SQL_RESULT:
		case Log.DB_PROCEDURE_PARAMS:
		case Log.FUCTION_RESULT:
			if (SI.LOG_LEVEL >= 4) {
				System.out.println(Log.LINE_HYPHEN);
				System.out.println(String.format("[%s] %s", logType, logger));
				for (String value : values) {
					if (!value.isEmpty()) {
						System.out.println(String.format("[%s] %s", logType, value));
					}
				}
			}
			break;

		/* LOG LEVEL 5 - DISPLAY WHEN [logLevel >= 5] */
//		case Log.EXECUTING_MAPPING:
		case Log.DB_CONNECT_SUCCESS:
			if (SI.LOG_LEVEL >= 5) {
				System.out.println(Log.LINE_HYPHEN);
				System.out.println(String.format("[%s] %s", logType, logger));
				for (String value : values) {
					if (!value.isEmpty()) {
						System.out.println(String.format("[%s] %s", logType, value));
					}
				}
			}
			break;
		default:
			break;
		}
	}

	public static void printStacktrace(Exception ex) {
		if (SI.LOG_LEVEL >= 4) {
			ex.printStackTrace();
		}
	}
	
	public static void printStacktrace(NoClassDefFoundError ex) {
		if (SI.LOG_LEVEL >= 4) {
			ex.printStackTrace();
		}
	}

	// 2022-10-20: USING
	public static void writeLogToDB(SI_Log log, String logDataSource) {

		log.getTiming().setFromFinish(log.getCurrentTime());
		if (log.getErrorMessage() != null) {
			log.setErrorMessage(log.getErrorMessage().replaceAll("\\|", "-"));
		}
		if (log.getFromOutput() != null) {
			log.setFromOutput(log.getFromOutput().replaceAll("\\|", "-"));
		}
		if (log.getToOutput() != null) {
			log.setToOutput(log.getToOutput().replaceAll("\\|", "-"));
		}
		
		if (log.getFromInput() != null) {
			String fromInput = new Gson().toJson(log.getFromInput());
			fromInput = fromInput.replaceAll("\\|", "-");
			log.setFromInput(new Gson().fromJson(fromInput, JsonObject.class));
		}
		if (log.getToInput() != null) {
			log.setToInput(log.getToInput().replaceAll("\\|", "-"));
		}

		String siLog = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create().toJson(log);

		String logData = MessageFormat.format("{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}", siLog, log.getFromInput(), log.getFromOutput(), log.getMidInput(), log.getMidOutput(), log.getToInput(), log.getToOutput(), log.getStacktrace());
		String logResult = null;
	
		try {
			// --> insert log//
			logResult = JDBC_Helper.updateByProcedure_out1varchar(SI.LOG_INSERT_SP, logData, logDataSource);
		} catch (Exception e) {
			logResult = e.getMessage();
		}

		log.setLogId(logResult);
	}

	public static void writeLogResponse(Object objectResponse, String service) {
		System.out.println(MessageFormat.format("[INFO - AIS] Json Result - {0}:", service));
		JsonObject json = (JsonObject) new Gson().toJsonTree(objectResponse);
		json.remove("SI_Log");
		System.out.println(json);
	}
}
