package com.example.WorkHoursManager.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "case_info")
public class CaseInfo {
	
	@Id
	@Column(name = "case_no")
	private String caseNo;
	
	@Column(name = "model")
	private String model;

//-------------------------------------------employeeId-------------------------------------------
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id" , 
    		insertable = true, updatable = false)
	private EmployeeInfo employeeInfo;
//----------------------------------------------------------------------------------------------------
	
	@Column(name = "duration")
	private float duration;
	
	@Column(name = "date")
	private String date;
	
	//Getter & Setter
	
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	
	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}