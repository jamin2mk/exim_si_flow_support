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
import com.si.helper.MoneyHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM26 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM26(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		/////////////////MASTER DATA///////////////////////////	
		map.put("ONGBA", "ONG_BA, BANK_GRANDPARENTS");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_EXIM" , BO);
		map.clear();
		
		map.put("NOICAPID", "NOI_CAP, CTD_CN_ISSUED_BY");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH.TT_DINHDANH[0]" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, CTD_DN_GRANDPARENTS|CTD_CN_GRANDPARENTS");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH" , BO);
		map.clear();
		
		map.put("CHUCVU", "CHUC_VU, CTD_DN_POSITION_ENTERPRISE");
		BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH" , BO);
		map.clear();
		
        map.put("TCTD", "TCPH, TIEN_ISSUERS");
        BO = SupBM.getMasterData(map,"#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_TV_GTCG[0]" , BO);
        map.clear();
		////////////////END MASTERDATA//////////////////
		
		/////////////GET ADDRESS TT_LL//////////////////
		BO = SupBM.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH.TT_DIACHI", "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
		BO = SupBM.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		/////////////END ADDRESS TT_LL//////////////////
		
		/////////////GET CHECKBOX//////////////////
		BO = SupBM.toCheck("#.TT_SOANTHAO.TT_YC_HDTINDUNG[0].LOAI_PHI", "OTHER_FEES_2_CHECK", BO);
		BO = SupBM.toCheck("#.TT_DENGHI.TT_CACBEN_KI_HD.YC_HONPHOI", "IS_MARRY", BO);
		BO = SupBM.toCheck("#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH.BEN_UQ", "KWNN", BO);
		BO = SupBM.toCheck("#.TT_SOANTHAO.TT_YC_HDTINDUNG[0].VAYTUNGLAN_GN1LAN.THOIHAN_GN_DT", "FIRST_DISCHARGE_DURATION", BO);
		/////////////END CHECKBOX//////////////////
		
		////////////Change Num to Let//////////////
		try {
			JsonElement js = get_value_byDir(BO,"#.TT_SOANTHAO.TT_YC_HDTINDUNG[0].SOTIEN_CTD");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_MONEY_LOAN", moneyText);
		} catch (Exception e) {
			BO.addProperty("BY_MONEY_LOAN", "");
		}
		////////////////////////////////////////
		
		//////////////TABLE/////////////////////
		map.put("TYPE_TSBD", "LOAI_TSBD");
		map.put("TIEN_MONEY", "SO_TIEN");
		map.put("TIEN_STK", "SO_TK");
		map.put("TIEN_ISSUERS", "TCPH");
		JsonObject condition1 = new JsonObject();
		condition1.addProperty("LOAI_TSBD", "LOAITSBD01");
		JsonArray TABLE_GTCG_T = SupBM.getTable(map,"#.TT_SOANTHAO.TT_YC_HDTINDUNG[0].DS_TIEN_VANG_GTCG", BO, condition1);
		if(TABLE_GTCG_T.size()>0){
			BO.addProperty("TYPE_TSBD", "LOAITSBD01");
		}else{
			BO.addProperty("TYPE_TSBD", "");
		}
		BO.addProperty("TABLE_GTCG_T", TABLE_GTCG_T.toString());
		map.clear();
		/////////////////////////////////////
		map.put("GTCG_NAME", "TCPH");
		map.put("GTCG_SERI", "SO_TK");
		map.put("GTCG_DELIVERY_DATE", "NGAY_PH");
		map.put("GTCG_DTAE", "NGAY_DAO_HAN");
		map.put("GTCG_CURRENCY", "LOAI_TIEN");
		map.put("GTCG_VALUE", "GIA_TRI");
		map.put("GTCG_MONEY", "SOTIEN_DB");
		JsonObject condition2 = new JsonObject();
		condition2.addProperty("LOAI_TSBD", "LOAITSBD02");
		JsonArray TABLE_GTCG_G = SupBM.getTableInTable(map,"#.TT_SOANTHAO.TT_YC_HDTINDUNG[0].DS_TIEN_VANG_GTCG", BO, "TT_TGTCG", condition2);
		if(TABLE_GTCG_G.size()>0){
			BO.addProperty("TYPE_TSBD", "LOAITSBD02");
		}else{
			BO.addProperty("TYPE_TSBD", "");
		}
		BO.addProperty("TABLE_GTCG_G", TABLE_GTCG_G.toString());
		map.clear();
		
		////////////////////////////////////
//		map.put("VANG_LTS", "LOAI_TS");
//		map.put("VANG_QUANTITY", "SO_LUONG");
//		map.put("VANG_CURRENCY", "LOAI_TIEN");
//		map.put("VANG_VALUE", "GIA_TRI");
//		map.put("VANG_DOCUMENT", "CHUNG_TU");
//		JsonArray TABLE_VANG = SupBM.getTable(map, "#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_TV_GTCG[0].TT_V", BO);
//		BO.addProperty("TABLE_VANG", TABLE_VANG.toString());
//		map.clear();
		//////////////{}//////////////////
		map.put("GTCG_SERI", "SO_TK");
		JsonArray TABLE_GTCG_1 = SupBM.getTableInTable(map,"#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_TIEN_VANG_GTCG", BO, "TT_TGTCG", null);
		BO.addProperty("TABLE_GTCG_1", TABLE_GTCG_1.toString());
		map.clear();
	  //////////////{}//////////////////
		map.put("GTCG_SERI", "SO_TK");
		JsonArray TABLE_GTCG_2 = SupBM.getTableInTable(map,"#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_TIEN_VANG_GTCG", BO, "TT_TGTCG", null);
		BO.addProperty("TABLE_GTCG_2", TABLE_GTCG_2.toString());
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
		LGIssuesHandling.ConvertUserCodeToName(inputData, "BANK_FULLNAME", this.processDataSource);
		
		LGIssuesHandling.RadioButtonExchange(inputData, "BANKING_PUNISH_FREE_CHECK");
		LGIssuesHandling.RadioButtonExchange(inputData, "PREVAILING_REGULATIONS");
		LGIssuesHandling.RadioButtonExchange(inputData, "BANKING_RULE", "BANKING_PUNISH", "BANKING_RIGHTS");
		LGIssuesHandling.RadioButtonExchange(inputData, "PAYOUT_PAYMENT_1", "PAYOUT_PAYMENT_2", "PAYOUT_PAYMENT_3", "PAYOUT_PAYMENT_4");
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}

}
