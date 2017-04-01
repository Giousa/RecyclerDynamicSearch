package com.zmm.recyclerdynamicsearch;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/1
 * Time:下午1:11
 */

public class StudentBean {

    private String name;
    private int age;

    public StudentBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
