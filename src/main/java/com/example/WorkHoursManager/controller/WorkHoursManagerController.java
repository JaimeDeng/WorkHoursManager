package com.example.WorkHoursManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WorkHoursManager.services.workHoursService.WorkHoursService;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.WorkHoursManager.services.accountService.AccountService;
import com.example.WorkHoursManager.services.caseInfoService.CaseInfoService;
import com.example.WorkHoursManager.services.employeeInfoService.EmployeeInfoService;
import com.example.WorkHoursManager.services.performanceReferenceService.PerformanceReferenceService;
import com.example.WorkHoursManager.services.workDayInfoService.WorkDayInfoService;
import com.example.WorkHoursManager.vo.accountVo.AccountReq;
import com.example.WorkHoursManager.vo.accountVo.AccountResp;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoReq;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoResp;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceReq;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceResp;
import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoReq;
import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoResp;

@RestController
@CrossOrigin
public class WorkHoursManagerController {

	//-----------------------Constructor Injection---------------------------
	private final AccountService accountService;	
	private final EmployeeInfoService employeeInfoService;
	private final WorkDayInfoService workDayInfoService;
	private final WorkHoursService workHoursService;
	private final CaseInfoService caseInfoService;
	private final PerformanceReferenceService performanceReferenceService;
	
	@Autowired
	public WorkHoursManagerController(
			@Qualifier("accountService") AccountService accountService ,
			@Qualifier("employeeInfoService")EmployeeInfoService employeeInfoService , 
			@Qualifier("workDayInfoService")WorkDayInfoService workDayInfoService,
			@Qualifier("workHoursService")WorkHoursService workHoursService,
			@Qualifier("caseInfoService")CaseInfoService caseInfoService,
			@Qualifier("performanceReferenceService")PerformanceReferenceService performanceReferenceService) {
				this.accountService = accountService;
				this.employeeInfoService = employeeInfoService;
				this.workDayInfoService = workDayInfoService;
				this.workHoursService = workHoursService;
				this.caseInfoService = caseInfoService;
				this.performanceReferenceService = performanceReferenceService;
	}
	//-----------------------Constructor Injection---------------------------
	
	//---------------------------------------Account API-------------------------------------------
	
	//新增帳號
	@PostMapping(value = "/setAccount" , produces = "application/json;charset=UTF-8")
	public AccountResp setAccount(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.setAccount(accountReq);
		return accountResp;
	}
	
	//獲取全部帳號資訊
	@GetMapping(value = "/getAllAccount" , produces = "application/json;charset=UTF-8")
	public AccountResp getAllAccount() {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.getAllAccount();
		return accountResp;
	}
	
	//以員工ID獲取帳號資訊
	@PutMapping(value = "/getAccountByEmployeeId" , produces = "application/json;charset=UTF-8")
	public AccountResp getAccountByEmployeeId(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.getAccountByEmployeeId(accountReq);
		return accountResp;
	}
	
	//刪除指定帳號
	@PostMapping(value = "/deleteAccount" , produces = "application/json;charset=UTF-8")
	public AccountResp deleteAccount(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.deleteAccount(accountReq);
		return accountResp;
	}
	
	//修改指定帳號的密碼
	@PutMapping(value = "/changPassword" , produces = "application/json;charset=UTF-8")
	public AccountResp changPassword(@RequestBody AccountReq accountReq) {
		AccountResp accountResp = new AccountResp();
		accountResp = accountService.changPassword(accountReq);
		return accountResp;
	}
	
	//---------------------------------------EmployeeInfo API-------------------------------------------
	
	//新增員工資訊
	@PostMapping(value = "/setEmployeeInfo" , produces = "application/json;charset=UTF-8")
	public EmployeeInfoResp setEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.setEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	//獲取全部員工資訊
	@GetMapping(value = "/getAllEmployeeInfo" , produces = "application/json;charset=UTF-8")
	public EmployeeInfoResp getAllEmployeeInfo() {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.getAllEmployeeInfo();
		return employeeInfoResp;
	}
	
	//以員工ID獲取員工資訊
	@PutMapping(value = "/getEmployeeInfoById" , produces = "application/json;charset=UTF-8")
	public EmployeeInfoResp getEmployeeInfoById(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.getEmployeeInfoById(employeeInfoReq);
		return employeeInfoResp;
	}
	
	//修改指定ID員工資訊
	@PutMapping(value = "/editEmployeeInfo"  , produces = "application/json;charset=UTF-8")
	public EmployeeInfoResp editEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.editEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	//刪除指定ID員工資訊
	@PostMapping(value = "/deleteEmployeeInfo" , produces = "application/json;charset=UTF-8")
	public EmployeeInfoResp deleteEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.deleteEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	//---------------------------------------WorkDayInfo API-------------------------------------------
	
