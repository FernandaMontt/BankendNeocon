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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.CuentaEmpresa;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;
import cl.forum.neoconportal.services.ICuentaEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CuentaEmpresaController {

	@Autowired
	private ICuentaEmpresaService service;
	
	/**
	 * get all cuentaEmpresa
	 * @return
	 */
	@GetMapping("/cuentaEmpresas")
	public ResponseEntity<CuentaEmpresaResponseRest> findAllCuentaEmpresa(){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findAllCuentas();
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaEmpresaId(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findByCuentasEmpresaId(id);
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/idempresa/{idempresa}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(@PathVariable Integer idempresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findByCuentasEmpresaEmpresaId(idempresa);
		return response;
	}
	
	@DeleteMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> updatestateCuentaEmpresa(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.updatestateCuentaEmpresa(id);
		return response;
	}
	
	@PostMapping("/cuentaEmpresas")
	public ResponseEntity<CuentaEmpresaResponseRest> saveCuentaEmpresa(@RequestBody CuentaEmpresa cuentaEmpresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.saveCuentaEmpresa(cuentaEmpresa);
		return response;
	}
	
	@PutMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> updateCuentaEmpresa(@RequestBody CuentaEmpresa cuentaEmpresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.updateCuentaEmpresa(cuentaEmpresa);
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/codigo/{codigo}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaEmpresaCodigo(@PathVariable String codigo){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.findByCuentaCodigo(codigo);
		return response;
	}
	
	@PostMapping("/cuentaEmpresas/allintergrupo")
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.searchInterCuentaEmpresa(codigorubro, acronimo);
		return response;
	}
	
	@PostMapping("/cuentaempresa/plancuentas")
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(@RequestParam("acronimo") String acronimo){
		ResponseEntity<CuentaEmpresaResponseRest> response = service.reportePlanCuentasHomologo(acronimo);
		return response;
	}
	
	@PostMapping("/uploadcuentas")
	public ResponseEntity<CuentaEmpresaResponseRest> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("acronimo") String acronimo) {
	  ResponseEntity<CuentaEmpresaResponseRest> response = service.cargarFile(file, acronimo);
	  return response;
	}
}
