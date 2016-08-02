package payrollapp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class PayrollApp extends JFrame
{
    Employee [] eArr = new Employee[3];
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {
        PayrollApp a = new PayrollApp();
        a.topMenu();    
    }
    
    public void topMenu() throws IOException, ClassNotFoundException
    {
        int topMenuInput = 0;
        do
         {
            System.out.println("Choose from the options below.");
            System.out.println("1)Populate Employees");
            System.out.println("2)Select Employees");
            System.out.println("3)Load Employees");
            System.out.println("4)Save Employees");
            System.out.println("5)Display all of the Employees current information!!!!");
            System.out.println("Press 9 to quit.");
            topMenuInput = sc.nextInt();

            switch (topMenuInput) 
            {
                case 1:
                    populateEmployees();
                    break;
                case 2:
                    selectEmployees();
                    break;
                case 3:
                    loadEmployees();
                    break;
                case 4:
                    saveEmployees();
                    break;
                case 5:
                    showAllGross();
                    break;                    
                case 9:
                    System.out.println("Payroll App has ended.  Don't forget to enjoy your day.");
                    break;
                default:
                    break;
            }


         }while(topMenuInput != 9);

    }

    public void populateEmployees()
    {
        int salType = 0;
        float [] comType;
        comType = new float [2]; 
        float [] hourlyType;
        float hourlyEmployeeGross = 0.0f;
        String comID;
        String hourlyID;
        String salID;

        System.out.println("Please enter Salary Employee ID. (first initial, last name)");
        salID = sc.next();
        salType = salEmpQs();
        eArr[0] = new Salary(salType, salID);

        System.out.println("Please enter Commission Employee ID. (first initial, last name)");
        comID = sc.next();
        comType = comEmpQs();
        eArr[1] = new Commission(comType, comID);

        System.out.println("Please enter Hourly Employee ID. (first initial, last name)");
        hourlyID = sc.next();
        hourlyType = hourlyEmpQs();
        hourlyEmployeeGross = getHourlyGross(hourlyType);
        eArr[2] = new Hourly(hourlyEmployeeGross, hourlyID, hourlyType);
        
        for (int i = 0 ; i < eArr.length; i++)
        {
            eArr[i].computeAll();
        }
    }

    public void selectEmployees()
    {
        String ID;
        System.out.println("Please enter the Employee ID. (first initial, last name)");
        ID = sc.next();
        
        if(ID.equals(eArr[0].ID))
        {
            eArr[0].displayEmployee();
            System.out.println();
        }
        if(ID.equals(eArr[1].ID))
        {
            eArr[1].displayEmployee();
            System.out.println();

        }
        if(ID.equals(eArr[2].ID))
        {
            eArr[2].displayEmployee();
            System.out.println();

        }
        else
        {
            System.out.println("**********Employee Not Found.**********");
        }

        

    }

    public void loadEmployees() throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("eArr.ser"));
        eArr = (Employee[]) in.readObject();
        in.close();
        System.out.println("*******You have successfully loaded the employee information.********");
    }

    public void saveEmployees() throws FileNotFoundException, IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("eArr.ser"));
        out.writeObject(eArr);
        out.flush();
        out.close();
        System.out.println("*******You have successfully saved the employee information.********");
    }


    private int salEmpQs()
    {
        int a;
        System.out.println("Is this a regular Staff or Executive? (enter corresponding number)");
        System.out.println("1)Staff");
        System.out.println("2)Executive");
        a = sc.nextInt();                                           
        return a;
    }

    private float [] comEmpQs()
    {
        float [] a;
        a = new float [2];
        System.out.println("How many widgets did the employee sell?");
        a[0] = sc.nextFloat();
        System.out.println("What is the price of one widget? (in US dollars)");
        a[1] = sc.nextFloat();
        return a;
    }

    private float [] hourlyEmpQs() 
    {
        float [] a;
        a = new float [2];
        System.out.println("How many hours did the employee work?");
        a[0] = sc.nextInt();
        System.out.println("Please enter the employee's hourly wage");
        a[1] = sc.nextFloat();
        return a;
    }

    private float getHourlyGross(float [] hoursWage)
    {
        float hours;
        float wage;
        float gross = 0.0f;
        hours = hoursWage[0];
        wage = hoursWage[1];

        if (hours <= 40)
        {
            gross = hours * wage;
        }
        else
        {
            gross = (float) ((wage * 40.0) + ((hours - 40.0) * (wage * 1.5)));
        }
        return gross;
    }
    
    
    private void showAllGross()
    {
        String emp1 = "\n     ID: "+eArr[0].ID+"\n     Gross: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[0].gross)+"\n     Tax Rate: "+String.format("%.2f",eArr[0].taxrate)+"\n     Tax: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[0].tax)+"\n     Net: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[0].net);  //+"\n     Tax: "+eArr[0].tax+"\n     Tax Rate: "+eArr[0].taxrate+"\n     Net: "+eArr[0].net+"\n     Net Percent: "+eArr[0].net_percent+""                                           
        String emp2 = "\n     ID: "+eArr[1].ID+"\n     Gross: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[1].gross)+"\n     Tax Rate: "+String.format("%.2f",eArr[1].taxrate)+"\n     Tax: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[1].tax)+"\n     Net: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[1].net);
        String emp3 = "\n     ID: "+eArr[2].ID+"\n     Gross: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[2].gross)+"\n     Tax Rate: "+String.format("%.2f",eArr[2].taxrate)+"\n     Tax: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[2].tax)+"\n     Net: "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(eArr[2].net);
        
        JTextArea ta1 = new JTextArea(emp1);
        JTextArea ta2 = new JTextArea(emp2);
        JTextArea ta3 = new JTextArea(emp3);
                
        JFrame f = new JFrame("A Quick Look");
        f.setSize(600,170);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p = new JPanel(new GridLayout(1,3));
        ta1.setEditable(false);
        
        f.setVisible(true);
        f.add(p);
        
        p.add(ta1);
        p.add(ta2);
        p.add(ta3);
    }
    
//    public static void welcomeMessage()
//    {
//        JTextArea ta1 = new JTextArea("Welcome to the Payroll Application. \nPlease use integers for all menu choices.");
//                
//        JFrame f = new JFrame("Welcome!");
//        f.setSize(400,270);
//        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JPanel p = new JPanel(new GridLayout(1,1));
//        ta1.setEditable(false);
//        //txtName.setHorizontalAlignment(JLabel.CENTER);
//        f.setVisible(true);
//        f.add(p);
//        //ta1.setHorizontalAlighment(JTextArea.CENTER_ALIGNMENT);
//        p.add(ta1);
//    }
    
}
