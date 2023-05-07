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
	
	@PostMapping("/editEmployeeInfo")
	public EmployeeInfoResp editEmployeeInfo(@RequestBody EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp = employeeInfoService.editEmployeeInfo(employeeInfoReq);
		return employeeInfoResp;
	}
	
	@PostMapping("/deleteEmployeeInfo")
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
	
	//---------------------------------------WorkHoursInfo API-------------------------------------------
	
	//Read（読み取り）
	@PostMapping("getAllWorkHoursInfo")
	public List<WorkHoursInfo>getInfos(){
		return workHoursService.getAllInfos();
	}
	
	//Create（生成）
	@PostMapping("setWorkHoursInfo")
	public WorkHoursInfoResp workHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp=new WorkHoursInfoResp();
		workHoursInfoResp= workHoursService.addinfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//Delete（削除）
	@DeleteMapping("/api/{id}")
	public ResponseEntity<WorkHoursInfoResp> deleteInfo(@PathVariable("id") int workInfoId){
	    WorkHoursInfoResp result = workHoursService.deleteInfo(workInfoId);
	    if (result.isSuccess()) {
	        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	    }
    }
	
	//Update(更新)
	@PutMapping("/api/{id}")
	public ResponseEntity<WorkHoursInfoResp> updateInfo(@PathVariable("id") int workInfoId, 
			@RequestBody WorkHoursInfoReq workHoursInfoReq) {
	    WorkHoursInfoResp result = workHoursService.updateInfo(workInfoId, workHoursInfoReq);

	    if (result.isSuccess()) {
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	    }
	}
	
	//---------------------------------------CaseInfo API-------------------------------------------
	
	//Read（読み取り）
	@PostMapping("getAllCaseInfo")
	public List<CaseInfo>getCaseInfos(){
		return caseInfoService.getAllInfos();
	}
	
	//Create（生成）
	@PostMapping("setCaseInfo")
	public CaseInfoResp caseInfo(@RequestBody CaseInfoReq caseInfoReq) {
		return caseInfoService.addInfo(caseInfoReq);
	}
		
	//--------------------------------PerformanceReference API------------------------------------
	//Read（読み取り）
	@PostMapping("prallInfo")
	public List<PerformanceReference>getPerformanceReferenceInfos(){
		return performanceReferenceService.getAllInfos();
	}
	
	//Create（生成）
	@PostMapping("pradd")
	public PerformanceReferenceResp setPerformanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		return performanceReferenceService.addInfo(performanceReferenceReq);
	}
	
	//Delete（削除）
//		@DeleteMapping("/pr/{id}")
//		public ResponseEntity<PerformanceReferenceResp> deleteInfo(@PathVariable("id") String caseNo){
//		    PerformanceReferenceResp result = performanceReferenceService.deleteInfo(caseNo);
//		    if (result.isSuccess()) {
//		        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
//		    } else {
//		        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//		    }
//	    }
	
	//Update(更新)
//		@PutMapping("/pr/{id}")
//		public ResponseEntity<PerformanceReferenceResp> updeteInfo(@PathVariable("id") String caseNo,@RequestBody PerformanceReferenceReq performanceReferenceReq){
//		    PerformanceReferenceResp result = performanceReferenceService.updateInfo(caseNo, performanceReferenceReq);
//		    if (result.isSuccess()) {
//		        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
//		    } else {
//		        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//		    }
//	    }
}