	//新增日報表
	@PostMapping(value = "/setWorkDayInfo" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp setWorkDayInfo(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.setWorkDayInfo(workDayInfoReq);
		return workDayInfoResp;
	}

	//獲取所有日報表
	@GetMapping(value = "/getAllWorkDayInfo" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp getAllWorkDayInfo() {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getAllWorkDayInfo();
		return workDayInfoResp;
	}
	
	//獲取指定日期日報表
	@PutMapping(value = "/getWorkDayInfoByDate" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp getWorkDayInfoByDate(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getWorkDayInfoByDate(workDayInfoReq);
		return workDayInfoResp;
	}
	
	//獲取指定員工日報表
	@PutMapping(value = "/getWorkDayInfoByEmployeeId" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp getWorkDayInfoByEmployeeId(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getWorkDayInfoByEmployeeId(workDayInfoReq);
		return workDayInfoResp;
	}
	
	//刪除指定ID日報表
	@PostMapping(value = "/deleteWorkDayInfo" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp deleteWorkDayInfo(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.deleteWorkDayInfo(workDayInfoReq);
		return workDayInfoResp;
	}
	
	//修改指定ID日報表
	@PostMapping(value = "/editWorkDayInfo" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp editWorkDayInfo(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.editWorkDayInfo(workDayInfoReq);
		return workDayInfoResp;
	}

	//獲取指定主管ID的待審核日報表
	@PutMapping(value = "/getPendingApprovalWorkDayInfoBySupervisorId" , produces = "application/json;charset=UTF-8")
	public WorkDayInfoResp getPendingApprovalWorkDayInfoBySupervisorId(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getPendingApprovalWorkDayInfoBySupervisorId(workDayInfoReq);
		return workDayInfoResp;
	}
	
	//---------------------------------------WorkHoursInfo API-------------------------------------------
	
	//讀取所有工時表
	@GetMapping(value= "getAllWorkHoursInfo" , produces = "application/json;charset=UTF-8")
	public WorkHoursInfoResp getAllWorkHoursInfo(){
		WorkHoursInfoResp workHoursInfoResp=new WorkHoursInfoResp();
		workHoursInfoResp= workHoursService.getAllWorkHoursInfo();
		return workHoursInfoResp;
	}
	
	//讀取指定員工工時表
	@PutMapping(value= "/getWorkHoursInfoByEmployeeId" , produces = "application/json;charset=UTF-8")
	public WorkHoursInfoResp getWorkHoursInfoByEmployeeId(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		workHoursInfoResp = workHoursService.getWorkHoursInfoByEmployeeId(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//讀取指定工時表ID的工時表
	@PutMapping(value= "/getWorkHoursInfoById" , produces = "application/json;charset=UTF-8")
	public WorkHoursInfoResp getWorkHoursInfoById(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		workHoursInfoResp = workHoursService.getWorkHoursInfoById(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//新增工時表
	@PostMapping(value= "setWorkHoursInfo" , produces = "application/json;charset=UTF-8")
	public WorkHoursInfoResp setWorkHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp=new WorkHoursInfoResp();
		workHoursInfoResp= workHoursService.setWorkHoursInfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//刪除工時表
	@PostMapping(value = "/deleteWorkHoursInfo" , produces = "application/json;charset=UTF-8")
	public WorkHoursInfoResp deleteWorkHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		workHoursInfoResp = workHoursService.deleteWorkHoursInfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//編輯工時表
	@PutMapping(value= "/editWorkHoursInfo" , produces = "application/json;charset=UTF-8")
	public WorkHoursInfoResp editWorkHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		workHoursInfoResp = workHoursService.editWorkHoursInfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//---------------------------------------CaseInfo API-------------------------------------------
	
	//查詢所有caseInfo
	@GetMapping(value = "getAllCaseInfo" , produces = "application/json;charset=UTF-8")
	public CaseInfoResp getAllCaseInfo(){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.getAllCaseInfo();
		return caseInfoResp;
	}
	
	//新增caseInfo
	@PostMapping(value = "setCaseInfo" , produces = "application/json;charset=UTF-8")
	public CaseInfoResp setCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.setCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
	
	//刪除caseInfo
	@PostMapping(value = "deleteCaseInfo" , produces = "application/json;charset=UTF-8")
	public CaseInfoResp deleteCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.deleteCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
	
	//以caseNo查詢caseInfo
	@PutMapping(value = "getCaseInfoById" , produces = "application/json;charset=UTF-8")
	public CaseInfoResp getCaseInfoByCaseNo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.getCaseInfoById(caseInfoReq);
		return caseInfoResp;
	}
	
	//編輯caseInfo
	@PutMapping(value = "editCaseInfo" , produces = "application/json;charset=UTF-8")
	public CaseInfoResp editCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.editCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
		
	//--------------------------------PerformanceReference API------------------------------------
	
	//獲取全部PR資料
	@GetMapping(value = "getAllPerformanceReferences" , produces = "application/json;charset=UTF-8")
	public PerformanceReferenceResp getAllPerformanceReferences(){
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.getAllPerformanceReferences();
		return performanceReferenceResp;
	}
	
	//以caseNo獲取PR資料
	@PutMapping(value = "getPerformanceReferenceByCaseNo" , produces = "application/json;charset=UTF-8")
	public PerformanceReferenceResp getPerformanceReferenceByCaseNo(@RequestBody PerformanceReferenceReq performanceReferenceReq){
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.getPerformanceReferenceByCaseNo(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//以caseNo刪除PR資料
	@PostMapping(value = "deletePerformanceReference" , produces = "application/json;charset=UTF-8")
	public PerformanceReferenceResp deletePerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.deletePerformanceReference(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//新增PR資料(rating預設為null)
	@PostMapping(value= "setPerformanceReference" , produces = "application/json;charset=UTF-8")
	public PerformanceReferenceResp setPerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.setPerformanceReference(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//編輯PR資料(rating可以編輯)
	@PutMapping(value= "editPerformanceReference" , produces = "application/json;charset=UTF-8")
	public PerformanceReferenceResp editPerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.editPerformanceReference(performanceReferenceReq);
		return performanceReferenceResp;
	}

}