using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

public class ATM
{
    Account[] acctArray = new Account[3];                                                               //create an array of accounts


    public static void Main(string[] args)                                                              //start main
    {
        ATM atmInstance = new ATM();                                                                    //create instance of ATM

        atmInstance.topMenu();


        atmInstance.writeArray();

        Console.WriteLine("ATM has ended. Don't forget to enjoy your day.");
        Console.ReadKey();
    }                                                                                                  //end main




    //**************************************************************************
    public void topMenu()
    {
        int topMenuInput = 0;
        do
        {
            Console.WriteLine("Please choose an option below (enter numeric value).\n");              //display top menu 
            Console.WriteLine("1. Load Accounts");
            Console.WriteLine("2. Populate Accounts");
            Console.WriteLine("3. Pick Account");
            Console.WriteLine("4. Quit");
            topMenuInput = Convert.ToInt32(Console.ReadLine());

            if (topMenuInput == 1)
            {
                readArray();
                Console.WriteLine("\nYou have successfully loaded the 3 accounts.\n");
            }
            else if (topMenuInput == 2)
            {
                populateArray();
            }
            else if (topMenuInput == 3)
            {
                pickAccount();
            }
            else if (topMenuInput == 4)
            {
                //exit ATM class
            }



        } while (topMenuInput != 4);
    }


    //*********************************************************************
    public void populateArray()
    {


        for (int i = 0; i < acctArray.Length; i++)
        {
            acctArray[i] = new Account();
            acctArray[i].Balance = 100;
        }

        Console.WriteLine("You have just created {0} accounts, please enter the creation date for each.", acctArray.Length);

        for (int i = 0; i < acctArray.Length; i++)
        {
            acctArray[i].getDate1();
        }




    }

    //*********************************************************************
    public void pickAccount()
    {
        Console.WriteLine("Please enter your account number.");
        int acctInput = Convert.ToInt32(Console.ReadLine());

        if (acctInput == 0)
        {
            Console.WriteLine("\n*******Account 0*******\n");
            acctArray[acctInput].Menu();
        }
        else if (acctInput == 1)
        {
            Console.WriteLine("\n*******Account 1*******\n");
            acctArray[acctInput].Menu();
        }
        else if (acctInput == 2)
        {
            Console.WriteLine("\n*******Account 2*******\n");
            acctArray[acctInput].Menu();
        }
        else
        {
            Console.WriteLine("\n*******Please enter a valid account number.*******\n");
        }
    }



    //*******************************************************************
    public void writeArray()
    {
        BinaryFormatter Serializer = new BinaryFormatter();
        Stream fileStream = File.Create("test.txt");
        Serializer.Serialize(fileStream, acctArray);
        fileStream.Close();
    }

    //*******************************************************************
    public void readArray()
    {
        BinaryFormatter formatter = new BinaryFormatter();
        Stream stream = new FileStream("test.txt", FileMode.Open, FileAccess.Read, FileShare.Read);
        acctArray = (Account[])formatter.Deserialize(stream);
        stream.Close();
    }
}


