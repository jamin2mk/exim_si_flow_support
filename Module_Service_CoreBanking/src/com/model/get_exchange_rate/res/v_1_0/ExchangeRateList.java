package com.model.get_exchange_rate.res.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name ="EXCHANGERATELIST", propOrder = {"exchangeRate"})
public class ExchangeRateList {
	
	@XmlElement(name = "EXCHANGERATE")
	protected List<ExchangeRate> exchangeRate;
	

}
