package com.example.c.Fields;

import com.example.c.Object.Consolee;
import com.example.c.Object.TypeOfArgument;
import main.org.example.models.*;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class FieldsGetter {
    private final Consolee console;
    private final ZonedDateTime birthday = null;

    public FieldsGetter(Consolee aConsole) {
        console = aConsole;
    }

    /**
     * @return null - if end of script or empty String
     */
    private Object workWithTypes(String line, TypeOfArgument type, boolean minExist, boolean nullAvailable) {

        try {
            switch (type) {
                case INTEGER: {
                    if (line != null) Integer.parseInt(line);
                    else if (nullAvailable) return null;
                    break;
                }
                case DOUBLE: {
                    if (line != null) Double.parseDouble(line);
                    else if (nullAvailable) return null;
                    break;
                }
                case STRING: {
                    if (line != null) return line;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            line = null;
        }

        if (line != null) {
            switch (type) {

                case INTEGER: {
                    if (!minExist || Integer.parseInt(line) > 0) return Integer.parseInt(line);
                    break;
                }
                case DOUBLE: {
                    if (!minExist || Double.parseDouble(line) > 0) return Double.parseDouble(line);
                    break;
                }
            }
        }

        return null;
    }

    private String getUniversalRequest(String requestField, String options, Consolee console, boolean nullAvailable) throws IOException {

        do {
            StringBuilder sb = new StringBuilder();
            sb.append("\t").append(requestField).
                    append(" should be ").append(options).
                    append("!\n");
            sb.append("Enter ").append(requestField).append(" again: ");
            console.print(sb, true);

            String line;

            line = console.read();

            if (line == null && console.getExeStatus()) return null;
            if (line != null || nullAvailable) return line;
        } while (true);
    }

    private Object getGeneralRequest(String requestField, String options,
                                     Consolee console, boolean minExist, boolean nullAvailable, TypeOfArgument type) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------\n");
        sb.append(requestField.toUpperCase()).append("\n");
        sb.append("-------------------\n\n");
        if (nullAvailable) sb.append("\t").append("\t(You can skip this field)\n\n");
        sb.append("Enter ").append(requestField).append(": ");
        console.print(sb, true);

        String line;
        try {
            line = console.read();
        } catch (IOException exception) {
            return null;
        }

        if (line == null) return null;
        else if (line.trim().equals("")) line = null;

        Object firstRequest = workWithTypes(line, type, minExist, nullAvailable);

        if (firstRequest != null || (nullAvailable && line == null)) return firstRequest;

        do {

            try {
                line = getUniversalRequest(requestField, options, console, nullAvailable);
            } catch (IOException exception) {
                return null;
            }

            if (line == null) return null;
            else if (line.trim().equals("")) line = null;

            Object request = workWithTypes(line, type, minExist, nullAvailable);

            if (request != null || (nullAvailable && line == null)) return request;

        } while (true);
    }
    private String getFirstEnumRequest(String requestField, String enumerateList, Consolee console) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------\n");
        sb.append(requestField.toUpperCase()).append("\n");
        sb.append("-------------------\n\n");
        sb.append("Available ").append(requestField).append(":\n");
        sb.append("\t").append(enumerateList);
        sb.append("\n\nEnter ").append(requestField).append(": ");
        console.print(sb, true);

        try {
            return console.read();
        } catch (IOException exception) {
            return null;
        }
    }

    private String getUniversalEnumRequest(String requestField, Consolee console) {

        StringBuilder sb = new StringBuilder();
        sb.append("\tIt's incorrect ").
                append(requestField).append("!");
        sb.append("\nEnter ").append(requestField).append(" again: ");
        console.print(sb, true);

        try {
            return console.read();
        } catch (IOException exception) {
            return null;
        }
    }
    public String getName() {

        return (String) getGeneralRequest("group name", "not null and not empty string",
                console, false, false, TypeOfArgument.STRING);
    }

    public Coordinates getCoordinates() {

        console.print("\n-------------------\n" +
                "GROUP'S COORDINATES\n" +
                "-------------------\n\n", true);

        Double x = (Double) getGeneralRequest("x coordinate",
                "not null int number", console, false, false, TypeOfArgument.DOUBLE);
        if (x == null) return null;

        Integer y = (Integer) getGeneralRequest("y coordinate", "not null Double number", console,
                false, false, TypeOfArgument.INTEGER);
        if (y == null) return null;

        return new Coordinates(x, y);
    }
    public Integer getStudentsCount() {

        return (Integer) getGeneralRequest("group student count",
                "not null positive Integer number", console, true, false, TypeOfArgument.INTEGER);
    }

    public FormOfEducation getFormOfEducation() {

        String line = getFirstEnumRequest("form of education", Arrays.toString(FormOfEducation.values())
                + ("\n\tYou can write form of education in lower case!")
                + ("\n\n\t(You can skip this field)"), console);
        if (line == null || line.trim().equals("")) return null;

        while (true) {

            if (line != null && line.trim().equals("")) line = null;
            if (line == null) return null;

            if (FormOfEducation.isIncludeElement(line.toUpperCase()))
                return FormOfEducation.valueOf(line.toUpperCase());

            line = getUniversalEnumRequest("form of education", console);
        }
    }

    public Semester getSemester() {

        String line = getFirstEnumRequest("group semester", Arrays.toString(Semester.values())
                + ("\n\tYou can write form of education in lower case!")
                + ("\n-----"), console);
        if (line == null) return null;

        while (line == null || !Semester.isIncludeElement(line.toUpperCase())) {

            line = getUniversalEnumRequest("semester", console);

            if (line == null) return null;
            if (line.trim().equals("")) line = null;
        }
        return Semester.valueOf(line.toUpperCase());
    }
    public Person getGroupAdmin() {

        console.print("\n-------------------\n" +
                ("\tGROUP ADMIN\n") +
                "-------------------\n\n", true);

        String name = (String) getGeneralRequest("group's admin name", "not null and not empty string",
                console, false, false, TypeOfArgument.STRING);

        if (name == null) return null;
        ZonedDateTime birthday = (ZonedDateTime) getBirtdayReguest();

        Integer weight = (Integer) getGeneralRequest("group admin weight",
                "not null positive integer number", console, true, false, TypeOfArgument.INTEGER);

        if (weight == null) return null;

        String passport = (String) getGeneralRequest("groups admin passport", "not null and not empty string",
        console, false, false, TypeOfArgument.STRING);

        String line = getFirstEnumRequest("group admin hair color", Arrays.toString(Color.values())
                + ("\n\tYou can write form of education in lower case!")
                + ("\n-----"), console);

        if (line == null) return null;

        while (line == null || !Color.isIncludeElement(line.toUpperCase())) {

            line = getUniversalEnumRequest("group admin hair color", console);

            if (line == null) return null;
            if (line.trim().equals("")) line = null;
        }

        Color hairColor = Color.valueOf(line.toUpperCase());

        return new Person(name, birthday ,  weight, passport ,  hairColor);
    }

    private Object getBirtdayReguest() {
        return birthday;
    }


}
