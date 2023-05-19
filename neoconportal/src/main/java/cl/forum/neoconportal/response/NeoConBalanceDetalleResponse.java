package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.NeoConBalanceDetalle;
import lombok.Data;

@Data
public class NeoConBalanceDetalleResponse {

	private List<NeoConBalanceDetalle> neoConBalanceDetalle;

	public List<NeoConBalanceDetalle> getNeoConBalanceDetalle() {
		return neoConBalanceDetalle;
	}

	public void setNeoConBalanceDetalle(List<NeoConBalanceDetalle> neoConBalanceDetalle) {
		this.neoConBalanceDetalle = neoConBalanceDetalle;
	}
}
