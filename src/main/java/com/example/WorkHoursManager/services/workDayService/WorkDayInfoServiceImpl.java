package com.example.WorkHoursManager.services.workDayService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.entity.WorkDayInfo;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.repository.WorkDayInfoDao;
import com.example.WorkHoursManager.repository.WorkHoursInfoDao;
import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoReq;
import com.example.WorkHoursManager.vo.workDayInfoVo.WorkDayInfoResp;

@Service
@Qualifier("workDayInfoService")
public class WorkDayInfoServiceImpl implements WorkDayInfoService {
	
	//-----------------------Constructor Injection---------------------------
	private final EmployeeInfoDao employeeInfoDao;

	private final WorkDayInfoDao workDayInfoDao;
	
	private final WorkHoursInfoDao workHoursInfoDao;
	
	@Autowired
	public WorkDayInfoServiceImpl(@Qualifier("employeeInfoDao")EmployeeInfoDao employeeInfoDao , 
			@Qualifier("workDayInfoDao")WorkDayInfoDao workDayInfoDao , 
			@Qualifier("workHoursInfoDao")WorkHoursInfoDao workHoursInfoDao) {
				this.employeeInfoDao = employeeInfoDao;
				this.workDayInfoDao = workDayInfoDao;
				this.workHoursInfoDao = workHoursInfoDao;
	}
	//-----------------------Constructor Injection---------------------------

