package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import cl.forum.neoconportal.model.InterGroupDetalle;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;

public interface IInterGroupDetalleService {
	
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumeroIG(Integer Id);
	public ResponseEntity<InterGroupDetalleResponseRest> saveInterGruposD(InterGroupDetalle interGroupDetalle);
	public ResponseEntity<InterGroupDetalleResponseRest> deleteIntergrupodetalle(
			@RequestParam("numeroig") Integer numeroig, @RequestParam("acronimo") String acronimo,
			@RequestParam("codigocuenta") int codigocuenta);
	public ResponseEntity<InterGroupDetalleResponseRest> findById(Integer IdInter);
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo);
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa2(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo);

}
