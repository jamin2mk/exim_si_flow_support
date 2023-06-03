package com.si.service.invoke;

import com.google.gson.JsonElement;
import com.si.exception.SIException;

public interface Invoker {

	public JsonElement invoke(JsonElement input) throws SIException;

	public JsonElement automap_input(JsonElement data) throws SIException;

	public JsonElement automap_output(JsonElement output, JsonElement data) throws SIException;

}
