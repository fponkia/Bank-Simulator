import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
 * Date: 2021-06-20
 * Description: The GUI which handles the employee panel. This class creates the bank customer managing window. Requires a pin to access this window.
 * 
 * Method List:
 * 
 * public BankRecords(): Constructor that creates the GUI and sets up the components.
 * 
 * 	public void actionPerformed(ActionEvent e): Method to listen and perform to the buttons.
 *
 *
 */
public class BankRecords1 extends JFrame implements ActionListener {


	private CustomerList custList;
	private JLabel lblInstruction;
	private JTextField textInput;
	private JTextArea textOutput;
	private JScrollPane scroller;
	private JButton btnexit, btndisplay, btnadd, btndelete, btnload, btnsave, btnclear;
	private JPanel inputPanel, outputPanel, controlPanel;
	/**
	 * 
	 */
	public BankRecords1() {
		super("Bank Records");
		setLayout(new GridLayout(4,1));	//initializing the layout of the JFrame using GridLayout with 5 rows and 1 column
		inputPanel = new JPanel(); // sub panels for different areas
		outputPanel = new JPanel();
		controlPanel = new JPanel();
		custList = new CustomerList();	//creating an object of CustomerList class

		//creating different components of JFrame
		textInput = new JTextField("don<>back$<>Don<>Back<>Brampton<>6471234567<>567945624196<>9000.00<>752980710017<>10990.80", 80);
		lblInstruction = new JLabel ("Enter Below: Login ID<>Password<>Name<>Address<>Phone Number<>Savings Account Number<>Balance<>Chequing Account Number<>Balance");
		textOutput = new JTextArea (9,80);
		scroller = new JScrollPane(textOutput); //scroller for the text area

		//creating buttons
		btnadd = new JButton("Add");
		btndelete = new JButton("Delete using the first name and Last name");
		btndisplay = new JButton("Display Sorted list by Last Name");
		btnload = new JButton("Load File");
		btnsave = new JButton("Save to File");
		btnclear = new JButton("Clear");
		btnexit = new JButton("Exit");



		// add components to panels
		inputPanel.add(lblInstruction);
		inputPanel.add(textInput);
		outputPanel.add(scroller);
		controlPanel.add(btnadd);
		controlPanel.add(btndelete);
		controlPanel.add(btndisplay);
		controlPanel.add(btnload);
		controlPanel.add(btnsave);
		controlPanel.add(btnclear);
		controlPanel.add(btnexit);

		// add components to the frame
		add(inputPanel);
		add(outputPanel);
		add(controlPanel);

		// add button as a listener in this frame
		btnadd.addActionListener(this);
		btndelete.addActionListener(this);
		btndisplay.addActionListener(this);
		btnload.addActionListener(this);
		btnsave.addActionListener(this);
		btnclear.addActionListener(this);
		btnexit.addActionListener(this);


		// set size of frame
		setSize(900,600);
		setResizable(false);
		setVisible(true);
		setLocation(0,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankRecords1 gui = new BankRecords1();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if buttons were clicked
		if(e.getSource() == btnadd) {
			CustomerRecord vr = new CustomerRecord(textInput.getText());	//gets the input and process it.
			if(custList.insert(vr)) {
				//inserts to the list.
				vr.setSavingbal(0);
				vr.setChequingbal(0);
				File create = new File(vr.getFirstname() + vr.getLastname() + vr.getSavingacc() + vr.getChequingacc() + ".txt");
				if(! create.exists()) {
					try {
						create.createNewFile();
					} catch (IOException e1) {
					}
				}
				//JOptionPane.showMessageDialog(null, "You need to create the customer text file manually, if need to do transactions, name should countain 'firstname lastname savingaccountnumber chequingaccountnumber'");
			}
			else
				textOutput.setText("Record add failed because list is full!");
		}
		
		else if(e.getSource() == btndelete) {
			CustomerRecord vr = new CustomerRecord(textInput.getText());	//gets the input and process it.
			if(custList.delete(vr)) {
				//inserts to the list.
				File del = new File(vr.getFirstname() + vr.getLastname() + vr.getSavingacc() + vr.getChequingacc() + ".txt");
				if(del.exists()) {
					del.delete();
				}
				else {
					JOptionPane.showMessageDialog(null, "File was not found and was not being able to delete");
				}
			}
			else
				textOutput.setText("Record delete failed because record was not found!");
		}
		
		else if(e.getSource() == btndisplay) {
			custList.Sort(0, custList.getactualSize() - 1);	//sort the list by make
			String output = custList.toString();	//gets the records as a String.
			textOutput.setText(output);	//displays it to the JTextArea
		}

		else if(e.getSource() == btnload) {
			textOutput.setText("");	//sets the display area to empty.
			String output = "";
			try {
				String fileName = JOptionPane.showInputDialog(null, "Enter the file Name where the records needed to be loaded", "BankEmployee.txt");
				custList.loader(fileName);	//loads the record to the array of list directly without returning anything.
				output = custList.toString();
				textOutput.setText(output);	//displays the loaded record.
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "File not Found or information in the file is not correct");
			}
		}
		
		else if(e.getSource() == btnsave) {
			String fileName = JOptionPane.showInputDialog(null, "Enter the file Name where the records needed to be stored", "BankEmployee.txt");
			try {
				custList.store(fileName);	//stores the list in file
				JOptionPane.showMessageDialog(null, "records got stored in file");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "File not found");
			}
		}
		
		else if(e.getSource() == btnclear) {
			textOutput.setText("");	//clears the display area.
		}
		
		else if(e.getSource() == btnexit) {
			System.exit(0);	//terminates the program.
		}
	}

}
