package com.demo.miramar.repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.miramar.model.EmployeeModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class EmployeeRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addEmployee(EmployeeModel employeeModel) throws SQLIntegrityConstraintViolationException {
		System.out.println("EXCUTE INSERT MEMBER");
		jdbcTemplate.update(
				"INSERT INTO employee(" + "employee_id, " + "employee_name, " + "login_account, " + "login_code, "
						+ "employee_status, " + "create_time, " + "create_by, " + "modified_time, " + "modified_by) "
						+ "VALUES (?,?,?,?,?,?,?,?,?)",
				employeeModel.getEmployeeId(), employeeModel.getEmployeeName(), employeeModel.getLoginAccount(),
				employeeModel.getLoginCode(), employeeModel.getEmployeeStatus(), employeeModel.getCreateTime(),
				employeeModel.getCreateBy(), employeeModel.getModifiedTime(), employeeModel.getModifiedBy());

	}

	public EmployeeModel findEmployee(String login_account, String loginCode) {
		EmployeeModel returnValue = null;
		System.out.println("EXCUTE FIND USER");
		List<EmployeeModel> resultList = jdbcTemplate.query(
				"SELECT "
				+ "employee_id, "
				+ "employee_name, "
				+ "login_account, "
				+ "login_code, "
				+ "employee_status, "
				+ "create_time, "
				+ "create_by, "
				+ "modified_time, "
				+ "modified_by "
				+ "FROM employee WHERE login_account = ? AND login_code = ?",
				new Object[] {login_account,loginCode},
				new BeanPropertyRowMapper<EmployeeModel>(EmployeeModel.class));
		System.out.println("resultList size :"+resultList.size());
		if(!resultList.isEmpty()) {
		
			Gson gson = new Gson();
			System.out.println(resultList.get(0));
			JsonObject json = gson.toJsonTree(resultList.get(0)).getAsJsonObject();
			returnValue	= gson.fromJson(json, EmployeeModel.class);
		}
		// TODO Auto-generated method stub
		return returnValue;
	}
}
