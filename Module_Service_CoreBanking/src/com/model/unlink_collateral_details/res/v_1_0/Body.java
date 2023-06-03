package com.model.unlink_collateral_details.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.error.res.v_1_0.Error;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
public class Body {

	@XmlElement(name = "UnlinkCollateralDetailsResponseBody")
	protected UnlinkCollateralDetailsResponseBody unlinkCollateralDetailsResponseBody;
	
	@XmlElement(name = "Error")
	protected Error error;

	public Body() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Body(
			UnlinkCollateralDetailsResponseBody unlinkCollateralDetailsResponseBody,
			Error error) {
		super();
		this.unlinkCollateralDetailsResponseBody = unlinkCollateralDetailsResponseBody;
		this.error = error;
	}

	public UnlinkCollateralDetailsResponseBody getUnlinkCollateralDetailsResponseBody() {
		return unlinkCollateralDetailsResponseBody;
	}

	public void setUnlinkCollateralDetailsResponseBody(
			UnlinkCollateralDetailsResponseBody unlinkCollateralDetailsResponseBody) {
		this.unlinkCollateralDetailsResponseBody = unlinkCollateralDetailsResponseBody;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
