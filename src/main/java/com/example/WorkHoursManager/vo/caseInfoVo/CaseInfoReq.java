package com.example.WorkHoursManager.vo.caseInfoVo;

import com.example.WorkHoursManager.entity.*;

public class CaseInfoReq extends CaseInfo {
	String employeeId;

	//Getters and Setters
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}