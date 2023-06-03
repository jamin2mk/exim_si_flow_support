package com.model.createdepositcoltrl.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepColtrlAddRs", propOrder = {"coltrlId","feeTranIdentifier"})
public class DepColtrlAddRs {

	@XmlElement(name = "ColtrlId")
	protected String coltrlId;
	@XmlElement(name = "FeeTranIdentifier")
	protected FeeTranIdentifier feeTranIdentifier;

	public DepColtrlAddRs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepColtrlAddRs(String coltrlId, FeeTranIdentifier feeTranIdentifier) {
		super();
		this.coltrlId = coltrlId;
		this.feeTranIdentifier = feeTranIdentifier;
	}

	public String getColtrlId() {
		return coltrlId;
	}

	public void setColtrlId(String coltrlId) {
		this.coltrlId = coltrlId;
	}

	public FeeTranIdentifier getFeeTranIdentifier() {
		return feeTranIdentifier;
	}

	public void setFeeTranIdentifier(FeeTranIdentifier feeTranIdentifier) {
		this.feeTranIdentifier = feeTranIdentifier;
	}

}
