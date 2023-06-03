package Handler;

import java.util.Arrays;
import java.util.List;

import Const.TYPE;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Auto_Converting {

	public void start_converting_bo(JsonObject data, String converting_type, JsonArray converting_target_ar) {
		for (JsonElement element : converting_target_ar) {
			converting_byObject("", converting_type, data, element.getAsJsonObject());
		}

	}

	public boolean converting_byObject(String dir, String converting_type, JsonObject BO, JsonObject converting_target) {

		Boolean did = false;

		for (String key : BO.keySet()) {
			
			if (BO.get(key).isJsonObject()) {
				String next_cur_dir = dir.equals("") ? key : dir + "." + key;
				// handle object type
				did = converting_byObject(next_cur_dir, converting_type, BO.get(key).getAsJsonObject(), converting_target);
			} else if (BO.get(key).isJsonArray()) {
				if (BO.get(key).getAsJsonArray().get(0).isJsonObject()) {

					for (int i = 0; i < BO.get(key).getAsJsonArray().size(); i++) {
						String temp_dir = dir;
						temp_dir = temp_dir.equals("") ? key : dir + "." + key;
						did = converting_byObject(temp_dir, converting_type, BO.get(key).getAsJsonArray().get(i).getAsJsonObject(), converting_target);
					}
				}
			}
			// end of dir (primitive)
			else {
				String next_cur_dir = dir.equals("") ? key : dir + "." + key;

				String field_mapping = converting_target.getAsJsonObject().get("field_mapping").getAsString();
				List<String> field_dir_ar = Arrays.asList(field_mapping.split(";"));

				// start mapping
				// if tartget dir contain cur dir
				if (field_dir_ar.contains(next_cur_dir)) {
					if (converting_type.equals(TYPE.TO_TARGET)) {
						did = start_converting_ToTargetCode(next_cur_dir, key, BO, converting_target);
					} else {
						did = start_converting_ToMasterdataCode(next_cur_dir, key, BO, converting_target);
					}
				}

			}
		}
		// if didn't do any mapping
		if (!did) {
			// if is default mapping
			if (converting_target.get("default_value") != null && !converting_target.get("default_value").isJsonNull()) {
				String default_value_je = converting_target.get("default_value").getAsString();

				String field_mapping = converting_target.getAsJsonObject().get("field_mapping").getAsString();

				if (!default_value_je.isEmpty()) {
					List<String> field_dir_ar = Arrays.asList(field_mapping.split(";"));

					// loop dir in list target dir
					for (String e : field_dir_ar) {
						String[] list_key_of_fieldDir = e.split("\\.");
						String key = list_key_of_fieldDir[list_key_of_fieldDir.length - 1];

						String cur_dir = dir;

						// mapping for exist key
						if (cur_dir.equals(e.replace("." + key, ""))) {
							BO.addProperty(key, default_value_je);
							did = true;
						}

						if (!did) {
							for (int i = 2; i <= list_key_of_fieldDir.length; i++) {

								// real: A
								// target: A.B.C

								key = list_key_of_fieldDir[list_key_of_fieldDir.length - i];
								String temp = "";
								if (e.startsWith(key)) {
									temp = e.substring(0, e.indexOf(key));
								} else {
									temp = e.substring(0, e.indexOf(key) - 1);

								}

								if (cur_dir.equals(temp)) {
									BO.add(key, new JsonObject());
									String temp_dir_2 = dir.equals("") ? key : dir + "." + key;

									did = converting_byObject(temp_dir_2, converting_type, BO.get(key).getAsJsonObject(), converting_target);
									if (did) {
										break;
									}
								}
							}
							// mapping for not exist key

						}
					}
				}

			}
		}
		return did;
	}

	public boolean start_converting_ToMasterdataCode(String current_dir, String current_key, JsonObject current_object, JsonObject converting_target) {

//		String field_mapping = converting_target.get("FIELD_MAPPING").getAsString();
//		String field_dir = current_dir;
//		field_dir = field_dir.substring(2);

		// check KEY = FIELD-NAME
//		int index = field_mapping.indexOf(field_dir);
//		List<String> field_dir_ar = Arrays.asList(field_mapping.split(";"));

		String dataValue = null;
		try {
			dataValue = current_object.get(current_key).getAsString();
		} catch (Exception e) {
			return false;
		}

		// check whether load defaut-value
		JsonElement default_value_je = converting_target.get("default_value");
		JsonElement target_system_code_je = converting_target.get("target_system_code");

		if (default_value_je != null && default_value_je.isJsonPrimitive()) {
			// --> CASE: Load default-value
			if (target_system_code_je.isJsonNull() || target_system_code_je.getAsString().equals(dataValue)) {
				current_object.addProperty(current_key, default_value_je.getAsString());
				return true;
			}
		} else {
			// --> CASE: Convert code
			// check KEY-VALUE = ORIGIN-CODE
			if (target_system_code_je.isJsonPrimitive()) {
				if (dataValue.equals(target_system_code_je.getAsString())) {
					String masterdata_code = converting_target.get("masterdata_code").getAsString();
					current_object.addProperty(current_key, masterdata_code);
					return true;
				}
			}
		}
		return false;
	}

	public boolean start_converting_ToTargetCode(String current_dir, String current_key, JsonObject current_object, JsonObject converting_target) {

//		String field_mapping = converting_target.get("FIELD_MAPPING").getAsString();

//		String field_dir = current_dir;
//		field_dir = field_dir.substring(2);

		// check KEY = FIELD-NAME
//		int index = field_mapping.indexOf(field_dir);

//		List<String> field_dir_ar = Arrays.asList(field_mapping.split(";"));

		// if (field_mapping.contains(field_dir)
		// && !field_mapping.startsWith(".", index - 1)) {

		JsonElement default_value_je = converting_target.get("default_value");
		JsonElement masterdata_code_je = converting_target.get("masterdata_code");

		String dataValue = null;
		try {
			dataValue = current_object.get(current_key).getAsString();
		} catch (Exception e) {
			if (!default_value_je.isJsonPrimitive()) {
				return false;
			}
		}

		// check whether load defaut-value
		if (default_value_je != null && default_value_je.isJsonPrimitive()) {
			// --> CASE: Load default-value
			if (masterdata_code_je.isJsonNull() || masterdata_code_je.getAsString().equals(dataValue)) {
				current_object.addProperty(current_key, default_value_je.getAsString());
				return true;
			}
		} else {
			// --> CASE: Convert code
			// check KEY-VALUE = ORIGIN-CODE
			if (masterdata_code_je.isJsonPrimitive()) {
				if (dataValue.equals(masterdata_code_je.getAsString())) {
					String target_system_code = converting_target.get("target_system_code").getAsString();
					current_object.addProperty(current_key, target_system_code);
					return true;
				}
			}
		}
		return false;
	}

	// public String getCorrectDir(String dir){
	// String res = "";
	// try{
	// res= dir.substring(2);
	// }catch(Exception e){
	// // return res;
	// }
	// return res;
	// }

	/** mapping JSON-ARRAY **/
//	public JsonArray mappingToTargetCode(JsonArray data, String parent)
//			throws Exception {
//
//		// JsonArray mapper = loadMasterDataMapping();
//
//		for (JsonElement element : data) {
//			JsonObject objectData = element.getAsJsonObject();
//			// objectData = mappingToTarget_byObject(parent, objectData,
//			// mapper);
//			objectData = mappingToTarget_byObject(parent, objectData);
//		}
//
//		return data;
//	}

//	public JsonArray mappingToMasterdataCode(JsonArray data, String parent)
//			throws Exception {
//
//		// JsonArray mapper = loadMasterDataMapping();
//
//		for (JsonElement element : data) {
//			JsonObject objectData = element.getAsJsonObject();
//			objectData = mappingToMasterdataCode_byObject(parent, objectData);
//		}
//
//		return data;
//	}

	/** mapping JSON-OBJECT **/
//	public JsonObject mappingToTargetCode(JsonObject data, String parent)
//			throws Exception {
//		// JsonArray mapper = loadMasterDataMapping();
//		return mappingToTarget_byObject(parent, data);
//	}

//	public JsonObject mappingToMasterdataCode(JsonObject data, String parent)
//			throws Exception {
//		// JsonArray mapper = loadMasterDataMapping();
//		return mappingToMasterdataCode_byObject(parent, data);
//	}

	/** mapping STRING **/
//	public String mappingToTargetCode(String data, String parent)
//			throws Exception {
//
//		String result = null;
//
//		/** mapping data **/
//		JsonElement je = new Gson().fromJson(data, JsonElement.class);
//
//		if (je.isJsonArray()) {
//			// case JSON-ARRAY
//			result = mappingToTargetCode(je.getAsJsonArray(), parent)
//					.toString();
//
//		} else if (je.isJsonObject()) {
//			// case JSON-OBJECT
//			result = mappingToTargetCode(je.getAsJsonObject(), parent)
//					.toString();
//
//		} else {
//			throw new Exception(
//					"Data mapping must be a JsonObject or JsonArray");
//		}
//
//		return result;
//	}

//	public JsonObject mappingToTarget_byObject(String parent, JsonObject data) {
//
//		Set<String> keySet = data.keySet();
//
//		for (String key : keySet) {
//
//			for (JsonElement element : this.mappingData) {
//				String field_mapping = element.getAsJsonObject()
//						.get("FIELD_MAPPING").getAsString();
//				String field_dir = parent == null ? key : (parent + "." + key);
//
//				// check KEY = FIELD-NAME
//				int index = field_mapping.indexOf(field_dir);
//				String[] field_mapping_list = field_mapping.split(";");
//
//				boolean isMatched = false;
//				for (String item : field_mapping_list) {
//					if (field_dir.equals(item)) {
//						isMatched = true;
//					}
//				}
//				if (isMatched && !field_mapping.startsWith(".", index - 1)) {
//
//					JsonElement default_value_je = element.getAsJsonObject()
//							.get("DEFAULT_VALUE");
//					JsonElement masterdata_code_je = element.getAsJsonObject()
//							.get("MASTERDATA_CODE");
//
//					String dataValue = null;
//					try {
//						dataValue = data.get(key).getAsString();
//					} catch (Exception e) {
//						if (!default_value_je.isJsonPrimitive()) {
//							break;
//						}
//					}
//
//					// check whether load defaut-value
//					if (default_value_je.isJsonPrimitive()) {
//						// --> CASE: Load default-value
//						if (masterdata_code_je.isJsonNull()
//								|| masterdata_code_je.getAsString().equals(
//										dataValue)) {
//							data.addProperty(key,
//									default_value_je.getAsString());
//							break;
//						}
//					} else {
//						// --> CASE: Convert code
//						// check KEY-VALUE = ORIGIN-CODE
//						if (masterdata_code_je.isJsonPrimitive()) {
//							if (dataValue.equals(masterdata_code_je
//									.getAsString())) {
//								String target_system_code = element
//										.getAsJsonObject()
//										.get("TARGET_SYSTEM_CODE")
//										.getAsString();
//								data.addProperty(key, target_system_code);
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
//
//		return data;
//	}

	// maping support
//	public JsonObject mappingToMasterdataCode_byObject(String parent,
//			JsonObject data) {
//
//		Set<String> keySet = data.keySet();
//
//		for (String key : keySet) {
//
//			for (JsonElement element : this.mappingData) {
//
//				String field_mapping = element.getAsJsonObject()
//						.get("FIELD_MAPPING").getAsString();
//				String field_dir = parent == null ? key : (parent + "." + key);
//
//				// check KEY = FIELD-NAME
//				int index = field_mapping.indexOf(field_dir);
//				if (field_mapping.contains(field_dir)
//						&& !field_mapping.startsWith(".", index - 1)) {
//
//					String dataValue = null;
//					try {
//						dataValue = data.get(key).getAsString();
//					} catch (Exception e) {
//						break;
//					}
//
//					// check whether load defaut-value
//					JsonElement default_value_je = element.getAsJsonObject()
//							.get("DEFAULT_VALUE");
//					JsonElement target_system_je = element.getAsJsonObject()
//							.get("TARGET_SYSTEM_CODE");
//
//					if (default_value_je.isJsonPrimitive()) {
//						// --> CASE: Load default-value
//						if (target_system_je.isJsonNull()
//								|| target_system_je.getAsString().equals(
//										dataValue)) {
//							data.addProperty(key,
//									default_value_je.getAsString());
//							break;
//						}
//					} else {
//						// --> CASE: Convert code
//						// check KEY-VALUE = ORIGIN-CODE
//						if (target_system_je.isJsonPrimitive()) {
//							if (dataValue
//									.equals(target_system_je.getAsString())) {
//								String masterdata_code = element
//										.getAsJsonObject()
//										.get("MASTERDATA_CODE").getAsString();
//								data.addProperty(key, masterdata_code);
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
//
//		return data;
//	}
}
