package com.demo.miramar.web.employee.service;

import java.util.List;

import com.demo.miramar.web.employee.model.EmployeeModelJPA;

public interface EmployeeService {
	
	public int addEmployee(EmployeeModelJPA employeeModelJPA);
	
	public int updateEmployee(EmployeeModelJPA employeeModelJPA);
	
	public EmployeeModelJPA getEmployee(String employeeId);
	
	public List<EmployeeModelJPA> getAllEmployeeList();
	
	public int deleteEmployee(String employeeId);


}
