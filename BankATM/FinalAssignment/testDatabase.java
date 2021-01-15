import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// this class is just for test
public class testDatabase {
	public static void main( String args[] )
	  {
	    Connection c = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	      	c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
	      	
	      	
	      	InquiryController inquiry = new InquiryController();
			Customer selectedCustomer = inquiry.showCustomer(11);
			selectedCustomer.getLoan().getCurrency();
			String str = selectedCustomer.getLoan().toString();
	      	System.out.println(str);
	      	
	     // create bank operation object 
//			BankDaoImpl bankDao = new BankDaoImpl();
//					
//					
//			// increase the balance of the bank
//			bankDao.addBalance(new DigitMoney(10,CHYen.getInstance()));
//			
//			
//			CustomerDaoImpl customerdao = new CustomerDaoImpl();
//			customerdao.addLoan(6, new DigitMoney(10,CHYen.getInstance()));
//			
			
			ManagerDaoImpl manage = new ManagerDaoImpl();
			manage.insert("admin", "admin", "123456");
	      	
	      	// public void insert(int uid, int type, DigitMoney balance, java.util.Date startTime)
	      	
//	      	CustomerDaoImpl customerDao = new CustomerDaoImpl();
//			
//			customerDao.addLoan(7, new DigitMoney(10,CHYen.getInstance()));
	      	
	      	
//	      	InquiryController inquiryControl = new InquiryController();
//			List<Account> Accountlist = inquiryControl.getAccountOfCustomer(7);
	      	
	      	List<Account> accountlist = new ArrayList<>();
			
			AccountDaoImpl accountDao = new AccountDaoImpl();
			
			accountlist = accountDao.getAccountList(7);
			
			OpenAccountController controller = new OpenAccountController();
					
	      	
//	      	AccountDaoImpl accountDao = new AccountDaoImpl();
//			
//			List<Account> accountlist = accountDao.getSavingAccountList(7);
	      	
	      	
//	      	DigitMoney money1 = new DigitMoney(10,CHYen.getInstance());
//	      	BankDaoImpl bankDao = new BankDaoImpl();
//	      	double a = bankDao.getBank().getBalance().getMoney_Num();
//	      	// bankDao.updateBalance(new DigitMoney(10,CHYen.getInstance()));
//	      	bankDao.addBalance(new DigitMoney(10,CHYen.getInstance()));
//	      	double bankbalance = bankDao.getBank().getBalance().getMoney_Num();
//	      	System.out.println(bankbalance);
	      	
	      	
//	      	OpenAccountController Accountcontroller = new OpenAccountController();
//			Accountcontroller.openZeroAccount(7, 1);
	      	
//	      	CustomerDao customerDao1 = new CustomerDaoImpl();
//			
//			boolean success = customerDao1.checkThePassword("aaaa", "bbbb");
	      	
//	      	CustomerDao customerDao=new CustomerDaoImpl();
//	      	boolean flag = customerDao.checkThePassword("songboyi", "12345");
//	      	customerDao.getCustomer("songboyi");
//	        List<Customer> customerlist = customerDao.getAllCustomer();
	        //LoginController acontroller = new LoginController();
	        
	        //acontroller.Login("aaaa", "bbbb");
//	        System.out.println(customerlist.get(0).getName());
//	        System.out.println(flag);
	//		customerDao.insert("boyi","songboyi2","12345",0,new DigitMoney(0,CHYen.getInstance()),"Earth");
			//customerDao.insert("jiatong","jiatong","12345",0,new DigitMoney(0,CHYen.getInstance()),"Mars");
	      	//BankDao bankDao=new BankDaoImpl();
	      	//bankDao.insert(new DigitMoney(0,CHYen.getInstance()),new DigitMoney(0,CHYen.getInstance()));
	      	//AccountDao accountDao=new AccountDaoImpl();
			//accountDao.insert(1,1,new DigitMoney(100,CHYen.getInstance()),new Date());
			//accountDao.insert(1,1,new DigitMoney(200,USDollar.getInstance()),new Date());
			//accountDao.insert(2,1,new DigitMoney(300,CHYen.getInstance()),new Date());
			//accountDao.insert(2,1,new DigitMoney(400,EuroDollar.getInstance()),new Date());
			TransactionDao transactionDao=new TransactionDaoImpl();
			//transactionDao.insert(1,3,new DigitMoney(10,CHYen.getInstance()),new Date());
			//transactionDao.insert(2,4,new DigitMoney(20,USDollar.getInstance()),new Date());
	//		List<Transaction> transaction= transactionDao.getTransactionByUser(1,2);
	//		System.out.println(transaction);
	//      Customer newCustomer=customerDao.getCustomer("12345");
	//      System.out.println(newCustomer.getAddress());
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	  }
}
