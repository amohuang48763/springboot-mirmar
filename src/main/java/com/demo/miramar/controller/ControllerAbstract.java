package com.demo.miramar.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ControllerAbstract {
	
	
	public static Gson gson = new Gson();
	
	public JsonObject toRequestJsonObject(Object requestObject) {
		
		System.out.println("Request:" + requestObject.toString());
		String str = gson.toJson(requestObject);// 先用gson轉化為json
		JsonObject jsonObject = gson.fromJson(str, JsonObject.class);
		
		return jsonObject;
	}

}
