package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterGroupDetalleResponseRest {
	
	private InterGroupDetalleResponse interGroupDetalleResponse = new InterGroupDetalleResponse();

	public InterGroupDetalleResponse getInterGroupDetalleResponse() {
		return interGroupDetalleResponse;
	}

	public void setInterGroupDetalleResponse(InterGroupDetalleResponse interGroupDetalleResponse) {
		this.interGroupDetalleResponse = interGroupDetalleResponse;
	}

	
}
