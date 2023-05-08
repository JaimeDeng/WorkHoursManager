package com.example.WorkHoursManager.vo.workHoursInfoVo;

import java.util.List;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorkHoursInfoResp extends WorkHoursInfo {

	@JsonInclude(JsonInclude.Include.ALWAYS)
	private List<WorkHoursInfo>workHoursInfoList;
	
	public String message;
	
	public boolean success;
	
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
	
	public List<WorkHoursInfo> getWorkHoursInfoList() {
		return workHoursInfoList;
	}

	public void setWorkHoursInfoList(List<WorkHoursInfo> workHoursInfoList) {
		this.workHoursInfoList = workHoursInfoList;
	}

	//Constructor
	
	public WorkHoursInfoResp(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public WorkHoursInfoResp(List<WorkHoursInfo> workHoursInfoList, String message, boolean success) {
		super();
		this.workHoursInfoList = workHoursInfoList;
		this.message = message;
		this.success = success;
	}
	
	public WorkHoursInfoResp() {
	}
}
