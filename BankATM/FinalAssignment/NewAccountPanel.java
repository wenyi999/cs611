//start panel
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;

public class NewAccountPanel extends JFrame 
{
	private JPanel contentPane;
	
	private Customer currentCustomer;



	/**
	 * Login Panel
	 */
	public NewAccountPanel(Customer customer) 
	{ 
		this.currentCustomer = customer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 481, 347);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("New Account"); // new account
		label.setFont(new Font("", Font.BOLD, 25));
		JButton checkingButton = new JButton("Checking Account"); // checking account button
		checkingButton.setBackground(new Color(255, 204, 204));
		checkingButton.setForeground(Color.BLACK);
		checkingButton.setFont(new Font("", Font.PLAIN, 18));
		checkingButton.setBorderPainted(true);
		checkingButton.setFocusPainted(true);
		
		//new checking account method
		checkingButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{   
				JOptionPane.showMessageDialog(null,"Successfully apply a new account!","message",JOptionPane.ERROR_MESSAGE);
				OpenAccountController Accountcontroller = new OpenAccountController();
				Accountcontroller.openZeroAccount(customer.getUid(), 2);
			}
		});
		
		JButton SavingAccount = new JButton("Saving Account"); //saving account button
		SavingAccount.setBackground(new Color(204, 204, 255));
		SavingAccount.setFont(new Font("", Font.PLAIN, 18));
		SavingAccount.setBorderPainted(true);
		SavingAccount.setFocusPainted(true);
		
		//sign up for a new saving account
		SavingAccount.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{   JOptionPane.showMessageDialog(null,"Successfully apply a new account!","message",JOptionPane.ERROR_MESSAGE);
				OpenAccountController Accountcontroller = new OpenAccountController();
				Accountcontroller.openZeroAccount(customer.getUid(), 1);
			}
		});
		
		JButton exitButton = new JButton("Quit");//quit button
		exitButton.setBackground(new Color(255, 218, 185));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);
		
		//click to exit
		exitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				UserSurface usersurface = new UserSurface(customer);
				usersurface.setVisible(true);
						
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(227, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addGap(90))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(150)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(SavingAccount, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(checkingButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
					.addGap(200))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(00, Short.MAX_VALUE)
						.addComponent(label)
						.addGap(240))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(label)
					.addGap(70)
					.addComponent(checkingButton)
					.addGap(30)
					.addComponent(SavingAccount)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addGap(46))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
