package com.example.WorkHoursManager.services.employeeInfoService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.WorkHoursManager.entity.EmployeeInfo;
import com.example.WorkHoursManager.repository.EmployeeInfoDao;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoReq;
import com.example.WorkHoursManager.vo.employeeInfoVo.EmployeeInfoResp;

@Service
@Transactional
@Qualifier("employeeInfoService")
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
	
	@Autowired
	EmployeeInfoDao employeeInfoDao;

	//--------------------------------------新增員工資訊------------------------------------------
	@Override
	public EmployeeInfoResp setEmployeeInfo(EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		if(!StringUtils.hasText(employeeInfoReq.getEmployeeId())) {
			employeeInfoResp.message = "員工ID不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setEmployeeId(employeeInfoReq.getEmployeeId());
		
		if(!StringUtils.hasText(employeeInfoReq.getDepartment())) {
			employeeInfoResp.message = "員工部門不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setDepartment(employeeInfoReq.getDepartment());
		
		if(!StringUtils.hasText(employeeInfoReq.getEmail())) {
			employeeInfoResp.message = "員工信箱不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setEmail(employeeInfoReq.getEmail());
		
		if(!StringUtils.hasText(employeeInfoReq.getGender())) {
			employeeInfoResp.message = "員工性別不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setGender(employeeInfoReq.getGender());
		
		if(!StringUtils.hasText(employeeInfoReq.getLevel())) {
			employeeInfoResp.message = "員工職等不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setLevel(employeeInfoReq.getLevel());
		
		if(!StringUtils.hasText(employeeInfoReq.getName())) {
			employeeInfoResp.message = "員工姓名不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setName(employeeInfoReq.getName());
		
		if(!StringUtils.hasText(employeeInfoReq.getPhone())) {
			employeeInfoResp.message = "員工電話號碼不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setPhone(employeeInfoReq.getPhone());
		
		if(!StringUtils.hasText(employeeInfoReq.getPosition())) {
			employeeInfoResp.message = "員工職務不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setPosition(employeeInfoReq.getPosition());
		
		//有輸入主管ID才檢查
		if(StringUtils.hasText(employeeInfoReq.getSupervisor())) {
			EmployeeInfo supervisor = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeInfoReq.getSupervisor());
			if(supervisor == null) {
				employeeInfoResp.message = "您設定主管的員工ID不存在";
				employeeInfoResp.success = false;
				return employeeInfoResp;
			}
			if(employeeInfoReq.getSupervisor().equals(employeeInfo.getEmployeeId())) {
				employeeInfoResp.message = "不可將主管ID設為自己";
				employeeInfoResp.success = false;
				return employeeInfoResp;
			}
		}
		employeeInfo.setSupervisor(employeeInfoReq.getSupervisor());
		
		employeeInfoDao.save(employeeInfo);
		employeeInfoResp.message = "員工資訊新增成功";
		employeeInfoResp.success = true;
			
		return employeeInfoResp;
	}

	//--------------------------------------獲取全部員工資訊------------------------------------------
	@Override
	public EmployeeInfoResp getAllEmployeeInfo() {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		employeeInfoResp.setEmployeeInfoList(employeeInfoDao.findAll());
		employeeInfoResp.message = "資料獲取成功";
		employeeInfoResp.success = true;
		return employeeInfoResp;
	}
	
	@Override
	public EmployeeInfoResp getEmployeeInfoById(EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		if(!StringUtils.hasText(employeeInfoReq.getEmployeeId())) {
			employeeInfoResp.message = "員工ID不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeInfoReq.getEmployeeId());
		if(employeeInfo == null) {
			employeeInfoResp.message = "無此ID的員工";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfoResp.setEmployeeId(employeeInfo.getEmployeeId());
		employeeInfoResp.setDepartment(employeeInfo.getDepartment());
		employeeInfoResp.setEmail(employeeInfo.getEmail());
		employeeInfoResp.setGender(employeeInfo.getGender());
		employeeInfoResp.setLevel(employeeInfo.getLevel());
		employeeInfoResp.setName(employeeInfo.getName());
		employeeInfoResp.setPhone(employeeInfo.getPhone());
		employeeInfoResp.setPosition(employeeInfo.getPosition());
		employeeInfoResp.setSupervisor(employeeInfo.getSupervisor());
		employeeInfoResp.message = "資料獲取成功";
		employeeInfoResp.success = true;
		return employeeInfoResp;
	}

	//--------------------------------------獲取指定ID員工資訊------------------------------------------
	@Override
	public EmployeeInfoResp editEmployeeInfo(EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		if(!StringUtils.hasText(employeeInfoReq.getEmployeeId())) {
			employeeInfoResp.message = "員工ID不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeInfoReq.getEmployeeId());
		if(employeeInfo == null) {
			employeeInfoResp.message = "無此ID的員工";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		
		if(!StringUtils.hasText(employeeInfoReq.getDepartment())) {
			employeeInfoResp.message = "員工部門不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setDepartment(employeeInfoReq.getDepartment());
		
		if(!StringUtils.hasText(employeeInfoReq.getEmail())) {
			employeeInfoResp.message = "員工信箱不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setEmail(employeeInfoReq.getEmail());
		
		if(!StringUtils.hasText(employeeInfoReq.getGender())) {
			employeeInfoResp.message = "員工性別不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setGender(employeeInfoReq.getGender());
		
		if(!StringUtils.hasText(employeeInfoReq.getLevel())) {
			employeeInfoResp.message = "員工職等不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setLevel(employeeInfoReq.getLevel());
		
		if(!StringUtils.hasText(employeeInfoReq.getName())) {
			employeeInfoResp.message = "員工姓名不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setName(employeeInfoReq.getName());
		
		if(!StringUtils.hasText(employeeInfoReq.getPhone())) {
			employeeInfoResp.message = "員工電話號碼不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setPhone(employeeInfoReq.getPhone());
		
		if(!StringUtils.hasText(employeeInfoReq.getPosition())) {
			employeeInfoResp.message = "員工職務不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		employeeInfo.setPosition(employeeInfoReq.getPosition());
		
		//有輸入主管ID才檢查
		if(StringUtils.hasText(employeeInfoReq.getSupervisor())) {
			EmployeeInfo supervisor = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeInfoReq.getSupervisor());
			if(supervisor == null) {
				employeeInfoResp.message = "您設定主管的員工ID不存在";
				employeeInfoResp.success = false;
				return employeeInfoResp;
			}
			if(employeeInfoReq.getSupervisor().equals(employeeInfo.getEmployeeId())) {
				employeeInfoResp.message = "不可將主管ID設為自己";
				employeeInfoResp.success = false;
				return employeeInfoResp;
			}
		}
		employeeInfo.setSupervisor(employeeInfoReq.getSupervisor());
		employeeInfoDao.save(employeeInfo);
		employeeInfoResp.message = "員工資訊修改成功";
		employeeInfoResp.success = true;
			
		return employeeInfoResp;
	}

	//--------------------------------------刪除員工資訊(連同帳號)------------------------------------------
	@Override
	public EmployeeInfoResp deleteEmployeeInfo(EmployeeInfoReq employeeInfoReq) {
		EmployeeInfoResp employeeInfoResp = new EmployeeInfoResp();
		if(!StringUtils.hasText(employeeInfoReq.getEmployeeId())) {
			employeeInfoResp.message = "員工ID不得空白";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		
		EmployeeInfo employeeInfo = employeeInfoDao.getEmployeeInfoByEmployeeId(employeeInfoReq.getEmployeeId());
		if(employeeInfo == null) {
			employeeInfoResp.message = "無此ID的員工";
			employeeInfoResp.success = false;
			return employeeInfoResp;
		}
		
		//把員工的主管.工時資料(員工離職但工作資料保留)關聯清空,防止關聯同步而刪除主管的資料
		employeeInfo.setSupervisor(null);
		employeeInfo.setWorkDayInfo(null);
		employeeInfo.setWorkHoursInfo(null);
		//Account則會隨員工資料一併被刪除
		
		employeeInfoDao.delete(employeeInfo);
		if(employeeInfoDao.getEmployeeInfoByEmployeeId(employeeInfoReq.getEmployeeId()) == null) {
			employeeInfoResp.message = "員工資訊刪除成功";
			employeeInfoResp.success = true;
		}
		return employeeInfoResp;
	}

}
