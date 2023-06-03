package com.model.odacctaddrequest.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.model.error.res.v_1_0.fix.FIBusinessException;

@XmlRootElement(name = "ODAcctAddResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ODAcctAddResponse {

	@SerializedName("ODAcctId")
	@XmlElement(name = "ODAcctId", namespace = "http://www.finacle.com/fixml")
	protected ODAcctId odAcctId;

	@SerializedName("FIBusinessException")
	@XmlElement(name = "FIBusinessException", namespace = "http://www.finacle.com/fixml")
	protected FIBusinessException fiBusinessException;

	public ODAcctAddResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ODAcctAddResponse(ODAcctId odAcctId,
			FIBusinessException fiBusinessException) {
		super();
		this.odAcctId = odAcctId;
		this.fiBusinessException = fiBusinessException;
	}

	public ODAcctId getOdAcctId() {
		return odAcctId;
	}

	public void setOdAcctId(ODAcctId odAcctId) {
		this.odAcctId = odAcctId;
	}

	public FIBusinessException getFiBusinessException() {
		return fiBusinessException;
	}

	public void setFiBusinessException(FIBusinessException fiBusinessException) {
		this.fiBusinessException = fiBusinessException;
	}

}
