package com.model.collateralcreatecustom.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "CollateralCreateCustom", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollateralCreateCustomRequest {

	@XmlElement(name = "coltrlId")
	@SerializedName("coltrlId")
	protected String coltrlId;
	@XmlElement(name = "issDat")
	@SerializedName("issDat")
	protected String issDat;
	@XmlElement(name = "issTerm")
	@SerializedName("issTerm")
	protected String issTerm;
	@XmlElement(name = "brnch")
	@SerializedName("brnch")
	protected String brnch;
	@XmlElement(name = "secuObliga")
	@SerializedName("secuObliga")
	protected String secuObliga;
	@XmlElement(name = "valDate")
	@SerializedName("valDate")
	protected String valDate;

	public CollateralCreateCustomRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollateralCreateCustomRequest(String coltrlId, String issDat,
			String issTerm, String brnch, String secuObliga, String valDate) {
		super();
		this.coltrlId = coltrlId;
		this.issDat = issDat;
		this.issTerm = issTerm;
		this.brnch = brnch;
		this.secuObliga = secuObliga;
		this.valDate = valDate;
	}

	public String getColtrlId() {
		return coltrlId;
	}

	public void setColtrlId(String coltrlId) {
		this.coltrlId = coltrlId;
	}

	public String getIssDat() {
		return issDat;
	}

	public void setIssDat(String issDat) {
		this.issDat = issDat;
	}

	public String getIssTerm() {
		return issTerm;
	}

	public void setIssTerm(String issTerm) {
		this.issTerm = issTerm;
	}

	public String getBrnch() {
		return brnch;
	}

	public void setBrnch(String brnch) {
		this.brnch = brnch;
	}

	public String getSecuObliga() {
		return secuObliga;
	}

	public void setSecuObliga(String secuObliga) {
		this.secuObliga = secuObliga;
	}

	public String getValDate() {
		return valDate;
	}

	public void setValDate(String valDate) {
		this.valDate = valDate;
	}

}
