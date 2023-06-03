package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RELATEDPARTYDTL", namespace = "http://www.finacle.com/fixml", propOrder = {
		"relnType", "relnCode", "relCifId", "custTitle", "reltnCustName",
		"custAddrLine1" })
public class RelatedPartyDTL {

	@XmlElement(name = "relnType", namespace = "http://www.finacle.com/fixml")
	protected String relnType;
	@XmlElement(name = "relnCode", namespace = "http://www.finacle.com/fixml")
	protected String relnCode;
	@XmlElement(name = "relCifId", namespace = "http://www.finacle.com/fixml")
	protected String relCifId;
	@XmlElement(name = "custTitle", namespace = "http://www.finacle.com/fixml")
	protected String custTitle;
	@XmlElement(name = "reltnCustName", namespace = "http://www.finacle.com/fixml")
	protected String reltnCustName;
	@XmlElement(name = "custAddrLine1", namespace = "http://www.finacle.com/fixml")
	protected String custAddrLine1;

	public RelatedPartyDTL(String relnType, String relnCode, String relCifId,
			String custTitle, String reltnCustName, String custAddrLine1) {
		super();
		this.relnType = relnType;
		this.relnCode = relnCode;
		this.relCifId = relCifId;
		this.custTitle = custTitle;
		this.reltnCustName = reltnCustName;
		this.custAddrLine1 = custAddrLine1;
	}

	public RelatedPartyDTL() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRelnType() {
		return relnType;
	}

	public void setRelnType(String relnType) {
		this.relnType = relnType;
	}

	public String getRelnCode() {
		return relnCode;
	}

	public void setRelnCode(String relnCode) {
		this.relnCode = relnCode;
	}

	public String getRelCifId() {
		return relCifId;
	}

	public void setRelCifId(String relCifId) {
		this.relCifId = relCifId;
	}

	public String getCustTitle() {
		return custTitle;
	}

	public void setCustTitle(String custTitle) {
		this.custTitle = custTitle;
	}

	public String getReltnCustName() {
		return reltnCustName;
	}

	public void setReltnCustName(String reltnCustName) {
		this.reltnCustName = reltnCustName;
	}

	public String getCustAddrLine1() {
		return custAddrLine1;
	}

	public void setCustAddrLine1(String custAddrLine1) {
		this.custAddrLine1 = custAddrLine1;
	}

}
