package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.Expose;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "document", namespace = "http://www.finacle.com/fixml", propOrder = {
		"docCode", "docId", "docNgayCap", "docQuocGia", "docNoiCap",
		"docPrefer" })
public class Document {

	@Expose
	@XmlElement(name = "docCode", namespace = "http://www.finacle.com/fixml")
	protected String docCode;
	@Expose
	@XmlElement(name = "docId", namespace = "http://www.finacle.com/fixml")
	protected String docId;
	@Expose
	@XmlElement(name = "docNgayCap", namespace = "http://www.finacle.com/fixml")
	protected String docNgayCap;
	@Expose
	@XmlElement(name = "docQuocGia", namespace = "http://www.finacle.com/fixml")
	protected String docQuocGia;
	@Expose
	@XmlElement(name = "docNoiCap", namespace = "http://www.finacle.com/fixml")
	protected String docNoiCap;
	@Expose
	@XmlElement(name = "docPrefer", namespace = "http://www.finacle.com/fixml")
	protected String docPrefer;

	public Document(String docCode, String docId, String docNgayCap,
			String docQuocGia, String docNoiCap, String docPrefer) {
		super();
		this.docCode = docCode;
		this.docId = docId;
		this.docNgayCap = docNgayCap;
		this.docQuocGia = docQuocGia;
		this.docNoiCap = docNoiCap;
		this.docPrefer = docPrefer;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocNgayCap() {
		return docNgayCap;
	}

	public void setDocNgayCap(String docNgayCap) {
		this.docNgayCap = docNgayCap;
	}

	public String getDocQuocGia() {
		return docQuocGia;
	}

	public void setDocQuocGia(String docQuocGia) {
		this.docQuocGia = docQuocGia;
	}

	public String getDocNoiCap() {
		return docNoiCap;
	}

	public void setDocNoiCap(String docNoiCap) {
		this.docNoiCap = docNoiCap;
	}

	public String getDocPrefer() {
		return docPrefer;
	}

	public void setDocPrefer(String docPrefer) {
		this.docPrefer = docPrefer;
	}

}
