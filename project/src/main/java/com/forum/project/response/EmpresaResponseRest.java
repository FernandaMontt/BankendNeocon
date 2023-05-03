package com.forum.project.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaResponseRest {
	
	private EmpresaResponse empresaResponse = new EmpresaResponse();

	public EmpresaResponse getEmpresaResponse() {
		return empresaResponse;
	}

	public void setEmpresaResponse(EmpresaResponse empresaResponse) {
		this.empresaResponse = empresaResponse;
	}

}
