package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForIt implements Report {

    private Store store;
    private DateTimeParser<Calendar> dateTimeParser;

    public ReportForIt(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Store store1 = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser1 = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee one = new Employee("1", now, now, 200000);
        Employee two = new Employee("2", now, now, 300000);
        Employee three = new Employee("3", now, now, 400000);
        store1.add(one);
        store1.add(two);
        store1.add(three);
        Report report = new ReportForIt(store1, dateTimeParser1);
        System.out.println(report.generate(employee -> true));
    }

}
