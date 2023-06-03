package com.model.odacctaddrequest.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlType(name = "ODAcctId", namespace = "http://www.finacle.com/fixml", propOrder = {})
@XmlAccessorType(XmlAccessType.FIELD)
public class ODAcctId {

	@SerializedName("AcctId")
	@XmlElement(name = "AcctId", namespace = "http://www.finacle.com/fixml")
	protected String acctId;
	@SerializedName("AcctType")
	@XmlElement(name = "AcctType", namespace = "http://www.finacle.com/fixml")
	protected AcctType acctType;
	@SerializedName("AcctCurr")
	@XmlElement(name = "AcctCurr", namespace = "http://www.finacle.com/fixml")
	protected String acctCurr;
	@SerializedName("BankInfo")
	@XmlElement(name = "BankInfo", namespace = "http://www.finacle.com/fixml")
	protected BankInfo bankInfo;

	public ODAcctId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ODAcctId(String acctId, AcctType acctType, String acctCurr,
			BankInfo bankInfo) {
		super();
		this.acctId = acctId;
		this.acctType = acctType;
		this.acctCurr = acctCurr;
		this.bankInfo = bankInfo;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public AcctType getAcctType() {
		return acctType;
	}

	public void setAcctType(AcctType acctType) {
		this.acctType = acctType;
	}

	public String getAcctCurr() {
		return acctCurr;
	}

	public void setAcctCurr(String acctCurr) {
		this.acctCurr = acctCurr;
	}

	public BankInfo getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(BankInfo bankInfo) {
		this.bankInfo = bankInfo;
	}

}
