package com.demo.miramar.web.systemParameter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "system_parameter")
public class SystemParameterModelJPA implements Persistable{
	
	@Id
	@Column(name = "system_id")
	private String systemId;
	@Column(name="system_name")
	private String systemName;
	@Column(name="system_value")
	private String systemvalue;
	@Column(name="description")
	private String description;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="create_by")
	private String createBy;
	@Column(name="modified_time")
	private Date modifiedTime;
	@Column(name="modified_by")
	private String modifiedBy;
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemvalue() {
		return systemvalue;
	}
	public void setSystemvalue(String systemvalue) {
		this.systemvalue = systemvalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return null;
	}
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
