package com.demo.miramar.web.employee.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.miramar.MiramarLoginSessionMap;
import com.demo.miramar.controller.ControllerAbstract;
import com.demo.miramar.result.ResultCommon;
import com.demo.miramar.result.ResultContants;
import com.demo.miramar.util.StringUtil;
import com.demo.miramar.web.employee.model.EmployeeModelJPA;
import com.demo.miramar.web.employee.repository.EmployeeJPARepository;
import com.demo.miramar.web.employee.service.EmployeeService;
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
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.FAIL.getResultCode());

		 List<EmployeeModelJPA> resultList = employeeJPARepository.findCheckMemberAccount(jsonObject.get("loginAccount").getAsString(),
				jsonObject.get("loginCode").getAsString());
		if (!resultList.isEmpty()) {
			for (int i = 0; i < resultList.size(); i++) {
				System.out.println(resultList.get(i).toJsonObject());
			}
			
			// 加入loginSessionMap
			String loginSession = UUID.randomUUID().toString().toUpperCase();
			MiramarLoginSessionMap.getInstance().addLoginSession(resultList.get(0).getEmployeeId(), loginSession);
			ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.SUCCESS.getResultCode());
			
		}
		return jsonObject;
	}

	/* 修改員工 */
	@ResponseBody
	@RequestMapping(value = "/api/updateEmployee", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public  JsonObject updateEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		System.out.println("debug01");
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.FAIL.getResultCode());
		System.out.println("debug02");
		EmployeeModelJPA employeeRequest = gson.fromJson(jsonObject.toString(), EmployeeModelJPA.class);
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

		String result = employeeService.updateEmployee(employeeModel);
		ResultCommon.setResponseCode(jsonObject, result);
		}
		System.out.println("debug04");
		System.out.println(jsonObject);
		
		return jsonObject;
	}
	
	/* 新增員工 */
	@ResponseBody
	@RequestMapping(value = "/api/addEmployee", method = RequestMethod.POST)
	public JsonObject addEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getResultCode());
		EmployeeModelJPA employeeRequest = gson.fromJson(jsonObject.toString(), EmployeeModelJPA.class);
		
		//檢查有無登入
//		if(MiramarLoginSessionMap.getInstance().extensionLoginSession(employeeRequest.getEmployeeId())) {
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


		String result = employeeService.addEmployee(employeeModel);
		ResultCommon.setResponseCode(jsonObject, result);
//		}
		return jsonObject;
	}
	
	/* 刪除員工*/
	@ResponseBody
	@RequestMapping(value = "/api/deleteEmployee", method = RequestMethod.POST)
	public JsonObject deleteEmployee(@RequestBody Object jsonObjectRequest) {

		JsonObject jsonObject = toRequestJsonObject(jsonObjectRequest);
		ResultCommon.setResponseCode(jsonObject, ResultContants.RESULT_CODE.LOGIN_FAIL.getResultCode());

		EmployeeModelJPA employeeRequest = gson.fromJson(jsonObject.toString(), EmployeeModelJPA.class);
		if(StringUtil.checkString(employeeRequest.getEmployeeId())){
			String result = employeeService.deleteEmployee(employeeRequest.getEmployeeId());
			ResultCommon.setResponseCode(jsonObject, result);
		}

		return jsonObject;
	}
	

	@RequestMapping("/")
	public String hello() {
		return "Hey, Spring Boot 的 Hello World !";
	}

}
