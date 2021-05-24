/*
 * Teniel Nieuwoudt 
 * Student ID - 15437155
 *
 * I pledge by honor that this program is my own work.
 *
 * Assignmetn
 */

package my_package;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.lang.out;

public class Main
{
	 public static void main(String[] args)
	 {
	 try
	 {
	 new BankAccApp("data/AssignmentData.txt");
	 } catch (IOException ioe)
	 {
	 out.printf("Missing file: %s/data/AssignmentData.txt? \n\n",
	 new Main().getClass().getPackage().getName());
	 }
	 }
	}

class BankAccApp
{
	 private List<BankAcc> acc;
	 public BankAcc(String filename) throws IOException
	 {
	 readAccData(filename);
	 displayMenu();
	 int option = 0;
	 Scanner scan = new Scanner(System.in);
	 while (true)
	 {
		 out.print("\nSelect An Option: ");
		 option = scan.nextInt();
		 if (option < 0 || option > 6)
		 {
			 out.println("Invalid input. Can only be 1 - 6 (0 for the Menu).");
			 continue;
		 }
	 }
	 switch (option)
	     {
	       case 0: displayMenu(); break;
	       case 1: doDeposit(); break;
	       case 2: doWithdraw(); break;
	       case 3: displayHighestBalanceAcc(); break;
	       case 4: displayMostActiveAcc(); break;
	       case 5: displayAllAcc(); break;
	     {
	 int totalTrans = getTotalTransactions(acc);
	 out.println("Total Transactions: " + totalTrans);
	 break;
	     }
	       case 6: farewell(); return;
	     }
	}
}
	 public void readAccData(String fn) throws IOException
	 {
		 acc = new LinkedList<>();
         Path pathway = new File("data/AssignmentData.txt").toPath();
         // Putting everything that is in the text document into the list that is created
         List<String> content = Files.readAllLines(pathway);
         for(String line : content)
         {
        	 // Splitting the data with commas to make it easier of the input into the list
             String[] items = line.split(",");
             String name = items[0];
             int age = Integer.valueOf(items[1]);
             String accountID = items[2];
             double balance = Double.valueOf(items[3]);
             int accType = Integer.valueOf(items[4]);
             BankAcc newBankAcc = new BankAcc(name, age, accountID, balance, accountType);
             acc.add(newBankAcc);
         }
	 } 

	 public void displayAllAcc()
	 {
		 for(int num = 0; num < acc.size(); num++)
		 {
		 		acc.get(num).displayInfo();
		 } 

	 public void displayHighestBalanceAcc()
	 {
		 BankAcc Money = acc.get(0);
	        for(BankAcc mon : acc)
	        {
	            if(Money.getBalance() < mon.getBalance())
	            {
	                Money = mon;
	            }
	        }
	        Money.displayInfo();
	 }

	 public void displayMostActiveAcc()
	 {
			BankAcc account = acc.get(0);
			// A loop to display all accounts
			for (int x = 1 ; x < accounts.size(); x++)
			{
				BankAcc active_acc = acc.get(x);
				if (account.getTransactionCounter() < active_acc.getTransactionCounter())
					account = active_acc;
			}
			account.displayInfo();
		}


	 public int getTotalTransactions(List<BankAcc> list)
	 {
			int total_trans = 0;
			for(int x = 0; x < acc.size(); x++)
			{
				BankAcc account = acc.get(x);
				int transactions = account.getTransactionCounter();
				total_trans += transactions;
			}
			return total_trans;
		}

	 public void doDeposit()
	 {
		 String ID = null;
		 double amt;
	        Scanner scan = new Scanner(System.in);
	        out.println("Enter Your ID Number: ");
	        ID = scan.nextLine();

	        BankAcc depo_account = null;
	        for (BankAcc depoA : acc)
	        {
	            if(depoA.getAccountID().equalsIgnoreCase(ID))
	            {
	            	depo_account = depoA;
	                break;
	            }
	        }

			while(depo_account == null)
			{
				out.print("Account Could Not Found \nEnter Your Account ID: ");
				ID = scan.nextLine();
				for (int x = 0; x < acc.size(); x++)
				{
					BankAcc account = acc.get(x);
					String account_Id = account.getAccountID();
					if (account_Id.equals(ID))
					{
						depo_account = account;
					}
				}
			}

			out.println("Enter An Amount To Be Deposited ");
			amt = scan.nextDouble();
			while(amt < 0)
			{
				out.println("Can Not Be A Negative Number Or 0 \nEnter A Positive Number! \nEnter Amount To Be Deposited ");
				amt = scan.nextDouble();
			}

			boolean success = depo_account.deposit(amt);
			if(success == true)
			{
				out.println("Deposit Was Successful");

			}
			else
			{
				out.println("Deposit Was Could Not Be Made And Was Unsuccessful");
			}
		}

	 	 public void doWithdraw()
		 {
	 		 String ID = null;
	 		        Scanner scan = new Scanner(System.in);
	 		        out.println("Enter Your ID Number: ");
	 		     {
	 		        ID = scan.nextLine();
	 		        BankAcc account_found = null;
	 		        for (BankAcc bank : acc)
	 		        {
	 		            if(bank.getAccountID().equalsIgnoreCase(ID))
	 		            {
	 		                account_found = bank;
	 		                break;
	 		            }
	 		        }

	 		        if(account_found == null)
	 		        {
	 		        	out.printf("The Was No Matching Account Found For: %s", ID);
	 		        }
	 		        else
	 		        {
	 		        	out.print("Enter An Amount: ");

	 		            int amount = scan.nextInt();
	 		            boolean result = account_found.withdraw(amount);
	 		            if(result == true)
	 		            {
	 		            	out.println("Withdrawal Was Successful!");
	 		            }
	 		            else
	 		            {
	 		            	out.println("Withdrawal Could Not Be Made And Was Unsuccessful");
	 		            }
	 		        }
	 		     }
		 }


		 public void farewell()
         {
		 out.println("\tThanks For Using The Service. Come again!");
		 }

		 public void displayMenu()
		 {
		 out.println("\n\n***********************************");
		 out.println("* Operation Menu *");
		 out.println("***********************************\n");
		 out.println("0. Display Menu");
		 out.println("1. Deposit");
		 out.println("2. Withdraw");
		 out.println("3. Display Highest Balance Account");
		 out.println("4. Display Most Active Account");
		 out.println("5. Display All Accounts");
		 out.println("6. Exit");
		 }
	}

		class BankAccount extends Person
		{
			private String accountID;
			private double balance;
			private int accType;
			private int transactionCounter = 0;
			private double fee;

			public BankAcc (String name, int age, String accountID, double balance, int accType)
			{
				super(name, age);
				this.accountID = accountID;
				this.balance = balance;
				this.accType = accType;
			}

			public int getTransactionCounter()
			{
				transactionCounter++;
				return transactionCounter;
			}
			public void setTransactionCounter(int transactionCounter)
			{
		 		this.transactionCounter = transactionCounter;
			}
			public double getFee()
			{
		 		switch(getAccType())
		 		{
		 		case 1:
		 			fee = fee + 3.5;
		 			break;
		 		case 2:
		 			fee = fee + 1;
		 			break;
		 		case 3:
		 			fee = fee + 0;
		 			break;
		 		}
		 		return this.fee;
		 	}

			public void setFee(double fee)
			{
		 		this.fee = fee;
		 	}

			public String getAccountID()
			{
				return this.accountID;
			}
			public double getBalance()
			{
				return this.balance;
			}
			public int getAccType() 
			{
				return this.accType;
			}
			public void setAccountID(String accountID) 
			{
				this.accountID = accountID;
			}
			public void setBalance(double balance)
			{
				this.balance = balance;
			}
			public void setAccountType(int AccType)
			{
				this.accType = accType;
			}

			@Override
		 	public void displayInfo()
			{
		 		switch(getAccType())
		 		{
		 		case 1:
		 			out.printf("Name: %s\nAge: %d\nAccountID: %s\nBalance: $%.2f\nAccountType: Regular Account\n\n", getName() , getAge(), getAccountID(), getBalance());
		 			break;
		 		case 2:
		 			out.printf("Name: %s\nAge: %d\nAccountID: %s\nBalance: $%.2f\nAccountType: Premium Account\n\n", getName() , getAge(), getAccountID(), getBalance());
		 			break;
		 		case 3:
		 			out.printf("Name: %s\nAge: %d\nAccountID: %s\nBalance: $%.2f\nAccountType: VIP Account\n\n", getName() , getAge(), getAccountID(), getBalance());
		 			break;
		 		}

		 	}

	 	public boolean deposit(double amt)
	 	{
	 		double balance = getBalance();
	        if (amt > balance)
	        {
	            return false;
	        }
	        else
	        {
	        	balance = balance + amt;
	            return true;
	        }
	 	}

	 	public boolean withdraw(double amt)
	 	{
	 		double balance = getBalance();
	        if (amt > balance)
	        {
	            return false;
	        }
	        else
	        {
	        	balance = balance - amt;
	            return true;
	        }
	 	}
	}

		class Person
		{
			private String name;
			private int age;

			public Person (String name, int age)
			{
				this.name = name;
				this.age = age;
			}
			public String getName()
			{
				return this.name;
			}
			public int getAge()
			{
				return this.age;
			}
			public void setName(String name)
			{
				this.name = name;
			}
			public void setAge(int age)
			{
				this.age = age;
			}

			public void displayInfo()
			{
			out.println ("Personal Information: " + "\nName: " + name + "\nAge: " + age);
			}
		}











