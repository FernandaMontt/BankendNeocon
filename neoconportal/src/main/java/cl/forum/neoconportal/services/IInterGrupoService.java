package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import cl.forum.neoconportal.model.InterGroupDetalle;
import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;
import cl.forum.neoconportal.response.InterGroupResponseRest;

public interface IInterGrupoService {

	public ResponseEntity<InterGroupResponseRest> createInterGroup(InterGroupHeader interGroupHeader);
	public ResponseEntity<InterGroupResponseRest> updateInterGroupState(Integer Id);
	public ResponseEntity<InterGroupResponseRest> updateInterGroup(InterGroupHeader interGroupHeader);
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumberIG(Integer Id);
	public ResponseEntity<InterGroupDetalleResponseRest> createInterGroupDetail(InterGroupDetalle interGroupDetalle);
	public ResponseEntity<InterGroupDetalleResponseRest> deleteInterGroupDetail(
			@RequestParam("numeroig") Integer numeroig, @RequestParam("acronimo") String acronimo,
			@RequestParam("codigocuenta") int codigocuenta);
	public ResponseEntity<InterGroupResponseRest> findInterGroupById(Integer IdInter);
	public ResponseEntity<InterGroupDetalleResponseRest> searchIntergroupCompanyAccounts(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo,
			@RequestParam("numeroig") Integer numeroig);
	public ResponseEntity<InterGroupDetalleResponseRest> searchIntergroupCompanyAccounts2(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo,
			@RequestParam("numeroig") Integer numeroig);
	public ResponseEntity<InterGroupDetalleResponseRest> reportInterGroup();
}
