package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import cl.forum.neoconportal.model.InterGroupDetalle;
import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;
import cl.forum.neoconportal.response.InterGroupResponseRest;

public interface IInterGrupoService {

	public ResponseEntity<InterGroupResponseRest> findAllIntergrupo();
	public ResponseEntity<InterGroupResponseRest> saveInterGruposH(InterGroupHeader interGroupHeader);
	public ResponseEntity<InterGroupResponseRest> updatestateIntergrupo(Integer Id);
	public ResponseEntity<InterGroupResponseRest> updateInterGruposH(InterGroupHeader interGroupHeader);
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumeroIG(Integer Id);
	public ResponseEntity<InterGroupDetalleResponseRest> saveInterGruposD(InterGroupDetalle interGroupDetalle);
	public ResponseEntity<InterGroupDetalleResponseRest> deleteIntergrupodetalle(
			@RequestParam("numeroig") Integer numeroig, @RequestParam("acronimo") String acronimo,
			@RequestParam("codigocuenta") int codigocuenta);
	public ResponseEntity<InterGroupDetalleResponseRest> findById(Integer IdInter);
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo,
			@RequestParam("numeroig") Integer numeroig);
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa2(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo,
			@RequestParam("numeroig") Integer numeroig);
	public ResponseEntity<InterGroupDetalleResponseRest> reporteintegrupo();
}
