package com.example.mybook.entity;

import java.util.List;

public class Window {
    String name;
    int number;
    List<Fruit> list;

    public List<Fruit> getList() {
        return list;
    }

    public void setList(List<Fruit> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
