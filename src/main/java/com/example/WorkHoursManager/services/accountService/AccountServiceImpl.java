package com.example.WorkHoursManager.services.accountService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.Account;
import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.repository.AccountDao;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.vo.accountVo.AccountReq;
import com.example.WorkHoursManager.vo.accountVo.AccountResp;

@Service
@Transactional
@Qualifier("accountService")
public class AccountServiceImpl implements AccountService {

	//-----------------------Constructor Injection---------------------------
	private final AccountDao accountDao;
	
	private final EmployeeInfoDao employeeInfoDao;
	
	@Autowired
	public AccountServiceImpl(@Qualifier("accountDao")AccountDao accountDao ,
			@Qualifier("employeeInfoDao")EmployeeInfoDao employeeInfoDao) {
		this.accountDao = accountDao;
		this.employeeInfoDao = employeeInfoDao;
	}
	//-----------------------Constructor Injection---------------------------
	
	//-----------------------新增帳號-----------------------
	@Override
	public AccountResp setAccount(AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		Account newAccount = new Account();
		String employeeId = accountReq.getEmployeeId();
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
		
		//check area
		Account thisAccount = accountDao.getAccountByAccountId(accountReq.getAccountId());
		if(thisAccount != null){
			accountResp.message = "此帳號已被使用!";
			accountResp.success = false;
			return accountResp;
		}
		thisAccount = accountDao.getAccountByEmployeeInfo(employeeInfo);
		if(thisAccount != null){
			accountResp.message = "此員工已有帳號!";
			accountResp.success = false;
			return accountResp;
		}
		if(!StringUtils.hasText(accountReq.getAccountId())){
			accountResp.message = "請輸入帳號,不得為空白";
			accountResp.success = false;
			return accountResp;
		}
		if(!StringUtils.hasText(accountReq.getPassword())){
			accountResp.message = "請輸入密碼,不得為空白";
			accountResp.success = false;
			return accountResp;
		}
		if(!StringUtils.hasText(employeeId)) {
			accountResp.message = "請輸入員工ID,不得為空白";
			accountResp.success = false;
			return accountResp;
		}
		if(employeeInfo == null) {
			accountResp.message = "此員工ID不存在!";
			accountResp.success = false;
			return accountResp;
		}
		newAccount.setAccountId(accountReq.getAccountId());
		newAccount.setPassword(accountReq.getPassword());
		newAccount.setEmployeeInfo(employeeInfo);
		accountDao.save(newAccount);
		accountResp.message = "新增成功";
		accountResp.success = true;
		return accountResp;
	}

	//-----------------------獲取全部帳號-----------------------
	@Override
	public AccountResp getAllAccount() {
		AccountResp accountResp = new AccountResp();
		List<Account > accounts = accountDao.findAll();
		accountResp.setAccounts(accounts);
		accountResp.message = "資料獲取成功";
		accountResp.success = true;
		return accountResp;
	}

	//-----------------------獲取指定員工ID帳號-----------------------
	@Override
	public AccountResp getAccountByEmployeeId(AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		Account account = new Account();
		String employeeId = accountReq.getEmployeeId();
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
		account = accountDao.getAccountByEmployeeInfo(employeeInfo);
		if(!StringUtils.hasText(employeeId)) {
			accountResp.message = "請輸入員工ID,不得為空白";
			accountResp.success = false;
			return accountResp;
		}
		if(employeeInfo == null) {
			accountResp.message = "此員工ID不存在!";
			accountResp.success = false;
			return accountResp;
		}
		accountResp.setAccountId(account.getAccountId());
		accountResp.setPassword(account.getPassword());
		accountResp.setEmployeeInfo(account.getEmployeeInfo());
		accountResp.message = "資料獲取成功";
		accountResp.success = true;
		return accountResp;
	}

	//-----------------------刪除指定帳號-----------------------
	@Override
	public AccountResp deleteAccount(AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		String accountId = accountReq.getAccountId();
		if(accountDao.getAccountByAccountId(accountId) == null) {
			accountResp.message = "無此帳號!";
			accountResp.success = false;
			return accountResp;
		}
		accountDao.getAccountByAccountId(accountId).getEmployeeInfo().setAccount(null);
		accountDao.delete(accountDao.getAccountByAccountId(accountId));
		if(accountDao.getAccountByAccountId(accountId) == null) {
			accountResp.message = "帳號刪除成功";
			accountResp.success = true;
			return accountResp;
		}
		accountResp.message = "刪除失敗!";
		accountResp.success = false;
		return accountResp;
	}

	//-----------------------修改密碼-----------------------
	@Override
	public AccountResp changPassword(AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		String account = accountReq.getAccountId();
		String newPassword = accountReq.getNewPassword();
		if(accountDao.getAccountByAccountId(account) == null) {
			accountResp.message = "無此帳號!";
			accountResp.success = false;
			return accountResp;
		}
		if(!StringUtils.hasText(newPassword)) {
			accountResp.message = "請輸入密碼,不得為空白";
			accountResp.success = false;
			return accountResp;
		}
		if(accountDao.getAccountByAccountId(account).getPassword().equals(newPassword)) {
			accountResp.message = "新密碼不得與舊密碼相同!";
			accountResp.success = false;
			return accountResp;
		}
		Account newAccount = accountDao.getAccountByAccountId(account);
		newAccount.setPassword(newPassword);
		accountDao.save(newAccount);
		accountResp.message = "密碼修改成功!";
		accountResp.success = true;
		return accountResp;
	}
	
}
