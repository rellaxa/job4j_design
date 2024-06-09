package ru.job4j.serialization.json;

import java.util.Arrays;

public class Traveler {
    private final boolean internPassport;
    private final int numberOfTrips;
    private final String favouriteCountry;
    private final Person person;
    private final String[] countries;

    public Traveler(boolean internPassport, int numberOfTrips, String favouriteCountry, Person person, String[] countries) {
        this.internPassport = internPassport;
        this.numberOfTrips = numberOfTrips;
        this.favouriteCountry = favouriteCountry;
        this.person = person;
        this.countries = countries;
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
}
