package com.example.WorkHoursManager.services.workHoursService.imp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.repository.WorkHoursInfoDao;
import com.example.WorkHoursManager.services.workHoursService.WorkHoursService;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

@Service
public class WorkHoursServiceImp implements WorkHoursService {

	@Autowired
	private WorkHoursInfoDao workHoursInfoDao;
	
	//Read（読み取り）
	@Override
	public List<WorkHoursInfo>getAllInfos(){
		return workHoursInfoDao.findAll();
	}

	//Create（生成）
	@Override
	public WorkHoursInfoResp addinfo(WorkHoursInfoReq workHoursInfoReq) {
		List<WorkHoursInfo>WorkHoursInfo=workHoursInfoReq.getHoursInfos();
		if(CollectionUtils.isEmpty(WorkHoursInfo)) {
			return new WorkHoursInfoResp("新增失敗",false);
		}
		List<WorkHoursInfo>existInfoList=new ArrayList<WorkHoursInfo>();
		for(WorkHoursInfo info:WorkHoursInfo) {
			if(!StringUtils.hasText(info.getPerformanceReference().getCaseNo())){
				return new WorkHoursInfoResp("案件號碼不可為空",false);
			}
			if(!StringUtils.hasText(info.getPerformanceReference().getCaseNo())){
				return new WorkHoursInfoResp("日期不可為空",false);
			}
			if(!StringUtils.hasText(info.getDetail())){
				return new WorkHoursInfoResp("工作內容不可為空",false);
			}
			if(!StringUtils.hasText(info.getEndTime())){
				return new WorkHoursInfoResp("工作結束時間不可為空",false);
			}
			if(!StringUtils.hasText(info.getStartTime())){
				return new WorkHoursInfoResp("工作開始時間不可為空",false);
			}
			if(!StringUtils.hasText(info.getModel())){
				return new WorkHoursInfoResp("機型不可為空",false);
			}
			if(!StringUtils.hasText(info.getStatus())){
				return new WorkHoursInfoResp("狀態不可為空",false);
			}
			if(workHoursInfoDao.existsById(info.getWorkInfoId())) {
				existInfoList.add(info);
			}
			
			List<WorkHoursInfo> sameDateInfos = workHoursInfoDao.findByWorkDayInfo(info.getPerformanceReference().getCaseNo());
	        if(!CollectionUtils.isEmpty(sameDateInfos)) {	        
	        	List<WorkHoursInfo> sameTimeInfos = workHoursInfoDao.findByStartTimeAndEndTime(info.getStartTime(),info.getEndTime());
	        		if(!CollectionUtils.isEmpty(sameTimeInfos)){
	        			return new WorkHoursInfoResp("新增失敗，時間已存在",false);
	        		}else {
			        	DateFormat format = new SimpleDateFormat("HH:mm");		
						try {
							Date existStartTime = format.parse(info.getStartTime());
							Date existEndTime = format.parse(info.getEndTime());
							Integer existStartTimeMinutes=existStartTime.getMinutes();
							Integer existEndTimeMinutes=existEndTime.getMinutes();
							for(WorkHoursInfo time:sameDateInfos) {
								Date startTime=format.parse(time.getStartTime());
								Date endTime=format.parse(time.getEndTime());
								Integer startTimeMinutes=startTime.getMinutes();
								Integer endTimeMinutes=endTime.getMinutes();
								if(startTimeMinutes<existStartTimeMinutes
									&&startTimeMinutes>existEndTimeMinutes
									||endTimeMinutes>existStartTimeMinutes
									&&endTimeMinutes<existEndTimeMinutes) {
									return new WorkHoursInfoResp("新增失敗，時間相衝",false);
								}
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
			        }		
	    }	        
	}
		workHoursInfoDao.saveAll(WorkHoursInfo);	
		return new WorkHoursInfoResp("新增成功",true);
	}


	//Delete（削除）
	@Override
	public String deleteInfo(int workInfoId) {
	    Optional<WorkHoursInfo> optionalInfo = workHoursInfoDao.findById(workInfoId);
	    if (optionalInfo.isPresent()) {
	        workHoursInfoDao.deleteById(workInfoId);
	        return "工時資訊已成功刪除";
	    } else {
	        return "該ID對應的工時資訊不存在，刪除失敗";
	    }
	}

}
