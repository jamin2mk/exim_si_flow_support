package com.model.openloanaccount.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "OpenLoanAccount", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpenLoanAccountRequest {

	@XmlElement(name = "CustId")
	@SerializedName("CustId")
	protected String custId;
	@XmlElement(name = "LASchmCode")
	@SerializedName("LASchmCode")
	protected String lasChmCode;
	@XmlElement(name = "LASchmType")
	@SerializedName("LASchmType")
	protected String lasShmType;
	@XmlElement(name = "LAAcctCurr")
	@SerializedName("LAAcctCurr")
	protected String laaCctCurr;
	@XmlElement(name = "LABranchId")
	@SerializedName("LABranchId")
	protected String laBranchId;
	@XmlElement(name = "GenLedgerSubHeadCode")
	@SerializedName("GenLedgerSubHeadCode")
	protected String genLedgerSubHeadCode;
	@XmlElement(name = "CurCode")
	@SerializedName("CurCode")
	protected String curCode;
	@XmlElement(name = "AcctStmtMode")
	@SerializedName("AcctStmtMode")
	protected String acctStmtMode;
	@XmlElement(name = "LoanPeriodMonths")
	@SerializedName("LoanPeriodMonths")
	protected String loanPeriodMonths;
	@XmlElement(name = "LoanPeriodDays")
	@SerializedName("LoanPeriodDays")
	protected String loanPeriodDays;
	@XmlElement(name = "RePmtMethod")
	@SerializedName("RePmtMethod")
	protected String rePmtMethod;
	@XmlElement(name = "AcctId")
	@SerializedName("AcctId")
	protected String acctId;
	@XmlElement(name = "SchmCode")
	@SerializedName("SchmCode")
	protected String schmCode;
	@XmlElement(name = "SchmType")
	@SerializedName("SchmType")
	protected String schmType;
	@XmlElement(name = "AcctCurr")
	@SerializedName("AcctCurr")
	protected String acctCurr;
	@XmlElement(name = "BankId")
	@SerializedName("BankId")
	protected String bankId;
	@XmlElement(name = "BranchId")
	@SerializedName("BranchId")
	protected String branchId;
	@XmlElement(name = "EqInstallFlg")
	@SerializedName("EqInstallFlg")
	protected String eqInstallFlg;
	@XmlElement(name = "InstallmentId1")
	@SerializedName("InstallmentId1")
	protected String installmentId1;
	@XmlElement(name = "InstallStartDt1")
	@SerializedName("InstallStartDt1")
	protected String installStartDt1;
	@XmlElement(name = "InstallFreq_Cal1")
	@SerializedName("InstallFreq_Cal1")
	protected String installFreqCal1;
	@XmlElement(name = "InstallFreq_Type1")
	@SerializedName("InstallFreq_Type1")
	protected String installFreqType1;
	@XmlElement(name = "InstallFreq_StartDt1")
	@SerializedName("InstallFreq_StartDt1")
	protected String installFreqStartDt1;
	@XmlElement(name = "InstallFreq_WeekDay1")
	@SerializedName("InstallFreq_WeekDay1")
	protected String installFreqWeekDay1;
	@XmlElement(name = "InstallFreq_HolStat1")
	@SerializedName("InstallFreq_HolStat1")
	protected String installFreqHolStat1;
	@XmlElement(name = "IntFreq_Cal1")
	@SerializedName("IntFreq_Cal1")
	protected String intFreqCal1;
	@XmlElement(name = "IntFreq_Type1")
	@SerializedName("IntFreq_Type1")
	protected String intFreqType1;
	@XmlElement(name = "IntFreq_StartDt1")
	@SerializedName("IntFreq_StartDt1")
	protected String intFreqStartDt1;
	@XmlElement(name = "IntFreq_WeekDay1")
	@SerializedName("IntFreq_WeekDay1")
	protected String intFreqWeekDay1;
	@XmlElement(name = "IntFreq_HolStat1")
	@SerializedName("IntFreq_HolStat1")
	protected String intFreqHolStat1;
	@XmlElement(name = "NoOfInstall")
	@SerializedName("NoOfInstall")
	protected String noOfInstall;
	@XmlElement(name = "IntStartDt1")
	@SerializedName("IntStartDt1")
	protected String intStartDt1;
	@XmlElement(name = "FlowAmt_amountValue1")
	@SerializedName("FlowAmt_amountValue1")
	protected String flowAmtamountValue1;
	@XmlElement(name = "FlowAmt_currencyCode1")
	@SerializedName("FlowAmt_currencyCode1")
	protected String flowAmtcurrencyCode1;
	@XmlElement(name = "InstallmentId2")
	@SerializedName("InstallmentId2")
	protected String installmentId2;
	@XmlElement(name = "InstallStartDt2")
	@SerializedName("InstallStartDt2")
	protected String installStartDt2;
	@XmlElement(name = "InstallFreq_Cal2")
	@SerializedName("InstallFreq_Cal2")
	protected String installFreqCal2;
	@XmlElement(name = "InstallFreq_Type2")
	@SerializedName("InstallFreq_Type2")
	protected String installFreqType2;
	@XmlElement(name = "InstallFreq_StartDt2")
	@SerializedName("InstallFreq_StartDt2")
	protected String installFreqStartDt2;
	@XmlElement(name = "InstallFreq_WeekDay2")
	@SerializedName("InstallFreq_WeekDay2")
	protected String installFreqWeekDay2;
	@XmlElement(name = "InstallFreq_HolStat2")
	@SerializedName("InstallFreq_HolStat2")
	protected String installFreqHolStat2;
	@XmlElement(name = "IntFreq_Cal2")
	@SerializedName("IntFreq_Cal2")
	protected String intFreqCal2;
	@XmlElement(name = "IntFreq_Type2")
	@SerializedName("IntFreq_Type2")
	protected String intFreqType2;
	@XmlElement(name = "IntFreq_StartDt2")
	@SerializedName("IntFreq_StartDt2")
	protected String intFreqStartDt2;
	@XmlElement(name = "IntFreq_WeekDay2")
	@SerializedName("IntFreq_WeekDay2")
	protected String intFreqWeekDay2;
	@XmlElement(name = "IntFreq_HolStat2")
	@SerializedName("IntFreq_HolStat2")
	protected String intFreqHolStat2;
	@XmlElement(name = "IntStartDt2")
	@SerializedName("IntStartDt2")
	protected String intStartDt2;
	@XmlElement(name = "FlowAmt_amountValue2")
	@SerializedName("FlowAmt_amountValue2")
	protected String flowAmtamountValue2;
	@XmlElement(name = "FlowAmt_currencyCode2")
	@SerializedName("FlowAmt_currencyCode2")
	protected String flowAmtcurrencyCode2;
	@XmlElement(name = "LoanAmt_amountValue")
	@SerializedName("LoanAmt_amountValue")
	protected String loanAmtamountValue;
	@XmlElement(name = "LoanAmt_currencyCode")
	@SerializedName("LoanAmt_currencyCode")
	protected String loanAmtcurrencyCode;
	@XmlElement(name = "HoldInOperAcctFlg")
	@SerializedName("HoldInOperAcctFlg")
	protected String holdInOperAcctFlg;
	@XmlElement(name = "AcctDrPrefPcnt")
	@SerializedName("AcctDrPrefPcnt")
	protected String acctDrPrefPcnt;
	@XmlElement(name = "MaxIntPcntDr")
	@SerializedName("MaxIntPcntDr")
	protected String maxIntPcntDr;
	@XmlElement(name = "MinIntPcntDr")
	@SerializedName("MinIntPcntDr")
	protected String minIntPcntDr;
	@XmlElement(name = "OCCUPATION")
	@SerializedName("OCCUPATION")
	protected String occupation;
	@XmlElement(name = "REGION")
	@SerializedName("REGION")
	protected String region;
	@XmlElement(name = "PRODUCT")
	@SerializedName("PRODUCT")
	protected String product;
	@XmlElement(name = "MARKET")
	@SerializedName("MARKET")
	protected String market;
	@XmlElement(name = "otherType2")
	@SerializedName("otherType2")
	protected String otherType2;
	@XmlElement(name = "ACCT_LIM_ENT")
	@SerializedName("ACCT_LIM_ENT")
	protected String acctLimEnt;
	@XmlElement(name = "DRWNG_PCNT")
	@SerializedName("DRWNG_PCNT")
	protected String drwngPcnt;
	@XmlElement(name = "LIM_DOC_DATE")
	@SerializedName("LIM_DOC_DATE")
	protected String limDocDate;
	@XmlElement(name = "LIM_EXP_DATE")
	@SerializedName("LIM_EXP_DATE")
	protected String limExpDate;
	@XmlElement(name = "LIM_LEVL_INT")
	@SerializedName("LIM_LEVL_INT")
	protected String limLevlInt;
	@XmlElement(name = "LIM_PREFIX")
	@SerializedName("LIM_PREFIX")
	protected String limPrefix;
	@XmlElement(name = "LIM_SUFFIX")
	@SerializedName("LIM_SUFFIX")
	protected String limSuffix;
	@XmlElement(name = "SANCTL_LIM")
	@SerializedName("SANCTL_LIM")
	protected String sanctlLim;
	@XmlElement(name = "SANCTL_LIM_CRNCY")
	@SerializedName("SANCTL_LIM_CRNCY")
	protected String sanctlLimCrncy;
	@XmlElement(name = "SANCT_DATE")
	@SerializedName("SANCT_DATE")
	protected String sanctDate;
	@XmlElement(name = "DRWNG_IND")
	@SerializedName("DRWNG_IND")
	protected String drwngInd;
	@XmlElement(name = "LIM_CIF_ID")
	@SerializedName("LIM_CIF_ID")
	protected String limCifId;
	@XmlElement(name = "LIM_CRNCY_CODE")
	@SerializedName("LIM_CRNCY_CODE")
	protected String limCrncyCode;
	@XmlElement(name = "LIM_SCHM_CODE")
	@SerializedName("LIM_SCHM_CODE")
	protected String limSchmCode;

	public OpenLoanAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OpenLoanAccountRequest(String custId, String lasChmCode,
			String lasShmType, String laaCctCurr, String laBranchId,
			String genLedgerSubHeadCode, String curCode, String acctStmtMode,
			String loanPeriodMonths, String loanPeriodDays, String rePmtMethod,
			String acctId, String schmCode, String schmType, String acctCurr,
			String bankId, String branchId, String eqInstallFlg,
			String installmentId1, String installStartDt1,
			String installFreqCal1, String installFreqType1,
			String installFreqStartDt1, String installFreqWeekDay1,
			String installFreqHolStat1, String intFreqCal1,
			String intFreqType1, String intFreqStartDt1,
			String intFreqWeekDay1, String intFreqHolStat1, String noOfInstall,
			String intStartDt1, String flowAmtamountValue1,
			String flowAmtcurrencyCode1, String installmentId2,
			String installStartDt2, String installFreqCal2,
			String installFreqType2, String installFreqStartDt2,
			String installFreqWeekDay2, String installFreqHolStat2,
			String intFreqCal2, String intFreqType2, String intFreqStartDt2,
			String intFreqWeekDay2, String intFreqHolStat2, String intStartDt2,
			String flowAmtamountValue2, String flowAmtcurrencyCode2,
			String loanAmtamountValue, String loanAmtcurrencyCode,
			String holdInOperAcctFlg, String acctDrPrefPcnt,
			String maxIntPcntDr, String minIntPcntDr, String occupation,
			String region, String product, String market, String otherType2,
			String acctLimEnt, String drwngPcnt, String limDocDate,
			String limExpDate, String limLevlInt, String limPrefix,
			String limSuffix, String sanctlLim, String sanctlLimCrncy,
			String sanctDate, String drwngInd, String limCifId,
			String limCrncyCode, String limSchmCode) {
		super();
		this.custId = custId;
		this.lasChmCode = lasChmCode;
		this.lasShmType = lasShmType;
		this.laaCctCurr = laaCctCurr;
		this.laBranchId = laBranchId;
		this.genLedgerSubHeadCode = genLedgerSubHeadCode;
		this.curCode = curCode;
		this.acctStmtMode = acctStmtMode;
		this.loanPeriodMonths = loanPeriodMonths;
		this.loanPeriodDays = loanPeriodDays;
		this.rePmtMethod = rePmtMethod;
		this.acctId = acctId;
		this.schmCode = schmCode;
		this.schmType = schmType;
		this.acctCurr = acctCurr;
		this.bankId = bankId;
		this.branchId = branchId;
		this.eqInstallFlg = eqInstallFlg;
		this.installmentId1 = installmentId1;
		this.installStartDt1 = installStartDt1;
		this.installFreqCal1 = installFreqCal1;
		this.installFreqType1 = installFreqType1;
		this.installFreqStartDt1 = installFreqStartDt1;
		this.installFreqWeekDay1 = installFreqWeekDay1;
		this.installFreqHolStat1 = installFreqHolStat1;
		this.intFreqCal1 = intFreqCal1;
		this.intFreqType1 = intFreqType1;
		this.intFreqStartDt1 = intFreqStartDt1;
		this.intFreqWeekDay1 = intFreqWeekDay1;
		this.intFreqHolStat1 = intFreqHolStat1;
		this.noOfInstall = noOfInstall;
		this.intStartDt1 = intStartDt1;
		this.flowAmtamountValue1 = flowAmtamountValue1;
		this.flowAmtcurrencyCode1 = flowAmtcurrencyCode1;
		this.installmentId2 = installmentId2;
		this.installStartDt2 = installStartDt2;
		this.installFreqCal2 = installFreqCal2;
		this.installFreqType2 = installFreqType2;
		this.installFreqStartDt2 = installFreqStartDt2;
		this.installFreqWeekDay2 = installFreqWeekDay2;
		this.installFreqHolStat2 = installFreqHolStat2;
		this.intFreqCal2 = intFreqCal2;
		this.intFreqType2 = intFreqType2;
		this.intFreqStartDt2 = intFreqStartDt2;
		this.intFreqWeekDay2 = intFreqWeekDay2;
		this.intFreqHolStat2 = intFreqHolStat2;
		this.intStartDt2 = intStartDt2;
		this.flowAmtamountValue2 = flowAmtamountValue2;
		this.flowAmtcurrencyCode2 = flowAmtcurrencyCode2;
		this.loanAmtamountValue = loanAmtamountValue;
		this.loanAmtcurrencyCode = loanAmtcurrencyCode;
		this.holdInOperAcctFlg = holdInOperAcctFlg;
		this.acctDrPrefPcnt = acctDrPrefPcnt;
		this.maxIntPcntDr = maxIntPcntDr;
		this.minIntPcntDr = minIntPcntDr;
		this.occupation = occupation;
		this.region = region;
		this.product = product;
		this.market = market;
		this.otherType2 = otherType2;
		this.acctLimEnt = acctLimEnt;
		this.drwngPcnt = drwngPcnt;
		this.limDocDate = limDocDate;
		this.limExpDate = limExpDate;
		this.limLevlInt = limLevlInt;
		this.limPrefix = limPrefix;
		this.limSuffix = limSuffix;
		this.sanctlLim = sanctlLim;
		this.sanctlLimCrncy = sanctlLimCrncy;
		this.sanctDate = sanctDate;
		this.drwngInd = drwngInd;
		this.limCifId = limCifId;
		this.limCrncyCode = limCrncyCode;
		this.limSchmCode = limSchmCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getLasChmCode() {
		return lasChmCode;
	}

	public void setLasChmCode(String lasChmCode) {
		this.lasChmCode = lasChmCode;
	}

	public String getLasShmType() {
		return lasShmType;
	}

	public void setLasShmType(String lasShmType) {
		this.lasShmType = lasShmType;
	}

	public String getLaaCctCurr() {
		return laaCctCurr;
	}

	public void setLaaCctCurr(String laaCctCurr) {
		this.laaCctCurr = laaCctCurr;
	}

	public String getLaBranchId() {
		return laBranchId;
	}

	public void setLaBranchId(String laBranchId) {
		this.laBranchId = laBranchId;
	}

	public String getGenLedgerSubHeadCode() {
		return genLedgerSubHeadCode;
	}

	public void setGenLedgerSubHeadCode(String genLedgerSubHeadCode) {
		this.genLedgerSubHeadCode = genLedgerSubHeadCode;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public String getAcctStmtMode() {
		return acctStmtMode;
	}

	public void setAcctStmtMode(String acctStmtMode) {
		this.acctStmtMode = acctStmtMode;
	}

	public String getLoanPeriodMonths() {
		return loanPeriodMonths;
	}

	public void setLoanPeriodMonths(String loanPeriodMonths) {
		this.loanPeriodMonths = loanPeriodMonths;
	}

	public String getLoanPeriodDays() {
		return loanPeriodDays;
	}

	public void setLoanPeriodDays(String loanPeriodDays) {
		this.loanPeriodDays = loanPeriodDays;
	}

	public String getRePmtMethod() {
		return rePmtMethod;
	}

	public void setRePmtMethod(String rePmtMethod) {
		this.rePmtMethod = rePmtMethod;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getSchmCode() {
		return schmCode;
	}

	public void setSchmCode(String schmCode) {
		this.schmCode = schmCode;
	}

	public String getSchmType() {
		return schmType;
	}

	public void setSchmType(String schmType) {
		this.schmType = schmType;
	}

	public String getAcctCurr() {
		return acctCurr;
	}

	public void setAcctCurr(String acctCurr) {
		this.acctCurr = acctCurr;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getEqInstallFlg() {
		return eqInstallFlg;
	}

	public void setEqInstallFlg(String eqInstallFlg) {
		this.eqInstallFlg = eqInstallFlg;
	}

	public String getInstallmentId1() {
		return installmentId1;
	}

	public void setInstallmentId1(String installmentId1) {
		this.installmentId1 = installmentId1;
	}

	public String getInstallStartDt1() {
		return installStartDt1;
	}

	public void setInstallStartDt1(String installStartDt1) {
		this.installStartDt1 = installStartDt1;
	}

	public String getInstallFreqCal1() {
		return installFreqCal1;
	}

	public void setInstallFreqCal1(String installFreqCal1) {
		this.installFreqCal1 = installFreqCal1;
	}

	public String getInstallFreqType1() {
		return installFreqType1;
	}

	public void setInstallFreqType1(String installFreqType1) {
		this.installFreqType1 = installFreqType1;
	}

	public String getInstallFreqStartDt1() {
		return installFreqStartDt1;
	}

	public void setInstallFreqStartDt1(String installFreqStartDt1) {
		this.installFreqStartDt1 = installFreqStartDt1;
	}

	public String getInstallFreqWeekDay1() {
		return installFreqWeekDay1;
	}

	public void setInstallFreqWeekDay1(String installFreqWeekDay1) {
		this.installFreqWeekDay1 = installFreqWeekDay1;
	}

	public String getInstallFreqHolStat1() {
		return installFreqHolStat1;
	}

	public void setInstallFreqHolStat1(String installFreqHolStat1) {
		this.installFreqHolStat1 = installFreqHolStat1;
	}

	public String getIntFreqCal1() {
		return intFreqCal1;
	}

	public void setIntFreqCal1(String intFreqCal1) {
		this.intFreqCal1 = intFreqCal1;
	}

	public String getIntFreqType1() {
		return intFreqType1;
	}

	public void setIntFreqType1(String intFreqType1) {
		this.intFreqType1 = intFreqType1;
	}

	public String getIntFreqStartDt1() {
		return intFreqStartDt1;
	}

	public void setIntFreqStartDt1(String intFreqStartDt1) {
		this.intFreqStartDt1 = intFreqStartDt1;
	}

	public String getIntFreqWeekDay1() {
		return intFreqWeekDay1;
	}

	public void setIntFreqWeekDay1(String intFreqWeekDay1) {
		this.intFreqWeekDay1 = intFreqWeekDay1;
	}

	public String getIntFreqHolStat1() {
		return intFreqHolStat1;
	}

	public void setIntFreqHolStat1(String intFreqHolStat1) {
		this.intFreqHolStat1 = intFreqHolStat1;
	}

	public String getNoOfInstall() {
		return noOfInstall;
	}

	public void setNoOfInstall(String noOfInstall) {
		this.noOfInstall = noOfInstall;
	}

	public String getIntStartDt1() {
		return intStartDt1;
	}

	public void setIntStartDt1(String intStartDt1) {
		this.intStartDt1 = intStartDt1;
	}

	public String getFlowAmtamountValue1() {
		return flowAmtamountValue1;
	}

	public void setFlowAmtamountValue1(String flowAmtamountValue1) {
		this.flowAmtamountValue1 = flowAmtamountValue1;
	}

	public String getFlowAmtcurrencyCode1() {
		return flowAmtcurrencyCode1;
	}

	public void setFlowAmtcurrencyCode1(String flowAmtcurrencyCode1) {
		this.flowAmtcurrencyCode1 = flowAmtcurrencyCode1;
	}

	public String getInstallmentId2() {
		return installmentId2;
	}

	public void setInstallmentId2(String installmentId2) {
		this.installmentId2 = installmentId2;
	}

	public String getInstallStartDt2() {
		return installStartDt2;
	}

	public void setInstallStartDt2(String installStartDt2) {
		this.installStartDt2 = installStartDt2;
	}

	public String getInstallFreqCal2() {
		return installFreqCal2;
	}

	public void setInstallFreqCal2(String installFreqCal2) {
		this.installFreqCal2 = installFreqCal2;
	}

	public String getInstallFreqType2() {
		return installFreqType2;
	}

	public void setInstallFreqType2(String installFreqType2) {
		this.installFreqType2 = installFreqType2;
	}

	public String getInstallFreqStartDt2() {
		return installFreqStartDt2;
	}

	public void setInstallFreqStartDt2(String installFreqStartDt2) {
		this.installFreqStartDt2 = installFreqStartDt2;
	}

	public String getInstallFreqWeekDay2() {
		return installFreqWeekDay2;
	}

	public void setInstallFreqWeekDay2(String installFreqWeekDay2) {
		this.installFreqWeekDay2 = installFreqWeekDay2;
	}

	public String getInstallFreqHolStat2() {
		return installFreqHolStat2;
	}

	public void setInstallFreqHolStat2(String installFreqHolStat2) {
		this.installFreqHolStat2 = installFreqHolStat2;
	}

	public String getIntFreqCal2() {
		return intFreqCal2;
	}

	public void setIntFreqCal2(String intFreqCal2) {
		this.intFreqCal2 = intFreqCal2;
	}

	public String getIntFreqType2() {
		return intFreqType2;
	}

	public void setIntFreqType2(String intFreqType2) {
		this.intFreqType2 = intFreqType2;
	}

	public String getIntFreqStartDt2() {
		return intFreqStartDt2;
	}

	public void setIntFreqStartDt2(String intFreqStartDt2) {
		this.intFreqStartDt2 = intFreqStartDt2;
	}

	public String getIntFreqWeekDay2() {
		return intFreqWeekDay2;
	}

	public void setIntFreqWeekDay2(String intFreqWeekDay2) {
		this.intFreqWeekDay2 = intFreqWeekDay2;
	}

	public String getIntFreqHolStat2() {
		return intFreqHolStat2;
	}

	public void setIntFreqHolStat2(String intFreqHolStat2) {
		this.intFreqHolStat2 = intFreqHolStat2;
	}

	public String getIntStartDt2() {
		return intStartDt2;
	}

	public void setIntStartDt2(String intStartDt2) {
		this.intStartDt2 = intStartDt2;
	}

	public String getFlowAmtamountValue2() {
		return flowAmtamountValue2;
	}

	public void setFlowAmtamountValue2(String flowAmtamountValue2) {
		this.flowAmtamountValue2 = flowAmtamountValue2;
	}

	public String getFlowAmtcurrencyCode2() {
		return flowAmtcurrencyCode2;
	}

	public void setFlowAmtcurrencyCode2(String flowAmtcurrencyCode2) {
		this.flowAmtcurrencyCode2 = flowAmtcurrencyCode2;
	}

	public String getLoanAmtamountValue() {
		return loanAmtamountValue;
	}

	public void setLoanAmtamountValue(String loanAmtamountValue) {
		this.loanAmtamountValue = loanAmtamountValue;
	}

	public String getLoanAmtcurrencyCode() {
		return loanAmtcurrencyCode;
	}

	public void setLoanAmtcurrencyCode(String loanAmtcurrencyCode) {
		this.loanAmtcurrencyCode = loanAmtcurrencyCode;
	}

	public String getHoldInOperAcctFlg() {
		return holdInOperAcctFlg;
	}

	public void setHoldInOperAcctFlg(String holdInOperAcctFlg) {
		this.holdInOperAcctFlg = holdInOperAcctFlg;
	}

	public String getAcctDrPrefPcnt() {
		return acctDrPrefPcnt;
	}

	public void setAcctDrPrefPcnt(String acctDrPrefPcnt) {
		this.acctDrPrefPcnt = acctDrPrefPcnt;
	}

	public String getMaxIntPcntDr() {
		return maxIntPcntDr;
	}

	public void setMaxIntPcntDr(String maxIntPcntDr) {
		this.maxIntPcntDr = maxIntPcntDr;
	}

	public String getMinIntPcntDr() {
		return minIntPcntDr;
	}

	public void setMinIntPcntDr(String minIntPcntDr) {
		this.minIntPcntDr = minIntPcntDr;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getOtherType2() {
		return otherType2;
	}

	public void setOtherType2(String otherType2) {
		this.otherType2 = otherType2;
	}

	public String getAcctLimEnt() {
		return acctLimEnt;
	}

	public void setAcctLimEnt(String acctLimEnt) {
		this.acctLimEnt = acctLimEnt;
	}

	public String getDrwngPcnt() {
		return drwngPcnt;
	}

	public void setDrwngPcnt(String drwngPcnt) {
		this.drwngPcnt = drwngPcnt;
	}

	public String getLimDocDate() {
		return limDocDate;
	}

	public void setLimDocDate(String limDocDate) {
		this.limDocDate = limDocDate;
	}

	public String getLimExpDate() {
		return limExpDate;
	}

	public void setLimExpDate(String limExpDate) {
		this.limExpDate = limExpDate;
	}

	public String getLimLevlInt() {
		return limLevlInt;
	}

	public void setLimLevlInt(String limLevlInt) {
		this.limLevlInt = limLevlInt;
	}

	public String getLimPrefix() {
		return limPrefix;
	}

	public void setLimPrefix(String limPrefix) {
		this.limPrefix = limPrefix;
	}

	public String getLimSuffix() {
		return limSuffix;
	}

	public void setLimSuffix(String limSuffix) {
		this.limSuffix = limSuffix;
	}

	public String getSanctlLim() {
		return sanctlLim;
	}

	public void setSanctlLim(String sanctlLim) {
		this.sanctlLim = sanctlLim;
	}

	public String getSanctlLimCrncy() {
		return sanctlLimCrncy;
	}

	public void setSanctlLimCrncy(String sanctlLimCrncy) {
		this.sanctlLimCrncy = sanctlLimCrncy;
	}

	public String getSanctDate() {
		return sanctDate;
	}

	public void setSanctDate(String sanctDate) {
		this.sanctDate = sanctDate;
	}

	public String getDrwngInd() {
		return drwngInd;
	}

	public void setDrwngInd(String drwngInd) {
		this.drwngInd = drwngInd;
	}

	public String getLimCifId() {
		return limCifId;
	}

	public void setLimCifId(String limCifId) {
		this.limCifId = limCifId;
	}

	public String getLimCrncyCode() {
		return limCrncyCode;
	}

	public void setLimCrncyCode(String limCrncyCode) {
		this.limCrncyCode = limCrncyCode;
	}

	public String getLimSchmCode() {
		return limSchmCode;
	}

	public void setLimSchmCode(String limSchmCode) {
		this.limSchmCode = limSchmCode;
	}

}
