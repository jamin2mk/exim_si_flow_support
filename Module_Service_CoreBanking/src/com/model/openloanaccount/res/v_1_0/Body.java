package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.error.res.v_1_0.Error;

@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

	@XmlElement(name = "LoanAcctAddResponse")
	protected LoanAcctAddResponse loanAcctAddResponse;

	@XmlElement(name = "Error")
	protected Error error;

	public Body() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Body(LoanAcctAddResponse loanAcctAddResponse, Error error) {
		super();
		this.loanAcctAddResponse = loanAcctAddResponse;
		this.error = error;
	}

	public LoanAcctAddResponse getLoanAcctAddResponse() {
		return loanAcctAddResponse;
	}

	public void setLoanAcctAddResponse(LoanAcctAddResponse loanAcctAddResponse) {
		this.loanAcctAddResponse = loanAcctAddResponse;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
