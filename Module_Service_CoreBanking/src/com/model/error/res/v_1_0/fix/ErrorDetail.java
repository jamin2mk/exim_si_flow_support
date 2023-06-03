package com.model.error.res.v_1_0.fix;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorDetail", propOrder = {"errorCode","errorDesc","errorSource","errorType"}, namespace = "http://www.finacle.com/fixml")
public class ErrorDetail {

	@XmlElement(name = "ErrorCode", namespace = "http://www.finacle.com/fixml")
	protected String errorCode;
	@XmlElement(name = "ErrorDesc", namespace = "http://www.finacle.com/fixml")
	protected String errorDesc;
	@XmlElement(name = "ErrorSource", namespace = "http://www.finacle.com/fixml")
	protected String errorSource;
	@XmlElement(name = "ErrorType", namespace = "http://www.finacle.com/fixml")
	protected String errorType;

	public ErrorDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorDetail(String errorCode, String errorDesc, String errorSource,
			String errorType) {
		super();
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.errorSource = errorSource;
		this.errorType = errorType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

}
