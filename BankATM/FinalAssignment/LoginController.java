
public class LoginController {
	
	public int Signup(String name, String loggingID, String password, int inDebt, double loanNum, String address) {
		
		DigitMoney loanMoney = new DigitMoney(0, USDollar.getInstance());
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		int success = customerDao.insert(name, loggingID, password, inDebt, loanMoney, address);
		
		
		return success;
	}
	
	// return null if logging id and password don't match
	public Customer Login(String loggingID, String password) {
		// init the return customer
		Customer selectedCustomer = null;
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		boolean success = customerDao.checkThePassword(loggingID, password);
		if(success) {
		   selectedCustomer = customerDao.getCustomer(loggingID);
		}
		
		return selectedCustomer;
	}
	// this method for manager to login
	public int LoginForManager(String loggingID, String password) {
		int result = 1;
		ManagerDaoImpl managerDao = new ManagerDaoImpl();
		boolean success = managerDao.checkThePassword(loggingID, password);
		
		if(success) {
			result = 1;
		}else {
			result = 0;
		}
		
		return result;
	}
	
	public int changePassword(String loggingId, String newpassword) {
		
		int result = 1;
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		result = customerDao.changePassword(loggingId, newpassword);
		
		
		
		return result;
	}

}
