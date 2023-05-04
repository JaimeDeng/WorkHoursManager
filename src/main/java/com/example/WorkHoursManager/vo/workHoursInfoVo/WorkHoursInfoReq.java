package com.example.WorkHoursManager.vo.workHoursInfoVo;

import java.util.List;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkHoursInfoReq extends WorkHoursInfo {
	
	@JsonProperty("WorkHoursInfo")
	private List<WorkHoursInfo>hoursInfos;
	
	public List<WorkHoursInfo> getHoursInfos() {
		return hoursInfos;
	}

	public void setHoursInfos(List<WorkHoursInfo> hoursInfos) {
		this.hoursInfos = hoursInfos;
	}	
	
	public WorkHoursInfoReq() {
	}

	public WorkHoursInfoReq(List<WorkHoursInfo> hoursInfos) {
		super();
		this.hoursInfos = hoursInfos;
	}
}
