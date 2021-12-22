package com.demo.miramar.web.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.miramar.result.ResultContants;
import com.demo.miramar.web.employee.model.EmployeeModelJPA;
import com.demo.miramar.web.employee.repository.EmployeeJPARepository;

		

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeJPARepository employeeJPARepository;
	
	@Override
	public int addEmployee(EmployeeModelJPA employeeModelJPA) {
		int returnValue = ResultContants.RESULT_CODE.FAIL.getCode();
		try {
			System.out.println("debug01");
			if(employeeJPARepository.findByLoginAccount(employeeModelJPA.getLoginAccount()).isEmpty()) {
				
				if(employeeJPARepository.findByEmployeeId(employeeModelJPA.getEmployeeId()).isEmpty()) {
					employeeJPARepository.save(employeeModelJPA);
				}else {
					return ResultContants.RESULT_CODE.EMPLOYEE_IS_EXIST.getCode();
				}
			
			}else {
				return ResultContants.RESULT_CODE.LOGIN_ACCOUNT_IS_EXIST.getCode();
			}
			returnValue = ResultContants.RESULT_CODE.SUCCESS.getCode();
		} catch (Throwable e) {
			System.out.println("debug02");
		}
		return returnValue;
	}

	@Override
	public int updateEmployee(EmployeeModelJPA employeeModelJPA) {
		int returnValue = ResultContants.RESULT_CODE.FAIL.getCode();
//		employeeJPARepository.updateEmployee(employeeModelJPA.getEmployeeId(),employeeModelJPA.getEmployeeName(),employeeModelJPA.getLoginCode(),employeeModelJPA.getModifiedBy());
		employeeJPARepository.updateEmployee(employeeModelJPA.getEmployeeId(),employeeModelJPA.getEmployeeName(),employeeModelJPA.getLoginCode(),employeeModelJPA.getModifiedTime(),employeeModelJPA.getModifiedBy());
		returnValue = ResultContants.RESULT_CODE.SUCCESS.getCode();
		return returnValue;
	}

	@Override
	public EmployeeModelJPA getEmployee(String employeeId) {
		// TODO Auto-generated method stub
		return employeeJPARepository.findByEmployeeId(employeeId).get(0);
	}

	@Override
	public List<EmployeeModelJPA> getAllEmployeeList() {
		// TODO Auto-generated method stub
		return employeeJPARepository.findAll();
	}

	@Override
	public int deleteEmployee(String employeeId) {
		// TODO Auto-generated method stub
		employeeJPARepository.deleteByEmployeeId(employeeId);
		return 0;
	}
	
}
