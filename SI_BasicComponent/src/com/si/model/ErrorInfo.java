package com.si.model;

public class ErrorInfo {

	protected String errorCode;
	protected String message;
	protected Object stacktrace;

	public ErrorInfo() {
		// TODO Auto-generated constructor stub
	}

	public ErrorInfo(String errorCode, String message, Object stacktrace) {
		this.errorCode = errorCode;
		this.message = message;
		this.stacktrace = stacktrace;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(Object stacktrace) {
		this.stacktrace = stacktrace;
	}

}
