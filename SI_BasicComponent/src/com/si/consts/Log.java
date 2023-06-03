package com.si.consts;

public class Log {

	public static final String DB_CONNECT_SUCCESS = "CONNECTING TO DATABASE IS SUCCESSFUL !";
	public static final String DB_CONNECT_FAILED = "CONNECTING TO DATABASE IS FAILED !";

	public static final String DB_PROCEDURE_PARAMS = "PROCEDURE PARAMS:";
	public static final String DB_SQL = "SQL EXCUTING: ";

	public static final String FROM_INPUT = "FROM-INPUT:";
	public static final String FROM_OUTPUT = "FROM-OUPUT:";
	public static final String TO_INPUT = "TO-INPUT:";
	public static final String TO_OUTPUT = "TO-OUTPUT:";
	
	public static final String MAP_INFO = "MAP-INFO:";
	public static final String CONVERTED_INFO = "CONVERT-INFO:";

	public static final String SP_RESULT = "SP EXECUTING RESULT:";
	public static final String SQL_RESULT = "SQL RESULT: ";
	public static final String RULE_RESULT = "RULE RESULT: ";
	public static final String FUCTION_RESULT = "FUNCTION RESULT: ";
	public static final String BEFORE_CONVERTING_CODE = "BEFORE CONVERTING CODE: ";
	public static final String AFTER_CONVERTING_CODE = "AFTER CONVERTING CODE: ";

	public static final String CALLING_ODM_REST_API = "CALLING ODM REST API: ";

	public static final String PROPERTIES_READING = "READING PROPERTIES FILE AT:";

	public static final String INFO = "INFO";
	public static final String ERROR = "ERROR";

	public static final String CDATA_PROTOCOL_RESULT = "CDATA GET PROTOCOL RESULT:";
	public static final String CDATA_CALL_FROM_DB = "CDATA CALL FROM DATABASE!";
	public static final String CDATA_RESULT = "CDATA RESULT:";
	

	public static final String LINE_HYPHEN = "-----------------------------------------------";
	public static final String INTEGRATION_BEGIN = "************************ INTEGRATION BEGIN ************************";
	public static final String INTEGRATION_END = "************************* INTEGRATION END *************************";

	public static final String EXECUTING_MAPPING = "Executing extract data from business object: ";

	public static final String SI_LOG_INSERT_STATEMENT = "INSERT INTO SI_LOG (service, case_id, case_id_ref, bpm_input, bpm_output, si_input, si_output, error_code, error_message, stacktrace, log_code, parent_code, start_time, finish_time, instance_info, process_data, caller, target_system) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SI_LOG_ID_QUERY_STATEMENT = "select log_id from si_log where log_code=?";
	
	// LOG CALLER
	public static final String BPM_CALLER = "BPM";
	public static final String SMB_AUTO_CALLER = "SMB-AUTO";
}
