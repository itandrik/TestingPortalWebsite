package com.javaweb.model.entity;

public class Subject {
	private long id;
	private String nameOfSubject;

	public Subject() {
	}

	public Subject(long id, String nameOfSubject) {
		super();
		this.id = id;
		this.nameOfSubject = nameOfSubject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameOfSubject() {
		return nameOfSubject;
	}

	public void setNameOfSubject(String nameOfSubject) {
		this.nameOfSubject = nameOfSubject;
	}

	public static class Builder{
		private Subject subject = new Subject();

		public Builder setId(long id) {
			subject.setId(id);
			return this;
		}

		public Builder setName(String nameOfSubject) {
			subject.setNameOfSubject(nameOfSubject);
			return this;
		}

		public Subject build(){
			return subject;
		}
	}
}
