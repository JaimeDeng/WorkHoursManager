package com.example.WorkHoursManager.Dto;

public class WorkDayInfoDto{
	
	private Integer workInfoId;
	
	private String date;

	private String employeeId;
	
	private String status;
	
	private float workingHours;
	
	private boolean approved;
	
	private String supervisorId;

	
	//Getter & Setter
	public Integer getWorkInfoId() {
		return workInfoId;
	}

	public void setWorkInfoId(Integer workInfoId) {
		this.workInfoId = workInfoId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(float workingHours) {
		this.workingHours = workingHours;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	//Constructor
	public WorkDayInfoDto() {
	}
	
	public WorkDayInfoDto(Integer workInfoId, String date, String employeeId, String status, float workingHours,
			boolean approved, String supervisorId) {
		super();
		this.workInfoId = workInfoId;
		this.date = date;
		this.employeeId = employeeId;
		this.status = status;
		this.workingHours = workingHours;
		this.approved = approved;
		this.supervisorId = supervisorId;
	}
	
}
