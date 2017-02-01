package com.javaweb.model.entity;

public class Subject {
	private int id;
	private String nameOfSubject;

	public Subject() {
	}

	public Subject(int id, String nameOfSubject) {
		super();
		this.id = id;
		this.nameOfSubject = nameOfSubject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameOfSubject() {
		return nameOfSubject;
	}

	public void setNameOfSubject(String nameOfSubject) {
		this.nameOfSubject = nameOfSubject;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;

		Subject subject = (Subject) o;

		if (id != subject.id) return false;
		return nameOfSubject != null ? nameOfSubject.equals(subject.nameOfSubject) : subject.nameOfSubject == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (nameOfSubject != null ? nameOfSubject.hashCode() : 0);
		return result;
	}

	public static class Builder{
		private Subject subject = new Subject();

		public Builder setId(int id) {
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
