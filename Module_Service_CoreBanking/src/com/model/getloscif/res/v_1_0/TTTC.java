package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tttc", namespace = "http://www.finacle.com/fixml", propOrder = {
		"tnLuong", "tnChoThue", "tnKinhDoanh", "tnKhac", "chiPhiSinhHoat",
		"chiTraTTThe", "chiPhiKhac" })
public class TTTC {

	@XmlElement(name = "TNLuong", namespace = "http://www.finacle.com/fixml")
	protected String tnLuong;
	@XmlElement(name = "TNChoThue", namespace = "http://www.finacle.com/fixml")
	protected String tnChoThue;
	@XmlElement(name = "TNKinhDoanh", namespace = "http://www.finacle.com/fixml")
	protected String tnKinhDoanh;
	@XmlElement(name = "TNKhac", namespace = "http://www.finacle.com/fixml")
	protected String tnKhac;
	@XmlElement(name = "chiPhiSinhHoat", namespace = "http://www.finacle.com/fixml")
	protected String chiPhiSinhHoat;
	@XmlElement(name = "chiTraTTThe", namespace = "http://www.finacle.com/fixml")
	protected String chiTraTTThe;
	@XmlElement(name = "chiPhiKhac", namespace = "http://www.finacle.com/fixml")
	protected String chiPhiKhac;

	public TTTC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TTTC(String tnLuong, String tnChoThue, String tnKinhDoanh,
			String tnKhac, String chiPhiSinhHoat, String chiTraTTThe,
			String chiPhiKhac) {
		super();
		this.tnLuong = tnLuong;
		this.tnChoThue = tnChoThue;
		this.tnKinhDoanh = tnKinhDoanh;
		this.tnKhac = tnKhac;
		this.chiPhiSinhHoat = chiPhiSinhHoat;
		this.chiTraTTThe = chiTraTTThe;
		this.chiPhiKhac = chiPhiKhac;
	}

	public String getTnLuong() {
		return tnLuong;
	}

	public void setTnLuong(String tnLuong) {
		this.tnLuong = tnLuong;
	}

	public String getTnChoThue() {
		return tnChoThue;
	}

	public void setTnChoThue(String tnChoThue) {
		this.tnChoThue = tnChoThue;
	}

	public String getTnKinhDoanh() {
		return tnKinhDoanh;
	}

	public void setTnKinhDoanh(String tnKinhDoanh) {
		this.tnKinhDoanh = tnKinhDoanh;
	}

	public String getTnKhac() {
		return tnKhac;
	}

	public void setTnKhac(String tnKhac) {
		this.tnKhac = tnKhac;
	}

	public String getChiPhiSinhHoat() {
		return chiPhiSinhHoat;
	}

	public void setChiPhiSinhHoat(String chiPhiSinhHoat) {
		this.chiPhiSinhHoat = chiPhiSinhHoat;
	}

	public String getChiTraTTThe() {
		return chiTraTTThe;
	}

	public void setChiTraTTThe(String chiTraTTThe) {
		this.chiTraTTThe = chiTraTTThe;
	}

	public String getChiPhiKhac() {
		return chiPhiKhac;
	}

	public void setChiPhiKhac(String chiPhiKhac) {
		this.chiPhiKhac = chiPhiKhac;
	}

}
