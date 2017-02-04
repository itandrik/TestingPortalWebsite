package com.javaweb.model.entity;

public class Test {
    private int id;
    private String nameOfTest;
    private int durationTimeInMinutes;
    private int subjectId;

    public int getId() {
        return id;
    }

    public String getNameOfTest() {
        return nameOfTest;
    }

    public int getDurationTimeInMinutes() {
        return durationTimeInMinutes;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameOfTest(String nameOfTest) {
        this.nameOfTest = nameOfTest;
    }

    public void setDurationTimeInMinutes(int durationTimeInMinutes) {
        this.durationTimeInMinutes = durationTimeInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        if (durationTimeInMinutes != test.durationTimeInMinutes) return false;
        if (subjectId != test.subjectId) return false;
        return nameOfTest.equals(test.nameOfTest);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nameOfTest.hashCode();
        result = 31 * result + durationTimeInMinutes;
        result = 31 * result + subjectId;
        return result;
    }

    public static class Builder {
        private Test test = new Test();

        public Builder setId(int id) {
            test.setId(id);
            return this;
        }

        public Builder setName(String name) {
            test.setNameOfTest(name);
            return this;
        }

        public Builder setSubjectId(int subjectId) {
            test.setSubjectId(subjectId);
            return this;
        }

        public Builder setDurationTimeInMinutes(int durationTimeInMinutes) {
            test.setDurationTimeInMinutes(durationTimeInMinutes);
            return this;
        }

        public Test build() {
            return test;
        }
    }
}
