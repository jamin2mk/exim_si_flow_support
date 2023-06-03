package com.model.odacctaddrequest.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ODAcctAdd", namespace = "http://www.alsb.com/")
public class ODAcctAddRequest {

	@SerializedName("CustId")
	@XmlElement(name = "CustId")
	protected String custId;
	@SerializedName("SchmCode")
	@XmlElement(name = "SchmCode")
	protected String schmCode;
	@SerializedName("SchmType")
	@XmlElement(name = "SchmType")
	protected String schmType;
	@SerializedName("AcctCurr")
	@XmlElement(name = "AcctCurr")
	protected String acctCurr;
	@SerializedName("BankId")
	@XmlElement(name = "BankId")
	protected String bankId;
	@SerializedName("BranchId")
	@XmlElement(name = "BranchId")
	protected String branchId;
	@SerializedName("GenLedgerSubHeadCode")
	@XmlElement(name = "GenLedgerSubHeadCode")
	protected String genLedgerSubHeadCode;
	@SerializedName("CurCode")
	@XmlElement(name = "CurCode")
	protected String curCode;
	@SerializedName("AcctName")
	@XmlElement(name = "AcctName")
	protected String acctName;
	@SerializedName("AcctShortName")
	@XmlElement(name = "AcctShortName")
	protected String acctShortName;
	@SerializedName("AcctStmtMode")
	@XmlElement(name = "AcctStmtMode")
	protected String acctStmtMode;
	@SerializedName("RelPartyType")
	@XmlElement(name = "RelPartyType")
	protected String relPartyType;
	@SerializedName("Name")
	@XmlElement(name = "Name")
	protected String name;
	@SerializedName("TitlePrefix")
	@XmlElement(name = "TitlePrefix")
	protected String titlePrefix;
	@SerializedName("amountValue")
	@XmlElement(name = "amountValue")
	protected String amountValue;
	@SerializedName("currencyCode")
	@XmlElement(name = "currencyCode")
	protected String currencyCode;
	@SerializedName("LimitLevelIntFlg")
	@XmlElement(name = "LimitLevelIntFlg")
	protected String limitLevelIntFlg;
	@SerializedName("SanctionDt")
	@XmlElement(name = "SanctionDt")
	protected String sanctionDt;
	@SerializedName("ExpDt")
	@XmlElement(name = "ExpDt")
	protected String expDt;
	@SerializedName("DocumentDt")
	@XmlElement(name = "DocumentDt")
	protected String documentDt;
	@SerializedName("ReviewDt")
	@XmlElement(name = "ReviewDt")
	protected String reviewDt;
	@SerializedName("SanctionLevel")
	@XmlElement(name = "SanctionLevel")
	protected String sanctionLevel;
	@SerializedName("DrawingPowerInd")
	@XmlElement(name = "DrawingPowerInd")
	protected String drawingPowerInd;
	@SerializedName("LimitIdPrefix")
	@XmlElement(name = "LimitIdPrefix")
	protected String limitIdPrefix;
	@SerializedName("LimitIdSuffix")
	@XmlElement(name = "LimitIdSuffix")
	protected String limitIdSuffix;
	@SerializedName("AcctRecallFlg")
	@XmlElement(name = "AcctRecallFlg")
	protected String acctRecallFlg;
	@SerializedName("value")
	@XmlElement(name = "value")
	protected String value;
	@SerializedName("IntTblCode")
	@XmlElement(name = "IntTblCode")
	protected String intTblCode;
	@SerializedName("IntPeriodInMonths")
	@XmlElement(name = "IntPeriodInMonths")
	protected String intPeriodInMonths;
	@SerializedName("IntPeriodInDays")
	@XmlElement(name = "IntPeriodInDays")
	protected String intPeriodInDays;
	@SerializedName("PeggedFlg")
	@XmlElement(name = "PeggedFlg")
	protected String peggedFlg;
	@SerializedName("LIM_CUST_ID")
	@XmlElement(name = "LIM_CUST_ID")
	protected String limCustId;
	@SerializedName("LIM_SCHM_CODE")
	@XmlElement(name = "LIM_SCHM_CODE")
	protected String limSchmCode;
	@SerializedName("LIM_CRNCY_CODE")
	@XmlElement(name = "LIM_CRNCY_CODE")
	protected String limCrncyCode;
	@SerializedName("DSA_ID")
	@XmlElement(name = "DSA_ID")
	protected String dsaId;
	@SerializedName("LANG_CODE")
	@XmlElement(name = "LANG_CODE")
	protected String langCode;
	@SerializedName("REGION")
	@XmlElement(name = "REGION")
	protected String region;
	@SerializedName("PRODUCT")
	@XmlElement(name = "PRODUCT")
	protected String product;
	@SerializedName("MARKET")
	@XmlElement(name = "MARKET")
	protected String market;
	@SerializedName("fundPurpCode")
	@XmlElement(name = "fundPurpCode")
	protected String fundPurpCode;
	@SerializedName("subPurpCodePurp2")
	@XmlElement(name = "subPurpCodePurp2")
	protected String subPurpCodePurp2;
	@SerializedName("subPurpCodePurp3")
	@XmlElement(name = "subPurpCodePurp3")
	protected String subPurpCodePurp3;
	@SerializedName("otherType1Purp4")
	@XmlElement(name = "otherType1Purp4")
	protected String otherType1Purp4;
	@SerializedName("otherType1Purp5")
	@XmlElement(name = "otherType1Purp5")
	protected String otherType1Purp5;
	@SerializedName("otherType2")
	@XmlElement(name = "otherType2")
	protected String otherType2;
	@SerializedName("industryGroup")
	@XmlElement(name = "industryGroup")
	protected String industryGroup;
	@SerializedName("crdtrskrtseq")
	@XmlElement(name = "crdtrskrtseq")
	protected String crdtrskrtseq;
	@SerializedName("insStDt")
	@XmlElement(name = "insStDt")
	protected String insStDt;
	@SerializedName("insEndDt")
	@XmlElement(name = "insEndDt")
	protected String insEndDt;
	@SerializedName("crdtrskrtdate")
	@XmlElement(name = "crdtrskrtdate")
	protected String crdtrskrtdate;
	@SerializedName("crdtrskrt")
	@XmlElement(name = "crdtrskrt")
	protected String crdtrskrt;
	@SerializedName("crdt_rnk_actv")
	@XmlElement(name = "crdt_rnk_actv")
	protected String crdtRnkActv;
	@SerializedName("crdt_rnk_actv_date")
	@XmlElement(name = "crdt_rnk_actv_date")
	protected String crdtRnkActvDate;
	@SerializedName("crdt_rnk_actv_type")
	@XmlElement(name = "crdt_rnk_actv_type")
	protected String crdtRnkActvType;
	@SerializedName("c39EnableHid")
	@XmlElement(name = "c39EnableHid")
	protected String c39EnableHid;

