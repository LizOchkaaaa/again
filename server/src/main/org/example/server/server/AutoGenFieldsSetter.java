package main.org.example.server.server;


import org.example.main.Request;
import org.example.models.StudyGroup;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

/**Sets the collections of the author and the date**/
public class AutoGenFieldsSetter {

    public static Request setFields(Request aRequest) {
        StudyGroup studyGroup = aRequest.getCommand().getStudyGroup();
        String author = aRequest.getSession().getName();

        if (studyGroup != null) {
            studyGroup.setCreationDate(Date.from(Instant.now()));
            studyGroup.setAuthor(author);
        }
        return aRequest;
    }
}
