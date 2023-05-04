package com.example.WorkHoursManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("allInfo")
	public List<WorkHoursInfo>getInfos(){
		return workHoursService.getAllInfos();
	}
	
	@PostMapping("addInfo")
	public WorkHoursInfoResp workHoursInfo(@RequestBody WorkHoursInfoReq workHoursInfoReq) {
		return workHoursService.addinfo(workHoursInfoReq);
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteWorkHoursInfo(@PathVariable("id") int workInfoId) {
//        boolean isDeleted = workHoursService.deleteInfo(workInfoId);
//        if (isDeleted) {
//            return ResponseEntity.ok("Work hours info with ID " + workInfoId + " is deleted successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to delete work hours info with ID " + workInfoId + ".");
//        }
//    }
}