package com.helper;

import java.util.ArrayList;
import java.util.List;

import com.consts.Gendoc;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.si.exception.SIException;
import com.si.helper.ServiceHelper;

public class AddressHelper {
	private static String mergeAddress(String regexJoin,List<String> arr){		
		return String.join(regexJoin, arr);
	}
	public static String getMergerAddress(JsonArray listAddress,String type,String dataSourceName) throws SIException{
		String result = null;
		List<String> rs = new ArrayList<String>();
		if(listAddress != null && !listAddress.isEmpty() && listAddress.size() > 0){
			for (JsonElement address : listAddress) {
				if(type.equalsIgnoreCase(address.getAsJsonObject().get(Gendoc.LOAI_DIA_CHI).getAsString())){
					String dcct = (address.getAsJsonObject().has(Gendoc.propAddress[0]) && !address.getAsJsonObject().get(Gendoc.propAddress[0]).isJsonNull()) ? address.getAsJsonObject().get(Gendoc.propAddress[0]).getAsString() : null;
					if(dcct!=null && dcct.length() > 0){ rs.add(dcct);}
					String xa = ServiceHelper.convertMasterdataCodeToName("XA",(address.getAsJsonObject().has(Gendoc.propAddress[1]) && !address.getAsJsonObject().get(Gendoc.propAddress[1]).isJsonNull()) ? address.getAsJsonObject().get(Gendoc.propAddress[1]).getAsString() : null,dataSourceName);
					if(xa!=null && xa.length() > 0){ rs.add(xa);}
					String quan = ServiceHelper.convertMasterdataCodeToName("QUAN",(address.getAsJsonObject().has(Gendoc.propAddress[2]) && !address.getAsJsonObject().get(Gendoc.propAddress[2]).isJsonNull()) ? address.getAsJsonObject().get(Gendoc.propAddress[2]).getAsString() : null,dataSourceName);
					if(quan!=null && quan.length() > 0){ rs.add(quan);}
					String tinh = ServiceHelper.convertMasterdataCodeToName("TINH",(address.getAsJsonObject().has(Gendoc.propAddress[3])&& !address.getAsJsonObject().get(Gendoc.propAddress[3]).isJsonNull()) ? address.getAsJsonObject().get(Gendoc.propAddress[3]).getAsString() : null,dataSourceName);
					if(tinh!=null && tinh.length() > 0){ rs.add(tinh);}
					String quocgia = ServiceHelper.convertMasterdataCodeToName("QUOCGIA",(address.getAsJsonObject().has(Gendoc.propAddress[1]) && !address.getAsJsonObject().get(Gendoc.propAddress[4]).isJsonNull()) ? address.getAsJsonObject().get(Gendoc.propAddress[4]).getAsString() : null,dataSourceName);
					if(quocgia!=null && quocgia.length() > 0){ rs.add(quocgia);}
					break;
				}
			}
		}
		result=mergeAddress(", ", rs);
		return result;
	}
}
