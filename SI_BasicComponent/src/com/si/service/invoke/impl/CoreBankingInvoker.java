package com.si.service.invoke.impl;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.Log;
import com.si.exception.SIException;
import com.si.helper.LogHelper;
import com.si.helper.ServiceHelper;
import com.si.helper.XmlHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.invoke.Invoker;

public class CoreBankingInvoker implements Invoker {

	ServiceConfig serviceConfig;
	String dataSourceName;
	SI_Log log;
	boolean isMerged;

	Auto_Mapping mapper = new Auto_Mapping();
	Gson gson = new GsonBuilder().serializeNulls().create();

	protected Class<?> requestType;
	protected Class<?> responseType;

	public CoreBankingInvoker(ServiceConfig serviceConfig, String dataSourceName, Class<?> requestType, Class<?> responseType, SI_Log log) {

		this.serviceConfig = serviceConfig;
		this.dataSourceName = dataSourceName;
		this.log = log;
//		this.isMerged = isMerged;

		this.requestType = requestType;
		this.responseType = responseType;
	}

	@Override
	public JsonElement invoke(JsonElement input) throws SIException {

		// generate body-request msg
		Object requestObject = new Gson().fromJson(input, this.requestType);
		String bodyRequest_msg = XmlHelper.convertObjectToXml(this.requestType, requestObject);

		// generate request msg
		String request_msg = ServiceHelper.generateRequest(this.serviceConfig.getUsername(), this.serviceConfig.getPassword(), bodyRequest_msg);

		this.log.setToInput(request_msg);
		LogHelper.writeLog(Log.INFO, Log.TO_INPUT, this.log.getService(), request_msg);

		// send request
		String response_msg;
		try {
			response_msg = ServiceHelper.invokeService(this.serviceConfig.getUrl(), request_msg);
//			response_msg = "<?xml version=\"1.0\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">  <soap:Header xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:alsb=\"http://www.alsb.com/\">    <alsb:HeaderIn>      <UserName>IB</UserName>      <Password>abcd1234</Password>    </alsb:HeaderIn>  </soap:Header>  <soapenv:Body>    <alsb:GetLOSTDResponse xmlns:alsb=\"http://www.alsb.com/\">      <fix:InforQHTD xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:fix=\"http://www.finacle.com/fixml\">        <fix:Details>          <fix:OpenDate>15/09/2010</fix:OpenDate>          <fix:soTheActive>2</fix:soTheActive>          <fix:NgayQHTD>07/03/2019</fix:NgayQHTD>          <fix:NhomNoCif>001</fix:NhomNoCif>          <fix:SoNgayQH>0</fix:SoNgayQH>          <fix:DSGiaiNgan>0</fix:DSGiaiNgan>          <fix:DSGiaiNgan1>0</fix:DSGiaiNgan1>          <fix:DSGiaiNgan2>0</fix:DSGiaiNgan2>          <fix:DSThuNo>6600159</fix:DSThuNo>          <fix:DSThuNo1>40385332</fix:DSThuNo1>          <fix:DSThuNo2>42860831</fix:DSThuNo2>        </fix:Details>        <fix:LAVLIST>          <fix:ListLAVInfor>            <fix:SOHOPDONG>2235LAV190017614</fix:SOHOPDONG>            <fix:LIMITSOLID>2235</fix:LIMITSOLID>            <fix:NGAYMOHD>07/03/2019</fix:NGAYMOHD>            <fix:LIMEXPDATE>07/03/2025</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD>TUNGLAN</fix:PTCAPTD>            <fix:LOAICAPTD>DAIHAN</fix:LOAICAPTD>            <fix:HANMUC>200000000</fix:HANMUC>            <fix:HMKHADUNG>135540000</fix:HMKHADUNG>            <fix:DUNO>64460000</fix:DUNO>            <fix:LSBQ>8.5</fix:LSBQ>            <fix:HTCAPTD>04LL</fix:HTCAPTD>            <fix:TSDB/>            <fix:CAPPHEDUYET>GIÁM ĐỐC ĐVKD</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>2235LAV190114650</fix:SOHOPDONG>            <fix:LIMITSOLID>2235</fix:LIMITSOLID>            <fix:NGAYMOHD>29/10/2019</fix:NGAYMOHD>            <fix:LIMEXPDATE>31/10/2025</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD>HANMUC</fix:PTCAPTD>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>30000000</fix:HANMUC>            <fix:HMKHADUNG>29500000</fix:HMKHADUNG>            <fix:DUNO>500000</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD>09CC</fix:HTCAPTD>            <fix:TSDB/>            <fix:CAPPHEDUYET>GIÁM ĐỐC ĐVKD</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV220067585</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD>02SL</fix:HTCAPTD>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV230013390</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD>02SL</fix:HTCAPTD>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV230013391</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD>02SL</fix:HTCAPTD>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV230067740</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD/>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV230067741</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD/>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV230013392</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD/>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>          <fix:ListLAVInfor>            <fix:SOHOPDONG>1504LAV230013441</fix:SOHOPDONG>            <fix:LIMITSOLID>1000</fix:LIMITSOLID>            <fix:NGAYMOHD>03/01/2023</fix:NGAYMOHD>            <fix:LIMEXPDATE>03/12/2023</fix:LIMEXPDATE>            <fix:CCY>VND</fix:CCY>            <fix:PTCAPTD/>            <fix:LOAICAPTD>NGANHAN</fix:LOAICAPTD>            <fix:HANMUC>150000000</fix:HANMUC>            <fix:HMKHADUNG>0</fix:HMKHADUNG>            <fix:DUNO>0</fix:DUNO>            <fix:LSBQ>0</fix:LSBQ>            <fix:HTCAPTD/>            <fix:TSDB/>            <fix:CAPPHEDUYET>PHÓ PHÒNG TÍN DỤNG</fix:CAPPHEDUYET>            <fix:MAGDLOS/>            <fix:MASTLOS/>          </fix:ListLAVInfor>        </fix:LAVLIST>        <fix:ERRORDETAIL>          <fix:ERRORCODE>0</fix:ERRORCODE>          <fix:ERRORDESC>Thành công</fix:ERRORDESC>        </fix:ERRORDETAIL>      </fix:InforQHTD>    </alsb:GetLOSTDResponse>    <ns2:GetLOSTD xmlns:ns2=\"http://www.alsb.com/\">      <Cif>106058979</Cif>    </ns2:GetLOSTD>  </soapenv:Body></soapenv:Envelope>";
		} catch (Exception e) {
			throw new SIException(Error.SV_99, "Error in invoking taget system", e);
		}

		this.log.setToOutput(response_msg);
		LogHelper.writeLog(Log.INFO, Log.TO_OUTPUT, this.log.getService(), response_msg);

		// customize response for return
		String dataResponse_xml = XmlHelper.fetchSubElementAsString(response_msg, String.format("alsb:%s", responseType.getSimpleName()));
		Object dataResponse_object;
		try {
			dataResponse_object = XmlHelper.convertXmlToObject(responseType, dataResponse_xml);
		} catch (Exception e) {
			throw new SIException(Error.EX_99, "Error in convert data-response xml", e);
		}

		JsonElement invoked_output = gson.toJsonTree(dataResponse_object);
		return invoked_output;
	}	

