package com.service.impl.gendoc.v1_0;
import java.util.HashMap;
import java.util.Map;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.SupportBMHelper;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM89 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM89(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		
		SupportBMHelper support = new SupportBMHelper(super.mapper, super.processDataSource);
		int indexOfN = support.findIndexOfN(BO, this.dependentRequired, "ID_HD");
		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
		super.mapper = new Auto_Mapping(indexOfN);
		String dirParent = String.format("#.TT_SOANTHAO.TT_YC_HDTSBD[%s]", indexOfN);
		/////////////////MASTER DATA///////////////////////////	
		Map<String, String> map = new HashMap<String, String>();

         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////

		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		

        ////////////////RADIO BUTTON END///////////////////////
		
         ///////////////TABLE START////////////////////////////
		JsonElement BTC_DN_COMPANY = get_value_byDir(BO,dirParent+ ".TT_BEN_BD.TEN_KH_CODAU");
		if(BTC_DN_COMPANY.isJsonPrimitive())
		map.put("_BTC_DN_COMPANY", BTC_DN_COMPANY.getAsString());
		map.put("GTCG_SERI", "SO_TK");
		map.put("GTCG_DELIVERY_DATE", "NGAY_PH");
		map.put("GTCG_DTAE", "NGAY_DAO_HAN");
		map.put("GTCG_CURRENCY", "LOAI_TIEN");
		map.put("GTCG_MONEY", "SOTIEN_DB");
		JsonObject condition2 = new JsonObject();
		condition2.addProperty("LOAI_TSBD", "LOAITSBD02");
		JsonArray TABLE_89 = support.getTableInTable(map,dirParent+ ".DS_TV_GTCG", BO, "TT_TGTCG", condition2);
		BO.addProperty("TABLE_89", TABLE_89.toString());
		map.clear();
            ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		map.put("GTCG_MONEY_number", "GTCG_SUM_MONEY");
		BO = support.sum(map, TABLE_89, BO);
		map.clear();
		//////////////////////////////////////
		BO= contractDate(dirParent+".DS_HDTD_SO", BO, "HDBD_NO_CONTRACT_DATE");

        //////////////CUSTOME FIELD-END////////////////////
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
//	private JsonObject sum(Map<String,String> map, JsonArray jsonArray, JsonObject BO){
//		if(jsonArray.isEmpty()){
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//			    BO.addProperty(entry.getValue(), "");
//	        }
//			return BO;
//		}
//		double[] sumArr = new double[map.size()];
//
//		for (JsonElement je : jsonArray) {
//			if (!je.isJsonObject())
//				continue;
//
//			JsonObject jo = je.getAsJsonObject();
//			if (jo != null && !jo.isJsonNull()) {
//				int count = 0;
//				for (Map.Entry<String, String> entry : map.entrySet()) {
//					if (jo.get(entry.getKey()).isJsonPrimitive())
//						sumArr[count] += jo.get(entry.getKey()).getAsDouble();
//					count++;
//				}
//
//			}
//
//		}
//
//		int count = 0;
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			BO.addProperty(entry.getValue(), sumArr[count]);
//			count++;
//		}
//
//		return BO;
//	}



	@Override
	public void manualMapping_input() throws SIException {
		
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
		// Handle business
		JsonArray inputData = super.data.getAsJsonObject().get("InputData").getAsJsonArray();
		
		if(inputData != null && inputData.size() > 0){
			JsonObject input = inputData.get(0).getAsJsonObject();
			// prop handle 
			//WF_TYPE
			input.addProperty("WF_TYPE", "10");
			
//			input.addProperty("ADDRESS_CHECK", "CHECK");
		}
		String stringInputData = inputData.toString();
		super.data.getAsJsonObject().addProperty("InputData", stringInputData);
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}
 
}
