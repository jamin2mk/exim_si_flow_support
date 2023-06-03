package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankInfo", propOrder = {"bankId","name","branchId","branchName","postAddr"})
public class BankInfo {

	@XmlElement(name = "BankId")
	protected String bankId;
	@XmlElement(name = "Name")
	protected String name;
	@XmlElement(name = "BranchId")
	protected String branchId;
	@XmlElement(name = "BranchName")
	protected String branchName;
	@XmlElement(name = "PostAddr")
	protected PostAddr postAddr;

	public BankInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankInfo(String bankId, String name, String branchId,
			String branchName, PostAddr postAddr) {
		super();
		this.bankId = bankId;
		this.name = name;
		this.branchId = branchId;
		this.branchName = branchName;
		this.postAddr = postAddr;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public PostAddr getPostAddr() {
		return postAddr;
	}

	public void setPostAddr(PostAddr postAddr) {
		this.postAddr = postAddr;
	}

}
