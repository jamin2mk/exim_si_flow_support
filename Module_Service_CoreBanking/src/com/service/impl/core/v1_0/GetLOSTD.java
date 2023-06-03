package com.service.impl.core.v1_0;

import java.text.MessageFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.getlostd.req.v_1_0.GetLOSTDRequest;
import com.model.getlostd.res.v_1_0.GetLOSTDResponse;
import com.model.getlostd.res.v_1_0.InforQHTD;
import com.si.consts.DATE_PATTERN;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class GetLOSTD extends SI_Service{

	protected InforQHTD inforQHTD = null;
	
	public GetLOSTD(String data, boolean isMergeData, String siVersion,
			String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Output = true;
		super.isManualMapping_Input = true;
//		super.isManualInvoke = true;
		super.invoker = new CoreBankingInvoker(serviceConfig, processDataSource, GetLOSTDRequest.class, GetLOSTDResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		
		super.invoked_input = new Gson().fromJson(super.data, JsonObject.class);
		
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub
//		this.invoked_output = super.gson.fromJson(json, JsonObject.class);
	}

	@Override
	public void check_invokeResult() throws SIException {
		// TODO Auto-generated method stub
		GetLOSTDResponse getLOSTDResponse = super.gson.fromJson(super.invoked_output, GetLOSTDResponse.class);
		this.inforQHTD = getLOSTDResponse.getInfoQHTD();
		
		if(this.inforQHTD!=null){
			if(!this.inforQHTD.getErrorDetail().getErrorCode().equalsIgnoreCase("0")){
				throw new SIException(String.format("SV-%s", this.inforQHTD.getErrorDetail().getErrorCode()),
						this.inforQHTD.getErrorDetail().getErrorDesc());
			}
		}else{
			throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE, super.log.getSystem().getTo(), super.log.getService()));
		}
		String sol ="";
		try {
			sol =this.invoked_input.getAsJsonObject().get("sol").getAsString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new SIException(Error.DATA_99, "SOL null!!!");
		}
		JsonArray arrc = new JsonArray();
		try {
			JsonArray arr = super.invoked_output.getAsJsonObject().get("inforQHTD").getAsJsonObject().get("lavlist").getAsJsonObject().get("listLAVInfor").getAsJsonArray();
			Date date = new Date();
			
			for (JsonElement e : arr) {
				JsonObject o = e.getAsJsonObject();
				if(sol.equals(o.get("limitSOLID").getAsString())){
					try {
						Date lavDate = DateHelper.convertStringToDate(o.get("limExpDate").getAsString(), DATE_PATTERN.SLASH_DD_MM_YYYY);
						if(!date.after(lavDate)){
							arrc.add(e);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		JsonObject lavlist = new JsonObject();
		lavlist.add("listLAVInfor", arrc);
		super.invoked_output.getAsJsonObject().get("inforQHTD").getAsJsonObject().add("lavlist", lavlist);
	}
	

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
//		super.data = super.invoked_output;
		//get lavlist for BOTT
		super.data = super.invoked_output.getAsJsonObject().get("inforQHTD").getAsJsonObject().get("lavlist");
		
		
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub
		
	}
	

}
