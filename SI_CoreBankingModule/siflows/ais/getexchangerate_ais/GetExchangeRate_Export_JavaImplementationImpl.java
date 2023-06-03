package siflows.ais.getexchangerate_ais;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.core.Pre_GetExchangeRate;

public class GetExchangeRate_Export_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public GetExchangeRate_Export_JavaImplementationImpl() {
		super();
	}

	/**
	 * Return a reference to the component service instance for this implementation
	 * class.  This method should be used when passing this service to a partner reference
	 * or if you want to invoke this component service asynchronously.    
	 *
	 * @generated (com.ibm.wbit.java)
	 */
	@SuppressWarnings("unused")
	private Object getMyService() {
		return (Object) ServiceManager.INSTANCE.locateService("self");
	}

	/**
	 * Method generated to support implementation of operation "invoke" defined for WSDL port type 
	 * named "GetExchangeRate_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(String procedure, Boolean isMergeData, String iData,
			String siVersion, String processInfo, String processDataSource,
			String logDataSource, Boolean isFlagOn) {
		//TODO Needs to be implemented.
		if(isFlagOn){
			return new Pre_GetExchangeRate(iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
		} else {
			return new Gson().fromJson("{\"data\":{\"exchangeRateList\":{\"exchangeRate\":[{\"ccycd\":\"AUD\",\"exchangeDate\":\"20230202\",\"buyCash\":16458,\"sellCash\":16894,\"buyTransfer\":16507,\"sellTransfer\":16894,\"baseRate\":\"16507.0\"},{\"ccycd\":\"CAD\",\"exchangeDate\":\"20230202\",\"buyCash\":17399,\"sellCash\":17843,\"buyTransfer\":17451,\"sellTransfer\":17843,\"baseRate\":\"17451.0\"},{\"ccycd\":\"CHF\",\"exchangeDate\":\"20230202\",\"buyCash\":25436,\"sellCash\":26085,\"buyTransfer\":25512,\"sellTransfer\":26085,\"baseRate\":\"25512.0\"},{\"ccycd\":\"CNY\",\"exchangeDate\":\"20230202\",\"buyCash\":0,\"sellCash\":3542,\"buyTransfer\":3420,\"sellTransfer\":3542,\"baseRate\":\"3420.0\"},{\"ccycd\":\"EUR\",\"exchangeDate\":\"20230202\",\"buyCash\":25433,\"sellCash\":26082,\"buyTransfer\":25509,\"sellTransfer\":26082,\"baseRate\":\"25509.0\"},{\"ccycd\":\"GBP\",\"exchangeDate\":\"20230202\",\"buyCash\":28519,\"sellCash\":29247,\"buyTransfer\":28605,\"sellTransfer\":29247,\"baseRate\":\"28605.0\"},{\"ccycd\":\"GD1\",\"exchangeDate\":\"20230202\",\"buyCash\":0,\"sellCash\":6780000,\"buyTransfer\":6700000,\"sellTransfer\":6780000,\"baseRate\":\"6700000.0\"},{\"ccycd\":\"HKD\",\"exchangeDate\":\"20230202\",\"buyCash\":2500,\"sellCash\":3024,\"buyTransfer\":2958,\"sellTransfer\":3024,\"baseRate\":\"2958.0\"},{\"ccycd\":\"JPY\",\"exchangeDate\":\"20230202\",\"buyCash\":179.09,\"sellCash\":183.66,\"buyTransfer\":179.63,\"sellTransfer\":183.66,\"baseRate\":\"179.63\"},{\"ccycd\":\"NZD\",\"exchangeDate\":\"20230202\",\"buyCash\":15022,\"sellCash\":15451,\"buyTransfer\":15097,\"sellTransfer\":15451,\"baseRate\":\"15097.0\"},{\"ccycd\":\"SGD\",\"exchangeDate\":\"20230202\",\"buyCash\":17697,\"sellCash\":18148,\"buyTransfer\":17750,\"sellTransfer\":18148,\"baseRate\":\"17750.0\"},{\"ccycd\":\"THB\",\"exchangeDate\":\"20230202\",\"buyCash\":689,\"sellCash\":734,\"buyTransfer\":706,\"sellTransfer\":734,\"baseRate\":\"706.0\"},{\"ccycd\":\"USD\",\"exchangeDate\":\"20230202\",\"buyCash\":23300,\"sellCash\":23580,\"buyTransfer\":23320,\"sellTransfer\":23580,\"baseRate\":\"23320.0\"}]},\"errorCode\":\"0\",\"errorDes\":\"Success\"},\"errorInfo\":{\"errorCode\":\"00\",\"message\":\"Success [LOG_ID:32790]\"}}",JsonObject.class).toString();
		}
	}

}