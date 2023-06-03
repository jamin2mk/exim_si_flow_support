package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoanAcctAddRs", propOrder = { "acctOpenDt", "acctId" })
public class LoanAcctAddRs {
	@XmlElement(name = "AcctOpenDt")
	protected String acctOpenDt;
	@XmlElement(name = "AcctId")
	protected AcctId acctId;

	public LoanAcctAddRs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanAcctAddRs(String acctOpenDt, AcctId acctId) {
		super();
		this.acctOpenDt = acctOpenDt;
		this.acctId = acctId;
	}

	public String getAcctOpenDt() {
		return acctOpenDt;
	}

	public void setAcctOpenDt(String acctOpenDt) {
		this.acctOpenDt = acctOpenDt;
	}

	public AcctId getAcctId() {
		return acctId;
	}

	public void setAcctId(AcctId acctId) {
		this.acctId = acctId;
	}

}
