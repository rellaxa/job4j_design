package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;
    private Contact contact;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Person() {
    }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Person.class);

        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();

        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
