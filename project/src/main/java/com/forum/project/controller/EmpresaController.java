package com.forum.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.project.response.EmpresaResponseRest;
import com.forum.project.services.IEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EmpresaController {

	@Autowired
	private IEmpresaService service;
	
	/**
	 * get all empresas
	 * @return
	 */
	@GetMapping("/empresas")
	public ResponseEntity<EmpresaResponseRest> findAlLEmpresa(){
		ResponseEntity<EmpresaResponseRest> response = service.findAll();
		return response;
	}
	
	@GetMapping("/empresas/{id}")
	public ResponseEntity<EmpresaResponseRest> findByEmpresaId(@PathVariable Integer id){
		ResponseEntity<EmpresaResponseRest> response = service.findByEmpresaId(id);
		return response;
	}
}
