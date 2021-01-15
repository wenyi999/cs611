import java.util.Date;
import java.util.List;

public interface CustomerDao {
    public Customer getCustomer(String loggingID);
    public Customer getCustomerByUid(int uid);
    public List<Customer> getAllCustomer();
    public int changePassword(String loggingID,String password);
    public int insert(String name,String loggingID,String password, int inDebt, DigitMoney loan, String address);
    public void updateTheLoan(String loggingID,DigitMoney loan);
    public void addLoan(int uid,DigitMoney deltaloan);
    public void decLoan(int uid,DigitMoney deltaloan);
    public void updateInDebt(String loggingID,int inDebt);
    public boolean checkThePassword(String loggingID,String password);
}
