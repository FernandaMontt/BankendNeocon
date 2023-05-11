package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.Empresa;
import lombok.Data;
@Data
public class EmpresaResponse {
	
	private List<Empresa> empresa;

	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

}
