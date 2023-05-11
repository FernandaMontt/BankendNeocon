package com.forum.project.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.forum.project.model.Balance;
import com.forum.project.response.BalanceResponseRest;

public interface IBalanceService {
	
	public ResponseEntity<BalanceResponseRest> findAll();
	public ResponseEntity<BalanceResponseRest> saveBalance(Balance balance);
	public ResponseEntity<BalanceResponseRest> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);

}
