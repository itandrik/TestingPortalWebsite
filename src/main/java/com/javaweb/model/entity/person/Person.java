package com.javaweb.model.entity.person;

public class Person {
	private long id;
	private String firstName;
	private String secondName;
	private Gender gender;
	private String login;
	private String password;
	private PersonRole role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secodName) {
		this.secondName = secodName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonRole getRole() {
		return role;
	}

	public void setRole(PersonRole role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		
		Person other = (Person) obj;
		
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (role != other.role)
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}

	public static class Builder {
		private Person person = new Person();

		public Builder setId(long id) {
			person.setId(id);
			return this;
		}

		public Builder setFirstName(String firstName) {
			person.setFirstName(firstName);
			return this;
		}

		public Builder setSecondName(String secondName) {
			person.setSecondName(secondName);
			return this;
		}

		public Builder setGender(Gender gender) {
			person.setGender(gender);
			return this;
		}

		public Builder setLogin(String login) {
			person.setLogin(login);
			return this;
		}

		public Builder setPassword(String password) {
			person.setPassword(password);
			return this;
		}
		
		public Builder setRole(PersonRole role){
			person.setRole(role);
			return this;
		}
		
		public Person build(){
			return person;
		}
	}
}
