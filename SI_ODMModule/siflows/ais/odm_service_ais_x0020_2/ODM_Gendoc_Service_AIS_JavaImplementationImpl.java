package siflows.ais.odm_service_ais_x0020_2;

import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.odm.v1_0.Pre_ODM_Gendoc_Service;

public class ODM_Gendoc_Service_AIS_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public ODM_Gendoc_Service_AIS_JavaImplementationImpl() {
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
	 * named "ODM_Service_AIS2".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(String operation, Boolean isMergeData, String iData,
			String siVersion, String processInfo, String processDataSource,
			String logDataSource) {
		//TODO Needs to be implemented.
		return new Pre_ODM_Gendoc_Service(operation, isMergeData, iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
	}

}