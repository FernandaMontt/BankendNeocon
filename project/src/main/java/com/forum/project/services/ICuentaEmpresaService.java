package com.forum.project.services;

import org.springframework.http.ResponseEntity;

import com.forum.project.model.CuentaEmpresa;
import com.forum.project.response.CuentaEmpresaResponseRest;

public interface ICuentaEmpresaService {
	
	public ResponseEntity<CuentaEmpresaResponseRest> findAll();
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaId(Integer Id);
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(Integer IdEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> updatestateCuentaEmpresa(Integer Id);
	public ResponseEntity<CuentaEmpresaResponseRest> saveCuentaEmpresa(CuentaEmpresa cuentaEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> updateCuentaEmpresa(CuentaEmpresa cuentaEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaCodigo(String Codigo);

}