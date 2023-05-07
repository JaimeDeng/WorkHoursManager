package com.example.WorkHoursManager.services.caseInfoService;

import java.util.List;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;

public interface CaseInfoService {
	public List<CaseInfo> getAllInfos();

	public CaseInfoResp addInfo(CaseInfoReq caseInfoReq);
}
