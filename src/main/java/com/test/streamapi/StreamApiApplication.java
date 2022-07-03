package com.test.streamapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamApiApplication {

	static List<Employee> employees = new ArrayList<>();
	static {
		employees.add(new Employee("prabath","udayanga",50000.0, List.of("project 1","project 2")));
		employees.add(new Employee("janaka","chathuranga",60000.0, List.of("project 1","project 3")));
		employees.add(new Employee("prabath","udayanga",65000.0, List.of("project 3","project 4")));
	}

	public static void main(String[] args) {
//		SpringApplication.run(StreamApiApplication.class, args);
		//foreach
		employees.stream()
				.forEach(employee -> System.out.println(employee));
		//map
		//collect
		List<Employee> increasedSalary = employees.stream()
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.10,
						employee.getProjects()
				)).collect(Collectors.toList());
		System.out.println(increasedSalary);

		//filter
		List<Employee> filterEmployee = employees.stream()
				.filter(employee -> employee.getSalary() > 59000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.10,
						employee.getProjects()
				)).collect(Collectors.toList());
		System.out.println(filterEmployee);


		//findFirst
		Employee firstEmployee = employees.stream()
				.filter(employee -> employee.getSalary() > 59000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.10,
						employee.getProjects()
				)).findFirst()
				.orElse(null);
		System.out.println(firstEmployee);

		//flatMap
		String projects =  employees.stream()
				.map(employee -> employee.getProjects())
				.flatMap(strings -> strings.stream())
				.collect(Collectors.joining(","));
		System.out.println(projects);

		//short circuit operations
		List<Employee> shortCircuit = employees.stream()
				.skip(1)
				.limit(1)
				.collect(Collectors.toList());
		System.out.println(shortCircuit);
	}

}
