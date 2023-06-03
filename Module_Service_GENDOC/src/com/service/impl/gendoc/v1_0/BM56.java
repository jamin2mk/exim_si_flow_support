package com.service.impl.gendoc.v1_0;

import java.util.HashMap;
import java.util.Map;

import Handler.Auto_Mapping;

import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.LGIssuesHandling;
import com.helper.SupportBMHelper;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.helper.MoneyHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM56 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM56(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
		genDocInvoker  = new GenDocInvoker(docName, genDocEnv, processDataSource, log);
		super.invoker = genDocInvoker;
		super.isMergeData = true;
		super.isManualMapping_Input =true;
		super.isManualInvoke =true;
		this.docName = docName;
		this.dependentRequired = dependentRequired;
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
		Map<String, String> map = new HashMap<String, String>();
		
		SupportBMHelper SupBM = new SupportBMHelper(super.mapper, super.processDataSource);
		int indexOfN = SupBM.findIndexOfN(BO, this.dependentRequired, "ID_HD");
		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
		super.mapper = new Auto_Mapping(indexOfN);
		String dirParent = String.format("#.TT_SOANTHAO.TT_YC_HDTSBD[%s]", indexOfN);
		/////////////////MASTER DATA///////////////////////////	
		map.put("ONGBA", "ONG_BA, BANK_GRANDPARENTS");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_EXIM" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, BTC_DN_GRANDPARENTS");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, BTC_CN_GRANDPARENTS");
		BO = SupBM.getMasterData(map, dirParent+".TT_BEN_BD.KH_CN" , BO);
		map.clear();
		
		map.put("NOICAPID", "NOI_CAP, BTC_DN_BUSINESSES_NUMBER_ISSUED_BY");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, CTD_DN_GRANDPARENTS|CTD_CN_GRANDPARENTS");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH" , BO);
		map.clear();
		
		map.put("CHUCVU", "CHUC_VU, CTD_DN_POSITION_ENTERPRISE");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, NHP_GRANDPARENTS");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI" , BO);
		map.clear();
		
		map.put("NOICAPID", "NOI_CAP, BTC_CN_COUPLE_ISSUED_BY");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.TT_DINHDANH[0]" , BO);
		map.clear();
		
		map.put("LOAIID", "LOAI_ID, BTC_CN_ID_TYPE");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.TT_DINHDANH[0]" , BO);
		map.clear();
		
		map.put("LOAIID", "LOAI_ID, NHP_ID_TYPE");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI.TT_DINHDANH[0]" , BO);
		map.clear();
		
		map.put("NOICAPID", "NOI_CAP, NHP_ISSUED_BY");
		BO = SupBM.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI.TT_DINHDANH[0]" , BO);
		map.clear();
		////////////////END MASTERDATA//////////////////
		
		/////////////GET ADDRESS TT_LL//////////////////
		BO = SupBM.getAddress(BO, dirParent+".TT_BEN_BD.TT_LIENLAC", "BTC_CN_COUPLE_PER_ADDRESS", "BTC_CN_COUPLE_CONT_ADDRESS");
		BO = SupBM.getAddress(BO, dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI.TT_LIENLAC", "NHP_PER_ADDRESS", "NHP_CONT_ADDRESS");
		BO = SupBM.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH.TT_DIACHI", "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
		BO = SupBM.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		/////////////END ADDRESS TT_LL//////////////////
		
		/////////////GET CHECKBOX//////////////////
		BO = SupBM.toCheck(dirParent+".TT_BEN_BD.YC_NGUOIHONPHOI", "MARRIAGE_SIGNING", BO);
		BO = SupBM.toCheck(dirParent+".TT_BEN_BD.UYQUYEN_KY_HS", "BBD_KWNN", BO);
		BO = SupBM.toCheck("#.TT_DENGHI.TT_CACBEN_KI_HD.YC_HONPHOI", "IS_MARRY", BO);
		BO = SupBM.toCheck(dirParent+".TT_BEN_BD.DONG_SO_HUU", "IS_DSH", BO);
		/////////////END CHECKBOX//////////////////
		
		BO= contractDate(dirParent+".DS_HDTD_SO", BO, "HDBD_NO_CONTRACT_DATE");
		
		////////////Change Num to Let//////////////
		try {
			JsonElement js = get_value_byDir(BO,dirParent+".TONG_NV_BAODAM_TIEN");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_TOTAL_GUARANTEE_OBLIGATIONS_3", moneyText);
		} catch (Exception e) {
			BO.addProperty("BY_TOTAL_GUARANTEE_OBLIGATIONS_3", "");
		}
		
		try {
			JsonElement js = get_value_byDir(BO,dirParent+".DS_BDS[0].GIA_TRI");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_ASSET_VALUE", moneyText);
		} catch (Exception e) {
			BO.addProperty("BY_ASSET_VALUE", "");
		}
		////////////////////////////////////////////

		map.put("*DSH_GRANDPARENTS", "ONGBA, ONG_BA");
		map.put("DSH_FULLNAME_INDIVIDUAL", "TEN_KH_CODAU");
		map.put("DSH_BIRTHDAY", "NGAY_SINH");
		map.put("*DSH_ID_TYPE", "LOAIID, TT_DINHDANH[0].LOAI_ID");
	    map.put("DSH_IDNUMBER", "TT_DINHDANH[0].SO_ID");
	    map.put("DSH_DATE_RANGE", "TT_DINHDANH[0].NGAY_CAP");
	    map.put("DSH_ISSUED_BY", "TT_DINHDANH[0].NOI_CAP");
	    map.put("DSH_PER_ADDRESS", "TT_LIENLAC[0].DIA_CHI_CUTHE");
	    map.put("DSH_CONT_ADDRESS", "TT_LIENLAC[0].DIA_CHI_CUTHE");
	    map.put("DSH_PHONE_NUMBER", "SO_DT");
		JsonArray TABLE_DSH = SupBM.getTable(map, dirParent+".TT_BEN_BD.DS_DONGSOHUU", BO);
		BO.addProperty("TABLE_DSH", TABLE_DSH.toString());
		map.clear();
		
		map.put("PROPERTY_DESCRIPTION_2", "MO_TA_THEM");
		map.put("CONNEC_LAND_LOTTERY_NUMBER", "TS_TRENDAT.TEN_NGUOI_SD");
		map.put("LOTTERY_NUMBER", "QSSD.THUADATSO");
		map.put("NUMBER_MAP", "QSSD.TOBANDOSO");
		map.put("LAND_ADDRESS","QSSD.DIA_CHI");
		map.put("ACREAGE", "QSSD.DIEN_TICH");
		map.put("PRIVATE_USED_AREA","QSSD.DIEN_TICH_SDR");
		map.put("GENERAL_USED_AREA", "QSSD.DIEN_TICH_SDC");
		map.put("USE_PROPOSE", "QSSD.MUC_DICH");
		map.put("EXPIRY_DATE", "QSSD.THOIHANSUDUNG");
		map.put("ORIGINAL_USE", "QSSD.NGUON_GOC");
		map.put("PROPERTY_DOC_NUMBER", "QSSD.SO_GCN");		
		map.put("TYPE_ASSETS","TS_TRENDAT.LOAI_TS");
		map.put("CONSTRUCTION_AREA","TS_TRENDAT.DIENTICH_XD");
		map.put("NI_PAPER_BOOK", "QSSD.SO_VAOSO");
		map.put("NI_GRANTING_AGENCIES", "QSSD.CO_QUAN_CAP");
		map.put("NI_DATE_RANGE", "QSSD.NGAY_CAP");
		map.put("NO_CERTIFICATE", "QSSD.TEN_GIAY_CN");
		JsonArray TABLE_MOTA = SupBM.getTable(map, dirParent+".DS_BDS", BO);
		BO.addProperty("TABLE_MOTA", TABLE_MOTA.toString());
		map.clear();
		
		BO.addProperty("BY_ACREAGE", "");
		BO.addProperty("BY_CONSTRUCTION_AREA", "");
		
		
	}
	private JsonObject contractDate(String dir, JsonObject BO, String keyMap) throws SIException{
		JsonElement jsonElement = null;
		try {
			jsonElement=	get_value_byDir(BO, dir);
		} catch (Exception e) {
			BO.addProperty(keyMap, "");
			return BO;
		}
		
		if(!jsonElement.isJsonArray()){
			BO.addProperty(keyMap, "");
			return BO;
		}
		JsonArray ja = jsonElement.getAsJsonArray();
		String HDBD_NO_CONTRACT_DATE = "";
		for(JsonElement je: ja){
			if (!je.isJsonObject())
				continue;
			JsonObject jo = je.getAsJsonObject();
			if (jo != null && !jo.isJsonNull()) {
				String HDBD_CONTRACT_TYPE = "";
				String HDBD_NO_CONTRACT="";
				String HDBD_CONTRACT_DATE="";

				if(jo.get("LOAI_HD").isJsonPrimitive())
					HDBD_CONTRACT_TYPE = jo.get("LOAI_HD").getAsString();
				if(jo.get("SO").isJsonPrimitive())
					HDBD_NO_CONTRACT = jo.get("SO").getAsString();
				if(jo.get("NGAY").isJsonPrimitive())
					HDBD_CONTRACT_DATE = jo.get("NGAY").getAsString();
				HDBD_NO_CONTRACT_DATE += String.format("%s số %s ngày %s và các văn bản, sửa đổi bổ sung của Hợp đồng này \n", HDBD_CONTRACT_TYPE,HDBD_NO_CONTRACT,HDBD_CONTRACT_DATE);
				
			}
		}
		BO.addProperty(keyMap, HDBD_NO_CONTRACT_DATE);
		
		return BO;	
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
//		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		LGIssuesHandling.ConvertUserCodeToName(inputData, "BANK_FULLNAME", this.processDataSource);
		
		LGIssuesHandling.RadioButtonExchange(inputData, "GUARANTEE_OBLIGATIONS_1", "GUARANTEE_OBLIGATIONS_2");
		LGIssuesHandling.RadioButtonExchange(inputData, "TOTAL_GUARANTEE_OBLIGATIONS_2");
		
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}

}
