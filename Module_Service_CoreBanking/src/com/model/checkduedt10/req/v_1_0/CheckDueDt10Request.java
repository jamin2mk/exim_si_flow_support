package com.model.checkduedt10.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "CheckDueDt10", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckDueDt10Request {

	@XmlElement(name = "FLGSTS")
	@SerializedName("FLGSTS")
	protected String flgsts;

	@XmlElement(name = "CUSTSEQ")
	@SerializedName("CUSTSEQ")
	protected String custseq;

	public CheckDueDt10Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckDueDt10Request(String flgsts, String custseq) {
		super();
		this.flgsts = flgsts;
		this.custseq = custseq;
	}

	public String getFlgsts() {
		return flgsts;
	}

	public void setFlgsts(String flgsts) {
		this.flgsts = flgsts;
	}

	public String getCustseq() {
		return custseq;
	}

	public void setCustseq(String custseq) {
		this.custseq = custseq;
	}

}
