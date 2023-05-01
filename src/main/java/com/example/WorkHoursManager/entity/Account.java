package com.example.WorkHoursManager.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name ="account")
	private String account;

	@Column(name ="employee_id")
	private String employeeId;
	
	@Column(name ="password")
	private String password;

	
	//Getter & Setter
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
