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
@RequestMapping("/neoconportal/v1")
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
    
    @GetMapping("/item/state/{state}")
	public ResponseEntity<RubroResponseRest> findAllItems(@PathVariable String state){
		ResponseEntity<RubroResponseRest> response = rubroService.findAllItems(state);
		return response;
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<RubroResponseRest> findByItemId(@PathVariable Integer id){
		ResponseEntity<RubroResponseRest> response = rubroService.findByItemId(id);
		return response;
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<RubroResponseRest> updateItemState(@PathVariable Integer id){
		ResponseEntity<RubroResponseRest> response = rubroService.updateState(id);
		return response;
	}
	
	@GetMapping("/item/code/{code}")
	public ResponseEntity<RubroResponseRest> findItemByCode(@PathVariable String code){
		ResponseEntity<RubroResponseRest> response = rubroService.findItemByCode(code);
		return response;
	}
	
	@PostMapping("/items")
	public ResponseEntity<RubroResponseRest> createItem(@RequestBody Rubro rubro){
		ResponseEntity<RubroResponseRest> response = rubroService.createItem(rubro);
		return response;
	}
	
	@PutMapping("/items/{id}")
	public ResponseEntity<RubroResponseRest> updateItem(@RequestBody Rubro rubro){
		ResponseEntity<RubroResponseRest> response = rubroService.updateItem(rubro);
		return response;
	}
	/*Empresa Service*/
	
	@GetMapping("/company/{id}")
	public ResponseEntity<EmpresaResponseRest> findCompanyById(@PathVariable Integer id){
		ResponseEntity<EmpresaResponseRest> response = empresaService.findCompanyById(id);
		return response;
	}
	
	/*Cuenta Empresa Service*/
	
	@GetMapping("/companyaccount/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> findCompanyAccountById(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findCompanyAccountById(id);
		return response;
	}
	
	@GetMapping("/companyaccount/idcompany/{idcompany}")
	public ResponseEntity<CuentaEmpresaResponseRest> findCompanyAccountByCompanyId(@PathVariable Integer idcompany){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findCompanyAccountByCompanyId(idcompany);
		return response;
	}
	
	@DeleteMapping("/companyaccount/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> updateCompanyAccountState(@PathVariable Integer id){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.updateCompanyAccountState(id);
		return response;
	}
	
	@PostMapping("/companyaccount")
	public ResponseEntity<CuentaEmpresaResponseRest> createCompanyAccount(@RequestBody CuentaEmpresa cuentaEmpresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.createCompanyAccount(cuentaEmpresa);
		return response;
	}
	
	@PutMapping("/companyaccount/{id}")
	public ResponseEntity<CuentaEmpresaResponseRest> updateCompanyAccount(@RequestBody CuentaEmpresa cuentaEmpresa){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.updateCompanyAccount(cuentaEmpresa);
		return response;
	}
	
	@GetMapping("/companyaccount/code/{code}")
	public ResponseEntity<CuentaEmpresaResponseRest> findCompanyAccountByCode(@PathVariable String code){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.findCompanyAccountByCode(code);
		return response;
	}
	
	@PostMapping("/companyaccount/accountplan")
	public ResponseEntity<CuentaEmpresaResponseRest> reportAccountPlanHomologo(@RequestParam("acronimo") String acronimo){
		ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.reportAccountPlanHomologo(acronimo);
		return response;
	}
	
	@PostMapping("/uploadaccount")
	public ResponseEntity<CuentaEmpresaResponseRest> uploadAccountFile(@RequestParam("file") MultipartFile file,@RequestParam("acronimo") String acronimo) {
	  ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.uploadAccountFile(file, acronimo);
	  return response;
	}
	
	@PostMapping("/uploadaccountv2")
	public ResponseEntity<CuentaEmpresaResponseRest> uploadAccountFilev2(@RequestParam("file") MultipartFile file,@RequestParam("acronimo") String acronimo) {
	  ResponseEntity<CuentaEmpresaResponseRest> response = cuentaEmpresaService.uploadAccountFilev2(file, acronimo);
	  return response;
	}
	
	/*Intergrupo*/
	
	@PostMapping("/intergroup")
	public ResponseEntity<InterGroupResponseRest> createInterGroup(@RequestBody InterGroupHeader interGroupHeader){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.createInterGroup(interGroupHeader);
		return response;
	}
	
	@DeleteMapping("/intergroup/state/{id}")
	public ResponseEntity<InterGroupResponseRest> updateInterGroupState(@PathVariable Integer id){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.updateInterGroupState(id);
		return response;
	}
	
	@PutMapping("/intergroup/{id}")
	public ResponseEntity<InterGroupResponseRest> updateInterGroup(@RequestBody InterGroupHeader interGroupHeader){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.updateInterGroup(interGroupHeader);
		return response;
	}
	
	@GetMapping("/intergroupdetail/{id}")
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumberIG(@PathVariable Integer id){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.findByNumberIG(id);
		return response;
	}
	
	@PostMapping("/intergroupdetail")
	public ResponseEntity<InterGroupDetalleResponseRest> createInterGroupDetail(@RequestBody InterGroupDetalle interGroupDetalle){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.createInterGroupDetail(interGroupDetalle);
		return response;
	}
	
	@PostMapping("/intergroupdetail/{id}")
	public ResponseEntity<InterGroupDetalleResponseRest> deleteInterGroupDetail(@RequestParam("numeroig") Integer numeroig, @RequestParam("acronimo") String acronimo,
			@RequestParam("codigocuenta") int codigocuenta){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.deleteInterGroupDetail(numeroig, acronimo, codigocuenta);
		return response;
	}
	
	@GetMapping("/intergroupdetail/id/{id}")
	public ResponseEntity<InterGroupResponseRest> findInterGroupById(@PathVariable Integer id){
		ResponseEntity<InterGroupResponseRest> response = interGrupoService.findInterGroupById(id);
		return response;
	}
	
	@PostMapping("/intergroupdetail/allintergrupo")
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa(@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo, 
			@RequestParam("numeroig") Integer numeroig){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.searchIntergroupCompanyAccounts(codigorubro, acronimo, numeroig);
		return response;
	}
	
	@PostMapping("/intergroupdetail2/allintergrupo")
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa2(@RequestParam("codigorubro") Integer codigorubro, @RequestParam("acronimo") String acronimo,
			@RequestParam("numeroig") Integer numeroig){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.searchIntergroupCompanyAccounts2(codigorubro, acronimo, numeroig);
		return response;
	}
	
	@GetMapping("/reportintergroup")
	public ResponseEntity<InterGroupDetalleResponseRest> reportInterGroup(){
		ResponseEntity<InterGroupDetalleResponseRest> response = interGrupoService.reportInterGroup();
		return response;
	}
}
