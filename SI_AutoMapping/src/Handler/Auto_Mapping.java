package Handler;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;




import Const.TYPE;

import javax.script.Bindings;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;
import javax.xml.bind.DatatypeConverter;

public class Auto_Mapping {

	private ScriptEngineManager mgr;
	private ScriptEngine engine;
	private Bindings bindings;
	private JsonObject master_bo;
	private JsonArray mapping_target_ar;
	private String stmt;
	private int indexOfN;

	public Auto_Mapping() {
		mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("JavaScript");
		bindings = engine.createBindings();

	}

	public Auto_Mapping(String service, String module, String dataSourceName) throws ExecutionException {

		mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("JavaScript");
		bindings = engine.createBindings();

//		this.stmt = MessageFormat.format("{0}#{1}|{2}-{3}", SI.LOAD_SI_AUTO_MAPPING, service, module, dataSourceName);
//		this.mapping_target_ar = (JsonArray) CData.MAP_DATA.get(this.stmt);
//		System.out.println(mapping_target_ar.toString());
//		System.out.println("");
	}
	
	public Auto_Mapping(int indexOfN) {

		mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("JavaScript");
		bindings = engine.createBindings();
		this.indexOfN = indexOfN;

	}

	public String start_mapping_bo(JsonObject baseBO, JsonObject targetBO, JsonArray mapping_target_ar)
			throws Exception {
		String result = null;
		this.mapping_target_ar = mapping_target_ar;

		if (this.mapping_target_ar.size() > 0) {
			JsonArray target_calcula = get_calcula_value(baseBO, mapping_target_ar);

			for (JsonElement e : target_calcula) {
				JsonObject temp = e.getAsJsonObject();
				bindings.put(temp.get(TYPE.MAPPING_CTR_NAME).getAsString(), temp.get("value").getAsString());
			}

			this.master_bo = baseBO;

			for (JsonElement e : mapping_target_ar) {
				
				JsonObject mapping = e.getAsJsonObject();
				if(!mapping.has(TYPE.MAPPING_IS_REQUIRED)) {
					mapping.addProperty(TYPE.MAPPING_IS_REQUIRED, "1");
				}
				if(mapping.has(TYPE.MAPPING_FROM_FIELD)){
					String from_field = mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString();
					if(from_field.split("\\[n\\]").length == 2){
						from_field = from_field.replaceAll("\\[n\\]", "\\["+this.indexOfN+"\\]");
					mapping.addProperty(TYPE.MAPPING_FROM_FIELD, from_field);
					}
				}
				mapping_ver3(targetBO, baseBO, mapping, "#", target_calcula, mapping_target_ar);
//			System.out.println(targetBO.get("data").getAsJsonObject().get("LOS_CUSTOMER").getAsJsonObject().get("IDENTITY_INFORMATION").getAsJsonArray().toString());

			}

			// return condition
//			if (this.mapping_target_ar.size() > 1) {
//				// return non-primitive value
//				result = targetBO.toString();
//			} else {
//				result = get_value_byDir_2(baseBO, "#",
//						this.mapping_target_ar.get(0).getAsJsonObject().get(TYPE.MAPPING_FROM_FIELD).getAsString()).getAsString();
//			}
			result = targetBO.toString();

		} else {
			throw new Exception(String.format("Has no mapping-info. Stmt: [%s]", this.stmt));
		}

		return result;
	}

