package com.model.searchcurrentaccount.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SearchCurrentAccount", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchCurrentAccountRequest {

	@XmlElement(name = "AccountNo")
	protected String accountNo;

	public SearchCurrentAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchCurrentAccountRequest(String accountNo) {
		super();
		this.accountNo = accountNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
