package com.example.WorkHoursManager.services.workHoursService.imp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.WorkDayInfo;
import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.repository.CaseInfoDao;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.repository.WorkDayInfoDao;
import com.example.WorkHoursManager.repository.WorkHoursInfoDao;
import com.example.WorkHoursManager.services.workHoursService.WorkHoursService;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

@Service
public class WorkHoursServiceImp implements WorkHoursService {

	@Autowired
	private WorkHoursInfoDao workHoursInfoDao;
	@Autowired
	private EmployeeInfoDao employeeInfoDao;
	@Autowired
	private WorkDayInfoDao workDayInfoDao;
	@Autowired
	private CaseInfoDao caseInfoDao;
	
	//Read（読み取り）
	@Override
	public List<WorkHoursInfo> getAllInfos(){
		return workHoursInfoDao.findAll();
	}

	//Create（生成）
	@Override
	public WorkHoursInfoResp addinfo(WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfo newWorkHoursInfo=new WorkHoursInfo();
		String employeeId=workHoursInfoReq.getEmployeeId();
		String date=workHoursInfoReq.getDate();
		String caseNo=workHoursInfoReq.getCaseNo();
		
		//employeeInfo這些為null不知為何
		EmployeeInfo employeeInfo=employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
		WorkDayInfo workDayInfo=workDayInfoDao.getWorkDayInfoByDate(date);
		CaseInfo caseInfo=caseInfoDao.getCaseInfoByCaseNo(caseNo);
		
		//判斷有無資料
			if(!StringUtils.hasText(caseNo)){
				return new WorkHoursInfoResp("案件號碼不可為空",false);
			}
			if(!StringUtils.hasText(date)){
				return new WorkHoursInfoResp("日期不可為空",false);
			}
			if(!StringUtils.hasText(workHoursInfoReq.getDetail())){
				return new WorkHoursInfoResp("工作內容不可為空",false);
			}
			if(!StringUtils.hasText(workHoursInfoReq.getEndTime())){
				return new WorkHoursInfoResp("工作結束時間不可為空",false);
			}
			if(!StringUtils.hasText(workHoursInfoReq.getStartTime())){
				return new WorkHoursInfoResp("工作開始時間不可為空",false);
			}
			if(!StringUtils.hasText(workHoursInfoReq.getModel())){
				return new WorkHoursInfoResp("機型不可為空",false);
			}
			if(!StringUtils.hasText(workHoursInfoReq.getStatus())){
				return new WorkHoursInfoResp("狀態不可為空",false);
			}
			if(employeeId==null) {
				return new WorkHoursInfoResp("此員工ID不存在",false);
			}
			
			newWorkHoursInfo.setEmployeeInfo(employeeInfo);
			newWorkHoursInfo.setCaseInfo(caseInfo);
			newWorkHoursInfo.setWorkDayInfo(workDayInfo);
			newWorkHoursInfo.setDetail(workHoursInfoReq.getDetail());
			newWorkHoursInfo.setEndTime(workHoursInfoReq.getEndTime());
			newWorkHoursInfo.setStartTime(workHoursInfoReq.getStartTime());
			newWorkHoursInfo.setModel(workHoursInfoReq.getModel());
			newWorkHoursInfo.setStatus(workHoursInfoReq.getStatus());	
			
			//判斷是否為同一日期及是否為最新的日期
			List<WorkHoursInfo> infosOnSameDate = workHoursInfoDao.findAll();
			for (WorkHoursInfo info : infosOnSameDate) {
				//判斷是否為最新的日期，否則不存
//				LocalDate newDate=LocalDate.parse(date);
//				LocalDate olddate=LocalDate.parse(workDayInfo.getDate());
//				if(newDate.equals(olddate)) {
					//判斷是否為同一日期，若為同一日期則判定有無時間相衝
					LocalTime newStartTime = LocalTime.parse(workHoursInfoReq.getStartTime());
				    LocalTime newEndTime = LocalTime.parse(workHoursInfoReq.getEndTime());
				    LocalTime startTime = LocalTime.parse(info.getStartTime());
				    LocalTime endTime = LocalTime.parse(info.getEndTime());
				    if (newStartTime.isBefore(endTime) && newEndTime.isAfter(startTime)
				    	|| newEndTime.isBefore(endTime)&&newStartTime.isAfter(startTime)){
				        return new WorkHoursInfoResp("新增失敗，時間相衝", false);
				    }
				    if(newStartTime.isAfter(newEndTime)||newStartTime.equals(newEndTime)) {
				    	return new WorkHoursInfoResp("新增失敗，開始時間不可晚於結束時間", false);
				    }
				}
//			else if(!newDate.isAfter(olddate)||!newDate.equals(olddate)) {
//					return new WorkHoursInfoResp("新增失敗，非為最新日期", false);
//				}		    
//			}			
			workHoursInfoDao.save(newWorkHoursInfo);	
			return new WorkHoursInfoResp("新增成功",true);
	}


