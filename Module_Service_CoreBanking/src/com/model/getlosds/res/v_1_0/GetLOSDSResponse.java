package com.model.getlosds.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetLOSDSResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLOSDSResponse {

	@XmlElement(name = "FileStatus", namespace = "http://www.finacle.com/fixml")
	protected FileStatus fileStatus;

	public GetLOSDSResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetLOSDSResponse(FileStatus fileStatus) {
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
