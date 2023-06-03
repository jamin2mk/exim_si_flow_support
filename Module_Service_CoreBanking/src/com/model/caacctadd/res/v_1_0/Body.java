package com.model.caacctadd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

	@XmlElement(name = "CAAcctAddResponse")
	private CAAcctAddResponse acctAddResponse;

	@XmlElement(name = "Error")
	protected Error error;

	public CAAcctAddResponse getAcctAddResponse() {
		return acctAddResponse;
	}

	public void setAcctAddResponse(CAAcctAddResponse acctAddResponse) {
		this.acctAddResponse = acctAddResponse;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
