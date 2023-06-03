package com.model.unlink_collateral_details.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unlinkCollateralDetailsResponse", propOrder = {""})
public class UnlinkCollateralDetailsResponseBody {
	
	@XmlElement(name ="CIUnLinkCollateralDtlOutput")
	protected CIUnLinkCollateralDtlOutput ciUnLinkCollateralDtlOutput;
	
	@XmlElement(name = "unlinkCollateralDetails_CustomData")
	protected String unlinkCollateralDetailsCustomData;

}
