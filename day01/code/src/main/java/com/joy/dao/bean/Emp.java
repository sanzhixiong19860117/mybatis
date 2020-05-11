package com.joy.dao.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/11 19:27
 */
public class Emp {
    private int id;
    private String color;

    public Emp(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", color='" + color + '\'' +
                '}';
    }
}
