package com.example.WorkHoursManager.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.PerformanceReference;

@Repository
@Qualifier("performanceReferenceDao")
public interface PerformanceReferenceDao extends JpaRepository<PerformanceReference , Integer> {

	public PerformanceReference getPerformanceReferenceByCaseNo(String caseNo);

}
