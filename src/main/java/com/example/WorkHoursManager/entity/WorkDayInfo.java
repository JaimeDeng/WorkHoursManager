package com.example.WorkHoursManager.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "work_day_info")
public class WorkDayInfo{

	@Id
	@Column(name ="date")
	private String date;

//---------------------------------------------employeeId------------------------------------------------
	//設置EmployeeInfo為employee_id外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id")
	private EmployeeInfo employeeInfo;
//-----------------------------------------------------------------------------------------------------------
	
	@Column(name = "info")
	private String info;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "approved")
	private boolean approved;
	
//---------------------------------------------reviewer------------------------------------------------
	//設置EmployeeInfo為reviewer外鍵關聯對象
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id", referencedColumnName ="employee_id" , insertable = false, updatable = false)
	private EmployeeInfo reviewer;
//-----------------------------------------------------------------------------------------------------------

//==============================================================
	//WorkHoursInfo的date外鍵關聯
	@OneToMany(mappedBy = "workDayInfo")
    private List<WorkHoursInfo> workHoursInfo;


	//Getter & Setter
	
	//外鍵date實體WorkHoursInfo (有多個以List儲存)
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

	//外鍵employee_id實體EmployeeInfo
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
	
}
