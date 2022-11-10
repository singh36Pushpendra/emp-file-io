package emp.file.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeMain {

    private static int empId;
    private static String empName;
    private static double empSalary;
    private static Scanner scanner;

    private static void inputEmpPayroll() {

        System.out.print("Enter Employee Id: ");
        empId = scanner.nextInt();
        System.out.print("Enter Employee Name: ");
        empName = scanner.next();
        System.out.print("Enter Employee Salary: ");
        empSalary = scanner.nextDouble();

    }

    public static void main(String[] args) {
        System.out.println("Welcome to File IO!");

        String option;
        scanner = new Scanner(System.in);

        List<Employee> employees = new ArrayList<>();

        while (true) {
            inputEmpPayroll();
            employees.add(new Employee(empId, empName, empSalary));

            System.out.print("Do you wan't to add one more Employee entry[Yes/No]: ");
            option = scanner.next();

            if (option.equalsIgnoreCase("nO"))
                break;
        }

        Path path = Paths.get("C:/Users/micro/Downloads/emp-info.txt");

        List<String> empsList = employees
                .stream().map(emp -> emp.toString())
                .collect(Collectors.toList());

        try {
            if (! Files.exists(path))
                Files.createFile(path);

            Files.write(path, empsList, StandardOpenOption.APPEND);
            System.out.println(employees.size() + " Employee information saved successfully to file!\n" + path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

