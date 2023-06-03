package com.model.searchcurrentaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SearchCurrentAccountResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchCurrentAccountResponse {

	@XmlElement(name = "AccountName")
	protected String accountName;
	@XmlElement(name = "CustomerName")
	protected String customerName;
	@XmlElement(name = "CustomerCode")
	protected String customerCode;
	@XmlElement(name = "CustType")
	protected String custType;
	@XmlElement(name = "Balance")
	protected String balance;
	@XmlElement(name = "AvailableBal")
	protected String availableBal;
	@XmlElement(name = "CCYCD")
	protected String ccycd;
	@XmlElement(name = "LastTransDate")
	protected String lastTransDate;
	@XmlElement(name = "BlockBalance")
	protected String blockBalance;
	@XmlElement(name = "OpenDate")
	protected String openDate;
	@XmlElement(name = "InterestRate")
	protected String interestRate;
	@XmlElement(name = "BRID")
	protected String brid;
	@XmlElement(name = "Status")
	protected String status;
	@XmlElement(name = "OverLimit")
	protected String overLimit;
	@XmlElement(name = "SUBBRID")
	protected String subbrid;
	@XmlElement(name = "AccDisplay")
	protected String accDisplay;
	@XmlElement(name = "ErrorCode")
	protected String errorCode;
	@XmlElement(name = "ErrorDesc")
	protected String errorDesc;

	public SearchCurrentAccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchCurrentAccountResponse(String accountName,
			String customerName, String customerCode, String custType,
			String balance, String availableBal, String ccycd,
			String lastTransDate, String blockBalance, String openDate,
			String interestRate, String brid, String status, String overLimit,
			String subbrid, String accDisplay, String errorCode,
			String errorDesc) {
		super();
		this.accountName = accountName;
		this.customerName = customerName;
		this.customerCode = customerCode;
		this.custType = custType;
		this.balance = balance;
		this.availableBal = availableBal;
		this.ccycd = ccycd;
		this.lastTransDate = lastTransDate;
		this.blockBalance = blockBalance;
		this.openDate = openDate;
		this.interestRate = interestRate;
		this.brid = brid;
		this.status = status;
		this.overLimit = overLimit;
		this.subbrid = subbrid;
		this.accDisplay = accDisplay;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvailableBal() {
		return availableBal;
	}

	public void setAvailableBal(String availableBal) {
		this.availableBal = availableBal;
	}

	public String getCcycd() {
		return ccycd;
	}

	public void setCcycd(String ccycd) {
		this.ccycd = ccycd;
	}

	public String getLastTransDate() {
		return lastTransDate;
	}

	public void setLastTransDate(String lastTransDate) {
		this.lastTransDate = lastTransDate;
	}

	public String getBlockBalance() {
		return blockBalance;
	}

	public void setBlockBalance(String blockBalance) {
		this.blockBalance = blockBalance;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getBrid() {
		return brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOverLimit() {
		return overLimit;
	}

	public void setOverLimit(String overLimit) {
		this.overLimit = overLimit;
	}

	public String getSubbrid() {
		return subbrid;
	}

	public void setSubbrid(String subbrid) {
		this.subbrid = subbrid;
	}

	public String getAccDisplay() {
		return accDisplay;
	}

	public void setAccDisplay(String accDisplay) {
		this.accDisplay = accDisplay;
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
