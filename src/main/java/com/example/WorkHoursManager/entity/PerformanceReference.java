package com.example.WorkHoursManager.entity;

import javax.persistence.*;

@Entity
@Table(name = "performance_reference")
public class PerformanceReference {
	
	@Id
	@Column(name = "model")
	private String model;
	
	@Column(name = "rating")
	private String rating;

	
	//Getter & Setter

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
