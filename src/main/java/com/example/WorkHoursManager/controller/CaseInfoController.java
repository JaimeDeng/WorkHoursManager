package com.example.WorkHoursManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.services.caseInfoService.CaseInfoService;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;

@RestController
@CrossOrigin
public class CaseInfoController {
	
	@Autowired
	private CaseInfoService caseInfoService;
	
	//Read（読み取り）
	@PostMapping("allCaseInfo")
	public List<CaseInfo>getInfos(){
		return caseInfoService.getAllInfos();
	}
	
	//Create（生成）
	@PostMapping("addCaseInfo")
	public CaseInfoResp caseInfo(@RequestBody CaseInfoReq caseInfoReq) {
		return caseInfoService.addInfo(caseInfoReq);
	}
}
