import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao{
    @Override
    public List<Transaction> getTransaction(int aid1, int aid2) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM \"Transaction\" WHERE aid1=? and aid2=?");
            ps.setInt(1,aid1);
            ps.setInt(2,aid2);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;

    }
    
    @Override
    public List<Transaction> getAllTransaction(){
    	Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM \"Transaction\"");
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionFrom(int aid) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM \"Transaction\" WHERE aid1=?");
            ps.setInt(1,aid);
            ResultSet rs=ps.executeQuery();
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid2=rs.getInt("aid2");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionTo(int aid) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM \"Transaction\" WHERE aid2=?");
            ps.setInt(1,aid);
            ResultSet rs =ps.executeQuery();
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid1=rs.getInt("aid1");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;

    }

    @Override
    public List<Transaction> getTransaction(Date datetime) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM \"Transaction\" WHERE time=?");
            ps.setDate(1, new java.sql.Date(datetime.getTime()));
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),datetime));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    @Override
    public void insert(int aid1, int aid2, DigitMoney money, Date time) {
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(money.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(money.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else {
                currencyType = 3;
            }
            PreparedStatement ps=c.prepareStatement("INSERT INTO \"Transaction\" (aid1,aid2,money,currency,time) VALUES (?,?,?,?,?)");
            ps.setInt(1,aid1);
            ps.setInt(2,aid2);
            ps.setDouble(3,money.getMoney_Num());
            ps.setInt(4,currencyType);
            ps.setDate(5, new java.sql.Date(time.getTime()));
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    @Override
    public List<Transaction> getTransactionByUser(int uid1, int uid2) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT tempTable.* From " +
                    "(SELECT \"Transaction\".* FROM \"Transaction\" Join Account A1 on A1.aid = \"Transaction\".aid1 WHERE A1.uid=?) AS tempTable" +
                    " Join Account A2 on A2.aid=tempTable.aid2 Where A2.uid=?");
            ps.setInt(1,uid1);
            ps.setInt(2,uid2);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;

                if(currency==1) {
                    currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                    currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                    currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionFromByUser(int uid) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT \"Transaction\".* FROM \"Transaction\" Join Account A1 on A1.aid = \"Transaction\".aid1 WHERE A1.uid=?");
            ps.setInt(1,uid);
            ResultSet rs =ps.executeQuery();
            while ( rs.next() ) {
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;

                if(currency==1) {
                    currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                    currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                    currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionToByUser(int uid) {
        Connection c;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT \"Transaction\".* FROM \"Transaction\" Join Account A2 on A2.aid = \"Transaction\".aid2 WHERE A2.uid=?");
            ps.setInt(1,uid);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;

                if(currency==1) {
                    currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                    currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                    currencyWithRate=CHYen.getInstance();
                }
                rs.close();
                c.close();
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }
}
