package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForEconomy implements Report {

    private final Store store;
    private final CurrencyConverter converter;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportForEconomy(Store store, CurrencyConverter converter, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.converter = converter;
        this.dateTimeParser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; RUB")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(Currency.USD, employee.getSalary(), Currency.RUB))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Store store1 = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser1 = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("Relaxa", now, now, 10000);
        Employee employee2 = new Employee(null, now, now, 15000);
        Employee employee3 = new Employee(null, now, now, 5000);
        store1.add(employee);
        store1.add(employee2);
        store1.add(employee3);
        CurrencyConverter converter1 = new InMemoryCurrencyConverter();
        Report report = new ReportForEconomy(store1, converter1, dateTimeParser1);
        String rep = report.generate(empl -> true);
        System.out.println(rep);
    }

}
