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

public class BM22 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM22(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		
		// STARTING HANDLING TABLE
		BO.addProperty("TABLE_BPBD", new JsonArray().toString());
		// ENDING HNADLING TABLE
		
		// TODO HANDLING PATHS
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
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_DN_BUSINESSES_NUMBER_ISSUED_BY", "NOICAPID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_DN_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_DN_POSITION_ENTERPRISE", "CHUCVU", this.processDataSource);
		// ENDINGS HANDLING MASTER DATA FROM OUTPUT
		
		// LOADING NAME BY MA_NV
		LGIssuesHandling.ConvertUserCodeToName(inputData, "BANK_FULLNAME", this.processDataSource);

		// STARTING HANDLING OUTPUT RADIOBUTTON
		LGIssuesHandling.RadioButtonExchange(inputData, "PREVAILING_REGULATIONS");
		// ENDING HANDLING OUTPUT RADIOBUTTON
		
		// STARTING HANDLING OUTPUT CHECKBOXBUTTON
		LGIssuesHandling.CheckBoxExchange(inputData, "TYPE_LETTER_CREDIT_1");
		LGIssuesHandling.CheckBoxExchange(inputData, "TYPE_LETTER_CREDIT_2");
		LGIssuesHandling.CheckBoxExchange(inputData, "BY_SHIPMENT");
		LGIssuesHandling.CheckBoxExchange(inputData, "BY_COMMODITY_TYPE");
		// ENDING HANDLING OUTPUT CHECKBOXBUTTON
		
		// STARTING HANDLING NUMBER FORMAT
		LGIssuesHandling.GetNumberFormater(inputData, "LOAN", "VN", 0);
		LGIssuesHandling.GetNumberFormater(inputData, "TOTAL_VALUE_ASSETS", "VN", 0);
		// ENDING HANDLING NUMBER FORMAT
		
		// STARTING GET BY NUMBER IN WORLD
		LGIssuesHandling.GetByNumberInWords(inputData, "LOAN", "BY_MONEY_LOAN");
		LGIssuesHandling.GetByNumberInWords(inputData, "TOTAL_VALUE_ASSETS", "BY_TOTAL_VALUE_ASSETS");
		// ENDING GET BY NUMBER IN WORLD
		
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
		
	}

}
