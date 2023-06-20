package org.example.models;

import java.io.Serializable;
import java.util.Arrays;

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