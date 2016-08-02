package payrollapp;

import java.io.Serializable;
import java.util.Scanner;

public class Employee implements Serializable
{
    //Attributes
    float rate = 30.0f;
    float taxrate = 0.2f;
    int hours = 45;
    float gross = 0.0f;
    float tax = 0.0f;
    float net = 0.0f;
    float net_percent = 0.0f;
    String ID;
    
    //Constructor
    public Employee()
    {
        
    }
    
    public void computeAll()
    {
        computeGross();
        computeTax();
        computeNet();
        computeNetPerc();
        displayEmployee();
    }
    
    //start of methods
    public void computeGross()
    { 
        gross = rate * hours;
    }

    protected void computeTax() 
    { 
        tax = gross * taxrate;
    }

    protected void computeNet()
    { 
        net = gross - tax;
    }

    protected void computeNetPerc() 
    { 
        net_percent = (net / gross) * 100;
    }

    protected void displayEmployee() 
    {   
        System.out.println("Hours: "+ hours);
        System.out.println("Rate: "+ rate);
        System.out.println("Gross: "+ gross);
        System.out.println("Net: " + net);
        System.out.println("Net%: "+ net_percent +"%");
    }
} 
