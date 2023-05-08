package com.example.WorkHoursManager.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name ="account")
	private String accountId;

	@OneToOne(cascade = CascadeType.REFRESH , fetch = FetchType.EAGER)
	@JsonProperty("employeeId")
	@JsonBackReference
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id" ,
    		insertable = true, updatable = true)
	private EmployeeInfo employeeInfo;
	
	@Column(name ="password")
	private String password;

	
	//Getter & Setter
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String account) {
		this.accountId = account;
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