	//Delete（削除）
	@Override
	public WorkHoursInfoResp deleteInfo(int workInfoId) {
	    Optional<WorkHoursInfo> optionalInfo = workHoursInfoDao.findById(workInfoId);
	    if (optionalInfo.isPresent()) {
	        workHoursInfoDao.deleteById(workInfoId);
	        return new WorkHoursInfoResp("工時資訊已成功刪除", false);
	    } else {
	        return new WorkHoursInfoResp("該ID對應的工時資訊不存在，刪除失敗", false);
	    }
	}
	
	//Update(更新)
	@Override
	public WorkHoursInfoResp updateInfo(int workInfoId, WorkHoursInfoReq workHoursInfoReq) {
	    WorkHoursInfo workHoursInfo = workHoursInfoDao.findById(workInfoId).orElse(null);
	    if (workHoursInfo == null) {
	        return new WorkHoursInfoResp("找不到指定的工時紀錄", false);
	    }
	    
	    // 更新工時紀錄的屬性值
	    String employeeId = workHoursInfoReq.getEmployeeId();
	    String date = workHoursInfoReq.getDate();
	    String caseNo = workHoursInfoReq.getCaseNo();
	    EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
	    WorkDayInfo workDayInfo = workDayInfoDao.getWorkDayInfoByDate(date);
	    CaseInfo caseInfo = caseInfoDao.getCaseInfoByCaseNo(caseNo);

	    // 判斷有無資料
	    if (!StringUtils.hasText(workHoursInfoReq.getCaseNo())) {
	        return new WorkHoursInfoResp("案件號碼不可為空", false);
	    }

	    workHoursInfo.setCaseInfo(caseInfo);
	    workHoursInfo.setWorkDayInfo(workDayInfo);
	    workHoursInfo.setDetail(workHoursInfoReq.getDetail());
	    workHoursInfo.setEndTime(workHoursInfoReq.getEndTime());
	    workHoursInfo.setStartTime(workHoursInfoReq.getStartTime());
	    workHoursInfo.setModel(workHoursInfoReq.getModel());
	    workHoursInfo.setStatus(workHoursInfoReq.getStatus());
	    workHoursInfo.setEmployeeInfo(employeeInfo);

	    // 判斷是否為同一日期，若為同一日期則判定有無時間相衝
	    List<WorkHoursInfo> infosOnSameDate = workHoursInfoDao.findAll();
	    for (WorkHoursInfo info : infosOnSameDate) {
	        LocalTime newStartTime = LocalTime.parse(workHoursInfoReq.getStartTime());
	        LocalTime newEndTime = LocalTime.parse(workHoursInfoReq.getEndTime());
	        LocalTime startTime = LocalTime.parse(info.getStartTime());
	        LocalTime endTime = LocalTime.parse(info.getEndTime());
	        if (info.getWorkInfoId()!= workInfoId && (newStartTime.isBefore(endTime) && newEndTime.isAfter(startTime)
	                || newEndTime.isBefore(endTime) && newStartTime.isAfter(startTime))) {
	            return new WorkHoursInfoResp("更新失敗，時間相衝", false);
	        }
	        if (newStartTime.isAfter(newEndTime) || newStartTime.equals(newEndTime)) {
	            return new WorkHoursInfoResp("更新失敗，開始時間不可晚於結束時間", false);
	        }
	    }
	    workHoursInfoDao.save(workHoursInfo);
	    return new WorkHoursInfoResp("更新成功", true);
	}

}
