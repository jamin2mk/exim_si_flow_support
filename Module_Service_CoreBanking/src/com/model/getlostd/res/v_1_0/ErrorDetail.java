package com.model.getlostd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ERRORDETAIL", namespace = "http://www.finacle.com/fixml", propOrder = { "errorCode", "errorDesc" })
public class ErrorDetail {

	@XmlElement(name = "ERRORCODE", namespace = "http://www.finacle.com/fixml")
	protected String errorCode;

	@XmlElement(name = "ERRORDESC", namespace = "http://www.finacle.com/fixml")
	protected String errorDesc;

	public ErrorDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorDetail(String errorCode, String errorDesc) {
		super();
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
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

}
