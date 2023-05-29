package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.InterGroupResponseRest;

public interface IInterGroupHService {
	
	public ResponseEntity<InterGroupResponseRest> findAllIntergrupo();
	public ResponseEntity<InterGroupResponseRest> saveInterGruposH(InterGroupHeader interGroupHeader);
	public ResponseEntity<InterGroupResponseRest> updatestateIntergrupo(Integer Id);
	public ResponseEntity<InterGroupResponseRest> updateInterGruposH(InterGroupHeader interGroupHeader);
}
