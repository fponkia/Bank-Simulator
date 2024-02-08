/**
 * 
 */

/**
 * @author Foram Patel
 * Date: June 20, 2021
 * Description: A Class that stores information of the customer names, address, and phone number.
 * Methods / Constructors: public Customer(): A default constructor to initial the variables
 * 	public Customer(String record): An Overloaded constructor to initial the variables by calling another method.
 *	public void processrecord(String record): A method that separates the information and assign the information to the appropriate variables.
 *	public String getFirstname(): gets the first name.
 *	public void setFirstname(String firstname): sets the first name.
 *	public String getLastname(): gets the last name.
 *	public void setLastname(String lastname): sets the last name.
 *	public String getAddress(): gets the address.
 *	public void setAddress(String address): sets the address.
 *	public String getPhone(): gets the phone number.
 *	public void setPhone(String phone): sets the phone number.
 *	public String toString(): convert the above information to a String.
 *	public static void main(String[] args): A self testing main method.
 */
public class Customer {
	
	//instance Variables
	private String firstname, lastname, address, phone;
	
	/**
	 * Default Constructor
	 */
	public Customer() {
		firstname = "";
		lastname = "";
		address = "";
		phone = "";
	}
	
	/**
	 * Overloaded Constructor
	 * @param record
	 */
	public Customer(String record) {
		processrecord(record);
	}
	
	/**
	 * Processes the information of the customer and assigns appropriate information to the variables.
	 * @param record
	 */
	public void processrecord(String record) {
		String array[] = record.split("<>");	//splitting the information
		//assign the appropriate information to the variables.
		this.firstname = array[0];
		this.lastname = array[1];
		this.address = array[2];
		this.phone = array[3];
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * This method converts the information of the Customer to a String and returns it.
	 */
	public String toString() {
		return "FirstName: " + getFirstname() + ", LastName: " + getLastname() + ", Address: " + getAddress() + ", Phone Number: " + getPhone();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//creating the default object
		Customer c1 = new Customer();
		
		//testing the toString method
		System.out.println(c1.toString());
			
		//adding customer information to the existing object
		String input = "Foram<>Patel<>xyz location<>6477360489";
		c1.processrecord(input);
		
		System.out.println(c1.toString());
		
		//testing setter method
		c1.setFirstname("Abc");
		c1.setLastname("Xyz");
		c1.setAddress("Bramption");
		c1.setPhone("6470905049");
		
		System.out.println(c1.toString());
		
		//creating another object
		Customer c2 = new Customer("MyName<>YourName<>Toronto<>6479830957");
		
		System.out.println(c2.toString());

	}

}
