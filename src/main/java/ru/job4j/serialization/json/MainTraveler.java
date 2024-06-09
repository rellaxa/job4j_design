package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainTraveler {
    public static void main(String[] args) {
        Traveler traveler = new Traveler(true, 35, "Canada",
                new Person(false, 24, new Contact("+7 999 111 11 3"), new String[]{"Worker", "Free"}),
                new String[]{"Canada", "Japan", "USA", "Italy"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String obj = gson.toJson(traveler);
        System.out.println(obj);

        /* Создаём новую json-строку с модифицированными данными*/
        final String travelerGson =
                "{"
                + "\"internPassport\":true,"
                + "\"numberOfTrips\":35,"
                + "\"favouriteCountry\":\"Canada\","
                        + "\"person\":"
                + "{"
                        + "\"sex\":false,"
                        + "\"age\":24,"
                        + "\"contact\":"
                        + "{"
                            + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Worker\",\"Free\"]"
                + "},"
                + "\"countries\":"
                + "[\"Canada\", \"Japan\", \"USA\", \"Italy\"]"
                + "}";
        final Traveler travelerFromGson = gson.fromJson(travelerGson, Traveler.class);
        System.out.println(travelerFromGson);
    }
}
