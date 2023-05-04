package com.example.WorkHoursManager.services.workHoursService;

import java.util.List;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

public interface WorkHoursService {
	public List<WorkHoursInfo> getAllInfos();
	public WorkHoursInfoResp addinfo(WorkHoursInfoReq workHoursInfoReq);
	public String deleteInfo(int workInfoId);
}
