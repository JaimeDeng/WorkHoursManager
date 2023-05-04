package com.example.WorkHoursManager.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "performance_reference")
public class PerformanceReference {
	
	@Id
	@Column(name = "case_no")
	private String caseNo;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "rating")
	private String rating;

//==============================================================
	//WorkHoursInfo的case_no外鍵關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "caseInfo")
	private List<WorkHoursInfo> workHoursInfo;
	
	//Getter & Setter

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<WorkHoursInfo> getWorkHoursInfo() {
		return workHoursInfo;
	}

	public void setWorkHoursInfo(List<WorkHoursInfo> workHoursInfo) {
		this.workHoursInfo = workHoursInfo;
	}
	
	
}
