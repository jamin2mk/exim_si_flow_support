package com.model.unlink_collateral_details.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UnlinkCollateralDetails", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnlinkCollateralDetailsRequest {

	@XmlElement(name = "coltrlLinkage")
	protected String coltrlLinkage;
	@XmlElement(name = "coltrlSrlNum")
	protected String coltrlSrlNum;
	@XmlElement(name = "coltrlType")
	protected String coltrlType;
	@XmlElement(name = "limitPrefix")
	protected String limitPrefix;
	@XmlElement(name = "limitSuffix")
	protected String limitSuffix;
	@XmlElement(name = "primSecndry")
	protected String primSecndry;
	@XmlElement(name = "withdrawReasonCode")
	protected String withdrawReasonCode;
	@XmlElement(name = "APPR_CRNCY")
	protected String apprCrncy;
	@XmlElement(name = "APPR_VALUE")
	protected String apprValue;
	@XmlElement(name = "colFromLoanFlg")
	protected String colFromLoanFlg;
	@XmlElement(name = "approveTSBD")
	protected String approveTSBD;
	@XmlElement(name = "topupTSBD")
	protected String topupTSBD;

	public UnlinkCollateralDetailsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnlinkCollateralDetailsRequest(String coltrlLinkage,
			String coltrlSrlNum, String coltrlType, String limitPrefix,
			String limitSuffix, String primSecndry, String withdrawReasonCode,
			String apprCrncy, String apprValue, String colFromLoanFlg,
			String approveTSBD, String topupTSBD) {
		super();
		this.coltrlLinkage = coltrlLinkage;
		this.coltrlSrlNum = coltrlSrlNum;
		this.coltrlType = coltrlType;
		this.limitPrefix = limitPrefix;
		this.limitSuffix = limitSuffix;
		this.primSecndry = primSecndry;
		this.withdrawReasonCode = withdrawReasonCode;
		this.apprCrncy = apprCrncy;
		this.apprValue = apprValue;
		this.colFromLoanFlg = colFromLoanFlg;
		this.approveTSBD = approveTSBD;
		this.topupTSBD = topupTSBD;
	}

	public String getColtrlLinkage() {
		return coltrlLinkage;
	}

	public void setColtrlLinkage(String coltrlLinkage) {
		this.coltrlLinkage = coltrlLinkage;
	}

	public String getColtrlSrlNum() {
		return coltrlSrlNum;
	}

	public void setColtrlSrlNum(String coltrlSrlNum) {
		this.coltrlSrlNum = coltrlSrlNum;
	}

	public String getColtrlType() {
		return coltrlType;
	}

	public void setColtrlType(String coltrlType) {
		this.coltrlType = coltrlType;
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

	public String getPrimSecndry() {
		return primSecndry;
	}

	public void setPrimSecndry(String primSecndry) {
		this.primSecndry = primSecndry;
	}

	public String getWithdrawReasonCode() {
		return withdrawReasonCode;
	}

	public void setWithdrawReasonCode(String withdrawReasonCode) {
		this.withdrawReasonCode = withdrawReasonCode;
	}

	public String getApprCrncy() {
		return apprCrncy;
	}

	public void setApprCrncy(String apprCrncy) {
		this.apprCrncy = apprCrncy;
	}

	public String getApprValue() {
		return apprValue;
	}

	public void setApprValue(String apprValue) {
		this.apprValue = apprValue;
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
