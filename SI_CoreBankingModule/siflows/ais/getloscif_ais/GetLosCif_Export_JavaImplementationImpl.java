package siflows.ais.getloscif_ais;


import com.ibm.websphere.sca.ServiceManager;
import com.pre.impl.core.Pre_GR_GetLosCif;

public class GetLosCif_Export_JavaImplementationImpl {
	/**
	 * Default constructor.
	 */
	public GetLosCif_Export_JavaImplementationImpl() {
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
	 * for WSDL port type named "GetLosCif_AIS".
	 * 
	 * Please refer to the WSDL Definition for more information on the type of
	 * input, output and fault(s).
	 */
	public String invoke(String procedure, Boolean isMergeData, String iData, String siVersion, String processInfo, String processDataSource, String logDataSource, Boolean isFlagOn) {
		if (isFlagOn) {

			return new Pre_GR_GetLosCif(iData, siVersion, processInfo, processDataSource, logDataSource).invoke();
		} else {
			String result = "{\"data\":{\"searchUser\":[{\"CUSTOMER_CODE\":\"CF0001111\",\"CUSTOMER_INDENTITY\":\"052188017684\",\"CUSTOMER_TYPE\":\"CN\",\"CIF\":\"106058979\",\"FULL_NAME\":\"LE PHAN VI AI\",\"PHONE\":\"0933094247\",\"COUNT_HS\":\"\",\"CORE_FULL_NAME\":\"LE PHAN VI AI\"}],\"losData\":{\"cif\":\"106058979\",\"tenKh\":\"LE PHAN VI AI\",\"gioiTinh\":\"GT_NU\",\"ngaySinh\":\"13/12/1988\",\"noiSinh\":\"\",\"quocTich\":\"VNM\",\"danToc\":\"DT01\",\"ttHonNhan\":\"LYTHAN\",\"khDen\":null,\"cuTru\":\"1\",\"kCapTinDung\":\"DTCTD01\",\"hanChe\":\"DTHC01\",\"vip\":\"2\",\"loaiKH\":\"CN\",\"dsaid\":\"TEL200009812\",\"acctmngrid\":\"MAI.NT03\",\"maNVEIB\":\"100401934\",\"salutation\":\"MS.\",\"document\":[{\"docCode\":\"THECCCD\",\"docId\":\"052188017684\",\"docNgayCap\":\"20/12/2021\",\"docQuocGia\":\"VNM\",\"docNoiCap\":\"NCID343\",\"docPrefer\":\"Y\"}],\"phone\":[{\"phoneType\":\"CELLPH\",\"phonePrefer\":\"Y\",\"phoneNo\":\"0933094247\"},{\"phoneType\":\"HOMEPH1\",\"phonePrefer\":\"N\",\"phoneNo\":\"038397756\"}],\"address\":[{\"quocGiaDiaChi\":\"VNM\",\"thanhPhoDiaChi\":\"79\",\"diaChi\":\"93/16 HO THI KI P1 Q10 HCM..\",\"hieuLucDiaChi\":\"05/07/2022\",\"loaiDiaChi\":\"DCLIENHE\",\"addrPreferType\":\"N\"},{\"quocGiaDiaChi\":\"VNM\",\"thanhPhoDiaChi\":\"79\",\"diaChi\":\"93/16 HO THI KI P1 Q10 HCM..\",\"hieuLucDiaChi\":\"05/07/2022\",\"loaiDiaChi\":\"HKTHUONGTRU\",\"addrPreferType\":\"Y\"}],\"khlq\":null,\"email\":[{\"email\":\"ai.lpv@eximbank.com.vn\"}],\"tttc\":{\"tnLuong\":\"0\",\"tnChoThue\":\"0\",\"tnKinhDoanh\":\"0\",\"tnKhac\":\"0\",\"chiPhiSinhHoat\":\"0\",\"chiTraTTThe\":\"\",\"chiPhiKhac\":\"\"},\"errcode\":\"0\",\"errDesc\":\"Thành công\",\"userInfo\":{\"userCode\":\"U0000142\"}}},\"errorInfo\":{\"errorCode\":\"00\",\"message\":\"Success [LOG_ID:32823]\"}}";
			return result;
		}
	}

}