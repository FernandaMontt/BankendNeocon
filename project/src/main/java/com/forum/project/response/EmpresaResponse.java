package com.forum.project.response;

import java.util.List;

import com.forum.project.model.Empresa;

import lombok.Data;
@Data
public class EmpresaResponse {
	
	private List<Empresa> empresa;

	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

}
