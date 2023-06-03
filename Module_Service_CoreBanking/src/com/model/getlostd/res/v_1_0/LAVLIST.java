package com.model.getlostd.res.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LAVLIST", namespace = "http://www.finacle.com/fixml", propOrder ={"listLAVInfor"})
public class LAVLIST {
	
	@XmlElement(name = "ListLAVInfor", namespace = "http://www.finacle.com/fixml")
	protected List<ListLAVInfor> listLAVInfor;

}
