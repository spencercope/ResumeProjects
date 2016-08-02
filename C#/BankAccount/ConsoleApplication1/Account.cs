using System;

[Serializable]
public class Account
{
    private decimal balance;                                                                    //instance variable that stores the balance
    double dateDiff;
    double rate;
    DateTime secondDateTime;
    DateTime firstDateTime;

    //******************************************************************************
    public void getDate1()
    {
        Console.WriteLine("Please enter the date.");
        string myDate = Console.ReadLine();
        firstDateTime = Convert.ToDateTime(myDate);
    }


    //*******************************************************************************
    public void getDate2()
    {
        Console.WriteLine("Please enter the date.");
        string myDate = Console.ReadLine();
        secondDateTime = Convert.ToDateTime(myDate);
    }


    //*******************************************************************************
    public void Deposit(decimal amount)                                                         //deposit method
    {
        getInterest();
        Balance = Balance + amount;                                                             //add amount to balance
    }

    //******************************************************************************
    public decimal Balance                                                                      //property to get and set acocunt balance
    {
        get
        {
            return balance;
        }
        set
        {
            if (value >= 0)                                                                     //validation
                balance = value;
        }
    }

    //****************************************************************************
    public void Withdrawal(decimal amount)                                                      //withdrawal method
    {
        getInterest();
        Balance = Balance - amount;                                                             //substract amount from balance
    }

    //****************************************************************************
    public void getInterest()
    {
        double dateDiff = (secondDateTime - firstDateTime).TotalDays;
        double rateTime = .05 / 365;

        rateTime = Math.Pow(1 + rateTime, dateDiff);
        balance = balance * (decimal)rateTime;
        firstDateTime = secondDateTime;
    }

    //***************************************************************************
    public void Menu()
    {
        Account myAcct = new Account();

        int menuInput = 0;
        do
        {
            Console.WriteLine("\nPlease choose an option below (enter numeric value).");                          //display menu for ATM
            Console.WriteLine();
            Console.WriteLine("1. Deposit");
            Console.WriteLine("2. Withdrawal");
            Console.WriteLine("3. Check Balance");
            Console.WriteLine("4. Exit");
            menuInput = Convert.ToInt32(Console.ReadLine());

            if (menuInput == 1)                                                                                 //For Deposit
            {
                Console.WriteLine("\nDeposit!");
                getDate2();

                if (secondDateTime >= firstDateTime)
                {
                    decimal depositAmount;

                    Console.Write("Enter deposit amount for account: ");                                            //get deposit amount for account from user
                    depositAmount = Convert.ToDecimal(Console.ReadLine());
                    Console.WriteLine("Adding {0:C} to Account {1} balance...\n", depositAmount, menuInput);
                    Deposit(depositAmount);                                                                         //add the deposit for account
                    Console.WriteLine("Your account has a balance of: {0:C}\n", Balance);
                }
                else
                {
                    Console.WriteLine("\n*******Please enter a valid date*******\n");
                }



            }
            else if (menuInput == 2)                                                                                //For Withdrawal
            {
                Console.WriteLine("\nWithdrawal :(");
                getDate2();

                if (secondDateTime >= firstDateTime)
                {
                    decimal withdrawalAmount;
                    Console.WriteLine("Enter withdrawal amount for account: ");
                    withdrawalAmount = Convert.ToDecimal(Console.ReadLine());
                    if (withdrawalAmount < balance)
                    {
                        Console.WriteLine("\nWithdrawing {0:C} from account balance...\n", withdrawalAmount);
                        Withdrawal(withdrawalAmount);
                        Console.WriteLine("Your account has a balance of: {0:C}\n", Balance);
                    }
                    else
                    {
                        Console.WriteLine("\n*******You are poor and overdrafting is not allowed at this bank!*******\n");
                    }

                }
                else
                {
                    Console.WriteLine("\n*******Please enter a valid date*******\n");
                }
            }
            else if (menuInput == 3)
            {
                getDate2();

                if (secondDateTime >= firstDateTime)
                {
                    getInterest();
                    Console.WriteLine("Your account has a balance of: {0:C}\n   ", Balance);
                }
                else
                {
                    Console.WriteLine("\n*******Please enter a valid date*******\n");
                }

            }
            else if (menuInput == 4)
            {

            }
            else
            {
                Console.WriteLine("Please enter a number that correlates to the menu.");
            }


        } while (menuInput != 4);

    }
}