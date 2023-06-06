package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;
import cl.forum.neoconportal.response.NeoConHeaderResponseRest;

public interface INeoConBalanceService {

	public ResponseEntity<NeoConHeaderResponseRest> findNeoConBalanceId(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> createNeoConBalanceSheet(
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateTestCuentaNeoConBalance(Integer id_neocon);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateTestRubroNeoConBalance(Integer id_neocon);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> ReporteProestec(@RequestParam("periodo") Integer periodo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> DescargaCodGInterfaz(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
}
