package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;
import cl.forum.neoconportal.services.IInterGroupDetalleService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class InterGroupDetalleController {
	
	@Autowired
	private IInterGroupDetalleService service;
	
	@GetMapping("/intergrupodetalle/{id}")
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumeroIG(@PathVariable Integer id){
		ResponseEntity<InterGroupDetalleResponseRest> response = service.findByNumeroIG(id);
		return response;
	}

}
