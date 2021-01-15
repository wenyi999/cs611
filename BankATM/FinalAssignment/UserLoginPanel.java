
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class UserLoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField accountText;
	private JPasswordField codePassword;
	private JButton registerButton;
	private JButton loginButton;
	private JButton exitButton;
	private JLabel promptLabel;
	private int passwordRestNum = 3;
	public static String userAccount;
	public static int fileExsist = 0;
	


	public UserLoginPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel accountLabel = new JLabel("Account");
		accountLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JLabel passwordLabel = new JLabel("Password");//密码标签
		passwordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		accountText = new JTextField();
		accountText.setFont(new Font("", Font.PLAIN, 18));
		accountText.setOpaque(false);
		accountText.setColumns(10);
		
		codePassword = new JPasswordField();
		codePassword.setFont(new Font("", Font.PLAIN, 18));
		codePassword.setOpaque(false);
		
		//numbers only
		codePassword.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				int key = e.getKeyChar();
				if((!(key >= KeyEvent.VK_0 && key<=KeyEvent.VK_9)))
				{
					e.consume();
				}
			}
		});
		
		registerButton = new JButton("sign up");
		registerButton.setBackground(new Color(175, 238, 238));
		registerButton.setBorderPainted(true);
		registerButton.setFocusPainted(true);
		registerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				RegisterSurface registerSurface = new RegisterSurface();
				registerSurface.setVisible(true);
			}
		});
		
		loginButton = new JButton("login");
		loginButton.setBackground(new Color(175, 238, 238));
		loginButton.setBorderPainted(true);
		loginButton.setFocusPainted(true);
		loginButton.addActionListener(new loginButtonHandler(accountText,codePassword));
		
		exitButton = new JButton("back");
		exitButton.setBackground(new Color(245, 222, 179));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);
		exitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				StartPanel startingpanel = new StartPanel();
				startingpanel.setVisible(true);
				// new NewAccountPanel().setVisible(true);
			}
		});
		
		promptLabel = new JLabel("User Login");
		promptLabel.setFont(new Font("", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordLabel)
								.addComponent(accountLabel))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(registerButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(loginButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(exitButton))
								.addComponent(accountText)
								.addComponent(codePassword, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(159)
							.addComponent(promptLabel)))
					.addContainerGap(110, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(promptLabel)
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(accountLabel)
						.addComponent(accountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(codePassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(registerButton)
						.addComponent(loginButton)
						.addComponent(exitButton))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	// login listener
	public class loginButtonHandler implements ActionListener{
		private JTextField accountText;
		private JPasswordField password;
		
		public loginButtonHandler(JTextField accountText,JPasswordField password) {
			this.accountText = accountText;
			this.password = password;
		}
		
		
		// login method
		public void actionPerformed(ActionEvent e) {
			
			String loggingID = this.accountText.getText();
			String passwardStr = new String(password.getPassword());
			
			LoginController loginController = new LoginController();
			Customer currentCustomer = loginController.Login(loggingID, passwardStr);
			
			if(currentCustomer!=null) {
				dispose();
				UserSurface userFrame = new UserSurface(currentCustomer);
				userFrame.setVisible(true);
				
			}else {
				JOptionPane.showMessageDialog(null,"the ID and password don't match!","message",JOptionPane.ERROR_MESSAGE);
			}
			
			
			// StartPanel.list = (List<User>) StartPanel.userFile.printFile();
//			int accountNum = 0;
//			int passwordNum = 0;
//			String passwordStr = new String(password.getPassword());
//			StringBuilder password = null;
//			
//			for(int i = 0;i < StartPanel.list.size();i++)
//			{
//				if(accountText.getText().equals(StartPanel.list.get(i).getLoggingID()))
//				{
//					userAccount = accountText.getText();
//					accountNum++;
//					break;
//				}
//			}
//			if(accountNum == 0)//user not exist
//			{
//				JOptionPane.showMessageDialog(null,"Can't find this user","ERROR",JOptionPane.ERROR_MESSAGE);
//			}
//			if(accountNum == 1)//user exist
//			{
//				for(int i = 0;i < StartPanel.list.size();i++)//check password
//				{
//					if(userAccount.equals(StartPanel.list.get(i).getLoggingID()))//find user
//					{
//						password = new StringBuilder(StartPanel.list.get(i).getPassword());
//						password.replace(StartPanel.list.get(i).getPassword().length()-1, StartPanel.list.get(i).getPassword().length(), "");
//						if(passwordStr.equals(new String(password.toString())))//correct password
//						{
//							passwordNum++;
//							break;
//						}
//						else //wrong password
//						{
//							if(passwordRestNum == 1)
//							{
//								JOptionPane.showMessageDialog(null,"You have entered 3 times! Exit!","message",JOptionPane.ERROR_MESSAGE);
//								dispose();
//								StartPanel mainFrame = new StartPanel();
//								mainFrame.setVisible(true);
//							}
//							if(passwordRestNum != 1)
//							{
//								JOptionPane.showMessageDialog(null,"Wrong password! Remain enter time: "+(--passwordRestNum),"message",JOptionPane.ERROR_MESSAGE);
//								if(passwordStr.equals(new String(password.toString())))
//								{
//									passwordNum++;
//									break;
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			if(accountNum == 1 && passwordNum == 1)//all correct, enter user surfaces
//			{
//				userAccount = accountText.getText();
//				dispose();
//				JOptionPane.showMessageDialog(null,"login success","message",JOptionPane.INFORMATION_MESSAGE);
//				UserSurface userFrame = new UserSurface();
//				userFrame.setVisible(true);
//			}
		}
	}
}