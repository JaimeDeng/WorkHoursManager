package com.example.WorkHoursManager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "work_day_info")
public class WorkDayInfo{

	@Id
	@Column(name ="date")
	private String date;

//---------------------------------------------employeeId------------------------------------------------
	//設置EmployeeInfo為employee_id外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id" , 	
		insertable = false, updatable = false)
	private EmployeeInfo employeeInfo;
//-----------------------------------------------------------------------------------------------------------
	
	@Column(name = "info")
	private String info;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "approved")
	private boolean approved;
	
//---------------------------------------------reviewer------------------------------------------------
	//閮剔蔭EmployeeInfo�reviewer憭��撠情
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id" , 
		insertable = false, updatable = false)
	private EmployeeInfo reviewer;
//-----------------------------------------------------------------------------------------------------------

//==============================================================
	//WorkHoursInfo��ate憭��
	@OneToMany(mappedBy = "workDayInfo")
    private List<WorkHoursInfo> workHoursInfo;


	//Getter & Setter
	
	//憭date撖阡�orkHoursInfo (����誑List�摮�)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<WorkHoursInfo> getWorkHoursInfo() {
		return workHoursInfo;
	}

	public void setWorkHoursInfo(List<WorkHoursInfo> workHoursInfo) {
		this.workHoursInfo = workHoursInfo;
	}

	//憭employee_id撖阡�mployeeInfo
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
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

	public EmployeeInfo getReviewer() {
		return reviewer;
	}

	public void setReviewer(EmployeeInfo reviewer) {
		this.reviewer = reviewer;
	}
	
}
