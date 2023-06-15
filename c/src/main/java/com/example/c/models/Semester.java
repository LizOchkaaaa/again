package com.example.c.models;

import java.io.Serializable;
import java.util.Arrays;

public enum Semester implements Serializable {
    FIRST("FIRST" , "first"),
    SECOND("SECOND", "second"),
    THIRD("THIRD", "third"),
    FOURTH("FOURTH", "fourth");
    private final String stringInUpperCaseRepresentation;
    private final String stringInLowerCaseRepresentation;

    Semester(String aStringInUpperCaseRepresentation, String aStringInLowerCaseRepresentation) {
        stringInUpperCaseRepresentation = aStringInUpperCaseRepresentation;
        stringInLowerCaseRepresentation = aStringInLowerCaseRepresentation;
    }

    private String getStringInLowerCaseRepresentation() {
        return stringInLowerCaseRepresentation;
    }

    private String getStringInUpperCaseRepresentation() {
        return stringInUpperCaseRepresentation;
    }

    public static boolean isIncludeElement(String aSemester) {

        return Arrays.stream(Semester.values()).anyMatch(semester ->
                aSemester.equals(semester.getStringInLowerCaseRepresentation()) ||
                        aSemester.equals(semester.getStringInUpperCaseRepresentation())
        );
    }
}