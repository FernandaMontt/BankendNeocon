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
@RequestMapping("/neoconportal/v1")
public class BalanceController {
	
	@Autowired
	private BalancesServiceImpl serviceBalance;
	
	@Autowired
	private NeoConBalanceServiceImpl serviceNeoconHeader;
	
	/*Balance*/
	

	@GetMapping("/balancesheet")
	public ResponseEntity<BalanceResponseRest> findAllBalanceSheet(){
		ResponseEntity<BalanceResponseRest> response = serviceBalance.findAllBalanceSheet();
		return response;
	}
	
	@PostMapping("/uploadbalancesheet")
	public ResponseEntity<BalanceResponseRest> uploadBalanceSheetFile(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = serviceBalance.uploadBalanceSheetFile(file, periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/getbalancesheetid")
	public ResponseEntity<BalanceResponseRest> findBalanceSheetById(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = serviceBalance.findBalanceSheetById(periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/uploadbalancesheet2")
	public ResponseEntity<BalanceResponseRest> uploadBalanceSheetFile2(@RequestParam("file") MultipartFile file,
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<BalanceResponseRest> response = serviceBalance.uploadBalanceSheetFile2(file, periodo, acronimo);
	  return response;
	}
	
	@GetMapping("/balancedetailaccount/{id}")
	public ResponseEntity<BalanceDetalleResponseRest> validateAccountBalanceSheet(@PathVariable Integer id){
		ResponseEntity<BalanceDetalleResponseRest> response = serviceBalance.validateAccountBalanceSheet(id);
		return response;
	}
	
	@GetMapping("/balancedetailitem/{id}")
	public ResponseEntity<BalanceDetalleResponseRest> validateItemAccountBalanceSheet(@PathVariable Integer id){
		ResponseEntity<BalanceDetalleResponseRest> response = serviceBalance.validateItemAccountBalanceSheet(id);
		return response;
	}
	
	/*NeoConBalance*/
	
	@PostMapping("/getneoconid")
	public ResponseEntity<NeoConHeaderResponseRest> findNeoConBalanceSheetId(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<NeoConHeaderResponseRest> response = serviceNeoconHeader.findNeoConBalanceSheetId(periodo, acronimo);
	  return response;
	}
	
	@PostMapping("/uploadneoconbalancesheet")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> createNeoConBalanceSheet(
											 @RequestParam("periodo") Integer periodo,
											 @RequestParam("acronimo") String acronimo) {
	  ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.createNeoConBalanceSheet(periodo, acronimo);
	  return response;
	}
	
	@GetMapping("/neoconbalancedetailaccount/{id}")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateAccountNeoconBalanceSheet(@PathVariable Integer id){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.validateAccountNeoconBalanceSheet(id);
		return response;
	}
	
	@GetMapping("/neoconbalancedetailitem/{id}")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateItemAccountNeoconBalanceSheet(@PathVariable Integer id){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.validateItemAccountNeoconBalanceSheet(id);
		return response;
	}
	
	@PostMapping("/reportproestpec")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportNeoconBalanceSheetProespect(@RequestParam("periodo") Integer periodo) {
	  ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.reportNeoconBalanceSheetProespect(periodo);
	  return response;
	}
	
	@PostMapping("/reportneoconbalancesheet")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportNeoconBalanceSheet(@RequestParam("periodo") Integer periodo, @RequestParam("acronimo") String acronimo){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.reportNeoconBalanceSheet(periodo, acronimo);
		return response;
	}
	
	@GetMapping("/neoconitembalancesheet")
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoConItemById(){
		ResponseEntity<NeoConBalanceDetalleResponseRest> response = serviceNeoconHeader.findNeoConItemById();
		return response;
	}
	

}
