package com.model.getsavingaccount.res.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "SAVINGACCOUNTLIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class SavingAccountList {

	@XmlElement(name = "SAVINGACCOUNT")
	protected List<SavingAccount> savingAccount;

	public SavingAccountList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingAccountList(List<SavingAccount> savingAccount) {
		super();
		this.savingAccount = savingAccount;
	}

	public List<SavingAccount> getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(List<SavingAccount> savingAccount) {
		this.savingAccount = savingAccount;
	}

}
