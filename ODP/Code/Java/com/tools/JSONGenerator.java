package com.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonGenerator;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;

public class JSONGenerator {
	public JSONGenerator() {

	}

	public String getJson() {
		String json = "";

		List<Map<String, String>> entries = new ArrayList<Map<String, String>>();

		Map<String, String> data = new HashMap<String, String>();
		data.put("firstname", "Oliver");
		data.put("lastname", "Busse");
		data.put("email", "obusse@gmail.com");

		entries.add(data);

		data = new HashMap<String, String>();
		data.put("firstname", "Heike");
		data.put("lastname", "Blume");
		data.put("email", "mardou@mardou.de");

		entries.add(data);

		data = new HashMap<String, String>();
		data.put("firstname", "Mel");
		data.put("lastname", "Bourne");
		data.put("email", "---");

		entries.add(data);

		JsonJavaObject returnJson = new JsonJavaObject();
		returnJson.put("success", true);
		returnJson.put("total", entries.size());
		returnJson.put("data", entries);

		try {
			json = JsonGenerator.toJson(JsonJavaFactory.instanceEx, returnJson);
		} catch (JsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
