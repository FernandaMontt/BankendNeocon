package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.ValidacionMensaje;
import lombok.Data;

@Data
public class ValidacionMensajeResponse {
	
	private List<ValidacionMensaje> validacionMensaje;

	public List<ValidacionMensaje> getValidacionMensaje() {
		return validacionMensaje;
	}

	public void setValidacionMensaje(List<ValidacionMensaje> validacionMensaje) {
		this.validacionMensaje = validacionMensaje;
	}
	
	

}
