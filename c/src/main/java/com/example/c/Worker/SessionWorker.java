package com.example.c.Worker;

import com.example.c.InputClireader;
import com.example.c.OutStream;
import main.org.example.main.Session;
import main.org.example.main.TypeOfSession;

import java.io.IOException;
import java.util.regex.Pattern;

/**Class for registration **/
public class SessionWorker {
    private final OutStream outStream;

    public SessionWorker(OutStream aoutStream) {
        outStream = aoutStream;
    }

    public Session getSession() throws IOException {
        boolean sessionStatus = getSessionStatus();
        return sessionStatus ? new Session(getUsername(), getUserPassword(), TypeOfSession.Login)
                : new Session(getUsername(), getUserPassword(), TypeOfSession.Register);
    }

    private boolean getSessionStatus(){
        String answer;
        do {
            System.out.print("Do you register or login? [\"r\", \"l\"]: ");
            answer = InputClireader.getInputReader().nextLine().trim();
        } while (answer == null || !answer.equals("r") && !answer.equals("l"));
        return answer.equals("l");
    }

    private String getUsername(){
        String username;
        Pattern usernamePattern = Pattern.compile("^\\s*\\b(\\w+)\\b\\s*");

        while (true) {
            System.out.print("Please, enter username! (Example: Lizochek228): ");
            username = InputClireader.getInputReader().nextLine().trim();
            if (!username.isEmpty() && usernamePattern.matcher(username).find()) break;
            System.out.print("Username should be not empty string of letters and digits");
        }
        return username.trim();
    }

    private String getUserPassword() throws IOException {
        if (System.console() == null) {
            String password;
            Pattern passwordPattern = Pattern.compile("^\\s*([\\d\\w]*)\\s*");
            while (true) {
                System.out.print("Please, enter password: ");
                password = InputClireader.getInputReader().nextLine().trim();
                if (!password.isEmpty() && passwordPattern.matcher(password).find() && password.length() <= 16) break;
                System.out.print("Password should be string of letters and digits");
            }
            return password.trim();
        } else {
            String password;
            Pattern passwordPattern = Pattern.compile("^\\s*([\\d\\w]*)\\s*");
            while (true) {
                System.out.print("\tPlease, enter password: ");
                password = new String(System.console().readPassword());
                if (!password.isEmpty() && passwordPattern.matcher(password).find() && password.length() <= 16) break;
                System.out.print("Password should be string of letters and digits!");
            }
            return password.trim();
        }
    }

}
