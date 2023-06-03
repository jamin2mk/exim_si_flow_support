package com.service.impl.gendoc.v1_0;

import java.util.HashMap;
import java.util.Map;

import Handler.Auto_Mapping;

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

public class BM25 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM25(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		String dirParent = String.format("#.TT_SOANTHAO.TT_YC_HDTINDUNG[%s]", indexOfN);
		/////////////////MASTER DATA///////////////////////////	
		Map<String, String> map = new HashMap<String, String>();
		

		////////////////////////////////	
		map.put("ONGBA", "ONG_BA, BANK_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_EXIM" , BO);
		map.clear();
		//////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, CTD_DN_BUSINESSES_NUMBER_ISSUED_BY");
		map.put("ONGBA", "ONG_BA, CTD_DN_GRANDPARENTS|CTD_CN_GRANDPARENTS");
		map.put("CHUCVU", "CHUC_VU, CTD_DN_POSITION_ENTERPRISE");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH" , BO);
		map.clear();
		//////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, CTD_CN_ISSUED_BY");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH.TT_DINHDANH[0]" , BO);
		map.clear();
		////////////////////////////////
		map.put("ONGBA", "ONG_BA, NDV_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH" , BO);
		map.clear();
        ////////////////////////////////
//        map.put("LOAITIEN", "LOAITIEN, CURRENCY");
//        BO = getMasterData(map,"#.TT_SOANTHAO.TT_YC_HDTINDUNG[0]" , BO);
//        map.clear();
		////////////////////////////////
		BO = support.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		// //////////////////////////////
		BO = support.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH.TT_DIACHI", "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		BO = support.toCheck(dirParent+".PTHUC_GN_TIENMAT", "CASH", BO);
		BO = support.toCheck(dirParent+".PTHUC_GN_CK", "TRANSFER", BO);
		BO = support.toCheck("#.TT_DENGHI.TT_CACBEN_KI_HD.YC_HONPHOI", "IS_MARRY", BO);
		BO = support.toCheck(dirParent+".PHI_KHAC", "OTHER_FEES_2_CHECK", BO);
		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		
		String[] radio_PHUONGTHUC_PHAT= {"BANKING_RULE","BANKING_PUNISH","BANKING_RIGHTS"};
		BO = support.toRadio(dirParent+".PHUONGTHUC_PHAT",radio_PHUONGTHUC_PHAT, BO, "CHECK");
		
		String[] radio_LOAI_YC_PHAT= {"BANKING_PUNISH_FREE_CHECK","BANKING_PUNISH_NO"};
		BO = support.toRadio(dirParent+".LOAI_YC_PHAT",radio_LOAI_YC_PHAT, BO, "CHECK");
		
		String[] radio_KY_TRA_LAI= {"MONTHLY_ON_DAY","OTHER_PAYMENT_TERMS"};
		BO = support.toRadio(dirParent+".KY_HAN_LAI",radio_KY_TRA_LAI, BO, "CHECK");
		
		String[] radio_KY_TRA_GOC= {"MULTIPLE_PAYMENT_MATERIALS","PAY_ONE_PROFIT_G", "OTHER_PAYMENT_MATERIALS"};
		BO = support.toRadio(dirParent+".KY_HAN_GOC",radio_KY_TRA_GOC, BO, "CHECK");
		
		String[] radio_CHUKY_LAISUAT= {"RULE_1","RULE_2","REGULARLY_ADJUSTMENT"};
		BO = support.toRadio(dirParent+".CHUKY_LAISUAT",radio_CHUKY_LAISUAT, BO, "CHECK");
		
		String[] radio_NGUYENTAC_DC= {"GOVERNMENT_PRINCIPLES_1","GOVERNMENT_PRINCIPLES_2","GOVERNMENT_ORTHER_1"};
		BO = support.toRadio(dirParent+".NGUYENTAC_DC",radio_NGUYENTAC_DC, BO, "CHECK");
		
		
		String[] radio_NGTAC_THUNOGO_TH= {"PAYOUT_PAYMENT_1","PAYOUT_PAYMENT_2","PAYOUT_PAYMENT_3","PAYOUT_PAYMENT_4"};
		BO = support.toRadio(dirParent+".NGTAC_THUNOGOC_TH",radio_NGTAC_THUNOGO_TH, BO, "CHECK");
        ////////////////RADIO BUTTON END///////////////////////
		
   ///////////////TABLE START////////////////////////////
		
		//////////////{}//////////////////
//		map.put("GTCG_NAME", "TCPH");
//		map.put("GTCG_SERI", "SO_TK");
//		map.put("GTCG_DELIVERY_DATE", "NGAY_PH");
//		map.put("GTCG_DTAE", "NGAY_DAO_HAN");
//		map.put("GTCG_CURRENCY", "LOAI_TIEN");
//		map.put("GTCG_VALUE", "GIA_TRI");
//		map.put("GTCG_MONEY", "SOTIEN_DB");
//		JsonArray TABLE_GTCG = support.getTable(map,dirParent+ ".DS_TIEN_VANG_GTCG[0].TT_TGTCG", BO);
//		BO.addProperty("TABLE_GTCG", TABLE_GTCG.toString());
//		map.clear();
        //////////////{}//////////////////
        //////////////{}//////////////////
//		map.put("VANG_LTS", "SOTIEN");
//		map.put("VANG_QUANTITY", "SOTIEN");
//		map.put("VANG_CURRENCY", "SOTIEN");
//		map.put("VANG_VALUE", "SOTIEN");
//		map.put("VANG_DOCUMENT", "SOTIEN");
//		JsonArray TABLE_VANG = getTable(map, "#.data.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_TV_GTCG[0].TT_V.LOAI_TS", BO);
//		BO.addProperty("TABLE_VANG", TABLE_VANG.toString());
//		map.clear();
				
		map.put("TYPE_TSBD", "LOAI_TSBD");
		map.put("TIEN_MONEY", "SO_TIEN");
		map.put("TIEN_STK", "SO_TK");
		map.put("TIEN_ISSUERS", "TCPH");
		JsonObject condition1 = new JsonObject();
		condition1.addProperty("LOAI_TSBD", "LOAITSBD01");
		JsonArray TABLE_GTCG_T = support.getTable(map,dirParent+ ".DS_TIEN_VANG_GTCG", BO, condition1);
		if(TABLE_GTCG_T.size()>0){
			BO.addProperty("TYPE_TSBD_T", "LOAITSBD01");
		}else{
			BO.addProperty("TYPE_TSBD_T", "");
		}
		BO.addProperty("TABLE_GTCG_T", TABLE_GTCG_T.toString());
		map.clear();
		
		map.put("GTCG_NAME", "TCPH");
		map.put("GTCG_SERI", "SO_TK");
		map.put("GTCG_DELIVERY_DATE", "NGAY_PH");
		map.put("GTCG_DTAE", "NGAY_DAO_HAN");
		map.put("GTCG_CURRENCY", "LOAI_TIEN");
		map.put("GTCG_VALUE", "GIA_TRI");
		map.put("GTCG_MONEY", "SOTIEN_DB");
		JsonObject condition2 = new JsonObject();
		condition2.addProperty("LOAI_TSBD", "LOAITSBD02");
		JsonArray TABLE_GTCG_G = support.getTableInTable(map,dirParent+ ".DS_TIEN_VANG_GTCG", BO, "TT_TGTCG", condition2);
		if(TABLE_GTCG_G.size()>0){
			BO.addProperty("TYPE_TSBD_G", "LOAITSBD02");
		}else{
			BO.addProperty("TYPE_TSBD_G", "");
		}
		BO.addProperty("TABLE_GTCG_G", TABLE_GTCG_G.toString());
		map.clear();
		
		
		map.put("VANG_LTS", "LOAI_TS");
		map.put("VANG_QUANTITY", "SO_LUONG");
		map.put("VANG_CURRENCY", "LOAI_TIEN");
		map.put("VANG_VALUE", "GIA_TRI");
		map.put("VANG_DOCUMENT", "CHUNG_TU");
		JsonObject condition3 = new JsonObject();
		condition3.addProperty("LOAI_TSBD", "LOAITSBD03");
		JsonArray TABLE_GTCG_V = support.getTableInTable(map,dirParent+ ".DS_TIEN_VANG_GTCG", BO, "TT_V", condition3);
		if(TABLE_GTCG_V.size()>0){
			BO.addProperty("TYPE_TSBD_V", "LOAITSBD03");
		}else{
			BO.addProperty("TYPE_TSBD_V", "");
		}
		BO.addProperty("TABLE_GTCG_V", TABLE_GTCG_V.toString());
		map.clear();
		
        //////////////{}//////////////////
		map.put("GTCG_SERI", "SO_TK");
		JsonArray TABLE_GTCG_1 = support.getTableInTable(map,dirParent+ ".DS_TIEN_VANG_GTCG", BO, "TT_TGTCG", condition2);
		BO.addProperty("TABLE_GTCG_1", TABLE_GTCG_1.toString());
		map.clear();
        //////////////{}//////////////////
		map.put("GTCG_SERI", "SO_TK");
		JsonArray TABLE_GTCG_2 = support.getTableInTable(map,dirParent+ ".DS_TIEN_VANG_GTCG", BO, "TT_TGTCG", condition2);
		BO.addProperty("TABLE_GTCG_2", TABLE_GTCG_2.toString());
		map.clear();
        //////////////{}//////////////////
	//	map.put("_TRANSFER_CHECK", getTrueOrCheck("#.TT_SOANTHAO.TT_YC_HDTINDUNG.DS_PTHUC_CHUYENKHOAN", BO, "CHECK"));
		map.put("HDTD_157", "TENTHUHUONG");
		map.put("HDTD_154", "STK_THU_HUONG");
		map.put("*HDTD_155", "TCTD, TCTD");
		map.put("HDTD_478", "SOTIEN");
		JsonArray TABLE_INFO_BENEFICIARYS = support.getTable(map,dirParent+ ".DS_PTHUC_CHUYENKHOAN", BO);
		BO = getListNTH(TABLE_INFO_BENEFICIARYS,BO);
		map.clear();
    ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		try {
			JsonElement js = get_value_byDir(BO,dirParent+ ".SOTIEN_CTD");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_MONEY_LOAN", moneyText);
		} catch (Exception e) {
			BO.addProperty("BY_MONEY_LOAN", "");
		}
		
		//////////////////////////////////////
		map.put("GTCG_VALUE_number", "GTCG_SUM_VALUE");
		map.put("GTCG_MONEY_number", "GTCG_SUM_MONEY");
		BO = support.sum(map, TABLE_GTCG_G, BO);
		map.clear();
		//////////////////////////////////////

        //////////////CUSTOME FIELD-END////////////////////
	}
	
