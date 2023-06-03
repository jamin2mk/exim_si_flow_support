package com.model.getlostd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "GetLOSTD", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSTDRequest {

	@SerializedName("Cif")
	@XmlElement(name = "Cif")
	protected String cif;

	@SerializedName("DocId")
	@XmlElement(name = "DocId")
	protected String docId;

	public GetLOSTDRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetLOSTDRequest(String cif, String docId) {
		super();
		this.cif = cif;
		this.docId = docId;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

}
