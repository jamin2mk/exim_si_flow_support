package com.model.get_exchange_rate.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetExchangeRateResponse", namespace = "http://www.alsb.com/")
public class GetExchangeRateResponse {

	@XmlElement(name = "EXCHANGERATELIST")
	protected ExchangeRateList exchangeRateList;
	@XmlElement(name = "ErrorCode")
	protected String errorCode;
	@XmlElement(name = "ErrorDesc")
	protected String errorDes;

	public GetExchangeRateResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetExchangeRateResponse(ExchangeRateList exchangeRateList,
			String errorCode, String errorDes) {
		super();
		this.exchangeRateList = exchangeRateList;
		this.errorCode = errorCode;
		this.errorDes = errorDes;
	}

	public ExchangeRateList getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(ExchangeRateList exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDes() {
		return errorDes;
	}

	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}

}
