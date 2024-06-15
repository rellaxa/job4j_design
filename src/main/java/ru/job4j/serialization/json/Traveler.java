package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Traveler {

    private boolean internPassport;
    private int numberOfTrips;
    private String favouriteCountry;
    private Person person;
    private String[] countries;

    public Traveler(boolean internPassport, int numberOfTrips, String favouriteCountry, Person person, String[] countries) {
        this.internPassport = internPassport;
        this.numberOfTrips = numberOfTrips;
        this.favouriteCountry = favouriteCountry;
        this.person = person;
        this.countries = countries;
    }

    public boolean getInternPassport() {
        return internPassport;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public String getFavouriteCountry() {
        return favouriteCountry;
    }

    public Person getPerson() {
        return person;
    }

    public String[] getCountries() {
        return countries;
    }

    @Override
    public String toString() {
        return "Traveler{"
                + "internPassport=" + internPassport
                + ", numberOfTrips=" + numberOfTrips
                + ", favouriteCountry='" + favouriteCountry + '\''
                + ", person=" + person
                + ", countries=" + Arrays.toString(countries)
                + '}';
    }

    public static void main(String[] args) {
        Traveler traveler = new Traveler(true, 35, "Canada",
                new Person(false, 24, new Contact("+7 999 111 11 3"), "Worker", "Free"),
                new String[]{"Canada", "Japan", "USA", "Italy"});

        /* JSONArray из ArrayList */
        List<String> countries = Arrays.asList("India", "China", "New Zeland", "Brazil");
        List<String> statuses = Arrays.asList("Student", "Free");
        JSONArray jsonCountries = new JSONArray(countries);
        JSONArray jsonStatuses = new JSONArray(statuses);

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7 999 111 11 3\"}");
        JSONObject jsonPerson = new JSONObject("{\"sex\":false,\"age\":24}");

        /* JSONObject напрямую методом put */
        jsonPerson.put("contact", jsonContact);
        jsonPerson.put("statuses", jsonStatuses);

        JSONObject jsonTraveler = new JSONObject();
        jsonTraveler.put("internPassport", traveler.getInternPassport());
        jsonTraveler.put("numberOfTrips", traveler.getNumberOfTrips());
        jsonTraveler.put("favouriteCountry", traveler.getFavouriteCountry());
        jsonTraveler.put("person", jsonPerson);
        jsonTraveler.put("countries", jsonCountries);

        System.out.println(jsonTraveler.toString());
        System.out.println(new JSONObject(traveler));
    }
}
