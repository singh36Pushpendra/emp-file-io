package emp.file.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class EmployeeMain {
    public static void main(String[] args) {
        System.out.println("Welcome to File IO!");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee Id: ");
        int empId = scanner.nextInt();
        System.out.print("Enter Employee Name: ");
        String empName = scanner.next();
        System.out.print("Enter Employee Salary: ");
        double empSalary = scanner.nextDouble();

        Employee emp = new Employee(empId, empName, empSalary);

        Path path = Paths.get("C:/Users/micro/Downloads/emp-info.txt");

        try {
            Files.writeString(path, emp.toString(), StandardOpenOption.CREATE);
            System.out.println("Employee information saved successfully to file!\n" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

