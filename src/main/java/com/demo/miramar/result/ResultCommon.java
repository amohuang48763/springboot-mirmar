package com.demo.miramar.result;

import com.google.gson.JsonObject;

public class ResultCommon {

	
	public static JsonObject setResponseCode(JsonObject jsonObject,int code) {
		jsonObject.addProperty("result", code);
		System.out.println("test");
		return jsonObject;
	}
	
}
