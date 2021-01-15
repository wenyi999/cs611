import java.util.Date;

public class OpenAccountController {
	
	public int openAccount(int uid, int type, DigitMoney balance) {
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		Date currentDate = new Date();
		// insert to database
		accountDao.insert(uid, type, balance, currentDate);
		// take service fee as bank's profit and add to bank's balance
		BankDaoImpl bankDao = new BankDaoImpl();
		
		DigitMoney servicefee = new DigitMoney(10,USDollar.getInstance());
		
		bankDao.addBalance(balance);
		bankDao.addBalance(servicefee);
		bankDao.addProfit(servicefee);
		
		return 1;
	}
	
    public int openZeroAccount(int uid, int type) {
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		Date currentDate = new Date();
		DigitMoney starting_money = new DigitMoney(0,USDollar.getInstance());
		// insert to database
		System.out.println("test0");
		accountDao.insert(uid, type, starting_money, currentDate);
		System.out.println("test1");
		// take service fee as bank's profit and add to bank's balance
		BankDaoImpl bankDao = new BankDaoImpl();
		
		DigitMoney servicefee = new DigitMoney(10,USDollar.getInstance());
		
		System.out.println("test2");
		
		bankDao.addBalance(servicefee);
		bankDao.addProfit(servicefee);
		
		return 1;
	}
	
	
	public int DepositMoney(int aid, DigitMoney money) {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		accountDao.deposit(aid, money);
		
		// create bank operation object 
		BankDaoImpl bankDao = new BankDaoImpl();
				
				
		// increase the balance of the bank
		bankDao.addBalance(money);
		
		return 1;
	}
	
	public int WithDrawMoney(int aid, DigitMoney money) {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		accountDao.withdraw(aid, money);
		
		// create bank operation object 
		BankDaoImpl bankDao = new BankDaoImpl();
		
		
		// decrease the balance of the bank
		bankDao.decBalance(money);
		
		// charge for service fee if this account is a checking account
		Account targetAccount = accountDao.getAccount(aid);
		if(targetAccount.getType()==2) {
			CheckingAccount checkings = (CheckingAccount)targetAccount;
			double service_rate = checkings.getServiceFee();
			double start_money_num = money.getMoney_Num(); 
			double service = start_money_num + money.getMoney_Num()*service_rate;
			DigitMoney serviceMoney = new DigitMoney(service,money.getCurrency());

			bankDao.addBalance(serviceMoney);
			bankDao.addProfit(serviceMoney);
		}
		
		return 1;
	}
	
	public int TransferMoney(int aid1, int aid2, DigitMoney money) {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		accountDao.transfer(aid1, aid2, money);
		
		Date currentDate = new Date();
		transactionDao.insert(aid1, aid2, money, currentDate);
		
		// create bank operation object 
		BankDaoImpl bankDao = new BankDaoImpl();
		
		double start_money_num = money.getMoney_Num(); 
		double service = start_money_num + money.getMoney_Num()*0.01;
		DigitMoney serviceMoney = new DigitMoney(service,money.getCurrency());
		
		// charge for service fee.
		bankDao.addProfit(serviceMoney);
		bankDao.addBalance(serviceMoney);
		
		return 1;
	}
	
	// a method for user to ask for loan 
	public int requestLoan(int uid, DigitMoney loanMoney) {
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		customerDao.addLoan(uid, loanMoney);
		
		
		return 1;
	}
	
	// a method for user to pay the loan
	public int payLoan(int uid, DigitMoney loanMoney) {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		customerDao.decLoan(uid, loanMoney);
		
		
		return 1;
	}

}
