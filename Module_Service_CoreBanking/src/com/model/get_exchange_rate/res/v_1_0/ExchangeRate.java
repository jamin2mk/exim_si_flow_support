package com.model.get_exchange_rate.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "EXCHANGERATE", propOrder = {"ccycd","exchangeDate","buyCash","sellCash"
		,"buyTransfer","sellTransfer","baseRate"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRate {
	
	@XmlElement(name = "CCYCD")
	protected String ccycd;
	@XmlElement(name = "ExchangeDate")
	protected String exchangeDate;
	@XmlElement(name = "BUYCASH")
	protected Double buyCash;
	@XmlElement(name = "SELLCASH")
	protected Double sellCash;
	@XmlElement(name = "BUYTRANSFER")
	protected Double buyTransfer;
	@XmlElement(name = "SELLTRANSFER")
	protected Double sellTransfer;
	@XmlElement(name = "BASERATE")
	protected String baseRate;
	
}
