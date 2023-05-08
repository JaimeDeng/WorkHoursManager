package com.example.WorkHoursManager.vo.caseInfoVo;

import java.util.List;

import com.example.WorkHoursManager.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseInfoResp extends CaseInfo {
	
	public String message;
	
	public boolean success;
	
	private List<CaseInfo>caseInfoList;
	
	//Getter & Setter
	public List<CaseInfo> getCaseInfoList() {
		return caseInfoList;
	}

	public void setCaseInfoList(List<CaseInfo> caseInfoList) {
		this.caseInfoList = caseInfoList;
	}	
	
	//Constructor
	
	public CaseInfoResp(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public CaseInfoResp() {
	}
}