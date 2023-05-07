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

import com.example.WorkHoursManager.entity.PerformanceReference;
import com.example.WorkHoursManager.services.performanceReferenceService.PerformanceReferenceService;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceReq;
import com.example.WorkHoursManager.vo.performanceReferenceVo.PerformanceReferenceResp;

@RestController
@CrossOrigin
public class PerformanceReferenceController {
	@Autowired
	private PerformanceReferenceService performanceReferenceService;
	
	//Read（読み取り）
	@PostMapping("prallInfo")
	public List<PerformanceReference>getInfos(){
		return performanceReferenceService.getAllInfos();
	}
	
	//Create（生成）
	@PostMapping("pradd")
	public PerformanceReferenceResp performanceReference(@RequestBody PerformanceReferenceReq performanceReferenceReq) {
		return performanceReferenceService.addInfo(performanceReferenceReq);
	}
	
	//Delete（削除）
//	@DeleteMapping("/pr/{id}")
//	public ResponseEntity<PerformanceReferenceResp> deleteInfo(@PathVariable("id") String caseNo){
//	    PerformanceReferenceResp result = performanceReferenceService.deleteInfo(caseNo);
//	    if (result.isSuccess()) {
//	        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
//	    } else {
//	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//	    }
//    }
	
	//Update(更新)
//	@PutMapping("/pr/{id}")
//	public ResponseEntity<PerformanceReferenceResp> updeteInfo(@PathVariable("id") String caseNo,@RequestBody PerformanceReferenceReq performanceReferenceReq){
//	    PerformanceReferenceResp result = performanceReferenceService.updateInfo(caseNo, performanceReferenceReq);
//	    if (result.isSuccess()) {
//	        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
//	    } else {
//	        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//	    }
//    }
}
