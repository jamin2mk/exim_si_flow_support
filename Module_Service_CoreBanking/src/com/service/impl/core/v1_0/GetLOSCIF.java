package com.service.impl.core.v1_0;

import java.text.MessageFormat;


import Const.TYPE;

import com.consts.Service;
import com.google.gson.JsonObject;
import com.model.getloscif.req.v_1_0.GetLOSCIFRequest;
import com.model.getloscif.res.v_1_0.CifDetail;
import com.model.getloscif.res.v_1_0.GetLosCifResponse;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class GetLOSCIF extends SI_Service {

	protected CifDetail cifDetail = null;

	protected boolean isHandling = false;
	
	public GetLOSCIF(String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {

		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		super.isManualMapping_Output = true;
		super.isManualMapping_Input = true;
		super.invoker = new CoreBankingInvoker(serviceConfig, processDataSource, GetLOSCIFRequest.class, GetLosCifResponse.class, log);
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		

//		if(this.data.getAsJsonObject().get("isHandling")!=null){
//			this.isHandling = this.data.getAsJsonObject().get("isHandling").getAsBoolean();
//		}
//		
		
		this.invoked_input = this.data;
//		
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

		GetLosCifResponse getRLOSCIFResponse = super.gson.fromJson(invoked_output, GetLosCifResponse.class);
		this.cifDetail = getRLOSCIFResponse.getCifDetail();

		if (this.cifDetail != null) {
			if (!this.cifDetail.getErrcode().equals("0")) {
				throw new SIException(String.format("SV-%s", this.cifDetail.getErrcode()), this.cifDetail.getErrDesc());
			}
		} else {
			throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE, super.log.getSystem().getTo(), super.log.getService()));
		}
		
		String loaiKH = "";
		try {
			loaiKH= this.invoked_input.getAsJsonObject().get("loaiKH").getAsString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new SIException(Error.DATA_99, "loaiKH null!!!");
		}
		
		GetLOSCIFRequest request = super.gson.fromJson(this.invoked_input, GetLOSCIFRequest.class);

		boolean check = false;

		if(null!=request.getId()){
			if(!request.getId().trim().equals("")){
				for(int i =0;i<cifDetail.getDocument().size();i++){
					if(request.getId().equals(cifDetail.getDocument().get(i).getDocId())){
						check = true;
						break;
					}
				}
				if(!check){
					throw new SIException(Error.DATA_99, "Không tìm thấy dữ liệu đáp ứng điều kiện.");
				}
			}
			
		} else if(null!=request.getCif()){
			if(request.getCif().trim().equals("")){
				if(!request.getCif().equals(cifDetail.getCif())){
					throw new SIException(Error.DATA_99, "Không tìm thấy dữ liệu đáp ứng điều kiện.");
				}
			}
			
		}
//		if(request.get)
		
		if(!loaiKH.equals("")){
			switch(loaiKH){
			case "CN":
				loaiKH = "CANHAN";
				break;
			case "DN":
				loaiKH = "DOANHNGHIEP";
				break;
			}
			if(!cifDetail.getLoaiKH().equals(loaiKH)){
				throw new SIException(Error.DATA_99, "loaiKH khong dung!!!");
			}
		}
		
		
		
	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		
		// convert to masterdata code
		JsonObject convertedData = super.gson_SerializeNulls.toJsonTree(this.cifDetail).getAsJsonObject();
		super.convertMasterdataCode(Service.GetLOSCIF, TYPE.TO_RLOS, convertedData);
		super.invoked_output = convertedData;


	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
		super.data = super.invoked_output;
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub

	}

}
