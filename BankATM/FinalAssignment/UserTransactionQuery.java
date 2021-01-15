// register panel

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class UserTransactionQuery extends JFrame 
{
	private JTable table;
	private JPanel contentPane;
	private JTextField name;
	private String[] columnNames = {"a"};
    private Object[][] rowData = {{"b"}};


	public UserTransactionQuery(Customer customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("Account ID");
		nameLabel.setFont(new Font("", Font.PLAIN, 18));
				
		JButton queryButton = new JButton("Query");
		queryButton.setBackground(new Color(175, 238, 238));
		queryButton.setBorderPainted(true);
		queryButton.setFocusPainted(true);
		
		name = new JTextField();
		name.setFont(new Font("", Font.PLAIN, 18));
		name.setOpaque(false);
		
		
		// implement query method. name is input account ID
		queryButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						int uid = customer.getUid();
						
						TransactionController transactioncontrol = new TransactionController();
						
						String input_accountId_Str = name.getText();
						int input_accountId = Integer.valueOf(input_accountId_Str);
						
						List<Transaction> transactionlist = transactioncontrol.showAccountTransactions(input_accountId);
						
						if(!transactionlist.isEmpty()) {
							
							String[][] transaction_attrs =  new String[transactionlist.size()][4];
							
							for(int i =0; i < transactionlist.size();i++) {
								
								for(int j = 0; j<4 ;j++) {
									
									Transaction a_transaction =  transactionlist.get(i);
									
									if(j==0) {
										transaction_attrs[i][j] = String.valueOf(a_transaction.getStartAccount().getAccountId());
									}else if(j==1) {
										transaction_attrs[i][j] = String.valueOf(a_transaction.getReceiveAccount().getAccountId());
									}else if(j==2) {
										transaction_attrs[i][j] = a_transaction.getMoney().toString();
									}else if(j==3) {
										transaction_attrs[i][j] = a_transaction.getTime().toString();

									}
									
									
								}
								
							}
							table.setModel(new DefaultTableModel(
									transaction_attrs,
									new String[] {
										"from account", "to account", "money", "date"
									}
								) 
										);
							
							
						}else {
							table.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"from account", "to account", "money", "date"
									}
								) 
										);
						}
						
						
						
						
					
						
						
						
					}
					
				});
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(true);
		cancelButton.setFocusPainted(true);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserSurface usersurface = new UserSurface(customer);
				usersurface.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		table.setBackground(new Color(250, 235, 215));
		table.setFont(new Font("", Font.PLAIN, 16));
		
		// get customer's all transactions
		int uid = customer.getUid();
		
		TransactionController transactControl = new TransactionController();
		List<Transaction> transactionlist_all = transactControl.showCustomerTransactions(uid);
		
		if(!transactionlist_all.isEmpty()) {
			
			String[][] transaction_attrs =  new String[transactionlist_all.size()][4];
			
			for(int i =0; i < transactionlist_all.size();i++) {
				
				for(int j = 0; j<4 ;j++) {
					
					Transaction a_transaction =  transactionlist_all.get(i);
					
					if(j==0) {
						transaction_attrs[i][j] = String.valueOf(a_transaction.getStartAccount().getAccountId());
					}else if(j==1) {
						transaction_attrs[i][j] = String.valueOf(a_transaction.getReceiveAccount().getAccountId());
					}else if(j==2) {
						transaction_attrs[i][j] = a_transaction.getMoney().toString();
					}else if(j==3) {
						transaction_attrs[i][j] = a_transaction.getTime().toString();

					}
					
					
				}
				
			}
			table.setModel(new DefaultTableModel(
					transaction_attrs,
					new String[] {
						"from account", "to account", "money", "date"
					}
				) 
						);
			
			
		}else {
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"from account", "to account", "money", "date"
					}
				) 
						);
		}
		
		
		
		
		
		// get value for table
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"from account", "to account", "money", "date"
			}
		) 
				);
		scrollPane.setViewportView(table);

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(nameLabel))
						.addGap(34)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGap(0)
						.addComponent(queryButton)
					.addGap(124)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cancelButton))
					.addGap(14)	
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane))
		));
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(queryButton)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel))
					.addComponent(scrollPane)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton))
					.addGap(30)));
		contentPane.setLayout(gl_contentPane);
	}
	
}