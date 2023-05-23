package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.services.BalanceServiceImpl;

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
	public ResponseEntity<BalanceResponseRest> findAllCuentaEmpresa(){
		ResponseEntity<BalanceResponseRest> response = service.findAll();
		return response;
	}
	
	@PostMapping("/uploadbalance")
	public ResponseEntity<BalanceResponseRest> uploadFile(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = service.uploadFile(file, periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/obtenerid")
	public ResponseEntity<BalanceResponseRest> findBalanceId(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = service.findBalanceId(periodo, acronimo);
	  return response;
	}
	

}
