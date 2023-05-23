package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;

import cl.forum.neoconportal.response.ValidacionMensajeResponseRest;

public interface IValidacionMensajeService {
	
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeCuentaId(Integer Id);
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeRubroId(Integer Id);
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeNaturalezaId(Integer Id);
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeIntergrupoId(Integer Id);

}
