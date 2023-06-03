package com.si.helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.JDBC;
import com.si.consts.Log;
import com.si.exception.SIException;
import com.si.model.serviceconfig.DBConfig;

public class JDBC_Helper {

	public static Connection getConnection(String dataSourceName) throws SIException {

		Connection connection = null;
		try {
			Class.forName(JDBC.ORACLE_DRIVER);

			if (dataSourceName.equalsIgnoreCase("SAALEM_116")) {

				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.18.116:1521:orcl", "SAALEM", "Hpt123456");

			} else if (dataSourceName.equalsIgnoreCase("SAALEM_51")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.62.51:1521:orcl", "SAALEM", "Full3car");

			} else if (dataSourceName.equalsIgnoreCase("SAALEM_UAT")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.62.61:1521:orcl", "SAALEM", "Full3car");

			} else if (dataSourceName.equalsIgnoreCase("LOS_2019_116")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.18.116:1521:orcl", "LOS_2019", "Hpt123456");

			} else if (dataSourceName.equalsIgnoreCase("SAALOP_LOG_116")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.18.116:1521:orcl", "SAALOP_LOG", "Hpt123456");

			} else if (dataSourceName.equalsIgnoreCase("LOS_2019_224")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.53.252.224:1521:rlos", "LOS_2019", "P4ssw0rd");

			} else {
				Context ctx = new InitialContext();
				DataSource dataSource = (DataSource) ctx.lookup(dataSourceName);
				connection = dataSource.getConnection();
			}

			if (connection != null) {
				String url = String.format("URL: %s", connection.getMetaData().getURL());
				String userName = String.format("USER: %s", connection.getMetaData().getUserName());
				LogHelper.writeLog(Log.INFO, Log.DB_CONNECT_SUCCESS, url, userName);

			} else {
				throw new SIException(Error.DB_99, "Error in connecting to database");
			}
		} catch (SQLException | ClassNotFoundException | NamingException e) {
			throw new SIException(Error.DB_99, "Error in connecting to database", e);
		}

		return connection;
	}

	/** 28.Dec.2022 - BEGIN **/

