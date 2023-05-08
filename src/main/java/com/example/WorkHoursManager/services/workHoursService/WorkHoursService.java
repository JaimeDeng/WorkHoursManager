package com.example.WorkHoursManager.services.workHoursService;

import java.util.List;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

public interface WorkHoursService {
	public WorkHoursInfoResp setWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
	public WorkHoursInfoResp editWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
	public WorkHoursInfoResp deleteWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
	public WorkHoursInfoResp getAllWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq);
}
