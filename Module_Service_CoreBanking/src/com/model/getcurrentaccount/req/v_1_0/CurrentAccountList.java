package com.model.getcurrentaccount.req.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CURRENTACCOUNTLIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentAccountList {

	@XmlElement(name = "CURRENTACCOUNT")
	protected List<CurrentAccount> currentAccount;

	public CurrentAccountList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentAccountList(List<CurrentAccount> currentAccount) {
		super();
		this.currentAccount = currentAccount;
	}

	public List<CurrentAccount> getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(List<CurrentAccount> currentAccount) {
		this.currentAccount = currentAccount;
	}

}
