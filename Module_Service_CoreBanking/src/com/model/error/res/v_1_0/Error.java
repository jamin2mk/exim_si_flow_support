package com.model.error.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Error", propOrder = { "fiBusinessException" })
public class Error {

	@XmlElement(name = "FIBusinessException")
	protected FIBusinessException fiBusinessException;

	public Error() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Error(FIBusinessException fiBusinessException) {
		super();
		this.fiBusinessException = fiBusinessException;
	}

	public FIBusinessException getFiBusinessException() {
		return fiBusinessException;
	}

	public void setFiBusinessException(FIBusinessException fiBusinessException) {
		this.fiBusinessException = fiBusinessException;
	}

}
