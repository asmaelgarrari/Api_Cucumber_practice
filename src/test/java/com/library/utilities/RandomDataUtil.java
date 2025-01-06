package com.library.utilities;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class RandomDataUtil {

    static Faker faker = new Faker();

    public static Map<String, Object> createRandomBook() {

        Map<String, Object> book = new HashMap<>();
        book.put("name","Challenge yourself");
        book.put("isbn", faker.ancient().hero());
        book.put("year", faker.number().numberBetween(2020, 2025));
        book.put("author", faker.book().author());
        book.put("book_category_id", 7);
        book.put("description", faker.chuckNorris().fact());

        return book;
    }

    public static Map<String, Object> createRandomUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("full_name", faker.name().fullName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("user_group_id",2);
        user.put("status","ACTIVE"); // Example: static value
        user.put("start_date","2020-10-14");
        user.put("end_date","2021-10-12");
        user.put("address",faker.address().streetAddress());
        return user;
    }

    public static void login (String username, String password){
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username",username );
        credentials.put("password", password);
    }



    }


