package siflows.ais.db_upsert_bott_gn_process_ais_x0020_2;

import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.db.Pre_DB_INSERT_BOTT_GN_TRANSACTIONS;

public class DB_INSERT_BOTT_GN_TRANSACTIONS_Export_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public DB_INSERT_BOTT_GN_TRANSACTIONS_Export_JavaImplementationImpl() {
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
	 * named "DB_UPSERT_BOTT_GN_PROCESS_AIS2".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(Boolean isMergeData, String iData, String siVersion,
			String processInfo, String processDataSource, String logDataSource) {
		//TODO Needs to be implemented.
		return new Pre_DB_INSERT_BOTT_GN_TRANSACTIONS(isMergeData, iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
	}

}