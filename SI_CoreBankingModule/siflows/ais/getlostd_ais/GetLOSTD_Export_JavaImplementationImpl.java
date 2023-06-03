package siflows.ais.getlostd_ais;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.core.Pre_GetLOSTD;

public class GetLOSTD_Export_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public GetLOSTD_Export_JavaImplementationImpl() {
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
	 * named "GetLOSTD_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public String invoke(String procedure, Boolean isMergeData, String iData,
			String siVersion, String processInfo, String processDataSource,
			String logDataSource, Boolean isFlagOn) {
		//TODO Needs to be implemented.
		if(isFlagOn){
			return new Pre_GetLOSTD(iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
		}else {
			return new Gson().fromJson("{\"data\":{\"listLAVInfor\":[{\"soHopDong\":\"2235LAV190017614\",\"ngayMoHD\":\"07/03/2019\",\"ptCapTd\":\"TUNGLAN\",\"loaiCapTd\":\"DAIHAN\",\"hanMuc\":\"200000000\",\"hmKhadung\":\"135540000\",\"duNo\":\"64460000\",\"lsbq\":\"8.5\",\"htCapTd\":\"04LL\",\"tsdb\":\"\",\"capPheDuyet\":\"GIÁM ??C ?VKD\"},{\"soHopDong\":\"2235LAV190114650\",\"ngayMoHD\":\"29/10/2019\",\"ptCapTd\":\"HANMUC\",\"loaiCapTd\":\"NGANHAN\",\"hanMuc\":\"30000000\",\"hmKhadung\":\"29500000\",\"duNo\":\"500000\",\"lsbq\":\"0\",\"htCapTd\":\"09CC\",\"tsdb\":\"\",\"capPheDuyet\":\"GIÁM ??C ?VKD\"}]},\"errorInfo\":{\"errorCode\":\"00\",\"message\":\"Success [LOG_ID:32916]\"}}",JsonObject.class).toString();
		}
	}

}