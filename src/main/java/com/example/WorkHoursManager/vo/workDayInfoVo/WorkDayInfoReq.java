package com.example.WorkHoursManager.vo.workDayInfoVo;

import com.example.WorkHoursManager.entity.*;

public class WorkDayInfoReq extends WorkDayInfo {
	
	private String employeeId;
	
	private String supervisorId;

	//Getter & Setter
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	
}
