package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.forum.neoconportal.model.InterGroupHeader;
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
		ResponseEntity<InterGroupResponseRest> response = service.findAllIntergrupo();
		return response;
	}
	
	@PostMapping("/intergrupoheader")
	public ResponseEntity<InterGroupResponseRest> saveRubros(@RequestBody InterGroupHeader interGroupHeader){
		ResponseEntity<InterGroupResponseRest> response = service.saveInterGruposH(interGroupHeader);
		return response;
	}
	
	@DeleteMapping("/intergrupoheader/{id}")
	public ResponseEntity<InterGroupResponseRest> updatestateIntergrupo(@PathVariable Integer id){
		ResponseEntity<InterGroupResponseRest> response = service.updatestateIntergrupo(id);
		return response;
	}
	
	@PutMapping("/intergrupoheader/{id}")
	public ResponseEntity<InterGroupResponseRest> updateInterGruposH(@RequestBody InterGroupHeader interGroupHeader){
		ResponseEntity<InterGroupResponseRest> response = service.updateInterGruposH(interGroupHeader);
		return response;
	}

}
