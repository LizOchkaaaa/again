package com.example.c;

import main.org.example.models.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class StudyGroupFactory {
    private final FieldsReceiver fieldsReceiver;

    public StudyGroupFactory() {
        fieldsReceiver = (FieldsReceiver) new FieldsGetter(Consolee.getInstance());
    }

    public StudyGroup createStudyGroup(ArrayList<String> args) {
        String name = args.get(0);
        String[] coordinatesvalues = {args.get(1), args.get(2)};
        String[] person = {args.get(6), args.get(7), args.get(8), args.get(9), args.get(10)};
        Integer countStudent = Integer.parseInt(args.get(3));
        FormOfEducation formOfEducation = FormOfEducation.valueOf(args.get(4));
//        Coordinates coordinates = new Coordinates().createCoordinates(coordinatesvalues);
//        Person newPerson = new PersonFactory().createPerson(person);

        Semester semester = null;
        if (!args.get(5).equals("")) {
            semester = Semester.valueOf(args.get(5));
        }

//        StudyGroup newStudyGroup = new StudyGroup(0 , name ,newPerson, semester, formOfEducation, coordinates, countStudent);
//        return newStudyGroup;
        return null;
    }


//    public StudyGroup createGUIStudyGroup(AddDetailsModel model) {
//        String name = model.getName();
//        Double coordinateX = Double.parseDouble(model.getXCoordinate());
//        Integer coordinateY = Integer.parseInt(model.getYCoordinate());
//        int studentsCount = Integer.parseInt(model.getStudentsCount());
//        FormOfEducation formOfEducation = FormOfEducation.valueOf(model.getFormOfEducation());
//        Semester semester = Semester.valueOf(model.getSemester());
//        String adminName = model.getGroupAdminName();
//        Integer adminWeight = Integer.parseInt(model.getGroupAdminWeight());
//        ZonedDateTime birthday = model.ge();
//        String passportId = model.getGroupAdminPassportId();
//        Color adminColor = Color.valueOf(model.getGroupAdminHairColor());

//        return new StudyGroup(0,
//                name,
//                new Coordinates(coordinateX, coordinateY),
//                null,
//                studentsCount,
//                formOfEducation,
//                semester,
//                new Person(adminName, adminWeight, birthday , passportId, adminColor));
//    }

    public StudyGroup createScriptStudyGroup() {

        String name = fieldsReceiver.getName();
        Coordinates coordinates = fieldsReceiver.getCoordinates();
        Integer studentsCount = fieldsReceiver.getStudentsCount();
        FormOfEducation formOfEducation = fieldsReceiver.getFormOfEducation();
        Semester semester = fieldsReceiver.getSemester();
        Person groupAdmin = fieldsReceiver.getGroupAdmin();

        return new StudyGroup(0 , name , groupAdmin , semester , formOfEducation , coordinates , studentsCount);
    }
}
