package com.example.WorkHoursManager.entity;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employee_info")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class EmployeeInfo {
	
	@Id
	@JsonIdentityReference(alwaysAsId = true)
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "supervisor")
	private String supervisor;

//==============================================================
	//WorkHoursInfo的mployee_id外鍵關聯
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private List<WorkHoursInfo> workHoursInfo;
	
	//WorkDayInfo的employee_id外鍵關聯
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private List<WorkDayInfo> workDayInfo;
	
	//CaseInfo的employee_id外鍵關聯
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private List<CaseInfo> caseInfo;
	
	//Account的employee_id外鍵關聯
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private Account account;
	

	
	//Getter & Setter
	
	public void setWorkHoursInfo(List<WorkHoursInfo> workHoursInfo) {
		this.workHoursInfo = workHoursInfo;
	}

	public List<WorkDayInfo> getWorkDayInfo() {
		return workDayInfo;
	}

	public void setWorkDayInfo(List<WorkDayInfo> workDayInfo) {
		this.workDayInfo = workDayInfo;
	}

	public List<CaseInfo> getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(List<CaseInfo> caseInfo) {
		this.caseInfo = caseInfo;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public List<WorkHoursInfo> getWorkHoursInfo() {
		return workHoursInfo;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

}
