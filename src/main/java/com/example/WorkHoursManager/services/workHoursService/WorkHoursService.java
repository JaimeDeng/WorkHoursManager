package com.example.WorkHoursManager.services.workHoursService;

import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

public interface WorkHoursService {
	public WorkHoursInfoResp setWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
	public WorkHoursInfoResp editWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
	public WorkHoursInfoResp deleteWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
	public WorkHoursInfoResp getAllWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
}
