package com.example.WorkHoursManager.services.performanceReferenceService;

import java.util.List;

import com.example.WorkHoursManager.entity.PerformanceReference;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceReq;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceResp;

public interface PerformanceReferenceService {
	public List<PerformanceReference> getAllInfos();
	public PerformanceReferenceResp addInfo(PerformanceReferenceReq performanceReferenceReq);
//	public PerformanceReferenceResp deleteInfo(String caseNo);
//	public PerformanceReferenceResp updateInfo(String caseNo, PerformanceReferenceReq performanceReferenceReq);
}
