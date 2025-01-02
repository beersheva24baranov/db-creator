package employees;
import java.util.Random;
import static employees.Properties.*;

public class Employee {
    private static long lastId = MIN_ID;
    private static Random random = new Random();

    private long id;
    private String type;
    private String department;
    private int basicSalary;
    private float factor;
    private int wage;
    private int hours;
    private float percent;
    private long sales;

    private Employee(String type, String department, int basicSalary, float factor, int wage, int hours, float percent,
            long sales) {
        this.id = lastId++;
        this.type = type;
        this.department = department;
        this.basicSalary = basicSalary;
        this.factor = factor;
        this.wage = wage;
        this.hours = hours;
        this.percent = percent;
        this.sales = sales;
    }

    @Override
    public String toString() {
        String factorStr = factor == 0 ? "" : String.valueOf(factor);
        String wageStr = wage == 0 ? "" : String.valueOf(wage);
        String hoursStr = hours == 0 ? "" : String.valueOf(hours);
        String percentStr = percent == 0 ? "" : String.valueOf(percent);
        String salesStr = sales == 0 ? "" : String.valueOf(sales);
        return "%d,%s,%d,%s,%s,%s,%s,%s,%s"
                .formatted(id, type, basicSalary, department, factorStr, wageStr, hoursStr, percentStr, salesStr);
    }

    public static Employee getEmployee(String department) {
        int basicSalary = getBasicSalary();
        return new Employee("Employee", department, basicSalary, 0, 0, 0, 0, 0);
    }

    public static Employee getManager(String department) {
        Employee manager = getEmployee(department);
        manager.type = "Manager";
        manager.factor = getFactor();
        return manager;
    }

    public static Employee getWageEmployee(String department) {
        Employee wageEmployee = getEmployee(department);
        wageEmployee.type = "WageEmployee";
        wageEmployee.wage = getWage();
        wageEmployee.hours = getHours();
        return wageEmployee;
    }

    public static Employee getSalesPerson(String department) {
        Employee salesPerson = getWageEmployee(department);
        salesPerson.type = "SalesPerson";
        salesPerson.percent = getPercent();
        salesPerson.sales = getSales();
        return salesPerson;
    }

    private static int getWage() {
        return random.nextInt(MIN_WAGE, MAX_WAGE);
    }

    private static int getHours() {
        return random.nextInt(MIN_HOURS, MAX_HOURS);
    }

    private static int getBasicSalary() {
        return random.nextInt(MIN_BASIC_SALARY, MAX_BASIC_SALARY) / 100 * 100;
    }

    private static float getFactor() {
        return ((float) Math.round(random.nextFloat(MIN_FACTOR, MAX_FACTOR) * 10)) / 10;
    }

    private static float getPercent() {
        return ((float) Math.round(random.nextFloat(MIN_PERCENT, MAX_PERCENT) * 10)) / 10;
    }

    private static long getSales() {
        return random.nextLong(MIN_SALES, MAX_SALES);
    }
}