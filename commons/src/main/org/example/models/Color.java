package main.org.example.models;

import java.io.Serializable;
import java.util.Arrays;

public enum Color implements Serializable {

    GREEN("GREEN" , "green"),
    RED("RED" , "red"),
    ORANGE("ORANGE" , "orange"),
    WHITE("WHITE", "white");
    private final String stringInUpperCaseRepresentation;
    private final String stringInLowerCaseRepresentation;

    Color(String aStringInUpperCaseRepresentation, String aStringInLowerCaseRepresentation) {
        stringInUpperCaseRepresentation = aStringInUpperCaseRepresentation;
        stringInLowerCaseRepresentation = aStringInLowerCaseRepresentation;
    }

    private String getStringInLowerCaseRepresentation() {
        return stringInLowerCaseRepresentation;
    }

    private String getStringInUpperCaseRepresentation() {
        return stringInUpperCaseRepresentation;
    }

    public static boolean isIncludeElement(String aColor) {

        return Arrays.stream(Color.values()).anyMatch(color -> aColor.equals(
                color.getStringInLowerCaseRepresentation()) ||
                aColor.equals(color.getStringInUpperCaseRepresentation())
        );
    }

    @Override
    public String toString() {
        return getStringInUpperCaseRepresentation();
    }
}
