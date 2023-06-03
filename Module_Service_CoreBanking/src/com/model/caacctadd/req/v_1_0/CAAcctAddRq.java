package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAAcctAddRq", propOrder = { "custId", "cAAcctId", "cAAcctGenInfo" })
public class CAAcctAddRq {

	@SerializedName("CustId")
	@XmlElement(name = "CustId")
	private CustId custId;

	@SerializedName("CAAcctId")
	@XmlElement(name = "CAAcctId")
	private CAAcctId cAAcctId;

	@SerializedName("CAAcctGenInfo")
	@XmlElement(name = "CAAcctGenInfo")
	private CAAcctGenInfo cAAcctGenInfo;

	public CustId getCustId() {
		return custId;
	}

	public void setCustId(CustId custId) {
		this.custId = custId;
	}

	public CAAcctId getCAAcctId() {
		return cAAcctId;
	}

	public void setCAAcctId(CAAcctId cAAcctId) {
		this.cAAcctId = cAAcctId;
	}

	public CAAcctGenInfo getCAAcctGenInfo() {
		return cAAcctGenInfo;
	}

	public void setCAAcctGenInfo(CAAcctGenInfo cAAcctGenInfo) {
		this.cAAcctGenInfo = cAAcctGenInfo;
	}
}
