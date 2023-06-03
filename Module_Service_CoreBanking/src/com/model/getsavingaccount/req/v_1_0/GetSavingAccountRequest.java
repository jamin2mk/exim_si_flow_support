package com.model.getsavingaccount.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "GetSavingAccount", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSavingAccountRequest {

	@XmlElement(name = "Cif")
	@SerializedName("Cif")
	protected String cif;

	public GetSavingAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetSavingAccountRequest(String cif) {
		super();
		this.cif = cif;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

}