	public ODAcctAddRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ODAcctAddRequest(String custId, String schmCode, String schmType,
			String acctCurr, String bankId, String branchId,
			String genLedgerSubHeadCode, String curCode, String acctName,
			String acctShortName, String acctStmtMode, String relPartyType,
			String name, String titlePrefix, String amountValue,
			String currencyCode, String limitLevelIntFlg, String sanctionDt,
			String expDt, String documentDt, String reviewDt,
			String sanctionLevel, String drawingPowerInd, String limitIdPrefix,
			String limitIdSuffix, String acctRecallFlg, String value,
			String intTblCode, String intPeriodInMonths,
			String intPeriodInDays, String peggedFlg, String limCustId,
			String limSchmCode, String limCrncyCode, String dsaId,
			String langCode, String region, String product, String market,
			String fundPurpCode, String subPurpCodePurp2,
			String subPurpCodePurp3, String otherType1Purp4,
			String otherType1Purp5, String otherType2, String industryGroup,
			String crdtrskrtseq, String insStDt, String insEndDt,
			String crdtrskrtdate, String crdtrskrt, String crdtRnkActv,
			String crdtRnkActvDate, String crdtRnkActvType, String c39EnableHid) {
		super();
		this.custId = custId;
		this.schmCode = schmCode;
		this.schmType = schmType;
		this.acctCurr = acctCurr;
		this.bankId = bankId;
		this.branchId = branchId;
		this.genLedgerSubHeadCode = genLedgerSubHeadCode;
		this.curCode = curCode;
		this.acctName = acctName;
		this.acctShortName = acctShortName;
		this.acctStmtMode = acctStmtMode;
		this.relPartyType = relPartyType;
		this.name = name;
		this.titlePrefix = titlePrefix;
		this.amountValue = amountValue;
		this.currencyCode = currencyCode;
		this.limitLevelIntFlg = limitLevelIntFlg;
		this.sanctionDt = sanctionDt;
		this.expDt = expDt;
		this.documentDt = documentDt;
		this.reviewDt = reviewDt;
		this.sanctionLevel = sanctionLevel;
		this.drawingPowerInd = drawingPowerInd;
		this.limitIdPrefix = limitIdPrefix;
		this.limitIdSuffix = limitIdSuffix;
		this.acctRecallFlg = acctRecallFlg;
		this.value = value;
		this.intTblCode = intTblCode;
		this.intPeriodInMonths = intPeriodInMonths;
		this.intPeriodInDays = intPeriodInDays;
		this.peggedFlg = peggedFlg;
		this.limCustId = limCustId;
		this.limSchmCode = limSchmCode;
		this.limCrncyCode = limCrncyCode;
		this.dsaId = dsaId;
		this.langCode = langCode;
		this.region = region;
		this.product = product;
		this.market = market;
		this.fundPurpCode = fundPurpCode;
		this.subPurpCodePurp2 = subPurpCodePurp2;
		this.subPurpCodePurp3 = subPurpCodePurp3;
		this.otherType1Purp4 = otherType1Purp4;
		this.otherType1Purp5 = otherType1Purp5;
		this.otherType2 = otherType2;
		this.industryGroup = industryGroup;
		this.crdtrskrtseq = crdtrskrtseq;
		this.insStDt = insStDt;
		this.insEndDt = insEndDt;
		this.crdtrskrtdate = crdtrskrtdate;
		this.crdtrskrt = crdtrskrt;
		this.crdtRnkActv = crdtRnkActv;
		this.crdtRnkActvDate = crdtRnkActvDate;
		this.crdtRnkActvType = crdtRnkActvType;
		this.c39EnableHid = c39EnableHid;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getAcctShortName() {
		return acctShortName;
	}

	public void setAcctShortName(String acctShortName) {
		this.acctShortName = acctShortName;
	}

	public String getAcctStmtMode() {
		return acctStmtMode;
	}

	public void setAcctStmtMode(String acctStmtMode) {
		this.acctStmtMode = acctStmtMode;
	}

	public String getRelPartyType() {
		return relPartyType;
	}

	public void setRelPartyType(String relPartyType) {
		this.relPartyType = relPartyType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitlePrefix() {
		return titlePrefix;
	}

	public void setTitlePrefix(String titlePrefix) {
		this.titlePrefix = titlePrefix;
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

	public String getLimitLevelIntFlg() {
		return limitLevelIntFlg;
	}

	public void setLimitLevelIntFlg(String limitLevelIntFlg) {
		this.limitLevelIntFlg = limitLevelIntFlg;
	}

	public String getSanctionDt() {
		return sanctionDt;
	}

	public void setSanctionDt(String sanctionDt) {
		this.sanctionDt = sanctionDt;
	}

	public String getExpDt() {
		return expDt;
	}

	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public String getDocumentDt() {
		return documentDt;
	}

	public void setDocumentDt(String documentDt) {
		this.documentDt = documentDt;
	}

	public String getReviewDt() {
		return reviewDt;
	}

	public void setReviewDt(String reviewDt) {
		this.reviewDt = reviewDt;
	}

	public String getSanctionLevel() {
		return sanctionLevel;
	}

	public void setSanctionLevel(String sanctionLevel) {
		this.sanctionLevel = sanctionLevel;
	}

	public String getDrawingPowerInd() {
		return drawingPowerInd;
	}

	public void setDrawingPowerInd(String drawingPowerInd) {
		this.drawingPowerInd = drawingPowerInd;
	}

	public String getLimitIdPrefix() {
		return limitIdPrefix;
	}

	public void setLimitIdPrefix(String limitIdPrefix) {
		this.limitIdPrefix = limitIdPrefix;
	}

	public String getLimitIdSuffix() {
		return limitIdSuffix;
	}

	public void setLimitIdSuffix(String limitIdSuffix) {
		this.limitIdSuffix = limitIdSuffix;
	}

	public String getAcctRecallFlg() {
		return acctRecallFlg;
	}

	public void setAcctRecallFlg(String acctRecallFlg) {
		this.acctRecallFlg = acctRecallFlg;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIntTblCode() {
		return intTblCode;
	}

	public void setIntTblCode(String intTblCode) {
		this.intTblCode = intTblCode;
	}

	public String getIntPeriodInMonths() {
		return intPeriodInMonths;
	}

	public void setIntPeriodInMonths(String intPeriodInMonths) {
		this.intPeriodInMonths = intPeriodInMonths;
	}

	public String getIntPeriodInDays() {
		return intPeriodInDays;
	}

	public void setIntPeriodInDays(String intPeriodInDays) {
		this.intPeriodInDays = intPeriodInDays;
	}

	public String getPeggedFlg() {
		return peggedFlg;
	}

	public void setPeggedFlg(String peggedFlg) {
		this.peggedFlg = peggedFlg;
	}

	public String getLimCustId() {
		return limCustId;
	}

	public void setLimCustId(String limCustId) {
		this.limCustId = limCustId;
	}

	public String getLimSchmCode() {
		return limSchmCode;
	}

	public void setLimSchmCode(String limSchmCode) {
		this.limSchmCode = limSchmCode;
	}

	public String getLimCrncyCode() {
		return limCrncyCode;
	}

	public void setLimCrncyCode(String limCrncyCode) {
		this.limCrncyCode = limCrncyCode;
	}

	public String getDsaId() {
		return dsaId;
	}

	public void setDsaId(String dsaId) {
		this.dsaId = dsaId;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
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

	public String getFundPurpCode() {
		return fundPurpCode;
	}

	public void setFundPurpCode(String fundPurpCode) {
		this.fundPurpCode = fundPurpCode;
	}

	public String getSubPurpCodePurp2() {
		return subPurpCodePurp2;
	}

	public void setSubPurpCodePurp2(String subPurpCodePurp2) {
		this.subPurpCodePurp2 = subPurpCodePurp2;
	}

	public String getSubPurpCodePurp3() {
		return subPurpCodePurp3;
	}

	public void setSubPurpCodePurp3(String subPurpCodePurp3) {
		this.subPurpCodePurp3 = subPurpCodePurp3;
	}

	public String getOtherType1Purp4() {
		return otherType1Purp4;
	}

	public void setOtherType1Purp4(String otherType1Purp4) {
		this.otherType1Purp4 = otherType1Purp4;
	}

	public String getOtherType1Purp5() {
		return otherType1Purp5;
	}

	public void setOtherType1Purp5(String otherType1Purp5) {
		this.otherType1Purp5 = otherType1Purp5;
	}

	public String getOtherType2() {
		return otherType2;
	}

	public void setOtherType2(String otherType2) {
		this.otherType2 = otherType2;
	}

	public String getIndustryGroup() {
		return industryGroup;
	}

	public void setIndustryGroup(String industryGroup) {
		this.industryGroup = industryGroup;
	}

	public String getCrdtrskrtseq() {
		return crdtrskrtseq;
	}

	public void setCrdtrskrtseq(String crdtrskrtseq) {
		this.crdtrskrtseq = crdtrskrtseq;
	}

	public String getInsStDt() {
		return insStDt;
	}

	public void setInsStDt(String insStDt) {
		this.insStDt = insStDt;
	}

	public String getInsEndDt() {
		return insEndDt;
	}

	public void setInsEndDt(String insEndDt) {
		this.insEndDt = insEndDt;
	}

	public String getCrdtrskrtdate() {
		return crdtrskrtdate;
	}

	public void setCrdtrskrtdate(String crdtrskrtdate) {
		this.crdtrskrtdate = crdtrskrtdate;
	}

	public String getCrdtrskrt() {
		return crdtrskrt;
	}

	public void setCrdtrskrt(String crdtrskrt) {
		this.crdtrskrt = crdtrskrt;
	}

	public String getCrdtRnkActv() {
		return crdtRnkActv;
	}

	public void setCrdtRnkActv(String crdtRnkActv) {
		this.crdtRnkActv = crdtRnkActv;
	}

	public String getCrdtRnkActvDate() {
		return crdtRnkActvDate;
	}

	public void setCrdtRnkActvDate(String crdtRnkActvDate) {
		this.crdtRnkActvDate = crdtRnkActvDate;
	}

	public String getCrdtRnkActvType() {
		return crdtRnkActvType;
	}

	public void setCrdtRnkActvType(String crdtRnkActvType) {
		this.crdtRnkActvType = crdtRnkActvType;
	}

	public String getC39EnableHid() {
		return c39EnableHid;
	}

	public void setC39EnableHid(String c39EnableHid) {
		this.c39EnableHid = c39EnableHid;
	}

}
