package com.example.WorkHoursManager.vo.workHoursInfoVo;

import com.example.WorkHoursManager.entity.WorkHoursInfo;

public class WorkHoursInfoReq extends WorkHoursInfo {
	
	private String employeeId;
	
	//Getters and Setters

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
}
