package com.javaweb.model.entity;

public class Test {
    private int id;
    private String nameOfTest;
    private int durationTimeInMinutes;

    public int getId() {
        return id;
    }

    public String getNameOfTest() {
        return nameOfTest;
    }

    public int getDurationTimeInMinutes() {
        return durationTimeInMinutes;
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

        public Builder setDurationTimeInMinutes(int durationTimeInMinutes) {
            test.setDurationTimeInMinutes(durationTimeInMinutes);
            return this;
        }

        public Test build() {
            return test;
        }
    }
}
