package com.si.exception;

import java.text.MessageFormat;

public class SIException extends Exception {

	private static final long serialVersionUID = -1378667676916876035L;

	public String errorCode;
	public String errorMessage;
	public String service;


	public SIException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public SIException(String errorCode, String errorMessage, Exception ex) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = MessageFormat.format("{0}. Cause by: [{1}] {2}", errorMessage, ex.getClass(), ex.getMessage());
		super.setStackTrace(ex.getStackTrace());
	}

	public SIException(String errorCode, String errorMessage, String service) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.service = service;
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

}
