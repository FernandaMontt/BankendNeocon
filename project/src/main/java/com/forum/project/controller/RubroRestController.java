package com.forum.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.project.model.Rubro;
import com.forum.project.response.RubroResponseRest;
import com.forum.project.services.IRubroService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class RubroRestController {

	@Autowired
	private IRubroService service;
	/**
	 * get all rubros
	 * @return
	 */
	
	@GetMapping("/rubros")
	public ResponseEntity<RubroResponseRest> findAllRubros(){
		ResponseEntity<RubroResponseRest> response = service.findAll();
		return response;
	}
	
	@GetMapping("/rubros/{id}")
	public ResponseEntity<RubroResponseRest> findByRubrosId(@PathVariable Integer id){
		ResponseEntity<RubroResponseRest> response = service.findByRubroId(id);
		return response;
	}
	
	@DeleteMapping("/rubros/{id}")
	public ResponseEntity<RubroResponseRest> updatestate(@PathVariable Integer id){
		ResponseEntity<RubroResponseRest> response = service.updatestate(id);
		return response;
	}
	
	@GetMapping("/rubros/{codigo}")
	public ResponseEntity<RubroResponseRest> findByRubrosCodigo(@PathVariable String codigo){
		ResponseEntity<RubroResponseRest> response = service.findByRubroCodigo(codigo);
		return response;
	}
	
	@PostMapping("/rubros")
	public ResponseEntity<RubroResponseRest> saveRubros(@RequestBody Rubro rubro){
		ResponseEntity<RubroResponseRest> response = service.saveRubros(rubro);
		return response;
	}
	
	@PutMapping("/rubros/{id}")
	public ResponseEntity<RubroResponseRest> updateRubros(@RequestBody Rubro rubro){
		ResponseEntity<RubroResponseRest> response = service.updateRubros(rubro);
		return response;
	}
}
