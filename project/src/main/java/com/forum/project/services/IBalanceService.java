package com.forum.project.services;

import org.springframework.http.ResponseEntity;

import com.forum.project.model.Balance;
import com.forum.project.response.BalanceResponseRest;

public interface IBalanceService {
	
	public ResponseEntity<BalanceResponseRest> saveBalance(Balance balance);

}
