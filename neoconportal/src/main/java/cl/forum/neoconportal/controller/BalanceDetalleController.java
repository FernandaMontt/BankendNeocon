package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.RubroResponseRest;
import cl.forum.neoconportal.services.BalanceDetalleServiceImpl;
import cl.forum.neoconportal.services.BalanceServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class BalanceDetalleController {
	
	@Autowired
	private BalanceDetalleServiceImpl service;
	
	/**
	 * get all balance
	 * @return
	 */
	@GetMapping("/balancedetallecuenta/{id}")
	public ResponseEntity<BalanceDetalleResponseRest> findByBalanceDetalleCuentaId(@PathVariable Integer id){
		ResponseEntity<BalanceDetalleResponseRest> response = service.findBalanceDetalleId(id);
		return response;
	}
	
	@GetMapping("/balancedetallerubro/{id}")
	public ResponseEntity<BalanceDetalleResponseRest> findByBalanceDetalleRubroId(@PathVariable Integer id){
		ResponseEntity<BalanceDetalleResponseRest> response = service.findBalanceDetalleRubroId(id);
		return response;
	}
	

}
