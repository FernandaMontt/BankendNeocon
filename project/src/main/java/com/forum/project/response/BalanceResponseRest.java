package com.forum.project.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceResponseRest {
	
	private BalanceResponse balanceResponse = new BalanceResponse();

	public BalanceResponse getBalanceResponse() {
		return balanceResponse;
	}

	public void setBalanceResponse(BalanceResponse balanceResponse) {
		this.balanceResponse = balanceResponse;
	}

}
