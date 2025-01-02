package employees;

import static employees.Properties.*;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        String fileName = "employees-sql-data.csv";
        Staff[] departments = {
                new Staff("QA", QA_N_MANAGERS, QA_N_EMPLOYEES, QA_N_WAGE_EMPLOYEES, QA_N_SALES_PERSONS),
                new Staff("Development", DEV_N_MANAGERS, DEV_N_EMPLOYEES, DEV_N_WAGE_EMPLOYEES, DEV_N_SALES_PERSONS),
                new Staff("Devops", DEVOPS_N_MANAGERS, DEVOPS_N_EMPLOYEES, DEVOPS_N_WAGE_EMPLOYEES, DEVOPS_N_SALES_PERSONS),
                new Staff("Sales", SALES_N_MANAGERS, SALES_N_EMPLOYEES, SALES_N_WAGE_EMPLOYEES, SALES_N_SALES_PERSONS)
        };
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writeFile(writer, departments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFile(PrintWriter writer, Staff[] departments) {
        for (int i = 0; i < departments.length; i++) {
            for (int e = 0; e < departments[i].nManagers(); e++) {
                writer.println(Employee.getManager(departments[i].department()));
            }
            for (int e = 0; e < departments[i].nEmployees(); e++) {
                writer.println(Employee.getEmployee(departments[i].department()));
            }
            for (int e = 0; e < departments[i].nWageEmployees(); e++) {
                writer.println(Employee.getWageEmployee(departments[i].department()));
            }
            for (int e = 0; e < departments[i].nSalesPersons(); e++) {
                writer.println(Employee.getSalesPerson(departments[i].department()));
            }
        }
    }

}