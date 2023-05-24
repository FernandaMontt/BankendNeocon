package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.model.BalanceDetalle;
import lombok.Data;

@Data
public class BalanceDetalleResponse {
	
	private List<BalanceDetalle> balanceDetalle;

	public List<BalanceDetalle> getBalanceDetalle() {
		return balanceDetalle;
	}

	public void setBalanceDetalle(List<BalanceDetalle> balanceDetalle) {
		this.balanceDetalle = balanceDetalle;
	}

}
