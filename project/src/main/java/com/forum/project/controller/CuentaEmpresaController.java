package com.forum.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.project.response.CuentaEmpresaResponseRest;
import com.forum.project.services.ICuentaEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CuentaEmpresaController {

	@Autowired
	private ICuentaEmpresaService service;
	
	/**
	 * get all cuentaEmpresa
	 * @return
	 */
	@GetMapping("/cuentaEmpresas")
	public ResponseEntity<CuentaEmpresaResponseRest> findAllCuentaEmpresa(){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findAll();
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaEmpresaId(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findByCuentasEmpresaId(id);
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/idempresa/{idempresa}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(@PathVariable Integer idempresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findByCuentasEmpresaEmpresaId(idempresa);
		return response;
	}
}
