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

public class MS01a extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
//	private String tableKH = "";
	private String dependentRequired;
	public MS01a(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
		genDocInvoker  = new GenDocInvoker(docName, genDocEnv, processDataSource, log);
		super.invoker = genDocInvoker;
		super.isMergeData = true;
		super.isManualMapping_Input =true;
		super.isManualInvoke =true;
		this.docName = docName;
		this.dependentRequired= dependentRequired;
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
	     ////////////////////////////////
        map.put("ONGBA", "ONG_BA, BANK_GRANDPARENTS");
        BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_EXIM" , BO);
        map.clear();
        /////////////////////////////////
        map.put("NOICAPID", "NOI_CAP, BTC_DN_BUSINESSES_NUMBER_ISSUED_BY");
        BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
        map.clear();
        ////////////////////////////////
        map.put("NOICAPID", "NOI_CAP, BTC_CN_COUPLE_ISSUED_BY");
        BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_DINHDANH[0]" , BO);
        map.clear();
        ////////////////////////////////
        map.put("NOICAPID", "NOI_CAP, NHP_ISSUED_BY");
        BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI.TT_DINHDANH[0]" , BO);
        map.clear();
        ////////////////////////////////
        try {
        	 String  BTC_CN_COUPLE_PER_CONT_ADDRESS=  support.getPerContAddress(BO, ".TT_BEN_BD.TT_LIENLAC");
             BO.addProperty("BTC_CN_COUPLE_PER_CONT_ADDRESS", BTC_CN_COUPLE_PER_CONT_ADDRESS);
		} catch (Exception e) {
			  BO.addProperty("BTC_CN_COUPLE_PER_CONT_ADDRESS", "");
		}
       try {
    	   String  NHP_PER_CONT_ADDRESS=  support.getPerContAddress(BO,dirParent+ ".TT_BEN_BD.TT_NGUOIHONPHOI.TT_LIENLAC");
           BO.addProperty("NHP_PER_CONT_ADDRESS", NHP_PER_CONT_ADDRESS);
	   } catch (Exception e) {
		   BO.addProperty("NHP_PER_CONT_ADDRESS", "");
	    }
     
        ////////////////////////////////
        map.put("ONGBA", "ONG_BA, NHP_GRANDPARENTS");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI" , BO);
		map.clear();
        /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		
        String[] radio_PTHUC_NHANKQ= {"KQDK_LIVE","KQDK_POSTAL","KQDK_ELECTRONIC","KQDK_OTHER"};
		BO = support.toRadio(dirParent+".TT_DK_BPBD.PTHUC_NHANKQ",radio_PTHUC_NHANKQ, BO,"");
        ////////////////RADIO BUTTON END///////////////////////
		
   ///////////////TABLE START////////////////////////////
        map.put("*DSH_GRANDPARENTS", "ONGBA, ONG_BA");
		map.put("DSH_FULLNAME_INDIVIDUAL", "TEN_KH_CODAU");
		map.put("DSH_IDNUMBER", "TT_DINHDANH[0].SO_ID");
		map.put("DSH_PER_CONT_ADDRESS", "TT_LIENLAC");
		map.put("DSH_PHONE_NUMBER", "SO_DT");
		map.put("*DSH_ISSUED_BY", "NOICAPID, TT_DINHDANH[0].NOI_CAP");
		map.put("DSH_PHONE_NUMBER", "TT_DINHDANH[0].NGAY_CAP");
		JsonArray TABLE_DSH = support.getTable(map, dirParent+".TT_BEN_BD.DS_DONGSOHUU", BO);
		TABLE_DSH.forEach(jo -> {				
		      if(((JsonObject) jo).has("DSH_GRANDPARENTS") && ((JsonObject) jo).has("DSH_FULLNAME_INDIVIDUAL")){
		    	  String gr = ((JsonObject) jo).get("DSH_GRANDPARENTS").getAsString();
		    	  if(gr.isEmpty()) gr = "Ông/Bà";
		    	  String name = ((JsonObject) jo).get("DSH_FULLNAME_INDIVIDUAL").getAsString();
		    	  ((JsonObject) jo).addProperty("DSH_GRANDPARENTS_FULLNAME", String.format("%s %s", gr, name));
		      }		
		});
		BO.addProperty("TABLE_DSH", TABLE_DSH.toString());
		map.clear();
		
//		map.put("NUMBER_MAP","QSSD.BAN_DO_SO");
//		map.put("USE_PURPOSE","QSSD.MUC_DICH");
//		map.put("EXPIRY_DATE","QSSD.THOIHAN_SD");
//		map.put("LAND_ADDRESS","QSSD.DIA_CHI");
//		map.put("NO_CERTIFICATE","QSSD.TEN_GIAY_CN");
//		map.put("NUMBER_ISSUE_GCN","QSSD.SO_GCN");
//		map.put("NI_PAPER_BOOK","QSSD.SO_VAOSO");
//		map.put("NI_GRANTING_AGENCIES","QSSD.CO_QUAN_CAP");
//		map.put("NI_DATE_RANGE","QSSD.NGAY_CAP");
//		map.put("DESCRIBE","TS_HTTTL.MO_TA");
//		map.put("QTS_DOCUMENT","TS_HTTTL.GIAY_TO");
//		JsonArray TABLE_BDS = support.getTable(map, "#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_BDS", BO);
//		map.clear();
    ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		try {
			JsonElement TEN_NHP_CODAU_el = get_value_byDir(BO, dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI.TEN_KH_CODAU");
			String TEN_NHP_CODAU ="";
			if(TEN_NHP_CODAU_el.isJsonPrimitive())
				TEN_NHP_CODAU = TEN_NHP_CODAU_el.getAsString();		
			String NHP_GRANDPARENTS_FULLNAME= String.format("%s %s", BO.get("NHP_GRANDPARENTS").getAsString(), TEN_NHP_CODAU);
			BO.addProperty("NHP_GRANDPARENTS_FULLNAME", NHP_GRANDPARENTS_FULLNAME.toUpperCase());
		} catch (Exception e) {
			BO.addProperty("NHP_GRANDPARENTS_FULLNAME", "");
		}
		
        //////////////CUSTOME FIELD-END////////////////////
	}
	

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
//	private JsonObject toCheck(String dir, String key, JsonObject BO) throws SIException{
//		JsonElement jsonElement = get_value_byDir(BO, dir);
//		if(!jsonElement.isJsonPrimitive()){
//			return BO;
//		}		
//		String status = jsonElement.getAsString();
//		if(status.equalsIgnoreCase("1")){
//			BO.addProperty(key, "CHECK");
//		}else if(status.equalsIgnoreCase("0")){
//			BO.addProperty(key, "UNCHECK");
//		}
//		return BO;
//	}
//	private String getTrueOrCheck(String dir, JsonObject BO, String condition) throws SIException{
//		JsonElement jsonElement = get_value_byDir(BO, dir);
//		String result = "";
//		if(!jsonElement.isJsonPrimitive()){
//			return result;
//		}		
//		String status = jsonElement.getAsString();
//		if(condition.equals("CHECK")){
//			if(status.equalsIgnoreCase("1") ){
//				result= "CHECK";
//			}else if(status.equalsIgnoreCase("0")){
//				result= "UNCHECK";
//			}
//		}else if(condition.equals("TRUE")){
//			if(status.equalsIgnoreCase("1") ){
//				result= "TRUE";
//			}else if(status.equalsIgnoreCase("0")){
//				result= "FALSE";
//			}
//		}
//		
//		return result;
//	}
  
  
//  private JsonArray getTable(Map<String,String> map, String dir, JsonObject BO) throws SIException{
//		
//	  JsonArray jsonArrReturn = new JsonArray();
//		 JsonElement jsonElement= null;
//		 try {
//			  jsonElement=  get_value_byDir(BO,dir);	
//		} catch (Exception e) {
//			return jsonArrReturn;
//		}	
//		if(!jsonElement.isJsonArray())
//			return jsonArrReturn;
//		
//		JsonArray jsonArr= jsonElement.getAsJsonArray();
//		
//		if(jsonArr !=null && jsonArr.size()>0){
//			int count = 1;
//			for(JsonElement je: jsonArr){
//				if(!je.isJsonObject()) continue;
//				 JsonObject jo= new JsonObject();
//				 jo.addProperty("STT", count);
//				if(je.getAsJsonObject() != null && !je.getAsJsonObject().isJsonNull()){					
//					for (Map.Entry<String, String> entry : map.entrySet()) {					
//						if (entry.getKey().startsWith("_")) {
//							jo.addProperty(entry.getKey().substring(1), entry.getValue());
//						}else if(entry.getKey().startsWith("*")){
//							String[] masterData= entry.getValue().split(","); 
//							String group = masterData[0].trim();
//							String dirT = "#." + masterData[1].trim();
//							JsonElement valueJsonElement = get_value_byDir(je.getAsJsonObject(), dirT);
//							if(valueJsonElement== null || !valueJsonElement.isJsonPrimitive()) continue;
//							String code = valueJsonElement.getAsString();									
//							String name=	convertMasterdataCodeToName(group,code);
//							jo.addProperty(entry.getKey().substring(1), name == null? "": name);
//						}else if(entry.getValue().contains("TT_DINHDANH[0]")){
//							String key_of_ttdd = entry.getValue().split("\\.")[1].trim();
//							String dir_ttdd = "#." + entry.getValue();
//							if(je.isJsonObject())
//								continue;
//							if(key_of_ttdd.equalsIgnoreCase("NGAY_CAP")){
//								JsonElement value_dir_ttdd = get_value_byDir(je.getAsJsonObject(), dir_ttdd);
//								if(value_dir_ttdd!= null && value_dir_ttdd.isJsonPrimitive())
//									if(!value_dir_ttdd.getAsString().equals("0") || !value_dir_ttdd.getAsString().equals(""))							
//									   jo.addProperty(entry.getKey(), dateToString(value_dir_ttdd.getAsString()));
//							}else{
//								JsonElement value_dir_ttdd = get_value_byDir(je.getAsJsonObject(), dir_ttdd);
//								if(value_dir_ttdd!= null && value_dir_ttdd.isJsonPrimitive())
//									jo.addProperty(entry.getKey(), value_dir_ttdd.getAsString());
//							}
//							
//						}else if(entry.getValue().equals("TT_LIENLAC")){
////							JsonElement jaLLElement = je.getAsJsonObject().get(entry.getValue());
////							JsonArray jaLL = jaLLElement.getAsJsonArray();
//							SupportBMHelper support = new SupportBMHelper(super.mapper, super.processDataSource);
//						String DSH_PER_CONT_ADDRESS=	support.getPerContAddress(je.getAsJsonObject(),"#.TT_LIENLAC");
//						jo.addProperty("DSH_PER_CONT_ADDRESS", DSH_PER_CONT_ADDRESS);
//							
//						}else {							
//							JsonElement valueJsonElement = je.getAsJsonObject().get(entry.getValue());
//							if(valueJsonElement== null || !valueJsonElement.isJsonPrimitive()) continue;
//							String value = valueJsonElement.getAsString();
//							jo.addProperty(entry.getKey(), value);
//						}
//			        }
//					
//					String DSH_GRANDPARENTS = jo.get("DSH_GRANDPARENTS").getAsString();
//					String DSH_FULLNAME_INDIVIDUAL = jo.get("DSH_FULLNAME_INDIVIDUAL").getAsString();					
//					String DSH_GRANDPARENTS_FULLNAME = String.format("%s %s",DSH_GRANDPARENTS, DSH_FULLNAME_INDIVIDUAL);
//					jo.addProperty("DSH_GRANDPARENTS_FULLNAME", DSH_GRANDPARENTS_FULLNAME.toUpperCase());
//
//					
//				}
//				jsonArrReturn.add(jo);
//				count++;
//			}		
//		}
//		return jsonArrReturn;
//	}

//private String getPerContAddress(JsonObject jo, String dir) throws SIException {
//	Map<String, String> map = new HashMap<String, String>();
//	map.put("_DIA_CHI_CUTHE", "DIA_CHI_CUTHE");
//	map.put("_LOAI_DIA_CHI", "LOAI_DIA_CHI");
//	map.put("QUOCGIA", "QUOC_GIA");
//	map.put("QUAN", "QUAN_HUYEN");
//	map.put("XA", "XA_PHUONG");
//	map.put("TINH", "TINH_TP");
//	List<Map<String, String>> listMap1 = getMasterData1(map,dir, jo);
//	String PER_CONT_ADDRESS = "";
//	for (Map<String, String> mapDC : listMap1) {
//		if (mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("HKTHUONGTRU")) {
//			String dctt = String.format("%s, %s, %s, %s, %s. ",
//					mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
//					mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
//					mapDC.get("QUOC_GIA"));
//			//target.addProperty("NDV_PER_ADDRESS", dctt);
//			PER_CONT_ADDRESS += String.format("Ä�á»‹a chá»‰ thÆ°á»�ng chÃº: %s", dctt) ;
//		}
//		if (mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("DCLIENHE")) {
//			String dctt = String.format("%s, %s, %s, %s, %s. ",
//					mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
//					mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
//					mapDC.get("QUOC_GIA"));
//			//target.addProperty("NDV_CONT_ADDRESS", dctt);
//			PER_CONT_ADDRESS += String.format("; Ä�á»‹a chá»‰ liÃªn láº¡c: %s", dctt);
//		}
//	}
//	if(PER_CONT_ADDRESS.startsWith(";"))
//		PER_CONT_ADDRESS = PER_CONT_ADDRESS.substring(1);
//	if(PER_CONT_ADDRESS.endsWith(";"))
//		PER_CONT_ADDRESS = PER_CONT_ADDRESS.substring(0, PER_CONT_ADDRESS.length() -1);
//	map.clear();
//	
//	return PER_CONT_ADDRESS;
//}



//	private String dateToString(String date){
//		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("'ngày ' dd ' tháng ' MM ' năm ' yyyy");
//		ZonedDateTime ldt = ZonedDateTime.parse(date);
//		String formattedString = ldt.format(dateFormat); 
//		return formattedString;
//	}

}
