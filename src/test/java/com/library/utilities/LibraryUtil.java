package com.library.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

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
    public static String getTokenByRole(String role) {

        Map<String, String> credentials = retrieveCredentials(role);
        String email = credentials.get("email");
        String password = credentials.get("password");
        return getToken(email, password);
    }

    public static String getToken(String email, String password) {


        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);
        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .formParams(credentials)
                .when().post("/login")
                .then()
                .extract().jsonPath();

        String token = jsonPath.getString("token");

        return token;
    }
}
