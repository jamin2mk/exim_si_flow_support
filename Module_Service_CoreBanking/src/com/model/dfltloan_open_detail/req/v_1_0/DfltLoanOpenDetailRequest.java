package com.model.dfltloan_open_detail.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DfltLoanOpenDetail", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class DfltLoanOpenDetailRequest {

	protected String cifId;
	protected String solId;
	protected String schmCode;
	protected String glSubHeadCode;
	protected String crncyCode;

	public DfltLoanOpenDetailRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DfltLoanOpenDetailRequest(String cifId, String solId,
			String schmCode, String glSubHeadCode, String crncyCode) {
		super();
		this.cifId = cifId;
		this.solId = solId;
		this.schmCode = schmCode;
		this.glSubHeadCode = glSubHeadCode;
		this.crncyCode = crncyCode;
	}

	public String getCifId() {
		return cifId;
	}

	public void setCifId(String cifId) {
		this.cifId = cifId;
	}

	public String getSolId() {
		return solId;
	}

	public void setSolId(String solId) {
		this.solId = solId;
	}

	public String getSchmCode() {
		return schmCode;
	}

	public void setSchmCode(String schmCode) {
		this.schmCode = schmCode;
	}

	public String getGlSubHeadCode() {
		return glSubHeadCode;
	}

	public void setGlSubHeadCode(String glSubHeadCode) {
		this.glSubHeadCode = glSubHeadCode;
	}

	public String getCrncyCode() {
		return crncyCode;
	}

	public void setCrncyCode(String crncyCode) {
		this.crncyCode = crncyCode;
	}

}
