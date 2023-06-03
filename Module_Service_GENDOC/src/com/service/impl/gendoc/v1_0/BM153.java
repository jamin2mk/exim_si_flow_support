package com.service.impl.gendoc.v1_0;

import java.util.HashMap;
import java.util.Map;

import Handler.Auto_Mapping;

import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.SupportBMHelper;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.helper.MoneyHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM153 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM153(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
//		JsonObject TT_BAO_CAO = BO.get("TT_BAO_CAO").getAsJsonObject();
//
//		JsonArray TT_YC_BAOLANH = TT_BAO_CAO.get("TT_YC_BAOLANH").getAsJsonArray();
//		TT_YC_BAOLANH.add(new JsonObject());
//		TT_YC_BAOLANH.get(0).getAsJsonObject().addProperty("DIACHI_THUHUONG", "");
//		TT_YC_BAOLANH.get(0).getAsJsonObject().addProperty("SHD_KINHTE", "");
//		TT_YC_BAOLANH.get(0).getAsJsonObject().addProperty("KY_NGAY", "");
//		TT_YC_BAOLANH.get(0).getAsJsonObject().addProperty("DK_NV_BAOLANH", "");
//		TT_YC_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHETHAN_SUKIEN", "");
//		TT_YC_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHIEULUC_SUKIEN", "");
		
		SupportBMHelper SupBM = new SupportBMHelper(super.mapper, super.processDataSource);
		String newDependentRequired = SupBM.modifiedDIR(this.dependentRequired, "#.TT_BAO_CAO.TT_YC_BAOLANH");
		int indexOfN = SupBM.findIndexOfN(BO, newDependentRequired, "ID_BAOLANH");
		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
		super.mapper = new Auto_Mapping(indexOfN);
		String dirParent = String.format("#.TT_BAO_CAO.TT_YC_BAOLANH[%s]", indexOfN);
		
		Map<String, String> map = new HashMap<String, String>();
		//MASTERDATA
		map.put("LOAIID", "LOAI_ID, BL_ID_TYPE");
		BO = getMasterData(map, dirParent+".TT_DINH_DANH[0]", BO);
		map.clear();
		
		map.put("NOICAPID", "NOI_CAP, BL_ID_ISSUED");
		BO = getMasterData(map, dirParent+".TT_DINH_DANH[0]", BO);
		map.clear();
		
		map.put("LOAIID", "SO_ID, BL_CN_TYPE_ID");
		BO = getMasterData(map, "#.TT_BAO_CAO.TT_CTD_KH.TT_DINHDANH[0]", BO);
		map.clear();
		
//		map.put("NGAYHIEULUC", "NGAY_HIEULUC, BL_DAY");
//		BO = getMasterData(map, "#.TT_BAO_CAO.TT_YC_BAOLANH[0]", BO);
//		map.clear();
		
		map.put("LOAITIEN", "LOAI_TIEN, BL_CURRENCY");
		BO = getMasterData(map, "#.TT_BAO_CAO.TT_YC_BAOLANH[0]", BO);
		map.clear();
		
		BO = SupBM.getAddress(BO, "#.TT_BAO_CAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		
		//quy doi ra chu~
		JsonElement js = get_value_byDir(BO, dirParent+".SOTIEN_BLANH");
		String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
		BO.addProperty("BY_GUARANTEE_AMOUNT", moneyText);
		
		BO = toCheck(dirParent+".NGAYHETHAN_CUTHE", "BL_EXACT_DAY", BO);	
		BO = toCheck(dirParent+".NGAY_HH_SUKIEN", "GNBL_EVENT_1", BO);
		BO = toCheck(dirParent+".NGAY_HH_CTHE", "BL_EXACT_DAY", BO);
		BO = toCheck(dirParent+".SONGAY", "NO_DAYS_1", BO);
		BO = toCheck(dirParent+".SONGAY", "BL_NO_DATE_1", BO);
		BO = toCheck(dirParent+".CUTHE_NGAYHETHIEULUC", "BL_SPECIFICALLY_1", BO);
	}

	
	private JsonObject toCheck(String dir, String key, JsonObject BO) throws SIException{
		String status = "0";
		try {
			status=	get_value_byDir(BO, dir).getAsString();
		} catch (Exception e) {
			status = "0";
		}
		if(status.equalsIgnoreCase("1")){
			BO.addProperty(key, "CHECK");
		}else if(status.equalsIgnoreCase("0")){
			BO.addProperty(key, "UNCHECK");
		}
		return BO;
	}
	
	public JsonObject getMasterData(Map<String,String> map, String dir, JsonObject BO) throws SIException{
		
		JsonElement jsonElement= null;		
		try {
			jsonElement = get_value_byDir(BO,dir);	
		} catch (Exception e) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String[] values = entry.getValue().split(",");
				String[] twoKey = values[1].split("\\|");
				for(String tk : twoKey)
					BO.addProperty(values.length==2 ? tk.trim() : values[0].trim(), "");
	        }
			return BO;
		}		
		if(jsonElement == null || !jsonElement.isJsonObject())
			return BO;
		
		JsonObject jsonObject= jsonElement.getAsJsonObject();
				if(jsonObject != null && !jsonObject.isJsonNull()){
					for (Map.Entry<String, String> entry : map.entrySet()) {
					String[] values = entry.getValue().split(",");
					String name="";
					if(jsonObject.get(values[0].trim()) != null && !jsonObject.get(values[0].trim()).isJsonNull())
					 name=	convertMasterdataCodeToName(entry.getKey(),jsonObject.get(values[0].trim()).getAsString());
					String[] twoKey = values[1].split("\\|");
					for(String tk : twoKey)
					BO.addProperty(values.length==2 ? tk.trim() : values[0].trim(), name);
			        }
		}
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
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}

}
