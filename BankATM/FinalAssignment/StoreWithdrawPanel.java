import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class StoreWithdrawPanel extends JFrame{

	private JPanel contentPane;
	private JTextField amountText;
	private JRadioButton jr1 = new JRadioButton("CHY");
    private JRadioButton jr2 = new JRadioButton("USD");
    private JRadioButton jr3 = new JRadioButton("EUR");



	public StoreWithdrawPanel(Customer customer,Account account,int saveOrwithdraw) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		JLabel label = new JLabel("Store&Withdraw");
		label.setFont(new Font("", Font.BOLD, 25));
        
		
//		jr1.setBackground(null);
//		jr1.setBorderPainted(false);
//		jr1.setFocusPainted(false);
//		jr2.setBackground(null);
//		jr2.setBorderPainted(false);
//		jr2.setFocusPainted(false);
//		jr3.setBackground(null);
//		jr3.setBorderPainted(false);
//		jr3.setFocusPainted(false);
		
		jr1.setBackground(new Color(248, 248, 255));
		jr1.setBorderPainted(false);
		jr1.setFocusPainted(false);
		jr2.setBackground(new Color(248, 248, 255));
		jr2.setBorderPainted(false);
		jr2.setFocusPainted(false);
		jr3.setBackground(new Color(248, 248, 255));
		jr3.setBorderPainted(false);
		jr3.setFocusPainted(false);
		
        ButtonGroup bg = new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);
        bg.add(jr3);
        amountText = new JTextField();
		amountText.setFont(new Font("", Font.PLAIN, 18));
		amountText.setOpaque(false);
		// numbers only
		amountText.addKeyListener(new KeyAdapter() 
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
		
		JLabel amountLabel = new JLabel("Currency value");
		amountLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBackground(new Color(175, 238, 238));
		confirmButton.setBorderPainted(true);
		confirmButton.setFocusPainted(true);
		
		
		// store/withdraw method to do
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jr1.isSelected())
				{
					//CHY
					  String value_str = amountText.getText();
					  double amount_value = Double.valueOf(value_str);
					  OpenAccountController openaccountController = new OpenAccountController();
					  DigitMoney deltamoney = new DigitMoney(amount_value,CHYen.getInstance());
					  if(saveOrwithdraw==1) {
						  openaccountController.DepositMoney(account.getAccountId(), deltamoney); // save the money
					  }else if(saveOrwithdraw==2) {
						  openaccountController.WithDrawMoney(account.getAccountId(), deltamoney);// withdraw money
					  }
					
				}
				else if(jr2.isSelected())
				{
					//USD
					  String value_str = amountText.getText();
					  double amount_value = Double.valueOf(value_str);
					  OpenAccountController openaccountController = new OpenAccountController();
					  DigitMoney deltamoney = new DigitMoney(amount_value,USDollar.getInstance());
					  if(saveOrwithdraw==1) {
						  openaccountController.DepositMoney(account.getAccountId(), deltamoney); // save the money
					  }else if(saveOrwithdraw==2) {
						  openaccountController.WithDrawMoney(account.getAccountId(), deltamoney);// withdraw money
					  }

					
				}
				else if(jr3.isSelected())
				{
					//EUR
					  String value_str = amountText.getText();
					  double amount_value = Double.valueOf(value_str);
					  OpenAccountController openaccountController = new OpenAccountController();
					  DigitMoney deltamoney = new DigitMoney(amount_value,EuroDollar.getInstance());
					  if(saveOrwithdraw==1) {
						  openaccountController.DepositMoney(account.getAccountId(), deltamoney); // save the money
					  }else if(saveOrwithdraw==2) {
						  openaccountController.WithDrawMoney(account.getAccountId(), deltamoney);// withdraw money
					  }

					
				}
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(true);
		cancelButton.setFocusPainted(true);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// return to one of saving/checking account
				dispose();
				UserSurface usersurface = new UserSurface(customer);
				usersurface.setVisible(true);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
    			gl_contentPane.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_contentPane.createSequentialGroup()
    					.addComponent(jr1)
    					.addGap(20)
    					.addComponent(jr2)
    					.addGap(20)
    					.addComponent(jr3)
    					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    					.addContainerGap())
    				.addGroup(gl_contentPane.createSequentialGroup()
    					.addGap(222)
    					.addComponent(confirmButton)
    					.addComponent(cancelButton))
    				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
    						.addContainerGap(100, Short.MAX_VALUE)
    						.addComponent(label)
    						.addGap(200))
    				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
    						.addContainerGap(00, Short.MAX_VALUE)
    						.addComponent(amountLabel)
    						.addComponent(amountText)
    						.addGap(150))
    		);
        
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(amountLabel)
								.addComponent(amountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						
						.addComponent(jr1)
						.addGap(50)
						.addComponent(jr2)
						.addGap(50)
						.addComponent(jr3)
						.addGap(0)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGap(150)
						.addComponent(confirmButton)
						.addComponent(cancelButton))
					
		);

		contentPane.setLayout(gl_contentPane);
	}
}
