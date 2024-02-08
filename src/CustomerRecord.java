/*
 * Author: Sparsh Soni
 * Date: 2021/06/19
 * Description: The customer record class
 */

public class CustomerRecord extends Customer {
	
	private String savingacc, chequingacc;
	private double savingbal, chequingbal;
	private String login, password;

	public CustomerRecord() {
		super();
		this.login = "";
		this.password = "";
		this.savingacc = "";
		this.chequingacc = "";
		this.savingbal = 0;
		this.chequingbal = 0;
	}
	
	public CustomerRecord(String records) {
		super(records.split("<>")[2] + "<>" + records.split("<>")[3] + "<>" + records.split("<>")[4] + "<>" + records.split("<>")[5]);
		processRecord(records);
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param chequingacc the chequingacc to set
	 */
	public void setChequingacc(String chequingacc) {
		this.chequingacc = chequingacc;
	}

	public void processRecord(String input) {
		String record[] = input.split("<>");
		this.login = record[0];
		this.password = record[1];
		this.savingacc = record[6];
		this.chequingacc = record[8];
		this.savingbal = Double.parseDouble(record[7]);
		this.chequingbal = Double.parseDouble(record[9]);
	}

	//return savings account
	public String getSavingacc() {
		return savingacc;
	}

	
	 // the saving account to set
	
	public void setSavingacc(String savingacc) {
		this.savingacc = savingacc;
	}

	// return chequing account
	public String getChequingacc() {
		return chequingacc;
	}
	
	 //saving balance
	 
	public double getSavingbal() {
		return savingbal;
	}

	
	 // saving balance the saving balance to set
	 
	public void setSavingbal(double savingbal) {
		this.savingbal = savingbal;
	}

	
	 // return chequing balance
	 
	public double getChequingbal() {
		return chequingbal;
	}

	
	 // chequing balance the chequing balance to set
	 
	public void setChequingbal(double chequingbalance) {
		this.chequingbal = chequingbalance;
	}

	public String toString() {
		return "[Login ID: " + login + ", Password: " + password +", First Name: " + super.getFirstname() + ", Last Name: " + super.getLastname() + ", Address: " + super.getAddress() + ", Phone No.: " + super.getPhone()
		+ ", Savings Account No.: " + getSavingacc() + ", Balance: " + getSavingbal() + ", chequing Account No.: " + getChequingacc()
		+ ", Balance: " + getChequingbal() +"]";
	}

	public static void main(String[] args) 
	{
		
				String info = "bill<>bob<>Bill<>Bob<>123 hello street<>1234567890<>123456789012<>100<>567890123456<>100";
				
				
				CustomerRecord cusRecord = new CustomerRecord(info);

				
				System.out.println(cusRecord.toString());

				
				cusRecord.setFirstname("Sammy");
				cusRecord.setLastname("Asdd");

				
				System.out.println(cusRecord.toString());

	}

}