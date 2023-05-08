package com.example.WorkHoursManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.WorkHoursInfo;

@Repository
@Qualifier("workHoursInfoDao")
public interface WorkHoursInfoDao extends JpaRepository<WorkHoursInfo, Integer> {
	
	List<WorkHoursInfo>getWorkHoursInfoByEmployeeInfo(EmployeeInfo employeeInfo);
	
}