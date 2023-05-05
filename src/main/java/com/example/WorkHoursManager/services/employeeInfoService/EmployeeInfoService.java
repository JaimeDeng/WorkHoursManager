package com.example.WorkHoursManager.services.employeeInfoService;

import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoReq;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoResp;

public interface EmployeeInfoService {
	
	public EmployeeInfoResp setEmployeeInfo(EmployeeInfoReq employeeInfoReq);
	public EmployeeInfoResp getAllEmployeeInfo(EmployeeInfoReq employeeInfoReq);
	public EmployeeInfoResp getEmployeeInfoById(EmployeeInfoReq employeeInfoReq);
	public EmployeeInfoResp deleteEmployeeInfo(EmployeeInfoReq employeeInfoReq);
	public EmployeeInfoResp editEmployeeInfo(EmployeeInfoReq employeeInfoReq);

}