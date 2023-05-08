package com.example.WorkHoursManager.services.workHoursService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.PerformanceReference;
import com.example.WorkHoursManager.entity.WorkDayInfo;
import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.repository.CaseInfoDao;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.repository.PerformanceReferenceDao;
import com.example.WorkHoursManager.repository.WorkDayInfoDao;
import com.example.WorkHoursManager.repository.WorkHoursInfoDao;
import com.example.WorkHoursManager.services.accountService.AccountService;
import com.example.WorkHoursManager.services.caseInfoService.CaseInfoService;
import com.example.WorkHoursManager.services.employeeInfoService.EmployeeInfoService;
import com.example.WorkHoursManager.services.performanceReferenceService.PerformanceReferenceService;
import com.example.WorkHoursManager.services.workDayService.WorkDayInfoService;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceReq;
import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

@Service
@Qualifier("workHoursService")
public class WorkHoursServiceImp implements WorkHoursService {
	
	//-----------------------Constructor Injection---------------------------
	//Service
	private final WorkDayInfoService workDayInfoService;
	private final CaseInfoService caseInfoService;
	private final PerformanceReferenceService performanceReferenceService;
	//Dao
	private final WorkHoursInfoDao workHoursInfoDao;
	private final EmployeeInfoDao employeeInfoDao;
	private final WorkDayInfoDao workDayInfoDao;
	private final CaseInfoDao caseInfoDao;
	private final PerformanceReferenceDao performanceReferenceDao;
	
	@Autowired
	public WorkHoursServiceImp(
			@Qualifier("workDayInfoService")WorkDayInfoService workDayInfoService,
			@Qualifier("caseInfoService")CaseInfoService caseInfoService,
			@Qualifier("performanceReferenceService")PerformanceReferenceService performanceReferenceService,
			@Qualifier("workHoursInfoDao")WorkHoursInfoDao workHoursInfoDao,
			@Qualifier("employeeInfoDao")EmployeeInfoDao employeeInfoDao,
			@Qualifier("workDayInfoDao")WorkDayInfoDao workDayInfoDao,
			@Qualifier("caseInfoDao")CaseInfoDao caseInfoDao,
			@Qualifier("performanceReferenceDao")PerformanceReferenceDao performanceReferenceDao) {
				this.workDayInfoService = workDayInfoService;
				this.caseInfoService = caseInfoService;
				this.performanceReferenceService = performanceReferenceService;
				this.workHoursInfoDao = workHoursInfoDao;
				this.employeeInfoDao = employeeInfoDao;
				this.workDayInfoDao = workDayInfoDao;
				this.caseInfoDao = caseInfoDao;
				this.performanceReferenceDao = performanceReferenceDao;
	}
	//-----------------------Constructor Injection---------------------------

