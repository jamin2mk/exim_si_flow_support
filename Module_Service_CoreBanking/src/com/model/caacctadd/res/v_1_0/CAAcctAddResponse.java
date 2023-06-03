package com.model.caacctadd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CAAcctAddResponse {

	@XmlElement(name = "CAAcctAddRs")
	private CAAcctAddRs acctAddRs;

	public CAAcctAddRs getAcctAddRs() {
		return acctAddRs;
	}

	public void setAcctAddRs(CAAcctAddRs acctAddRs) {
		this.acctAddRs = acctAddRs;
	}

}
