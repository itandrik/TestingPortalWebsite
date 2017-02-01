package com.javaweb.model.entity.history;

import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;

import java.sql.Timestamp;

/**
 * @author Andrii Chernysh on 31-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class PersonHistory {
    private Person person;
    private Test test;
    private Timestamp endTime;
    private Grade grade;

    public Person getPerson() {
        return person;
    }

    public Test getTest() {
        return test;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonHistory)) return false;

        PersonHistory that = (PersonHistory) o;

        if (!person.equals(that.person)) return false;
        if (!test.equals(that.test)) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        return grade == that.grade;
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + test.hashCode();
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }

    public static class Builder{
        private PersonHistory personHistory = new PersonHistory();

        public Builder setPerson(Person person) {
            personHistory.setPerson(person);
            return this;
        }

        public Builder setTest(Test test) {
            personHistory.setTest(test);
            return this;
        }

        public Builder setEndTime(Timestamp endTime) {
            personHistory.setEndTime(endTime);
            return this;
        }

        public Builder setGrade(Grade grade) {
            personHistory.setGrade(grade);
            return this;
        }

        public PersonHistory build() {
            return personHistory;
        }
    }
}
