package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceDetalleResponseRest {
	
	private BalanceDetalleResponse balanceDetalleResponse = new BalanceDetalleResponse();

	public BalanceDetalleResponse getBalanceDetalleResponse() {
		return balanceDetalleResponse;
	}

	public void setBalanceDetalleResponse(BalanceDetalleResponse balanceDetalleResponse) {
		this.balanceDetalleResponse = balanceDetalleResponse;
	}

}
