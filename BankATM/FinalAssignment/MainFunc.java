import java.awt.EventQueue;
public class MainFunc {
	
	public static void main(String[] args) 
	{
		ManagerDaoImpl manage = new ManagerDaoImpl();
		Manager manager = manage.getManager("admin");
		if(manager==null) {
			manage.insert("admin", "admin", "123456");
		}
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					StartPanel frame = new StartPanel();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

}
