package com.wwy.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MessageAssembler {
	
	public static String assembleMessageJson(Message message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("command", message.getCommand());
		JsonArray jsonArray = new JsonArray();
		
		jsonObject.add("params", jsonArray);
		for(int param : message.getParams()) {
			JsonObject jsonParam = new JsonObject();
			jsonParam.addProperty("param", param);
			jsonArray.add(jsonParam);
		}
		return jsonObject.toString();
	}

}
