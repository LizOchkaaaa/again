package com.example.c.models;

import java.io.Serializable;
import java.util.Arrays;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("DISTANCE_EDUCATION", "distance_education"),
    FULL_TIME_EDUCATION("FULL_TIME_EDUCATION", "full_time_education"),
    EVENING_CLASSES("EVENING_CLASSES", "evening_classes");

    private final String stringInUpperCaseRepresentation;
    private final String stringInLowerCaseRepresentation;

    FormOfEducation(String stringInUpperCaseRepresentation, String stringInLowerCaseRepresentation) {
        this.stringInUpperCaseRepresentation = stringInUpperCaseRepresentation;
        this.stringInLowerCaseRepresentation = stringInLowerCaseRepresentation;
    }

    private String getStringInLowerCaseRepresentation() {
        return stringInLowerCaseRepresentation;
    }

    private String getStringInUpperCaseRepresentation() {
        return stringInUpperCaseRepresentation;
    }

    public static boolean isIncludeElement(String aFormOfEducation) {
        return Arrays.stream(FormOfEducation.values()).anyMatch(formOfEducation ->
                aFormOfEducation.equals(formOfEducation.getStringInLowerCaseRepresentation()) ||
                        aFormOfEducation.equals(formOfEducation.getStringInUpperCaseRepresentation())
        );
    }
}
