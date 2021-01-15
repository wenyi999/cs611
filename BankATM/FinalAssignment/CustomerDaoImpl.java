import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer getCustomer(String loggingID) {
        Connection c = null;
        //Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            String sql="SELECT * FROM Customer WHERE loggingID=?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,loggingID);
            ResultSet rs=preparedStatement.executeQuery();
            while ( rs.next() ) {
                String name = rs.getString("name");
                String password=rs.getString("password");
                boolean inDebt=rs.getInt("in_debt")==1;
                double loan=rs.getDouble("loan");
                String addr=rs.getString("address");
                int uid=rs.getInt("uid");
                int currency=rs.getInt("currency");
                Currency currencyWithRate = null;
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                result=new Customer(name,loggingID,password,addr,new DigitMoney(loan,currencyWithRate),uid);
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
    public Customer getCustomerByUid(int uid) {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c.setAutoCommit(false);
            String sql="SELECT * FROM Customer WHERE uid=?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,uid);
            ResultSet rs=preparedStatement.executeQuery();
            while ( rs.next() ) {
                String name = rs.getString("name");
                String loggingID = rs.getString("loggingID");
                String password=rs.getString("password");
                boolean inDebt=rs.getInt("in_debt")==1;
                double loan=rs.getDouble("loan");
                String addr=rs.getString("address");
                int uidfromtable=rs.getInt("uid");
                int currency=rs.getInt("currency");
                Currency currencyWithRate = null;
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                result=new Customer(name,loggingID,password,addr,new DigitMoney(loan,currencyWithRate),uidfromtable);
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
    public List<Customer> getAllCustomer() {
    	Connection c =null ;
        //Statement stmt = null;
        List<Customer> result= new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            String sql="SELECT * FROM Customer";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while ( rs.next() ) {
            	String loggingID = rs.getString("loggingID");
                String name = rs.getString("name");
                String password=rs.getString("password");
                boolean inDebt=rs.getInt("in_debt")==1;
                double loan=rs.getDouble("loan");
                String addr=rs.getString("address");
                int uid=rs.getInt("uid");
                int currency=rs.getInt("currency");
                Currency currencyWithRate = null;
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                result.add(new Customer(name,loggingID,password,addr,new DigitMoney(loan,currencyWithRate),uid));
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
    public int changePassword(String loggingID, String password) {
    	int flag = 1;
        Connection c = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement preparedStatement=c.prepareStatement("UPDATE Customer SET password=? WHERE loggingID=?");
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,loggingID);
            preparedStatement.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            flag = 0;
            
        }
        return flag;
    }
    // return 1 means ok, 0 means fail to insert, maybe loggingID already exists.
    @Override
    public int insert(String name, String loggingID, String password, int inDebt, DigitMoney loan, String address) {
    	int result = 1;
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            String namet=loan.getCurrency().getName();
            //System.out.println(namet);
            if(namet.equals("USDollar")) {
                currencyType = 1;
            }else if(namet.equals("EuroDollar")) {
                currencyType = 2;
            }else if(namet.equals("CHYen")) {
                currencyType = 3;
            }
            else {currencyType=3;}
            try (PreparedStatement insert = c.prepareStatement(
                    "insert into Customer(name, loggingID, password, in_debt, loan, currency, address) values(?,?,?,?,?,?,?)")) {
                insert.setString(1, name);
                insert.setString(2, loggingID);
                insert.setString(3,password);
                insert.setInt(4,inDebt);
                insert.setDouble(5,loan.getMoney_Num());
                insert.setInt(6,currencyType);
                insert.setString(7,address);

                insert.executeUpdate();
            } catch (SQLException e) {
            	result = 0;
                throw new RuntimeException("Cannot insert the customer into SQL Database.", e);
            }
            c.commit();
            c.close();
        } catch ( Exception e ) {
        	result = 0;
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
        }
        
        return result;
    }

    @Override
    public void updateTheLoan(String loggingID,DigitMoney loan) {
        Connection c = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(loan.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(loan.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(loan.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }else {currencyType=3;}
            PreparedStatement ps=c.prepareStatement("UPDATE Customer SET loan=?, currency=? WHERE loggingID=?");
            ps.setDouble(1,loan.getMoney_Num());
            ps.setInt(2,currencyType);
            ps.setString(3,loggingID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
        }
    }

    @Override
    public void updateInDebt(String loggingID,int inDebt) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("UPDATE Customer SET in_debt=? WHERE loggingID=?");
            ps.setInt(1,inDebt);
            ps.setString(2,loggingID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
        }
    }
    @Override
    public void addLoan(int uid,DigitMoney deltaloan) {
    	Customer selectedCustomer = this.getCustomerByUid(uid);
    	
    	DigitMoney loan = selectedCustomer.getLoan();
    	loan.add(deltaloan);
    	
    	this.updateTheLoan(selectedCustomer.getLoggingID(), loan);
    }
    
    @Override
    public void decLoan(int uid,DigitMoney deltaloan) {
        Customer selectedCustomer = this.getCustomerByUid(uid);
    	
    	DigitMoney loan = selectedCustomer.getLoan();
    	loan.decrease(deltaloan);
    	
    	this.updateTheLoan(selectedCustomer.getLoggingID(), loan);
    }

    @Override
    public boolean checkThePassword(String loggingID, String password) {
        Connection c = null;
        String realPassword="";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT password from Customer WHERE loggingID=?");
            ps.setString(1,loggingID);
            ResultSet rs=ps.executeQuery();
            
            while ( rs.next() ) {
            	realPassword = rs.getString("password");
            }
            

            rs.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
        }
        return realPassword.equals(password);
    }
}
