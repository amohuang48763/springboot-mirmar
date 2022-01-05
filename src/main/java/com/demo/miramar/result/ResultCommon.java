package com.demo.miramar.result;

import com.google.gson.JsonObject;
import com.sun.istack.logging.Logger;

public class ResultCommon {

	
	public static JsonObject setResponseCode(JsonObject jsonObject,String code) {
		jsonObject.addProperty("result", code);
		
		return jsonObject;
	}
	
}
