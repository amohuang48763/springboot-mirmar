package com.demo.miramar.web.employee.service;

import java.util.List;

import com.demo.miramar.web.employee.model.EmployeeModelJPA;

public interface EmployeeService {
	
	public String addEmployee(EmployeeModelJPA employeeModelJPA);
	
	public String updateEmployee(EmployeeModelJPA employeeModelJPA);
	
	public EmployeeModelJPA getEmployee(String employeeId);
	
	public List<EmployeeModelJPA> getAllEmployeeList();
	
	public String deleteEmployee(String employeeId);


}
