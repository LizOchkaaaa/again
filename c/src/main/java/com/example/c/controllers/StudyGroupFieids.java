package com.example.c.controllers;

public enum StudyGroupFieids {
    ID("id"), NAME("name"), COORDINATEX("coordinateX"), COORDINATEY("coordinateY"), DATE("date") ,  COUNT("count"), FORM("form") , SEMESTER("semester") ,ADMINNAME("AdminName") ,
    ADMINBIRTHDAY("AdminBirthday"), ADMINWEIGHT("AdminWeight") , ADMINPASSPORT("AdminPassport") , COLOR("AdminColor") , USER("author");

    private String field;

    StudyGroupFieids(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    @Deprecated
    public static StudyGroupFieids getFieldByNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            for (StudyGroupFieids fields : StudyGroupFieids.values()) {
                if (fields.ordinal() + 1 == num) {
                    return fields;
                }
            }
        } catch (NumberFormatException ignored){}
        return null;
    }

    public static String[] getAll() {
        String[] enums = new String[10];
        for(StudyGroupFieids fields: StudyGroupFieids.values()) {
            enums[fields.ordinal()] = fields.getField();
        }
        return enums;
    }
}
