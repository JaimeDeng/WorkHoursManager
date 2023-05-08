package com.example.WorkHoursManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.entity.PerformanceReference;
import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.services.workHoursService.WorkHoursService;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.WorkHoursManager.services.accountService.AccountService;
import com.example.WorkHoursManager.services.caseInfoService.CaseInfoService;
import com.example.WorkHoursManager.services.employeeInfoService.EmployeeInfoService;
import com.example.WorkHoursManager.services.performanceReferenceService.PerformanceReferenceService;
import com.example.WorkHoursManager.services.workDayService.WorkDayInfoService;
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
	
	@DeleteMapping("/deleteAccount")
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
	
	@PostMapping("/editEmployeeInfo")
	public EmployeeInfoResp editEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.editEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	@DeleteMapping("/deleteEmployeeInfo")
	public EmployeeInfoResp deleteEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.deleteEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	//---------------------------------------WorkDayInfo API-------------------------------------------
	
	@PostMapping("/setWorkDayInfo")
	public WorkDayInfoResp setWorkDayInfo(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.setWorkDayInfo(workDayInfoReq);
		return workDayInfoResp;
	}

	@GetMapping("/getAllWorkDayInfo")
	public WorkDayInfoResp getAllWorkDayInfo(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getAllWorkDayInfo(workDayInfoReq);
		return workDayInfoResp;
	}
	
	@GetMapping("/getWorkDayInfoByDate")
	public WorkDayInfoResp getWorkDayInfoByDate(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getWorkDayInfoByDate(workDayInfoReq);
		return workDayInfoResp;
	}
	
	@GetMapping("/getWorkDayInfoByEmployeeId")
	public WorkDayInfoResp getWorkDayInfoByEmployeeId(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.getWorkDayInfoByEmployeeId(workDayInfoReq);
		return workDayInfoResp;
	}
	
	@DeleteMapping("/deleteWorkDayInfo")
	public WorkDayInfoResp deleteWorkDayInfo(@RequestBody WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		workDayInfoResp = workDayInfoService.deleteWorkDayInfo(workDayInfoReq);
		return workDayInfoResp;
	}
	
	//---------------------------------------WorkHoursInfo API-------------------------------------------
	
	//Read（読み取り）
	@GetMapping("getAllWorkHoursInfo")
	public WorkHoursInfoResp getAllWorkHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq){
		WorkHoursInfoResp workHoursInfoResp=new WorkHoursInfoResp();
		return workHoursInfoResp;
	}
	
	//Create（生成）
	@PostMapping("setWorkHoursInfo")
	public WorkHoursInfoResp setWorkHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp=new WorkHoursInfoResp();
		workHoursInfoResp= workHoursService.setWorkHoursInfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//Delete（削除）
	@DeleteMapping("/deleteWorkHoursInfo")
	public WorkHoursInfoResp deleteWorkHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		workHoursInfoResp = workHoursService.deleteWorkHoursInfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//Update(更新)
	@PutMapping("/api/{id}")
	public ResponseEntity<WorkHoursInfoResp> updateInfo(@PathVariable("id") int workInfoId, 
			@RequestBody WorkHoursInfoReq workHoursInfoReq) {
	    WorkHoursInfoResp result = workHoursService.editWorkHoursInfo(workHoursInfoReq);

	    if (result.isSuccess()) {
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	    }
	}
	
	//---------------------------------------CaseInfo API-------------------------------------------
	
	//查詢所有caseInfo
	@GetMapping("getAllCaseInfo")
	public CaseInfoResp getAllCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.getAllCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
	
	//新增caseInfo
	@PostMapping("setCaseInfo")
	public CaseInfoResp setCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.setCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
	
	//刪除caseInfo
	@DeleteMapping("deleteCaseInfo")
	public CaseInfoResp deleteCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.deleteCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
	
	//以caseNo查詢caseInfo
	@GetMapping("getCaseInfoByCaseNo")
	public CaseInfoResp getCaseInfoByCaseNo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.getCaseInfoByCaseNo(caseInfoReq);
		return caseInfoResp;
	}
	
	//編輯caseInfo
	@PutMapping("editCaseInfo")
	public CaseInfoResp editCaseInfo(@RequestBody CaseInfoReq caseInfoReq){
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		caseInfoResp = caseInfoService.editCaseInfo(caseInfoReq);
		return caseInfoResp;
	}
		
	//--------------------------------PerformanceReference API------------------------------------
	
	//獲取全部PR資料
	@GetMapping("getAllPerformanceReferences")
	public PerformanceReferenceResp getAllPerformanceReferences(@RequestBody PerformanceReferenceReq performanceReferenceReq){
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.getAllPerformanceReferences(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//以caseNo獲取PR資料
	@GetMapping("getPerformanceReferenceByCaseNo")
	public PerformanceReferenceResp getPerformanceReferenceByCaseNo(@RequestBody PerformanceReferenceReq performanceReferenceReq){
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.getAllPerformanceReferences(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//以caseNo刪除PR資料
	@DeleteMapping("deletePerformanceReference")
	public PerformanceReferenceResp deletePerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.setPerformanceReference(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//新增PR資料(rating預設為null)
	@PostMapping("setPerformanceReference")
	public PerformanceReferenceResp setPerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.setPerformanceReference(performanceReferenceReq);
		return performanceReferenceResp;
	}
	
	//編輯PR資料(rating可以編輯)
	@PutMapping("editPerformanceReference")
	public PerformanceReferenceResp editPerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		performanceReferenceResp = performanceReferenceService.setPerformanceReference(performanceReferenceReq);
		return performanceReferenceResp;
	}

}