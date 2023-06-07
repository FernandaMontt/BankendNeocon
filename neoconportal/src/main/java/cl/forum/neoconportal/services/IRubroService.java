package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.model.Rubro;
import cl.forum.neoconportal.response.RubroResponseRest;

public interface IRubroService {
	
	public ResponseEntity<RubroResponseRest> findAllItems(String state);
	public ResponseEntity<RubroResponseRest> findByItemId(Integer Id);
	public ResponseEntity<RubroResponseRest> updateState(Integer Id);
	public ResponseEntity<RubroResponseRest> findItemByCode(String code);
	public ResponseEntity<RubroResponseRest> createItem(Rubro rubro);
	public ResponseEntity<RubroResponseRest> updateItem(Rubro rubro);
	
}