	//讀取全部工時表
	@Override
	public WorkHoursInfoResp getAllWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq){
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		return workHoursInfoResp;
	}
	

	//Create（生成）
	@Override
	public WorkHoursInfoResp setWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfo newWorkHoursInfo = new WorkHoursInfo();
		String employeeIdReq = workHoursInfoReq.getEmployeeId();
		String dateReq = workHoursInfoReq.getDate();
		String caseNoReq = workHoursInfoReq.getCaseNo();
		String detailReq = workHoursInfoReq.getDetail();
		String endTimeReq = workHoursInfoReq.getEndTime();
		String startTimeReq = workHoursInfoReq.getStartTime();
		String modelReq = workHoursInfoReq.getModel();
		String statusReq = workHoursInfoReq.getStatus();
		
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeIdReq);
		
		//判斷有無資料
		if(!StringUtils.hasText(employeeIdReq)) {
			return new WorkHoursInfoResp("請輸入員工ID",false);
		}
		if(employeeInfo == null) {
			return new WorkHoursInfoResp("此員工ID不存在",false);
		}
		if(!StringUtils.hasText(caseNoReq)){
			return new WorkHoursInfoResp("案件號碼不可為空",false);
		}
		if(!StringUtils.hasText(dateReq)){
			return new WorkHoursInfoResp("日期不可為空",false);
		}
		
		//檢驗日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);	//設定日期檢驗模式為嚴格 , 不可有寬鬆模式容忍2月30這樣的狀況
        try {
            Date date = dateFormat.parse(dateReq);
        } catch (java.text.ParseException e) {	//字串轉換Date格式失敗視為無效日期
        	return new WorkHoursInfoResp("日期格式錯誤",false);
        }
        
		if(!StringUtils.hasText(detailReq)){
			return new WorkHoursInfoResp("工作內容不可為空",false);
		}
		if(!StringUtils.hasText(endTimeReq)){
			return new WorkHoursInfoResp("工作結束時間不可為空",false);
		}
		if(!StringUtils.hasText(startTimeReq)){
			return new WorkHoursInfoResp("工作開始時間不可為空",false);
		}
		if(!StringUtils.hasText(modelReq)){
			return new WorkHoursInfoResp("機型不可為空",false);
		}
		if(!StringUtils.hasText(statusReq)){
			return new WorkHoursInfoResp("狀態不可為空",false);
		}
		
		//取得開始與結束時間的分鐘數
		LocalTime startTime = LocalTime.parse(startTimeReq);
		//把小時轉成分鐘加上分鐘數
		int startTimeMinutes = startTime.getHour() * 60 + startTime.getMinute();
		
		LocalTime endTime = LocalTime.parse(endTimeReq);
		//把小時轉成分鐘加上分鐘數
		int endTimeMinutes = endTime.getHour() * 60 + endTime.getMinute();
		
		if(startTimeMinutes > endTimeMinutes) {
			return new WorkHoursInfoResp("開始時間不得在結束時間之後",false);
		}
		
		newWorkHoursInfo.setEmployeeInfo(employeeInfo);
		newWorkHoursInfo.setCaseNo(caseNoReq);
		newWorkHoursInfo.setDate(dateReq);
		newWorkHoursInfo.setDetail(detailReq);
		newWorkHoursInfo.setStartTime(startTimeReq);
		newWorkHoursInfo.setEndTime(endTimeReq);
		newWorkHoursInfo.setModel(modelReq);
		newWorkHoursInfo.setStatus(statusReq);
		
		//判斷是否為同一日期
		List<WorkHoursInfo> workHoursInfoList = workHoursInfoDao.getWorkHoursInfoByEmployeeInfo(employeeInfo);
		//將同一個日期的workHoursInfo抓出來為一個新的List
		List<WorkHoursInfo> sameDateWorkHoursInfoList = new ArrayList<>();
		for (WorkHoursInfo info : workHoursInfoList) {
			if(info.getDate().equals(dateReq)) {
				sameDateWorkHoursInfoList.add(info);
			}
		}
		//沒有相同日期的資料的話直接存
		if(sameDateWorkHoursInfoList.size() == 0) {
			workHoursInfoDao.save(newWorkHoursInfo);
			
			//連動新增WorkDayInfo
			WorkDayInfoReq workDayInfoReq = new WorkDayInfoReq();
			workDayInfoReq.setDate(dateReq);
			workDayInfoReq.setEmployeeId(employeeIdReq);
			workDayInfoReq.setStatus(statusReq);
			//計算總時數
			float durationMinutes = endTimeMinutes - startTimeMinutes;
			float durationHours = durationMinutes / 60;
			workDayInfoReq.setWorkingHours(durationHours);
			workDayInfoService.setWorkDayInfo(workDayInfoReq);
			
			//連動新增PR(先看沒有此CaseNo的PR存在才新增)
			if(performanceReferenceDao.getPerformanceReferenceByCaseNo(caseNoReq) == null){
				PerformanceReferenceReq performanceReferenceReq = new PerformanceReferenceReq();
				performanceReferenceReq.setCaseNo(caseNoReq);
				performanceReferenceReq.setModel(modelReq);
				performanceReferenceService.setPerformanceReference(performanceReferenceReq);
			}
			return new WorkHoursInfoResp("新增成功",true);
			
		}else {
			
			//有相同日期的表單需要處理
			float existsWorkingHours = 0;
			boolean attendance = false;
			for(WorkHoursInfo info : sameDateWorkHoursInfoList) {
				
				//檢查之前的表單是否有狀態為出勤的
				if(info.getStatus().equals("出勤")) {
					attendance = true;
				}
				
				//取得舊表單開始與結束時間的分鐘數
				String sameDateInfoStartTimeStr = info.getStartTime();
				LocalTime sameDateInfoStartTime = LocalTime.parse(sameDateInfoStartTimeStr);
				int sameDateInfoStartTimeMinutes = sameDateInfoStartTime.getHour() * 60 + sameDateInfoStartTime.getMinute();
				
				String sameDateInfoEndTimeStr = info.getEndTime();
				LocalTime sameDateInfoEndTime = LocalTime.parse(sameDateInfoEndTimeStr);
				int sameDateInfoEndTimeMinutes = sameDateInfoEndTime.getHour() * 60 + sameDateInfoEndTime.getMinute();
				
				//計算總時數
				float durationMinutes = sameDateInfoEndTimeMinutes - sameDateInfoStartTimeMinutes;
				float durationHours = durationMinutes / 60;
				existsWorkingHours += durationHours;
				
				//檢查時間有無重疊
				if(startTimeMinutes > sameDateInfoStartTimeMinutes
						&& startTimeMinutes < sameDateInfoEndTimeMinutes
						||	endTimeMinutes > sameDateInfoStartTimeMinutes
						&& endTimeMinutes < sameDateInfoEndTimeMinutes
						||startTimeMinutes == sameDateInfoStartTimeMinutes
						&&endTimeMinutes == sameDateInfoEndTimeMinutes
						||startTimeMinutes < sameDateInfoStartTimeMinutes
						&&endTimeMinutes == sameDateInfoEndTimeMinutes
						||startTimeMinutes == sameDateInfoStartTimeMinutes
						&&endTimeMinutes > sameDateInfoEndTimeMinutes) {
					return new WorkHoursInfoResp("新增失敗 , 新增表單與既有表單時間重疊",false);
				}
			}
			
			//計算新表單時數
			float durationMinutes = endTimeMinutes - startTimeMinutes;
			float durationHours = durationMinutes / 60;
			//連動新增WorkDayInfo
			WorkDayInfoReq workDayInfoReq = new WorkDayInfoReq();
			//取此日期日工時表物件
			WorkDayInfo thisDateWorkDayInfo = new WorkDayInfo();
			for(WorkDayInfo element : workDayInfoDao.getWorkDayInfoByEmployeeInfo(employeeInfo)){
				if(element.getDate().equals(dateReq)){
					 thisDateWorkDayInfo = element;
				}
			}
			workDayInfoReq.setDate(dateReq);
			workDayInfoReq.setEmployeeId(employeeIdReq);
			//前面沒有狀態為出勤的時候才會複寫狀態
			if(attendance == false) {
				workDayInfoReq.setStatus(statusReq);
			}else {
				//維持原本的狀態欄位
				workDayInfoReq.setStatus(thisDateWorkDayInfo.getStatus());
			}
			//與舊表單時數相加後存入
			workDayInfoReq.setWorkingHours(existsWorkingHours + durationHours);
			if(thisDateWorkDayInfo.getWorkInfoId() == null) {
				return new WorkHoursInfoResp("新增失敗 , 舊表單ID = NULL",false);
			}
			workDayInfoReq.setWorkInfoId(thisDateWorkDayInfo.getWorkInfoId());
			System.out.println(workDayInfoReq.getDate() + " " + workDayInfoReq.getEmployeeId() +
					" " + workDayInfoReq.getStatus() + " " + workDayInfoReq.getWorkingHours() + 
					" " + workDayInfoReq.getWorkInfoId());
			workDayInfoService.editWorkDayInfo(workDayInfoReq);
		}
		
		workHoursInfoDao.save(newWorkHoursInfo);
		
		//連動新增PR(先看沒有此CaseNo的PR存在才新增)
		if(performanceReferenceDao.getPerformanceReferenceByCaseNo(caseNoReq) == null){
			PerformanceReferenceReq performanceReferenceReq = new PerformanceReferenceReq();
			performanceReferenceReq.setCaseNo(caseNoReq);
			performanceReferenceReq.setModel(modelReq);
			performanceReferenceService.setPerformanceReference(performanceReferenceReq);
		}
		return new WorkHoursInfoResp("新增成功",true);
		
}


	//Delete
	@Override
	public WorkHoursInfoResp deleteWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq) {
		//Req輸入工單號
		Integer workInfoId = workHoursInfoReq.getWorkInfoId();
		//以工單ID取資料
		Optional<WorkHoursInfo> workHoursInfo = workHoursInfoDao.findById(workInfoId);
		if(workHoursInfo.isEmpty()) {
			return new WorkHoursInfoResp("無此工單號的工時表存在", false);
		}
		//獲取此單號的員工ID及日期
		String employeeId = workHoursInfo.get().getEmployeeInfo().getEmployeeId();
		String date = workHoursInfo.get().getDate();
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
		
		//獲取欲刪除的workHoursInfo工作時數
		String startTimeStr = workHoursInfo.get().getStartTime();
		LocalTime startTime = LocalTime.parse(startTimeStr);
		int startTimeMinutes = startTime.getHour() * 60 + startTime.getMinute();
		
		String endTimeStr = workHoursInfo.get().getEndTime();
		LocalTime endTime = LocalTime.parse(endTimeStr);
		int endTimeMinutes = endTime.getHour() * 60 + endTime.getMinute();
		
		float durationMinutes = endTimeMinutes - startTimeMinutes;
		float durationHours = durationMinutes / 60;

		//刪除此工時表
		workHoursInfoDao.delete(workHoursInfo.get());
		if(workHoursInfoDao.existsById(workInfoId)){
			return new WorkHoursInfoResp("刪除失敗", false);
		}
		
		//檢查剩餘工時表有無狀態為出勤
		boolean attendance = false;
		String remainStatus = null;
		List<WorkHoursInfo>workHoursInfoList = workHoursInfoDao.getWorkHoursInfoByEmployeeInfo(employeeInfo);
		List<WorkHoursInfo>sameDateWorkHoursInfoList = new ArrayList<>();
		//抓出同日的工時資料
	    for(WorkHoursInfo existsWorkHoursInfo : workHoursInfoList) {
	    	if(existsWorkHoursInfo.getDate().equals(date)){
	    		sameDateWorkHoursInfoList.add(existsWorkHoursInfo);
	    	}
	    }
		//連動更新WorkDayInfo
		//如果當日的工時表完全被刪除
		if(sameDateWorkHoursInfoList.size() == 0) {
			//直接刪掉當天日工時表
			WorkDayInfoReq workDayInfoReq = new WorkDayInfoReq();
			workDayInfoReq.setEmployeeId(employeeId);
			workDayInfoReq.setDate(date);
			workDayInfoService.deleteWorkDayInfo(workDayInfoReq);
			return new WorkHoursInfoResp("工時表刪除成功", true);
			
		}else {
			
			//刪除後還有其他當日的工時表
			//檢查剩餘表單是否有狀態為出勤
			for(WorkHoursInfo sameDateWorkHoursInfo : sameDateWorkHoursInfoList) {
				if(sameDateWorkHoursInfo.getStatus().equals("出勤")) {
	    			attendance = true;
	    		}else {
	    			remainStatus = sameDateWorkHoursInfo.getStatus();
	    		}
			}
    	}
		
		WorkDayInfoReq workDayInfoReq = new WorkDayInfoReq();
		List<WorkDayInfo> workDayInfoList = workDayInfoDao.getWorkDayInfoByEmployeeInfo(employeeInfo);
		for(WorkDayInfo workDayInfo : workDayInfoList) {
			if(workDayInfo.getDate().equals(date)) {
				workDayInfoReq.setDate(workDayInfo.getDate());
				workDayInfoReq.setEmployeeId(employeeId);
				workDayInfoReq.setWorkInfoId(workDayInfo.getWorkInfoId());
				workDayInfoReq.setWorkingHours(workDayInfo.getWorkingHours() - durationHours);
				//如果還有出勤的表單存在就保持狀態為出勤
				System.out.println(attendance);
				if(attendance == true) {
					workDayInfoReq.setStatus("出勤");
				}else {
					//沒有則為剩餘表單的狀態(ex.請假)
					System.out.println(remainStatus);
					workDayInfoReq.setStatus(remainStatus);
				}
				workDayInfoService.editWorkDayInfo(workDayInfoReq);
			}
		}
		return new WorkHoursInfoResp("工時表刪除成功", true);
	}
	
	
	
	
	
	//Update(更新)
	@Override
	public WorkHoursInfoResp editWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq) {
	    WorkHoursInfo workHoursInfo = workHoursInfoDao.findById(workHoursInfoReq.getWorkInfoId()).orElse(null);
	    if (workHoursInfo == null) {
	        return new WorkHoursInfoResp("找不到指定的工時紀錄", false);
	    }
	    
	    // 更新工時紀錄的屬性值
	    String employeeId = workHoursInfoReq.getEmployeeId();
	    String date = workHoursInfoReq.getDate();
	    String caseNo = workHoursInfoReq.getCaseNo();
	    EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
	    CaseInfo caseInfo = caseInfoDao.getCaseInfoByCaseNo(caseNo);

	    // 判斷有無資料
	    if (!StringUtils.hasText(workHoursInfoReq.getCaseNo())) {
	        return new WorkHoursInfoResp("案件號碼不可為空", false);
	    }

	    workHoursInfo.setCaseNo(caseNo);
	    workHoursInfo.setDate(date);
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
	        if (info.getWorkInfoId()!= workHoursInfoReq.getWorkInfoId() && (newStartTime.isBefore(endTime) && newEndTime.isAfter(startTime)
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
