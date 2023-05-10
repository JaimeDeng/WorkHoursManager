package com.example.WorkHoursManager.services.workHoursService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.example.WorkHoursManager.services.caseInfoService.CaseInfoService;
import com.example.WorkHoursManager.services.performanceReferenceService.PerformanceReferenceService;
import com.example.WorkHoursManager.services.workDayInfoService.WorkDayInfoService;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
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
	public WorkHoursInfoResp getAllWorkHoursInfo(){
		WorkHoursInfoResp workHoursInfoResp = new WorkHoursInfoResp();
		workHoursInfoResp.setWorkHoursInfoList(workHoursInfoDao.findAll());
		return workHoursInfoResp;
	}
	
	//創建工時表
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
		if(dateReq.length() != 8) {
			return new WorkHoursInfoResp("日期格式為yyyyMMdd 8位數字",false);
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
			
			//連動新增caseInfo(先看沒有此員工該CaseNo的caseInfo存在決定新增或更新)
			CaseInfoReq caseInfoReq = new CaseInfoReq();
			List<CaseInfo> existsCaseInfoList = caseInfoDao.getCaseInfoByCaseNo(caseNoReq);
			if(existsCaseInfoList.size() == 0){
				System.out.println("沒有此caseNo , 直接存新的");
				//完全沒有此caseNo的資料存在就直接存
				caseInfoReq.setCaseNo(caseNoReq);
				caseInfoReq.setDate(dateReq);
				caseInfoReq.setEmployeeId(employeeIdReq);
				caseInfoReq.setModel(modelReq);
				caseInfoReq.setDuration(durationHours);
				caseInfoService.setCaseInfo(caseInfoReq);
				if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
					return new WorkHoursInfoResp("CaseInfo新增失敗",false);
				}
			}else{
				//已有該caseNo存在就檢查有屬於無此員工的
				System.out.println("有此caseNo");
				boolean thisEmployeeHasThisCaseNoInfo = false;
				for(CaseInfo existsCaseInfo : existsCaseInfoList) {
					if(existsCaseInfo.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
						
						//如果有該員工此caseNo存在就用修改的
						thisEmployeeHasThisCaseNoInfo = true;
						caseInfoReq.setCaseInfoId(existsCaseInfo.getCaseInfoId());
						
						//檢查最新日期
						try {
							Date date = dateFormat.parse(dateReq);
							Date existsDate = dateFormat.parse(existsCaseInfo.getDate());
							if(date.after(existsDate)) {
								caseInfoReq.setDate(dateReq);	//新日期覆寫
							}else {
								caseInfoReq.setDate(existsCaseInfo.getDate());	//輸入日期較舊 , 日期維持
							}
						} catch (ParseException e) {
							return new WorkHoursInfoResp("日期格式錯誤",true);
						}
						caseInfoReq.setCaseNo(caseNoReq);
						caseInfoReq.setEmployeeId(employeeIdReq);
						caseInfoReq.setModel(modelReq);
						caseInfoReq.setDuration(existsCaseInfo.getDuration() + durationHours);	//時間疊加			
						caseInfoService.editCaseInfo(caseInfoReq);
						if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
							return new WorkHoursInfoResp("CaseInfo新增失敗",false);
						}
					}
				}
				//有該caseNo存在但不是此員工的也直接存新的
				if(thisEmployeeHasThisCaseNoInfo == false) {
					caseInfoReq.setCaseNo(caseNoReq);
					caseInfoReq.setDate(dateReq);
					caseInfoReq.setEmployeeId(employeeIdReq);
					caseInfoReq.setModel(modelReq);
					caseInfoReq.setDuration(durationHours);
					caseInfoService.setCaseInfo(caseInfoReq);
					if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
						return new WorkHoursInfoResp("CaseInfo新增失敗",false);
					}
				}			
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
			
			//連動新增PR(先看沒有此CaseNo的PR存在才新增)
			if(performanceReferenceDao.getPerformanceReferenceByCaseNo(caseNoReq) == null){
				PerformanceReferenceReq performanceReferenceReq = new PerformanceReferenceReq();
				performanceReferenceReq.setCaseNo(caseNoReq);
				performanceReferenceReq.setModel(modelReq);
				performanceReferenceService.setPerformanceReference(performanceReferenceReq);
			}
			
			//連動新增caseInfo(先看沒有此員工該CaseNo的caseInfo存在決定新增或更新)
			CaseInfoReq caseInfoReq = new CaseInfoReq();
			List<CaseInfo> existsCaseInfoList = caseInfoDao.getCaseInfoByCaseNo(caseNoReq);
			if(existsCaseInfoList.size() == 0){
				//完全沒有此caseNo的資料存在就直接存
				caseInfoReq.setCaseNo(caseNoReq);
				caseInfoReq.setDate(dateReq);
				caseInfoReq.setEmployeeId(employeeIdReq);
				caseInfoReq.setModel(modelReq);
				caseInfoReq.setDuration(durationHours);
				caseInfoService.setCaseInfo(caseInfoReq);
				if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
					return new WorkHoursInfoResp("CaseInfo新增失敗",false);
				}
			}else{
				//已有該caseNo存在就檢查有屬於無此員工的
				boolean thisEmployeeHasThisCaseNoInfo = false;
				for(CaseInfo existsCaseInfo : existsCaseInfoList) {
					if(existsCaseInfo.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
						
						//如果該員工有此caseNo存在就用修改的
						thisEmployeeHasThisCaseNoInfo = true;
						caseInfoReq.setCaseInfoId(existsCaseInfo.getCaseInfoId());
						
						//檢查最新日期
						try {
							Date date = dateFormat.parse(dateReq);
							Date existsDate = dateFormat.parse(existsCaseInfo.getDate());
							if(date.after(existsDate)) {
								caseInfoReq.setDate(dateReq);	//新日期覆寫
							}else {
								caseInfoReq.setDate(existsCaseInfo.getDate());	//輸入日期較舊 , 日期維持
							}
						} catch (ParseException e) {
							return new WorkHoursInfoResp("日期格式錯誤",false);
						}
						caseInfoReq.setCaseNo(caseNoReq);
						caseInfoReq.setDate(dateReq);
						caseInfoReq.setEmployeeId(employeeIdReq);
						caseInfoReq.setModel(modelReq);
						caseInfoReq.setDuration(existsCaseInfo.getDuration() + durationHours);	//時間疊加		
						caseInfoService.editCaseInfo(caseInfoReq);
						if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
							return new WorkHoursInfoResp("CaseInfo新增失敗",false);
						}
					}
				}
				//有該caseNo存在但不是此員工的也直接存新的
				if(thisEmployeeHasThisCaseNoInfo == false) {
					caseInfoReq.setCaseNo(caseNoReq);
					caseInfoReq.setDate(dateReq);
					caseInfoReq.setEmployeeId(employeeIdReq);
					caseInfoReq.setModel(modelReq);
					caseInfoReq.setDuration(durationHours);
					caseInfoService.setCaseInfo(caseInfoReq);
					if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
						return new WorkHoursInfoResp("CaseInfo新增失敗",false);
					}
				}			
			}
			workHoursInfoDao.save(newWorkHoursInfo);
		}
		return new WorkHoursInfoResp("新增成功",true);	
	}

	//刪除工時表
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
		//連動更新caseInfo
		List<CaseInfo> existsCaseInfoList =  caseInfoDao.getCaseInfoByCaseNo(workHoursInfo.get().getCaseNo());
		System.out.println(workInfoId + "進到連動更新CI囉");
		if(existsCaseInfoList.size() == 0) {
			return new WorkHoursInfoResp("無caseInfo資料存在", false);
		}
		for(CaseInfo existsCaseInfo : existsCaseInfoList) {
			if(existsCaseInfo.getEmployeeInfo().getEmployeeId().equals(employeeId)) {
				System.out.println(workInfoId + "進到同員工ID區囉");
				//若此筆工單刪除後caseInfo合計時間為0就刪除
				if((existsCaseInfo.getDuration() - durationHours) == 0) {
					System.out.println(workInfoId + "進到直接刪除囉");
					caseInfoDao.delete(existsCaseInfo);
				}else {
					System.out.println(workInfoId + "進到日期判斷囉");
					existsCaseInfo.setDuration(existsCaseInfo.getDuration() - durationHours);
					//抓出此caseNo剩下的工時表的所有日期 , 來比對最後日期
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					List<WorkHoursInfo>workHoursInfoListInEdit = workHoursInfoDao.getWorkHoursInfoByEmployeeInfo(employeeInfo);
					List<Date>dateList = new ArrayList<>();
					Date existsDate;
				    for(WorkHoursInfo existsWorkHoursInfo : workHoursInfoListInEdit) {
				    	if(existsWorkHoursInfo.getCaseNo().equals(existsCaseInfo.getCaseNo())){
				            try {
				                existsDate = dateFormat.parse(existsWorkHoursInfo.getDate());
				            } catch (java.text.ParseException e) {	//字串轉換Date格式失敗視為無效日期
				            	return new WorkHoursInfoResp("日期格式錯誤",false);
				            }
				    		dateList.add(existsDate);
				    	}
				    }
				    System.out.println(dateList.toString());
				    //只剩下一個就直接把這個日期寫入
				    if(dateList.size() == 1) {
				    	String latestDate = dateFormat.format(dateList.get(0));
				    	existsCaseInfo.setDate(latestDate);
				    }else {
				    	//找出最新的日期
					    Date latestDate = null;
					    System.out.println(dateList.size());
					    for(Date existsDateA : dateList) {
					    	for(Date existsDateB : dateList) {
						    	if(existsDateB.after(existsDateA)) {
						    		latestDate = existsDateB;
						    	}
						    }
					    }
					    System.out.println(latestDate);
					    String latestDateStr = dateFormat.format(latestDate);
					    System.out.println(latestDateStr);
					    if(latestDate == null) {
					    	return new WorkHoursInfoResp("caseInfo:日期為NULL",false);
					    }
					    existsCaseInfo.setDate(latestDateStr);
				    }
				    //修改完時數與日期後存回
				    caseInfoDao.save(existsCaseInfo);
				}	
			}		
		}
		return new WorkHoursInfoResp("工時表刪除成功", true);
	}
	
	//編輯工時表(不可更改日期)
	@Override
	public WorkHoursInfoResp editWorkHoursInfo(WorkHoursInfoReq workHoursInfoReq) {
		Integer workInfoIdReq = workHoursInfoReq.getWorkInfoId();
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
		if(workInfoIdReq < 1) {
			return new WorkHoursInfoResp("請輸入工時表單號碼 , 且不得小於1",false);
		}
		Optional<WorkHoursInfo>newWorkHoursInfoOpt = workHoursInfoDao.findById(workInfoIdReq);
		WorkHoursInfo newWorkHoursInfo = newWorkHoursInfoOpt.get();
		String oldCaseNo = newWorkHoursInfo.getCaseNo();
		String oldStartTime = newWorkHoursInfo.getStartTime();
		String oldEndTime = newWorkHoursInfo.getEndTime();
		if(newWorkHoursInfoOpt.isEmpty()) {
			return new WorkHoursInfoResp("無此表單號碼的工時表存在",false);
		}
		if(!StringUtils.hasText(employeeIdReq)) {
			return new WorkHoursInfoResp("請輸入員工ID",false);
		}
		if(employeeInfo == null) {
			return new WorkHoursInfoResp("此員工ID不存在",false);
		}
		if(!StringUtils.hasText(caseNoReq)){
			return new WorkHoursInfoResp("案件號碼不可為空",false);
		}
		if(StringUtils.hasText(dateReq)){
			return new WorkHoursInfoResp("不可更改日期",false);
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
		
		//取得修改前的開始與結束時間的分鐘數
		LocalTime startTime = LocalTime.parse(oldStartTime);
		//把小時轉成分鐘加上分鐘數
		int startTimeMinutes = startTime.getHour() * 60 + startTime.getMinute();
		
		LocalTime endTime = LocalTime.parse(oldEndTime);
		//把小時轉成分鐘加上分鐘數
		int endTimeMinutes = endTime.getHour() * 60 + endTime.getMinute();
		
		//取得修改後的開始與結束時間的分鐘數
		LocalTime newStartTime = LocalTime.parse(startTimeReq);
		//把小時轉成分鐘加上分鐘數
		int newStartTimeMinutes = newStartTime.getHour() * 60 + newStartTime.getMinute();
		
		LocalTime newEndTime = LocalTime.parse(endTimeReq);
		//把小時轉成分鐘加上分鐘數
		int newEndTimeMinutes = newEndTime.getHour() * 60 + newEndTime.getMinute();
		
		if(newStartTimeMinutes > newEndTimeMinutes) {
			return new WorkHoursInfoResp("開始時間不得在結束時間之後",false);
		}
		
		//判斷是否為同一日期
		List<WorkHoursInfo> workHoursInfoList = workHoursInfoDao.getWorkHoursInfoByEmployeeInfo(employeeInfo);
		//將同一個日期的workHoursInfo抓出來為一個新的List
		List<WorkHoursInfo> sameDateWorkHoursInfoList = new ArrayList<>();
		for (WorkHoursInfo info : workHoursInfoList) {
			if(info.getDate().equals(newWorkHoursInfo.getDate()) && info.getWorkInfoId() != newWorkHoursInfo.getWorkInfoId()) {
				sameDateWorkHoursInfoList.add(info);
			}
		}
		System.out.println("同一員工的單有" + workHoursInfoList.size() + "筆");
		System.out.println("同一天的單有" + sameDateWorkHoursInfoList.size() + "筆");
		for(WorkHoursInfo sameDateWorkHoursInfo : sameDateWorkHoursInfoList) {
			//取得每個同日期工時表的開始與結束時間的分鐘數
			LocalTime sameDateWorkHoursInfoStartTime = LocalTime.parse(sameDateWorkHoursInfo.getStartTime());
			int sameDateWorkHoursInfoStartTimeMinutes = sameDateWorkHoursInfoStartTime.getHour() * 60 + sameDateWorkHoursInfoStartTime.getMinute();
			
			LocalTime sameDateWorkHoursInfoEndTime = LocalTime.parse(sameDateWorkHoursInfo.getEndTime());
			int sameDateWorkHoursInfoEndTimeMinutes = sameDateWorkHoursInfoEndTime.getHour() * 60 + sameDateWorkHoursInfoEndTime.getMinute();
			
			//檢查時間有無重疊
			if(newStartTimeMinutes > sameDateWorkHoursInfoStartTimeMinutes
					&& newStartTimeMinutes < sameDateWorkHoursInfoEndTimeMinutes
					||	newEndTimeMinutes > sameDateWorkHoursInfoStartTimeMinutes
					&& newEndTimeMinutes < sameDateWorkHoursInfoEndTimeMinutes
					||	newStartTimeMinutes == sameDateWorkHoursInfoStartTimeMinutes
					&&newEndTimeMinutes == sameDateWorkHoursInfoEndTimeMinutes
					||	newStartTimeMinutes < sameDateWorkHoursInfoStartTimeMinutes
					&&newEndTimeMinutes == sameDateWorkHoursInfoEndTimeMinutes
					||	newStartTimeMinutes == sameDateWorkHoursInfoStartTimeMinutes
					&&	newEndTimeMinutes > sameDateWorkHoursInfoEndTimeMinutes) {
				return new WorkHoursInfoResp("新增失敗 , 新增表單與既有表單時間重疊",false);
			}
		}
		
		newWorkHoursInfo.setWorkInfoId(newWorkHoursInfo.getWorkInfoId());
		newWorkHoursInfo.setEmployeeInfo(employeeInfo);
		newWorkHoursInfo.setCaseNo(caseNoReq);
		newWorkHoursInfo.setDate(newWorkHoursInfo.getDate());	//日期不動
		newWorkHoursInfo.setDetail(detailReq);
		newWorkHoursInfo.setStartTime(startTimeReq);
		newWorkHoursInfo.setEndTime(endTimeReq);
		newWorkHoursInfo.setModel(modelReq);
		newWorkHoursInfo.setStatus(statusReq);
		workHoursInfoDao.save(newWorkHoursInfo);
		
		//檢查時間有無更動
		boolean timeHasBeenChange = false;
		if(!newWorkHoursInfo.getStartTime().equals(oldStartTime) || !newWorkHoursInfo.getEndTime().equals(oldEndTime)) {
			//有更動
			timeHasBeenChange = true;
		}
		//檢查案件號碼有無更動
		boolean caseNoHasBeenChange = false;
		if(!newWorkHoursInfo.getCaseNo().equals(oldCaseNo)) {
			//有更動
			caseNoHasBeenChange = true;
		}
		
		//連動更新WorkDayInfo
		List<WorkDayInfo>existsWorkDayInfoList = workDayInfoDao.getWorkDayInfoByDate(newWorkHoursInfo.getDate());
		for(WorkDayInfo existsWorkDayInfo : existsWorkDayInfoList) {
			if(existsWorkDayInfo.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
				//取出該員工該日的日報表
				WorkDayInfo workDayInfo = existsWorkDayInfo;
				WorkDayInfoReq workDayInfoReq = new WorkDayInfoReq();
				workDayInfoReq.setWorkInfoId(existsWorkDayInfo.getWorkInfoId());
				workDayInfoReq.setDate(workDayInfo.getDate());
				workDayInfoReq.setEmployeeId(employeeIdReq);		
				
				//1.caseNo沒改時間沒改 2.caseNo沒改時間有改 3.caseNo有改時間沒改 4.caseNo有改時間有改
				//1.檢查status就好         2.檢查status跟時間補正   3.檢查status就好		4.檢查status跟時間補正
				
				//時間沒改 , 日報表時間也不用改
				if(timeHasBeenChange == false) {
					workDayInfoReq.setWorkingHours(workDayInfo.getWorkingHours());
					
					//該日的所有工時資料來檢查status
					boolean attendance = false;
				    for(WorkHoursInfo existsWorkHoursInfo : workHoursInfoList) {
				    	if(existsWorkHoursInfo.getDate().equals(workDayInfo.getDate())){
				    		if(existsWorkHoursInfo.getStatus() == "出勤") {
				    			attendance = true;
				    			workDayInfoReq.setStatus("出勤");
				    		}
				    	}
				    }
				    if(attendance == false) {
				    	workDayInfoReq.setStatus(statusReq);
				    }
				    
				}else {
					//計算舊的工時表總時數
					float durationMinutes = endTimeMinutes - startTimeMinutes;
					float durationHours = durationMinutes / 60;
					//計算新的工時表總時數
					float newDurationMinutes = newEndTimeMinutes - newStartTimeMinutes;
					float newDurationHours = newDurationMinutes / 60;
					//新的比舊的時間長
					if(newDurationHours > durationHours) {
						//原本的日報表時間增加多的時間
						workDayInfoReq.setWorkingHours(workDayInfo.getWorkingHours() + (newDurationHours - durationHours));
					}else {
						//新的比舊的時間短
						//原本的日報表時間減去少掉的時間
						workDayInfoReq.setWorkingHours(workDayInfo.getWorkingHours() - (durationHours - newDurationHours));
					}
					
					//該日的所有工時資料來檢查status
					boolean attendance = false;
				    for(WorkHoursInfo existsWorkHoursInfo : workHoursInfoList) {
				    	if(existsWorkHoursInfo.getDate().equals(workDayInfo.getDate())){
				    		if(existsWorkHoursInfo.getStatus() == "出勤") {
				    			attendance = true;
				    			workDayInfoReq.setStatus("出勤");
				    		}
				    	}
				    }
				    if(attendance == false) {
				    	workDayInfoReq.setStatus(statusReq);
				    }
				    
				}
				//判斷完畢更新日報表
				workDayInfoService.editWorkDayInfo(workDayInfoReq);
			}
		}
		
		//連動更新PR
		PerformanceReferenceReq performanceReferenceReq = new PerformanceReferenceReq();
		performanceReferenceReq.setCaseNo(caseNoReq);
		performanceReferenceReq.setModel(modelReq);
		performanceReferenceService.editPerformanceReference(performanceReferenceReq);
		
		//連動更新caseInfo(先看沒有此CaseNo的caseInfo存在決定新增或更新)
		CaseInfoReq caseInfoReq = new CaseInfoReq();
		List<CaseInfo> existsCaseInfoList = caseInfoDao.getCaseInfoByCaseNo(caseNoReq);
		System.out.println("抓到caseNo資料了 , 有" + existsCaseInfoList.size() + "筆");
		
//-----------------------有改caseNo , 沒有修改後的案件號碼在資料庫內,新增案件號碼資訊 & 修改舊案件資訊----------------------
		if(existsCaseInfoList.size() == 0) {
			System.out.println("案件號碼修改後是新的案件號碼");
			
			//計算舊的工時表總時數
			float durationMinutes = endTimeMinutes - startTimeMinutes;
			float durationHours = durationMinutes / 60;
			//計算新的工時表總時數
			float newDurationMinutes = newEndTimeMinutes - newStartTimeMinutes;
			float newDurationHours = newDurationMinutes / 60;
			
			//檢查原本caseNo扣掉工時表時間後還有沒有剩餘的資料
			List<CaseInfo> oldCaseNoCaseInfoList = caseInfoDao.getCaseInfoByCaseNo(oldCaseNo);
			for(CaseInfo oldCaseNoCaseInfo : oldCaseNoCaseInfoList) {
				if(oldCaseNoCaseInfo.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
					//扣掉原本的工時表時間後時間歸零了 , 直接砍掉這個caseInfo
					if((oldCaseNoCaseInfo.getDuration() - durationHours) == 0) {
						caseInfoDao.delete(oldCaseNoCaseInfo);
						System.out.println("舊案件資訊已刪除");
					}else {
						//若扣掉原本工時表還有剩餘時間就更新時間就好
						oldCaseNoCaseInfo.setDuration(oldCaseNoCaseInfo.getDuration() - durationHours);
						caseInfoDao.save(oldCaseNoCaseInfo);
						System.out.println("舊案件資訊已修改");
					}
				}
			}
			
			//直接新增一個更改後的caseNo資料
			CaseInfoReq caseInfoReqInEdit = new CaseInfoReq();
			caseInfoReqInEdit.setCaseNo(caseNoReq);
			caseInfoReqInEdit.setDate(newWorkHoursInfo.getDate());
			caseInfoReqInEdit.setEmployeeId(employeeIdReq);
			caseInfoReqInEdit.setModel(modelReq);
			caseInfoReqInEdit.setDuration(newDurationHours);
			caseInfoService.setCaseInfo(caseInfoReqInEdit);
			if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
				return new WorkHoursInfoResp("CaseInfo新增失敗",false);
			}
			System.out.println("新增新的案件資訊完成");
			return new WorkHoursInfoResp("資料修改成功",true);
			

//-----------------------有修改後的案件號碼在資料庫內 或 沒有改caseNo----------------------			
		}else {
			System.out.println("案件號碼沒改 或 修改後有舊有的此案件號碼存在");
			
			CaseInfo caseInfo = new CaseInfo();
			for(CaseInfo existsCaseInfo : existsCaseInfoList) {
				if(existsCaseInfo.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
					System.out.println("進到此員工的此caseNo案件內了");
					//抓出此員工的這個caseNo的案件資訊
					caseInfo = existsCaseInfo;
					
					//-----------------------------------沒改caseNo--------------------------------------
					if(caseNoHasBeenChange == false) {
						//如果案件號碼沒有更改 , 只要再檢查時間有無更改
						System.out.println(workInfoIdReq + "進到案件號碼沒有改囉");
						caseInfoReq.setCaseInfoId(existsCaseInfo.getCaseInfoId());
						caseInfoReq.setCaseNo(existsCaseInfo.getCaseNo());
						caseInfoReq.setDate(existsCaseInfo.getDate());	//日期不會改
						caseInfoReq.setEmployeeId(employeeIdReq);
						caseInfoReq.setModel(modelReq);
						//時間沒有更改 , 該case總時數不用改
						if(timeHasBeenChange == false) {
							System.out.println(workInfoIdReq + "進到時間也沒有更改囉");
							caseInfoReq.setDuration(existsCaseInfo.getDuration());
							caseInfoService.editCaseInfo(caseInfoReq);
							return new WorkHoursInfoResp("資料修改成功",true);
						}else {
							System.out.println(workInfoIdReq + "進到案件沒改但時間有更改囉");
							//時間有更改 , 該case總時數隨著更改
							//計算舊的工時表總時數
							float durationMinutes = endTimeMinutes - startTimeMinutes;
							float durationHours = durationMinutes / 60;
							//計算新的工時表總時數
							float newDurationMinutes = newEndTimeMinutes - newStartTimeMinutes;
							float newDurationHours = newDurationMinutes / 60;
							//新的比舊的時間長
							if(newDurationHours > durationHours) {
								//原本的案件資料時間增加多的時間
								System.out.println(workInfoIdReq + "時間變更長囉");
								float newDuration = caseInfo.getDuration() + (newDurationHours - durationHours);
								caseInfoReq.setDuration(newDuration);
								System.out.println(caseInfoReq.getDuration());
								caseInfoService.editCaseInfo(caseInfoReq);
							}else {
								//新的比舊的時間短
								//原本的案件資料時間減去少掉的時間
								System.out.println(workInfoIdReq + "時間變更短囉");
								float newDuration = caseInfo.getDuration() + (newDurationHours - durationHours);
								caseInfoReq.setDuration(newDuration);
								System.out.println(caseInfoReq.getDuration());
								caseInfoService.editCaseInfo(caseInfoReq);
							}
						}
						if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
							return new WorkHoursInfoResp("CaseInfo新增失敗",false);
						}
						return new WorkHoursInfoResp("資料修改成功",true);
					}
					//-----------------------------------沒改caseNo--------------------------------------
					
					
					//-----------------------------------有改caseNo--------------------------------------
					if(caseNoHasBeenChange) {
						System.out.println(workInfoIdReq + "進到案件號碼有更改囉");
						//計算舊的工時表總時數
						float durationMinutes = endTimeMinutes - startTimeMinutes;
						float durationHours = durationMinutes / 60;
						//計算新的工時表總時數
						float newDurationMinutes = newEndTimeMinutes - newStartTimeMinutes;
						float newDurationHours = newDurationMinutes / 60;
						
						//如果扣掉這張工時表的時數 , 案件表時數為0 , 就直接砍掉案件表 , 並新增案件表
						CaseInfo originCaseInfo = null;
						List<CaseInfo> originCaseInfoList = caseInfoDao.getCaseInfoByCaseNo(oldCaseNo);
						for(CaseInfo thisEmployeeIdoriginCaseInfo : originCaseInfoList) {
							if(thisEmployeeIdoriginCaseInfo.getEmployeeInfo().getEmployeeId().equals(employeeIdReq));
							originCaseInfo = thisEmployeeIdoriginCaseInfo;
						}
						System.out.println("原本該caseInfo的時間是"+originCaseInfo.getDuration()+"這張工時表的時間是"
								+durationHours);
						if((originCaseInfo.getDuration() - durationHours) == 0) {
							caseInfoDao.delete(originCaseInfo);
							System.out.println(workInfoIdReq + "扣掉這張表時數歸0 , 刪除原本caseInfo新增新的");
							//新增caseInfo(先看沒有此員工該CaseNo的caseInfo存在決定新增或更新)
							CaseInfoReq caseInfoReqInEdit = new CaseInfoReq();
							List<CaseInfo> existsCaseInfoListInEdit = caseInfoDao.getCaseInfoByCaseNo(caseNoReq);
							if(existsCaseInfoListInEdit.size() == 0){
								//完全沒有此caseNo的資料存在就直接存
								System.out.println(workInfoIdReq + "進到沒caseNo資料直接存囉");
								caseInfoReqInEdit.setCaseNo(caseNoReq);
								caseInfoReqInEdit.setDate(newWorkHoursInfo.getDate());
								caseInfoReqInEdit.setEmployeeId(employeeIdReq);
								caseInfoReqInEdit.setModel(modelReq);
								caseInfoReqInEdit.setDuration(newDurationHours);
								caseInfoService.setCaseInfo(caseInfoReqInEdit);
								if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
									return new WorkHoursInfoResp("CaseInfo新增失敗",false);
								}
								return new WorkHoursInfoResp("資料修改成功",true);
							}else{
								//已有該caseNo存在就檢查有屬於無此員工的
								System.out.println(workInfoIdReq + "進到有caseNo檢查員工囉");
								boolean thisEmployeeHasThisCaseNoInfo = false;
								for(CaseInfo existsCaseInfoInEdit : existsCaseInfoList) {
									if(existsCaseInfoInEdit.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
										
										//如果有該員工此caseNo存在就用修改的
										thisEmployeeHasThisCaseNoInfo = true;
										caseInfoReqInEdit.setCaseInfoId(existsCaseInfoInEdit.getCaseInfoId());									
										caseInfoReqInEdit.setDate(existsCaseInfoInEdit.getDate());	//輸入日期較舊 , 日期維持
										caseInfoReqInEdit.setCaseNo(caseNoReq);
										caseInfoReqInEdit.setEmployeeId(employeeIdReq);
										caseInfoReqInEdit.setModel(modelReq);
										caseInfoReqInEdit.setDuration(existsCaseInfoInEdit.getDuration() + newDurationHours);	//時間疊加		
										caseInfoService.editCaseInfo(caseInfoReqInEdit);
										if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
											return new WorkHoursInfoResp("CaseInfo新增失敗",false);
										}
										return new WorkHoursInfoResp("資料修改成功",true);
									}
								}
								//有該caseNo存在但不是此員工的也直接存新的
								if(thisEmployeeHasThisCaseNoInfo == false) {
									caseInfoReqInEdit.setCaseNo(caseNoReq);
									caseInfoReqInEdit.setDate(dateReq);
									caseInfoReqInEdit.setEmployeeId(employeeIdReq);
									caseInfoReqInEdit.setModel(modelReq);
									caseInfoReqInEdit.setDuration(newDurationHours);
									caseInfoService.setCaseInfo(caseInfoReqInEdit);
									if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
										return new WorkHoursInfoResp("CaseInfo新增失敗",false);
									}
									return new WorkHoursInfoResp("資料修改成功",true);
								}			
							}
						}else {
							//新增caseInfo(先看沒有此員工該CaseNo的caseInfo存在決定新增或更新)
							System.out.println(workInfoIdReq + "還有剩餘的工時表是舊caseInfo的");
							CaseInfoReq caseInfoReqInEdit = new CaseInfoReq();
							List<CaseInfo> existsCaseInfoListInEdit = caseInfoDao.getCaseInfoByCaseNo(caseNoReq);
							if(existsCaseInfoListInEdit.size() == 0){
								System.out.println(workInfoIdReq + "沒有新caseNo的案件資料存在");
								//完全沒有此caseNo的資料存在就直接存
								caseInfoReqInEdit.setCaseNo(caseNoReq);
								caseInfoReqInEdit.setDate(dateReq);
								caseInfoReqInEdit.setEmployeeId(employeeIdReq);
								caseInfoReqInEdit.setModel(modelReq);
								caseInfoReqInEdit.setDuration(newDurationHours);
								caseInfoService.setCaseInfo(caseInfoReqInEdit);
								if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
									return new WorkHoursInfoResp("CaseInfo新增失敗",false);
								}
							}else{
								//已有該caseNo存在就檢查有屬於無此員工的
								System.out.println(workInfoIdReq + "有新caseNo的案件資料存在");
								boolean thisEmployeeHasThisCaseNoInfo = false;
								for(CaseInfo existsCaseInfoInEdit : existsCaseInfoList) {
									if(existsCaseInfoInEdit.getEmployeeInfo().getEmployeeId().equals(employeeIdReq)) {
										
										//如果有該員工此caseNo存在就用修改的
										System.out.println(workInfoIdReq + "有新caseNo的案件且是該員工的資料存在");
										System.out.println("新的caseNo為" + existsCaseInfoInEdit.getCaseNo());
										thisEmployeeHasThisCaseNoInfo = true;
										caseInfoReqInEdit.setCaseNo(caseNoReq);
										caseInfoReqInEdit.setCaseInfoId(existsCaseInfoInEdit.getCaseInfoId());
										caseInfoReqInEdit.setDate(existsCaseInfoInEdit.getDate());	//輸入日期較舊 , 日期維持
										caseInfoReqInEdit.setEmployeeId(employeeIdReq);
										caseInfoReqInEdit.setModel(modelReq);
										caseInfoReqInEdit.setDuration(existsCaseInfoInEdit.getDuration() + newDurationHours);	//時間疊加
										System.out.println(existsCaseInfoInEdit.getDuration() +"舊時間加上新時間"+ newDurationHours);
										caseInfoService.editCaseInfo(caseInfoReqInEdit);
										
									}
								}
								//有該caseNo存在但不是此員工的也直接存新的
								if(thisEmployeeHasThisCaseNoInfo == false) {
									System.out.println(workInfoIdReq + "有新caseNo的案件但不是該員工的資料存在");
									caseInfoReqInEdit.setCaseNo(caseNoReq);
									caseInfoReqInEdit.setDate(dateReq);
									caseInfoReqInEdit.setEmployeeId(employeeIdReq);
									caseInfoReqInEdit.setModel(modelReq);
									caseInfoReqInEdit.setDuration(newDurationHours);
									caseInfoService.setCaseInfo(caseInfoReqInEdit);
									if(caseInfoDao.getCaseInfoByCaseNo(caseNoReq) == null) {
										return new WorkHoursInfoResp("CaseInfo新增失敗",false);
									}
								}
							}
							//原本的表的剩餘caseInfo要修正時間
							originCaseInfo.setDuration(originCaseInfo.getDuration() - durationHours);
							System.out.println("修正原caseInfo時間為" + originCaseInfo.getDuration());
							caseInfoDao.save(originCaseInfo);
							return new WorkHoursInfoResp("資料修改成功",true);
						}	//判斷該工時表是否為caseInfo的初始表
					}
					//-----------------------------------有改caseNo--------------------------------------
					
					
				}	//此員工的此caseNo案件內
			}	//此caseNo遍歷內
		}	//有修改後的案件號碼在資料庫內,修改既有資訊 & 修改或刪除舊案件資訊
		System.out.println("跑到最底囉");
		return new WorkHoursInfoResp("資料修改成功",true);
	}
	
	//如果案件號碼改了 要抓原案件的表 原案件的表減掉修改的工時表時長 = 0就整個砍掉
	//若滿足上面也表示這工時表就是生成案件表的初表 , 所以更改的內容 = 新增新的案件表
	//如果不是 , 除了新增新的案件表 , 要再修改原本的案件表
	//==>原本的案件表時長減掉本日工時長即可
}
