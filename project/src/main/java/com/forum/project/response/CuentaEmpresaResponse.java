package com.forum.project.response;

import java.util.List;

import com.forum.project.model.CuentaEmpresa;

import lombok.Data;

@Data
public class CuentaEmpresaResponse {
	
	private List<CuentaEmpresa> cuentaEmpresa;

	public List<CuentaEmpresa> getCuentaEmpresa() {
		return cuentaEmpresa;
	}

	public void setCuentaEmpresa(List<CuentaEmpresa> cuentaEmpresa) {
		this.cuentaEmpresa = cuentaEmpresa;
	}
	
	

}
