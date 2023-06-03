package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
public class GenLedgerSubHead {

	@SerializedName("GenLedgerSubHeadCode")
	@XmlElement(name = "GenLedgerSubHeadCode")
	private String genLedgerSubHeadCode;

	public String getGenLedgerSubHeadCode() {
		return genLedgerSubHeadCode;
	}

	public void setGenLedgerSubHeadCode(String genLedgerSubHeadCode) {
		this.genLedgerSubHeadCode = genLedgerSubHeadCode;
	}
}
