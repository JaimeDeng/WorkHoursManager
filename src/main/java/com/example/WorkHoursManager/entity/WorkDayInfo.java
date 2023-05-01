package com.example.WorkHoursManager.entity;

import javax.persistence.*;

@Entity
@Table(name = "work_day_info")
public class WorkDayInfo {

	@Id
	@Column(name = "date")
	private String date;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "info")
	private String info;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "approved")
	private boolean approved;


	//Getter & Setter
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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
	
}
