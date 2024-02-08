import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author Foram Patel
 * Date: June 20, 2021
 * Description: A class that creates the Chequing account and does transactions. Fee is not being calculated over here as there are changes that other bank uses this class and has other fee structure.
 * 			Fee is being calculated in the CustomerGUI part.
 * Methods / Constructors: public Chequing(): A default constructor that initialize the variable and calls the super constructor.
 * 	public Chequing(Customer theOwner): An Overloaded constructor that initialize the variable and calls the super constructor.
 * 	public boolean withdraw(double amount): A method that withdraws the money from the chequing account. Overrides the method of super class.
 * 	public void deposit(double amount): A method that deposits the money to the chequing account. Overrides the method of super class.
 * 	public double getFee(): gets the fee for the transaction.
 * 	public void setFee(double fee): sets the fee for the transaction.
 * 	public String toString(): Converts the information of the chequing account and customer to String and returns it.
 * 	public static void main(String[] args): A self testing main method.
 */
public class Chequing extends Account{
	
	//Instance Variable
	private double fee;
	
	/**
	 * Default Constructor
	 */
	public Chequing() {
		super();	//calling the constructor of super class
		fee = 0;
	}

	/**
	 * Overloaded Constructor
	 * @param theOwner
	 */
	public Chequing(Customer theOwner) {
		super(theOwner);	//calling the constructor of the super class with variable of the Customer class.
		fee = 0;
	}

	/**
	 * This method is used to withdraw money from the chequing account
	 */
	public boolean withdraw(double amount) {
		if((getBalance() - amount - getFee()) >= 0) {	//checks for the positive balance after charging the fee and withdrawing the money.
			amount = amount + getFee();	//determines total amount to be deducted after including the fee.
			super.withdraw(amount);	//calls the withdraw method of the super class to withdraw money.
			return true;
		}
		return false;	//money is not sufficient
	}

	/**
	 * This method is used to deposit money to the chequing account.
	 */
	public void deposit(double amount) {
		if((getBalance() + amount - getFee()) >= 0) {	//checks for the positive balance after charging the fee and depositing the money.
			amount = amount - getFee();	//determine the total amount to be added after deducting the fee.
			super.deposit(amount);	//calls the deposit method of the super class to deposit money.
		}
	}

	/**
	 * @return the fee
	 */
	public double getFee() {
		DecimalFormat twoDigit = new DecimalFormat("#.##");
		this.fee = Double.parseDouble(twoDigit.format(this.fee));	//convert the fee to 2 decimal points
		return this.fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(double fee) {
		DecimalFormat twoDigit = new DecimalFormat("#.##");
		this.fee = Double.parseDouble(twoDigit.format(fee));	//convert the fee to 2 decimal points
	}

	/**
	 * This method converts the information of the Customer and chequing account to the String and returns it.
	 * This method is just used in self testing main method
	 */
	public String toString() {
		return super.toString() + ", Fee Charged: " + getFee();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Chequing c1 = new Chequing();
		
		//testing toString method
		System.out.println(c1.toString());
		
		//setting the fee
		c1.setFee(1.5);
		double amount = 2000;
		//testing the deposit method
		if(c1.getBalance() - c1.getFee() + amount >= 0) {
			c1.deposit(amount);
			System.out.println(c1.toString());
		}

		//setting the fee
		c1.setFee((0.005 * c1.getBalance()));
		//testing the withdraw method
		if(c1.withdraw(200)) {
			System.out.println(c1.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//setting the fee
		c1.setFee((0.005 * c1.getBalance()));
		//testing the withdraw method over balance
		if(c1.withdraw(2200)) {
			System.out.println(c1.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//Changing the owner's information of the account
		Customer cs1 = new Customer("Foram<>Patel<>Brampton<>6475678903");
		c1.setOwner(cs1);
		System.out.println(c1.toString());

		//creating the new object of Customer class
		Customer cs2 = new Customer("Xyz<>Abx<>Toronto<>6475631562");
		Chequing c2 = new Chequing(cs2);	//Creating a overloaded object of Chequing class
		System.out.println("");

		//testing toString method
		System.out.println(c2.toString());

		//setting the fee
		c2.setFee(1.5);
		amount = 5000;
		//testing the deposit method
		if(c1.getBalance() - c1.getFee() + amount >= 0) {
			c2.deposit(5000);
			System.out.println(c2.toString());
		}
		
		//setting the fee
		c2.setFee((0.005 * c2.getBalance()));
		//testing the withdraw method
		if(c2.withdraw(2000)) {
			System.out.println(c2.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

		//setting the fee
		c2.setFee((0.005 * c2.getBalance()));
		//testing the withdraw method over balance
		if(c2.withdraw(4200)) {
			System.out.println(c2.toString());
		}
		else {
			System.out.println("Cannot withdaw money, Insufficient Funds");
		}

	}

}
