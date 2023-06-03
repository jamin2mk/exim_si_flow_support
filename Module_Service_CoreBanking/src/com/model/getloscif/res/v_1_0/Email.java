package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.Expose;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "email", namespace = "http://www.finacle.com/fixml")
public class Email {

	@Expose
	protected String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {                        
		this.email = email;
	}

}
