package com.demo.miramar.web.employee.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Entity
@Table(name = "employee")
public class EmployeeModelJPA implements Persistable{
	
	@Id
	@Column(name = "employee_id")
	private String employeeId;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name="login_account")
	private String loginAccount;
	@Column(name="login_code")
	private String loginCode;
	@Column(name="employee_status")
	private String employeeStatus;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="create_by")
	private String createBy;
	@Column(name="modified_time")
	private Date modifiedTime;
	@Column(name="modified_by")
	private String modifiedBy;
	
	public JsonObject toJsonObject() {
		Gson gson = new Gson();
		String objectToString = gson.toJson(this);
		return gson.fromJson(objectToString, JsonObject.class);
	}
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return employeeId;
	}


	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
