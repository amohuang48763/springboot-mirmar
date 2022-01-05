package com.demo.miramar.web.systemParameter.service;

import java.util.List;

import com.demo.miramar.web.systemParameter.model.SystemParameterModelJPA;

public interface SystemParameterService {

	public String addSystemParameter(SystemParameterModelJPA systemParameterModelJPA);
	
	public List<SystemParameterModelJPA> getAllSystemParameterList();

	SystemParameterModelJPA getSystemParameterBySystemParameterId(String systemParamterId);

	int getHourWage(String systemParamterId);
}
