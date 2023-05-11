package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;

public interface IInterGroupDetalleService {
	
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumeroIG(Integer Id);

}
