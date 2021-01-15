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
import java.awt.event.ActionEvent;

public class SavingAccountPanel extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SavingAccountPanel frame = new SavingAccountPanel(new Customer(), new Account());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SavingAccountPanel(Customer customer,Account account) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Saving Account");
		label.setFont(new Font("", Font.BOLD, 25));
				
		
		JButton seekButton = new JButton("Query");
		seekButton.setBackground(new Color(204, 204, 255));
		seekButton.setBorderPainted(true);
		seekButton.setFocusPainted(true);

		
		// query method for saving account
		seekButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String balanceStr = account.getBalance().toString();
				
				String message = "The balance is "+ balanceStr;
				
				JOptionPane.showMessageDialog(null,message);
			
			
			
			}
		});
		
		JButton storeButton = new JButton("Store");
		storeButton.setBackground(new Color(135, 206, 250));
		storeButton.setBorderPainted(true);
		storeButton.setFocusPainted(true);

		
		// store for saving account
		storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StoreWithdrawPanel storeWithdraw = new StoreWithdrawPanel(customer,account,1);//1 means save money
				storeWithdraw.setVisible(true);
				
			}
		});
		
		JButton takeButton = new JButton("Withdraw");
		takeButton.setBackground(new Color(221, 160, 221));
		takeButton.setForeground(new Color(0, 0, 0));
		takeButton.setBorderPainted(true);
		takeButton.setFocusPainted(true);
		
		// withdraw method for saving account
		takeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   
				dispose();
				StoreWithdrawPanel storeWithdraw = new StoreWithdrawPanel(customer,account,2);//2 means withdraw money
				storeWithdraw.setVisible(true);
				
			}
		});
		
		JButton exitButton = new JButton("Log out");
		exitButton.setBackground(new Color(255, 204, 153));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				UserSurface usersurface = new UserSurface(customer);
				usersurface.setVisible(true);
							
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(exitButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
						.addComponent(takeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(storeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(seekButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
					.addGap(167))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(100, Short.MAX_VALUE)
						.addComponent(label)
						.addGap(100))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addComponent(label)
					.addGap(20)
					.addComponent(seekButton)
					.addGap(20)
					.addComponent(storeButton)
					.addGap(20)
					.addComponent(takeButton)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
