package com.example.WorkHoursManager.vo.accountVo;

import com.example.WorkHoursManager.entity.*;

public class AccountReq extends Account {
	
	private String employeeId;
	
	private String newPassword;
	
	//Getter & Setter
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}