package com.example.WorkHoursManager.services.caseInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.repository.CaseInfoDao;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;

@Service
@Qualifier("caseInfoService")
public class CaseInfoServiceImp implements CaseInfoService{
	
	//-----------------------Constructor Injection---------------------------
	private final CaseInfoDao caseInfoDao;
	private final EmployeeInfoDao employeeInfoDao;
	
	public CaseInfoServiceImp(
			@Qualifier("caseInfoDao")CaseInfoDao caseInfoDao,
			@Qualifier("employeeInfoDao")EmployeeInfoDao employeeInfoDao
			) {
		this.caseInfoDao = caseInfoDao;
		this.employeeInfoDao = employeeInfoDao;
	}
	//-----------------------Constructor Injection---------------------------
	
	//讀取全部caseInfo
	@Override
	public CaseInfoResp getAllCaseInfo(CaseInfoReq caseInfoReq) {
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		List<CaseInfo>caseInfoList = caseInfoDao.findAll();
		if(caseInfoList.size() == 0) {
			caseInfoResp.message = "沒有任何caseInfo存在";
			caseInfoResp.success = false;
			return caseInfoResp;
		}
		caseInfoResp.setCaseInfoList(caseInfoList);
		caseInfoResp.message = "資料獲取成功";
		caseInfoResp.success = true;
		return caseInfoResp;
	}
	
	//新增caseInfo
	@Override
	public CaseInfoResp setCaseInfo(CaseInfoReq caseInfoReq) {
		CaseInfo newCaseInfo = new CaseInfo();
		String employeeId = caseInfoReq.getEmployeeId();
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
		
		//判斷有無資料
		if(!StringUtils.hasText(caseInfoReq.getCaseNo())){
			return new CaseInfoResp("案件號碼不可為空",false);
		}
		if(caseInfoDao.getCaseInfoByCaseNo(caseInfoReq.getCaseNo()) != null) {
			return new CaseInfoResp("此caseNo已有資料存在",false);
		}
		if(!StringUtils.hasText(caseInfoReq.getModel())){
			return new CaseInfoResp("機型不可為空",false);
		}
		if(caseInfoReq.getDuration() < 0){
			return new CaseInfoResp("完工時長不可小於0",false);
		}
		if(!StringUtils.hasText(caseInfoReq.getDate())){
			return new CaseInfoResp("最後工作日不可為空",false);
		}
		if(!StringUtils.hasText(employeeId)) {
			return new CaseInfoResp("請輸入員工ID",false);
		}
		if(employeeInfo == null) {
			return new CaseInfoResp("此員工ID不存在",false);
		}
		
		newCaseInfo.setCaseNo(caseInfoReq.getCaseNo());
		newCaseInfo.setModel(caseInfoReq.getModel());
		newCaseInfo.setDuration(caseInfoReq.getDuration());
		newCaseInfo.setDate(caseInfoReq.getDate());
		newCaseInfo.setEmployeeInfo(employeeInfo);
		caseInfoDao.save(newCaseInfo);
		return new CaseInfoResp("新增成功",true);
	}

	//以caseNo獲取caseInfo
	@Override
	public CaseInfoResp getCaseInfoByCaseNo(CaseInfoReq caseInfoReq) {
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		if(!StringUtils.hasText(caseInfoReq.getCaseNo())){
			return new CaseInfoResp("請輸入案件號碼",false);
		}
		CaseInfo caseInfo = caseInfoDao.getCaseInfoByCaseNo(caseInfoReq.getCaseNo());
		if(caseInfo == null) {
			return new CaseInfoResp("無此案件號碼的caseInfo存在",false);
		}
		caseInfoResp.setCaseNo(caseInfo.getCaseNo());
		caseInfoResp.setDate(caseInfo.getDate());
		caseInfoResp.setDuration(caseInfo.getDuration());
		caseInfoResp.setEmployeeInfo(caseInfo.getEmployeeInfo());
		caseInfoResp.setModel(caseInfo.getModel());
		caseInfoResp.message = "資料獲取成功";
		caseInfoResp.success = true;
		return caseInfoResp;
	}

	//編輯caseInfo
	@Override
	public CaseInfoResp editCaseInfo(CaseInfoReq caseInfoReq) {
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		CaseInfo newCaseInfo = new CaseInfo();
		if(!StringUtils.hasText(caseInfoReq.getCaseNo())){
			return new CaseInfoResp("請輸入案件號碼",false);
		}
		CaseInfo caseInfo = caseInfoDao.getCaseInfoByCaseNo(caseInfoReq.getCaseNo());
		if(caseInfo == null) {
			return new CaseInfoResp("無此案件號碼的caseInfo存在",false);
		}
		if(!StringUtils.hasText(caseInfoReq.getModel())){
			return new CaseInfoResp("機型不可為空",false);
		}
		if(caseInfoReq.getDuration() < 0){
			return new CaseInfoResp("完工時長不可小於0",false);
		}
		if(!StringUtils.hasText(caseInfoReq.getDate())){
			return new CaseInfoResp("最後工作日不可為空",false);
		}
		if(!StringUtils.hasText(caseInfoReq.getEmployeeId())) {
			return new CaseInfoResp("請輸入員工ID",false);
		}
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(caseInfoReq.getEmployeeId());
		if(employeeInfo == null) {
			return new CaseInfoResp("此員工ID不存在",false);
		}
		
		newCaseInfo.setCaseNo(caseInfoReq.getCaseNo());
		newCaseInfo.setModel(caseInfoReq.getModel());
		newCaseInfo.setDuration(caseInfoReq.getDuration());
		newCaseInfo.setDate(caseInfoReq.getDate());
		newCaseInfo.setEmployeeInfo(employeeInfo);
		caseInfoDao.save(newCaseInfo);
		return new CaseInfoResp("資料修改成功",true);
	}

	//刪除指定caseNo的caseInfo
	@Override
	public CaseInfoResp deleteCaseInfo(CaseInfoReq caseInfoReq) {
		CaseInfoResp caseInfoResp = new CaseInfoResp();
		if(!StringUtils.hasText(caseInfoReq.getCaseNo())){
			return new CaseInfoResp("請輸入案件號碼",false);
		}
		CaseInfo caseInfo = caseInfoDao.getCaseInfoByCaseNo(caseInfoReq.getCaseNo());
		if(caseInfo == null) {
			return new CaseInfoResp("無此案件號碼的caseInfo存在",false);
		}
		caseInfoDao.delete(caseInfo);
		caseInfoResp.message = "資料刪除成功";
		caseInfoResp.success = true;
		return caseInfoResp;
	}

}
