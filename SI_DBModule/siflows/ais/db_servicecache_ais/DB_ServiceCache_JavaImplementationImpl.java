package siflows.ais.db_servicecache_ais;

import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.db.Pre_DB_ServiceCache;

public class DB_ServiceCache_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public DB_ServiceCache_JavaImplementationImpl() {
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
	 * named "DB_ServiceCache_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(String procedure, Boolean isMergeData, String iData,
			String siVersion, String processInfo, String processDataSource,
			String logDataSource) {
		//TODO Needs to be implemented.
		return new Pre_DB_ServiceCache(procedure, isMergeData, iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
	}

}