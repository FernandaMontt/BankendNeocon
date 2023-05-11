package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.response.InterGroupResponseRest;

public interface IInterGroupHService {
	
	public ResponseEntity<InterGroupResponseRest> findAll();

}
