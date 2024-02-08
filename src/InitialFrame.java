import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Foram Patel
 * Date: June 20, 2021
 * Description: A class that ask the user whether he is Customer or Bank Employee.
 * Methods / Constructors: public InitialFrame(): A constructor that creates the JFrame and ask the user to click the button that matches him.
 * 	public void storeinFile(String fileName, String passcode) throws IOException: Stores the current password to the file.
 * 	public String loader(String fileName) throws IOException: loads the current password from the file.
 * 	public void actionPerformed(ActionEvent e): A method that gets called when any buttons gets clicked in the JFrame.
 * 	public static void main(String[] args): A method that creates the object of the class to call the constructor.
 */
public class InitialFrame extends JFrame implements ActionListener {
	
	//instance Variables
	private JPanel questionPanel, initialcontrolPanel, passwordPanel, controlPanel, passwordPanel1, controlPanel1;
	private JLabel question, passwordinput;
	private JTextField password, newpassword;
	private JButton employee, customer, okay, change, set, cancel, back, exit;
	private JFrame frame, frame1;
	private String currentpassword;
	/**
	 * 
	 */
	public InitialFrame() {
		super("Initial Frame");
		
		setSize(350, 150);	//setting size, layout and location of the JFrame
		setLayout(new GridLayout(2,1));
		setLocation(100, 100);
		
		
		questionPanel = new JPanel();	//creating the sub panels of the JFrame
		initialcontrolPanel = new JPanel();
		
		//creating the JLabels and JButtons for the frame.
		question = new JLabel("Are you a: ");
		question.setFont(new Font("Georgia", Font.PLAIN, 20));
		question.setForeground(Color.BLUE); //gray text color
		employee = new JButton("Bank Employee");
		customer = new JButton("Customer");
		exit = new JButton("Exit");
		
		try {
			currentpassword = loader("employeePin.txt");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Not found");
		}
		//adding action listener to the buttons
		employee.addActionListener(this);
		customer.addActionListener(this);
		exit.addActionListener(this);
		
		//adding JLabels and JButtons to the panels.
		questionPanel.add(question);
		initialcontrolPanel.add(employee);
		initialcontrolPanel.add(customer);
		initialcontrolPanel.add(exit);
		
		add(questionPanel);	//adding panels to the frame.
		add(initialcontrolPanel);
		
		setVisible(true);	//setting the frame visibility to true
		setResizable(false);	//setting resizable of the frame to false
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Stores the password to the file
	 * @param fileName
	 * @param passcode
	 * @throws IOException
	 */
	public void storeinFile(String fileName, String passcode) throws IOException {
		//open the file to write
		PrintWriter fw = new PrintWriter(new FileWriter(fileName));

		fw.println(passcode);	//writes the password to the file
		fw.close();//close the file stream
	}

	/**
	 * Loads the password from the file.
	 * @param fileName
	 * @throws IOException
	 */
	public String loader(String fileName) throws IOException {
		// opens file for reading
		BufferedReader fr = new BufferedReader(new FileReader(fileName));
		String line = fr.readLine();		//reads first line.

		// close file
		fr.close();
		return line;
	}

	/**
	 * Gets performed when any button on the JFrames gets clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == customer) {
			setVisible(false);	//setting the visibility of the frame to false
			CustomerGUI gui = new CustomerGUI();	//creating the object of the CustomerGUI class.
		}
		
		else if(e.getSource() == employee) {
			setVisible(false);
			frame = new JFrame("Employee Login");
			frame.setSize(350, 150);
			frame.setLayout(new GridLayout(2,1));
			frame.setLocation(100, 100);
			
			passwordPanel = new JPanel();	//creates the subpanels for the JFrame
			controlPanel = new JPanel();
			
			//creates the JLabel and JTextField and setting size and color of the text.
			passwordinput = new JLabel("Enter the password");
			passwordinput.setFont(new Font("Georgia", Font.PLAIN, 18));
			passwordinput.setForeground(Color.BLUE); //gray text color
			password = new JTextField("", 15);
			
			//creates the JButton for the JFrame and adding listener to it.
			okay = new JButton("Enter");
			okay.addActionListener(this);
			change = new JButton("Change or Set Password");
			change.addActionListener(this);
			back = new JButton("Back");
			back.addActionListener(this);
			
			passwordPanel.add(passwordinput);	//adding JLabels, JTextField and JButtons to the panels.
			passwordPanel.add(password);
			controlPanel.add(okay);
			controlPanel.add(change);
			controlPanel.add(back);
			
			frame.add(passwordPanel);	//adding panels to the frame.
			frame.add(controlPanel);
			
			frame.setVisible(true);	//setting the frame to visible
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		else if(e.getSource() == okay) {
			frame.setVisible(false);	//setting the frame to not visible.
			if(password.getText().equals(currentpassword)) {
				BankRecords record = new BankRecords();	//creating the object of the class BankRecords.
			}
			else {
				JOptionPane.showMessageDialog(null, "Password is incorrect");
				frame.setVisible(true);	//setting the frame to visible again.
			}
		}
		
		else if(e.getSource() == change) {
			frame.setVisible(false);	//setting the frame to not visible.
			frame1 = new JFrame("Change Password");	//creating another frame.
			frame1.setSize(300, 150);	//setting the size. layout and location of the new frame.
			frame1.setLayout(new GridLayout(2,1));
			frame1.setLocation(100, 100);
			
			passwordPanel1 = new JPanel();	//creating panels for the new JFrame
			controlPanel1 = new JPanel();
			
			//creates the JLabel and JTextField and setting size and color of the text.
			passwordinput = new JLabel("Enter the new password");
			passwordinput.setFont(new Font("Georgia", Font.PLAIN, 18));
			passwordinput.setForeground(Color.BLUE); //gray text color
			newpassword = new JTextField("", 15);
			
			//creates the JButton for the JFrame and adding listener to it.
			set = new JButton("Set");
			set.addActionListener(this);
			cancel = new JButton("Cancel");
			cancel.addActionListener(this);
			
			passwordPanel1.add(passwordinput);	//adding JLabels, JTextField and JButtons to the panels.
			passwordPanel1.add(newpassword);
			controlPanel1.add(set);
			controlPanel1.add(cancel);
			
			frame1.add(passwordPanel1);	//adding panels to the new JFrame.
			frame1.add(controlPanel1);
			
			frame1.setVisible(true);	//setting the frame to visible
			frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		else if(e.getSource() == set) {
			currentpassword = newpassword.getText();	//saves the new password to a variable that checks.
			try {
				storeinFile("employeePin.txt", currentpassword);	//saving the new password to the file.
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "File Not found");
			}
			frame1.setVisible(false);	//setting the frame to not visible.
			frame.setVisible(true);		//setting the old frame to visible.
		}
		else if(e.getSource() == cancel) {
			frame1.setVisible(false);	//setting the frame to not visible.
			frame.setVisible(true);		//setting the old frame to visible.
		}
		
		else if(e.getSource() == exit) {
			System.exit(0);	//terminates the program.
		}
		
		else if(e.getSource() == back) {
			frame.setVisible(false);	//setting the frame to not visible.
			setVisible(true);	//setting the old frame to visible.
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InitialFrame frame = new InitialFrame();	//creates the object of the class to call the constructor.

	}

}
