package siflows.ais.sendmail_ais;

import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.communication.Pre_SendMail;

public class SendMail_Export_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public SendMail_Export_JavaImplementationImpl() {
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
	 * named "SendMail_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(String mailRequest, String siVersion, String processInfo, String processDataSource, String logDataSource) {
		//TODO Needs to be implemented.
//		return "{\"data\":null,\"errorInfo\":{\"errorCode\":\"00\",\"message\":\"Success\"}}";
		return new Pre_SendMail(mailRequest, siVersion, processInfo, processDataSource, logDataSource).invoke();
	}

}