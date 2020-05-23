package com.example.hiot_cloud.test.networktest;

import java.io.Serializable;

/**
 * 实验实体类
 */
public class Experiment implements Serializable {
    /**
     * 是否毕业
     */
    private boolean graduation;
    /**
     * 学生id
     */
    private int id;
    /**
     * 学生身高
     */
    private int height;

    public boolean isGraduation() {
        return graduation;
    }

    public void setGraduation(boolean graduation) {
        this.graduation = graduation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 学生姓名
     */
    private String name;
}
