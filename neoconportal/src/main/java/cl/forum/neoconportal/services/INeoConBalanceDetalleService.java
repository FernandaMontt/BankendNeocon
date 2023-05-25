package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;

public interface INeoConBalanceDetalleService {
	
	public ResponseEntity<NeoConBalanceDetalleResponseRest> saveNeoConBalanceDetalles(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleId(Integer id_neocon);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleRubroId(Integer id_neocon);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> ReporteProestec(@RequestParam("periodo") Integer periodo);
	public ResponseEntity<NeoConBalanceDetalleResponseRest> DescargaCodGInterfaz(Integer id_neocon);
}
