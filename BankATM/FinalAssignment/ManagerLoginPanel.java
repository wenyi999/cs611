

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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ManagerLoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField accountText;
	private JPasswordField codePassword;

	/**
	 * start
	 */

	/**
	 * manager login panel
	 */
	public ManagerLoginPanel() 
	{   
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Manager Login");
		JLabel accountLabel = new JLabel("Account"); // account label
		accountLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JLabel passwordLabel = new JLabel("Password"); // password label
		passwordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		accountText = new JTextField();
		accountText.setFont(new Font("", Font.PLAIN, 18));
		accountText.setColumns(10);
		accountText.setOpaque(false);
		
		codePassword = new JPasswordField();
		codePassword.setFont(new Font("", Font.PLAIN, 18));
		codePassword.setOpaque(false);
		
		// numbers only
		codePassword.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				int key = e.getKeyChar();
				if((!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)))
				{
					e.consume();
				}
			}
		});
		
		JButton loginButton = new JButton("login"); //login button
		loginButton.setBackground(new Color(175, 238, 238));
		loginButton.setBorderPainted(true);
		loginButton.setFocusPainted(true);
		loginButton.addActionListener(new loginButtonHandler(accountText,codePassword));
		
		JButton exitButton = new JButton("back"); //back button
		exitButton.setBackground(new Color(216, 191, 216));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);
		exitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new StartPanel().setVisible(true);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordLabel)
						.addComponent(accountLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(loginButton)
							.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
							.addComponent(exitButton))
						.addComponent(codePassword)
						.addComponent(accountText, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
					.addContainerGap(103, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(162, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(160))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addComponent(label)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(accountLabel)
						.addComponent(accountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(codePassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton)
						.addComponent(exitButton))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Inside class loginButtonHandler
	 */
	public class loginButtonHandler implements ActionListener
	{
		private JTextField accountText;
		private JPasswordField codePassword;
		
		public loginButtonHandler(JTextField accountText,JPasswordField codePassword) {
			this.accountText = accountText;
			this.codePassword = codePassword;
		}
		
		
		// manager login method
		public void actionPerformed(ActionEvent e) {
			String loggingID = accountText.getText();
			String password = new String(codePassword.getPassword());
			
			LoginController logincontroller = new LoginController();
			int success = logincontroller.LoginForManager(loggingID, password);
			if(success==1) {
				dispose();
				ManagerSurface managersurface = new ManagerSurface();
				managersurface.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null,"the ID and password don't match!","message",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}

}