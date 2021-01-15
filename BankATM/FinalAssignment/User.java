
public class User {
	
	private String name;
	private String loggingID;
	private String password;
	
	
	public User() {
		this.setName("A user");
		this.setLoggingID("test_user");
		this.setPassword("123456");
	}
	
	public User(String n, String id, String pw) {
		this.setName(n);
		this.setLoggingID(id);
		this.setPassword(pw);
	}
	
	
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setLoggingID(String id) {
		this.loggingID = id;
	}
	
	public String getLoggingID() {
		return this.loggingID;
	}
	
	public void setPassword(String pw) {
		this.password = pw;
	}
	
	public String getPassword() {
		return this.password;
	}

}
