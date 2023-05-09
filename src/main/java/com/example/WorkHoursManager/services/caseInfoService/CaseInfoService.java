package com.example.WorkHoursManager.services.caseInfoService;

import java.util.List;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;

public interface CaseInfoService {
	
	public CaseInfoResp getAllCaseInfo(CaseInfoReq caseInfoReq);
	public CaseInfoResp getCaseInfoById(CaseInfoReq caseInfoReq);
	public CaseInfoResp setCaseInfo(CaseInfoReq caseInfoReq);
	public CaseInfoResp editCaseInfo(CaseInfoReq caseInfoReq);
	public CaseInfoResp deleteCaseInfo(CaseInfoReq caseInfoReq);
	
}
