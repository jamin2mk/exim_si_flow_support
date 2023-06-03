package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoanAcctAddResponse", propOrder = { "loanAcctAddRs",
		"loanAcctAddCustomData" })
public class LoanAcctAddResponse {

	@XmlElement(name = "LoanAcctAddRs")
	protected LoanAcctAddRs loanAcctAddRs;

	@XmlElement(name = "LoanAcctAdd_CustomData")
	protected String loanAcctAddCustomData;

	public LoanAcctAddResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanAcctAddResponse(LoanAcctAddRs loanAcctAddRs,
			String loanAcctAddCustomData) {
		super();
		this.loanAcctAddRs = loanAcctAddRs;
		this.loanAcctAddCustomData = loanAcctAddCustomData;
	}

	public LoanAcctAddRs getLoanAcctAddRs() {
		return loanAcctAddRs;
	}

	public void setLoanAcctAddRs(LoanAcctAddRs loanAcctAddRs) {
		this.loanAcctAddRs = loanAcctAddRs;
	}

	public String getLoanAcctAddCustomData() {
		return loanAcctAddCustomData;
	}

	public void setLoanAcctAddCustomData(String loanAcctAddCustomData) {
		this.loanAcctAddCustomData = loanAcctAddCustomData;
	}

}
