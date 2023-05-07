package com.example.WorkHoursManager.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.CaseInfo;

@Repository
@Qualifier("caseInfoDao")
public interface CaseInfoDao extends JpaRepository<CaseInfo, String> {
	 CaseInfo getCaseInfoByCaseNo(String case_no);
}