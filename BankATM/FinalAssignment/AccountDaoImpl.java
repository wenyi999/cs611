import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public List<Account> getAccountList(int UID) {
        Connection c;
        List<Account> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM Account WHERE uid=?");
            ps.setInt(1,UID);
            ResultSet rs=ps.executeQuery();
            while ( rs.next() ) {
                int aid = rs.getInt("aid");
                int uid=rs.getInt("uid");
                int type=rs.getInt("type");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Date start_time =rs.getDate("start_time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                if(type==1){
                	SavingAccount selectedAccount = new SavingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.001);
                	// calculate saving account's balance after interested.
                	//selectedAccount.calculateCurrentBalance();
                	//this.updateBalance(aid, selectedAccount.getBalance());
                    result.add(selectedAccount);
                }
                else {
                    result.add(new CheckingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.01));
                }
                //result.add(new Account(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,type));
            }
            rs.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }
    
    
    @Override
    public List<Account> getSavingAccountList(int UID) {
        Connection c;
        List<Account> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM Account WHERE uid=? and type = 1");
            ps.setInt(1,UID);
            ResultSet rs=ps.executeQuery();
            while ( rs.next() ) {
                int aid = rs.getInt("aid");
                int uid=rs.getInt("uid");
                int type=rs.getInt("type");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Date start_time =rs.getDate("start_time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                if(type==1){
                	SavingAccount selectedAccount = new SavingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.001);
                	// calculate saving account's balance after interested.
                	//selectedAccount.calculateCurrentBalance();
                	//this.updateBalance(aid, selectedAccount.getBalance());
                    result.add(selectedAccount);
                }
                else {
                    result.add(new CheckingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.01));
                }
                //result.add(new Account(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,type));
            }
            rs.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }
    
    @Override
    public List<Account> getCheckingAccountList(int UID) {
        Connection c;
        List<Account> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM Account WHERE uid=? and type = 2");
            ps.setInt(1,UID);
            ResultSet rs=ps.executeQuery();
            while ( rs.next() ) {
                int aid = rs.getInt("aid");
                int uid=rs.getInt("uid");
                int type=rs.getInt("type");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Date start_time =rs.getDate("start_time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                if(type==1){
                	SavingAccount selectedAccount = new SavingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.001);
                	// calculate saving account's balance after interested.
                	//selectedAccount.calculateCurrentBalance();
                	//this.updateBalance(aid, selectedAccount.getBalance());
                    result.add(selectedAccount);
                }
                else {
                    result.add(new CheckingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.01));
                }
                //result.add(new Account(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,type));
            }
            rs.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }
    

    @Override
    public Account getAccount(int AID) {
        Connection c;
        Account result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM Account WHERE aid=?");
            ps.setInt(1,AID);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int aid = rs.getInt("aid");
                int uid=rs.getInt("uid");
                int type=rs.getInt("type");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Date start_time =rs.getDate("start_time");
                Currency currencyWithRate = null;

                if(currency==1) {
                    currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                    currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                    currencyWithRate=CHYen.getInstance();
                }
                
                if(type==1){

                	SavingAccount selectedAccount = new SavingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.001);
                	// calculate saving account's balance after interested.
                	//selectedAccount.calculateCurrentBalance();
                	//this.updateBalance(aid, selectedAccount.getBalance());

                    result = selectedAccount;

                }
                else {
                    result= new CheckingAccount(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,0.01);
                }

                // result=new Account(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,type);
            }
            rs.close();
            c.close();
            if(result.getType()==1){
                //((SavingAccount)result).calculateCurrentBalance();
                //this.updateBalance(AID, result.getBalance());
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }



    @Override
    public void deposit(int AID, DigitMoney money) {
        Account account= getAccount(AID);
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            account.deposit(money);
            PreparedStatement ps=c.prepareStatement("UPDATE Account SET balance=? WHERE aid=?");
            ps.setDouble(1,account.getBalance().getMoney_Num());
            ps.setInt(2,AID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void withdraw(int AID, DigitMoney money) {
        Account account= getAccount(AID);
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            account.withdraw(money);
            PreparedStatement ps=c.prepareStatement("UPDATE Account SET balance=? WHERE aid=?");
            ps.setDouble(1,account.getBalance().getMoney_Num());
            ps.setInt(2,AID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void transfer(int AID1, int AID2, DigitMoney money) {
        Account account1= getAccount(AID1);
        Account account2= getAccount(AID2);
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            account1.transfer(money,account2);
            PreparedStatement ps=c.prepareStatement("UPDATE Account SET balance=? WHERE aid=?");
            ps.setDouble(1,account1.getBalance().getMoney_Num());
            ps.setInt(2,AID1);
            ps.executeUpdate();
            PreparedStatement ps2=c.prepareStatement("UPDATE Account SET balance=? WHERE aid=?");
            ps2.setDouble(1,account2.getBalance().getMoney_Num());
            ps2.setInt(2,AID2);
            ps2.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void exchangeCurrency(int AID, Currency targetCurrency) {
        Account account= getAccount(AID);
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            account.getBalance().exchangeTo(targetCurrency);
            int currencyType=0;
            if(targetCurrency.getName().equals("USDollar")) {
            	currencyType = 1;
            }else if(targetCurrency.getName().equals("EuroDollar")) {
            	currencyType = 2;
            }else if(targetCurrency.getName().equals("CHYen")) {
            	currencyType = 3;
            }
            PreparedStatement ps=c.prepareStatement("UPDATE Account SET balance=?,currency=? WHERE aid=?");
            ps.setDouble(1,account.getBalance().getMoney_Num());
            ps.setInt(2,currencyType);
            ps.setInt(3,AID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    @Override
    public void insert(int uid, int type, DigitMoney balance, java.util.Date startTime) {
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(balance.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(balance.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else {
                currencyType = 3;
            }
            PreparedStatement ps=c.prepareStatement("INSERT INTO Account (uid,type,balance,currency,start_time) VALUES (?,?,?,?,?) ");
            ps.setInt(1,uid);
            ps.setInt(2,type);
            ps.setDouble(3,balance.getMoney_Num());
            ps.setInt(4,currencyType);
            ps.setDate(5, new java.sql.Date(startTime.getTime()));
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateBalance(int AID, DigitMoney balance) {
        Connection c;
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
            }
            PreparedStatement ps=c.prepareStatement("UPDATE Account SET balance=?, currency=? WHERE aid=?");
            ps.setDouble(1,balance.getMoney_Num());
            ps.setInt(2,currencyType);
            ps.setInt(3,AID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

}
