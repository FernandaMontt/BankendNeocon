package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


import cl.forum.neoconportal.response.NeoConHeaderResponseRest;

public interface INeoConHeaderService {

	public ResponseEntity<NeoConHeaderResponseRest> saveNeoConHeaders(@RequestParam("periodo") Integer periodo,
			 @RequestParam("acronimo") String acronimo);
}
