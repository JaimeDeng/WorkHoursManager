package com.example.WorkHoursManager.constants;

public enum RtnCode {
	
	ADD_SUCCESSFUL("200" , "資料新增成功"),
	GET_SUCCESSFUL("200" , "資料獲取成功"),
	EDIT_SUCCESSFUL("200" , "資料修改成功"),
	DELETE_SUCCESSFUL("200" , "資料刪除成功"),
	//Account
	ACCOUNT_CANNOT_EMPTY("400" , "帳號不得為空"),
	ACCOUNT_NOT_EXISTS("400" , "此帳號不存在"),
	ACCOUNT_CANNOT_SAME_AS_EMPLOYEEID("400" , "帳號不得為員工ID"),
	ACCOUNT_HAS_BEEN_USED("400" , "此帳號已被使用"),
	EMPLOYEE_HAS_ACCOUNT("400" ,"此員工已有帳號"),
	PWD_CANNOT_EMPTY("400" , "密碼不得為空"),
	PWD_CANNOT_SAME("400" , "新密碼不得與舊密碼相同"),
	//EmployeeID
	EMPLOYEEID_CANNOT_EMPTY("400" , "員工ID不得為空"),
	EMPLOYEEID_NOT_EXISTS("400" , "無此ID員工存在"),
	NOT_FOUND("404" , "not found");
	
	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private RtnCode(String message) {
		this.message = message;
	}
	
	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
