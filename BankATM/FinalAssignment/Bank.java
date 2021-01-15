import java.util.ArrayList;
import java.util.List;

public class Bank {
	
	private List<Customer> customer_list;
	
	private Manager manager;
	
	private DigitMoney profit;
	
	private DigitMoney total_Balance;
	
	private List<Transaction> transaction_list;
	
	public Bank() {
		this.setCustomerList(new ArrayList<Customer>());
		this.set_Transaction_List(new ArrayList<Transaction>());
		this.setManager(new Manager());
		this.setProfit(new DigitMoney());
		this.setBalance(new DigitMoney());
	}
	
	
	public Bank(List<Customer> customers,List<Transaction> transacitons ,Manager mana, DigitMoney profits, DigitMoney balance) {
		this.setCustomerList(customers);
		this.set_Transaction_List(transacitons);
		this.setManager(mana);
		this.setProfit(profits);
		this.setBalance(balance);
	}

	public Bank(DigitMoney profits,DigitMoney balance){
		this.setProfit(profits);
		this.setBalance(balance);
	}
	
	public void setCustomerList(List<Customer> customers) {
		this.customer_list = customers;
	}

	public List<Customer> getCustomerList(){
		return this.customer_list;
	}
	
	public int add_Customer(Customer customer) {
		
		this.customer_list.add(customer);
		return 1;
	}
	
	public int remove_Customer(Customer customer) {
		this.customer_list.remove(customer);
		
		return 1;
	}
	
	public void set_Transaction_List(List<Transaction> transactions) {
		this.transaction_list = transactions;
	}
	
	public List<Transaction> get_Transaction_List(){
		return this.transaction_list;
	}
	
	public void setManager(Manager m) {
		this.manager = m;
	}
	
	public Manager getManager(Manager m) {
		return this.manager;
	}
	
	public void setProfit(DigitMoney p) {
		this.profit = p;
	}
	
	public DigitMoney getProfit() {
		return this.profit;
	}
	
	public void setBalance(DigitMoney bl) {
		this.total_Balance = bl;
	}
	
	public DigitMoney getBalance() {
		return this.total_Balance;
	}
	
}
