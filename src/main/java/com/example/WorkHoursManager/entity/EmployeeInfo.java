package com.example.WorkHoursManager.entity;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "employee_info")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class EmployeeInfo {
	
	@Id
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
	

//------------------------------------------------supervisor----------------------------------------------
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonProperty("supervisor")
	@JsonInclude(JsonInclude.Include.ALWAYS)	//即使是NULL也顯示在RESP
    @JoinColumn(name = "supervisor", referencedColumnName = "employee_id" , nullable=true , 
    		insertable = true, updatable = true)
    private EmployeeInfo supervisor;
//----------------------------------------------------------------------------------------------------------

//==============================================================
	//WorkHoursInfo的employee_id外鍵關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private List<WorkHoursInfo> workHoursInfo;
	
	//WorkDayInfo的employee_id外鍵關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private List<WorkDayInfo> workDayInfo;
	
	//CaseInfo的employee_id外鍵關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private List<CaseInfo> caseInfo;
	
	//Account的employee_id外鍵關聯
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeInfo")
	private Account account;
	
	//EmployeeInfo的employee_id自關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supervisor")
	private List<EmployeeInfo> subordinates;

	
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

	public EmployeeInfo getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(EmployeeInfo supervisor) {
		this.supervisor = supervisor;
	}

}
