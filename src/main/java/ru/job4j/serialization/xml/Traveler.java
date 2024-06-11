package ru.job4j.serialization.xml;

import ru.job4j.serialization.Person;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "traveler")
@XmlAccessorType(XmlAccessType.FIELD)
public class Traveler {

    @XmlAttribute
    private boolean internPassport;
    @XmlAttribute
    private int numberOfTrips;
    @XmlAttribute
    private String favouriteCountry;
    private Person person;

    @XmlElementWrapper(name = "countries")
    @XmlElement(name = "country")
    private String[] countries;

    public Traveler() {
    }

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