	public JsonArray get_calcula_value(JsonObject baseBO, JsonArray target_mapping) throws Exception {

		JsonArray result = new JsonArray();

		for (JsonElement e : target_mapping) {
			if (e.getAsJsonObject().get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("1")) {
				JsonObject mapping = e.getAsJsonObject();
				String value = null;
				if (!mapping.has(TYPE.MAPPING_FROM_FIELD) && mapping.has(TYPE.MAPPING_DEFAULT_DATA)) {
					if (mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_NULL")
							|| mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_null")) {
//						targetBO.add(key, null);
						value = null;
					} else if (mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals(" ")
							|| mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_empty")
							|| mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_EMPTY")) {
//						targetBO.addProperty(key, "");
						value = "";
					} else {
//						targetBO.add(key, mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsJsonPrimitive());
						value = mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString();
					}
				}else {
					if(mapping.has(TYPE.MAPPING_FROM_FIELD)){
						String from_field = mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString();
						if(from_field.split("\\[n\\]").length == 2){
							from_field = from_field.replaceAll("\\[n\\]", "\\["+this.indexOfN+"\\]");
						mapping.addProperty(TYPE.MAPPING_FROM_FIELD, from_field);
						}
					}
					
					 JsonElement	valueJE = null;
					try {
						 	valueJE = get_value_byDir(baseBO, mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString());
					} catch (Exception e2) {
						  if(mapping.has(TYPE.MAPPING_IS_REQUIRED) &&mapping.get(TYPE.MAPPING_IS_REQUIRED).getAsString().equalsIgnoreCase("1")) {
								throw new Exception(e2);							
							} else {
								System.out.println("Can't map value for mapping: " + mapping.toString());		
							}
					}				   
					if(valueJE == null || valueJE.isJsonNull()){
					value = "";
					}else{
						value = valueJE.getAsString();
					}
				}
				
				mapping.addProperty("value", value);
				result.add(mapping);
			}

		}
		return result;
	}

