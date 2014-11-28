package com.tools;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONtoMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7337697872960029487L;
	private final String json = "{\"name\":\"Oliver Busse\", \"age\":\"42\", \"city\":\"Dresden\"}";

	public JSONtoMap() {

	}

	public Map<String, String> getData() {

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			map = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(map);
		return map;
	}

	public String getJson() {
		return json;
	}
}
