package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;

public interface INeoConBalanceDetalleService {
	
	public ResponseEntity<NeoConBalanceDetalleResponseRest> saveNeoConBalanceDetalles(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo);

}
