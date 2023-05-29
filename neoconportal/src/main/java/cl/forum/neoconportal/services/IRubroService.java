package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.model.Rubro;
import cl.forum.neoconportal.response.RubroResponseRest;

public interface IRubroService {
	
	public ResponseEntity<RubroResponseRest> findAllRubro();
	public ResponseEntity<RubroResponseRest> findByRubroId(Integer Id);
	public ResponseEntity<RubroResponseRest> updatestate(Integer Id);
	public ResponseEntity<RubroResponseRest> findByRubroCodigo(String Codigo);
	public ResponseEntity<RubroResponseRest> saveRubros(Rubro rubro);
	public ResponseEntity<RubroResponseRest> updateRubros(Rubro rubro);
	public ResponseEntity<RubroResponseRest> findAllActivos();
	
}
