package com.example.WorkHoursManager.config;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.WorkHoursInfo;

public class WorkHoursInfoIdConfig implements Serializable {
	
	private Integer workInfoId;
	
	private EmployeeInfo employeeInfo;		//employeeId
	
	//Getter & Setter
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public Integer getWorkInfoId() {
		return workInfoId;
	}
	public void setWorkInfoId(Integer workInfoId) {
		this.workInfoId = workInfoId;
	}
}
