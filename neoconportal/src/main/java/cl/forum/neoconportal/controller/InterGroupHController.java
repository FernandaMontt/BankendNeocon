package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.forum.neoconportal.response.InterGroupResponseRest;
import cl.forum.neoconportal.services.InterGroupHServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class InterGroupHController {
	
	@Autowired
	private InterGroupHServiceImpl service;
	
	/**
	 * get all intergrupos header
	 * @return
	 */
	@GetMapping("/intergrupoheader")
	public ResponseEntity<InterGroupResponseRest> findAllInterGroupHeader(){
		ResponseEntity<InterGroupResponseRest> response = service.findAll();
		return response;
	}

}
