package com.model.odacctaddrequest.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlType(name = "BankInfo", namespace = "http://www.finacle.com/fixml", propOrder = {"bankId","name","branchId","branchName","postAddr"})
@XmlAccessorType(XmlAccessType.FIELD)
public class BankInfo {

	@SerializedName("BankId")
	@XmlElement(name = "BankId", namespace = "http://www.finacle.com/fixml")
	protected String bankId;
	@SerializedName("Name")
	@XmlElement(name = "Name", namespace = "http://www.finacle.com/fixml")
	protected String name;
	@SerializedName("BranchId")
	@XmlElement(name = "BranchId", namespace = "http://www.finacle.com/fixml")
	protected String branchId;
	@SerializedName("BranchName")
	@XmlElement(name = "BranchName", namespace = "http://www.finacle.com/fixml")
	protected String branchName;
	@SerializedName("PostAddr")
	@XmlElement(name = "PostAddr", namespace = "http://www.finacle.com/fixml")
	protected PostAddr postAddr;

	public BankInfo() {
		super();
		// TODO Auto-generated constructor stub
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
