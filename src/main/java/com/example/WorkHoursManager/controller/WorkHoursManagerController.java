package com.example.WorkHoursManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WorkHoursManager.entity.WorkHoursInfo;
import com.example.WorkHoursManager.services.workHoursService.WorkHoursService;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoReq;
import com.example.WorkHoursManager.vo.workHoursInfoVo.WorkHoursInfoResp;

@RestController
@CrossOrigin
public class WorkHoursManagerController {
	
	@Autowired
	private WorkHoursService workHoursService;
	
	//Read（読み取り）
	@PostMapping("allInfo")
	public List<WorkHoursInfo>getInfos(){
		return workHoursService.getAllInfos();
	}
	
	//Create（生成）
	@PostMapping("addInfo")
	public WorkHoursInfoResp workHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		WorkHoursInfoResp workHoursInfoResp=new WorkHoursInfoResp();
		workHoursInfoResp= workHoursService.addinfo(workHoursInfoReq);
		return workHoursInfoResp;
	}
	
	//Delete（削除）
	@DeleteMapping("/api/{id}")
	public ResponseEntity<WorkHoursInfoResp> deleteInfo(@PathVariable("id") int workInfoId){
	    WorkHoursInfoResp result = workHoursService.deleteInfo(workInfoId);
	    if (result.isSuccess()) {
	        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	    }
    }
	
	//Update(更新)
	@PutMapping("/api/{id}")
	public ResponseEntity<WorkHoursInfoResp> updateInfo(@PathVariable("id") int workInfoId, @RequestBody WorkHoursInfoReq workHoursInfoReq) {
	    WorkHoursInfoResp result = workHoursService.updateInfo(workInfoId, workHoursInfoReq);

	    if (result.isSuccess()) {
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	    }
	}


}