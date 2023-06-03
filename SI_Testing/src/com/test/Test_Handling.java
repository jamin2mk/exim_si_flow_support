package com.test;

import com.google.gson.JsonObject;
import com.si.model.sql.SqlStatement;

public class Test_Handling {

	public static void main(String[] args) {
		JsonObject a = new JsonObject();
		a.addProperty("A", 1);
		System.out.println(a.get("A").getAsString());
	}

	public static void toDoWithMultiParams(String a, String b, String... params) {
		System.out.println(params.getClass());
		System.out.println(new SqlStatement(a, params, b).toString());
	}
}
