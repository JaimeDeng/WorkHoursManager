package com.example.WorkHoursManager.vo.employeeInfoVo;

import com.example.WorkHoursManager.entity.*;

public class EmployeeInfoResp extends EmployeeInfo {
	
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