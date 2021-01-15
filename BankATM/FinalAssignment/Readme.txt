Student name: Jiatong Yin, Boyi Song, Yichen Sun.
Group No: 6


Compilation: javac \yourpath\*.java
Execution: java MainFunc

For manager testing:
a manager: loggingID = admin, password = 123456 


For customer testing:
You can sign up as our customer, and login with your loggingID and Password. Then you can start to use our bank!


The databaseForBank.db is the database we used, but you can ignore it since when you run the code, a new database will be created 
automatically on your machine.

The script.sql is the file about how we define our SQLite Database of bank. 


The bank system has two parts, the first part is for customers. First of all, to be the users of our bank, you need to sign up and fill 
some information, then you can go back and login the user's interface. Then as our customer, you can choose to open an bank 
account(checking account or saving account) to deposit some money, and there are three currencies in our bank system. Don't worry 
if you are lack of money, our bank can provide loan service, which means you can lend money from us! User can check their account 
info in their interfaces. The second part is for manager, there is only one manager in the system. you need to know the logging ID and 
password to enter the manager's interface, where you can check all customers and the profit and balance of our bank!


Object Design:

Class Account:
This class represents the accounts which allow customers to deposit, withdraw and transfer money. Customers need to open an account before they make use of the functions of our bank.

Class CheckingAccount:
This class extends the class Account which allow customers to deposit, withdraw and transfer money. It has a new attribute service_fee and overrides the method withdraw()  

Class SavingAccount:
This class extends the class Account which allow customers to deposit, withdraw and transfer money. It has a new attribute interest and the balance of this account will increase by the interest.

Interface AccountDao:
This interface defines methods that interact with the database of the bank. These methods are used to deal with table Account in the database.

Class AccountDaoImpl:
This class is responsible to connect to the database of the bank and do some operations with table Account, which implements the Interface AccountDao.

Class Bank:
This class represents the bank which store some basic information like the profit and total balance.
 

Interface BankDao:
This interface defines methods that interact with the database of the bank. These methods are used to deal with table Bank in the database.

Class BankDaoImpl:
This class is responsible to connect to the database of the bank and do some operations with table Bank, which implements the Interface BankDao.

Class User:
This class represents the user of the bank, which can be a customer or a manager. In this class some basic methods are defined to maintain users’ info.

Class Customer:
This class represents the customer of the bank, which stores information like name, id, password and address.

Interface CustomerDao:
This interface defines methods that interact with the database of the bank. These methods are used to deal with table Customer in the database.

Class CustomerDaoImpl:
This class is responsible to connect to the database of the bank and do some operations with table Customer, which implements the Interface CustomerDao.

Class Manager:
This class represents the manager of the bank, which provides basic information of a manager.

Interface ManagerDao:
This interface defines methods that interact with the database of the bank. These methods are used to deal with table Manager in the database.

Class ManagerDaoImpl:
This class is responsible to connect to the database of the bank and do some operations with table Manager, which implements the Interface ManagerDao.

Class Transaction:
This class represents a transaction record of the bank. In a transaction, the account that starts the transaction and the account that receive the transaction is recorded, which is represented by an object of Class Transaction and stored in database. 

Interface TransactionDao:
This interface defines methods that interact with the database of the bank. These methods are used to deal with table Transaction in the database.

Class TransactionDaoImpl:
This class is responsible to connect to the database of the bank and do some operations with table Transaction, which implements the Interface TransactionDao.

Class Currency:
This class represents the currency of the money that is used for the bank. It has the attribute exchanging rate, which can be used to exchange the type of currency.

Class USDollar:
This class extends the class Currency and represents the currency, USDollar. It is implemented in single pattern, which is not allowed to be initialized outside the class. 

Class EuroDollar:
This class extends the class Currency and represents the currency, EuroDollar. It is implemented in single pattern, which is not allowed to be initialized outside the class.


Class CHYen:
This class extends the class Currency and represents the currency, CHYen. It is implemented in single pattern, which is not allowed to be initialized outside the class.

Class DigitMoney:
This class represents the money that is used in the bank system, which have two attributes a number and a Currency object.

Class LoginController:
This class is used to implement methods related to login and sign up interface of the bank system.

Class InquiryController:
This class is used to implement methods for manager to check customers’ information.

Class TransactionController:
This class is used to implement methods for manager to check all transaction records or for customers to check their own transactions records.

Class OpenAccountController:
This class is used to implement methods for customers to open an account and do some transactions with the accounts they have already own. 
  