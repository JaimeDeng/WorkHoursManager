package com.example.WorkHoursManager.vo.workHoursInfoVo;

import java.util.List;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkHoursInfoResp extends WorkHoursInfo {

	private List<WorkHoursInfo>hoursInfos;
	
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
	
	public List<WorkHoursInfo> getHoursInfos() {
		return hoursInfos;
	}

	public void setHoursInfos(List<WorkHoursInfo> hoursInfos) {
		this.hoursInfos = hoursInfos;
	}

	//Constructor
	
	public WorkHoursInfoResp(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public WorkHoursInfoResp(List<WorkHoursInfo> hoursInfos, String message, boolean success) {
		super();
		this.hoursInfos = hoursInfos;
		this.message = message;
		this.success = success;
	}
	
	public WorkHoursInfoResp() {
	}
}
