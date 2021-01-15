import java.util.Date;
import java.util.List;

public interface AccountDao {
        public List<Account> getAccountList(int UID);
        public List<Account> getSavingAccountList(int UID);
        public List<Account> getCheckingAccountList(int UID);
        public Account getAccount(int AID);
        public void deposit(int AID, DigitMoney money);
        public void withdraw(int AID, DigitMoney money);
        public void transfer(int AID1,int AID2,DigitMoney money);
        public void exchangeCurrency(int AID,Currency targetCurrency);
        public void insert(int uid, int type, DigitMoney balance, Date startTime);
        public void updateBalance(int AID, DigitMoney balance);
}