	public boolean mapping_ver3(JsonElement target_BO, JsonObject baseBO, JsonObject mapping, String dir,
			JsonArray target_calcula, JsonArray mapping_target_ar) throws Exception {

		// ignore is_calcula mapping object
		if (mapping.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("0")
				|| mapping.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("2")) {
			Boolean did = false;
			target_BO = target_BO.getAsJsonObject();
			JsonObject temp_target_BO = target_BO.getAsJsonObject();
			for (String key : temp_target_BO.keySet()) {
				if (did) {
					return did;
				}
				if (mapping.get(TYPE.MAPPING_TO_FIELD).getAsString().contains(dir + "." + key)) {

					// dive in object
					if (temp_target_BO.get(key).isJsonObject()) {
						String temp_dir = dir + "." + key;
						did = mapping_ver3(temp_target_BO.get(key).getAsJsonObject(), baseBO, mapping, temp_dir,
								target_calcula, mapping_target_ar);
					}
					// dive in array
					else if (temp_target_BO.get(key).isJsonArray()) {

						if (mapping.get(TYPE.MAPPING_TO_FIELD).getAsString().contains("[i]")
								&& mapping.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("2")) {
							try {
								String temp_cur_dir = dir + "." + key + "[i]";
								if (mapping.get(TYPE.MAPPING_TO_FIELD).getAsString().contains(temp_cur_dir)) {
									String base_i_dir = mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString().substring(0,
											mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString().indexOf("[i]"));
									String base_i_dir_target = mapping.get(TYPE.MAPPING_TO_FIELD).getAsString().substring(0,
											mapping.get(TYPE.MAPPING_TO_FIELD).getAsString().indexOf("[i]"));
									JsonArray temp_ar_base = get_value_byDir(baseBO, base_i_dir).getAsJsonArray();
									JsonArray temp_ar_target = temp_target_BO.get(key).getAsJsonArray();
									while (temp_ar_target.size() > 0) {
										temp_ar_target.remove(0);
									}
//									temp_ar_target.addAll(temp_ar_base);
									JsonObject temp_oj_base = new JsonObject();
									temp_oj_base.add("data", temp_ar_base);

									
									if(temp_ar_base.size() > 0 ) {
										
										JsonArray temp_target_ar_i = new JsonArray();
										String to_field_flag = "";
										for (JsonElement e : mapping_target_ar) {
											JsonObject temp_e = e.getAsJsonObject();
											if (temp_e.has(TYPE.MAPPING_FROM_FIELD)) {
												if (temp_e.get(TYPE.MAPPING_FROM_FIELD).getAsString()
														.contains(base_i_dir+"[i]")
														&& temp_e.get(TYPE.MAPPING_TO_FIELD).getAsString().contains(base_i_dir_target+"[i]")) {
													temp_target_ar_i.add(temp_e);
													to_field_flag = temp_e.get(TYPE.MAPPING_TO_FIELD).getAsString();
													to_field_flag = to_field_flag.substring(0,
															to_field_flag.indexOf("[i]"));
												}
											}
										}
										for (JsonElement e : mapping_target_ar) {
											JsonObject temp_e = e.getAsJsonObject();
											if(!temp_e.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("1")) {
												if (!temp_e.has(TYPE.MAPPING_FROM_FIELD)) {
													if (temp_e.get(TYPE.MAPPING_TO_FIELD).getAsString()
															.contains(to_field_flag)) {
														temp_target_ar_i.add(temp_e);
													}
												} else {
													if (temp_e.get(TYPE.MAPPING_TO_FIELD).getAsString()
															.contains(to_field_flag)) {
														if (!temp_target_ar_i.contains(temp_e)) {
															temp_target_ar_i.add(temp_e);
														}
													}
												}
											}
										}
										JsonObject temp_oj_target = new JsonObject();
										
										for (JsonElement e : temp_target_ar_i) {
											
											for (int i = 0; i < temp_ar_base.size(); i++) {
												
												boolean map_from_temp_oj_base = true;
												
												JsonObject temp_e = e.getAsJsonObject().deepCopy();
												if(temp_e.has(TYPE.MAPPING_FROM_FIELD)) {
													String temp_from_field = temp_e.get(TYPE.MAPPING_FROM_FIELD).getAsString();
													if (temp_from_field.contains("[i]")) {
//												temp_from_field=  temp_from_field.substring(temp_from_field.indexOf("[i]")+4,temp_from_field.length());
//												temp_from_field = "#.data["+ i+"]."+temp_from_field;
														temp_from_field = temp_from_field.replace(
																temp_from_field.substring(0,
																		temp_from_field.indexOf("[i]") + 3),
																"#.data[" + i + "]");
														
													} else {
														map_from_temp_oj_base = false;
													}												
													temp_e.addProperty(TYPE.MAPPING_FROM_FIELD, temp_from_field);
												}
												
												String temp_to_field = temp_e.get(TYPE.MAPPING_TO_FIELD).getAsString();
												if (temp_to_field.contains("[i]")) {
//												temp_to_field=  temp_to_field.substring(temp_to_field.indexOf("[i]")+4,temp_to_field.length());
//												temp_to_field = "#.data["+ i+"]."+temp_to_field;												
													temp_to_field = temp_to_field.replace(
															temp_to_field.substring(0, temp_to_field.indexOf("[i]") + 3),
															"#.data[" + i + "]");
													temp_e.addProperty(TYPE.MAPPING_TO_FIELD, temp_to_field);
												}
												if(temp_e.has(TYPE.MAPPING_DEFAULT_DATA)) {
													if(temp_e.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_STT")) {
														
														temp_e.addProperty(TYPE.MAPPING_DEFAULT_DATA,  String.valueOf(i+1));
													}
												}
												
												if (map_from_temp_oj_base) {
													mapping_ver3(temp_oj_target, temp_oj_base, temp_e, "#", target_calcula,
															mapping_target_ar);
												} else {
													mapping_ver3(temp_oj_target, this.master_bo, temp_e, "#",
															target_calcula, mapping_target_ar);
												}
												
											}
										}
										temp_ar_target.addAll(temp_oj_target.get("data").getAsJsonArray());
									}
									

//
//									for (JsonElement e : temp_ar_target) {
//										JsonObject temp_e = e.getAsJsonObject();
//
//										ArrayList<String> need_key = new ArrayList<String>();
//
//										for (JsonElement y : temp_target_ar_i) {
//
//											JsonObject temp_y = y.getAsJsonObject();
//											String temp_to_field = temp_y.get(TYPE.MAPPING_TO_FIELD).getAsString();
//											String temp_to_field_key = temp_to_field.substring(temp_to_field.indexOf("[i]") + 4, temp_to_field.length());
//
//											if (!temp_y.has(TYPE.MAPPING_FROM_FIELD) && temp_y.has(TYPE.MAPPING_DEFAULT_DATA)) {
//												start_mapping_props(temp_e, baseBO, temp_y, temp_to_field_key, null, true);
//
//											} else {
//												String temp_from_field = temp_y.get(TYPE.MAPPING_FROM_FIELD).getAsString();
//												String temp_from_field_key = "";
//												if (temp_from_field.contains("[i]")) {
//													temp_from_field_key = temp_from_field.substring(temp_from_field.indexOf("[i]") + 4, temp_from_field.length());
//
//													if (temp_e.has(temp_from_field_key) && !temp_from_field_key.equals(temp_to_field_key)) {
////														temp_e.add(temp_to_field_key, );
//														start_mapping_props(temp_e, baseBO, temp_y, temp_to_field_key, temp_e.get(temp_from_field_key), true);
//														temp_e.remove(temp_from_field_key);
//													}
//												} else {
//
//													String[] temp = temp_from_field.split("\\.");
//
//													temp_from_field_key = temp[temp.length - 1];
//													start_mapping_props(temp_e, baseBO, temp_y, temp_to_field_key, null, false);
//												}
//
//											}
//											need_key.add(temp_to_field_key);
//										}
//										Set<String> keys = temp_e.keySet();
//										keys.retainAll(need_key);
//									}
									did = true;
								}
							} catch (Exception e) {
								// check CALCULA = 2 and required = 1 throw exception
								if( mapping.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("2")) {									
									if(mapping.has(TYPE.MAPPING_IS_REQUIRED) && mapping.get(TYPE.MAPPING_IS_REQUIRED).getAsString().equalsIgnoreCase("1")){			
										throw new Exception("Can't map value for mapping: " + mapping.toString()+", detail:"+e.getMessage());
									}else{
										did = true;
									}
								}else{
									throw new Exception("Can't map value for mapping: " + mapping.toString()+", detail:"+e.getMessage());
								}
								System.out.println("can't get value From Field!!!!");
							}

						} else if (temp_target_BO.get(key).getAsJsonArray().size() > 0) {
							if (temp_target_BO.get(key).getAsJsonArray().get(0).isJsonObject()) {

								if (mapping.has(TYPE.MAPPING_CONDITION_FIELD)
										&& mapping.get(TYPE.MAPPING_TO_FIELD).getAsString().contains(key + "[]")) {

									String temp_dir = dir;
									temp_dir = temp_dir + "." + key + "[]";

									for (JsonElement e : temp_target_BO.get(key).getAsJsonArray()) {
										if (e.getAsJsonObject()
												.get(mapping.get(TYPE.MAPPING_CONDITION_FIELD).getAsString())
												.getAsJsonPrimitive()
												.equals(mapping.get(TYPE.MAPPING_CONDITION).getAsJsonPrimitive())) {
											for (String temp_key : e.getAsJsonObject().keySet()) {
												String temp_dir2 = temp_dir;
												temp_dir2 = temp_dir2 + "." + temp_key;
												if (temp_dir2
														.equals(mapping.get(TYPE.MAPPING_TO_FIELD).getAsString())) {
													e.getAsJsonObject().add(temp_key, get_value_byDir(baseBO,
															mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString()));
												}
											}
											did = true;
										}

									}
								}

								else {
									for (int i = 0; i < temp_target_BO.get(key).getAsJsonArray().size(); i++) {
										String temp_dir = dir;
										temp_dir = temp_dir + "." + key + "[" + i + "]";
										did = mapping_ver3(
												temp_target_BO.get(key).getAsJsonArray().get(i).getAsJsonObject(),
												baseBO, mapping, temp_dir, target_calcula, mapping_target_ar);
									}
								}
							}
						}

					} else {
						String temp_dir = dir + "." + key;

						String to_field = mapping.get(TYPE.MAPPING_TO_FIELD).getAsString();
						if (dir.equals(to_field) || key.equals(to_field) || temp_dir.equals(to_field)) {
							start_mapping_props(temp_target_BO, baseBO, mapping, key, null, false);
							did = true;
						}

					}
				}
				// fast return

			}
			if (!did) {
				String to_field = mapping.get(TYPE.MAPPING_TO_FIELD).getAsString();
				String[] list_key_of_fieldDir = to_field.split("\\.");
				String key = list_key_of_fieldDir[list_key_of_fieldDir.length - 1];

				// create cur key for cur object if not exist
				if (dir.equals(to_field.substring(0, to_field.length() - key.length() - 1))
						&& !to_field.contains("[i]")) {
					start_mapping_props(temp_target_BO, baseBO, mapping, key, null, false);
					did = true;
				}

				// path not found
				// mapping for not exist key
				String cur_dir = dir;
				if (!did) {
					for (int i = 2; i <= list_key_of_fieldDir.length; i++) {
						if (did || (to_field.contains("[i]")
								&& !mapping.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("2"))) {
							return did;
						}

						// find longest path exist for cur mapping dir
						key = list_key_of_fieldDir[list_key_of_fieldDir.length - i];
						
						String[] list_key_of_fieldDir_temp = new String[list_key_of_fieldDir.length - i];
						for(int j =0;j<=list_key_of_fieldDir_temp.length-1;j++) {
							list_key_of_fieldDir_temp[j] = list_key_of_fieldDir[j];
						}
						
//						String key_before = "";
//						try {
//							key_before = list_key_of_fieldDir[list_key_of_fieldDir.length - i - 1];
//						} catch (Exception e) {
//							// TODO: handle exception
//							key_before = "#";
//						}
						String temp = "";
						if (to_field.startsWith(key)) {
							
//							temp = to_field.substring(0, to_field.indexOf(key,to_field.indexOf(key_before)+key_before.length()-1));
							temp = to_field.substring(0, to_field.indexOf(key));
						} else {
//							temp = to_field.substring(0, to_field.indexOf(key, to_field.indexOf(key_before)+key_before.length()-1) - 1);
//							temp = to_field.substring(0, to_field.indexOf(key) - 1);
							temp = String.join(".", list_key_of_fieldDir_temp);

						}

						// found longest path
						if (cur_dir.equals(temp)) {

							// extend path by creating jsonObject or jsonArray
							// to match the mapping path
							String temp_dir_2 = dir + "." + key;

							if (key.contains("[") && key.contains("]") && !key.contains("[i]")) {

//										String temp_key = key.replace("[0]", "");
								String temp_key = key.replaceFirst("\\[\\d+\\]$", "");
								String index = key.substring(key.indexOf("["), key.length());
								index = index.replaceAll("\\[|\\]", "");

								if (!temp_target_BO.has(temp_key)) {
									temp_target_BO.add(temp_key, new JsonArray());
								}

								if (temp_target_BO.get(temp_key).getAsJsonArray().size() - 1 < Integer
										.parseInt(index)) {
									do {
										JsonObject temp_Object = new JsonObject();
										temp_target_BO.get(temp_key).getAsJsonArray().add(temp_Object);
									} while (temp_target_BO.get(temp_key).getAsJsonArray().size() - 1 < Integer
											.parseInt(index));
								}

								did = mapping_ver3(
										temp_target_BO.get(temp_key).getAsJsonArray().get(Integer.parseInt(index))
												.getAsJsonObject(),
										baseBO, mapping, temp_dir_2, target_calcula, mapping_target_ar);

							} else if (temp_dir_2.endsWith("[i]")) {
								if (mapping.get(TYPE.MAPPING_IS_CALCULA).getAsString().equals("2")) {
									String temp_key = key.replaceFirst("\\[\\w\\]$", "");
									temp_target_BO.add(temp_key, new JsonArray());
									did = mapping_ver3(temp_target_BO, baseBO, mapping, dir, target_calcula,
											mapping_target_ar);
								}

							} else {
								temp_target_BO.add(key, new JsonObject());

								did = mapping_ver3(temp_target_BO.get(key), baseBO, mapping, temp_dir_2,
										target_calcula, mapping_target_ar);
//								if (!to_field.contains("[i]")) {
//									temp_target_BO.add(key, new JsonObject());
//
//									did = mapping_ver3(temp_target_BO.get(key), baseBO, mapping, temp_dir_2,
//											target_calcula, mapping_target_ar);
//								}
							}
						}

						if (did) {
							break;
						}
					}
				}
			}
			return did;
		}

		else {
			return true;
		}
	}

