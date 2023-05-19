package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;
import cl.forum.neoconportal.services.NeoConBalanceDetalleServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class NeoConBalanceDetalleController {
	
	@Autowired
	private NeoConBalanceDetalleServiceImpl service;
	
	
	@PostMapping("/uploadNeoConBalanceDetalle")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> saveNeoConHeaders(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<NeoConBalanceDetalleResponseRest> response = service.saveNeoConBalanceDetalles(file, periodo, acronimo);
	  return response;
	}

}
