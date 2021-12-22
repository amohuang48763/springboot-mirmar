package com.demo.miramar.web.systemParameter.controller;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.miramar.MiramarLoginSessionMap;
import com.demo.miramar.controller.ControllerAbstract;
import com.demo.miramar.model.EmployeeModel;
import com.demo.miramar.result.ResultCommon;
import com.demo.miramar.result.ResultContants;
import com.demo.miramar.web.employee.model.EmployeeModelJPA;
import com.demo.miramar.web.systemParameter.model.SystemParameterModelJPA;
import com.demo.miramar.web.systemParameter.service.SystemParameterService;
import com.google.gson.JsonObject;

@RestController
public class SystemParameterController extends ControllerAbstract{
	
	@Autowired
	SystemParameterService systemParameterService;
	
	
	@Autowired
	DataSource dataSource;
	
	/* 新增參數 */
	@ResponseBody
	@RequestMapping(value = "/api/addSystemParameter", method = RequestMethod.POST)
	public JsonObject addSystemParameter(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getCode());
		SystemParameterModelJPA parameterRequest = gson.fromJson(jsonObject.toString(), SystemParameterModelJPA.class);
		
		parameterRequest.setCreateBy(jsonObject.get("userId").getAsString());
		parameterRequest.setModifiedBy(jsonObject.get("userId").getAsString());


		int result = systemParameterService.addSystemParameter(parameterRequest);
		ResultCommon.setResponseCode(jsonObject, result);
		
		return jsonObject;
	}
	

}
