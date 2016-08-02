package payrollapp;


import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import payrollapp.Employee;

public class Salary extends Employee implements Serializable
{
    float sal = 0.0f;
    String salType;
    
    public Salary(int t, String id)
    {
        ID = id;
        if (t == 1)
        {
            sal = 50000.0f;
            salType = "Staff";
        }
        else
        {
            sal = 100000.0f;
            salType = "Executive";
        }
        System.out.println("Salary Employee successfully created.");
    }
    
    @Override
    public void computeGross()
    {
        gross = sal;
    }
    
    
    @Override
    public void displayEmployee()
    {
        System.out.println("Type of Employee: Salary");
        System.out.println("Type of Salary: "+ salType);
        System.out.println("Gross: "+ NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(gross));
    }
    
}