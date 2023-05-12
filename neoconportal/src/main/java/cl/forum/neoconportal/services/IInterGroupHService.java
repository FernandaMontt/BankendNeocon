package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.InterGroupResponseRest;

public interface IInterGroupHService {
	
	public ResponseEntity<InterGroupResponseRest> findAll();
	public ResponseEntity<InterGroupResponseRest> saveInterGruposH(InterGroupHeader interGroupHeader);
	public ResponseEntity<InterGroupResponseRest> updatestateIntergrupo(Integer Id);
}