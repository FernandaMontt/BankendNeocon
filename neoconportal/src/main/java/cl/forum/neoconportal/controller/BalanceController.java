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
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;
import cl.forum.neoconportal.response.NeoConHeaderResponseRest;
import cl.forum.neoconportal.services.BalancesServiceImpl;
import cl.forum.neoconportal.services.NeoConBalanceServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class BalanceController {
	
	@Autowired
	private BalancesServiceImpl serviceBalance;
	
	@Autowired
	private NeoConBalanceServiceImpl serviceNeoconHeader;
	
	/*Balance*/
	

	@GetMapping("/balance")
	public ResponseEntity<BalanceResponseRest> findAllBalances(){
		ResponseEntity<BalanceResponseRest> response = serviceBalance.findAllBalances();
		return response;
	}
	
	@PostMapping("/uploadbalance")
	public ResponseEntity<BalanceResponseRest> uploadFile(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = serviceBalance.uploadFile(file, periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/obtenerid")
	public ResponseEntity<BalanceResponseRest> findBalanceId(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = serviceBalance.findBalanceId(periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/uploadbalance2")
	public ResponseEntity<BalanceResponseRest> uploadFileBalance2(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = serviceBalance.uploadFileBalance2(file, periodo, acronimo);
	  return response;
	}
	
	@GetMapping("/balancedetallecuenta/{id}")
	public ResponseEntity<BalanceDetalleResponseRest> validateTestCuentaBalance(@PathVariable Integer id){
		ResponseEntity<BalanceDetalleResponseRest> response = serviceBalance.validateTestCuentaBalance(id);
		return response;
	}
	
	@GetMapping("/balancedetallerubro/{id}")
	public ResponseEntity<BalanceDetalleResponseRest> validateTestRubroBalance(@PathVariable Integer id){
		ResponseEntity<BalanceDetalleResponseRest> response = serviceBalance.validateTestRubroBalance(id);
		return response;
	}
	
	/*NeoConBalance*/
	
	@PostMapping("/obtenerneoconid")
	public ResponseEntity<NeoConHeaderResponseRest> findNeoConBalanceId(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<NeoConHeaderResponseRest> response = serviceNeoconHeader.findNeoConBalanceId(periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/uploadNeoConBalanceDetalle")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> createNeoConBalanceDetalles(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.createNeoConBalanceDetalles(file, periodo, acronimo);
	  return response;
	}
	
	@GetMapping("/neoconbalancedetallecuenta/{id}")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateTestCuentaNeoConBalance(@PathVariable Integer id){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.validateTestCuentaNeoConBalance(id);
		return response;
	}
	
	@GetMapping("/neoconbalancedetallerubro/{id}")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateTestRubroNeoConBalance(@PathVariable Integer id){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.validateTestRubroNeoConBalance(id);
		return response;
	}
	
	@PostMapping("/reporteproestpec")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> resportProestpec(@RequestParam("periodo") Integer periodo) {
	  ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.ReporteProestec(periodo);
	  return response;
	}
	
	@PostMapping("/descarganeoconbalance")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportCodGInterfaz(@RequestParam("periodo") Integer periodo, @RequestParam("acronimo") String acronimo){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.DescargaCodGInterfaz(periodo, acronimo);
		return response;
	}
	

}
