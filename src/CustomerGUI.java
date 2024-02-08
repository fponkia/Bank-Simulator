import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Foram Patel
 * Date: June 20, 2021
 * Description: An User interface part for the Customer of the Bank.
 * Methods / Constructors: public CustomerGUI(): A Constructor that creates the frame for the customer part of the Bank.
 * 	public void storeinFile(String fileName, String line[]) throws IOException: A method that stores the information to the file.
 * 	public String[] loader(String fileName) throws IOException: A method that loads the information from the file.
 * 	public int linearSearch(String logins[], String passwords[]): A method that searches for the customer in the file.
 * 	public void update(): A method that updates the JTextArea.
 * 	public void storetransaction(): A method that stores the transactions to the file of the customer.
 * 	public void actionPerformed(ActionEvent e): A method that gets called when any button of the JFrames get clicked.
 * 	public static void main(String[] args): A method that creates the object to call the constructor.
 */
public class CustomerGUI extends JFrame implements ActionListener{
	
	//Instance Variables
	private JButton register, login, enter, done, exit, back, back1, withdraw, deposit, changeinformation, saveinformation, logout, cancel, exit1;
	private JLabel customerlbl, firstlbl, lastlbl, addresslbl, phonelbl, logidlbl, passwordlbl, instruction3;
	private JPanel informationPanel, controlPanel, inputPanel, controlPanel3, controlPanel1, inputPanel1, controlPanel2, inputPanel2;
	private int size, maxSize, position;
	private Customer owner[];
	private Savings save[];
	private Chequing cheq[];
	private String lines [], templogid, temppassword, transaction;
	private JTextField first, last, address, phone, logid, password;
	private JFrame frame, frame1, frame2, frame3;
	private JTextArea display;
	private JScrollPane scroller;
	
