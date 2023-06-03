package com.model.getloscif.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "GetLosCif", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSCIFRequest {

	@XmlElement(name = "cif")
	@SerializedName("cif")
	protected String cif;
	
	@XmlElement(name = "id")
	@SerializedName("id")
	protected String id;

	public GetLOSCIFRequest() {
		// TODO Auto-generated constructor stub
	}

	public GetLOSCIFRequest(String cif) {
		this.cif = cif;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
