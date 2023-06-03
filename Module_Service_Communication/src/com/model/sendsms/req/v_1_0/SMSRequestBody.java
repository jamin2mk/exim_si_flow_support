package com.model.sendsms.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SMSRequest")
public class SMSRequestBody {

	@SerializedName("ClientId")
	@XmlElement(name = "ClientId")
	protected String clientId;
	
	@SerializedName("ReferenceNo")
	@XmlElement(name = "ReferenceNo")
	protected String referenceNo;
	
	@SerializedName("SendType")
	@XmlElement(name = "SendType")
	protected String sendType;
	
	@SerializedName("PhoneNumber")
	@XmlElement(name = "PhoneNumber")
	protected String phoneNumber;

	@SerializedName("OTTPhoneNumber")
	@XmlElement(name = "OTTPhoneNumber")
	protected String ottPhoneNumber;

	@SerializedName("Message")
	@XmlElement(name = "Message")
	protected String message;
	
	@SerializedName("CustId")
	@XmlElement(name = "CustId")	
	protected String custId;
	
	@SerializedName("BranchCode")
	@XmlElement(name = "BranchCode")
	protected String branchCode;
	
	@SerializedName("RequestTime")
	@XmlElement(name = "RequestTime")
	protected String requestTime;

	public SMSRequestBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SMSRequestBody(String clientId, String referenceNo, String sendType,
			String phoneNumber, String ottPhoneNumber, String message,
			String custId, String branchCode, String requestTime) {
		super();
		this.clientId = clientId;
		this.referenceNo = referenceNo;
		this.sendType = sendType;
		this.phoneNumber = phoneNumber;
		this.ottPhoneNumber = ottPhoneNumber;
		this.message = message;
		this.custId = custId;
		this.branchCode = branchCode;
		this.requestTime = requestTime;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOttPhoneNumber() {
		return ottPhoneNumber;
	}

	public void setOttPhoneNumber(String ottPhoneNumber) {
		this.ottPhoneNumber = ottPhoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

}
