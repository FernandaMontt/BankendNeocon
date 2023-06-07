package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.CuentaEmpresa;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;

public interface ICuentaEmpresaService {
	
	public ResponseEntity<CuentaEmpresaResponseRest> findCompanyAccountById(Integer Id);
	public ResponseEntity<CuentaEmpresaResponseRest> findCompanyAccountByCompanyId(Integer IdCompany);
	public ResponseEntity<CuentaEmpresaResponseRest> updateCompanyAccountState(Integer Id);
	public ResponseEntity<CuentaEmpresaResponseRest> createCompanyAccount(CuentaEmpresa cuentaEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> updateCompanyAccount(CuentaEmpresa cuentaEmpresa);
	public ResponseEntity<CuentaEmpresaResponseRest> findCompanyAccountByCode(String Codigo);
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(
			@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo);
	public ResponseEntity<CuentaEmpresaResponseRest> reportAccountPlanHomologo(@RequestParam("acronimo") String acronimo);
	public ResponseEntity<CuentaEmpresaResponseRest> uploadAccountFile(@RequestParam("file") MultipartFile file,
			@RequestParam("acronimo") String acronimo);
	public ResponseEntity<CuentaEmpresaResponseRest> uploadAccountFilev2(@RequestParam("file") MultipartFile file,
			@RequestParam("acronimo") String acronimo);
}
