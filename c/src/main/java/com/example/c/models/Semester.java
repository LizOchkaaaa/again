package com.example.c.models;

import java.io.Serializable;

public enum Semester implements Serializable {
    FIRST("FIRST"),
    SECOND("SECOND"),
    THIRD("THIRD"),
    FOURTH("FOURTH");
    private String semester;

    Semester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }
}