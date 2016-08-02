package payrollapp;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale; 

public class Hourly extends Employee implements Serializable
{
    float hoursWorked; 
    float wage;
    
    public Hourly(float g, String id, float [] a)
    {
        ID = id;
        gross = g;
        hoursWorked = a[0];
        wage = a[1];
    }
    
        @Override
    public void computeGross()
    {

    }
    
    
    @Override
    public void displayEmployee()
    {
        System.out.println("Type of Employee: Hourly");
        System.out.println("Hours Worked: "+ hoursWorked);
        System.out.println("Wage: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(wage));
        System.out.println("Gross: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(gross));
    }
    
    
}