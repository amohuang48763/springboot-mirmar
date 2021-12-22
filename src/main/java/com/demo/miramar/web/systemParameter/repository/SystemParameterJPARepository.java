package com.demo.miramar.web.systemParameter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.miramar.web.systemParameter.model.SystemParameterModelJPA;

public interface SystemParameterJPARepository extends JpaRepository<SystemParameterModelJPA, Long>{

	List<SystemParameterModelJPA> findAll();
	
	List<SystemParameterModelJPA> findBySystemId(String systemId);

}
