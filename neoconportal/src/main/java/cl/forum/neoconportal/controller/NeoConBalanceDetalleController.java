package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
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
	
	@GetMapping("/neoconbalancedetallecuenta/{id}")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleNaturalezaId(@PathVariable Integer id){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = service.findNeoBalanceDetalleId(id);
		return response;
	}
	
	@GetMapping("/neoconbalancedetallerubro/{id}")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleIntergrupoId(@PathVariable Integer id){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = service.findNeoBalanceDetalleRubroId(id);
		return response;
	}
	
	@PostMapping("/reporteproestpec")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> resporteProestpec(@RequestParam("periodo") Integer periodo) {
	  ResponseEntity<NeoConBalanceDetalleResponseRest> response = service.ReporteProestec(periodo);
	  return response;
	}

}
