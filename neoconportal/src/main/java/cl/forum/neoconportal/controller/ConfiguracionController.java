package cl.forum.neoconportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.CuentaEmpresa;
import cl.forum.neoconportal.model.InterGroupDetalle;
import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.model.Rubro;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;
import cl.forum.neoconportal.response.EmpresaResponseRest;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;
import cl.forum.neoconportal.response.InterGroupResponseRest;
import cl.forum.neoconportal.response.RubroResponseRest;
import cl.forum.neoconportal.services.ICuentaEmpresaService;
import cl.forum.neoconportal.services.IEmpresaService;
import cl.forum.neoconportal.services.IInterGrupoService;
import cl.forum.neoconportal.services.IRubroService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ConfiguracionController {
	
	@Autowired
    private IRubroService rubroService;
    
    @Autowired
    private IEmpresaService empresaService;
    
    @Autowired
    private ICuentaEmpresaService cuentaEmpresaService;
    
    @Autowired
    private IInterGrupoService interGrupoService;
    
    /*Service Rubro*/
    
    @GetMapping("/rubros/estado/{estado}")
	public ResponseEntity<RubroResponseRest> findAllRubros(@PathVariable String estado){
		ResponseEntity<RubroResponseRest> response = rubroService.findAllRubro(estado);
		return response;
	}
	
	@GetMapping("/rubros/{id}")
	public ResponseEntity<RubroResponseRest> findByRubrosId(@PathVariable Integer id){
		ResponseEntity<RubroResponseRest> response = rubroService.findByRubroId(id);
		return response;
	}
	
	@DeleteMapping("/rubros/{id}")
	public ResponseEntity<RubroResponseRest> updatestate(@PathVariable Integer id){
		ResponseEntity<RubroResponseRest> response = rubroService.updatestate(id);
		return response;
	}
	
	@GetMapping("/rubros/codigo/{codigo}")
	public ResponseEntity<RubroResponseRest> findByRubrosCodigo(@PathVariable String codigo){
		ResponseEntity<RubroResponseRest> response = rubroService.findByRubroCodigo(codigo);
		return response;
	}
	
	@PostMapping("/rubros")
	public ResponseEntity<RubroResponseRest> saveRubros(@RequestBody Rubro rubro){
		ResponseEntity<RubroResponseRest> response = rubroService.saveRubros(rubro);
		return response;
	}
	
	@PutMapping("/rubros/{id}")
	public ResponseEntity<RubroResponseRest> updateRubros(@RequestBody Rubro rubro){
		ResponseEntity<RubroResponseRest> response = rubroService.updateRubros(rubro);
		return response;
	}
	/*Empresa Service*/
	
	@GetMapping("/empresas")
	public ResponseEntity<EmpresaResponseRest> findAlLEmpresa(){
		ResponseEntity<EmpresaResponseRest> response = empresaService.findAllEmpresa();
		return response;
	}
	
	@GetMapping("/empresas/{id}")
	public ResponseEntity<EmpresaResponseRest> findByEmpresaId(@PathVariable Integer id){
		ResponseEntity<EmpresaResponseRest> response = empresaService.findByEmpresaId(id);
		return response;
	}
	
	/*Cuenta Empresa Service*/
	
	@GetMapping("/cuentaEmpresas")
	public ResponseEntity<CuentaEmpresaResponseRest> findAllCuentaEmpresa(){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findAllCuentas();
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaEmpresaId(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findByCuentasEmpresaId(id);
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/idempresa/{idempresa}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(@PathVariable Integer idempresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findByCuentasEmpresaEmpresaId(idempresa);
		return response;
	}
	
	@DeleteMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> updatestateCuentaEmpresa(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.updatestateCuentaEmpresa(id);
		return response;
	}
	
	@PostMapping("/cuentaEmpresas")
	public ResponseEntity<CuentaEmpresaResponseRest> saveCuentaEmpresa(@RequestBody CuentaEmpresa cuentaEmpresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.saveCuentaEmpresa(cuentaEmpresa);
		return response;
	}
	
	@PutMapping("/cuentaEmpresas/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> updateCuentaEmpresa(@RequestBody CuentaEmpresa cuentaEmpresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.updateCuentaEmpresa(cuentaEmpresa);
		return response;
	}
	
	@GetMapping("/cuentaEmpresas/codigo/{codigo}")
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaEmpresaCodigo(@PathVariable String codigo){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findByCuentaCodigo(codigo);
		return response;
	}
	
	@PostMapping("/cuentaEmpresas/allintergrupo")
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.searchInterCuentaEmpresa(codigorubro, acronimo);
		return response;
	}
	
	@PostMapping("/cuentaempresa/plancuentas")
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(@RequestParam("acronimo") String acronimo){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.reportePlanCuentasHomologo(acronimo);
		return response;
	}
	
	@PostMapping("/uploadcuentas")
	public ResponseEntity<CuentaEmpresaResponseRest> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("acronimo") String acronimo) {
	  ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.cargarFile(file, acronimo);
	  return response;
	}
	
	@PostMapping("/uploadcuentasv2")
	public ResponseEntity<CuentaEmpresaResponseRest> uploadFilev2(@RequestParam("file") MultipartFile file,@RequestParam("acronimo") String acronimo) {
	  ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.cargarFilev2(file, acronimo);
	  return response;
	}
	
	/*Intergrupo*/
	
	@GetMapping("/intergrupoheader")
	public ResponseEntity<InterGroupResponseRest> findAllInterGroupHeader(){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.findAllIntergrupo();
		return response;
	}
	
	@PostMapping("/intergrupoheader")
	public ResponseEntity<InterGroupResponseRest> saveRubros(@RequestBody InterGroupHeader interGroupHeader){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.saveInterGruposH(interGroupHeader);
		return response;
	}
	
	@DeleteMapping("/intergrupoheader/{id}")
	public ResponseEntity<InterGroupResponseRest> updatestateIntergrupo(@PathVariable Integer id){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.updatestateIntergrupo(id);
		return response;
	}
	
	@PutMapping("/intergrupoheader/{id}")
	public ResponseEntity<InterGroupResponseRest> updateInterGruposH(@RequestBody InterGroupHeader interGroupHeader){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.updateInterGruposH(interGroupHeader);
		return response;
	}
	
	@GetMapping("/intergrupodetalle/{id}")
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumeroIG(@PathVariable Integer id){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.findByNumeroIG(id);
		return response;
	}
	
	@PostMapping("/intergrupodetalle")
	public ResponseEntity<InterGroupDetalleResponseRest> saveInterGruposD(@RequestBody InterGroupDetalle interGroupDetalle){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.saveInterGruposD(interGroupDetalle);
		return response;
	}
	
	@PostMapping("/intergrupodetalle/{id}")
	public ResponseEntity<InterGroupDetalleResponseRest> deleteIntergrupodetalle(@RequestParam("numeroig") Integer numeroig, @RequestParam("acronimo") String acronimo,
			@RequestParam("codigocuenta") int codigocuenta){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.deleteIntergrupodetalle(numeroig, acronimo, codigocuenta);
		return response;
	}
	
	@GetMapping("/intergrupodetalle/id/{id}")
	public ResponseEntity<InterGroupDetalleResponseRest> findByIdIntergrupo(@PathVariable Integer id){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.findById(id);
		return response;
	}
	
	@PostMapping("/intergrupodetalle/allintergrupo")
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa(@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo, 
			@RequestParam("numeroig") Integer numeroig){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.searchInterCuentaEmpresa(codigorubro, acronimo, numeroig);
		return response;
	}
	
	@PostMapping("/intergrupodetalle2/allintergrupo")
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa2(@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo,
			@RequestParam("numeroig") Integer numeroig){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.searchInterCuentaEmpresa2(codigorubro, acronimo, numeroig);
		return response;
	}
	
	@GetMapping("/intergruporeporte")
	public ResponseEntity<InterGroupDetalleResponseRest> reporteintegrupo(){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.reporteintegrupo();
		return response;
	}
}
