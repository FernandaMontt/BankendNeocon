package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaEmpresaResponseRest {

	private CuentaEmpresaResponse cuentaEmpresaResponse = new CuentaEmpresaResponse();

	public CuentaEmpresaResponse getCuentaEmpresaResponse() {
		return cuentaEmpresaResponse;
	}

	public void setCuentaEmpresaResponse(CuentaEmpresaResponse cuentaEmpresaResponse) {
		this.cuentaEmpresaResponse = cuentaEmpresaResponse;
	}
}
