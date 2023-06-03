package Const;

import java.lang.reflect.Modifier;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class SI {

	public static int LOG_LEVEL = 5;

	public static String LOG_INSERT_SP = "SI_HANDLING_V1.INSERT_SI_LOG";
	public static String LOG_INSERT_SP_MULTI_CLOB = "SI_HANDLING_V1.INSERT_SI_LOG_MULTI_CLOB";
	public static String SP_LOAD_INSTANCE_BY_CA = "SI_HANDLING_V1.LOAD_INSTANCE_BY_CA";

	public static String SERVICE_CONFIG_QUERY_PSTMT = "Select SERVICE_VERSION, SERVICE_CONFIG from SI_VERSION where SI_VERSION=? and SERVICE_NAME=? and STATUS=1";
	public static String CONVERTED_MAPPING_QUERY_PSTMT = "select field_mapping, masterdata_code, target_system_code, default_value from si_masterdata_convert where (convert_type=? or convert_type = 'TWO_WAY') and service=? and status=1 order by field_mapping";

	public static JsonObject get_siConfig() {
		return (JsonObject) new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create().toJsonTree(new SI());
	}
}