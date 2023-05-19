package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeoConBalanceDetalleResponseRest {
	
	private NeoConBalanceDetalleResponse neoConBalanceDetalleResponse = new NeoConBalanceDetalleResponse();

	public NeoConBalanceDetalleResponse getNeoConBalanceDetalleResponse() {
		return neoConBalanceDetalleResponse;
	}

	public void setNeoConBalanceDetalleResponse(NeoConBalanceDetalleResponse neoConBalanceDetalleResponse) {
		this.neoConBalanceDetalleResponse = neoConBalanceDetalleResponse;
	}
	
	

}
