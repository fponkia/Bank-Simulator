/**
 * 
 */

/**
 * @author Foram Patel
 * Date: June 20, 2021
 * Description: A Class that creates the account and generates the account number as well.
 * Methods / Constructors: public Account(): A default Constructor to initialize the variable and generate account number.
 * 	public Account(Customer theOwner): An overloaded Constructor to initialize the variables and generate account number.
 *	public void deposit(double amount): A method that deposits the money
 *	public boolean withdraw(double amount): A method that withdraws the money.
 *	public String getAccountNumber(): gets the account number.
 *	public void setAccountNumber(String accountNumber): sets the account number.
 *	public double getBalance(): gets the balance of the account.
 *	public Customer getOwner(): gets the information of the customer.
 *	public void setOwner(Customer owner): set the information of the customer.
 *	public String toString(): converts the information of the account and customer to the String and returns it.
 *	public static void main(String[] args): A self testing main method.
 */
public class Account {
	
	//Instance Variables
	private double balance;
	private String accountNumber;
	private Customer owner;
	
	/**
	 * Default Constructor
	 */
	public Account() {
		this.balance = 0;
		this.owner  = new Customer();
		this.accountNumber = "";
		for(int i = 0; i< 12; i++) {
			int number = (int) (Math.random()*9);	//getting a random number between 0 and 9.
			this.accountNumber = this.accountNumber + number;	//storing it to the accountNumber variable.
		}
	}

	/**
	 * Overloaded Constructor
	 * @param theOwner
	 */
	public Account(Customer theOwner) {
		this.owner = theOwner;	//giving the passed Customer object to the variable that has Customer type
		this.balance = 0;
		this.accountNumber = "";
		for(int i = 0; i< 12; i++) {
			int number = (int) (Math.random()*9);	//getting a random number between 0 and 9.
			this.accountNumber = this.accountNumber + number;	//storing it to the accountNumber variable.
		}
	}

	/**
	 * A method that deposits the money to the account
	 * @param amount
	 */
	public void deposit(double amount) {
		this.balance = this.balance + amount;
	}

	/**
	 * A method that withdraws the money from the account
	 * @param amount
	 * @return
	 */
	public boolean withdraw(double amount) {
		if((this.balance - amount) >= 0) {
			this.balance = this.balance - amount;
			return true;
		}
		return false;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * This method is just used in self testing main method
	 * @return the owner
	 */
	public Customer getOwner() {
		return owner;
	}

	/**
	 * This method is just used in self testing main method
	 * @param owner the owner to set
	 */
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
	/**
	 * This method converts the information of the Account and customer objects used in this class to a String and returns it.
	 * This method is just used in self testing main method
	 */
	public String toString() {
		return "FirstName: " + owner.getFirstname() + ", LastName: " + owner.getLastname() + ", Address: " + owner.getAddress() + ", Phone Number: " + owner.getPhone() + 
				", Account Number: " + getAccountNumber() + ", Balance: " + getBalance(); 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//creating the object
		Account a1 = new Account();
		
		//testing toString method
		System.out.println(a1.toString());

		//testing the deposit method
		a1.deposit(2000);
		System.out.println(a1.toString());

		//testing the withdraw method
		if(a1.withdraw(200)) {
			System.out.println(a1.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//testing the withdraw method over balance
		if(a1.withdraw(2200)) {
			System.out.println(a1.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//Changing the owner's information of the account
		Customer c1 = new Customer("Foram<>Patel<>Brampton<>6475678903");
		a1.setOwner(c1);
		System.out.println(a1.toString());

		Customer c2 = new Customer("Xyz<>Abx<>Toronto<>6475631562");
		Account a2 = new Account(c2);
		System.out.println("");
		
		//testing toString method
		System.out.println(a2.toString());

		//testing the deposit method
		a2.deposit(5000);
		System.out.println(a2.toString());

		//testing the withdraw method
		if(a2.withdraw(2000)) {
			System.out.println(a2.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//testing the withdraw method over balance
		if(a2.withdraw(4200)) {
			System.out.println(a2.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}
	}

}
