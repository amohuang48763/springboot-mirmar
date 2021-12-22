package com.demo.miramar.web.employee.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.miramar.web.employee.model.EmployeeModelJPA;

public interface EmployeeJPARepository extends JpaRepository<EmployeeModelJPA, Long> {

	List<EmployeeModelJPA> findAll();

	List<EmployeeModelJPA> findByLoginAccount(String loginAccount);

	List<EmployeeModelJPA> findByEmployeeId(String employeeId);

	@Query(value = "SELECT employee_id,employee_name,login_account,login_code,employee_status,CONVERT(create_time, DATETIME) AS create_time,create_by,modified_by,CONVERT(modified_time, DATETIME) AS modified_time from employee where login_account = ?1 and login_code = ?2 ", nativeQuery = true)
	List<EmployeeModelJPA> findCheckMemberAccount(String loginAccount, String loginCode);

	// 更新資料
//	@Modifying
//	@Transactional
//	@Query("UPDATE employee e set e.employee_name=?2 , e.login_code=?3  , e.modified_by=?4 where e.employee_id=?1 ")
//	int updateEmployee(String employeeId, String employeeName, String loginCode, String modifiedBy);
//	// 更新資料
	@Modifying
	@Transactional
	@Query(value ="UPDATE employee e set e.employee_name=?2 , e.login_code=?3 , e.modified_time=?4 , e.modified_by=?5 where e.employee_id=?1 ", nativeQuery = true)
	int updateEmployee(String employeeId, String employeeName, String loginCode, Date modifiedTime, String modifiedBy);

	int deleteByEmployeeId(String employeeId);
}
