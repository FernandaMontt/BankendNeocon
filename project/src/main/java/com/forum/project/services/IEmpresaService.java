package com.forum.project.services;

import org.springframework.http.ResponseEntity;

import com.forum.project.response.EmpresaResponseRest;

public interface IEmpresaService {
	
	public ResponseEntity<EmpresaResponseRest> findAll();
	public ResponseEntity<EmpresaResponseRest> findByEmpresaId(Integer Id);
	

}
