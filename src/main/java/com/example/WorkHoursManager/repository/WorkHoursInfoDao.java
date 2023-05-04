package com.example.WorkHoursManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.WorkHoursInfo;

@Repository
public interface WorkHoursInfoDao extends JpaRepository<WorkHoursInfo, Integer> {

	List<WorkHoursInfo> findByDate(String date);
	List<WorkHoursInfo> findByStartTimeAndEndTime(String startTimeString, String endTimeString);
}