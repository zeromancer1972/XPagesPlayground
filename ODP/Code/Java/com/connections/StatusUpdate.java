package com.connections;

import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.sbt.services.client.connections.activitystreams.ASGroup;
import com.ibm.sbt.services.client.connections.activitystreams.ActivityStreamService;

public class StatusUpdate {
	public String postEntry(String content) {
		try {
			JsonJavaObject postPayload = new JsonJavaObject();
			postPayload.put("content", content);
			ActivityStreamService _service = new ActivityStreamService();

			return _service.postMicroblogEntry("@me", ASGroup.ALL.getGroupType(), "", postPayload);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}
}
