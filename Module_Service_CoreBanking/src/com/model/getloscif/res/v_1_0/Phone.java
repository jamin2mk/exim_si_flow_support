package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.Expose;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "phone", namespace = "http://www.finacle.com/fixml", propOrder = {
		"phoneType", "phonePrefer", "phoneNo" })
public class Phone {

	@Expose
	@XmlElement(name = "phoneType", namespace = "http://www.finacle.com/fixml")
	protected String phoneType;
	@Expose
	@XmlElement(name = "phonePrefer", namespace = "http://www.finacle.com/fixml")
	protected String phonePrefer;
	@Expose
	@XmlElement(name = "phoneNo", namespace = "http://www.finacle.com/fixml")
	protected String phoneNo;

	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phone(String phoneType, String phonePrefer, String phoneNo) {
		super();
		this.phoneType = phoneType;
		this.phonePrefer = phonePrefer;
		this.phoneNo = phoneNo;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhonePrefer() {
		return phonePrefer;
	}

	public void setPhonePrefer(String phonePrefer) {
		this.phonePrefer = phonePrefer;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
