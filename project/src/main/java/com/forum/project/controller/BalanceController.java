package com.forum.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.project.model.Balance;
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
	@PostMapping("/balance")
	public ResponseEntity<BalanceResponseRest> saveBalance(@RequestBody Balance balance){
		ResponseEntity<BalanceResponseRest> response = service.saveBalance(balance);
		return response;
	}
	

}
