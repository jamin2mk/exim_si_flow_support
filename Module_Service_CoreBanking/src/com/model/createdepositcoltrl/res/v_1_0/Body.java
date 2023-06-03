package com.model.createdepositcoltrl.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.error.res.v_1_0.Error;

@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

	@XmlElement(name = "DepColtrlAddResponse")
	protected DepColtrlAddResponse depColtrlAddResponse;
	@XmlElement(name = "Error")
	protected Error error;

	public Body() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Body(DepColtrlAddResponse depColtrlAddResponse, Error error) {
		super();
		this.depColtrlAddResponse = depColtrlAddResponse;
		this.error = error;
	}

	public DepColtrlAddResponse getDepColtrlAddResponse() {
		return depColtrlAddResponse;
	}

	public void setDepColtrlAddResponse(
			DepColtrlAddResponse depColtrlAddResponse) {
		this.depColtrlAddResponse = depColtrlAddResponse;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
