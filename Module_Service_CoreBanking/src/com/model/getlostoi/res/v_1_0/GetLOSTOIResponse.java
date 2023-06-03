package com.model.getlostoi.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetLOSTOIResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSTOIResponse {

	@XmlElement(name = "TOIList")
	protected TOIList toiList;
	@XmlElement(name = "ErrorCode")
	protected String errorCode;
	@XmlElement(name = "ErrorDesc")
	protected String errorDesc;

	public GetLOSTOIResponse(TOIList toiList, String errorCode, String errorDesc) {
		super();
		this.toiList = toiList;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public GetLOSTOIResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TOIList getToiList() {
		return toiList;
	}

	public void setToiList(TOIList toiList) {
		this.toiList = toiList;
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
