package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "CAAcctAddRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAAcctAddRequest", propOrder = { "cAAcctAddRq", "cAAcctAddCustomData" })
public class CAAcctAddRequest {

	@SerializedName("CAAcctAdd_CustomData")
	@XmlElement(name = "CAAcctAdd_CustomData")
	private CAAcctAddCustomData cAAcctAddCustomData;

	@SerializedName("CAAcctAddRq")
	@XmlElement(name = "CAAcctAddRq")
	private CAAcctAddRq cAAcctAddRq;

	public CAAcctAddCustomData getCAAcctAddCustomData() {
		return cAAcctAddCustomData;
	}

	public void setCAAcctAddCustomData(CAAcctAddCustomData cAAcctAddCustomData) {
		this.cAAcctAddCustomData = cAAcctAddCustomData;
	}

	public CAAcctAddRq getCAAcctAddRq() {
		return cAAcctAddRq;
	}

	public void setCAAcctAddRq(CAAcctAddRq cAAcctAddRq) {
		this.cAAcctAddRq = cAAcctAddRq;
	}
}
