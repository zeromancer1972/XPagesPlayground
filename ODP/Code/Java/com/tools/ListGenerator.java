package com.tools;

import java.util.ArrayList;
import java.util.Iterator;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.commons.util.io.json.JsonParser;

public class ListGenerator {
	public ListGenerator() {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Object> getList(String json) {
		// String json =
		// "{\"success\":true,\"total\":1,\"data\": [{\"lastname\":\"Busse\",\"firstname\":\"Oliver\",\"email\":\"obusse@gmail.com\"},{\"lastname\":\"Blume\",\"firstname\":\"Heike\",\"email\":\"mardou@mardou.de\"}]}";
		ArrayList map = new ArrayList<Object>();

		try {
			JsonJavaFactory factory = JsonJavaFactory.instanceEx;
			JsonJavaObject obj = (JsonJavaObject) JsonParser.fromJson(factory, json);
			Object arrData = factory.getProperty(obj, "data");
			for (Iterator<Object> itData = factory.iterateArrayValues(arrData); itData.hasNext();) {
				Object o = itData.next();
				map.add(o);
			}
		} catch (JsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}
}
