package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidacionMensajeResponseRest {
	
	private ValidacionMensajeResponse validacionMensajeResponse = new ValidacionMensajeResponse();

	public ValidacionMensajeResponse getValidacionMensajeResponse() {
		return validacionMensajeResponse;
	}

	public void setValidacionMensajeResponse(ValidacionMensajeResponse validacionMensajeResponse) {
		this.validacionMensajeResponse = validacionMensajeResponse;
	}
	
	

}
