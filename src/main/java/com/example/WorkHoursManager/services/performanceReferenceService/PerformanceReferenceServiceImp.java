package com.example.WorkHoursManager.services.performanceReferenceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.PerformanceReference;
import com.example.WorkHoursManager.repository.PerformanceReferenceDao;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceReq;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceResp;

@Service
@Qualifier("performanceReferenceService")
public class PerformanceReferenceServiceImp implements PerformanceReferenceService {

	@Autowired
	PerformanceReferenceDao performanceReferenceDao;
	
	//讀取全部PR
	@Override
	public PerformanceReferenceResp getAllPerformanceReferences() {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		List<PerformanceReference> performanceReferenceList = performanceReferenceDao.findAll();
		if(performanceReferenceList.size() == 0) {
			performanceReferenceResp.message = "沒有找到任何PerformanceReference資料";
			performanceReferenceResp.success = false;
		}
		performanceReferenceResp.setPerformanceReferenceList(performanceReferenceList);
		performanceReferenceResp.message = "資料獲取成功 ";
		performanceReferenceResp.success = true;
		return performanceReferenceResp;
	}

	//新增PR
	@Override
	public PerformanceReferenceResp setPerformanceReference(PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		PerformanceReference performanceReference = new PerformanceReference();
		if(!StringUtils.hasText(performanceReferenceReq.getCaseNo())){
			performanceReferenceResp.message = "請輸入caseNo";
			performanceReferenceResp.success = false;
		}
		PerformanceReference existsPerformanceReference = performanceReferenceDao.
				getPerformanceReferenceByCaseNo(performanceReferenceReq.getCaseNo());
		if(existsPerformanceReference != null) {
			performanceReferenceResp.message = "此caseNo已經有資料存在";
			performanceReferenceResp.success = false;
		}
		if(!StringUtils.hasText(performanceReferenceReq.getModel())) {
			performanceReferenceResp.message = "請輸入機型";
			performanceReferenceResp.success = false;
		}
		if(StringUtils.hasText(performanceReferenceReq.getRating())) {
			performanceReferenceResp.message = "新增PR評價預設為NULL,不可輸入資料!";
			performanceReferenceResp.success = false;
		}
		performanceReference.setCaseNo(performanceReferenceReq.getCaseNo());
		performanceReference.setModel(performanceReferenceReq.getModel());
		performanceReferenceDao.save(performanceReference);
		performanceReferenceResp.message = "資料儲存成功";
		performanceReferenceResp.success = true;
		return performanceReferenceResp;
	}

	//以caseNo獲取PR資料
	@Override
	public PerformanceReferenceResp getPerformanceReferenceByCaseNo(PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		if(!StringUtils.hasText(performanceReferenceReq.getCaseNo())){
			performanceReferenceResp.message = "請輸入caseNo";
			performanceReferenceResp.success = false;
		}
		
		PerformanceReference performanceReference = performanceReferenceDao.getPerformanceReferenceByCaseNo(performanceReferenceReq.getCaseNo());
		if(performanceReference == null) {
			performanceReferenceResp.message = "無此caseNo的效率參考指標資料存在";
			performanceReferenceResp.success = false;
		}
		performanceReferenceResp.setCaseNo(performanceReference.getCaseNo());
		performanceReferenceResp.setModel(performanceReference.getModel());
		performanceReferenceResp.setRating(performanceReference.getRating());
		performanceReferenceResp.message = "資料獲取成功";
		performanceReferenceResp.success = true;
		return performanceReferenceResp;
	}

	//編輯PR資料
	@Override
	public PerformanceReferenceResp editPerformanceReference(PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		PerformanceReference newPerformanceReference = new PerformanceReference();
		if(!StringUtils.hasText(performanceReferenceReq.getCaseNo())){
			performanceReferenceResp.message = "請輸入caseNo";
			performanceReferenceResp.success = false;
		}
		PerformanceReference existsPerformanceReference = performanceReferenceDao.
				getPerformanceReferenceByCaseNo(performanceReferenceReq.getCaseNo());
		if(existsPerformanceReference == null) {
			performanceReferenceResp.message = "沒有找到此caseNo的資料存在";
			performanceReferenceResp.success = false;
		}
		if(!StringUtils.hasText(performanceReferenceReq.getModel())) {
			performanceReferenceResp.message = "請輸入機型";
			performanceReferenceResp.success = false;
		}
		
		newPerformanceReference.setCaseNo(performanceReferenceReq.getCaseNo());
		newPerformanceReference.setRating(performanceReferenceReq.getRating());
		newPerformanceReference.setModel(performanceReferenceReq.getModel());
		performanceReferenceDao.save(newPerformanceReference);
		performanceReferenceResp.message = "資料修改成功";
		performanceReferenceResp.success = true;
		return performanceReferenceResp;
	}


	@Override
	public PerformanceReferenceResp deletePerformanceReference(PerformanceReferenceReq performanceReferenceReq) {
		PerformanceReferenceResp performanceReferenceResp = new PerformanceReferenceResp();
		
		if(!StringUtils.hasText(performanceReferenceReq.getCaseNo())){
			performanceReferenceResp.message = "請輸入caseNo";
			performanceReferenceResp.success = false;
		}

		PerformanceReference existsPerformanceReference = performanceReferenceDao.
				getPerformanceReferenceByCaseNo(performanceReferenceReq.getCaseNo());
		if(existsPerformanceReference == null) {
			performanceReferenceResp.message = "沒有找到此caseNo的資料存在";
			performanceReferenceResp.success = false;
		}

		performanceReferenceDao.delete(existsPerformanceReference);
		performanceReferenceResp.message = "資料刪除成功";
		performanceReferenceResp.success = true;
		return performanceReferenceResp;
	}
	
}


