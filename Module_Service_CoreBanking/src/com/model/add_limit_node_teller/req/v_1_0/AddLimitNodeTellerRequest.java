package com.model.add_limit_node_teller.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AddLimitNode", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddLimitNodeTellerRequest {

	@XmlElement(name = "cifId")
	protected String cifId;
	@XmlElement(name = "drwngPowerInd")
	protected String drwngPowerInd;
	@XmlElement(name = "limitDesc")
	protected String limitDesc;
	@XmlElement(name = "limitEffDate")
	protected String limitEffDate;
	@XmlElement(name = "limitExpiryDate")
	protected String limitExpiryDate;
	@XmlElement(name = "limitPrefix")
	protected String limitPrefix;
	@XmlElement(name = "limitReviewDate")
	protected String limitReviewDate;
	@XmlElement(name = "limitSanctDate")
	protected String limitSanctDate;
	@XmlElement(name = "limitSigningDate")
	protected String limitSigningDate;
	@XmlElement(name = "limitSuffix")
	protected String limitSuffix;
	@XmlElement(name = "limitType")
	protected String limitType;
	@XmlElement(name = "singleTranFlg")
	protected String singleTranFlg;
	@XmlElement(name = "serial_num1")
	protected String serial_num1;
	@XmlElement(name = "primaryCustomer1")
	protected String primaryCustomer1;
	@XmlElement(name = "categoryType1")
	protected String categoryType1;
	@XmlElement(name = "categoryCode1")
	protected String categoryCode1;
	@XmlElement(name = "activeFlg1")
	protected String activeFlg1;
	@XmlElement(name = "serial_num2")
	protected String serial_num2;
	@XmlElement(name = "primaryCustomer2")
	protected String primaryCustomer2;
	@XmlElement(name = "categoryType2")
	protected String categoryType2;
	@XmlElement(name = "categoryCode2")
	protected String categoryCode2;
	@XmlElement(name = "activeFlg2")
	protected String activeFlg2;
	@XmlElement(name = "serial_num3")
	protected String serial_num3;
	@XmlElement(name = "primaryCustomer3")
	protected String primaryCustomer3;
	@XmlElement(name = "categoryType3")
	protected String categoryType3;
	@XmlElement(name = "categoryCode3")
	protected String categoryCode3;
	@XmlElement(name = "activeFlg3")
	protected String activeFlg3;
	@XmlElement(name = "sanctAuthCode")
	protected String sanctAuthCode;
	@XmlElement(name = "sanctLevelCode")
	protected String sanctLevelCode;
	@XmlElement(name = "amountValue")
	protected String amountValue;
	@XmlElement(name = "currencyCode")
	protected String currencyCode;
	@XmlElement(name = "custSanctLim")
	protected String custSanctLim;
	@XmlElement(name = "termType")
	protected String termType;
	@XmlElement(name = "Month")
	protected String month;
	@XmlElement(name = "day")
	protected String day;
	@XmlElement(name = "matDate_ui")
	protected String matDateUi;
	@XmlElement(name = "grcMonth")
	protected String grcMonth;
	@XmlElement(name = "grcDay")
	protected String grcDay;
	@XmlElement(name = "limitAmt")
	protected String limitAmt;
	@XmlElement(name = "limUnsecAmt")
	protected String limUnsecAmt;
	@XmlElement(name = "limitSolId")
	protected String limitSolId;
	@XmlElement(name = "maxMonth")
	protected String maxMonth;
	@XmlElement(name = "maxDay")
	protected String maxDay;
	@XmlElement(name = "isCollateral")
	protected String isCollateral;
	@XmlElement(name = "isTermAccount")
	protected String isTermAccount;
	@XmlElement(name = "isValuecollateral")
	protected String isValuecollateral;
	@XmlElement(name = "titName")
	protected String titName;
	@XmlElement(name = "proName")
	protected String proName;
	@XmlElement(name = "appName")
	protected String appName;
	@XmlElement(name = "PRODUCT_TYPE")
	protected String productType;
	@XmlElement(name = "MASTER_CODE")
	protected String masterCode;
	@XmlElement(name = "totSAmt")
	protected String totSAmt;

	public AddLimitNodeTellerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddLimitNodeTellerRequest(String cifId, String drwngPowerInd,
			String limitDesc, String limitEffDate, String limitExpiryDate,
			String limitPrefix, String limitReviewDate, String limitSanctDate,
			String limitSigningDate, String limitSuffix, String limitType,
			String singleTranFlg, String serial_num1, String primaryCustomer1,
			String categoryType1, String categoryCode1, String activeFlg1,
			String serial_num2, String primaryCustomer2, String categoryType2,
			String categoryCode2, String activeFlg2, String serial_num3,
			String primaryCustomer3, String categoryType3,
			String categoryCode3, String activeFlg3, String sanctAuthCode,
			String sanctLevelCode, String amountValue, String currencyCode,
			String custSanctLim, String termType, String month, String day,
			String matDateUi, String grcMonth, String grcDay, String limitAmt,
			String limUnsecAmt, String limitSolId, String maxMonth,
			String maxDay, String isCollateral, String isTermAccount,
			String isValuecollateral, String titName, String proName,
			String appName, String productType, String masterCode,
			String totSAmt) {
		super();
		this.cifId = cifId;
		this.drwngPowerInd = drwngPowerInd;
		this.limitDesc = limitDesc;
		this.limitEffDate = limitEffDate;
		this.limitExpiryDate = limitExpiryDate;
		this.limitPrefix = limitPrefix;
		this.limitReviewDate = limitReviewDate;
		this.limitSanctDate = limitSanctDate;
		this.limitSigningDate = limitSigningDate;
		this.limitSuffix = limitSuffix;
		this.limitType = limitType;
		this.singleTranFlg = singleTranFlg;
		this.serial_num1 = serial_num1;
		this.primaryCustomer1 = primaryCustomer1;
		this.categoryType1 = categoryType1;
		this.categoryCode1 = categoryCode1;
		this.activeFlg1 = activeFlg1;
		this.serial_num2 = serial_num2;
		this.primaryCustomer2 = primaryCustomer2;
		this.categoryType2 = categoryType2;
		this.categoryCode2 = categoryCode2;
		this.activeFlg2 = activeFlg2;
		this.serial_num3 = serial_num3;
		this.primaryCustomer3 = primaryCustomer3;
		this.categoryType3 = categoryType3;
		this.categoryCode3 = categoryCode3;
		this.activeFlg3 = activeFlg3;
		this.sanctAuthCode = sanctAuthCode;
		this.sanctLevelCode = sanctLevelCode;
		this.amountValue = amountValue;
		this.currencyCode = currencyCode;
		this.custSanctLim = custSanctLim;
		this.termType = termType;
		this.month = month;
		this.day = day;
		this.matDateUi = matDateUi;
		this.grcMonth = grcMonth;
		this.grcDay = grcDay;
		this.limitAmt = limitAmt;
		this.limUnsecAmt = limUnsecAmt;
		this.limitSolId = limitSolId;
		this.maxMonth = maxMonth;
		this.maxDay = maxDay;
		this.isCollateral = isCollateral;
		this.isTermAccount = isTermAccount;
		this.isValuecollateral = isValuecollateral;
		this.titName = titName;
		this.proName = proName;
		this.appName = appName;
		this.productType = productType;
		this.masterCode = masterCode;
		this.totSAmt = totSAmt;
	}

	public String getCifId() {
		return cifId;
	}

	public void setCifId(String cifId) {
		this.cifId = cifId;
	}

	public String getDrwngPowerInd() {
		return drwngPowerInd;
	}

	public void setDrwngPowerInd(String drwngPowerInd) {
		this.drwngPowerInd = drwngPowerInd;
	}

	public String getLimitDesc() {
		return limitDesc;
	}

	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}

	public String getLimitEffDate() {
		return limitEffDate;
	}

	public void setLimitEffDate(String limitEffDate) {
		this.limitEffDate = limitEffDate;
	}

	public String getLimitExpiryDate() {
		return limitExpiryDate;
	}

	public void setLimitExpiryDate(String limitExpiryDate) {
		this.limitExpiryDate = limitExpiryDate;
	}

	public String getLimitPrefix() {
		return limitPrefix;
	}

	public void setLimitPrefix(String limitPrefix) {
		this.limitPrefix = limitPrefix;
	}

	public String getLimitReviewDate() {
		return limitReviewDate;
	}

	public void setLimitReviewDate(String limitReviewDate) {
		this.limitReviewDate = limitReviewDate;
	}

	public String getLimitSanctDate() {
		return limitSanctDate;
	}

	public void setLimitSanctDate(String limitSanctDate) {
		this.limitSanctDate = limitSanctDate;
	}

	public String getLimitSigningDate() {
		return limitSigningDate;
	}

	public void setLimitSigningDate(String limitSigningDate) {
		this.limitSigningDate = limitSigningDate;
	}

	public String getLimitSuffix() {
		return limitSuffix;
	}

	public void setLimitSuffix(String limitSuffix) {
		this.limitSuffix = limitSuffix;
	}

	public String getLimitType() {
		return limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	public String getSingleTranFlg() {
		return singleTranFlg;
	}

	public void setSingleTranFlg(String singleTranFlg) {
		this.singleTranFlg = singleTranFlg;
	}

	public String getSerial_num1() {
		return serial_num1;
	}

	public void setSerial_num1(String serial_num1) {
		this.serial_num1 = serial_num1;
	}

	public String getPrimaryCustomer1() {
		return primaryCustomer1;
	}

	public void setPrimaryCustomer1(String primaryCustomer1) {
		this.primaryCustomer1 = primaryCustomer1;
	}

	public String getCategoryType1() {
		return categoryType1;
	}

	public void setCategoryType1(String categoryType1) {
		this.categoryType1 = categoryType1;
	}

	public String getCategoryCode1() {
		return categoryCode1;
	}

	public void setCategoryCode1(String categoryCode1) {
		this.categoryCode1 = categoryCode1;
	}

	public String getActiveFlg1() {
		return activeFlg1;
	}

	public void setActiveFlg1(String activeFlg1) {
		this.activeFlg1 = activeFlg1;
	}

	public String getSerial_num2() {
		return serial_num2;
	}

	public void setSerial_num2(String serial_num2) {
		this.serial_num2 = serial_num2;
	}

	public String getPrimaryCustomer2() {
		return primaryCustomer2;
	}

	public void setPrimaryCustomer2(String primaryCustomer2) {
		this.primaryCustomer2 = primaryCustomer2;
	}

	public String getCategoryType2() {
		return categoryType2;
	}

	public void setCategoryType2(String categoryType2) {
		this.categoryType2 = categoryType2;
	}

	public String getCategoryCode2() {
		return categoryCode2;
	}

	public void setCategoryCode2(String categoryCode2) {
		this.categoryCode2 = categoryCode2;
	}

	public String getActiveFlg2() {
		return activeFlg2;
	}

	public void setActiveFlg2(String activeFlg2) {
		this.activeFlg2 = activeFlg2;
	}

	public String getSerial_num3() {
		return serial_num3;
	}

	public void setSerial_num3(String serial_num3) {
		this.serial_num3 = serial_num3;
	}

	public String getPrimaryCustomer3() {
		return primaryCustomer3;
	}

	public void setPrimaryCustomer3(String primaryCustomer3) {
		this.primaryCustomer3 = primaryCustomer3;
	}

	public String getCategoryType3() {
		return categoryType3;
	}

	public void setCategoryType3(String categoryType3) {
		this.categoryType3 = categoryType3;
	}

	public String getCategoryCode3() {
		return categoryCode3;
	}

	public void setCategoryCode3(String categoryCode3) {
		this.categoryCode3 = categoryCode3;
	}

	public String getActiveFlg3() {
		return activeFlg3;
	}

	public void setActiveFlg3(String activeFlg3) {
		this.activeFlg3 = activeFlg3;
	}

	public String getSanctAuthCode() {
		return sanctAuthCode;
	}

	public void setSanctAuthCode(String sanctAuthCode) {
		this.sanctAuthCode = sanctAuthCode;
	}

	public String getSanctLevelCode() {
		return sanctLevelCode;
	}

	public void setSanctLevelCode(String sanctLevelCode) {
		this.sanctLevelCode = sanctLevelCode;
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

	public String getCustSanctLim() {
		return custSanctLim;
	}

	public void setCustSanctLim(String custSanctLim) {
		this.custSanctLim = custSanctLim;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMatDateUi() {
		return matDateUi;
	}

	public void setMatDateUi(String matDateUi) {
		this.matDateUi = matDateUi;
	}

	public String getGrcMonth() {
		return grcMonth;
	}

	public void setGrcMonth(String grcMonth) {
		this.grcMonth = grcMonth;
	}

	public String getGrcDay() {
		return grcDay;
	}

	public void setGrcDay(String grcDay) {
		this.grcDay = grcDay;
	}

	public String getLimitAmt() {
		return limitAmt;
	}

	public void setLimitAmt(String limitAmt) {
		this.limitAmt = limitAmt;
	}

	public String getLimUnsecAmt() {
		return limUnsecAmt;
	}

	public void setLimUnsecAmt(String limUnsecAmt) {
		this.limUnsecAmt = limUnsecAmt;
	}

	public String getLimitSolId() {
		return limitSolId;
	}

	public void setLimitSolId(String limitSolId) {
		this.limitSolId = limitSolId;
	}

	public String getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(String maxMonth) {
		this.maxMonth = maxMonth;
	}

	public String getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(String maxDay) {
		this.maxDay = maxDay;
	}

	public String getIsCollateral() {
		return isCollateral;
	}

	public void setIsCollateral(String isCollateral) {
		this.isCollateral = isCollateral;
	}

	public String getIsTermAccount() {
		return isTermAccount;
	}

	public void setIsTermAccount(String isTermAccount) {
		this.isTermAccount = isTermAccount;
	}

	public String getIsValuecollateral() {
		return isValuecollateral;
	}

	public void setIsValuecollateral(String isValuecollateral) {
		this.isValuecollateral = isValuecollateral;
	}

	public String getTitName() {
		return titName;
	}

	public void setTitName(String titName) {
		this.titName = titName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getTotSAmt() {
		return totSAmt;
	}

	public void setTotSAmt(String totSAmt) {
		this.totSAmt = totSAmt;
	}

}
