package com.forum.project.services;

import org.springframework.http.ResponseEntity;

import com.forum.project.model.Rubro;
import com.forum.project.response.RubroResponseRest;

public interface IRubroService {
	
	public ResponseEntity<RubroResponseRest> findAll();
	public ResponseEntity<RubroResponseRest> findByRubroId(Integer Id);
	public ResponseEntity<RubroResponseRest> updatestate(Integer Id);
	public ResponseEntity<RubroResponseRest> findByRubroCodigo(String Codigo);
	public ResponseEntity<RubroResponseRest> saveRubros(Rubro rubro);
	public ResponseEntity<RubroResponseRest> updateRubros(Rubro rubro);
	public ResponseEntity<RubroResponseRest> findAllActivos();
	
}
