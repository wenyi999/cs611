// user panel after login
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class UserSurface extends JFrame {

	private JPanel contentPane;
	private static long preMoney = 0;
	private Customer currentCustomer;


	public UserSurface(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
			
		
		String loanStr =  customer.getLoan().toString();
		String customerName = customer.getName();
		JLabel label = new JLabel("Hello,"+ customerName + "  Loan:"+loanStr); // head label
		label.setFont(new Font("", Font.BOLD, 25));
		
		JButton checkingAccountButton = new JButton("Checking Account");//checking account complete!
		checkingAccountButton.setBackground(new Color(204, 204, 255));
		checkingAccountButton.setBorderPainted(true);
		checkingAccountButton.setFocusPainted(true);

		// checking account
		checkingAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{   
				dispose();
				InquiryController inquiryControl = new InquiryController();
				List<Account> checkingAccountlist = inquiryControl.getCheckingAccountOfCustomer(customer.getUid());
				
				new ChooseAccountPanel(customer, checkingAccountlist,0).setVisible(true); // 0 means not transfer, withdraw or save
			}
		});
		
		JButton savingAccountButton = new JButton("Saving Account");//saving account complete!
		savingAccountButton.setBackground(new Color(135, 206, 250));
		savingAccountButton.setBorderPainted(true);
		savingAccountButton.setFocusPainted(true);
		
		// saving account
		savingAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{   
				dispose();
				InquiryController inquiryControl = new InquiryController();
				List<Account> savingAccountlist = inquiryControl.getSavingAccountOfCustomer(customer.getUid());
				new ChooseAccountPanel(customer, savingAccountlist,0).setVisible(true); // 0 means not transfer, withdraw or save
			}
		});
		
		JButton newAccountButton = new JButton("New Account"); // register complete!
		newAccountButton.setBackground(new Color(221, 160, 221));
		newAccountButton.setForeground(new Color(0, 0, 0));
		newAccountButton.setBorderPainted(true);
		newAccountButton.setFocusPainted(true);
		newAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewAccountPanel(customer).setVisible(true);
			}
		});
		
		JButton transferButton = new JButton("Transfer"); // transfer complete!
		transferButton.setBackground(new Color(221, 160, 221));
		transferButton.setForeground(new Color(0, 0, 0));
		transferButton.setBorderPainted(true);
		transferButton.setFocusPainted(true);
		transferButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InquiryController inquiryControl = new InquiryController();
				List<Account> Accountlist = inquiryControl.getAccountOfCustomer(customer.getUid());
				new ChooseAccountPanel(customer,Accountlist,1).setVisible(true); // 1 means transfer
				dispose();
			}
		});
		
		JButton loanButton = new JButton("Loan"); // loan complete!
		loanButton.setBackground(new Color(255, 204, 153));
		loanButton.setBorderPainted(true);
		loanButton.setFocusPainted(true);
		
		loanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				LoanPanel loanpanel = new LoanPanel(customer);
				loanpanel.setVisible(true);
			}		
		});
		
		JButton changeCodeButton = new JButton("Change Password"); // change password complete!
		changeCodeButton.setBackground(new Color(153, 255, 204));
		changeCodeButton.setBorderPainted(true);
		changeCodeButton.setFocusPainted(true);
		
		changeCodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword changePassword = new ChangePassword(customer);
				changePassword.setVisible(true);
			}
		});
		
		JButton queryButton = new JButton("Query Transactions"); // query transaction complete!
		queryButton.setBackground(new Color(153, 255, 204));
		queryButton.setBorderPainted(true);
		queryButton.setFocusPainted(true);
		
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionQuery query = new UserTransactionQuery(customer);
				query.setVisible(true);
			}
		});
		
		JButton exitButton = new JButton("Exit"); // exit complete!
		exitButton.setBackground(new Color(255, 204, 153));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserLoginPanel userlogin = new UserLoginPanel();
				userlogin.setVisible(true);
							
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(newAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(savingAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(checkingAccountButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
						.addComponent(transferButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(250))
				
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(250)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(loanButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
							.addComponent(changeCodeButton, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
							.addComponent(queryButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
							.addComponent(exitButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addGap(50)))
				
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(100, Short.MAX_VALUE)
						.addComponent(label)
						
						.addGap(140))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addComponent(label)
					.addGap(30)					
					.addComponent(checkingAccountButton)
					.addGap(15)
					.addComponent(savingAccountButton)
					.addGap(15)
					.addComponent(newAccountButton)
					.addGap(15)
					.addComponent(transferButton))
					
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(75)
					.addComponent(loanButton)
					.addGap(15)
					.addComponent(changeCodeButton)
					.addGap(15)
					.addComponent(queryButton)
					.addGap(15)
					.addComponent(exitButton))

					
				
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setCustomer(Customer c) {
		this.currentCustomer = c;
	}
	
	public Customer getCustomer() {
		return this.currentCustomer;
	}
}