	public void start_mapping_props(JsonObject targetBO, JsonObject baseBO, JsonObject mapping, String key,
			JsonElement target_value, boolean is_use_target_value) throws Exception {

		if (!mapping.has(TYPE.MAPPING_FROM_FIELD) && mapping.has(TYPE.MAPPING_DEFAULT_DATA)) {
			if (mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_NULL")
					|| mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_null")) {
				targetBO.add(key, null);
			} else if (mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals(" ")
					|| mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_empty")
					|| mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsString().equals("_EMPTY")) {
				targetBO.addProperty(key, "");
			} else {
				targetBO.add(key, mapping.get(TYPE.MAPPING_DEFAULT_DATA).getAsJsonPrimitive());
			}
		} else if (mapping.get(TYPE.MAPPING_IS_DATE).getAsString().equals("1")) {

			String dateString = "";
			if (is_use_target_value) {
				dateString = target_value.getAsString();
			} else {
				
				if(mapping.has(TYPE.MAPPING_IS_REQUIRED) && mapping.get(TYPE.MAPPING_IS_REQUIRED).getAsString().equalsIgnoreCase("1")){					
					if (get_value_byDir(baseBO, mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString()) != null && get_value_byDir(baseBO, mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString()).isJsonNull()) {
						dateString = null;
					} else {
						dateString = get_value_byDir(baseBO, mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString())
								.getAsString();
					}
				}else{
					try {						
						dateString = get_value_byDir(baseBO, mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString())
								.getAsString();
					} catch (Exception e) {
						dateString = null;
					}
				}
			}

			if (dateString != null) {
				if (!dateString.equals("")) {
					String[] target_date_params = mapping.get(TYPE.MAPPING_CONDITION).getAsString().split("\\|");
					if( !mapping.has(TYPE.MAPPING_CONDITION_FIELD) || (mapping.has(TYPE.MAPPING_CONDITION_FIELD) && mapping.get(TYPE.MAPPING_CONDITION_FIELD).isJsonNull())|| 
							(mapping.has(TYPE.MAPPING_CONDITION_FIELD) && !mapping.get(TYPE.MAPPING_CONDITION_FIELD).isJsonNull() && ("").equalsIgnoreCase(mapping.get(TYPE.MAPPING_CONDITION_FIELD).getAsString()))){
						Calendar parseDateTime = DatatypeConverter.parseDateTime(dateString);
						Date date = parseDateTime.getTime();
						DateFormat dateFormat = new SimpleDateFormat(target_date_params[0]);
						String date_formated =dateFormat.format(date);
						targetBO.addProperty(key, date_formated);
					}else if(mapping.has(TYPE.MAPPING_CONDITION_FIELD) && !mapping.get(TYPE.MAPPING_CONDITION_FIELD).isJsonNull()){						
						String[] base_date_params = mapping.get(TYPE.MAPPING_CONDITION_FIELD).getAsString().split("\\|");
						if (target_date_params[0].endsWith("XXX")) {
							String timezone = "7";
							if (target_date_params.length > 1) {
								timezone = target_date_params[1];
							}
							targetBO.addProperty(key,
									convertDateToBpmDateString(dateString,
											mapping.get(TYPE.MAPPING_CONDITION_FIELD).getAsString(), target_date_params[0],
											"7", timezone));
						} else if (base_date_params[0].endsWith("XXX")) {
							String timezone = "7";
							if (base_date_params.length > 1) {
								timezone = base_date_params[1];
							}
							targetBO.addProperty(key, convertDateToBpmDateString(dateString, base_date_params[0],
									mapping.get(TYPE.MAPPING_CONDITION).getAsString(), timezone, "7"));
						} else {
							Date date = new SimpleDateFormat(mapping.get(TYPE.MAPPING_CONDITION_FIELD).getAsString())
							.parse(dateString);
							DateFormat dateFormat = new SimpleDateFormat(mapping.get(TYPE.MAPPING_CONDITION).getAsString());
							String date_formated = dateFormat.format(date);
							targetBO.addProperty(key, date_formated);
						}
					}
				} else {
					targetBO.addProperty(key, dateString);
				}
			} else {
				targetBO.add(key, null);
			}

			// dd-mm-yyyy

		} else if (mapping.has("formula")) {

			String formula = mapping.get("formula").getAsString();

			targetBO.addProperty(key, engine.eval(formula, bindings).toString());
		} else {

			JsonElement res = null;
			if (is_use_target_value) {
				res = target_value;
			} else {
				res = get_value_byDir(baseBO, "#", mapping.get(TYPE.MAPPING_FROM_FIELD).getAsString());
				if (res.getAsJsonObject().has(TYPE.GET_HASVALUE)) {
					if (!res.getAsJsonObject().get(TYPE.GET_HASVALUE).getAsBoolean()) {
						if (mapping.has(TYPE.MAPPING_DEFAULT_DATA)) {
							mapping.remove(TYPE.MAPPING_FROM_FIELD);
							start_mapping_props(targetBO, baseBO,
									mapping, key, target_value,
									is_use_target_value);
							return;
						} else {
							if(mapping.has(TYPE.MAPPING_IS_REQUIRED) &&mapping.get(TYPE.MAPPING_IS_REQUIRED).getAsString().equalsIgnoreCase("1")) {
								throw new Exception("Can't map value for mapping: " + mapping.toString());							
							}else {
								System.out.println("Can't map value for mapping: " + mapping.toString());		
								res = null;
							}
//							throw new Exception("Can't map value for mapping: " + mapping.toString());								
						}
					} else {
						res = res.getAsJsonObject().get(TYPE.GET_RES);
					}
				}
			}

			targetBO.add(key, res);
		}
	}