	public String invoke(JsonElement input, String responseSubElementName) throws SIException {

		// generate body-request msg
		Object requestObject = new Gson().fromJson(input, this.requestType);
		String bodyRequest_msg = XmlHelper.convertObjectToXml(this.requestType, requestObject);

		// generate request msg
		String request_msg = ServiceHelper.generateRequest(this.serviceConfig.getUsername(), this.serviceConfig.getPassword(), bodyRequest_msg);

		this.log.setToInput(request_msg);
		LogHelper.writeLog(Log.INFO, Log.TO_INPUT, this.log.getService(), request_msg);

		// send request
		String response_msg;
		try {
			response_msg = ServiceHelper.invokeService(this.serviceConfig.getUrl(), request_msg);
//			response_msg = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><SendSMSResponse xmlns=\"http://tempuri.org/\"><SendSMSResult>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;&lt;SMSResponse&gt;&lt;ReferenceNo&gt;f159471f4b694ee1aab9f75c16570f35&lt;/ReferenceNo&gt;&lt;ErrorCode&gt;0&lt;/ErrorCode&gt;&lt;ErrorDesc&gt;Successful&lt;/ErrorDesc&gt;&lt;ResponseTime&gt;2023-05-05 10:10:52&lt;/ResponseTime&gt;&lt;/SMSResponse&gt;</SendSMSResult></SendSMSResponse></soap:Body></soap:Envelope>";

		} catch (Exception e) {
			throw new SIException(Error.SV_99, "Error in invoking taget system", e);
		}

		this.log.setToOutput(response_msg);
		LogHelper.writeLog(Log.INFO, Log.TO_OUTPUT, this.log.getService(), response_msg);

		String responseContent = XmlHelper.fetchTextFromElement("SendSMSResult", response_msg);
		String customizeContent = customizeContent(responseContent);

		return XmlHelper.fetchSubElementAsString(customizeContent, responseSubElementName);
	}

	@Override
	public JsonElement automap_input(JsonElement data) throws SIException {

		JsonObject fromData = data.getAsJsonObject();
		JsonObject toData = new JsonObject();

		try {
			String subjectCode = String.format("%s.IN", this.log.getService());
			JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);
			this.mapper.start_mapping_bo(fromData, toData, map_info);

		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in mapping input", e);
		}
		return toData;
	}

	@Override
	public JsonElement automap_output(JsonElement output, JsonElement data) throws SIException {

		JsonObject fromData = output.getAsJsonObject();
		JsonObject toData = data.getAsJsonObject();

		try {
			String subjectCode = String.format("%s.OUT", this.log.getService());
			JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);

			if (map_info.size() > 0) {
				this.mapper.start_mapping_bo(fromData, toData, map_info);
			} else {
				throw new SIException(Error.MAP_99, "Error in mapping output. Cause by: Has no record for mapping");
			}
		} catch (SIException e) {
			throw e;
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in mapping output", e);
		}
		return toData;
	}

	public String customizeContent(String content) {
		return content.replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&");
	}
}
