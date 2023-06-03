package com.si.helper;

import java.text.MessageFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.model.ErrorInfo;
import com.si.model.SI_Log;

public class ResultHelper {

	public static String getResultAsSuccess(JsonElement data, SI_Log log) {

		ErrorInfo errorInfo = new ErrorInfo(Error.SUCCESS_CODE_VALUE, Error.SUCCESS_MESSAGE_VALUE, null);
		log.setErrorInfo(errorInfo);

		JsonObject result = new JsonObject();
		result.add("data", data);
		result.add("errorInfo", new Gson().toJsonTree(errorInfo));

		return result.toString();
	}

	public static String getResultWithMessage(String errorCode, String message, SI_Log log) {

		Gson gson = new Gson();
		ErrorInfo errorInfo = new ErrorInfo(MessageFormat.format("SV-{0}", errorCode), message, null);
		log.setErrorInfo(errorInfo);

		JsonObject errorInfo_JS = (JsonObject) gson.toJsonTree(errorInfo);

		JsonObject result = new JsonObject();
		result.add("data", null);
		result.add("errorInfo", errorInfo_JS);

		return result.toString();
	}

	public static String getResultWithException(Exception ex, SI_Log log) {

		Gson gson = new Gson();
		JsonElement stacktrace = gson.toJsonTree(ex.getStackTrace());

		String exMessage = ex.getMessage() != null ? ex.getMessage() : "Unknown";
		String errorMessage = String.format("[%s] %s: %s", log.getService(), ex.getClass().getSimpleName(), exMessage);
		ErrorInfo errorInfo = new ErrorInfo("EX-99", errorMessage, stacktrace);
		log.setErrorInfo(errorInfo);

		JsonObject errorInfo_JS = (JsonObject) gson.toJsonTree(errorInfo);
		errorInfo_JS.add("stacktrace", null);

		JsonObject result = new JsonObject();
		result.add("data", null);
		result.add("errorInfo", errorInfo_JS);

		return result.toString();
	}

	public static String getResultWithException(NoClassDefFoundError ex, SI_Log log) {

		Gson gson = new Gson();
		JsonElement stacktrace = gson.toJsonTree(ex.getStackTrace());

		String exMessage = ex.getMessage() != null ? ex.getMessage() : "Unknown";
		String errorMessage = String.format("[%s] %s: %s", log.getService(), ex.getClass().getSimpleName(), exMessage);
		ErrorInfo errorInfo = new ErrorInfo("EX-99", errorMessage, stacktrace);
		log.setErrorInfo(errorInfo);

		JsonObject errorInfo_JS = (JsonObject) gson.toJsonTree(errorInfo);
		errorInfo_JS.add("stacktrace", null);

		JsonObject result = new JsonObject();
		result.add("data", null);
		result.add("errorInfo", errorInfo_JS);

		return result.toString();
	}

	public static String getResultWithException(SIException ex, SI_Log log) {

		Gson gson = new Gson();
		JsonElement stacktrace = gson.toJsonTree(ex.getStackTrace());

		String exMessage = ex.getErrorMessage() != null ? ex.getErrorMessage() : String.format("%s: Unknown", ex.getClass().getSimpleName());
		String errorMessage = String.format("[%s] %s", log.getService(), exMessage);
		ErrorInfo errorInfo = new ErrorInfo(ex.getErrorCode(), errorMessage, stacktrace);
		log.setErrorInfo(errorInfo);

		JsonObject errorInfo_JS = (JsonObject) gson.toJsonTree(errorInfo);
		errorInfo_JS.add("stacktrace", null);

		JsonObject result = new JsonObject();
		result.add("data", null);
		result.add("errorInfo", errorInfo_JS);

		return result.toString();
	}

	public static String addLogIdToErrorInfo(String result, SI_Log log) {

		Gson gson = new GsonBuilder().serializeNulls().create();

		if (result == null) {
			result = getResultWithException(new Exception("Runtime error - not cached"), log);
		}

		JsonObject result_JO = gson.fromJson(result, JsonObject.class);
		JsonObject errorInfo = result_JO.get("errorInfo").getAsJsonObject();

		JsonElement message = errorInfo.get("message");
		if (!message.isJsonNull() && !message.getAsString().trim().isEmpty()) {
			errorInfo.addProperty("message", MessageFormat.format("{0} [LOG_ID:{1}]", message.getAsString(), log.getLogId()));
		} else {
			errorInfo.addProperty("message", MessageFormat.format("[LOG_ID:{0}]", log.getLogId()));
		}

		return result_JO.toString();
	}

	public static String addSiBreaking(String group, String service, String result) {

		JsonObject resultAIS = new Gson().fromJson(result, JsonObject.class);

		JsonObject si_breaking = new JsonObject();
		si_breaking.addProperty("group", group);
		si_breaking.addProperty("service", service);

		resultAIS.add("brokenPoint", si_breaking);
		return resultAIS.toString();
	}
}
