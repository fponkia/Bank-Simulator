Name: Foram Patel

You need to open "InitialFrame.java" file to initial start the program.

Following are the list of the classes and files that are needed to run the program:
1. Customer.java - Made by Foram Patel
2. Account.java - Made by Foram Patel
3. Saving.java - Made by Foram Patel
4. Chequing.java - Made by Foram Patel
5. CustomerGUI.java - Made by Foram Patel
6. InitialFrame.java - Made by Foram Patel
7. CustomerRecord.java - Made by Sparsh Soni
8. CustomerList.java - Made by Sparsh Soni
9. BankRecords.java - Made by Kush Patel
10. logins.txt - Made by Foram Patel - Stores all the of the customers information.
11. BankEmployee.txt - Made by Foram Patel - Stores all the of the customers information but it is different than the above file.
12. employeePin.txt - Made by Foram Patel


How to run the program:
InitialFrame class will create a JFrame that will ask for which type of user you are, "Bank Employee" or "Customer".

If Clicked " Customer". A new JFrame will appear that has all the required fields.
You can register an account or can login to an account.

While login you need to give your first and last name, login id and password. First and last name is used because
there are chances that login id and password can be same for other user.
After login you can perform all the transaction that you want and also change the customer information if you want.
All the transaction gets saved in the file of the customer in the background.

***Bank Employee part is totally independent. Anything done overhere does not affect the customer part***
*** And anything done in the customer part does not affect the Bank Employee Part even the file that has records***
*** Both the part have different files of record***
*** adding, deleteing, saving, sorting here in Bank Employee Part will not affect the customer part***

If Clicked "Bank Employee". A dialog box will appear that will ask you to enter the pin or password.
Pin or Password is admin1234. After entering the correct password a new JFrame will appear and you can do all the
action required such load the file, save to file, and etc.