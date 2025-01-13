import javax.swing.*;

class Emolument {
    // Encapsulated fields
    private double basic_salary;
    private double tax_relief;

    // Constructor to initialize basic salary and tax relief
    public Emolument(double basic_salary, double tax_relief) {
        this.basic_salary = basic_salary;
        this.tax_relief = tax_relief;
    }

    // Method to get the basic salary
    public double getBasicSalary() {
        return basic_salary;
    }

    // Method to get the tax relief
    public double getTaxRelief() {
        return tax_relief;
    }

    // Method to compute SSNIT contribution (3.5% of basic salary)
    public double SSNIT() {
        return basic_salary * 0.035;
    }

    // Method to compute taxable income
    public double taxableIncome() {
        return basic_salary - (tax_relief + SSNIT());
    }
}

class MyEmolument extends Emolument {
    // Encapsulated fields for basic salary and tax relief
    private double basic_salary;
    private double tax_relief;

    // Default constructor
    public MyEmolument() {
        super(0, 0);  // default values of 0 for basic salary and tax relief
    }

    // Constructor that creates MyEmolument with specified values
    public MyEmolument(double basic_salary, double tax_relief) {
        super(basic_salary, tax_relief);  // calling superclass constructor
    }

    // Method to compute income tax based on taxable income
    public double incomeTax() {
        double taxableIncome = taxableIncome();
        double tax = 0;

        if (taxableIncome <= 500) {
            tax = taxableIncome * 0.05;
        } else if (taxableIncome <= 1000) {
            tax = 500 * 0.05 + (taxableIncome - 500) * 0.125;
        } else {
            tax = 500 * 0.05 + 500 * 0.125 + (taxableIncome - 1000) * 0.175;
        }

        return tax;
    }

    // Method to compute total deductions
    public double totalDeduction() {
        return SSNIT() + incomeTax();
    }

    // Method to compute net salary
    public double netSalary() {
        return getBasicSalary() - totalDeduction();
    }
}

public class Main {
    public static void main(String[] args) {
        // Accept input from the user
        String basicSalaryInput = JOptionPane.showInputDialog("Enter Basic Salary:");
        String taxReliefInput = JOptionPane.showInputDialog("Enter Tax Relief:");

        double basicSalary = Double.parseDouble(basicSalaryInput);
        double taxRelief = Double.parseDouble(taxReliefInput);

        // Create MyEmolument object
        MyEmolument staffSalary = new MyEmolument(basicSalary, taxRelief);

        // Display results in a formatted message
        double ssnitContribution = staffSalary.SSNIT();
        double taxableIncome = staffSalary.taxableIncome();
        double incomeTax = staffSalary.incomeTax();
        double totalDeduction = staffSalary.totalDeduction();
        double netSalary = staffSalary.netSalary();

        String output = String.format("Basic Salary: %.2f\n" +
                                     "Tax Relief: %.2f\n" +
                                     "SSNIT Contribution: %.2f\n" +
                                     "Taxable Income: %.2f\n" +
                                     "Income Tax: %.2f\n" +
                                     "Total Deduction: %.2f\n" +
                                     "Net Salary: %.2f",
                                     basicSalary, taxRelief, ssnitContribution, taxableIncome,
                                     incomeTax, totalDeduction, netSalary);

        JOptionPane.showMessageDialog(null, output);
    }
}
