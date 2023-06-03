package com.model.link_collateral_with_limit.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LinkCollateralWithLimit", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class LinkCollateralWithLimitRequest {

	@XmlElement(name = "ColtrlLinkageType")
	protected String coltrlLinkageType;
	@XmlElement(name = "LimitPrefix")
	protected String limitPrefix;
	@XmlElement(name = "LimitSuffix")
	protected String limitSuffix;
	@XmlElement(name = "ColtrlId")
	protected String coltrlId;
	@XmlElement(name = "amountValue")
	protected String amountValue;
	@XmlElement(name = "currencyCode")
	protected String currencyCode;
	@XmlElement(name = "ColtrlNatureInd")
	protected String coltrlNatureInd;
	@XmlElement(name = "MarginPcnt")
	protected String marginPcnt;
	@XmlElement(name = "LoanToValuePcnt")
	protected String loanToValuePcnt;
	@XmlElement(name = "colFromLoanFlg")
	protected String colFromLoanFlg;
	@XmlElement(name = "approveTSBD")
	protected String approveTSBD;
	@XmlElement(name = "topupTSBD")
	protected String topupTSBD;

	public LinkCollateralWithLimitRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkCollateralWithLimitRequest(String coltrlLinkageType,
			String limitPrefix, String limitSuffix, String coltrlId,
			String amountValue, String currencyCode, String coltrlNatureInd,
			String marginPcnt, String loanToValuePcnt, String colFromLoanFlg,
			String approveTSBD, String topupTSBD) {
		super();
		this.coltrlLinkageType = coltrlLinkageType;
		this.limitPrefix = limitPrefix;
		this.limitSuffix = limitSuffix;
		this.coltrlId = coltrlId;
		this.amountValue = amountValue;
		this.currencyCode = currencyCode;
		this.coltrlNatureInd = coltrlNatureInd;
		this.marginPcnt = marginPcnt;
		this.loanToValuePcnt = loanToValuePcnt;
		this.colFromLoanFlg = colFromLoanFlg;
		this.approveTSBD = approveTSBD;
		this.topupTSBD = topupTSBD;
	}

	public String getColtrlLinkageType() {
		return coltrlLinkageType;
	}

	public void setColtrlLinkageType(String coltrlLinkageType) {
		this.coltrlLinkageType = coltrlLinkageType;
	}

	public String getLimitPrefix() {
		return limitPrefix;
	}

	public void setLimitPrefix(String limitPrefix) {
		this.limitPrefix = limitPrefix;
	}

	public String getLimitSuffix() {
		return limitSuffix;
	}

	public void setLimitSuffix(String limitSuffix) {
		this.limitSuffix = limitSuffix;
	}

	public String getColtrlId() {
		return coltrlId;
	}

	public void setColtrlId(String coltrlId) {
		this.coltrlId = coltrlId;
	}

	public String getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(String amountValue) {
		this.amountValue = amountValue;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getColtrlNatureInd() {
		return coltrlNatureInd;
	}

	public void setColtrlNatureInd(String coltrlNatureInd) {
		this.coltrlNatureInd = coltrlNatureInd;
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

	public String getColFromLoanFlg() {
		return colFromLoanFlg;
	}

	public void setColFromLoanFlg(String colFromLoanFlg) {
		this.colFromLoanFlg = colFromLoanFlg;
	}

	public String getApproveTSBD() {
		return approveTSBD;
	}

	public void setApproveTSBD(String approveTSBD) {
		this.approveTSBD = approveTSBD;
	}

	public String getTopupTSBD() {
		return topupTSBD;
	}

	public void setTopupTSBD(String topupTSBD) {
		this.topupTSBD = topupTSBD;
	}

}
