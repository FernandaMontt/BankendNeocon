package com.forum.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.project.response.BalanceResponseRest;
import com.forum.project.services.BalanceServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class BalanceController {
	
	@Autowired
	private BalanceServiceImpl service;
	
	/**
	 * get all balance
	 * @return
	 */
	@GetMapping("/balance")
	public ResponseEntity<BalanceResponseRest> findAllBalance(){
		ResponseEntity<BalanceResponseRest> response = service.findAll();
		return response;
	}
	
	@GetMapping("/balance/{id}")
	public ResponseEntity<BalanceResponseRest> findByBalanceId(@PathVariable Integer id){
		ResponseEntity<BalanceResponseRest> response = service.findByBalanceId(id);
		return response;
	}
	

}
