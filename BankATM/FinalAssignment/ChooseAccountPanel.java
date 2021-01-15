// register panel

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ChooseAccountPanel extends JFrame 
{
	private JTable table;
	private JPanel contentPane;
	private JTextField name;
	private String[] columnNames = {"a"};
    private Object[][] rowData = {{"b"}};


	public ChooseAccountPanel(Customer customer,List<Account> accountlist,int transferOrNot) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("Choose Account");
		nameLabel.setFont(new Font("", Font.PLAIN, 18));
				
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBackground(new Color(175, 238, 238));
		confirmButton.setBorderPainted(true);
		confirmButton.setFocusPainted(true);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				// not transfer
				if(transferOrNot==0) {
					
					if(row!=-1) {
						
						// get selected account
						Account selectedAccount = accountlist.get(row);
						// judge saving or checking
						if(selectedAccount.getType()==1) {
							dispose();
							SavingAccountPanel savingPanel = new SavingAccountPanel(customer, selectedAccount);
							savingPanel.setVisible(true);
						}else {
							dispose();
							CheckingAccountPanel checkingPanel = new CheckingAccountPanel(customer, selectedAccount);
							checkingPanel.setVisible(true);
						}
						
					}else {
						
						JOptionPane.showMessageDialog(null,"please select an account","message",JOptionPane.ERROR_MESSAGE);
					}
				
					
				}else if(transferOrNot==1) {
				 // transfer
                     if(row!=-1) {
						
						// get selected account
						Account selectedAccount = accountlist.get(row);
						// judge saving or checking
						dispose();
						TransferPanel transferpanel = new TransferPanel(customer,selectedAccount);
						transferpanel.setVisible(true);
						
					}else {
						
						JOptionPane.showMessageDialog(null,"please select an account","message",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				
				
			}
		});
		
		name = new JTextField();
		name.setFont(new Font("", Font.PLAIN, 18));
		name.setOpaque(false);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(true);
		cancelButton.setFocusPainted(true);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserSurface mainFrame = new UserSurface(customer);
				mainFrame.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		table.setBackground(new Color(250, 235, 215));
		table.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						int row = table.rowAtPoint(e.getPoint());
					}
				});
		
		table.setFont(new Font("", Font.PLAIN, 16));
		// need to get the header to String[] and value of every accounts to Object[][]
		
		if(!accountlist.isEmpty()) {
			String[][] account_attrs =  new String[accountlist.size()][4];
			
			for(int i =0; i < accountlist.size();i++) {
				
				for(int j = 0; j<4 ;j++) {
					
					Account a_account =  accountlist.get(i);
					
					if(j==0) {
						account_attrs[i][j] = String.valueOf(a_account.getAccountId());
					}else if(j==1) {
						account_attrs[i][j] = a_account.getBalance().toString();
					}else if(j==2) {
						if(a_account.getType()==1) {
							account_attrs[i][j] = "Saving";
						}else {
							account_attrs[i][j] = "Checking";
						}
						
					}else if(j==3) {
						account_attrs[i][j] = a_account.getStartTime().toString();

					}
					
					
				}
				
			}
			
			table.setModel(new DefaultTableModel(
					account_attrs,
					new String[] {
						"aid", "balance", "type", "date"
					}
				) 
						);
			
			
		}else {
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"aid", "balance", "type", "date"
					}
				) 
						);
		}
		
		
		
		scrollPane.setViewportView(table);

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(nameLabel))
						.addGap(34))
						
					.addGap(124)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(confirmButton)
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
						.addComponent(nameLabel))
					.addComponent(scrollPane)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmButton)
						.addComponent(cancelButton))
					.addGap(30)));
		contentPane.setLayout(gl_contentPane);
	}
	
}