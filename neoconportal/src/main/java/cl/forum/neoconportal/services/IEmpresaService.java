package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.response.EmpresaResponseRest;

public interface IEmpresaService {
	
	public ResponseEntity<EmpresaResponseRest> findCompanyById(Integer Id);
	

}
