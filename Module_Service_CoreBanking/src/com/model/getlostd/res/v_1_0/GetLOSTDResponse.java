package com.model.getlostd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "GetLOSTDResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetLOSTDResponse", namespace = "http://www.alsb.com/", propOrder = {"inforQHTD"})
public class GetLOSTDResponse {

	@XmlElement(name = "InforQHTD", namespace = "http://www.finacle.com/fixml")
	protected InforQHTD inforQHTD;

	public GetLOSTDResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetLOSTDResponse(InforQHTD inforQHTD) {
		super();
		this.inforQHTD = inforQHTD;
	}

	public InforQHTD getInfoQHTD() {
		return inforQHTD;
	}

	public void setInfoQHTD(InforQHTD inforQHTD) {
		this.inforQHTD = inforQHTD;
	}
	
	

}
