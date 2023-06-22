package com.example.c.models;

import java.io.Serializable;

public enum Color implements Serializable {
    GREEN("GREEN"),
    RED("RED"),
    ORANGE("ORANGE"),
    WHITE("WHITE");
    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
