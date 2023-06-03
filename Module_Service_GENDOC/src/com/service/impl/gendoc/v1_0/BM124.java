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

public class BM124 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM124(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		
		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");	
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		
		// STARTING HANDLING MULTIPLE TEMPLATES
		if(dependentRequired!=null || "".equals(dependentRequired)){
			if(BO.get("DEXUAT_STHAO_GN").getAsJsonObject().get("IS_BAOLANH").getAsInt()==1){
				LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_YCBL");
			} else if(BO.get("DEXUAT_STHAO_GN").getAsJsonObject().get("IS_LC").getAsInt()==1) {
				LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_YCLC");
			}
		}
		// ENDING HANDLING MULTIPLE TEMPLATES
		
		// CONFIGURATING AVIABLE PATH
		JsonArray DS_YCAU_BAOLANH = BO.get("TT_DEXUAT").getAsJsonObject().get("DS_YCAU_BAOLANH").getAsJsonArray();
		JsonArray DS_YCAU_LC = BO.get("TT_DEXUAT").getAsJsonObject().get("DS_YCAU_LC").getAsJsonArray();

		/*=================================== STARTING HANDLING MASTERDATA ===================================*/
		if(!DS_YCAU_BAOLANH.isEmpty()){
			String LOAIBAOLANH = convertMasterdataCodeToName("LOAITHUBL",DS_YCAU_BAOLANH.get(0).getAsJsonObject().has("LOAITHU_BLANH")? DS_YCAU_BAOLANH.get(0).getAsJsonObject().get("LOAITHU_BLANH").getAsString():"");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("LOAITHU_BLANH", LOAIBAOLANH);
			
//			String LOAITIEN_BL = convertMasterdataCodeToName("LOAITIEN",DS_YCAU_BAOLANH.get(0).getAsJsonObject().has("LOAITIEN")?DS_YCAU_BAOLANH.get(0).getAsJsonObject().get("LOAITIEN").getAsString():null);
//			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("LOAITIEN", LOAITIEN_BL);
			
			String NGAYHIEULUC_BL = convertMasterdataCodeToName("NGAYHIEULUC",DS_YCAU_BAOLANH.get(0).getAsJsonObject().has("NGAY_HIEULUC")?DS_YCAU_BAOLANH.get(0).getAsJsonObject().get("NGAY_HIEULUC").getAsString():null);
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAY_HIEULUC", NGAYHIEULUC_BL);
		} else {
			DS_YCAU_BAOLANH.add(new JsonObject());
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("LOAITHU_BLANH", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("LOAITIEN", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAY_HIEULUC", "");
			// Testing BAOLANH
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("SOTIEN", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("MUCDICH_BAOLANH", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGTHUHUONG", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHIEU_LUC_CUTHE", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHIEULUC_THEOSUKIEN", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHETHAN_CTHE", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHETHAN_CUTHE", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHETHAN_SUKIEN", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("KYQUY", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("PHI", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("PHI_TOITHIEU", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("DK_KHAC", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("CTU_DAYDU", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("CTIET_CTU_DAYDU", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("CTU_BOSUNG", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("CTIET_CTU_BOSUNG", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("PHUONGTHUC_THUPHI", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("LAN_THUPHI", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("CHITIET_THUPHI_KHAC", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("NGAYHETHAN_SKIEN", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("PHI_KHAC", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("DK_KHAC", "");
			DS_YCAU_BAOLANH.get(0).getAsJsonObject().addProperty("DK_NGOAILE", "");
		}
		
		if(!DS_YCAU_LC.isEmpty()){
			String LOAILC = convertMasterdataCodeToName("LOAILC",DS_YCAU_LC.get(0).getAsJsonObject().has("LOAI_LC")?DS_YCAU_LC.get(0).getAsJsonObject().get("LOAI_LC").getAsString():null);
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("LOAI_LC", LOAILC);
					
//			String LOAITIEN_LC = convertMasterdataCodeToName("LOAITIEN",DS_YCAU_LC.get(0).getAsJsonObject().has("LOAI_TIEN")?DS_YCAU_LC.get(0).getAsJsonObject().get("LOAI_TIEN").getAsString():null);
//			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("LOAI_TIEN", LOAITIEN_LC);
			
		} else {
			DS_YCAU_LC.add(new JsonObject());
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("LOAI_LC", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("LOAI_TIEN", "");
			// Testing BAOLANH
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("DUNG_SAI", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("TRI_GIA", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("MUCDICH_BAOLANH", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("NGUOI_THUHUONG", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("TU_NGAY", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("DEN_NGAY", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("KY_QUY", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("PHI_PHATHANH", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("DK_KHAC", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("CTU_CUNGCAP", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("CTIET_CTU_CUNGCAP", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("CTU_BOSUNG", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("CTIET_CTU_BOSUNG", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("PHUONGTHUC_THUPHI", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("DINHKY_THUPHI", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("CHITIET_THUPHI_KHAC", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("PHI_KHAC", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("DK_KHAC", "");
			DS_YCAU_LC.get(0).getAsJsonObject().addProperty("DK_NGOAILE", "");
		}
		/*=================================== ENDING HANDLING MASTERDATA ===================================*/
		
		//CONFIGURATING FROM_FIELDS
		BO.addProperty("BL_HOURS_DATE", "");
		BO.addProperty("BL_DURATION", "");
		BO.addProperty("TO_DATE_HOURS_DAY", "");
		BO.addProperty("RMTL_AGREE", "");
		BO.addProperty("RMTL_CANCEL_REASON", "");
		BO.addProperty("RMTL_CANCEL", "");
		BO.addProperty("RMTL_AGREE_CANCEL_DATE", "");
		BO.addProperty("CA_AGREE", "");
		BO.addProperty("CA_CANCEL", "");
		BO.addProperty("CA_AGREE_CANCEL_DATE", "");
		BO.addProperty("CA_CANCEL_REASON", "");
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
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		
		// STARTING HANDLING OUTPUT RADIOBUTTON
		LGIssuesHandling.RadioButtonExchange(inputData, "STABLE_OPERATION", "CHANGE_HAS_GENERATED");
		LGIssuesHandling.RadioButtonExchange(inputData, "EXIM_APPROVE", "EXIM_NOT_APPROVED");
		LGIssuesHandling.RadioButtonExchange(inputData, "EIB_GROUP_1", "EIB_OTHER_GROUPS");
		LGIssuesHandling.RadioButtonExchange(inputData, "COMMITMENT_NO", "COMMITMENT_YES");
		LGIssuesHandling.RadioButtonExchange(inputData, "BL_SUITABLE", "BL_INAPPROPRIATE");
		LGIssuesHandling.RadioButtonExchange(inputData, "BL_AGREE", "BL_DISAGREE");
		LGIssuesHandling.RadioButtonExchange(inputData, "BL_COLLECTED_ALL", "BL_PERIODIC_1", "BL_OTHER_1");
		LGIssuesHandling.RadioButtonExchange(inputData, "LC_COLLECTED_ALL", "LC_PERIODIC_1", "LC_OTHER_1");
		// ENDING HANDLING OUTPUT RADIOBUTTON
		
		// STARTING HANDLING OUTPUT CHECKBOXBUTTON
		LGIssuesHandling.CheckBoxExchange(inputData, "GUARANTEE");
		LGIssuesHandling.CheckBoxExchange(inputData, "LETTER_OF_CREDIT");
		LGIssuesHandling.CheckBoxExchange(inputData, "BL_DOCUMENT_1");
		LGIssuesHandling.CheckBoxExchange(inputData, "LC_DOCUMENT");
		LGIssuesHandling.CheckBoxExchange(inputData, "ADDITIONAL_DOCUMENTS");
		LGIssuesHandling.CheckBoxExchange(inputData, "LC_ADDITIONAL_DOCUMENTS");
		LGIssuesHandling.CheckBoxExchange(inputData, "BL_THE_EXACT_DAY");
		LGIssuesHandling.CheckBoxExchange(inputData, "NO_DAYS_1");
		LGIssuesHandling.CheckBoxExchange(inputData, "SPECIFIC_EXPIRY_DATE_1");
		
		// ENDING HANDLING OUTPUT CHECKBOXBUTTON
		
		// STARTING HANDLING NUMBER FORMAT
		LGIssuesHandling.GetNumberFormater(inputData, "BL_EXCHANGE_RATE", "EN", 2);
		LGIssuesHandling.GetNumberFormater(inputData, "LC_EXCHANGE_RATE", "EN", 2);
		// STARTING HANDLING NUMBER FORMAT
		
		// STARTING HANDLING OUTPUT BUSINESS LOGIC
//		LGIssuesHandling.FullDateFormater(inputData, inputDataCFG, "RM_CONFIRM_DATE");

		// ENDING HANDLING OUTPUT BUSINESS LOGIC
		
		// STARTING MULTIPLE TEMPLATES		
		if(inputDataCFG.get("GUARANTEE").getAsString()=="CHECK" && inputDataCFG.get("LETTER_OF_CREDIT").getAsString()=="CHECK"){
			
			JsonArray generalInputCFG = new JsonArray();
			inputDataCFG.addProperty("GUARANTEE", "CHECK");
			inputDataCFG.addProperty("LETTER_OF_CREDIT", "UNCHECK");
			generalInputCFG.add(inputDataCFG.toString());
			
			inputDataCFG.addProperty("GUARANTEE", "UNCHECK");
			inputDataCFG.addProperty("LETTER_OF_CREDIT", "CHECK");
			generalInputCFG.add(inputDataCFG.toString());
			
			JsonObject inputDataBLCFG = new Gson().fromJson(generalInputCFG.get(0).getAsString(), JsonObject.class);
			JsonObject inputDataLCCFG = new Gson().fromJson(generalInputCFG.get(1).getAsString(), JsonObject.class);

			JsonArray InputDataBL = new JsonArray();
			InputDataBL.add(inputDataBLCFG);
			JsonArray InputDataLC = new JsonArray();
			InputDataLC.add(inputDataLCCFG);
			
			JsonObject dataBL = new JsonObject();
			dataBL.addProperty("TemplateFileName", super.data.getAsJsonObject().get("TemplateFileName").getAsString());
			dataBL.addProperty("QRCodeEncodeString", super.data.getAsJsonObject().get("QRCodeEncodeString").getAsString());
			dataBL.addProperty("InputType", super.data.getAsJsonObject().get("InputType").getAsString());
			dataBL.addProperty("ECMProperties", super.data.getAsJsonObject().get("ECMProperties").getAsString());
			dataBL.addProperty("OutputFileName", super.data.getAsJsonObject().get("OutputFileName").getAsString());
			dataBL.addProperty("ECMDocumentClass", super.data.getAsJsonObject().get("ECMDocumentClass").getAsString());
			dataBL.addProperty("ECMFolderPath", super.data.getAsJsonObject().get("ECMFolderPath").getAsString());
			dataBL.add("InputData", InputDataBL);
			
			JsonObject dataLC = new JsonObject();
			dataLC.addProperty("TemplateFileName", super.data.getAsJsonObject().get("TemplateFileName").getAsString());
			dataLC.addProperty("QRCodeEncodeString", super.data.getAsJsonObject().get("QRCodeEncodeString").getAsString());
			dataLC.addProperty("InputType", super.data.getAsJsonObject().get("InputType").getAsString());
			dataLC.addProperty("ECMProperties", super.data.getAsJsonObject().get("ECMProperties").getAsString());
			dataLC.addProperty("OutputFileName", super.data.getAsJsonObject().get("OutputFileName").getAsString());
			dataLC.addProperty("ECMDocumentClass", super.data.getAsJsonObject().get("ECMDocumentClass").getAsString());
			dataLC.addProperty("ECMFolderPath", super.data.getAsJsonObject().get("ECMFolderPath").getAsString());
			dataLC.add("InputData", InputDataLC);
			
			dataBL.getAsJsonObject().addProperty(Gendoc.inpuData, InputDataBL.toString());
			JsonElement IDBL = genDocInvoker.invoke(dataBL);
			dataLC.getAsJsonObject().addProperty(Gendoc.inpuData, InputDataLC.toString());
			JsonElement IDLC = genDocInvoker.invoke(dataLC);
			
			JsonArray IDCollection = new JsonArray();
			IDCollection.add(IDBL);
			IDCollection.add(IDLC);
			super.data = IDCollection;
			
		} else {
			//convert InputData to JSON String
			super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
			JsonElement ID = genDocInvoker.invoke(super.data);
			super.data = ID;
		}
		// ENDING MULTIPLE TEMPLATES
		
		System.err.println(inputData.get(0).getAsJsonObject());
		
		//convert InputData to JSON String
//		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
//		JsonElement ID = genDocInvoker.invoke(super.data);
//		super.data = ID;
	}

}