	/* PROCEDUCE [external] */
	public static String invoke_CallableStmt(String dataSourceName, DBConfig protocol, List<String> dataInputs) throws SIException {

		String result = null;
		Connection connection = null;

		try {

			connection = getConnection(dataSourceName);
			result = execute_CallableStmt(connection, protocol, dataInputs);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, "Error in closing database's connection.", e);
				}
			}
		}

		return result;

	}

	public static String invoke_CallableStmt(String dataSourceName, String sp, List<String> dataInputs) throws SIException {

		String result = null;
		Connection connection = null;

		try {

			connection = getConnection(dataSourceName);
			result = execute_CallableStmt(connection, sp, dataInputs);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, "Error in closing database's connection.", e);
				}
			}
		}

		return result;

	}

	/* PROCEDUCE [external] - 03.Jan.2023 */
	public static String invoke_CallableStmt(JsonArray inputs, String dataSourceName, DBConfig protocol) throws SIException {

		String result = null;
		Connection connection = null;

		try {

			connection = getConnection(dataSourceName);
			result = execute_CallableStmt(connection, protocol, inputs);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, "Error in closing database's connection.", e);
				}
			}
		}

		return result;

	}

	/* FUNCTION [external] - 18.May.2023 */
	public static String invoke_CallableStmt_4Funcion(JsonArray inputs, String dataSourceName, DBConfig protocol) throws SIException {

		String result = null;
		Connection connection = null;

		try {

			connection = getConnection(dataSourceName);
			result = execute_CallableStmt_4Function(connection, protocol, inputs);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, "Error in closing database's connection.", e);
				}
			}
		}

		return result;

	}

	/* PROCEDUCE [internal] - level 1 */
	public static String execute_CallableStmt(Connection connection, DBConfig protocol, List<String> dataInputs) throws SIException {

		String result = null;
		CallableStatement callableStatement = null;

		try {
			// prepare for create CallableStatement
			int inputSize = protocol.getInput().getType().size();
			int outputSize = protocol.getOutput().getType().size();
			int paramSize = inputSize + outputSize;

			StringBuilder params = new StringBuilder("?");
			for (int i = 0; i < paramSize - 1; i++) {
				params.append(",?");
			}

			String sp = protocol.getSp();
			String stmt = String.format("{call %s (%s)}", sp, params);

			try {
				callableStatement = connection.prepareCall(stmt);
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, "Error in initializing callable statement", e);
			}

			// set input
			for (int i = 0; i < inputSize; i++) {
				try {
					String iData = dataInputs.get(i);
					callableStatement.setString(i + 1, iData);
				} catch (Exception e) {
					throw new SIException(Error.DB_99, "Error in register input parameter", e);
				}
			}

			// register output
			for (int i = 0; i < outputSize; i++) {
				String type = protocol.getOutput().getType().get(i);
				try {
					switch (type) {

					case "VARCHAR":
					case "VARCHAR2":
						callableStatement.registerOutParameter(inputSize + i + 1, oracle.jdbc.OracleTypes.VARCHAR);
						break;

					case "REFCUR":
						callableStatement.registerOutParameter(inputSize + i + 1, oracle.jdbc.OracleTypes.CURSOR);
						break;

					default:
						throw new SIException(Error.DB_99, String.format("Error in register output parameter. Cause by: output-type not found. SP: [%s], Type: [%s]", sp, type));
					}
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, "Error in register output parameter", e);
				}
			}

			/* execute & get result */

			try {
				callableStatement.execute();
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, String.format("Error in executing callable statement: [%s], input-size: [%s], output-size: [%s]", stmt, inputSize, outputSize), e);
			}

			JsonArray result_JA = new JsonArray();

			for (int i = 0; i < outputSize; i++) {
				String type = protocol.getOutput().getType().get(i);
				try {
					switch (type) {

					case "VARCHAR":
					case "VARCHAR2":
						String outString = callableStatement.getString(inputSize + i + 1);
						result_JA.add(outString);
						break;

					case "REFCUR":
						ResultSet resultSet = (ResultSet) callableStatement.getObject(inputSize + i + 1);
						JsonArray outCursor = fetchDatafrom_ResultSet(resultSet);
						result_JA.add(outCursor);
						break;
					}
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, String.format("Error in getting output value at index: [%s], type: [%s] ", inputSize + i + 1, type), e);
				}
			}
			if (protocol.getOutput().getType().size() == 1) {
				result = result_JA.get(0).toString();
			} else {
				result = result_JA.toString();
			}

		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, String.format("Error in close callable statement", e));
				}
			}
		}

		return result;
	}

	public static String execute_CallableStmt(Connection connection, String sp, List<String> dataInputs) throws SIException {

		String result = null;
		CallableStatement callableStatement = null;

		try {
			// prepare for create CallableStatement
			int inputSize = dataInputs.size();
			int outputSize = 1;
			int paramSize = inputSize + outputSize;

			StringBuilder params = new StringBuilder("?");
			for (int i = 0; i < paramSize - 1; i++) {
				params.append(",?");
			}

			String stmt = String.format("{call %s (%s)}", sp, params);

			try {
				callableStatement = connection.prepareCall(stmt);
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, "Error in initializing callable statement", e);
			}

			// set input
			for (int i = 0; i < inputSize; i++) {
				try {
					String iData = dataInputs.get(i);
					callableStatement.setString(i + 1, iData);
				} catch (Exception e) {
					throw new SIException(Error.DB_99, "Error in register input parameter", e);
				}
			}

			// register output
			try {
				callableStatement.registerOutParameter(inputSize + 1, oracle.jdbc.OracleTypes.CURSOR);
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, "Error in register output parameter", e);
			}

			/* execute & get result */
			try {
				callableStatement.execute();
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, String.format("Error in executing callable statement: [%s], input-size: [%s], output-size: [%s]", stmt, inputSize, outputSize), e);
			}

			try {
				ResultSet resultSet = (ResultSet) callableStatement.getObject(inputSize + 1);
				JsonArray outCursor = fetchDatafrom_ResultSet(resultSet);
				result = outCursor.toString();
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, String.format("Error in getting output value at index: [%s], type: [%s] ", inputSize + 1, oracle.jdbc.OracleTypes.CURSOR), e);
			}

		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, String.format("Error in close callable statement", e));
				}
			}
		}

		return result;
	}

	/* PROCEDUCE [internal] - level 1 - 03.Jan.2023 */
	public static String execute_CallableStmt(Connection connection, DBConfig protocol, JsonArray inputs) throws SIException {

		String result = null;
		CallableStatement callableStatement = null;

		try {
			// prepare for create CallableStatement
			int inputSize = protocol.getInput().getType().size();
			int outputSize = protocol.getOutput().getType().size();
			int paramSize = inputSize + outputSize;

			StringBuilder params = new StringBuilder("?");
			for (int i = 0; i < paramSize - 1; i++) {
				params.append(",?");
			}

			String sp = protocol.getSp();
			String stmt = String.format("{call %s (%s)}", sp, params);

			try {
				callableStatement = connection.prepareCall(stmt);
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, "Error in initializing callable statement", e);
			}

			// set input
			for (int i = 0; i < inputSize; i++) {
				String inType = protocol.getInput().getType().get(i);
				try {
					switch (inType) {
					case "NUMBER":
					case "VARCHAR":
					case "VARCHAR2":
						String inString = inputs.get(i).getAsJsonObject().get("item").getAsString();
						callableStatement.setString(i + 1, inString);
						break;
					case "CLOB":
						JsonElement inClob = inputs.get(i).getAsJsonObject().get("item");
						if (inClob.isJsonPrimitive()) {
							callableStatement.setString(i + 1, inClob.getAsString());
						} else {
							callableStatement.setString(i + 1, inClob.toString());
						}
						break;

					default:
						throw new SIException(Error.DB_99, String.format("Error in set input parameter. Cause by: input-type not found. SP: [%s], Type: [%s]", sp, inType));
					}

				} catch (Exception e) {
					throw new SIException(Error.DB_99, "Error in register input parameter", e);
				}
			}

			// register output
			for (int i = 0; i < outputSize; i++) {
				String type = protocol.getOutput().getType().get(i);
				try {
					switch (type) {

					case "VARCHAR":
					case "VARCHAR2":
						callableStatement.registerOutParameter(inputSize + i + 1, oracle.jdbc.OracleTypes.VARCHAR);
						break;

					case "REFCUR":
						callableStatement.registerOutParameter(inputSize + i + 1, oracle.jdbc.OracleTypes.CURSOR);
						break;

					default:
						throw new SIException(Error.DB_99, String.format("Error in register output parameter. Cause by: output-type not found. SP: [%s], Type: [%s]", sp, type));
					}
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, "Error in register output parameter", e);
				}
			}

			/* execute & get result */

			try {
				callableStatement.execute();
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, String.format("Error in executing callable statement: [%s], input-size: [%s], output-size: [%s]", stmt, inputSize, outputSize), e);
			}

			JsonArray result_JA = new JsonArray();

			for (int i = 0; i < outputSize; i++) {
				String type = protocol.getOutput().getType().get(i);
				try {
					switch (type) {

					case "VARCHAR":
					case "VARCHAR2":
						String outString = callableStatement.getString(inputSize + i + 1);
						result_JA.add(outString);
						break;

					case "REFCUR":
						ResultSet resultSet = (ResultSet) callableStatement.getObject(inputSize + i + 1);
						JsonArray outCursor = fetchDatafrom_ResultSet(resultSet);
						result_JA.add(outCursor);
						break;
					}
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, String.format("Error in getting output value at index: [%s], type: [%s] ", inputSize + i + 1, type), e);
				}
			}
			if (protocol.getOutput().getType().size() == 1) {
				result = result_JA.get(0).toString();
			} else {
				result = result_JA.toString();
			}

		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, String.format("Error in close callable statement", e));
				}
			}
		}

		return result;
	}

	/* FUNCTION [internal] - level 1 - 18.May.2023 */
	public static String execute_CallableStmt_4Function(Connection connection, DBConfig protocol, JsonArray inputs) throws SIException {

		String result = null;
		CallableStatement callableStatement = null;

		try {
			// prepare for create CallableStatement
			int inputSize = protocol.getInput().getType().size();
			int outputSize = protocol.getOutput().getType().size();
//			int paramSize = inputSize + outputSize;

			StringBuilder params = new StringBuilder("?");
			for (int i = 1; i < inputSize - 1; i++) {
				params.append(",?");
			}

			String sp = protocol.getSp();
//			String stmt = String.format("{call %s (%s)}", sp, params);
			String stmt = String.format("{? = call %s (%s)}", sp, params);

			try {
				callableStatement = connection.prepareCall(stmt);
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, "Error in initializing callable statement", e);
			}

			// set input
			for (int i = 0; i < inputSize; i++) {
				String inType = protocol.getInput().getType().get(i);
				try {
					switch (inType) {
					case "NUMBER":
					case "VARCHAR":
					case "VARCHAR2":
						String inString = inputs.get(i).getAsJsonObject().get("item").getAsString();
						callableStatement.setString(i + 2, inString);
						break;
					case "CLOB":
						JsonElement inClob = inputs.get(i).getAsJsonObject().get("item");
						if (inClob.isJsonPrimitive()) {
							callableStatement.setString(i + 2, inClob.getAsString());
						} else {
							callableStatement.setString(i + 2, inClob.toString());
						}
						break;

					default:
						throw new SIException(Error.DB_99, String.format("Error in set input parameter. Cause by: input-type not found. SP: [%s], Type: [%s]", sp, inType));
					}

				} catch (Exception e) {
					throw new SIException(Error.DB_99, "Error in register input parameter", e);
				}
			}

			// register output
			String type = protocol.getOutput().getType().get(0);
			try {
				switch (type) {

				case "VARCHAR":
				case "VARCHAR2":
					callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
					break;

				case "REFCUR":
					callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
					break;

				default:
					throw new SIException(Error.DB_99, String.format("Error in register output parameter. Cause by: output-type not found. SP: [%s], Type: [%s]", sp, type));
				}
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, "Error in register output parameter", e);
			}

			/* execute & get result */

			try {
				callableStatement.execute();
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, String.format("Error in executing callable statement: [%s], input-size: [%s], output-size: [%s]", stmt, inputSize, outputSize), e);
			}

			JsonArray result_JA = new JsonArray();

			try {
				switch (type) {

				case "VARCHAR":
				case "VARCHAR2":
					String outString = callableStatement.getString(1);
					result_JA.add(outString);
					break;

				case "REFCUR":
					ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
					JsonArray outCursor = fetchDatafrom_ResultSet(resultSet);
					result_JA.add(outCursor);
					break;
				}
			} catch (SQLException e) {
				throw new SIException(Error.DB_99, String.format("Error in getting output value at index: [%s], type: [%s] ", 1, type), e);
			}

			if (protocol.getOutput().getType().size() == 1) {
				result = result_JA.get(0).toString();
			} else {
				result = result_JA.toString();
			}

		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					throw new SIException(Error.DB_99, String.format("Error in close callable statement", e));
				}
			}
		}

		return result;
	}

	/* PROCEDUCE [internal] - level 2 */
	public static JsonArray fetchDatafrom_ResultSet(ResultSet resultSet) throws SIException {

		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();

		try {
			while (resultSet.next()) {

				jsonObject = new JsonObject();

				for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {

					String columnName = resultSet.getMetaData().getColumnName(i + 1);
					String value = resultSet.getString(i + 1);

					jsonObject.addProperty(columnName, value == null ? "" : value);
				}

				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			throw new SIException(Error.DB_99, "Error in fetching data from ResultSet", e);
		}

		return jsonArray;
	}

	/** 28.Dec.2022 - END **/

	/* UPDATE - PROCEDUCE - using */
	public static String updateByProcedure_out1varchar(String procedureName, String value, String dataSourceName) throws Exception {

		String logResult = null;
		Connection connection = null;

		try {
			connection = getConnection(dataSourceName);
			logResult = executeCallableStatement_out1varchar(connection, procedureName, value);

		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return logResult;

	}

	/* QUERY - PREPARE STATEMENT - using */
	public static JsonArray queryByPrepareStatement(String sql, String params, String dataSourceName) throws Exception {

		JsonArray result = null;
		Connection connection = null;

		try {
			connection = getConnection(dataSourceName);
			result = executeQueryPrepareStatement(connection, sql, params);

		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	/** Using for Load Mapping data with Caching - 28.Dec.2022 */
	public static JsonArray queryByPrepareStatement_2(String sql, String params, String dataSourceName) throws Exception {

		JsonArray result = null;
		Connection connection = null;

		try {
			connection = getConnection(dataSourceName);
			result = executeQueryPrepareStatement_2(connection, sql, params);

		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	/** Using for Load Mapping data with Caching - 11.Feb.2023 */
	public static JsonArray queryByPrepareStatement_3(String sql, String[] params, String dataSourceName) throws Exception {

		JsonArray result = null;
		Connection connection = null;

		try {
			connection = getConnection(dataSourceName);
			result = executeQueryPrepareStatement_3(connection, sql, params);

		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	/* EXECUTE UPDATE - PROCEDUCE - using */
	public static String executeCallableStatement_out1varchar(Connection connection, String procedureName, String value) throws SQLException {

		String logResult = null;
		CallableStatement callableStatement = null;

		try {

			// prepare for create CallableStatement
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?)}";
			callableStatement = connection.prepareCall(sql);

			// set input & register output
			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}

			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count + "_OracleTypes.VARCHAR";

			/** writing log **/
			String logSql = String.format("SQL string: %s", sql);
			String logParams = String.format("Params: %s", log);
			LogHelper.writeLog(Log.INFO, Log.DB_SQL, logSql, logParams);

			/* execute & get result */
			callableStatement.execute();
			logResult = callableStatement.getString(count);

		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
		}

		return logResult;
	}

	/* EXECUTE QUERY - PREPARE STATEMENT - using */
	private static JsonArray executeQueryPrepareStatement(Connection connection, String sql, String params) throws Exception {

		JsonArray result = null;
		PreparedStatement pStmt = null;
		ResultSet resultSet = null;
		try {
			pStmt = connection.prepareStatement(sql);
			String log = "";
			String logParams = "";

			if (params != null) {
				String[] value_split = params.split("\\|");
				int count = 0;

				for (int i = 0; i < value_split.length; i++) {
					pStmt.setString(++count, value_split[i]);
					log += count + "_" + value_split[i] + " :: ";
				}
				logParams = String.format("Params: %s", log);
			}
			/** writing log **/
			String logSql = String.format("SQL string: %s", sql);
			LogHelper.writeLog(Log.INFO, Log.DB_SQL, logSql, logParams);

			// execute & convert result
			resultSet = pStmt.executeQuery();
			result = convertResultSetToJsonElement(resultSet);

		} catch (Exception e) {
			throw new Exception(String.format("%s <Actual-value>: PreparedStatement=[%s]; params=[%s]", e.getMessage(), sql, params));

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
		}
		return result;
	}

	/** Using for Load Mapping data with Caching - 28.Dec.2022 */
	private static JsonArray executeQueryPrepareStatement_2(Connection connection, String sql, String params) throws Exception {

		JsonArray result = null;
		PreparedStatement pStmt = null;
		ResultSet resultSet = null;
		try {
			pStmt = connection.prepareStatement(sql);
			String log = "";
			String logParams = "";

			if (params != null) {
				String[] value_split = params.split("\\|");
				int count = 0;

				for (int i = 0; i < value_split.length; i++) {
					pStmt.setString(++count, value_split[i]);
					log += count + "_" + value_split[i] + " :: ";
				}
				logParams = String.format("Params: %s", log);
			}
			/** writing log **/
			String logSql = String.format("SQL string: %s", sql);
			LogHelper.writeLog(Log.INFO, Log.DB_SQL, logSql, logParams);

			// execute & convert result
			resultSet = pStmt.executeQuery();
			result = convertResultSetToJsonElement_2(resultSet);

		} catch (Exception e) {
			throw new Exception(String.format("%s <Actual-value>: PreparedStatement=[%s]; params=[%s]", e.getMessage(), sql, params));

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
		}
		return result;
	}

	/** Using for Load Mapping data with Caching - 11.Feb.2023 */
	private static JsonArray executeQueryPrepareStatement_3(Connection connection, String sql, String[] params) throws Exception {

		JsonArray result = null;
		PreparedStatement pStmt = null;
		ResultSet resultSet = null;
		try {
			pStmt = connection.prepareStatement(sql);
			String log = "";
			String logParams = "";

			int count = 0;
			for (int i = 0; i < params.length; i++) {
				pStmt.setString(++count, params[i]);
				log += count + "_" + params[i] + " :: ";
			}
			logParams = String.format("Params: %s", log);

			/** writing log **/
			String logSql = String.format("SQL string: %s", sql);
			LogHelper.writeLog(Log.INFO, Log.DB_SQL, logSql, logParams);

			// execute & convert result
			resultSet = pStmt.executeQuery();
			result = convertResultSetToJsonElement_3(resultSet);

		} catch (Exception e) {
			throw new Exception(String.format("%s <Actual-value>: PreparedStatement=[%s]; params=[%s]", e.getMessage(), sql, params));

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
		}
		return result;
	}

	public static int executePreparedStmtNonQuery(Connection connection, String sql, String value) throws SQLException {

		PreparedStatement pStmt = connection.prepareStatement(sql);
		String log = "";

		if (value != null) {

			String[] value_split = value.split("\\|");
			int count = 0;

			for (int i = 0; i < value_split.length; i++) {
				if (value_split[i].equalsIgnoreCase("NULL")) {
					pStmt.setString(++count, null);
				} else {
					pStmt.setString(++count, value_split[i]);
				}
				log += count + "_" + value_split[i] + " :: ";
			}
		}

		/** writing log **/
		String logSql = String.format("SQL string: %s", sql);
		String logParams = String.format("Params: %s", log);
		LogHelper.writeLog(Log.INFO, Log.DB_SQL, logSql, logParams);

		return pStmt.executeUpdate();
	}

	public static String executeProcedureNonQuery(Connection connection, String procedureName, String value) throws SQLException {

		String logResult = null;
		CallableStatement callableStatement = null;
		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?)}";
			callableStatement = connection.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}

			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count + "_OracleTypes.VARCHAR2";

			/** writing log **/
			String logSql = String.format("SQL string: %s", sql);
			String logParams = String.format("Params: %s", log);
			LogHelper.writeLog(Log.INFO, Log.DB_SQL, logSql, logParams);

			callableStatement.execute();
			logResult = callableStatement.getString(count);

		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
		}

		return logResult;

	}

	private static JsonArray convertResultSetToJsonElement(ResultSet resultSet) throws SQLException {

		JsonArray result = new JsonArray();
		if (resultSet != null) {
			while (resultSet.next()) {

				JsonObject jsonObject = new JsonObject();
				int columnCount = resultSet.getMetaData().getColumnCount();

				for (int i = 0; i < columnCount; i++) {
					jsonObject.addProperty(resultSet.getMetaData().getColumnName(i + 1).toUpperCase(), resultSet.getString(i + 1));
				}
				result.add(jsonObject);
			}
		}
		return result;
	}

	/** Using for Load Mapping data with Caching - 28.Dec.2022 */
	private static JsonArray convertResultSetToJsonElement_2(ResultSet resultSet) throws SQLException {

		JsonArray result = new JsonArray();
		if (resultSet != null) {
			while (resultSet.next()) {

				JsonObject jsonObject = new JsonObject();
				int columnCount = resultSet.getMetaData().getColumnCount();

				for (int i = 0; i < columnCount; i++) {
					if (resultSet.getString(i + 1) != null) {
						jsonObject.addProperty(resultSet.getMetaData().getColumnName(i + 1).toLowerCase(), resultSet.getString(i + 1));
					}
				}
				result.add(jsonObject);
			}
		}
		return result;
	}

	/** Using for Load Mapping data with Caching - 11.Feb.2023 */
	private static JsonArray convertResultSetToJsonElement_3(ResultSet resultSet) throws SQLException {

		JsonArray result = new JsonArray();
		if (resultSet != null) {
			while (resultSet.next()) {

				JsonObject jsonObject = new JsonObject();
				int columnCount = resultSet.getMetaData().getColumnCount();

				for (int i = 0; i < columnCount; i++) {
					if (resultSet.getString(i + 1) != null) {
						jsonObject.addProperty(resultSet.getMetaData().getColumnName(i + 1).toLowerCase(), resultSet.getString(i + 1));
					}
				}
				result.add(jsonObject);
			}
		}
		return result;
	}

	public static JsonObject customizeResult(ResultSet resultSet) throws SQLException {

		JsonObject result = new JsonObject();

		while (resultSet.next()) {

			int columnCount = resultSet.getMetaData().getColumnCount();

			for (int i = 0; i < columnCount; i++) {

				String columnName = resultSet.getMetaData().getColumnName(i + 1);
				String value = resultSet.getString(i + 1);

				String key = null;

				// remapping sp result
				switch (columnName) {
				case "LOG_ID":
				case "LOG_CODE":
					key = "errorCode";
					break;

				default:
					break;
				}

				result.addProperty(key, value);
			}
		}

		return result;
	}

	public static JsonObject customizeResultNonQuery(int row) {

		JsonObject result = new JsonObject();

		if (row > 0) {
			result.addProperty("errorCode", "00");
			result.addProperty("errorMessage", "Success");
		} else {
			result.addProperty("errorCode", "99");
			result.addProperty("errorMessage", "Fail");
		}
		return result;
	}

	public static CallableStatement Execute(Connection connObj, String procedureName, String value) {

		CallableStatement callableStatement = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?,?,?)}";
			callableStatement = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR2" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR2" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.CURSOR);
			log += count + "_OracleTypes.CURSOR";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			callableStatement.execute();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return callableStatement;
	}

	public static CallableStatement execute2(Connection connObj, String procedureName, String value) {

		CallableStatement callableStatement = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?,?,?)}";
			callableStatement = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR2" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR2" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.CLOB);
			log += count + "_OracleTypes.CLOB";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			callableStatement.execute();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return callableStatement;
	}

	public static CallableStatement execute1Cursor(Connection connObj, String procedureName, String value) {

		CallableStatement callableStatement = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?,?,?)}";
			callableStatement = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.CURSOR);
			log += count + "_OracleTypes.CURSOR";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			callableStatement.execute();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return callableStatement;
	}

	public static CallableStatement executeJust1Cursor(Connection connObj, String procedureName, String value) {

		CallableStatement callableStatement = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?)}";
			callableStatement = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.CURSOR);
			log += count + "_OracleTypes.CURSOR";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			callableStatement.execute();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return callableStatement;
	}

	public static CallableStatement execute2Cursor(Connection connObj, String procedureName, String value) {

		CallableStatement callableStatement = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?,?,?,?)}";
			callableStatement = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.CURSOR);
			log += count++ + "_OracleTypes.CURSOR" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.CURSOR);
			log += count + "_OracleTypes.CURSOR";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			callableStatement.execute();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return callableStatement;
	}

	public static CallableStatement executeNonCursor(Connection connObj, String procedureName, String value) {

		CallableStatement callableStatement = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\|");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?,?)}";
			callableStatement = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				callableStatement.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count++ + "_OracleTypes.VARCHAR" + "::";
			callableStatement.registerOutParameter(count, oracle.jdbc.OracleTypes.VARCHAR);
			log += count + "_OracleTypes.VARCHAR";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			callableStatement.execute();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return callableStatement;
	}

	public static JsonObject MappingData(ResultSet resultSet) throws SQLException {

		JsonObject resultJsonObject = null;
		try {
			resultJsonObject = new JsonObject();

			while (resultSet.next()) {
				for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {

					String columnName = resultSet.getMetaData().getColumnName(i + 1);

					resultJsonObject.addProperty(columnName, resultSet.getString(i + 1));

				}
			}
		} catch (Exception sqlException) {
			System.out.println(String.format("%s - %s - %s", "DB_MappingData", "ERROR", sqlException.toString()));
		}
		return resultJsonObject;
	}

	public static ResultSet execute(Connection connObj, String procedureName, String value) {
		ResultSet resultSet = null;

		if (value == null || value.equals("")) {
			return null;
		}

		try {
			String[] value_split = value.split("\\#");
			String var = "";
			for (int i = 0; i < value_split.length; i++)
				var += "?,";

			String sql = "{call " + procedureName + " (" + var + "?)}";
			CallableStatement call = connObj.prepareCall(sql);

			int count = 1;
			String log = "";
			for (int i = 0; i < value_split.length; i++) {

				call.setString(count, value_split[i].equalsIgnoreCase("null") ? null : value_split[i]);
				log += count + "_" + value_split[i] + "::";
				count++;
			}
			call.registerOutParameter(count, oracle.jdbc.OracleTypes.CURSOR);
			log += count + "_OracleTypes.CURSOR";

			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", sql));
			System.out.println(String.format("%s - %s - %s", "DB_Execute", "INFO", log));
			System.out.println();

			call.execute();
			resultSet = (ResultSet) call.getObject(count);

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			// System.out.println(String.format("%s - %s - %s", "DB_Execute",
			// "ERROR", sqlException.toString()));
		}

		return resultSet;
	}

	public static JsonArray getResultList(ResultSet resultSet) throws SQLException {

		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();

		try {
			while (resultSet.next()) {

				jsonObject = new JsonObject();

				for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {

					String columnName = resultSet.getMetaData().getColumnName(i + 1);
					String value = resultSet.getString(i + 1);

					jsonObject.addProperty(columnName, value == null ? "" : value);
				}

				jsonArray.add(jsonObject);
			}
		} catch (Exception sqlException) {
			System.out.println(String.format("%s - %s - %s", "DB_MappingData", "ERROR", sqlException.toString()));
		}
		return jsonArray;
	}
}
