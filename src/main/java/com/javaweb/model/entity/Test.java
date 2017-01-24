package com.javaweb.model.entity;

import java.sql.Time;

public class Test {
	private long id;
	private String name;
	private int durationTimeInMinutes;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getDurationTimeInMinutes() {
		return durationTimeInMinutes;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDurationTimeInMinutes(int durationTimeInMinutes) {
		this.durationTimeInMinutes = durationTimeInMinutes;
	}
	
	public static class Builder{
		private Test test = new Test();
		
		public Builder setId(long id){
			test.setId(id);
			return this;
		}
		
		public Builder setName(String name){
			test.setName(name);
			return this;
		}
		
		public Builder setDurationTimeInMinutes(int durationTimeInMinutes){
			test.setDurationTimeInMinutes(durationTimeInMinutes);
			return this;
		}
		
		public Test build(){
			return test;
		}
	}
}
