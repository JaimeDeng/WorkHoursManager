package com.example.WorkHoursManager.vo.accountVo;

import java.util.List;

import com.example.WorkHoursManager.Dto.AccountDto;
import com.example.WorkHoursManager.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountResp extends Account {
	
	public String message;
	
	public boolean success;
	
	private List<Account> accounts;
	
	private List<AccountDto> accountsForVerify;
	
	//Getter & Setter
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<AccountDto> getAccountsForVerify() {
		return accountsForVerify;
	}

	public void setAccountsForVerify(List<AccountDto> accountsForVerify) {
		this.accountsForVerify = accountsForVerify;
	}
	

}
