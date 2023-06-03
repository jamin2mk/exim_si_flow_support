package com.si.model;

public class ResultAIS {

	private Object data;
	private ErrorInfo errorInfo;
	private BrokenPoint brokenPoint;

	public Object getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public BrokenPoint getBrokedPoint() {
		return brokenPoint;
	}

	public void setBrokedPoint(BrokenPoint brokenPoint) {
		this.brokenPoint = brokenPoint;
	}

}
