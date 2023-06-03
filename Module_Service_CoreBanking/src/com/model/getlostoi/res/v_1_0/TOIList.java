package com.model.getlostoi.res.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOILIST", propOrder = {"toiListInfor"})
public class TOIList {
	
	@XmlElement(name = "TOIListInfor")
	protected List<TOIListInfor> toiListInfor;

}
