package com.model.getcurrentaccount.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CURRENTACCOUNT", propOrder = { "accountNo", "ccycd", "balance" })
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentAccount {

	@XmlElement(name = "AccountNo")
	protected String accountNo;
	@XmlElement(name = "CCYCD")
	protected String ccycd;
	@XmlElement(name = "Balance")
	protected String balance;

	public CurrentAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentAccount(String accountNo, String ccycd, String balance) {
		super();
		this.accountNo = accountNo;
		this.ccycd = ccycd;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCcycd() {
		return ccycd;
	}

	public void setCcycd(String ccycd) {
		this.ccycd = ccycd;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}
