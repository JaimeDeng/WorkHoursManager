package com.example.WorkHoursManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.entity.EmployeeInfo;

@Repository
@Qualifier("caseInfoDao")
public interface CaseInfoDao extends JpaRepository<CaseInfo, Integer> {
	
	 List<CaseInfo> getCaseInfoByCaseNo(String caseNo);
	 List<CaseInfo> getCaseInfoByEmployeeInfo(EmployeeInfo employeeInfo);
	 
}