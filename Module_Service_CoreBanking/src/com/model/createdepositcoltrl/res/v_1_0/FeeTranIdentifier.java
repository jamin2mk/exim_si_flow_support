package com.model.createdepositcoltrl.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeeTranIdentifier", propOrder = { "trnId" })
public class FeeTranIdentifier {

	@XmlElement(name = "TrnId")
	protected String trnId;

	public FeeTranIdentifier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeeTranIdentifier(String trnId) {
		super();
		this.trnId = trnId;
	}

	public String getTrnId() {
		return trnId;
	}

	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}

}
