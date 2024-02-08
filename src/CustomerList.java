import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


/*
 * Author: Sparsh Soni
 * Date: 2021/06/19
 * Description: The customer list class
 */

public class CustomerList {

	private CustomerRecord list[];
	private int actualsize, fullSize;

	public CustomerList()
	{
		this.actualsize = 0;  // actual number of records at a given time
		this.fullSize = 100; // maximum number of record it can have
		this.list = new CustomerRecord[this.fullSize];
	}
	
	//Checks if there is available space(size < maxSize) if their is not enough space, it increase the valid size  adds the records to the location just below the size
	public boolean insert(CustomerRecord record) 
	{
		
		if(this.actualsize < this.fullSize)
		{
			//check if there is space
			this.actualsize++;	// increase the valid size
			list[actualsize-1] = record;	// add the record
			return true;
		}
		return false;	
	}
	// Method to delete a record from my list
	public boolean delete(CustomerRecord record) 
	{
		//loop through the valid records
		for(int i = 0; i < this.actualsize;i++) 
		{
			//check if the record is in the valid list
			if(this.list[i].getFirstname().equalsIgnoreCase(record.getFirstname()) && this.list[i].getLastname().equalsIgnoreCase(record.getLastname())) {	
				
				list[i] = list[actualsize-1];	// replace it with the last valid record
				actualsize--;	
				return true;
			}
		}
		return false;	// this is not successful
	}
	
//Delete the old record by calling the delete method
	
	public boolean change(CustomerRecord oldR, CustomerRecord newR) {
		// delete the old record
		if(delete(oldR)) {
			insert(newR);	// inert the new one
			return true;
		}
		return false;	// not able to change
	}
	//sorts the accounts
	public void Sort(int left, int right) {
		if(left < right) {
			int p=finding(left,right);	
			
			Sort(left,p-1);
			Sort(p+1,right);
		}
	}
	
	// finds the account
	public int finding(int left, int right) 
	{
		CustomerRecord pivot=this.list[right];	
		int pindex=left-1;	
		for(int i=left; i < right; i++)
		{
			if(this.list[i].getLastname().compareToIgnoreCase(pivot.getLastname()) < 0)	
			{
				pindex++;	
				
				CustomerRecord temp=this.list[i];
				this.list[i]=this.list[pindex];
				this.list[pindex]=temp;
			}
		}
		pindex++;	
		
		CustomerRecord t=this.list[pindex];
		this.list[pindex]=this.list[right];
		this.list[right]=t;
		return pindex;	
	}

	
	//returns list
	 
	public CustomerRecord[] getList()
	{
		return list;
	}

	
	// returns the size
	 
	public int getactualSize() 
	{
		return actualsize;
	}


	 //returns maxSize
	 
	public int getfullSize() 
	{
		return fullSize;
	}

	public String toString() 
	{
		String theList = "";
		
		for(int i = 0;i < this.getactualSize(); i++) 
		{
			theList = theList + "Record " + i + ": " + this.getList()[i].toString() + "\n";
		}
		
		return theList;
	}
	
	// stores the accounts in a file
	
	public void store(String fileName) throws IOException
	{
		
		PrintWriter fw = new PrintWriter(new FileWriter(fileName));

		
		for(int i=0;i<this.actualsize;i++)
		{
			fw.println(this.list[i].getLogin() + "<>" + this.list[i].getPassword() + "<>" + this.list[i].getFirstname()  + "<>" +  this.list[i].getLastname()  + "<>" + this.list[i].getAddress() + "<>" + this.list[i].getPhone() + "<>"+  this.list[i].getSavingacc() + "<>" + this.list[i].getSavingbal() 
					+ "<>" + this.list[i].getChequingacc() + "<>" + this.list[i].getChequingbal());
		}
		fw.close();
	}
	
	public void loader(String fileName) throws IOException
	{
		
		BufferedReader fr = new BufferedReader(new FileReader(fileName));
		int lineCount = 0;
		String line = fr.readLine();		

		
		while (line != null)		
		{
			lineCount++;
			line = fr.readLine();
		}
		
		this.actualsize = lineCount;
		fr.close();		

		
		fr = new BufferedReader(new FileReader(fileName));

		
		for (int i = 0; i < lineCount; i++)
		{
			CustomerRecord cr = new CustomerRecord(fr.readLine());
			this.list[i] = cr;
		}
		
		fr.close();
	}


	public static void main(String[] args) {
		
		
		CustomerList custList = new CustomerList();

		
		while(true) {
			char command = JOptionPane.showInputDialog(null,
					"i - insert\n" + 
							"p - display\n" +
							"d - delete\n" +
							"c - change\n" +
							"s - Sort\n" +
							"q - quit","i").charAt(0);
			if(command == 'q') {
				break;
			}
			switch(command) {
			case 'i':{
				
				String record = JOptionPane.showInputDialog(null, "Enter record", "don<>back$<>Don<>Back<>Brampton<>6471234567<>567945624196<>9000.00<>752980710017<>10990.80");
				
				CustomerRecord cr = new CustomerRecord();
				
				cr.processrecord(record);
				
				if(custList.insert(cr)) {
					JOptionPane.showMessageDialog(null, "Successfully inserted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Insert has failed");
				}
				break;
			}	
			case 'p':{
				JOptionPane.showMessageDialog(null, custList.toString());
				break;
			}	
			case 'd':{
				
				String record = JOptionPane.showInputDialog(null, "Enter a record");
				
				CustomerRecord vr = new CustomerRecord();
				
				vr.processrecord(record);
				
				if(custList.delete(vr)) {
					JOptionPane.showMessageDialog(null, "Record was deleted!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Delete was failed!");
				}
				break;
			}	
			case 'c':{
				
				String oldRecord = JOptionPane.showInputDialog(null, "Enter a old record");
				
				CustomerRecord oldR = new CustomerRecord();
				oldR.processrecord(oldRecord);

				
				String newRecord = JOptionPane.showInputDialog(null, "Enter a new record");
				
				CustomerRecord newR = new CustomerRecord();
				newR.processrecord(newRecord);

				
				if(custList.change(oldR, newR)) {
					JOptionPane.showMessageDialog(null, "Record has been changed Successfully!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Record was not found!");
				}
				break;
			}	
			
			case 's':{
				custList.Sort(0, custList.actualsize - 1);
			}
			}
		}
	}
}
