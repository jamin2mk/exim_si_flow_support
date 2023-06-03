package com.model.getcurrentaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.getcurrentaccount.req.v_1_0.CurrentAccountList;

@XmlRootElement(name = "GetCurrentAccountResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCurrentAccountResponse {

	@XmlElement(name = "CURRENTACCOUNTLIST")
	protected CurrentAccountList currentAccountList;

	@XmlElement(name = "ErrorCode")
	protected String errorCode;

	@XmlElement(name = "ErrorDesc")
	protected String errorDesc;

	public GetCurrentAccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCurrentAccountResponse(CurrentAccountList currentAccountList,
			String errorCode, String errorDesc) {
		super();
		this.currentAccountList = currentAccountList;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public CurrentAccountList getCurrentAccountList() {
		return currentAccountList;
	}

	public void setCurrentAccountList(CurrentAccountList currentAccountList) {
		this.currentAccountList = currentAccountList;
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
