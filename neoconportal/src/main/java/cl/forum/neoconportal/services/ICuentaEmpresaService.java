package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.CuentaEmpresa;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;

public interface ICuentaEmpresaService {
	
	public ResponseEntity<CuentaEmpresaResponseRest> findAll();
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaId(Integer Id);
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(Integer IdEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> updatestateCuentaEmpresa(Integer Id);
	public ResponseEntity<CuentaEmpresaResponseRest> saveCuentaEmpresa(CuentaEmpresa cuentaEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> updateCuentaEmpresa(CuentaEmpresa cuentaEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaCodigo(String Codigo);
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo);
	public ResponseEntity<CuentaEmpresaResponseRest> reportePlanCuentasHomologo(@RequestParam("acronimo") String acronimo);
	public ResponseEntity<CuentaEmpresaResponseRest> cargarFile(@RequestParam("file") MultipartFile file,
			@RequestParam("acronimo") String acronimo);
}
