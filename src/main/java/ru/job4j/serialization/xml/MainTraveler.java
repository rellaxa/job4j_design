package ru.job4j.serialization.xml;

import ru.job4j.serialization.Contact;
import ru.job4j.serialization.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainTraveler {
    public static void main(String[] args) throws JAXBException {
        Traveler traveler = new Traveler(true, 35, "Canada",
                new Person(false, 24, new Contact("+7 999 111 11 3"), "Worker", "Free"),
                new String[]{"Canada", "Japan", "USA", "Italy"});

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Traveler.class);

        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();

        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        /* Указываем, что нам нужно форматирование */
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(traveler, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Traveler result = (Traveler) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
