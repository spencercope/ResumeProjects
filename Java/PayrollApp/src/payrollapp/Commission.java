package payrollapp;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;


public class Commission extends Employee implements Serializable
{
    float moneyMade = 0.0f;
    float commissionRate = 0.5f;
    float widgSold = 0.0f;
    float widgPrice = 0.0f;
    
    
    public Commission(float [] a, String id)
    {
        ID = id;
        widgSold = a[0];
        widgPrice = a[1];
        moneyMade = (widgSold * widgPrice) * commissionRate;

    }
    
    @Override
    public void computeGross()
    {
        gross = moneyMade;
    }
    
    @Override
    public void displayEmployee()
    {
        System.out.println("Type of Employee: Commission");
        System.out.println("Number of Widgets Sold: "+ widgSold);
        System.out.println("Cost of Widget: "+ NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(widgPrice));
        System.out.println("Gross: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(gross));
    }
    
    
}
