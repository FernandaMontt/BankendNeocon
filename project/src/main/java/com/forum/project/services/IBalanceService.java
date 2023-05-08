package com.forum.project.services;

import org.springframework.http.ResponseEntity;

import com.forum.project.response.BalanceResponseRest;

public interface IBalanceService {
	
	public ResponseEntity<BalanceResponseRest> findAll();
	public ResponseEntity<BalanceResponseRest> findByBalanceId(Integer Id);

}
