import java.sql.*;

public class ManagerDaoImpl implements ManagerDao {
    @Override
    public Manager getManager(String loggingID) {
        Connection c = null;
        Manager result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT * FROM Manager WHERE loggingID=?");
            ps.setString(1,loggingID);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                String name = rs.getString("name");
                String password=rs.getString("password");
                result=new Manager(name,loggingID,password);
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
    public void changePassword(String loggingID, String password) {
        Connection c = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("UPDATE Manager SET password=? WHERE loggingID=?");
            ps.setString(1,password);
            ps.setString(2,loggingID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void insert(String name, String loggingID, String password) {
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("INSERT INTO Manager (Name,loggingID,password)"
                    +"VALUES (?,?,?)");
            ps.setString(1,name);
            ps.setString(2,loggingID);
            ps.setString(3,password);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    @Override
    public boolean checkThePassword(String loggingID, String password) {
    	Connection c = null;
        String realPassword="";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT password from Manager WHERE loggingID=?");
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
            System.exit(0);
        }
        return realPassword.equals(password);
    }
    
}
