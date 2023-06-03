package com.model.getlostoi.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetLOSTOI", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSTOIRequest {

	@XmlElement(name = "Cif")
	protected String cif;

	public GetLOSTOIRequest(String cif) {
		super();
		this.cif = cif;
	}

	public GetLOSTOIRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

}
