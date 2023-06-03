package com.model.caacctadd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CAAcctAddRs {

	@XmlElement(name = "CAAcctId")
	private CAAcctId caAcctId;

	public CAAcctId getCaAcctId() {
		return caAcctId;
	}

	public void setCaAcctId(CAAcctId caAcctId) {
		this.caAcctId = caAcctId;
	}
}
