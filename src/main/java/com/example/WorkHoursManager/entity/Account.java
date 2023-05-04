package com.example.WorkHoursManager.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name ="account")
	private String account;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
	private EmployeeInfo employeeInfo;
	
	@Column(name ="password")
	private String password;

	
	//Getter & Setter
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	//外鍵employee_id實體EmployeeInfo
	
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
