package com.example.WorkHoursManager.vo.workDayInfoVo;

import java.util.List;

import com.example.WorkHoursManager.Dto.WorkDayInfoDto;
import com.example.WorkHoursManager.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties("approved")
public class WorkDayInfoResp extends WorkDayInfo {
	
	public String message;
	
	public boolean success;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private List<WorkDayInfo>workDayInfoList;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private List<WorkDayInfoDto>pendingApprovalWorkDayInfoList;
	
	//Getter & Setter
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<WorkDayInfo> getWorkDayInfoList() {
		return workDayInfoList;
	}

	public void setWorkDayInfoList(List<WorkDayInfo> workDayInfoList) {
		this.workDayInfoList = workDayInfoList;
	}

	public List<WorkDayInfoDto> getPendingApprovalWorkDayInfoList() {
		return pendingApprovalWorkDayInfoList;
	}

	public void setPendingApprovalWorkDayInfoList(List<WorkDayInfoDto> pendingApprovalWorkDayInfoList) {
		this.pendingApprovalWorkDayInfoList = pendingApprovalWorkDayInfoList;
	}

}