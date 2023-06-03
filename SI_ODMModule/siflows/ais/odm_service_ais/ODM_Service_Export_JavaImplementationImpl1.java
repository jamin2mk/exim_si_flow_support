package siflows.ais.odm_service_ais;

import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.odm.v1_0.Pre_ODM_Service;

public class ODM_Service_Export_JavaImplementationImpl1 {
	/**
	 * Default constructor.
	 */
	public ODM_Service_Export_JavaImplementationImpl1() {
		super();
	}

	/**
	 * Return a reference to the component service instance for this
	 * implementation class. This method should be used when passing this
	 * service to a partner reference or if you want to invoke this component
	 * service asynchronously.
	 *
	 * @generated (com.ibm.wbit.java)
	 */
	@SuppressWarnings("unused")
	private Object getMyService() {
		return (Object) ServiceManager.INSTANCE.locateService("self");
	}

	/**
	 * Method generated to support implementation of operation "invoke" defined
	 * for WSDL port type named "ODM_Service_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information on the type of
	 * input, output and fault(s).
	 */
	public String invoke(String operation, Boolean isMergeData, String iData, String siVersion, String processInfo, String processDataSource, String logDataSource) {
		return new Pre_ODM_Service(operation, isMergeData, iData, siVersion, processInfo, processDataSource, logDataSource).invoke();

	}

}