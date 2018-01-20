package com.pluralsight.model;

import sun.jvm.hotspot.debugger.DataSource;

import java.util.Date;

//Model for the Ride object
public class Ride {

	private Integer id;
	private String name;
	private int duration;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
