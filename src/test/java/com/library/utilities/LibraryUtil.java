package com.library.utilities;

import java.util.HashMap;
import java.util.Map;

public class LibraryUtil {


    public static Map<String, String> retrieveCredentials(String role) {

        String email = "";
        String password = "";

        Map<String, String> credentials = new HashMap<>();

        switch (role) {
            case "librarian":
                email = System.getenv("LIBRARIAN_USERNAME");
                password = System.getenv("LIBRARIAN_PASSWORD");
                break;
            case "student":
                email = System.getenv("STUDENT_USERNAME");
                password = System.getenv("STUDENT_PASSWORD");
                break;
            default:
                throw new RuntimeException("Invalid role: " + role);
        }
        credentials.put("email", email);
        credentials.put("password", password);
        return credentials;
    }
}
