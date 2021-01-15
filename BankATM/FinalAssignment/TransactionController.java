import java.util.ArrayList;
import java.util.List;

public class TransactionController {
	private TransactionDaoImpl transactionDao = new TransactionDaoImpl();
	// this method only for manager
	public List<Transaction> showAllTransactions(){
		//TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		List<Transaction> results = transactionDao.getAllTransaction();
		
		return results;
	}
	
	// this method for a customer to show all his own transaction records
	public List<Transaction> showCustomerTransactions(int uid){
        //TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		List<Transaction> result1 = transactionDao.getTransactionFromByUser(uid);
		
		List<Transaction> result2 = transactionDao.getTransactionToByUser(uid);
		
		result1.addAll(result2);

		
	    return result1;
	}
	
	// this method to check specific account's transaction records
	public List<Transaction> showAccountTransactions(int aid){
        //TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		List<Transaction> result1 = transactionDao.getTransactionFrom(aid);
		
		List<Transaction> result2 = transactionDao.getTransactionTo(aid);
		
		result1.addAll(result2);

		
	    return result1;
	}
	public List<Transaction> showAccountTransactions(int aid,int uid){
		//TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		AccountDao accountDao=new AccountDaoImpl();
		List<Account> accountList=accountDao.getAccountList(uid);
		int flag=0;
		for (Account a : accountList){
			if(aid==a.getAccountId()){
				flag=1;
				break;
			}
		}
		if(flag==0){
			List<Transaction> transactions=new ArrayList<>();
			return transactions;
		}
		List<Transaction> result1 = transactionDao.getTransactionFrom(aid);

		List<Transaction> result2 = transactionDao.getTransactionTo(aid);

		result1.addAll(result2);

		return result1;
	}


}
