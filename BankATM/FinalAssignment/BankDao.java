public interface BankDao {
    public Bank getBank();
    public void insert(DigitMoney profit,DigitMoney balance);
    public void updateProfit(DigitMoney profit);
    public void updateBalance(DigitMoney balance);
    public void addProfit(DigitMoney deltaMoney);
    public void addBalance(DigitMoney deltaMoney);
    public int decProfit(DigitMoney deltaMoney);
    public int decBalance(DigitMoney deltaMoney);
}
