package com.tools;

import java.io.Serializable;
import java.util.List;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.commons.util.io.json.JsonParser;

public class HTMLOut implements Serializable {

	private static final long serialVersionUID = 7500874726144786035L;
	private String data = "";

	public HTMLOut() {
		data = "{\r\n" + "  \"data\": [\r\n" + "    {\r\n" + "      \"Field 1\": [10,20,30,40],\r\n" + "      \"Field 2\": [40,50,60,70]\r\n" + "    }\r\n" + "  ]\r\n" + "}";

	}

	@SuppressWarnings("unchecked")
	public String getOutput() {
		StringBuilder out = new StringBuilder();
		try {
			JsonJavaFactory factory = JsonJavaFactory.instanceEx;
			JsonJavaObject json = (JsonJavaObject) JsonParser.fromJson(factory, data);

			@SuppressWarnings("unused")
			List data = json.getAsList("data");

			return out.toString();
		} catch (JsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
