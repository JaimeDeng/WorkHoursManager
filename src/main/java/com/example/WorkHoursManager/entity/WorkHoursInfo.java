package com.example.WorkHoursManager.entity;

import javax.persistence.*;


@Entity
@Table(name = "work_hours_info")
public class WorkHoursInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_info_id")
	private Integer workInfoId;

//---------------------------------------------employeeId------------------------------------------------
	//設置EmployeeInfo為employee_id外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id", 
	insertable = false, updatable = false)
	private EmployeeInfo employeeInfo;
//----------------------------------------------------------------------------------------------------------
	
//-------------------------------------------------date-------------------------------------------------
	//設置WorkDayInfo為date外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "date", referencedColumnName = "date" , nullable=true , 
		insertable = false, updatable = false)
	private WorkDayInfo workDayInfo;
//-------------------------------------------------------------------------------------------------------
	
	@Column(name = "model")
	private String model;

//---------------------------------------------caseNo------------------------------------------------
	//設置PerformanceReference為case_no外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_no", referencedColumnName = "case_no" , nullable=true ,
		insertable = false, updatable = false)
	private PerformanceReference performanceReference;
	
	//設置CaseInfo為case_no外鍵關聯對象
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
	public PerformanceReference getErformanceReference() {
		return performanceReference;
	}

	public void setErformanceReference(PerformanceReference erformanceReference) {
		this.performanceReference = erformanceReference;
	}
	
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
