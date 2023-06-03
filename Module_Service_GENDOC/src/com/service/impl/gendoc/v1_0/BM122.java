package com.service.impl.gendoc.v1_0;

import java.text.NumberFormat;
import java.util.Locale;

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

public class BM122 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	
	public BM122(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
		genDocInvoker  = new GenDocInvoker(docName, genDocEnv, processDataSource, log);
		super.invoker = genDocInvoker;
		super.isMergeData = true;
		super.isManualMapping_Input =true;
		super.isManualInvoke =true;
		this.docName = docName;

	}

	@Override
	public void businessMapping_Input() throws SIException {
		
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		
		String dependentRequired = super.data.getAsJsonObject().has("dependentRequired") 
				&& !super.data.getAsJsonObject().get("dependentRequired").isJsonNull() 
				? super.data.getAsJsonObject().get("dependentRequired").getAsString(): "";

		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		
		// STARTING HANDLING MULTIPLE TEMPLATES
//		dependentRequired = "{\"DIR\":\"#.data.TT_DEXUAT.DS_YCAU_GN\",\"ID_HOPDONG\":\"GN1685248418990\"}";
		if(dependentRequired != null || "".equals(dependentRequired)){
			LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_YCGN");
		}
		// ENDING HANDLING MULTIPLE TEMPLATES
		
		System.err.println(BO);
		
		// CONFIGURATING AVIABLE PATH
		JsonArray DS_YCAU_GN = BO.get("TT_DEXUAT").getAsJsonObject().get("DS_YCAU_GN").getAsJsonArray();
		
		// STARTING HANDLING DS_CHUYENKHOAN
		if(!DS_YCAU_GN.isEmpty()) {
			JsonArray DS_CHUYENKHOAN = DS_YCAU_GN.get(0).getAsJsonObject().get("DS_CHUYENKHOAN").getAsJsonArray();
			JsonArray tempInfo = new JsonArray();
	        
			String DBM_TRANS_METHOD = "";
			String BENEFICIARYS_NAME = "";
			String BENEFICIARYS_ACC = "";
			String BENEFICIARYS_ACC_PLACE = "";
			String BENEFICIARYS_LOAN = "";
			
			for(JsonElement beInfoEle : DS_CHUYENKHOAN) {
				Locale localeVN = new Locale("vi", "VN");
		        NumberFormat vn = NumberFormat.getInstance(localeVN);
		        
		        if (LGIssuesHandling.CheckElementInJsonObject(beInfoEle.getAsJsonObject(), "PHUONGTHUC")){
		        	DBM_TRANS_METHOD = convertMasterdataCodeToName("PTCHUYENKHOAN",beInfoEle.getAsJsonObject().get("PHUONGTHUC").getAsString());
		        }
		        if (LGIssuesHandling.CheckElementInJsonObject(beInfoEle.getAsJsonObject(), "TENTHUHUONG")){
		        	BENEFICIARYS_NAME = beInfoEle.getAsJsonObject().get("TENTHUHUONG").getAsString();
		        }
		        if (LGIssuesHandling.CheckElementInJsonObject(beInfoEle.getAsJsonObject(), "STK_THU_HUONG")){
		        	BENEFICIARYS_ACC = beInfoEle.getAsJsonObject().get("STK_THU_HUONG").getAsString();
		        }
		        if (LGIssuesHandling.CheckElementInJsonObject(beInfoEle.getAsJsonObject(), "TCTD")){
		        	BENEFICIARYS_ACC_PLACE = convertMasterdataCodeToName("TCTD",beInfoEle.getAsJsonObject().get("TCTD").getAsString());
		        }
		        if (LGIssuesHandling.CheckElementInJsonObject(beInfoEle.getAsJsonObject(), "SOTIEN_VND")){
		        	BENEFICIARYS_LOAN = beInfoEle.getAsJsonObject().get("SOTIEN_VND").getAsString();
		        }

				tempInfo.add(String.format("%s cho người thụ hưởng %s vào tài khoản số %s tại %s, số tiền %s"
				 		, DBM_TRANS_METHOD
						, BENEFICIARYS_NAME
						, BENEFICIARYS_ACC
						, BENEFICIARYS_ACC_PLACE
						, vn.format(Double.parseDouble(BENEFICIARYS_LOAN))));
			}
			if(!tempInfo.isEmpty()){
				StringBuilder strValueBenefit = new StringBuilder("- " + tempInfo.getAsJsonArray().get(0).getAsString());
				for(int i=1; i< tempInfo.size(); i++) {
					strValueBenefit.append("\n- " + tempInfo.getAsJsonArray().get(i).getAsString());
				}		
				BO.addProperty("TABLE_INFO_BENEFICIARYS", strValueBenefit.toString());
			} else {
				BO.addProperty("TABLE_INFO_BENEFICIARYS", "");
			}
		}		
		// ENDING HANDLING DS_CHUYENKHOAN
		
		BO.addProperty("DBM_ACCEPT", "");
		BO.addProperty("DBM_REJECT", "");
		BO.addProperty("DBM_REJECT_RESON", "");
		BO.addProperty("CA_ACTION_DATE", "");
		BO.addProperty("CA_CN_REJECT", "");
		BO.addProperty("CA_CN_ACCEPT", "");
		BO.addProperty("CA_CN_REJECT_REASON", "");
		BO.addProperty("RMTL_AGREE_CANCEL_DATE", "");
		BO.addProperty("RMTL_ACTION_DATE", "");
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

		// STARTING HANDLING OUTPUT RADIOBUTTON
		LGIssuesHandling.RadioButtonExchange(inputData, "STABLE_OPERATION", "CHANGE_HAS_GENERATED");
		LGIssuesHandling.RadioButtonExchange(inputData, "EXIM_APPROVE", "EXIM_NOT_APPROVED");
		LGIssuesHandling.RadioButtonExchange(inputData, "EIB_GROUP_1", "EIB_OTHER_GROUPS");
		LGIssuesHandling.RadioButtonExchange(inputData, "COMMITMENT_NO", "COMMITMENT_YES");
		LGIssuesHandling.RadioButtonExchange(inputData, "BL_SUITABLE", "BL_INAPPROPRIATE");
		LGIssuesHandling.RadioButtonExchange(inputData, "BL_AGREE", "BL_DISAGREE");
		LGIssuesHandling.RadioButtonExchange(inputData, "A_LOT_TIMES", "END_OF_TERM", "OTHER_PRINCP_PAY_TERM");
		LGIssuesHandling.RadioButtonExchange(inputData, "MONTH_INTEREST_PAY", "O_INS_INTEREST_PAY");
		// ENDING HANDLING OUTPUT RADIOBUTTON
		
		// STARTING HANDLING OUTPUT CHECKBOXBUTTON
		LGIssuesHandling.CheckBoxExchange(inputData, "PROVIDED_DOCUMENT");
		LGIssuesHandling.CheckBoxExchange(inputData, "DOC_AFTER_DBM");
		LGIssuesHandling.CheckBoxExchange(inputData, "PAY_IN_CASH");
		LGIssuesHandling.CheckBoxExchange(inputData, "DBM_TRANSFER");
		// ENDING HANDLING OUTPUT CHECKBOXBUTTON
		
		// STARTING HANDLING NUMBER FORMAT
		LGIssuesHandling.GetNumberFormater(inputData, "DXGN_EXCHANGE_RATE", "EN", 2);
		// STARTING HANDLING NUMBER FORMAT
		
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
		
	}

}
