package com.example.WorkHoursManager.services.cacsinfoSevice.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.CaseInfo;
import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.repository.CaseInfoDao;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.services.caseInfoService.CaseInfoService;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoReq;
import com.example.WorkHoursManager.vo.caseInfoVo.CaseInfoResp;

@Service
public class CaseInfoServiceImp implements CaseInfoService{
	
	@Autowired
	CaseInfoDao caseInfoDao;
	@Autowired
	private EmployeeInfoDao employeeInfoDao;
	
	//Read（読み取り）
	@Override
	public List<CaseInfo> getAllInfos() {
		return caseInfoDao.findAll();
	}
	
	//Create（生成）
	@Override
	public CaseInfoResp addInfo(CaseInfoReq caseInfoReq) {
		CaseInfo newCaseInfo=new CaseInfo();
		String employeeId=caseInfoReq.getEmployeeId();
		EmployeeInfo employeeInfo=employeeInfoDao.getEmployeeInfoByEmployeeId(employeeId);
		
		//判斷有無資料
			if(!StringUtils.hasText(caseInfoReq.getCaseNo())){
				return new CaseInfoResp("案件號碼不可為空",false);
			}
			if(!StringUtils.hasText(caseInfoReq.getModel())){
				return new CaseInfoResp("機型不可為空",false);
			}
			if(!StringUtils.hasText(caseInfoReq.getDuration())){
				return new CaseInfoResp("完工時長不可為空",false);
			}
			if(!StringUtils.hasText(caseInfoReq.getDate())){
				return new CaseInfoResp("最後工作日不可為空",false);
			}
			if(employeeId==null) {
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

}
