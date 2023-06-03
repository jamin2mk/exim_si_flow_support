package com.service.impl.gendoc.v1_0;

import java.util.HashMap;
import java.util.Map;

import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.LGIssuesHandling;
import com.helper.SupportBMHelper;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM130 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM130(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
		genDocInvoker  = new GenDocInvoker(docName, genDocEnv, processDataSource, log);
		super.invoker = genDocInvoker;
		super.isMergeData = true;
		super.isManualMapping_Input =true;
		super.isManualInvoke =true;
		this.docName = docName;
		this. dependentRequired = dependentRequired;
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		BO.addProperty("GENDOC_CODE", docName);
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
//		JsonObject TT_BAO_CAO = BO.get("TT_BAO_CAO").getAsJsonObject();
//		JsonObject TT_DANHGIA_HDONG = TT_BAO_CAO.get("TT_DANHGIA_HDONG").getAsJsonObject();
		JsonObject TT_DEXUAT = BO.get("TT_DEXUAT").getAsJsonObject();
		
		SupportBMHelper SupBM = new SupportBMHelper(super.mapper, super.processDataSource);
//		int indexOfN = SupBM.findIndexOfN(BO, this.dependentRequired, "ID_BAOLANH");
//		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
//		super.mapper = new Auto_Mapping(indexOfN);
//		String dirParent = String.format("#.TT_BAO_CAO.TT_YC_BAOLANH[%s]", indexOfN);
		
		if(dependentRequired!=null || "".equals(dependentRequired)){
			if(BO.get("DEXUAT_STHAO_GN").getAsJsonObject().get("IS_BAOLANH").getAsInt()==1){
				LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_YCBL");
			} else if(BO.get("DEXUAT_STHAO_GN").getAsJsonObject().get("IS_LC").getAsInt()==1) {
				LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_YCLC");
			}
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		//=============MASTERDATA===================//
		
		map.put("NOICAPID", "NOI_CAP, CTD_DN_BUSINESSES_NUMBER_ISSUED_BY");
		BO = SupBM.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH" , BO);
		map.clear();
		map.put("ONGBA", "ONG_BA, CTD_DN_GRANDPARENTS");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH", BO);
		map.clear();
		map.put("ONGBA", "ONG_BA, CTD_CN_GRANDPARENTS");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH", BO);
		map.clear();
		map.put("NOICAPID", "NOI_CAP, BL_DN_ISSUED_BY");
		BO = SupBM.getMasterData(map,"#.TT_BAO_CAO.TT_YC_BAOLANH[0].TT_DINH_DANH[0]" , BO);
		map.clear();
//		map.put("LOAITIEN", "LOAI_TIEN, LC_CURRENCY");
//		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_YC_THU_TINDUNG[0]", BO);
//		map.clear();
		map.put("NOICAPID", "NOI_CAP, NDV_ISSUED_BY");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH.TT_DINHDANH[0]", BO);
		map.clear();
//		map.put("LOAITIEN", "LOAI_TIEN, GUARANTEE_CURRENCY");
//		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_YC_BAOLANH[0]", BO);
//		map.clear();
//		map.put("NGAYHIEULUC", "NGAY_HIEULUC, BL_DAY");
//		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_YC_BAOLANH[0]", BO);
//		map.clear();
//		map.put("NOICAPID", "NOI_CAP, CTD_DN_ISSUED_BY");
//		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH.TT_DINHDANH[0]", BO);
//		map.clear();
		map.put("NOICAPID", "NOI_CAP, CTD_CN_ISSUED_BY");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH.TT_DINHDANH[0]", BO);
		map.clear();
		map.put("NHOMNO", "NHOM_NO, CURRENT_DEBT_GROUP");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_HD", BO);
		map.clear();
		map.put("ONGBA", "ONG_BA, NDV_GRANDPARENTS");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH", BO);
		map.clear();
		map.put("LOAILC", "LOAI_THU_CTD, LC_LETTER_OF_CREDIT");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_YC_THU_TINDUNG[0]", BO);
		map.clear();
		map.put("LOAITHUBL", "LOAI_BLANH, TYPE_OF_LETTER_OF_GUARANTEE");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TT_YC_BAOLANH[0]", BO);
		map.clear();
		map.put("TCTD", "TEN_TOCHUC, GNBL_TCPH");
		BO = SupBM.getMasterData(map, "#.TT_BAO_CAO.TAISAN_BAODAM", BO);
		map.clear();

		BO = SupBM.getAddress(BO, "#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH.TT_DIACHI", "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
		BO = SupBM.getAddress(BO, "#.TT_BAO_CAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		//==================End MASTERDATA====================//
		
		//==================Check & UnCheck===================//
		JsonObject DEXUAT_STHAO_GN = BO.get("DEXUAT_STHAO_GN").getAsJsonObject();
		String IS_LC = (DEXUAT_STHAO_GN.get("IS_LC") != null && DEXUAT_STHAO_GN.get("IS_LC").getAsString() != null) ? (DEXUAT_STHAO_GN.get("IS_LC").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
		BO.addProperty("LETTER_OF_CREDIT", IS_LC);  
		String IS_BAOLANH = (DEXUAT_STHAO_GN.get("IS_BAOLANH") != null && DEXUAT_STHAO_GN.get("IS_BAOLANH").getAsString() != null) ? (DEXUAT_STHAO_GN.get("IS_BAOLANH").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
		BO.addProperty("GUARANTEE", IS_BAOLANH); 
		//check IS_MARRY
		String YC_HONPHOI_KY = (TT_DEXUAT.get("YC_HONPHOI_KY") != null && TT_DEXUAT.get("YC_HONPHOI_KY").getAsString() != null) ? (TT_DEXUAT.get("YC_HONPHOI_KY").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
		BO.addProperty("IS_MARRY", YC_HONPHOI_KY);
		
		BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_BAOLANH[0].CHUNGTU_BS", "ADDITIONAL_DOCUMENTS", BO);
		BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_BAOLANH[0].CHUNGTU_BS", "ADDITIONAL_DOCUMENTS", BO);
        BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_THU_TINDUNG[0].CTU_CAN_BS", "LC_ADDITIONAL_DOCUMENTS", BO);  
        BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_BAOLANH[0].NGAY_HH_CTHE", "BL_THE_EXACT_DAY", BO);
        BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_BAOLANH[0].CUTHE_NGAYHETHIEULUC", "SPECIFIC_EXPIRY_DATE_1", BO);
        BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_BAOLANH[0].SONGAY", "NO_DAYS_1", BO);
        BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_BAOLANH[0].CHUNGTU_DA_CUNGCAP", "BL_DOCUMENT_1", BO);
        BO = SupBM.toCheck("#.TT_BAO_CAO.TT_YC_THU_TINDUNG[0].CTU_CUNGCAP", "LC_DOCUMENT", BO);
        ///////////////end CHECK/////////////////////
        
//        String[] radio_DK_KHAC= {"OTHER_COMPLY","OTHER_NON_COMPLIANCE"};
//		BO = SupBM.toRadio("TT_BAO_CAO.TT_DANHGIA_HDONG.DK_KHAC",radio_DK_KHAC, BO, "CHECK");
        
        
        map.put("TSBD_NAME", "TEN_TSBD");
		map.put("VALUATION_VALUE", "GIATRI_DINHGIA");
		map.put("GUARANTEED_VALUE","GIATRI_TSBD");
		map.put("NOTARIZATION", "DK_BPBD");
		JsonArray TABLE_DBM_BPBD = SupBM.getTable(map, "#.TT_BAO_CAO.TT_HD.BIENPHAP_BAODAM", BO);
		BO.addProperty("TABLE_DBM_BPBD", TABLE_DBM_BPBD.toString());
		map.clear();
        
	    map.put("*TTK_TCPH", "TCTD ,TOCHUC_PHATHANH");
		map.put("TTK_STK", "SO_TAIKHOAN");
		map.put("TTK_NPH", "NGAY_PHATHANH");
		map.put("TTK_NDH", "NGAY_DAOHAN");
		map.put("TTK_CURRENCY", "LOAITIEN");
		map.put("TTK_VALUE", "GIATRI");
		map.put("TTK_GUARANTEE_AMOUNT","SOTIEN_BAODAM");
		map.put("TTK_COURT_STATUS", "TINHTRANG_PHONGTOA");
		JsonArray TABLE_DBM_TTK = SupBM.getTable(map, "#.TT_BAO_CAO.TAISAN_BAODAM.TT_TIENGUI", BO);
		BO.addProperty("TABLE_DBM_TTK", TABLE_DBM_TTK.toString());
		map.clear();
		
		map.put("VANG_ASSET_TYPE", "LOAITAISAN");
		map.put("VANG_SL", "SOLUONG");
		map.put("VANG_CUR","LOAITIEN");
		map.put("VANG_VA", "GIATRI");
		map.put("VANG_GUARANTEE_AMOUNT", "SOTIEN_BAODAM");
		map.put("VANG_TTK_COURT_STATUS", "TINHTRANG_PHONGTOA");
		JsonArray TABLE_DBM_VANG = SupBM.getTable(map, "#.TT_BAO_CAO.TAISAN_BAODAM.TT_VANG", BO);
		BO.addProperty("TABLE_DBM_VANG", TABLE_DBM_VANG.toString());
		map.clear();
        
        map.put("KHAC_TSBD_NAME", "TEN_TSBD");
		map.put("KHAC_VALUE", "GIATRI");
		map.put("KHAC_VALUEBD","GIATRI_BAODAM");
		map.put("KHAC_DKBPBD", "CONGCHUNG");
		JsonArray TABLE_DBM_KHAC = SupBM.getTable(map, "#.TT_BAO_CAO.TAISAN_BAODAM.TT_TAISAN_KHAC", BO);
		BO.addProperty("TABLE_DBM_KHAC", TABLE_DBM_KHAC.toString());
		map.clear();
        
	}


	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		super.invoked_output = super.data;
		
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check_invokeResult() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
		
	}	
	@Override
	public void businessMapping_Output() throws SIException {
		
		JsonArray inputData = super.data.getAsJsonObject().get(Gendoc.inpuData).getAsJsonArray();
		//convert InputData to JSON String
		for(int i=1; i <= 11; i++) {
			if(!inputData.get(0).getAsJsonObject().get(String.format("TTD_APPROPRIATE_ASSESSMENT_%d",i)).isJsonNull() && inputData.get(0).getAsJsonObject().get(String.format("TTD_APPROPRIATE_ASSESSMENT_%d",i)).getAsInt()==1) {
				inputData.get(0).getAsJsonObject().addProperty(String.format("TTD_APPROPRIATE_ASSESSMENT_%d",i), "CHECK");
			} else {
				inputData.get(0).getAsJsonObject().addProperty(String.format("TTD_APPROPRIATE_ASSESSMENT_%d",i), "UNCHECK");
			}
		}	
		
		for(int i=1; i <= 11; i++) {
			if(!inputData.get(0).getAsJsonObject().get(String.format("APPROPRIATE_GUARANTEE_%d",i)).isJsonNull() && inputData.get(0).getAsJsonObject().get(String.format("APPROPRIATE_GUARANTEE_%d",i)).getAsInt()==1) {
				inputData.get(0).getAsJsonObject().addProperty(String.format("APPROPRIATE_GUARANTEE_%d",i), "CHECK");
			} else {
				inputData.get(0).getAsJsonObject().addProperty(String.format("APPROPRIATE_GUARANTEE_%d",i), "UNCHECK");
			}
		}
		// STARTING LOADING CAP_PHEDUYET AUTH_ROLE TABLE
		
		
		LGIssuesHandling.RadioButtonExchange(inputData, "COMPLY", "NON_COMPLIANCE");
		LGIssuesHandling.RadioButtonExchange(inputData, "DBM_COMPLY", "DBM_NON_COMPLIANCE");
		LGIssuesHandling.RadioButtonExchange(inputData, "TSBD_FOLLOW", "TSBD_DO_NOT_OBEY");
		LGIssuesHandling.RadioButtonExchange(inputData, "OTHER_COMPLY", "OTHER_NON_COMPLIANCE");
		LGIssuesHandling.RadioButtonExchange(inputData, "REPUTABLE", "ARISING");
		LGIssuesHandling.RadioButtonExchange(inputData, "BL_COLLECTED_ALL", "BL_PERIODIC_1", "BL_OTHER_1");
		LGIssuesHandling.RadioButtonExchange(inputData, "LC_COLLECTED_ALL", "LC_PERIODIC_1", "LC_OTHER_1");

			//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
		
	}

}
