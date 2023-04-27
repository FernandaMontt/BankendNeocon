package com.forum.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
