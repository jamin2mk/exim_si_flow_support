package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAAcctId", propOrder = { "acctType", "acctCurr", "bankInfo" })
public class CAAcctId {

	@SerializedName("AcctType")
	@XmlElement(name = "AcctType")
	private AcctType acctType;

	@SerializedName("AcctCurr")
	@XmlElement(name = "AcctCurr")
	private String acctCurr;

	@SerializedName("BankInfo")
	@XmlElement(name = "BankInfo")
	private BankInfo bankInfo;

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
