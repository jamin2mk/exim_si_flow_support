package com.model.add_limit_node_teller.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.error.res.v_1_0.fix.FIBusinessException;


@XmlRootElement(name = "AddLimitNodeResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddLimitNodeResponse {

	@XmlElement(name = "AddLimitNodeOutputVO", namespace = "http://www.finacle.com/fixml")
	protected AddLimitNodeOutputVO addLimitNodeOutputVO;

	@XmlElement(name = "FIBusinessException", namespace = "http://www.finacle.com/fixml")
	protected FIBusinessException fiBusinessException;

	@XmlElement(name = "ErrorCode")
	protected String errorCode;

	@XmlElement(name = "ErrorDesc")
	protected String errorDesc;

	public AddLimitNodeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddLimitNodeResponse(AddLimitNodeOutputVO addLimitNodeOutputVO,
			FIBusinessException fiBusinessException, String errorCode,
			String errorDesc) {
		super();
		this.addLimitNodeOutputVO = addLimitNodeOutputVO;
		this.fiBusinessException = fiBusinessException;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public AddLimitNodeOutputVO getAddLimitNodeOutputVO() {
		return addLimitNodeOutputVO;
	}

	public void setAddLimitNodeOutputVO(
			AddLimitNodeOutputVO addLimitNodeOutputVO) {
		this.addLimitNodeOutputVO = addLimitNodeOutputVO;
	}

	public FIBusinessException getFiBusinessException() {
		return fiBusinessException;
	}

	public void setFiBusinessException(FIBusinessException fiBusinessException) {
		this.fiBusinessException = fiBusinessException;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
