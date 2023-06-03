package com.model.checkduedt10.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CheckDueDt10Response", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckDueDt10Response {

	@XmlElement(name = "FLG_QH10DT")
	protected String flgQh10dt;

	public CheckDueDt10Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckDueDt10Response(String flgQh10dt) {
		super();
		this.flgQh10dt = flgQh10dt;
	}

	public String getFlgQh10dt() {
		return flgQh10dt;
	}

	public void setFlgQh10dt(String flgQh10dt) {
		this.flgQh10dt = flgQh10dt;
	}

}
