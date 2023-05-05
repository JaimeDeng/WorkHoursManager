package com.example.WorkHoursManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.WorkHoursManager.entity.*;
import com.example.WorkHoursManager.services.accountService.AccountService;
import com.example.WorkHoursManager.services.employeeInfoService.EmployeeInfoService;
import com.example.WorkHoursManager.vo.accountVo.AccountReq;
import com.example.WorkHoursManager.vo.accountVo.AccountResp;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoReq;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoResp;

@RestController
@CrossOrigin
public class WorkHoursManagerController {
	
	//-----------------------Constructor Injection---------------------------
	private final AccountService accountService;	
	private final EmployeeInfoService employeeInfoService;
	
	@Autowired
	public WorkHoursManagerController(@Qualifier("accountService") AccountService accountService,
			@Qualifier("employeeInfoService")EmployeeInfoService employeeInfoService) {
				this.accountService = accountService;
				this.employeeInfoService = employeeInfoService;
	}
	//-----------------------Constructor Injection---------------------------
	
	//---------------------------------------Account API-------------------------------------------
	@PostMapping("/setAccount")
	public AccountResp setAccount(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.setAccount(accountReq);
		return accountResp;
	}
	
	@GetMapping("/getAllAccount")
	public AccountResp getAllAccount(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.getAllAccount(accountReq);
		return accountResp;
	}
	
	@GetMapping("/getAccountByEmployeeId")
	public AccountResp getAccountByEmployeeId(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.getAccountByEmployeeId(accountReq);
		return accountResp;
	}
	
	@PostMapping("/deleteAccount")
	public AccountResp deleteAccount(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.deleteAccount(accountReq);
		return accountResp;
	}
	
	@PostMapping("/changPassword")
	public AccountResp changPassword(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.changPassword(accountReq);
		return accountResp;
	}
	
	//---------------------------------------EmployeeInfo API-------------------------------------------
	
	@PostMapping("/setEmployeeInfo")
	public EmployeeInfoResp setEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.setEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	@GetMapping("/getAllEmployeeInfo")
	public EmployeeInfoResp getAllEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.getAllEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	@GetMapping("/getEmployeeInfoById")
	public EmployeeInfoResp getEmployeeInfoById(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.getEmployeeInfoById(employeeInfoReq);
		return employeeInfoResp;
	}


}