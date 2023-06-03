package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ACCTINT", namespace = "http://www.finacle.com/fixml", propOrder = {"intRateCode","currIntRate"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ACCTINT {

	@XmlElement(name = "intRateCode", namespace = "http://www.finacle.com/fixml")
	protected String intRateCode;
	@XmlElement(name = "currIntRate", namespace = "http://www.finacle.com/fixml")
	protected String currIntRate;

	public ACCTINT() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ACCTINT(String intRateCode, String currIntRate) {
		super();
		this.intRateCode = intRateCode;
		this.currIntRate = currIntRate;
	}

	public String getIntRateCode() {
		return intRateCode;
	}

	public void setIntRateCode(String intRateCode) {
		this.intRateCode = intRateCode;
	}

	public String getCurrIntRate() {
		return currIntRate;
	}

	public void setCurrIntRate(String currIntRate) {
		this.currIntRate = currIntRate;
	}

}
