/**
 * 
 */

/**
 * @author Foram Patel
 * Date: June 20, 2021
 * Description: A class that creates the Savings account and does transactions. Fee is not being calculated over here as there are changes that other bank uses this class and has other fee structure.
 * 		Fee is being calculated in the CustomerGUI part.
 * Methods / Constructors: public Savings(): A default constructor that initialize the variable and calls the super constructor.
 * 	public Savings(Customer theOwner): An Overloaded constructor that initialize the variable and calls the super constructor.
 *	public boolean withdraw(double amount): A method that withdraws the money from the savings account. Overrides the method of super class.
 *	public double getFee(): gets the fee for the transaction.
 *	public void setFee(double fee): sets the fee for the transaction.
 *	public double getMinBalance(): gets the minimum balance.
 *	public void setMinBalance(double minBalance): sets the minimum balance.
 *	public String toString(): Converts the information of the savings account and customer to String and returns it.
 *	public static void main(String[] args): A self testing main method.
 */
public class Savings extends Account {
	
	//Instance Variables
	private double fee, minBalance;
	
	/**
	 * Default Constructor
	 */
	public Savings() {
		super();	//calling the constructor of the super class
		this.fee = 0;
		this.minBalance = 5000;
	}
	
	/**
	 * Overloaded Constructor
	 * @param theOwner
	 */
	public Savings(Customer theOwner) {
		super(theOwner);	//calling the constructor of the super class with a Customer object in it.
		this.fee = 0;
		this.minBalance = 5000;
	}
	
	/**
	 * This method is used to withdraw money in the saving account of the customer.
	 */
	public boolean withdraw(double amount) {
		if((getBalance() - amount - getFee()) >= 0) {	//checks if the balance will be in positive or not even after withdrawing the money.
			amount = amount + getFee();	//calculates the total amount to be deduct after including fees.
			super.withdraw(amount);	//calls the withdraw method of the super class to withdraw the money.
			return true;
		}
		return false;	//returns false if money is not being withdrawn.
	}

	/**
	 * @return the fee
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}

	/**
	 * @return the minBalance
	 */
	public double getMinBalance() {
		return minBalance;
	}

	/**
	 * @param minBalance the minBalance to set
	 */
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	/**
	 * This method converts the information of the customer and Saving accounts to the string and returns the string.
	 * This method is just used in self testing main method
	 */
	public String toString() {
		return super.toString() + ", Fee: " + getFee() + ", Minimum Balance Required for wavier while withdrawing: " + getMinBalance();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Savings s1 = new Savings();
		
		//testing toString method
		System.out.println(s1.toString());

		//testing the deposit method
		s1.deposit(2000);
		System.out.println(s1.toString());
		
		//calculating the fees
		if(s1.getMinBalance()> s1.getBalance()) {
			s1.setFee(3.25);
		}
		else
			s1.setFee(0);

		//testing the withdraw method
		if(s1.withdraw(200)) {
			System.out.println(s1.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}
		
		//calculating the fees
		if(s1.getMinBalance()> s1.getBalance()) {
			s1.setFee(3.25);
		}
		else
			s1.setFee(0);

		//testing the withdraw method over balance
		if(s1.withdraw(2200)) {
			System.out.println(s1.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//Changing the owner's information of the account
		Customer c1 = new Customer("Foram<>Patel<>Brampton<>6475678903");
		s1.setOwner(c1);
		System.out.println(s1.toString());
		
		//creating new object of the customer class
		Customer c2 = new Customer("Xyz<>Abx<>Toronto<>6475631562");
		Savings s2 = new Savings(c2);	//creating overloaded object
		System.out.println("");
		s2.setMinBalance(7000);	//setting minimum balance

		//testing toString method
		System.out.println(s2.toString());

		//testing the deposit method
		s2.deposit(5000);
		System.out.println(s2.toString());
		
		//calculating the fees
		if(s2.getMinBalance()> s2.getBalance()) {
			s2.setFee(3.25);
		}
		else
			s2.setFee(0);
		
		//testing the withdraw method
		if(s2.withdraw(2000)) {
			System.out.println(s2.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}
		
		//calculating the fees
		if(s2.getMinBalance()> s2.getBalance()) {
			s2.setFee(3.25);
		}
		else
			s2.setFee(0);
		
		//testing the withdraw method over balance
		if(s2.withdraw(4200)) {
			System.out.println(s2.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

	}

}
