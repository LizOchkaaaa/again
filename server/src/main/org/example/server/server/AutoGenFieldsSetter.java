package main.org.example.server.server;


import org.example.main.Request;
import org.example.models.StudyGroup;

import java.time.ZonedDateTime;

/**Sets the collections of the author and the date**/
public class AutoGenFieldsSetter {

    public static Request setFields(Request aRequest) {
        StudyGroup studyGroup = aRequest.getCommand().getStudyGroup();
        String author = aRequest.getSession().getName();

        if (studyGroup != null) {
            studyGroup.setCreationDate(ZonedDateTime.now());
            studyGroup.setAuthor(author);
        }
        return aRequest;
    }
}
