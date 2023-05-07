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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "work_hours_info")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "workInfoId")
public class WorkHoursInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_info_id")
	private Integer workInfoId;

//---------------------------------------------employeeId------------------------------------------------
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id", 
	insertable = true, updatable = true)
	private EmployeeInfo employeeInfo;
//----------------------------------------------------------------------------------------------------------
	
	@Column(name ="date")
	private String date;
	
	@Column(name = "model")
	private String model;

//---------------------------------------------caseNo------------------------------------------------
	//設置PerformanceReference為case_no的外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_no", referencedColumnName = "case_no" , nullable=true,
			insertable = false, updatable = false)
	private PerformanceReference performanceReference;
	
	//設置CaseInfo為case_no的外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_no", referencedColumnName = "case_no" , nullable=true)
	private CaseInfo caseInfo; 
	//----------------------------------------------------------------------------------------------------
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "status")
	private String status;

	
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PerformanceReference getPerformanceReference() {
		return performanceReference;
	}

	public void setPerformanceReference(PerformanceReference performanceReference) {
		this.performanceReference = performanceReference;
	}

	public CaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
