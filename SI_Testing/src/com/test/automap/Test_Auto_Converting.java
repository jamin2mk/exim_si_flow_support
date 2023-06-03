package com.test.automap;

import Const.TYPE;
import Handler.Auto_Converting;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Test_Auto_Converting {

	public static void main(String[] args) {
		
		String convert_info_arr = "[{\"field_mapping\":\"address.loaiDiaChi;khlq.KHLQLoaiDiaChi\",\"masterdata_code\":\"HKTHUONGTRU\",\"target_system_code\":\"Home\"},{\"field_mapping\":\"address.loaiDiaChi;khlq.KHLQLoaiDiaChi\",\"masterdata_code\":\"DCLIENHE\",\"target_system_code\":\"Mailing\"},{\"field_mapping\":\"address.loaiDiaChi;khlq.KHLQLoaiDiaChi\",\"masterdata_code\":\"DCDKKD\",\"target_system_code\":\"Registered\"}]";
		JsonArray convert_info = new Gson().fromJson(convert_info_arr, JsonArray.class);
		
		String data = "{\"cif\":\"116997630\",\"tenKh\":\"116997630\",\"gioiTinh\":\"M\",\"ngaySinh\":\"09/08/1976\",\"noiSinh\":\"\",\"quocTich\":\"VN\",\"danToc\":\"01\",\"ttHonNhan\":\"99\",\"khDen\":\"2\",\"cuTru\":\"1\",\"kCapTinDung\":\"N\",\"hanChe\":\"\",\"vip\":\"2\",\"loaiKH\":null,\"dsaid\":null,\"acctmngrid\":null,\"maNVEIB\":\"\",\"salutation\":null,\"document\":[{\"docCode\":\"CMND\",\"docId\":\"CMND_116997630\",\"docNgayCap\":\"20/07/2012\",\"docQuocGia\":\"VN\",\"docNoiCap\":\"00260\"}],\"phone\":[{\"dtdd\":\"116997630\",\"dtcd\":null}],\"address\":[{\"quocGiaDiaChi\":\"VN\",\"thanhPhoDiaChi\":\"22\",\"diaChi\":\"ADDR1ADDR2ADDR3\",\"hieuLucDiaChi\":\"30/06/2016\",\"loaiDiaChi\":\"Mailing\"},{\"quocGiaDiaChi\":\"VN\",\"thanhPhoDiaChi\":\"22\",\"diaChi\":\"ADDR1ADDR2ADDR3\",\"hieuLucDiaChi\":\"30/06/2016\",\"loaiDiaChi\":\"Home\"}],\"khlq\":[{\"KHLQCif\":\"116997678\",\"KHLQTen\":\"116997678\",\"KHLQngaySinh\":\"06/12/1979\",\"KHLQloaiID\":\"CCUOC\",\"KHLQID\":\"CCUOC_116997678\",\"KHLQNgayCap\":\"29/03/2016\",\"KHLQQuocGiaCap\":\"VN\",\"KHLQNoiCap\":\"01070\",\"KHLQDtdd\":\"84942058678\",\"KHLQDtcd\":\"\",\"KHLQEmail\":\"\",\"KHLQQuocGiaDiaChi\":\"VN\",\"KHLQThanhPhoDiaChi\":\"22\",\"KHLQDDiaChi\":\"ADDR1ADDR2ADDR3\",\"KHLQThoiDiem\":\"30/06/2016\",\"KHLQLoaiKH\":null,\"KHLQLoaiDiaChi\":\"Home\"},{\"KHLQCif\":\"116997719\",\"KHLQTen\":\"116997719\",\"KHLQngaySinh\":\"01/01/1951\",\"KHLQloaiID\":\"CMND\",\"KHLQID\":\"CMND_116997719\",\"KHLQNgayCap\":\"22/08/2008\",\"KHLQQuocGiaCap\":\"VN\",\"KHLQNoiCap\":\"00260\",\"KHLQDtdd\":\"\",\"KHLQDtcd\":\"\",\"KHLQEmail\":\"\",\"KHLQQuocGiaDiaChi\":\"VN\",\"KHLQThanhPhoDiaChi\":\"22\",\"KHLQDDiaChi\":\"ADDR1ADDR2ADDR3\",\"KHLQThoiDiem\":\"30/06/2016\",\"KHLQLoaiKH\":null,\"KHLQLoaiDiaChi\":\"Home\"}],\"email\":null,\"errcode\":\"0\",\"errDesc\":\"Th�nh c�ng\"}";
		JsonObject data_json = new Gson().fromJson(data, JsonObject.class);
		Auto_Converting convertor = new Auto_Converting();
		System.out.println("before: " + data_json);
		convertor.start_converting_bo(data_json, TYPE.TO_RLOS, convert_info);		
		System.out.println("after: " +data_json);
	}
}
