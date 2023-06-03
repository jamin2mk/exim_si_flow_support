package com.si.config;

public class SI {

	public static int LOG_LEVEL = 5;

	public static String LOG_INSERT_SP = "SI_HANDLING_V2.INSERT_SI_LOG";

	public static String SERVICE_CONFIG_QUERY_PSTMT = "SELECT service_version,service_config FROM si_version WHERE si_version=? AND service_name=? AND status=1";
	public static String SERVICE_PROCEDURE_QUERY_PSTMT = "SELECT input,output FROM si_package WHERE procedure=? AND status=1";
	public static String MAP_INFO_QUERY_PSTMT = "SELECT from_field,to_field,is_calcula,is_date,default_data,condition_field,condition,ctr_name,formula,is_required FROM si_auto_mapping WHERE subject_code=? AND status=1";

	public static String CONVERTED_INFO_QUERY_PSTMT = "SELECT field_mapping,masterdata_code,target_system_code,default_value FROM si_masterdata_convert WHERE service=? AND (convert_type=? or convert_type='TWO_WAY') AND status=1 ORDER BY field_mapping";
	public static String QUERY_MASTERDATA_BYCODE = "SELECT CODE ,NAME, MASTERDATA_GROUP_CODE FROM ADM_MASTERDATA WHERE TRIM(UPPER(MASTERDATA_GROUP_CODE)) IN (SELECT TRIM(UPPER(regexp_substr(?,'[^$]+', 1, level))) AS GROUP_TYPE FROM dual CONNECT BY regexp_substr(?, '[^$]+', 1, level) IS NOT NULL ) AND CODE = ? AND STATUS = 1";
	public static String QUERY_ROLENAME_BYCODE = "SELECT ROLE_CODE, ROLE_NAME FROM AUTH_ROLE WHERE TRIM(UPPER(ROLE_CODE)) IN (SELECT TRIM(UPPER(regexp_substr(?,'[^$]+', 1, level))) AS GROUP_TYPE FROM dual CONNECT BY regexp_substr(?, '[^$]+', 1, level) IS NOT NULL ) AND STATUS = 1";

	// public static String VALIDATE_QUERY_PSTMT =
	// "SELECT sf.field_dir, sf.field_type, sf.length, sf.mandatory FROM serv_fields sf LEFT JOIN adm_serv s ON s.serv_code = sf.serv_code WHERE s.serv_name=? AND sf.io='INPUT' AND (sf.length IS NOT NULL OR sf.mandatory IS NOT NULL) AND s.status=1";
	public static String QUERY_MUCDICHVAY = "SELECT DISTINCT MUC_DICH_VAY_CAP_1,MUC_DICH_VAY_CAP_2,MUC_DICH_VAY_CAP_3,MUC_DICH_VAY_CAP_4,MUC_DICH_VAY_CAP_5 FROM EIB_MUCDICHVAY WHERE CODE_1 =? AND CODE_2 =? AND CODE_3 =? AND CODE_4 =? AND CODE_5 =?  AND STATUS = 1";
	public static String QUERY_CODE_6 = "SELECT DISTINCT MUC_DICH_VAY_CAP_6 FROM EIB_MUCDICHVAY WHERE CODE_6 =?  AND STATUS = 1";
}