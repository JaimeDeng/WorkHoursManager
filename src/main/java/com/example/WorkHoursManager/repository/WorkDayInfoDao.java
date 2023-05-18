package com.example.WorkHoursManager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.Dto.WorkDayInfoDto;
import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.WorkDayInfo;

@Repository
@Qualifier("workDayInfoDao")
public interface WorkDayInfoDao extends JpaRepository<WorkDayInfo, String> {

	List<WorkDayInfo> getWorkDayInfoByDate(String date);
	List<WorkDayInfo> getWorkDayInfoByEmployeeInfo(EmployeeInfo employeeInfo);
	
	//獲取指定主管ID的未審核日工時表
	@Transactional
	@Query("SELECT new com.example.WorkHoursManager.Dto.WorkDayInfoDto(w.workInfoId, w.date, "
			+ "w.employeeInfo.employeeId, w.status, w.workingHours, w.approved, w.employeeInfo.supervisor) " +
	        "FROM WorkDayInfo w JOIN w.employeeInfo e " +
	        "WHERE w.approved = '0' AND w.employeeInfo.supervisor = :supervisorReq")
	List<WorkDayInfoDto> getPendingApprovalWorkDayInfoBySupervisorId(@Param(value = "supervisorReq")String supervisorReq);
	
	//獲取指定主管ID的下屬日工時表
		@Transactional
		@Query("SELECT new com.example.WorkHoursManager.Dto.WorkDayInfoDto(w.workInfoId, w.date, "
				+ "w.employeeInfo.employeeId, w.status, w.workingHours, w.approved, w.employeeInfo.supervisor) " +
		        "FROM WorkDayInfo w JOIN w.employeeInfo e " +
		        "WHERE w.employeeInfo.supervisor = :supervisorReq")
		List<WorkDayInfoDto> getWorkDayInfoBySupervisorId(@Param(value = "supervisorReq")String supervisorReq);
}