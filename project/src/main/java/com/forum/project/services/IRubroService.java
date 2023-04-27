package com.forum.project.services;

import org.springframework.http.ResponseEntity;
import com.forum.project.response.RubroResponseRest;

public interface IRubroService {
	
	public ResponseEntity<RubroResponseRest> findAll();
	public ResponseEntity<RubroResponseRest> findByRubroId(Integer Id);
	
}
