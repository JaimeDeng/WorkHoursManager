package com.example.WorkHoursManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.config.WorkDayInfoIdConfig;
import com.example.WorkHoursManager.entity.*;

@Repository
@Qualifier("workDayInfoDao")
public interface WorkDayInfoDao extends JpaRepository<WorkDayInfo, String> {
	
	List<WorkDayInfo> getWorkDayInfoByDate(String date);
	List<WorkDayInfo> getWorkDayInfoByEmployeeInfo(EmployeeInfo employeeInfo);
	
}