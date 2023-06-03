package com.si.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.si.consts.DATE_PATTERN;
import com.si.consts.Log;
import com.si.helper.DateHelper;
import com.si.helper.LogHelper;

public class SI_Log {

	@Expose
	protected String logId;
	@Expose
	protected String service;
	@Expose
	protected ProcessInfo processInfo;

	protected JsonObject fromInput;
	protected String fromOutput;
	protected String midInput;
	protected String midOutput;
	protected String toInput;
	protected String toOutput;

	@Expose
	protected String errorCode;
	@Expose
	protected String errorMessage;
//	@Expose
	protected Object stacktrace;

	@Expose
	protected String logCode;
	@Expose
	protected String parentCode;

	@Expose
	protected Timing timing;
	@Expose
	protected System system;

	public SI_Log() {
		this.logCode = UUID.randomUUID().toString();

		this.timing = new Timing();
		this.timing.setFromStart(getCurrentTime());
	}

	public String getCurrentTime() {
		return DateHelper.convertDateToString(new Date(), DATE_PATTERN.TIME_S3);
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorCode = errorInfo.getErrorCode();
		this.errorMessage = errorInfo.getMessage();
		this.stacktrace = errorInfo.getStacktrace();
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	public void setProcessInfo(String processInfo) {
		this.processInfo = new Gson().fromJson(processInfo, ProcessInfo.class);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	public String getService() {
		return service;
	}

	public void setServiceName(String service) {
		this.service = service;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public JsonObject getFromInput() {
		return fromInput;
	}

	public void setFromInput(JsonObject fromInput) {
		this.fromInput = fromInput;
	}

	public void setFromInput(FromInput fromInput) {
		this.fromInput = new Gson().toJsonTree(fromInput).getAsJsonObject();
		LogHelper.writeLog(Log.INFO, Log.FROM_INPUT, this.service, this.fromInput.toString());
	}

	// update service version to FromInput
	public void setServiceVersion(String version) {
		this.fromInput.addProperty("serviceVersion", version);
	}

	public String getFromOutput() {
		return fromOutput;
	}

	public void setFromOutput(String fromOutput) {
		this.fromOutput = fromOutput;
	}

	public String getMidInput() {
		return midInput;
	}

	public void setMidInput(String midInput) {
		this.midInput = midInput;
	}

	public String getMidOutput() {
		return midOutput;
	}

	public void setMidOutput(String midOutput) {
		this.midOutput = midOutput;
	}

	public String getToInput() {
		return toInput;
	}

	public void setToInput(String toInput) {
		this.toInput = toInput;
		this.timing.setToStart(getCurrentTime());
	}

	public String getToOutput() {
		return toOutput;
	}

	public void setToOutput(String toOutput) {
		this.timing.setToFinish(getCurrentTime());
		this.toOutput = toOutput;
	}

	public Object getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(Object stacktrace) {
		this.stacktrace = stacktrace;
	}

	public Timing getTiming() {
		return timing;
	}

	public void setTiming(Timing timing) {
		this.timing = timing;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public void setSystem(String from, String mid, String to) {
		this.system = new System(from, mid, to);
	}

	public void setService(String service) {
		this.service = service;
	}

	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}
}
