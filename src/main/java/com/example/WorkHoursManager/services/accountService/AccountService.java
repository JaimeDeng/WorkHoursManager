package com.example.WorkHoursManager.services.accountService;

import com.example.WorkHoursManager.vo.accountVo.AccountReq;
import com.example.WorkHoursManager.vo.accountVo.AccountResp;

public interface AccountService {

	public AccountResp setAccount(AccountReq accountReq);
	public AccountResp deleteAccount(AccountReq accountReq);
	public AccountResp getAccountByEmployeeId(AccountReq accountReq);
	public AccountResp getAllAccount();
	public AccountResp changePassword(AccountReq accountReq);
	
}