//	public String convertAuthBranchCodeToName(String code) throws SIException {
//
//		String result = null;
//		try {
//			JsonArray auth_branch_Info = ServiceHelper.loadAuthBranch(code, this.processDataSource);
//			if(auth_branch_Info != null && auth_branch_Info.size() > 0){
//				for (JsonElement authBranch : auth_branch_Info) {
//					if(code.equalsIgnoreCase(authBranch.getAsJsonObject().get("brn_code").getAsString())){
//						result = authBranch.getAsJsonObject().get("brn_name").getAsString();
//						break;
//					}
//				}
//			}
//		} catch (Exception e) {
//			throw new SIException(Error.MAP_99, "Error in converting masterdata-code-to-name", e);				
//		}
//		return result;
//	}

	 private JsonObject getListNTH(JsonArray TABLE_NTH, JsonObject BO) throws SIException{
			
			String template = "- %s, số tài khoản %s tại %s, số tiền %s \n";
			String result = "";
			for(JsonElement je: TABLE_NTH){
				if(!je.isJsonObject()) continue;
				JsonObject jo = je.getAsJsonObject();
				result += String.format(template, jo.get("HDTD_157"), jo.get("HDTD_154"), jo.get("HDTD_155"), jo.get("HDTD_478"));
			}
					
			BO.addProperty("TABLE_INFO_BENEFICIARYS", result);
			return BO;
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
		
		LGIssuesHandling.ConvertUserCodeToName(inputData, "BANK_FULLNAME", this.processDataSource);
		String stringInputData = inputData.toString();
		super.data.getAsJsonObject().addProperty("InputData", stringInputData);
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}


}