	public JsonObject get_value_byDir(JsonObject BO, String dir, String target_Dir)
			throws NumberFormatException, ScriptException {
//		JsonElement res = null;
		JsonObject res = new JsonObject();
		for (String key : BO.keySet()) {
			if (res.has(TYPE.GET_RES)) {
				return res;
			}
			if (target_Dir.contains(dir + "." + key)) {
				if (BO.get(key).isJsonObject()) {
					String temp_dir = dir;
					temp_dir = temp_dir + "." + key;
					res = get_value_byDir(BO.get(key).getAsJsonObject(), temp_dir, target_Dir);
				} else if (BO.get(key).isJsonArray()) {

					if (BO.get(key).getAsJsonArray().size() > 0) {
						if (BO.get(key).getAsJsonArray().get(0).isJsonObject()) {
							for (int i = 0; i < BO.get(key).getAsJsonArray().size(); i++) {
								if (!res.has(TYPE.GET_RES)) {
									String temp_dir = dir + "." + key + "[" + i + "]";
									String temp_dir_2 = "";
									if ((BO.get(key).getAsJsonArray().size() - i) > 1) {
										temp_dir_2 = dir + "." + key + "[n-"
												+ (BO.get(key).getAsJsonArray().size() - 1 - i) + "]";
									} else {
										temp_dir_2 = dir + "." + key + "[n]";
									}

									if(temp_dir.equals(target_Dir)) {
										res.add(TYPE.GET_RES, BO.get(key).getAsJsonArray().get(i));
										res.addProperty(TYPE.GET_HASVALUE, true);
										return res;
									}
									
									res = get_value_byDir(BO.get(key).getAsJsonArray().get(i).getAsJsonObject(),
											temp_dir, target_Dir);
									if (!res.has(TYPE.GET_RES)) {
										res = get_value_byDir(BO.get(key).getAsJsonArray().get(i).getAsJsonObject(),
												temp_dir_2, target_Dir);
									}
								}
							}
						} else {
							if (target_Dir.matches(".*\\Dn.*\\D+$")) {

								Pattern pattern = Pattern.compile("(.*)(?<=\\[)(.*?)(?=\\])");
								Matcher matcher = pattern.matcher(target_Dir);
								if (matcher.find()) {
									String temp_position = matcher.group(2);
									String temp_target_dir = matcher.group(1).substring(0,
											matcher.group(1).length() - 1);

									if (temp_target_dir.equals(dir + "." + key)) {

										String real_position = engine
												.eval(temp_position.replace("n",
														Integer.toString(BO.get(key).getAsJsonArray().size() - 1)))
												.toString();

										JsonElement temp_res = BO.get(key).getAsJsonArray()
												.get(new Integer(real_position));
										res.add(TYPE.GET_RES, temp_res);
										res.addProperty(TYPE.GET_HASVALUE, true);

									}
								}
							}
						}
					}

				} else {
					if (BO.get(key).isJsonPrimitive() && BO.get(key).getAsJsonPrimitive().isString()) {

						String temp = BO.get(key).getAsString();

						if (temp.startsWith("{") && temp.endsWith("}")) {

							JsonObject test = new Gson().fromJson(temp, JsonObject.class);

							String temp_dir = dir;
							temp_dir = dir + "." + key;

							res = get_value_byDir(test, temp_dir, target_Dir);
						}
					}
				}

				if (!res.has(TYPE.GET_RES)) {
					String temp_dir = dir + "." + key;
					if (key.equals(target_Dir) || temp_dir.equals(target_Dir)) {

						JsonElement temp_res = BO.get(key);
						res.add(TYPE.GET_RES, temp_res);
						res.addProperty(TYPE.GET_HASVALUE, true);
						return res;
					}
				}
			}
		}

		if (!res.has(TYPE.GET_RES)) {
			res.addProperty(TYPE.GET_HASVALUE, false);
		}

		return res;
	}

	public JsonElement get_value_byDir(JsonObject BO, String dir) throws Exception {

		try {
			JsonObject result = get_value_byDir(BO, "#", dir);
			if (result.get(TYPE.GET_HASVALUE).getAsBoolean()) {
				return result.get(TYPE.GET_RES);
			} else {
				throw new Exception("Can't get value from dir" + dir);
			}
		} catch (NumberFormatException | ScriptException e) {
			throw new Exception("Fetching value is failed", e);
		}
	}

	public String convertDateToBpmDateString(String dateString, String originFormat, String targetFormat, String timezone_base, String timezone_target) throws ParseException {

		String result = null;
		LocalDateTime ldt;
		
		if (dateString != null && !dateString.trim().isEmpty()) {
			try {
				ldt = LocalDateTime.parse(dateString.endsWith(".0") ? dateString.replace(".0", "") : dateString, DateTimeFormatter.ofPattern(originFormat));
				
			} catch (Exception e) {
				ldt = new SimpleDateFormat(originFormat).parse(dateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			}
			ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC+" + timezone_base));

			DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetFormat).withZone(ZoneId.of("UTC+" + timezone_target));
			result = targetFormatter.format(zdt);
		}

		return result;
	}
}
