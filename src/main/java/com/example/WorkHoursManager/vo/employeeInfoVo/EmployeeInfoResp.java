package com.example.WorkHoursManager.vo.employeeInfoVo;

import java.util.List;

import com.example.WorkHoursManager.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class EmployeeInfoResp extends EmployeeInfo {
	
	public String message;
	
	public boolean success;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	List<EmployeeInfo>employeeInfoList;
	
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

	public List<EmployeeInfo> getEmployeeInfoList() {
		return employeeInfoList;
	}

	public void setEmployeeInfoList(List<EmployeeInfo> employeeInfoList) {
		this.employeeInfoList = employeeInfoList;
	}

}