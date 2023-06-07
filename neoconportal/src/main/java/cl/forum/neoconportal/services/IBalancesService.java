package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.BalanceResponseRest;

public interface IBalancesService {

	public ResponseEntity<BalanceResponseRest> findAllBalanceSheet();
	public ResponseEntity<BalanceResponseRest> uploadBalanceSheetFile(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceResponseRest> findBalanceSheetById(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceResponseRest> uploadBalanceSheetFile2(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceDetalleResponseRest> validateAccountBalanceSheet(Integer id_balance);
	public ResponseEntity<BalanceDetalleResponseRest> validateItemAccountBalanceSheet(Integer id_balance);
}
