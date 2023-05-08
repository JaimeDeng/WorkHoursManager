package com.example.WorkHoursManager.vo.performanceReferenceVo;

import java.util.List;

import com.example.WorkHoursManager.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PerformanceReferenceResp extends PerformanceReference {

	public String message;
	
	public boolean success;
	
	private List<PerformanceReference>performanceReferenceList;
	
	//Getter & Setter
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public List<PerformanceReference> getPerformanceReferenceList() {
		return performanceReferenceList;
	}

	public void setPerformanceReferenceList(List<PerformanceReference> performanceReferenceList) {
		this.performanceReferenceList = performanceReferenceList;
	}
	

	//Constructor

	public PerformanceReferenceResp(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	public PerformanceReferenceResp() {
	}
}
