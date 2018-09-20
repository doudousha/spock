package com.wangqing.springbootspock.bean;

import org.hibernate.validator.constraints.NotEmpty;


public class Student {

    @NotEmpty(message = "用户名不能为空")
    private String name ;
    @NotEmpty(message = "性别不能为空")
    private String gender ;
    private int age ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
