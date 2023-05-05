package com.example.WorkHoursManager.vo.employeeInfoVo;

import com.example.WorkHoursManager.entity.*;

public class EmployeeInfoReq extends EmployeeInfo {
	
	private String supervisorId;
	
	
	//Getter & Setter
	public String getSupervisorId() {
		return supervisorId;
	}

	public void getSupervisorId(String supervisor) {
		this.supervisorId = supervisorId;
	}
	
}