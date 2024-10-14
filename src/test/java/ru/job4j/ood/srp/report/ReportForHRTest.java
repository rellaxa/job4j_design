package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportForHRTest {

    @Test
    public void whenReportForHr() {
        Store store = new MemoryStore();
        Employee one = new Employee("1", null, null, 300000);
        Employee two = new Employee("2", null, null, 200000);
        Employee three = new Employee("3", null, null, 500000);
        store.add(one);
        store.add(two);
        store.add(three);
        Report report = new ReportForHR(store);
        String separator = System.lineSeparator();
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Salary")
                .append(separator)
                .append(three.getName()).append(" ")
                .append(three.getSalary())
                .append(separator)
                .append(one.getName()).append(" ")
                .append(one.getSalary())
                .append(separator)
                .append(two.getName()).append(" ")
                .append(two.getSalary())
                .append(separator);
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

}