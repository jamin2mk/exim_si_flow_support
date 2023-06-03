package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcctId", propOrder = {"acctId","acctType","acctCurr","bankInfo"})
public class AcctId {

	@XmlElement(name = "AcctId")
	protected String acctId;
	@XmlElement(name = "AcctType")
	protected AcctType acctType;
	@XmlElement(name = "AcctCurr")
	protected String acctCurr;
	@XmlElement(name = "BankInfo")
	protected BankInfo bankInfo;

	public AcctId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcctId(String acctId, AcctType acctType, String acctCurr,
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
