package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class JsonReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public JsonReport(Store store) {
        this.store = store;
        this.dateTimeParser = new ReportDateTimeParser();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JsonArray array = new JsonArray();
        for (Employee employee : store.findBy(filter)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", employee.getName());
            jsonObject.addProperty("hired", dateTimeParser.parse(employee.getHired()));
            jsonObject.addProperty("fired", dateTimeParser.parse(employee.getFired()));
            jsonObject.addProperty("salary", employee.getSalary());
            array.add(jsonObject);
        }
        return gson.toJson(array);
    }

}
