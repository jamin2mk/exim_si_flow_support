package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostAddr", propOrder = {})
public class PostAddr {

	@XmlElement(name = "Addr1")
	@SerializedName("Addr1")
	protected String addr1;
	@XmlElement(name = "Addr2")
	@SerializedName("Addr2")
	protected String addr2;
	@XmlElement(name = "Addr3")
	@SerializedName("Addr3")
	protected String addr3;
	@XmlElement(name = "City")
	@SerializedName("City")
	protected String city;
	@XmlElement(name = "StateProv")
	@SerializedName("StateProv")
	protected String stateProv;
	@XmlElement(name = "PostalCode")
	@SerializedName("PostalCode")
	protected String postalCode;
	@XmlElement(name = "Country")
	@SerializedName("Country")
	protected String country;
	@XmlElement(name = "AddrType")
	@SerializedName("AddrType")
	protected String addrType;

	public PostAddr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostAddr(String addr1, String addr2, String addr3, String city,
			String stateProv, String postalCode, String country, String addrType) {
		super();
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.city = city;
		this.stateProv = stateProv;
		this.postalCode = postalCode;
		this.country = country;
		this.addrType = addrType;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProv() {
		return stateProv;
	}

	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

}
