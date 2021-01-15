import java.sql.*;

public class BankDaoImpl implements BankDao {
    @Override
    public Bank getBank() {
        Connection c = null;
        Bank result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM Bank;");
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                double profit=rs.getDouble("profit");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Currency currencyWithRate = null;
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                result=new Bank(new DigitMoney(profit,currencyWithRate),new DigitMoney(balance,currencyWithRate));
            }
            rs.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return result;
    }

    @Override
    public void insert(DigitMoney profit, DigitMoney balance) {
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            if(!profit.getCurrency().equals(balance.getCurrency())){
                System.out.println("the profit and the balance of a bank should have same currency type!");
            }
            int currencyType=0;
            if(profit.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(profit.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(profit.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }else {currencyType=3;}
            PreparedStatement ps=c.prepareStatement("INSERT INTO Bank (profit,balance,currency)"
                    +"VALUES (?,?,?)");
            ps.setDouble(1,profit.getMoney_Num());
            ps.setDouble(2,balance.getMoney_Num());
            ps.setInt(3,currencyType);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    @Override
    public void updateProfit(DigitMoney profit) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(profit.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(profit.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(profit.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }else {currencyType=3;}
            PreparedStatement ps=c.prepareStatement("UPDATE Bank SET profit=?, currency=? WHERE bid=3;");// the bank id is 3
            ps.setDouble(1,profit.getMoney_Num());
            ps.setInt(2,currencyType);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    @Override
    public void updateBalance(DigitMoney balance) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(balance.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(balance.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(balance.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }else {currencyType=3;}
            PreparedStatement ps=c.prepareStatement("UPDATE Bank SET balance=?, currency=? WHERE bid=3;");// the bank id is 3
            ps.setDouble(1,balance.getMoney_Num());
            ps.setInt(2,currencyType);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    @Override
    public void addProfit(DigitMoney deltaMoney) {
    	Bank currentBank = this.getBank();
    	
        DigitMoney currentProfit =  currentBank.getProfit();
        
        currentProfit.add(deltaMoney);
        
        
        this.updateProfit(currentProfit);
    	
    }
    
    @Override
    public void addBalance(DigitMoney deltaMoney) {
    	
        Bank currentBank = this.getBank();
    	
        DigitMoney currentBalance = currentBank.getBalance();
        
        System.out.println(deltaMoney.getMoney_Num());
        
        currentBalance.add(deltaMoney);
        
        this.updateBalance(currentBalance);
    }
    
    // return 1 means success, return 0 means deltaMoney greater than balance
    @Override
    public int decBalance(DigitMoney deltaMoney) {
    	
        Bank currentBank = this.getBank();
    	
        DigitMoney currentBalance = currentBank.getBalance();
        
        int success = currentBalance.decrease(deltaMoney);
        
        this.updateBalance(currentBalance);
        
        return success;
    }
    
 // return 1 means success, return 0 means deltaMoney greater than profit
    @Override
    public int decProfit(DigitMoney deltaMoney) {
    	Bank currentBank = this.getBank();
    	
        DigitMoney currentProfit =  currentBank.getProfit();
        
        int success = currentProfit.decrease(deltaMoney);
        
        this.updateProfit(currentProfit);
        
        return success;
    	
    }
}
