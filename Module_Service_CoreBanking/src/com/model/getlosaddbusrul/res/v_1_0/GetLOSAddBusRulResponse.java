package com.model.getlosaddbusrul.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetLOSAddBusRulResponse", namespace = "http://www.alsb.com/")
public class GetLOSAddBusRulResponse {

	@XmlElement(name = "FileStatus", namespace = "http://www.finacle.com/fixml")
	protected FileStatus fileStatus;

	public GetLOSAddBusRulResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetLOSAddBusRulResponse(FileStatus fileStatus) {
		super();
		this.fileStatus = fileStatus;
	}

	public FileStatus getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(FileStatus fileStatus) {
		this.fileStatus = fileStatus;
	}

}
