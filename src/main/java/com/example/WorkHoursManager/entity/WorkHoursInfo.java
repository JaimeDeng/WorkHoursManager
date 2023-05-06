package com.example.WorkHoursManager.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "work_hours_info")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class WorkHoursInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_info_id")
	private Integer workInfoId;

//---------------------------------------------employeeId------------------------------------------------
	//閮剔蔭EmployeeInfo�employee_id憭��撠情
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
	//insertable = false, updatable = false)
	private EmployeeInfo employeeInfo;
//----------------------------------------------------------------------------------------------------------
	
//-------------------------------------------------date-------------------------------------------------
	//閮剔蔭WorkDayInfo�date憭��撠情
//	@JsonProperty("date")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "date", referencedColumnName = "date")
		//insertable = false, updatable = false)
	private WorkDayInfo workDayInfo;
//-------------------------------------------------------------------------------------------------------
	
	@Column(name = "model")
	private String model;

//---------------------------------------------caseNo------------------------------------------------
	//閮剔蔭PerformanceReference�case_no憭��撠情
//	@JsonProperty("caseNo")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_no", referencedColumnName = "case_no" , nullable=true )
		//insertable = false, updatable = false)
	private PerformanceReference performanceReference;
	
//	@JsonProperty("caseNo")
	//閮剔蔭CaseInfo�case_no憭��撠情
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_no", referencedColumnName = "case_no" , nullable=true ,
		insertable = false, updatable = false)
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
	
	public WorkDayInfo getWorkDayInfo() {
		return workDayInfo;
	}

	public void setWorkDayInfo(WorkDayInfo workDayInfo) {
		this.workDayInfo = workDayInfo;
	}
	
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
}
