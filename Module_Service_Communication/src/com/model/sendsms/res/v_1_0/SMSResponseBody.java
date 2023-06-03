package com.model.sendsms.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SMSResponse")
public class SMSResponseBody {

	@XmlElement(name = "ReferenceNo")
	protected String referenceNo;
	@XmlElement(name = "ErrorCode")
	protected String errorCode;
	@XmlElement(name = "ErrorDesc")
	protected String errorDesc;
	@XmlElement(name = "ResponseTime")
	protected String responseTime;

	public SMSResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SMSResponseBody(String referenceNo, String errorCode,
			String errorDesc, String responseTime) {
		super();
		this.referenceNo = referenceNo;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.responseTime = responseTime;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

}
