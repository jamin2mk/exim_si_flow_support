package com.model.get_exchange_rate.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "GetExchangeRate", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetExchangeRateRequest {

	@SerializedName("ExchangeDate")
	@XmlElement(name = "ExchangeDate")
	protected String exchangeDate;
	@SerializedName("CCYCD")
	@XmlElement(name = "CCYCD")
	protected String ccycd;
	@SerializedName("BRID")
	@XmlElement(name = "BRID")
	protected String brid;

	public GetExchangeRateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetExchangeRateRequest(String exchangeDate, String ccycd, String brid) {
		super();
		this.exchangeDate = exchangeDate;
		this.ccycd = ccycd;
		this.brid = brid;
	}

	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public String getCcycd() {
		return ccycd;
	}

	public void setCcycd(String ccycd) {
		this.ccycd = ccycd;
	}

	public String getBrid() {
		return brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

}
