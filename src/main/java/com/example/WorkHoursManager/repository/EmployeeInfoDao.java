package com.example.WorkHoursManager.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.EmployeeInfo;

@Repository
@Qualifier("employeeInfoDao")
public interface EmployeeInfoDao extends JpaRepository<EmployeeInfo, String> {
	
	EmployeeInfo getEmployeeInfoByEmployeeId(String employeeId);	

}