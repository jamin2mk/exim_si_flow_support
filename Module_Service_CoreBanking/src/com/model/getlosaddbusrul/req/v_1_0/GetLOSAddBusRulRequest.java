package com.model.getlosaddbusrul.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetLOSAddBusRul", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSAddBusRulRequest {

	@XmlElement(name = "Cif")
	protected String cif;

	@XmlElement(name = "CurSol")
	protected String curSol;

	public GetLOSAddBusRulRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetLOSAddBusRulRequest(String cif, String curSol) {
		super();
		this.cif = cif;
		this.curSol = curSol;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCurSol() {
		return curSol;
	}

	public void setCurSol(String curSol) {
		this.curSol = curSol;
	}

}
