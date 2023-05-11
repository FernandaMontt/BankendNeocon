package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.CuentaEmpresa;
import lombok.Data;

@Data
public class CuentaEmpresaResponse {
	
	private List<CuentaEmpresa> cuentaEmpresa;

	public List<CuentaEmpresa> getCuentaEmpresa() {
		return cuentaEmpresa;
	}

	public void setCuentaEmpresa(List<CuentaEmpresa> cuentaEmpresa) {
		this.cuentaEmpresa = cuentaEmpresa;
	}
	
	

}
