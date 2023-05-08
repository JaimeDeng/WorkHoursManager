package com.example.WorkHoursManager.services.workDayService;

import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoReq;
import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoResp;

public interface WorkDayInfoService {

	public WorkDayInfoResp setWorkDayInfo(WorkDayInfoReq workDayInfoReq);
	public WorkDayInfoResp editWorkDayInfo(WorkDayInfoReq workDayInfoReq);
	public WorkDayInfoResp deleteWorkDayInfo(WorkDayInfoReq workDayInfoReq);
	public WorkDayInfoResp getAllWorkDayInfo(WorkDayInfoReq workDayInfoReq);
	public WorkDayInfoResp getWorkDayInfoByDate(WorkDayInfoReq workDayInfoReq);
	public WorkDayInfoResp getWorkDayInfoByEmployeeId(WorkDayInfoReq workDayInfoReq);
	
}
