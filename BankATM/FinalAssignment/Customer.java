import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
	
	private String address;
	
	private List<Account> account_List;
	private List<Transaction> transaction_List;
	
	private boolean is_in_debt; // whether the customer is in debt
	private DigitMoney loan; // the loan of current customer

	private int uid;
	
	
	public Customer() {
		super();
		this.setAddress("Earth");
		this.setAccount_List(new ArrayList<Account>());
		this.setTransaction_List(new ArrayList<Transaction>());
		this.set_Is_In_Debt(false);
	}
	
	public Customer(String n, String id, String pw, String addr) {
		super(n,id,pw);
		this.setAddress(addr);
		this.setAccount_List(new ArrayList<Account>());
		this.setTransaction_List(new ArrayList<Transaction>());
		this.set_Is_In_Debt(false); // the customer is not in debt when they sign up for our bank
	}

	public Customer(String n, String id, String pw, String addr, DigitMoney l) {
		super(n,id,pw);
		this.setAddress(addr);
		this.setAccount_List(new ArrayList<Account>());
		this.setTransaction_List(new ArrayList<Transaction>());
		if(l.getMoney_Num()>0) {
			this.set_Is_In_Debt(true);
		}else {
			this.set_Is_In_Debt(false);
		}
		this.setLoan(l);
	}
	public Customer(String n, String id, String pw, String addr, DigitMoney l,int uid) {
		super(n,id,pw);
		this.setAddress(addr);
		this.setAccount_List(new ArrayList<Account>());
		this.setTransaction_List(new ArrayList<Transaction>());
		if(l.getMoney_Num()>0) {
			this.set_Is_In_Debt(true);
		}else {
			this.set_Is_In_Debt(false);
		}
		this.setLoan(l);
		this.setUid(uid);
	}

	
	public void setAddress(String addr) {
		this.address = addr;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAccount_List(List<Account> acls) {
		this.account_List = acls;
	}
	
	public List<Account> getAccount_List(){
		return this.account_List;
	}
	
	public int add_To_Account_List(Account new_account) {
		this.account_List.add(new_account);
		
		return 0; // 0 means success
	}
	
	public int remove_From_Account_List(Account account) {
		this.account_List.remove(account);
		
		return 0;
	}
	
	public void setTransaction_List(List<Transaction> transactions) {
		this.transaction_List = transactions;
	}
	
	public List<Transaction> getTransaction_List(){
		return this.transaction_List;
	}
	
	public void set_Is_In_Debt(boolean flag) {
		this.is_in_debt = flag;
	}
	
	public boolean get_Is_In_Debt() {
		return this.is_in_debt;
	}
	
	public void setLoan(DigitMoney l) {
		this.loan = l;
	}
	
	public DigitMoney getLoan() {
		return this.loan;
	}

	public int getUid(){
		return this.uid;
	}

	public void setUid(int uid){
		this.uid=uid;
	}

}
