package com.example.WorkHoursManager.services.performanceReferenceService.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.PerformanceReference;
import com.example.WorkHoursManager.repository.PerformanceReferenceDao;
import com.example.WorkHoursManager.services.performanceReferenceService.PerformanceReferenceService;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceReq;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceResp;

@Service
public class PerformanceReferenceServiceImp implements PerformanceReferenceService {

	@Autowired
	PerformanceReferenceDao performanceReferenceDao;
	
	//Read（読み取り）
	@Override
	public List<PerformanceReference> getAllInfos() {
		return performanceReferenceDao.findAll();
	}
	
	//Create（生成）
	@Override
	public PerformanceReferenceResp addInfo(PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReference newPerformanceReference=new PerformanceReference();
		//判斷有無資料
		if(!StringUtils.hasText(performanceReferenceReq.getCaseNo())){
			return new PerformanceReferenceResp("案件號碼不可為空",false);
		}
		if(!StringUtils.hasText(performanceReferenceReq.getModel())){
			return new PerformanceReferenceResp("機型不可為空",false);
		}
		if(!StringUtils.hasText(performanceReferenceReq.getRating())){
			return new PerformanceReferenceResp("評等不可為空",false);
		}
		newPerformanceReference.setCaseNo(performanceReferenceReq.getCaseNo());
		newPerformanceReference.setModel(performanceReferenceReq.getModel());
		newPerformanceReference.setRating(performanceReferenceReq.getRating());
		performanceReferenceDao.save(newPerformanceReference);
		return new PerformanceReferenceResp("新增成功",true);
	}
	
	//Delete（削除）
//	@Override
//	public PerformanceReferenceResp deleteInfo(String caseNo) {
//		Optional<PerformanceReference>optional=performanceReferenceDao.deleteById(caseNo);
//		if (optional.isPresent()) {
//	        performanceReferenceDao.deleteById(caseNo);
//	        return new PerformanceReferenceResp("工時資訊已成功刪除", false);
//	    } else {
//	        return new PerformanceReferenceResp("該ID對應的工時資訊不存在，刪除失敗", false);
//	    }
//	}
	
	//Update(更新)
//	@Override
//	public PerformanceReferenceResp updateInfo(String caseNo,PerformanceReferenceReq performanceReferenceReq) {
//		PerformanceReference performanceReference=performanceReferenceDao.findById(caseNo);
//		if(performanceReference==null) {
//			return new PerformanceReferenceResp("找不到指定的工時紀錄", false);
//		}
//		//判斷有無資料
//				if(!StringUtils.hasText(performanceReferenceReq.getCaseNo())){
//					return new PerformanceReferenceResp("案件號碼不可為空",false);
//				}
//				if(!StringUtils.hasText(performanceReferenceReq.getModel())){
//					return new PerformanceReferenceResp("機型不可為空",false);
//				}
//				if(!StringUtils.hasText(performanceReferenceReq.getRating())){
//					return new PerformanceReferenceResp("評等不可為空",false);
//				}
//				performanceReference.setCaseNo(performanceReferenceReq.getCaseNo());
//				performanceReference.setModel(performanceReferenceReq.getModel());
//				performanceReference.setRating(performanceReferenceReq.getRating());
//				performanceReferenceDao.save(performanceReference);
//				return new PerformanceReferenceResp("更新成功",true);
//	}
}


