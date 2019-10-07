package com.ga.streams;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        list.forEach(employee -> System.out.println(employee));

    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employees =this.employees.stream()
                .filter(employee -> employee.getSalary() > 50000)
                .collect(Collectors.toList());

        printList(employees);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<String> employees = this.employees.stream()
                .filter(employee -> employee.getHireDate().getYear() > 2012)
                .map(employee -> employee.getName())
                .collect(Collectors.toList());


        printList(employees);
    }

    @Test
    public void getMaxSalary() {
        double max = this.employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(employee -> employee.getSalary())
                .get();

        System.out.println("Max:" + max);

    }

    @Test
    public void getMinSalary() {
        double min = this.employees.stream()
                .min(Comparator.comparing(employee -> employee.getSalary()))
                .map(employee -> employee.getSalary())
                .get();

        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
       double averageMale = this.employees.stream()
               .filter(employee -> employee.getGender() == "Male")
               .mapToDouble(employee -> employee.getSalary())
               .average()
               .orElse(-1);



        double averageFemale = this.employees.stream()
                .filter(employee -> employee.getGender() == "Female")
                .mapToDouble(employee -> employee.getSalary())
                .average()
                .orElse(-1);

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
//        Employee highest = this.employees.stream()
//                .max(Comparator.comparing(employee -> employee.getSalary()))
//                .get();

        Employee highest = this.employees.stream()
                .reduce((emp1, emp2) -> emp1.getSalary() > emp2.getSalary()? emp1: emp2)
                .orElse(null);


        System.out.println("highest " + highest);
    }
}

