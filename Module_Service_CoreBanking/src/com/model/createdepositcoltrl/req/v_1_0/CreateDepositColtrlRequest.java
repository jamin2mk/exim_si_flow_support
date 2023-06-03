package com.model.createdepositcoltrl.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CreateDepositColtrl", namespace = "http://www.alsb.com/")
public class CreateDepositColtrlRequest {

	@XmlElement(name = "MarginPcnt")
	protected String marginPcnt;
	@XmlElement(name = "LoanToValuePcnt")
	protected String loanToValuePcnt;
	@XmlElement(name = "LodgedDt")
	protected String lodgedDt;
	@XmlElement(name = "ReviewDt")
	protected String reviewDt;
	@XmlElement(name = "ReceivedDt")
	protected String receivedDt;
	@XmlElement(name = "DueDt")
	protected String dueDt;
	@XmlElement(name = "DepAcctId")
	protected String depAcctId;
	@XmlElement(name = "ApportionedAmt")
	protected String apportionedAmt;
	@XmlElement(name = "LienAmt")
	protected String lienAmt;
	@XmlElement(name = "CustId")
	protected String custId;
	@XmlElement(name = "CollateralValue")
	protected String collateralValue;

	public CreateDepositColtrlRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateDepositColtrlRequest(String marginPcnt,
			String loanToValuePcnt, String lodgedDt, String reviewDt,
			String receivedDt, String dueDt, String depAcctId,
			String apportionedAmt, String lienAmt, String custId,
			String collateralValue) {
		super();
		this.marginPcnt = marginPcnt;
		this.loanToValuePcnt = loanToValuePcnt;
		this.lodgedDt = lodgedDt;
		this.reviewDt = reviewDt;
		this.receivedDt = receivedDt;
		this.dueDt = dueDt;
		this.depAcctId = depAcctId;
		this.apportionedAmt = apportionedAmt;
		this.lienAmt = lienAmt;
		this.custId = custId;
		this.collateralValue = collateralValue;
	}

	public String getMarginPcnt() {
		return marginPcnt;
	}

	public void setMarginPcnt(String marginPcnt) {
		this.marginPcnt = marginPcnt;
	}

	public String getLoanToValuePcnt() {
		return loanToValuePcnt;
	}

	public void setLoanToValuePcnt(String loanToValuePcnt) {
		this.loanToValuePcnt = loanToValuePcnt;
	}

	public String getLodgedDt() {
		return lodgedDt;
	}

	public void setLodgedDt(String lodgedDt) {
		this.lodgedDt = lodgedDt;
	}

	public String getReviewDt() {
		return reviewDt;
	}

	public void setReviewDt(String reviewDt) {
		this.reviewDt = reviewDt;
	}

	public String getReceivedDt() {
		return receivedDt;
	}

	public void setReceivedDt(String receivedDt) {
		this.receivedDt = receivedDt;
	}

	public String getDueDt() {
		return dueDt;
	}

	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}

	public String getDepAcctId() {
		return depAcctId;
	}

	public void setDepAcctId(String depAcctId) {
		this.depAcctId = depAcctId;
	}

	public String getApportionedAmt() {
		return apportionedAmt;
	}

	public void setApportionedAmt(String apportionedAmt) {
		this.apportionedAmt = apportionedAmt;
	}

	public String getLienAmt() {
		return lienAmt;
	}

	public void setLienAmt(String lienAmt) {
		this.lienAmt = lienAmt;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCollateralValue() {
		return collateralValue;
	}

	public void setCollateralValue(String collateralValue) {
		this.collateralValue = collateralValue;
	}

}
