package com.example.WorkHoursManager.Dto;

public class AccountDto{
	
	private String account;
	
	private String email;
	
	private String phone;

	private String employeeId;

	
	//Getter & Setter
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
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


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	//Constructor
	public AccountDto() {
	}

	public AccountDto(String account, String email, String phone, String employeeId) {
		this.account = account;
		this.email = email;
		this.phone = phone;
		this.employeeId = employeeId;
	}
	
}
