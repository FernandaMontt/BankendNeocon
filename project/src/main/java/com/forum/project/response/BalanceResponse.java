package com.forum.project.response;

import java.util.List;

import com.forum.project.model.Balance;

import lombok.Data;

@Data
public class BalanceResponse {
	
	private List<Balance> balance;

	public List<Balance> getBalance() {
		return balance;
	}

	public void setBalance(List<Balance> balance) {
		this.balance = balance;
	}

}
