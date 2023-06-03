package com.helper;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestHelper {

	public static JsonObject loadLosProcess() {

		JsonObject los_process = null;
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("resources/los_process.json"));
			los_process = new Gson().fromJson(reader, JsonObject.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return los_process;
	}

	public static JsonObject loadLosProcess(String path) {

		JsonObject los_process = null;
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(path));
			los_process = new Gson().fromJson(reader, JsonObject.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return los_process;
	}

	public static JsonArray loadMapInfo() {

		JsonArray map_info = null;
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("resources/map_info.json"));
			map_info = new Gson().fromJson(reader, JsonArray.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map_info;
	}

	public static void main(String[] args) {
		// JSON strings
		String jsonExpected_str = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
		String jsonRetest_str = "{\"name\":\"John\", \"age\":30, \"country\":\"USA\", \"city\":\"New York\"}";

		// Convert JSON strings to JsonObject
		JsonObject jsonExpected = new Gson().fromJson(jsonExpected_str, JsonObject.class);
		JsonObject jsonRetest = new Gson().fromJson(jsonRetest_str, JsonObject.class);

		// Compare the JsonObject and list differing fields
		checkDifferingFields(jsonExpected, jsonRetest);

	}
	
	public static void compareGendocResult(String dir) {
		JsonObject jsonExpected = loadLosProcess(dir+"/result_expected.json");
		JsonObject jsonRetest = loadLosProcess(dir+"/result_actual.json");
		
		System.out.println("--<>-- Checking round 1 --<>--");
		checkDifferingFields(jsonExpected, jsonRetest);
		
		System.out.println("--<>-- Checking round 2 --<>--");
		JsonObject jsonExpected_2 = new Gson().fromJson(jsonExpected.get("InputData").getAsString(), JsonArray.class).get(0).getAsJsonObject();
		JsonObject jsonRetest_2 = new Gson().fromJson(jsonRetest.get("InputData").getAsString(), JsonArray.class).get(0).getAsJsonObject();
		checkDifferingFields(jsonExpected_2, jsonRetest_2);
	}

	public static void checkDifferingFields(JsonObject jsonExpected, JsonObject jsonRetest) {

		if (jsonExpected.equals(jsonRetest)) {
			System.out.println("Result: MATCHED");
		} else {
			List<String> missingFields = new ArrayList<>();
			List<String> extraFields = new ArrayList<>();
			Map<String, List<JsonElement>> differingFields = new HashMap<>();

			for (Entry<String, JsonElement> entry : jsonExpected.entrySet()) {

				String key = entry.getKey();
				JsonElement value = entry.getValue();

				JsonElement valueRetest = jsonRetest.get(key);

				if (valueRetest == null) {
					missingFields.add(key);

				} else if (!valueRetest.equals(value)) {

					List<JsonElement> values = new ArrayList<>();
					values.add(value);
					values.add(valueRetest);
					differingFields.put(key, values);
				}
			}

			for (Entry<String, JsonElement> entry : jsonRetest.entrySet()) {

				String key = entry.getKey();
				JsonElement valueExpect = jsonExpected.get(key);

				if (valueExpect == null) {
					extraFields.add(key);
				}
			}

			if (missingFields.size() > 0) {
				System.out.println(">>>> MISSING FIELDS:");
				for (String field : missingFields) {
					System.out.println(String.format("[%s]", field));
				}
				System.out.println("");
			}
			if (!differingFields.isEmpty()) {
				System.out.println(">>>> DIFFERING FIELDS:");
				for (Entry<String, List<JsonElement>> entry : differingFields.entrySet()) {
					System.out.println(String.format("KEY: [%s]", entry.getKey()));
					System.out.println(String.format("EXPECTED VALUE: [%s]", entry.getValue().get(0)));
					System.out.println(String.format("ACTUAL VALUE: [%s]", entry.getValue().get(1)));
					System.out.println();
				}
				System.out.println("");
			}
			if (extraFields.size() > 0) {
				System.out.println(">>>> EXTRA FIELDS:");
				for (String field : extraFields) {
					System.out.println(String.format("[%s]", field));
				}
				System.out.println("");
			}
		}

	}
}
