package com.model.link_collateral_with_limit.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColtrlLinkAddRs", propOrder = { "coltrlId",
		"coltrlLinkageType", "limitNodeId", "acctId" })
public class ColtrlLinkAddRs {

	@XmlElement(name = "ColtrlId")
	protected String coltrlId;
	@XmlElement(name = "ColtrlLinkageType")
	protected String coltrlLinkageType;
	@XmlElement(name = "LimitNodeId")
	protected LimitNodeId limitNodeId;
	@XmlElement(name = "AcctId")
	protected String acctId;

	public ColtrlLinkAddRs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColtrlLinkAddRs(String coltrlId, String coltrlLinkageType,
			LimitNodeId limitNodeId, String acctId) {
		super();
		this.coltrlId = coltrlId;
		this.coltrlLinkageType = coltrlLinkageType;
		this.limitNodeId = limitNodeId;
		this.acctId = acctId;
	}

	public String getColtrlId() {
		return coltrlId;
	}

	public void setColtrlId(String coltrlId) {
		this.coltrlId = coltrlId;
	}

	public String getColtrlLinkageType() {
		return coltrlLinkageType;
	}

	public void setColtrlLinkageType(String coltrlLinkageType) {
		this.coltrlLinkageType = coltrlLinkageType;
	}

	public LimitNodeId getLimitNodeId() {
		return limitNodeId;
	}

	public void setLimitNodeId(LimitNodeId limitNodeId) {
		this.limitNodeId = limitNodeId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

}
