package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.forum.neoconportal.response.ValidacionMensajeResponseRest;
import cl.forum.neoconportal.services.IValidacionMensajeService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ValidacionMensajeController {
	
	@Autowired
	private IValidacionMensajeService service;
	
	@GetMapping("/validacioncuentas/{id}")
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeCuentaId(@PathVariable Integer id){
		ResponseEntity<ValidacionMensajeResponseRest> response = service.findByValidacionMensajeCuentaId(id);
		return response;
	}
	
	@GetMapping("/validacionrubros/{id}")
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeRubroId(@PathVariable Integer id){
		ResponseEntity<ValidacionMensajeResponseRest> response = service.findByValidacionMensajeRubroId(id);
		return response;
	}
	
	@GetMapping("/validacionintergrupos/{id}")
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeIntergrupoId(@PathVariable Integer id){
		ResponseEntity<ValidacionMensajeResponseRest> response = service.findByValidacionMensajeIntergrupoId(id);
		return response;
	}
	
	@GetMapping("/validacionnaturaleza/{id}")
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeNaturalezaId(@PathVariable Integer id){
		ResponseEntity<ValidacionMensajeResponseRest> response = service.findByValidacionMensajeNaturalezaId(id);
		return response;
	}

}
