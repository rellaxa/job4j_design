package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportForHR implements Report {

    private final Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(employee -> true);
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Store store1 = new MemoryStore();
        Employee one = new Employee("1", null, null, 1000);
        Employee two = new Employee("2", null, null, 2000);
        Employee three = new Employee("3", null, null, 3000);
        store1.add(one);
        store1.add(two);
        store1.add(three);
        Report report = new ReportForHR(store1);
        System.out.println(report.generate(employee -> true));
    }

}
