package com.model.getsavingaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetSavingAccountResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSavingAccountResponse {

	@XmlElement(name = "SAVINGACCOUNTLIST")
	protected SavingAccountList savingAccountList;

	@XmlElement(name = "ErrorCode")
	protected String errorCode;

	@XmlElement(name = "ErrorDesc")
	protected String errorDesc;

	public GetSavingAccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetSavingAccountResponse(SavingAccountList savingAccountList,
			String errorCode, String errorDesc) {
		super();
		this.savingAccountList = savingAccountList;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public SavingAccountList getSavingAccountList() {
		return savingAccountList;
	}

	public void setSavingAccountList(SavingAccountList savingAccountList) {
		this.savingAccountList = savingAccountList;
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
