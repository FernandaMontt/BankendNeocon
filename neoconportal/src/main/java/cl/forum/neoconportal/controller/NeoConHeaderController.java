package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.forum.neoconportal.response.NeoConHeaderResponseRest;
import cl.forum.neoconportal.services.NeoConHeaderServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class NeoConHeaderController {

	@Autowired
	private NeoConHeaderServiceImpl service;
	
	@PostMapping("/uploadNeoconHeader")
	public ResponseEntity<NeoConHeaderResponseRest> saveNeoConHeaders(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<NeoConHeaderResponseRest> response = service.saveNeoConHeaders(periodo, acronimo);
	  return response;
	}
	
}
