package com.example.WorkHoursManager.vo.workHoursInfoVo;

import com.example.WorkHoursManager.entity.WorkHoursInfo;

public class WorkHoursInfoReq extends WorkHoursInfo {
	
	private String date;
	private String employeeId;
	private String caseNo;
	
	//Getters and Setters
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
}
