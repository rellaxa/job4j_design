package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportForItTest {
    @Test
    public void whenReportForIt() {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee one = new Employee("1", now, now, 200000);
        Employee two = new Employee("2", now, now, 300000);
        Employee three = new Employee("3", now, now, 400000);
        store.add(one);
        store.add(two);
        store.add(three);
        Report report = new ReportForIt(store, dateTimeParser);
        StringBuilder expected = new StringBuilder();
        String separator = System.lineSeparator();
        expected.append("Name;Hired;Fired;Salary;")
                .append(separator)
                .append(one.getName()).append(";")
                .append(dateTimeParser.parse(one.getHired())).append(";")
                .append(dateTimeParser.parse(one.getFired())).append(";")
                .append(one.getSalary()).append(";")
                .append(separator)
                .append(two.getName()).append(";")
                .append(dateTimeParser.parse(two.getHired())).append(";")
                .append(dateTimeParser.parse(two.getFired())).append(";")
                .append(two.getSalary()).append(";")
                .append(separator)
                .append(three.getName()).append(";")
                .append(dateTimeParser.parse(three.getHired())).append(";")
                .append(dateTimeParser.parse(three.getFired())).append(";")
                .append(three.getSalary()).append(";")
                .append(separator);
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

}