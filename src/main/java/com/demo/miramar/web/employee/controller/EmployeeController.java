package com.demo.miramar.web.employee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.demo.miramar.util.StringUtil;
import com.demo.miramar.web.employee.model.EmployeeModelJPA;
import com.demo.miramar.web.employee.repository.EmployeeJPARepository;
import com.demo.miramar.web.employee.service.EmployeeService;
import com.demo.miramar.web.employee.service.EmployeeServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class EmployeeController extends ControllerAbstract{

	

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeJPARepository employeeJPARepository;

	@Autowired
	DataSource dataSource;

	/* 員工登入 */
	@ResponseBody
	@RequestMapping(value = "/api/loginEmployee", method = RequestMethod.POST)
	public JsonObject loginEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getCode());

		 List<EmployeeModelJPA> resultList = employeeJPARepository.findCheckMemberAccount(jsonObject.get("loginAccount").getAsString(),
				jsonObject.get("loginCode").getAsString());
		if (!resultList.isEmpty()) {
			for (int i = 0; i < resultList.size(); i++) {
				System.out.println(resultList.get(i).toJsonObject());
			}
			
			// 加入loginSessionMap
			String loginSession = UUID.randomUUID().toString().toUpperCase();
			MiramarLoginSessionMap.getInstance().addLoginSession(resultList.get(0).getEmployeeId(), loginSession);
			ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.SUCCESS.getCode());
			
		}
		return jsonObject;
	}

	/* 修改員工 */
	@ResponseBody
	@RequestMapping(value = "/api/updateEmployee", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public  String updateEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		System.out.println("debug01");
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getCode());
		System.out.println("debug02");
		EmployeeModel employeeRequest = gson.fromJson(jsonObject.toString(), EmployeeModel.class);
		System.out.println("debug03");
		//檢查有無登入
		if(MiramarLoginSessionMap.getInstance().extensionLoginSession(employeeRequest.getEmployeeId())) {
			System.out.println("debug05");
		EmployeeModelJPA employeeModel = new EmployeeModelJPA();
		employeeModel.setEmployeeName(employeeRequest.getEmployeeName());
		employeeModel.setEmployeeStatus(employeeRequest.getEmployeeStatus());
		employeeModel.setLoginCode(employeeRequest.getLoginCode());
		employeeModel.setModifiedBy(employeeRequest.getEmployeeId());

		final Date today = new Date();
		employeeModel.setModifiedTime(today);

		int result = employeeService.updateEmployee(employeeModel);
		ResultCommon.setResponseCode(jsonObject, result);
		}
		System.out.println("debug04");
		System.out.println(jsonObject);
		
		return jsonObject.toString();
	}
	
	/* 新增員工 */
	@ResponseBody
	@RequestMapping(value = "/api/addEmployee", method = RequestMethod.POST)
	public JsonObject addEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getCode());
		EmployeeModel employeeRequest = gson.fromJson(jsonObject.toString(), EmployeeModel.class);
		
		//檢查有無登入
		if(MiramarLoginSessionMap.getInstance().extensionLoginSession(employeeRequest.getEmployeeId())) {
		EmployeeModelJPA employeeModel = new EmployeeModelJPA();
		employeeModel.setEmployeeId(employeeRequest.getEmployeeId());
		employeeModel.setEmployeeName(employeeRequest.getEmployeeName());
		employeeModel.setEmployeeStatus("Active");
		employeeModel.setLoginAccount(employeeRequest.getLoginAccount());
		employeeModel.setLoginCode(employeeRequest.getLoginCode());
		employeeModel.setCreateBy(employeeRequest.getEmployeeId());
		employeeModel.setModifiedBy(employeeRequest.getEmployeeId());

		final Date today = new Date();
		employeeModel.setCreateTime(today);
		employeeModel.setModifiedTime(today);


		int result = employeeService.addEmployee(employeeModel);
		ResultCommon.setResponseCode(jsonObject, result);
		}
		return jsonObject;
	}
	
	/* 刪除員工*/
	@ResponseBody
	@RequestMapping(value = "/api/deleteEmployee", method = RequestMethod.POST)
	public JsonObject deleteEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getCode());

		EmployeeModel employeeRequest = gson.fromJson(jsonObject.toString(), EmployeeModel.class);
		if(StringUtil.checkString(employeeRequest.getEmployeeId())){
			int result = employeeService.deleteEmployee(employeeRequest.getEmployeeId());
			ResultCommon.setResponseCode(jsonObject, result);
		}

		return jsonObject;
	}
	

	@RequestMapping("/")
	public String hello() {
		return "Hey, Spring Boot 的 Hello World !";
	}

}
