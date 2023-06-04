package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.BalanceResponseRest;

public interface IBalancesService {

	public ResponseEntity<BalanceResponseRest> findAllBalances();
	public ResponseEntity<BalanceResponseRest> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceResponseRest> findBalanceId(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceResponseRest> uploadFileBalance2(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<BalanceDetalleResponseRest> validateTestCuentaBalance(Integer id_balance);
	public ResponseEntity<BalanceDetalleResponseRest> validateTestRubroBalance(Integer id_balance);
}
