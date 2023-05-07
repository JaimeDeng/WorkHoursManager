package com.example.WorkHoursManager.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


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
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonProperty("employeeId")
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id" , 	
		insertable = true, updatable = true)
	private EmployeeInfo employeeInfo;
//-----------------------------------------------------------------------------------------------------------
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "working_hours")
	private Integer workingHours;
	
	@Column(name = "approved")
	private boolean approved;

	//Getter & Setter
	
	//外鍵date實體WorkHoursInfo (有多個以List儲存)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	//外鍵employee_id實體EmployeeInfo
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

	public Integer getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Integer workingHours) {
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
