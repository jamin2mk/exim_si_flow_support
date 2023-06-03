package com.service.impl.gendoc.v1_0;

import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.LGIssuesHandling;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM13 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM13(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		
		// STARTING HANDLING MULTIPLE TEMPLATES
		if(dependentRequired!=null || "".equals(dependentRequired)){
			LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_HD");
		}
		// ENDING HANDLING MULTIPLE TEMPLATES
		
		// CONFIGURATING AVIABLE PATH
		JsonObject TT_SOANTHAO = BO.get("TT_SOANTHAO").getAsJsonObject();
		JsonObject TT_CTD_KH = TT_SOANTHAO.get("TT_CTD_KH").getAsJsonObject();
		
		// STARTING HANDLING CONTACT INFOMATION
		JsonArray TT_LIENLAC = TT_CTD_KH.get("TT_LIENLAC").getAsJsonArray();
		JsonArray TT_DIACHI = TT_CTD_KH.get("TT_VC_KH").getAsJsonObject().get("TT_DIACHI").getAsJsonArray();
		
		/*CAP TIN DUNG CA NHAN*/
		if (!TT_LIENLAC.isEmpty()) {
			LGIssuesHandling.GetAddressInfo(TT_LIENLAC, BO, this.processDataSource, "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		} 
		else {
			BO.addProperty("CTD_CN_PER_ADDRESS", "");
			BO.addProperty("CTD_CN_CONT_ADDRESS", "");
		}
		
		/*CAP TIN DUNG NGUOI HON PHOI*/
		if (!TT_DIACHI.isEmpty()) {
			LGIssuesHandling.GetAddressInfo(TT_DIACHI, BO, this.processDataSource, "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
		} 
		else {
			BO.addProperty("NDV_PER_ADDRESS", "");
			BO.addProperty("NDV_CONT_ADDRESS", "");
		}
		// ENDING HANDLING CONTACT INFOMATION 
		
		// TODO HANDLING PATHS
		BO.addProperty("LOAN_DATE_FIRST_DESCRIBE", "Thời hạn giải ngân đầu tiên: Tối đa đến hết ngày .../.../...");
		BO.addProperty("REFEREE_1", "hoặc Trung tâm Trọng tài quốc tế Việt Nam (VIAC) theo Quy tắc tố tụng trọng tài của Trung tâm này do Eximbank lựa chọn ");		
		BO.addProperty("REFEREE_2", "hoặc Trọng tài ");		
		BO.addProperty("REFEREE_3", "hoặc Trung tâm Trọng tài quốc tế Việt Nam do Eximbank lựa chọn ");	
		
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
		
		// STARTING HANDLING MASTER DATA FROM OUTPUT
		LGIssuesHandling.ConvertMsDataToName(inputData, "BANK_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_CN_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NDV_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_CN_ISSUED_BY", "NOICAPID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NDV_ISSUED_BY", "NOICAPID", this.processDataSource);
		// ENDINGS HANDLING MASTER DATA FROM OUTPUT
		
		// LOADING NAME BY MA_NV
		LGIssuesHandling.ConvertUserCodeToName(inputData, "BANK_FULLNAME", this.processDataSource);

		// STARTING HANDLING OUTPUT RADIOBUTTON
		LGIssuesHandling.RadioButtonExchange(inputData, "BANKING_RULE", "BANKING_PUNISH", "BANKING_RIGHTS");
		LGIssuesHandling.RadioButtonExchange(inputData, "PAYOUT_PAYMENT_1", "PAYOUT_PAYMENT_2", "PAYOUT_PAYMENT_3", "PAYOUT_PAYMENT_4");
		LGIssuesHandling.RadioButtonExchange(inputData, "BANKING_PUNISH_FREE_CHECK");
		LGIssuesHandling.RadioButtonExchange(inputData, "PREVAILING_REGULATIONS");
		LGIssuesHandling.RadioButtonExchange(inputData, "OTHER_FEES_1_CHECK", "OTHER_FEES_2_CHECK");
		// ENDING HANDLING OUTPUT RADIOBUTTON
		
		// STARTING HANDLING OUTPUT CHECKBOXBUTTON
		LGIssuesHandling.CheckBoxExchange(inputData, "IS_MARRY");
		LGIssuesHandling.CheckBoxExchange(inputData, "KWNN");
		LGIssuesHandling.CheckBoxExchange(inputData, "LOAN_DATE_FIRST");
		LGIssuesHandling.CheckBoxExchange(inputData, "WITHDRAWAL_COMMITMENT_FEE");
		// ENDING HANDLING OUTPUT CHECKBOXBUTTON
		
		// STARTING HANDLING NUMBER FORMAT
		LGIssuesHandling.GetNumberFormater(inputData, "LOAN", "VN", 0);
		// ENDING HANDLING NUMBER FORMAT
		
		// STARTING GET BY NUMBER IN WORLD
		LGIssuesHandling.GetByNumberInWords(inputData, "LOAN", "BY_MONEY_LOAN");
		// ENDING GET BY NUMBER IN WORLD
		
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
		
	}

}
