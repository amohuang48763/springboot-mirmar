package com.demo.miramar.web.systemParameter.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.miramar.web.employee.model.EmployeeModelJPA;
import com.demo.miramar.web.systemParameter.model.SystemParameterModelJPA;
import com.demo.miramar.web.systemParameter.repository.SystemParameterJPARepository;


@Service
public class SystemParameterServiceImpl implements SystemParameterService{

	@Autowired
	SystemParameterJPARepository systemParameterJPARepository;
	
	
	public int addSystemParameter(SystemParameterModelJPA systemParameterModelJPA) {
		
		SystemParameterModelJPA insertModel = new SystemParameterModelJPA();
		insertModel.setSystemId(systemParameterModelJPA.getSystemId());
		insertModel.setSystemName(systemParameterModelJPA.getSystemName());
		insertModel.setSystemvalue(systemParameterModelJPA.getSystemvalue());
		insertModel.setDescription(systemParameterModelJPA.getDescription());
		insertModel.setCreateBy(systemParameterModelJPA.getCreateBy());
		insertModel.setModifiedBy(systemParameterModelJPA.getModifiedBy());
		
		final Date today = new Date();
		systemParameterModelJPA.setCreateTime(today);
		systemParameterModelJPA.setModifiedTime(today);
		systemParameterJPARepository.save(systemParameterModelJPA);
		
		return 1;
	}

	@Override
	public List<SystemParameterModelJPA> getAllSystemParameterList() {
		
		return systemParameterJPARepository.findAll();
	}
	
	@Override
	public int getHourWage(String systemId) {
		List<SystemParameterModelJPA> resultList =  systemParameterJPARepository.findBySystemId(systemId);
		if(!resultList.isEmpty()) {
			int hourWage = Integer.parseInt(resultList.get(0).getSystemvalue());
			return hourWage;
		}
		return 0;
	}

	@Override
	public SystemParameterModelJPA getSystemParameterBySystemParameterId(String systemParamterId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
