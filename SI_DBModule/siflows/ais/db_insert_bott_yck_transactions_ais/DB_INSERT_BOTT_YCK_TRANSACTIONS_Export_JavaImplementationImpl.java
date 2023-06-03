package siflows.ais.db_insert_bott_yck_transactions_ais;

import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.db.Pre_GR_YCK_InsertTransaction;

public class DB_INSERT_BOTT_YCK_TRANSACTIONS_Export_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public DB_INSERT_BOTT_YCK_TRANSACTIONS_Export_JavaImplementationImpl() {
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
	 * named "DB_INSERT_BOTT_YCK_TRANSACTIONS_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(Boolean insertProcess, String iData, String siVersion,
			String processInfo, String processDataSource, String logDataSource) {
		//TODO Needs to be implemented.
		return new Pre_GR_YCK_InsertTransaction(insertProcess, iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
	}

}