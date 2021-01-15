import java.util.Date;
import java.util.List;

public interface TransactionDao {
    public List<Transaction> getTransaction(int aid1,int aid2);
    public List<Transaction> getAllTransaction();
    public List<Transaction> getTransactionFrom(int aid);
    public List<Transaction> getTransactionTo(int aid);
    public List<Transaction> getTransaction(Date datetime);
    public void insert(int aid1,int aid2,DigitMoney money,Date time);
    public List<Transaction> getTransactionByUser(int uid1,int uid2);
    public List<Transaction> getTransactionFromByUser(int uid);
    public List<Transaction> getTransactionToByUser(int uid);

}
