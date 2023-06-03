package com.model.check_aml.req.v_1_0;

public class BodyRequest {

	protected String formatType;
	protected String fBranch;
	protected String fTrxRef;
	protected String fCurrency;
	protected String fAmount;
	protected String fIssuerName;
	protected String fIssuerId;
	protected String fIssuerCatergory;
	protected String fIssuerAddress;
	protected String fIssuerCity;
	protected String fIssuerCountry;
	protected String fFromBank;
	protected String fToBank;
	protected String fBenName;
	protected String fBenId;
	protected String fBenCategory;
	protected String fBenAddress;
	protected String fBenCity;
	protected String fBenCountry;

	public BodyRequest() {
		// TODO Auto-generated constructor stub
	}

	public BodyRequest(String formatType, String fBranch, String fTrxRef,
			String fCurrency, String fAmount, String fIssuerName,
			String fIssuerId, String fIssuerCatergory, String fIssuerAddress,
			String fIssuerCity, String fIssuerCountry, String fFromBank,
			String fToBank, String fBenName, String fBenId,
			String fBenCategory, String fBenAddress, String fBenCity,
			String fBenCountry) {
		super();
		this.formatType = formatType;
		this.fBranch = fBranch;
		this.fTrxRef = fTrxRef;
		this.fCurrency = fCurrency;
		this.fAmount = fAmount;
		this.fIssuerName = fIssuerName;
		this.fIssuerId = fIssuerId;
		this.fIssuerCatergory = fIssuerCatergory;
		this.fIssuerAddress = fIssuerAddress;
		this.fIssuerCity = fIssuerCity;
		this.fIssuerCountry = fIssuerCountry;
		this.fFromBank = fFromBank;
		this.fToBank = fToBank;
		this.fBenName = fBenName;
		this.fBenId = fBenId;
		this.fBenCategory = fBenCategory;
		this.fBenAddress = fBenAddress;
		this.fBenCity = fBenCity;
		this.fBenCountry = fBenCountry;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getfBranch() {
		return fBranch;
	}

	public void setfBranch(String fBranch) {
		this.fBranch = fBranch;
	}

	public String getfTrxRef() {
		return fTrxRef;
	}

	public void setfTrxRef(String fTrxRef) {
		this.fTrxRef = fTrxRef;
	}

	public String getfCurrency() {
		return fCurrency;
	}

	public void setfCurrency(String fCurrency) {
		this.fCurrency = fCurrency;
	}

	public String getfAmount() {
		return fAmount;
	}

	public void setfAmount(String fAmount) {
		this.fAmount = fAmount;
	}

	public String getfIssuerName() {
		return fIssuerName;
	}

	public void setfIssuerName(String fIssuerName) {
		this.fIssuerName = fIssuerName;
	}

	public String getfIssuerId() {
		return fIssuerId;
	}

	public void setfIssuerId(String fIssuerId) {
		this.fIssuerId = fIssuerId;
	}

	public String getfIssuerCatergory() {
		return fIssuerCatergory;
	}

	public void setfIssuerCatergory(String fIssuerCatergory) {
		this.fIssuerCatergory = fIssuerCatergory;
	}

	public String getfIssuerAddress() {
		return fIssuerAddress;
	}

	public void setfIssuerAddress(String fIssuerAddress) {
		this.fIssuerAddress = fIssuerAddress;
	}

	public String getfIssuerCity() {
		return fIssuerCity;
	}

	public void setfIssuerCity(String fIssuerCity) {
		this.fIssuerCity = fIssuerCity;
	}

	public String getfIssuerCountry() {
		return fIssuerCountry;
	}

	public void setfIssuerCountry(String fIssuerCountry) {
		this.fIssuerCountry = fIssuerCountry;
	}

	public String getfFromBank() {
		return fFromBank;
	}

	public void setfFromBank(String fFromBank) {
		this.fFromBank = fFromBank;
	}

	public String getfToBank() {
		return fToBank;
	}

	public void setfToBank(String fToBank) {
		this.fToBank = fToBank;
	}

	public String getfBenName() {
		return fBenName;
	}

	public void setfBenName(String fBenName) {
		this.fBenName = fBenName;
	}

	public String getfBenId() {
		return fBenId;
	}

	public void setfBenId(String fBenId) {
		this.fBenId = fBenId;
	}

	public String getfBenCategory() {
		return fBenCategory;
	}

	public void setfBenCategory(String fBenCategory) {
		this.fBenCategory = fBenCategory;
	}

	public String getfBenAddress() {
		return fBenAddress;
	}

	public void setfBenAddress(String fBenAddress) {
		this.fBenAddress = fBenAddress;
	}

	public String getfBenCity() {
		return fBenCity;
	}

	public void setfBenCity(String fBenCity) {
		this.fBenCity = fBenCity;
	}

	public String getfBenCountry() {
		return fBenCountry;
	}

	public void setfBenCountry(String fBenCountry) {
		this.fBenCountry = fBenCountry;
	}

	@Override
	public String toString() {
		return formatType + "|" + fBranch + "|" + fTrxRef + "|" + fCurrency
				+ "|" + fAmount + "|" + fIssuerName + "|" + fIssuerId + "|"
				+ fIssuerCatergory + "|" + fIssuerAddress + "|" + fIssuerCity
				+ "|" + fIssuerCountry + "|" + fFromBank + "|" + fToBank + "|"
				+ fBenName + "|" + fBenId + "|" + fBenCategory + "|"
				+ fBenAddress + "|" + fBenCity + "|" + fBenCountry;
	}

}
