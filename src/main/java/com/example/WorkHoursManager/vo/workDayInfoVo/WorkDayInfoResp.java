package com.example.WorkHoursManager.vo.workDayInfoVo;

import com.example.WorkHoursManager.entity.*;

public class WorkDayInfoResp extends WorkDayInfo {
	
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

}