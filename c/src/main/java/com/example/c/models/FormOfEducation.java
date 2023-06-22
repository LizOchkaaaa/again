package com.example.c.models;

import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("DISTANCE_EDUCATION"),
    FULL_TIME_EDUCATION("FULL_TIME_EDUCATION"),
    EVENING_CLASSES("EVENING_CLASSES");
    private String form;

    FormOfEducation(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }
}
