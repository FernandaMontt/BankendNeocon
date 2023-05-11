package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.InterGroupDetalle;
import lombok.Data;

@Data
public class InterGroupDetalleResponse {

	private List<InterGroupDetalle> interGroupDetalle;

	public List<InterGroupDetalle> getInterGroupDetalle() {
		return interGroupDetalle;
	}

	public void setInterGroupDetalle(List<InterGroupDetalle> interGroupDetalle) {
		this.interGroupDetalle = interGroupDetalle;
	}
}
