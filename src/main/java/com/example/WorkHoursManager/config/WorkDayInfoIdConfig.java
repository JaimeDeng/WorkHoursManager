package com.example.WorkHoursManager.config;

import java.io.Serializable;

import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.WorkHoursInfo;

public class WorkDayInfoIdConfig implements Serializable {
	
	private WorkHoursInfo workHoursInfo;	//date
	private EmployeeInfo employeeInfo;		//employeeId
	
	//Getter & Setter
	public WorkHoursInfo getWorkHoursInfo() {
		return workHoursInfo;
	}
	public void setWorkHoursInfo(WorkHoursInfo workHoursInfo) {
		this.workHoursInfo = workHoursInfo;
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
}