	@Override
	public WorkDayInfoResp setWorkDayInfo(WorkDayInfoReq workDayInfoReq) {
		WorkDayInfo newWorkDayInfo = new WorkDayInfo();
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		String dateReq = workDayInfoReq.getDate();
		String employeeIdReq = workDayInfoReq.getEmployeeId();
		String statusReq = workDayInfoReq.getStatus();
		float workingHoursReq = workDayInfoReq.getWorkingHours();
		boolean approvedReq = workDayInfoReq.isApproved();

        //檢查員工ID
        if(!StringUtils.hasText(employeeIdReq)) {
        	workDayInfoResp.message = "員工ID不得空白";
        	workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
        //檢查此員工ID是否存在
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeIdReq);
		if(employeeInfo == null) {
			workDayInfoResp.message = "無此ID的員工";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		newWorkDayInfo.setEmployeeInfo(employeeInfo);
		
		//檢查日期
		if(!StringUtils.hasText(dateReq)) {
			workDayInfoResp.message = "請輸入日期!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		//檢驗日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);	//設定日期檢驗模式為嚴格 , 不可有寬鬆模式容忍2月30這樣的狀況
        try {
            Date date = dateFormat.parse(dateReq);
        } catch (java.text.ParseException e) {	//字串轉換Date格式失敗視為無效日期
        	workDayInfoResp.message = "日期格式錯誤!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
        }
		
		//檢查工作狀態
		if(!StringUtils.hasText(statusReq)) {
			workDayInfoResp.message = "請輸入本日工作狀態!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		//檢查工作時數
		if(workingHoursReq < 0) {
			workDayInfoResp.message = "工作時數輸入不得低於0";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		//檢查審核是否被輸入
		if(approvedReq != false) {
			workDayInfoResp.message = "審核狀態預設為否,不可手動設定!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		approvedReq = false;
		
		System.out.println(employeeInfo);
		
		newWorkDayInfo.setStatus(statusReq);
		newWorkDayInfo.setApproved(approvedReq);
		newWorkDayInfo.setDate(dateReq);
		newWorkDayInfo.setWorkingHours(workingHoursReq);
		workDayInfoDao.save(newWorkDayInfo);
		workDayInfoResp.message = "工作日資料儲存成功";
		workDayInfoResp.success = true;
		return workDayInfoResp;
	}

	@Override
	public WorkDayInfoResp deleteWorkDayInfo(WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		String employeeIdReq = workDayInfoReq.getEmployeeId();
		String dateReq = workDayInfoReq.getDate();
		
        if(!StringUtils.hasText(employeeIdReq)) {
        	workDayInfoResp.message = "員工ID不得空白";
        	workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
        //檢查此員工ID是否存在
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeIdReq);
		if(employeeInfo == null) {
			workDayInfoResp.message = "無此ID的員工";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		List<WorkDayInfo>workDayInfoList = workDayInfoDao.getWorkDayInfoByEmployeeInfo(employeeInfo);
		for(WorkDayInfo workDayInfo : workDayInfoList) {
			if(workDayInfo.getDate().equals(dateReq)) {
				//刪除日工時表前先清空employeeInfo的關聯,才不會被連動刪除
				List<WorkDayInfo> employeesWorkDayInfoList = employeeInfo.getWorkDayInfo();
				for(int i = 0 ; i < employeesWorkDayInfoList.size() ; i++) {
					if(employeesWorkDayInfoList.get(i)==workDayInfo) {
						employeeInfo.getWorkDayInfo().set(i, null);
					}
				}
				workDayInfoDao.delete(workDayInfo);
				workDayInfoResp.message = "日工時表刪除成功";
				workDayInfoResp.success = true;
				return workDayInfoResp;
			}
		}
		workDayInfoResp.message = "沒有此日期的工時表存在";
		workDayInfoResp.success = false;
		return workDayInfoResp;
	}

	@Override
	public WorkDayInfoResp getAllWorkDayInfo(WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		List<WorkDayInfo>workDayInfoList = workDayInfoDao.findAll();
		workDayInfoResp.setWorkDayInfoList(workDayInfoList);
		workDayInfoResp.message = "工作日資料獲取成功";
		workDayInfoResp.success = true;
		return workDayInfoResp;
	}

	@Override
	public WorkDayInfoResp getWorkDayInfoByDate(WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		//檢查日期
		if(!StringUtils.hasText(workDayInfoReq.getDate())) {
			workDayInfoResp.message = "請輸入日期!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		//檢查此日期是否有日工時表存在
		List<WorkDayInfo>workDayInfoList = workDayInfoDao.getWorkDayInfoByDate(workDayInfoReq.getDate());
		if(workDayInfoList.size() ==0 ) {
			workDayInfoResp.message = "該日期無任何資料!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		workDayInfoResp.setWorkDayInfoList(workDayInfoList);
		workDayInfoResp.message = "資料獲取成功";
		workDayInfoResp.success = true;
		return workDayInfoResp;
	}
	
	@Override
	public WorkDayInfoResp getWorkDayInfoByEmployeeId(WorkDayInfoReq workDayInfoReq) {
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		
		//檢查員工ID
        if(!StringUtils.hasText(workDayInfoReq.getEmployeeId())) {
        	workDayInfoResp.message = "員工ID不得空白";
        	workDayInfoResp.success = false;
			return workDayInfoResp;
		}
        //檢查此員工ID是否存在
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(workDayInfoReq.getEmployeeId());
		if(employeeInfo == null) {
			workDayInfoResp.message = "無此ID的員工";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		List<WorkDayInfo>workDayInfoList = workDayInfoDao.getWorkDayInfoByEmployeeInfo(employeeInfo);
		workDayInfoResp.setWorkDayInfoList(workDayInfoList);
		workDayInfoResp.message = "資料獲取成功";
		workDayInfoResp.success = true;
		return workDayInfoResp;
	}

	@Override
	public WorkDayInfoResp editWorkDayInfo(WorkDayInfoReq workDayInfoReq) {
		WorkDayInfo newWorkDayInfo = new WorkDayInfo();
		WorkDayInfoResp workDayInfoResp = new WorkDayInfoResp();
		Integer workInfoId = workDayInfoReq.getWorkInfoId();
		String dateReq = workDayInfoReq.getDate();
		String employeeIdReq = workDayInfoReq.getEmployeeId();
		String statusReq = workDayInfoReq.getStatus();
		float workingHoursReq = workDayInfoReq.getWorkingHours();
		boolean approvedReq = workDayInfoReq.isApproved();

        //檢查員工ID
        if(!StringUtils.hasText(employeeIdReq)) {
        	workDayInfoResp.message = "員工ID不得空白";
        	workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
        //檢查此員工ID是否存在
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeIdReq);
		if(employeeInfo == null) {
			workDayInfoResp.message = "無此ID的員工";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		newWorkDayInfo.setEmployeeInfo(employeeInfo);
		
		//檢查日期
		if(!StringUtils.hasText(dateReq)) {
			workDayInfoResp.message = "請輸入日期!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		//檢驗日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);	//設定日期檢驗模式為嚴格 , 不可有寬鬆模式容忍2月30這樣的狀況
        try {
            Date date = dateFormat.parse(dateReq);
        } catch (java.text.ParseException e) {	//字串轉換Date格式失敗視為無效日期
        	workDayInfoResp.message = "日期格式錯誤!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
        }
		
		//檢查工作狀態
		if(!StringUtils.hasText(statusReq)) {
			workDayInfoResp.message = "請輸入本日工作狀態!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		//檢查工作時數
		if(workingHoursReq < 0) {
			workDayInfoResp.message = "工作時數輸入不得低於0";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		
		//檢查審核是否被輸入
		if(approvedReq != false) {
			workDayInfoResp.message = "審核狀態預設為否,不可手動設定!";
			workDayInfoResp.success = false;
			return workDayInfoResp;
		}
		approvedReq = false;
		
		System.out.println(employeeInfo);
		
		newWorkDayInfo.setWorkInfoId(workInfoId);
		newWorkDayInfo.setStatus(statusReq);
		newWorkDayInfo.setApproved(approvedReq);
		newWorkDayInfo.setDate(dateReq);
		newWorkDayInfo.setWorkingHours(workingHoursReq);
		workDayInfoDao.save(newWorkDayInfo);
		workDayInfoResp.message = "工作日資料修改成功";
		workDayInfoResp.success = true;
		return workDayInfoResp;
	}

}
