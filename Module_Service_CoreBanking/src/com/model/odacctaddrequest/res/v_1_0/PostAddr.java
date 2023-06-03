package com.model.odacctaddrequest.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlType(name = "PostAddr", namespace = "http://www.finacle.com/fixml", propOrder = {
		"addr1","addr2","addr3","city","stateProv","postalCode","country","addrType"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class PostAddr {

	@SerializedName("Addr1")
	@XmlElement(name = "Addr1", namespace = "http://www.finacle.com/fixml")
	protected String addr1;
	@SerializedName("Addr2")
	@XmlElement(name = "Addr2", namespace = "http://www.finacle.com/fixml")
	protected String addr2;
	@SerializedName("Addr3")
	@XmlElement(name = "Addr3", namespace = "http://www.finacle.com/fixml")
	protected String addr3;
	@SerializedName("City")
	@XmlElement(name = "City", namespace = "http://www.finacle.com/fixml")
	protected String city;
	@SerializedName("StateProv")
	@XmlElement(name = "StateProv", namespace = "http://www.finacle.com/fixml")
	protected String stateProv;
	@SerializedName("PostalCode")
	@XmlElement(name = "PostalCode", namespace = "http://www.finacle.com/fixml")
	protected String postalCode;
	@SerializedName("Country")
	@XmlElement(name = "Country", namespace = "http://www.finacle.com/fixml")
	protected String country;
	@SerializedName("AddrType")
	@XmlElement(name = "AddrType", namespace = "http://www.finacle.com/fixml")
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