	/**
	 * Default Constructor
	 */
	public CustomerGUI() {
		super("Customer GUI");
		setLayout(new GridLayout(2,1));	//setting layout and size of the JFrame
		setSize(300, 150);
		
		informationPanel = new JPanel();	//creating JPanels
		controlPanel = new JPanel();
		
		this.size = 0;	//size of the customers initially
		this.maxSize = 1000;	//maximum size of the customers and accounts handle by the program.
		//creating object of array to the size of maxSize
		owner = new Customer[maxSize];
		save = new Savings[maxSize];
		cheq = new Chequing[maxSize];
		
		//loading the accounts and information of the customer that were previously created and tranactions were done.
		try {
			lines = loader("logins.txt");	//loading the customer information from the files
			String information[] = new String [10];	//creating an array that stores the information stored in the line.
			for(int i =0; i <lines.length; i++) {	//runs the loop till the length of the lines stored from the file.
				if(i < this.maxSize) {	//determines if the array if full or not
					information = lines[i].split("<>");	//splits the information stored in line to array when "<>" matches.
					String record = information[2] + "<>" + information[3] + "<>" + information[4] + "<>" + information[5];	//stores the information of the customer this way.
					Customer c1 = new Customer(record);	//creates the object of the customer and sends the record.
					Savings s1 = new Savings(c1);	//creates the saving account object
					Chequing ch1 = new Chequing(c1);	//creates the chequing account object
					owner[this.size] = c1;	//stores the customer object to the customer array object.
					save[this.size] = s1;	//stores the savings object to the saving array object.
					save[this.size].setAccountNumber(information[6]);	//sets the account number of the saving accounts to the one that was stored in the file, which is previously used.
					save[this.size].deposit(Double.parseDouble(information[7]));	//deposits the initially money the account had previously when the transactions were done.
					cheq[this.size] = ch1;	//stores the chequing object to the chequing array object.
					cheq[this.size].setAccountNumber(information[8]);	//sets the account number of the chequing accounts to the one that was stored in the file, which is previously used.
					cheq[this.size].deposit(Double.parseDouble(information[9]));	//deposits the initially money the account had previously when the transactions were done.
					size++;	//increases the valid size of the array objects.
				}
				else {
					JOptionPane.showMessageDialog(null, "Cannot load all the Customer list, Only first 1000 were added, server overloaded");
					break;
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Not Found");
		}
		
		//creating JLabel and JButton for the frame
		customerlbl = new JLabel("Do you want to do:");
		customerlbl.setFont(new Font("Georgia", Font.PLAIN, 20));
		customerlbl.setForeground(Color.MAGENTA); //magenta text color
		register = new JButton("Register");
		login = new JButton("Login");
		exit1 = new JButton("Exit");
		
		//adding listerner to the buttons
		register.addActionListener(this);
		login.addActionListener(this);
		exit1.addActionListener(this);
		
		//adding JLabel and JButtons to the panel
		informationPanel.add(customerlbl);
		controlPanel.add(register);
		controlPanel.add(login);
		controlPanel.add(exit1);
		
		add(informationPanel);	//adding panels to the JFrame
		add(controlPanel);
		
		setLocation(100, 100);	//setting Location of the JFrame on the screen
		setVisible(true);	//setting the frame to visible
		setResizable(false);	//resizable to false
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Stores the record or Customer information to a file.
	 * @param fileName
	 * @throws IOException
	 */
	public void storeinFile(String fileName, String line[]) throws IOException {
		//open the file to write
		PrintWriter fw = new PrintWriter(new FileWriter(fileName));

		//store each record of the list in the new file
		for(int i=0;i<line.length;i++) {
			fw.println(line[i]);
		}
		fw.close();//close the file stream
	}

	/**
	 * Loads the record found in the file.
	 * @param fileName
	 * @throws IOException
	 */
	public String[] loader(String fileName) throws IOException {
		// opens file for reading
		BufferedReader fr = new BufferedReader(new FileReader(fileName));
		int lineCount = 0;
		String line = fr.readLine();		//reads first line.

		// loop to count the number of lines in the file
		while (line != null)		// while not a null character
		{
			lineCount++;
			line = fr.readLine();
		}
		fr.close();		// close the file

		String lines[] = new String[lineCount];
		// re-open the file to read
		fr = new BufferedReader(new FileReader(fileName));

		//loop to fill and display the array
		for (int i = 0; i < lineCount; i++)
		{
			lines[i] = fr.readLine();
		}
		// close file
		fr.close();
		return lines;
	}
	
	/**
	 * This method matches the login id, password, first and last name and returns the location
	 * @param logins
	 * @param passwords
	 * @return
	 */
	public int linearSearch(String logins[], String passwords[]) {
		for(int i = 0;i < this.size; i++) {
			//if login id, password, first name and last name matches with the one of the customer that we have than returns the location at which the record was found.
			if(logid.getText().equals(logins[i]) && password.getText().equals(passwords[i]) && owner[i].getFirstname().equalsIgnoreCase(first.getText()) && owner[i].getLastname().equalsIgnoreCase(last.getText())) {
				return i;	//returns the location
			}
		}
		return -1;	//return invalid index. Location not found
	}

	/**
	 * this method updates the JTextArea on the screen.
	 */
	public void update() {
		String output = "";
		//gets the updated information of the customer to a string variable output.
		output = output + "First Name: " + owner[position].getFirstname() + "\n"
				+ "Last Name: " + owner[position].getLastname() + "\n"
				+ "Address: " + owner[position].getAddress() + "\n"
				+ "Phone Number: " + owner[position].getPhone() + "\n"
				+ "Saving Account Number: " + save[position].getAccountNumber() + "\n"
				+ "Saving Account Balance: " + save[position].getBalance() + "\n"
				+ "Chequing Account Number: " + cheq[position].getAccountNumber() + "\n"
				+ "Chequing Account Balance: " + cheq[position].getBalance();
		display.setText(output);	//sets the JTextArea to the variable output
		//updates the information of the customer to the array that stores each information of all the customer from file logins.txt.
		lines [position] = logid.getText() + "<>" + password.getText() + "<>" + 
				owner[position].getFirstname()+ "<>" + owner[position].getLastname() + "<>" + owner[position].getAddress() + "<>" + owner[position].getPhone() + "<>" +
				save[this.position].getAccountNumber() + "<>" + save[this.position].getBalance() + "<>" + cheq[this.position].getAccountNumber() + "<>" + cheq[this.position].getBalance();
	}
	
	/**
	 * Stores the transaction to the file with the previous transactions and also updates the balances in the file.
	 */
	public void storetransaction() {
		try {
			storeinFile("logins.txt", lines);	//stores the array that has updated information to the file.
			String loadingtransaction[] = loader(owner[position].getFirstname() + owner[position].getLastname() +  save[position].getAccountNumber() + cheq[position].getAccountNumber() + ".txt");	//gets all the previous transactions of the customer.
			String loadingnewtransaction[] = new String [loadingtransaction.length + 1];	//creating new array that has size 1 more than the array above.
			for(int i = 0;i <loadingtransaction.length; i++) {
				loadingnewtransaction[i] = loadingtransaction[i];	//adding all the information of the previous transactions to the new array.
			}
			loadingnewtransaction[loadingtransaction.length] = transaction;	//adding the last transaction done to the last index of the new array.
			storeinFile(owner[position].getFirstname() + owner[position].getLastname() +  save[position].getAccountNumber() + cheq[position].getAccountNumber() + ".txt", loadingnewtransaction);	//storing the updated transaction to the same file again.
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "Transactions were not stored");
		}
	}
	
	/**
	 * This method gets performed when a button is being clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == register) {
			setVisible(false);	//setting the old frame visibility to false
			frame = new JFrame("Royal Bank of Canada");
			frame.setLayout(new GridLayout(2,1));	//setting the layout and size of the new JFrame
			frame.setSize(750, 300);
			
			//creating sub panels
			inputPanel = new JPanel();
			controlPanel3 = new JPanel();
			
			//creating JLabels
			instruction3 = new JLabel("                                                                Welcome to The Royal Bank of Canada                                                             ");
			instruction3.setFont(new Font("Georgia", Font.PLAIN, 20));
			instruction3.setForeground(Color.DARK_GRAY); //gray text color
			firstlbl = new JLabel("Enter the First Name");
			lastlbl = new JLabel("Enter the last Name");
			addresslbl = new JLabel("Enter the address");
			phonelbl = new JLabel("Enter the phone number");
			logidlbl = new JLabel("Enter the Login ID");
			passwordlbl = new JLabel("Enter the password");

			//creating the JTextField that are used to enter information
			first = new JTextField("Enter Here...", 25);
			last = new JTextField("Enter Here...", 25);
			address = new JTextField("Enter Here...", 25);
			phone = new JTextField("1234567890", 25);
			logid = new JTextField("Enter Here...", 25);
			password = new JTextField("Enter Here...", 25);

			//creating buttons
			done = new JButton("Done and Register");
			exit = new JButton("Exit");
			back = new JButton("Back");

			//adding listener to the buttons
			done.addActionListener(this);
			exit.addActionListener(this);
			back.addActionListener(this);

			//adding all JLabels, JTextFields, and buttons to the appropriate panels.
			inputPanel.add(instruction3);
			inputPanel.add(firstlbl);
			inputPanel.add(first);
			inputPanel.add(lastlbl);
			inputPanel.add(last);
			inputPanel.add(addresslbl);
			inputPanel.add(address);
			inputPanel.add(phonelbl);
			inputPanel.add(phone);
			inputPanel.add(logidlbl);
			inputPanel.add(logid);
			inputPanel.add(passwordlbl);
			inputPanel.add(password);
			controlPanel3.add(done);
			controlPanel3.add(back);
			controlPanel3.add(exit);
			
			frame.add(inputPanel);	//adding JPanels to the frame.
			frame.add(controlPanel3);
			
			frame.setVisible(true);	//setting the frame to visible
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		else if(e.getSource() == back) {
			frame.setVisible(false);	//sets the frame to not visible
			setVisible(true);	//sets the old frame to visible
		}
		
		else if(e.getSource() == back1) {
			frame1.setVisible(false);	//sets the frame to not visible
			setVisible(true);	//sets the old frame to visible
		}
		
		else if(e.getSource() == done) {
			if(this.size < this.maxSize) {	//checks if the new register is within the limit of the array or not.
				String newlines = logid.getText() + "<>" + password.getText() + "<>" + 
						first.getText()+ "<>" + last.getText() + "<>" + address.getText() + "<>" + phone.getText();	//gets the customer information into one string that will be used as the record.
				String information[] = newlines.split("<>");	//splitting the record (newlines) into an array
				String record = information[2] + "<>" + information[3] + "<>" + information[4] + "<>" + information[5];	//stores the 4 information needed by the customer class to a variable called record.
				Customer c1 = new Customer(record);	//creates the customer class object
				Savings s1 = new Savings(c1);	//creates the saving class object
				Chequing ch1 = new Chequing(c1);	//create the chequing object
				owner[this.size] = c1;	//adding the customer object to the array of customer object
				save[this.size] = s1;	//adding the saving object to the array of saving object
				cheq[this.size] = ch1;	//adding the chequing object to the array of chequing object
				size++;	//increases the valid size of the array of objects.

				//creating a file that save transactions for the saving and chequing accounts of the particular customer.
				File createFile = new File(first.getText() + last.getText() +  save[this.size-1].getAccountNumber() + cheq[this.size-1].getAccountNumber() + ".txt");
				try {
					createFile.createNewFile();	//creating the text file.
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "File and account were not able to create because it already exists");
				}

			}
			else {
				JOptionPane.showMessageDialog(null, "New Customer Cannot be register as server is full");	//displays that server is full, means array is full
			}

			try {
				String line[] = loader("logins.txt");	//loads each line of the file that stores the information of customer, saving and chequing account, login id and password.
				lines = new String [line.length+1];	//making a new array of size 1 more than the records we loaded.
				for(int i =0;i<line.length;i++) {
					lines[i] = line[i];	//stores the loaded array to the new array
				}
				//stores the information of the new register to the last index of the new array
				lines [lines.length-1] = logid.getText() + "<>" + password.getText() + "<>" + 
						first.getText()+ "<>" + last.getText() + "<>" + address.getText() + "<>" + phone.getText() + "<>" +
						save[this.size-1].getAccountNumber() + "<>" + save[this.size-1].getBalance() + "<>" + cheq[this.size-1].getAccountNumber() + "<>" + cheq[this.size-1].getBalance();
				storeinFile("logins.txt", lines);	//stores the new array to the file again.
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "File Not Found");
			}
			frame.setVisible(false);
			setVisible(true);
		}
		
		else if(e.getSource() == login) {
			setVisible(false);	//setting the old frame to not visible.
			frame1 = new JFrame("Login");
			frame1.setLayout(new GridLayout(2,1));	//setting the layout and size of the new frame
			frame1.setSize(750, 300);
			
			inputPanel1 = new JPanel();	//creating subpanels for the frame.
			controlPanel2 = new JPanel();
			
			//creating JLabels for the frame
			instruction3 = new JLabel("                                                                Welcome to The Royal Bank of Canada                                                             ");
			instruction3.setFont(new Font("Georgia", Font.PLAIN, 20));
			instruction3.setForeground(Color.DARK_GRAY); //gray text color
			firstlbl = new JLabel("Enter the First Name");
			lastlbl = new JLabel("Enter the last Name");
			logidlbl = new JLabel("Enter the Login ID");
			passwordlbl = new JLabel("Enter the password");

			//creating the JTextField that are used to enter information
			first = new JTextField("Enter Here...", 25);
			last = new JTextField("Enter Here...", 25);
			logid = new JTextField("Enter Here...", 25);
			password = new JTextField("Enter Here...", 25);
			
			//creating and adding listener to the buttons for the JFrame
			enter = new JButton("Enter and Login");
			enter.addActionListener(this);
			back1 = new JButton("Back");
			back1.addActionListener(this);
			exit = new JButton("Exit");
			exit.addActionListener(this);
			
			//adding the JLables and JButtons to the JPanels.
			inputPanel1.add(instruction3);
			inputPanel1.add(firstlbl);
			inputPanel1.add(first);
			inputPanel1.add(lastlbl);
			inputPanel1.add(last);
			inputPanel1.add(logidlbl);
			inputPanel1.add(logid);
			inputPanel1.add(passwordlbl);
			inputPanel1.add(password);
			controlPanel2.add(enter);
			controlPanel2.add(back1);
			controlPanel2.add(exit);
			
			frame1.add(inputPanel1);	//adding JPanels to the frame.
			frame1.add(controlPanel2);
			
			frame1.setVisible(true);	//setting the new frame to visible.
			frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		else if(e.getSource() == enter) {
			try {
				String line[] = loader("logins.txt");	//gets the lines or information stored of the existing customers in the file.
				String separatelines[] = new String [10];	//an array that separates each information from a line to different parts.
				String logins[] = new String [line.length];	//an array that stores the login id of all the customers.
				String passwords[] = new String [line.length];	//an array that stores the password of all the customer
				for(int i = 0;i < line.length; i++) {
					separatelines = line[i].split("<>");	//separates a line into an array when <> comes.
					logins[i] = separatelines[0];	//stores the login id of the customers.
					passwords[i] = separatelines[1];	//stores the password of the customers.
				}
				position = linearSearch(logins, passwords);	//gets the position at which the login id and password matches, with first and last name.
				if(position < 0) {
					JOptionPane.showMessageDialog(null, "Login ID or password is incorrect or you might have not enter your first and last name");	//if ID or password is wrong
				}
				else{
					frame1.setVisible(false);	//sets the frame to invisible
					JOptionPane.showMessageDialog(null, "Welcome " + owner[position].getFirstname() + " " + owner[position].getLastname());	//shows the welcome dialog
					frame3 = new JFrame("Welcome "+ owner[position].getFirstname() + " " + owner[position].getLastname());
					frame3.setSize(700, 500);	//sets the size and layout of the frame.
					frame3.setLayout(new GridLayout(2,1));
					
					//creating the JButtons
					deposit = new JButton("Deposit Money");
					withdraw = new JButton("Withdraw Money");
					changeinformation = new JButton("Change Customer Information");
					logout = new JButton("Logout and Exit");

					//Adding listener to the buttons
					deposit.addActionListener(this);
					withdraw.addActionListener(this);
					changeinformation.addActionListener(this);
					logout.addActionListener(this);

					//creating the JTextArea and JScrollPane and setting it editable false
					display = new JTextArea("", 20, 5);
					display.setEditable(false);
					scroller = new JScrollPane(display);

					update();	//calling the method update that updates the contents of the JTextArea.
					controlPanel1 = new JPanel();	//creating the JPanel for buttons
					//adding the buttons to the JPanel
					controlPanel1.add(deposit);
					controlPanel1.add(withdraw);
					controlPanel1.add(changeinformation);
					controlPanel1.add(logout);

					//adding scroller and controlPanel1 to the JFrame
					frame3.add(scroller);
					frame3.add(controlPanel1);

					frame3.setVisible(true);	//setting the visibility of the frame to true
					frame3.setDefaultCloseOperation(EXIT_ON_CLOSE);
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "File Not Found");
			}
		}
		
		else if(e.getSource() == deposit) {
			//asking the customer for the amount to be deposited and to which account saving or chequing.
			double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter amount that needs to be deposited"));
			char command = JOptionPane.showInputDialog(null, "To which account do you want to deposit.\nEnter 'c' for chequing\nEnter 's' for saving").charAt(0);
			switch(command) {
			case 's':
			case 'S':{
				save[position].setFee(0);	//setting the fee to the 0 as depositing money to saving account does not have any fee.
				save[position].deposit(amount);	//deposit the amount to the saving account
				update();	//updates the JTextArea
				JOptionPane.showMessageDialog(null, "Money is being deposited");	//a message dialog that shows that money is being deposited
				transaction = "Savings Account - Money Deposited: " + amount + ", Fee charged: 0.0, Balance: " + save[position].getBalance();	//stores the detail of the transaction.
				storetransaction();	//calls the method that stores this transaction to the file of the customer with the previous transaction. And also updates the balance information to the file that has all the customers information.
				break;
			}
			case 'c':
			case 'C':{
				cheq[position].setFee(1.5);	//sets the fee to 1.5 dollars
				if(cheq[position].getBalance() - cheq[position].getFee() + amount >= 0) {	//checks if the depositing money can be done or not. positive balance or not.
					cheq[position].deposit(amount);	//deposits the money to the chequing account
					JOptionPane.showMessageDialog(null, "Money is being deposited");
					transaction = "Chequing Account - Money Deposited: " + amount + ", Fee charged : " + cheq[position].getFee() + ", Balance: " + cheq[position].getBalance();
					update();	//updates the JTextArea
					storetransaction();	//calls the method that stores this transaction to the file of the customer with the previous transaction. And also updates the balance information to the file that has all the customers information.
				}
				else {
					JOptionPane.showMessageDialog(null, "Money is not being deposited becasue you dont have enough funds to pay the fee");	//shows a dialog that says that money is not being deposited as balance was going to negative
				}
				break;
			}
			default:{
				JOptionPane.showMessageDialog(null, "Money is not being deposited because you have not enter correctly in which account you want to deposit money");	//dialog box appears if correct account is not being entered.
			}
			}
		}

		else if(e.getSource() == withdraw) {
			//asking the customer for the amount to be withdrawn and from which account saving or chequing.
			double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter amount that needs to be withdrawn"));
			char command = JOptionPane.showInputDialog(null, "From which account do you want to withdrawn.\nEnter 'c' for chequing\nEnter 's' for saving").charAt(0);
			switch(command) {
			case 's':
			case 'S':{
				if(save[position].getBalance() < save[position].getMinBalance()) {	//checks if the existing balance is less than minimum balance or not
					save[position].setFee(3.25);	//sets the fee to 3.25 dollars
				}
				else
					save[position].setFee(0);	//sets the fee to 0 as balance is above minimum balance.
				if(save[position].withdraw(amount)) {	//withdraws the money from the saving account
					update();	//updates the JTextArea if successful
					JOptionPane.showMessageDialog(null, "Money is being Withdrawn");
					transaction = "Savings Account - Money Withdrawn: " + amount + ", Fee charged: " + save[position].getFee() + ", Balance: " + save[position].getBalance();
					storetransaction();	//calls the method that stores this transaction to the file of the customer with the previous transaction. And also updates the balance information to the file that has all the customers information.
				}
				else {
					JOptionPane.showMessageDialog(null, "Money is not being Withdrawn as there are insufficient funds after deducting the fee");	//There are insufficient funds to withdraw money
				}
				break;
			}
			case 'c':
			case 'C':{
				cheq[position].setFee(0.005 * cheq[position].getBalance());	//sets the fee to 0.5% of the current balance
				if(cheq[position].withdraw(amount)) {	//withdraws the money
					JOptionPane.showMessageDialog(null, "Money is being Withdrawn");
					update();	//updates the JTextArea if successful
					transaction = "Chequing Account - Money Withdrawn: " + amount + ", Fee charged: " + cheq[position].getFee() + ", Balance: " + cheq[position].getBalance();
					storetransaction();	//calls the method that stores this transaction to the file of the customer with the previous transaction. And also updates the balance information to the file that has all the customers information.
				}
				else {
					JOptionPane.showMessageDialog(null, "Money is not being withdrawn becasue you dont have enough funds to complete the transaction");	//There are insufficient funds to withdraw money
				}
				break;
			}
			default:{
				JOptionPane.showMessageDialog(null, "Money is not being withdrawn because you have not enter correctly from which account you want to withdraw money");	//dialog box appears if correct account is not being entered.
			}
			}
		}

		else if(e.getSource() == changeinformation) {

			frame3.setVisible(false);	//sets the frame to invisible
			frame2 = new JFrame("Changing Information");	//creating new JFrame
			frame2.setLayout(new GridLayout(2,1));	//setting the layout of the new JFrame and size as well
			frame2.setSize(400, 500);

			inputPanel2 = new JPanel();	//creating JPanel for the JFrame
			controlPanel2 = new JPanel();
			
			//creating the Labels for the address and phone as these were not creating during login.
			addresslbl = new JLabel("Enter the address");
			phonelbl = new JLabel("Enter the phone number");
			address = new JTextField("Enter Here...", 25);
			phone = new JTextField("Enter Here...", 25);

			//setting the text of the JTextFields that are shown in the JFrame to the information of the customer.
			first.setText(owner[position].getFirstname());
			last.setText(owner[position].getLastname());
			address.setText(owner[position].getAddress());
			phone.setText(owner[position].getPhone());
			logid.setText(logid.getText());
			password.setText(password.getText());
			
			//gets the login id and password of the customer to another variable for temporary basis.
			templogid = logid.getText();
			temppassword = password.getText();

			//creating JButtons and adding listener to it.
			saveinformation = new JButton("Save");
			saveinformation.addActionListener(this);
			cancel = new JButton("Cancel");
			cancel.addActionListener(this);

			//adding all JLabels and JTextField needed for this frame to appropriate panels.
			inputPanel2.add(firstlbl);
			inputPanel2.add(first);
			inputPanel2.add(lastlbl);
			inputPanel2.add(last);
			inputPanel2.add(addresslbl);
			inputPanel2.add(address);
			inputPanel2.add(phonelbl);
			inputPanel2.add(phone);
			inputPanel2.add(logidlbl);
			inputPanel2.add(logid);
			inputPanel2.add(passwordlbl);
			inputPanel2.add(password);
			controlPanel2.add(saveinformation);
			controlPanel2.add(cancel);

			//adding JPanels to the frame
			frame2.add(inputPanel2);
			frame2.add(controlPanel2);

			frame2.setVisible(true);	//setting the JFrame to visible.
			frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

		else if(e.getSource() == saveinformation) {
			//gets the name of the old file.
			File oldFile = new File(owner[position].getFirstname() + owner[position].getLastname() +  save[position].getAccountNumber() + cheq[position].getAccountNumber() + ".txt");
			
			//updates the information of the customer using the set method.
			owner[position].setFirstname(first.getText());
			owner[position].setLastname(last.getText());
			owner[position].setAddress(address.getText());
			owner[position].setPhone(phone.getText());
			
			//gets the name of the new file.
			File newFile = new File(first.getText() + last.getText() +  save[position].getAccountNumber() + cheq[position].getAccountNumber() + ".txt");
			
			//if the new file does not exits than rename the old file name with the new file name.
			if(!newFile.exists()) {
				oldFile.renameTo(newFile);	//rename the old file name with the new file name.
			}
			
			update();	//updates the JTextArea and to the file as well.
			try {
				storeinFile("logins.txt", lines);	//stores the updates the information to the file
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "File not Found");
			}
			JOptionPane.showMessageDialog(null, "Information has being updated");
			frame2.setVisible(false);	//sets the frame visibility to false
			frame3.setVisible(true);	//sets the old frame visibility to true that can do withdraw and deposit.
		}
		
		else if(e.getSource() == logout || e.getSource() == exit || e.getSource() == exit1) {
			System.exit(0);	//terminates the program
		}
		
		else if(e.getSource() == cancel) {
			logid.setText(templogid);	//sets the login id to the one that was before.
			password.setText(temppassword);	//sets the password to the one that was before.
			update();	//updates the information
			frame2.setVisible(false);	//sets the frame visibility to false
			frame3.setVisible(true);	//sets the old frame visibility to true that can do withdraw and deposit.
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerGUI gui = new CustomerGUI();	//creating the object to call the constructor
	}

}
