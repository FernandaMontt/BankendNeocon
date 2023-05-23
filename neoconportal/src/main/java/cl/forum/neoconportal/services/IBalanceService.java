package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.response.BalanceResponseRest;

public interface IBalanceService {
	
	public ResponseEntity<BalanceResponseRest> findAll();
	public ResponseEntity<BalanceResponseRest> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceResponseRest> findBalanceId(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
}
