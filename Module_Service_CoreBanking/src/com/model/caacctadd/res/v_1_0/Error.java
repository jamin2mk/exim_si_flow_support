package com.model.caacctadd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Error")
public class Error {

	@XmlElement(name = "FISystemException")
	protected FIException fiSystemException;

	@XmlElement(name = "FIBusinessException")
	protected FIException fiBusinessException;

	public Error() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Error(FIException fiSystemException, FIException fiBusinessException) {
		super();
		this.fiSystemException = fiSystemException;
		this.fiBusinessException = fiBusinessException;
	}

	public FIException getFiSystemException() {
		return fiSystemException;
	}

	public void setFiSystemException(FIException fiSystemException) {
		this.fiSystemException = fiSystemException;
	}

	public FIException getFiBusinessException() {
		return fiBusinessException;
	}

	public void setFiBusinessException(FIException fiBusinessException) {
		this.fiBusinessException = fiBusinessException;
	}

}
