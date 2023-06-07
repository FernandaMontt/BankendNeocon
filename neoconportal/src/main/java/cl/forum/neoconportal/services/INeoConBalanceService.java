package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;
import cl.forum.neoconportal.response.NeoConHeaderResponseRest;

public interface INeoConBalanceService {

	public ResponseEntity<NeoConHeaderResponseRest> findNeoConBalanceSheetId(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> createNeoConBalanceSheet(
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateAccountNeoconBalanceSheet(Integer id_neocon);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateItemAccountNeoconBalanceSheet(Integer id_neocon);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportNeoconBalanceSheetProespect(@RequestParam("periodo") Integer periodo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportNeoconBalanceSheet(@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
}
