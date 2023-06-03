package com.model.getlosds.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetLOSDS", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSDSRequest {

	@XmlElement(name = "Cif")
	protected String cif;

	public GetLOSDSRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetLOSDSRequest(String cif) {
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
