package com.example.WorkHoursManager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "work_day_info")
public class WorkDayInfo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_info_id")
	private Integer workInfoId;
	
	@Column(name ="date")
	private String date;

//---------------------------------------------employeeId------------------------------------------------
	//設置EmployeeInfo為employee_id外鍵關聯對象
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JsonProperty("employeeId")
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id" , 	
		insertable = true, updatable = true)
	private EmployeeInfo employeeInfo;
//-----------------------------------------------------------------------------------------------------------
	
	@Column(name = "status")
	private String status;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name = "working_hours")
	private float workingHours;
	
	@Column(name = "approved")
	private boolean approved;

	//Getter & Setter

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Integer getWorkInfoId() {
		return workInfoId;
	}

	public void setWorkInfoId(Integer workInfoId) {
		this.workInfoId = workInfoId;
	}

	public float getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(float workingHours) {
		this.workingHours = workingHours;
	}

	//Constructor
	public WorkDayInfo() {
	}
	public WorkDayInfo(String date, String status, boolean approved , Integer workingHours) {
		this.date = date;
		this.status = status;
		this.approved = approved;
		this.workingHours = workingHours;
	}
	
	
}
