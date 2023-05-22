package com.example.WorkHoursManager.services.caseInfoService;

import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;

public interface CaseInfoService {
	
	public CaseInfoResp getAllCaseInfo();
	public CaseInfoResp getCaseInfoById(CaseInfoReq caseInfoReq);
	public CaseInfoResp getCaseInfoByEmployeeId(CaseInfoReq caseInfoReq);
	public CaseInfoResp setCaseInfo(CaseInfoReq caseInfoReq);
	public CaseInfoResp editCaseInfo(CaseInfoReq caseInfoReq);
	public CaseInfoResp deleteCaseInfo(CaseInfoReq caseInfoReq);
	
}
