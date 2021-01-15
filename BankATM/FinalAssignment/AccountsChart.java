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
import java.awt.event.ActionEvent;

public class AccountsChart extends JFrame 
{
	private JTable table;
	private JPanel contentPane;
	private JTextField name;
	private String[] columnNames = {"a"};
    private Object[][] rowData = {{"b"}};



	public AccountsChart() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("User ID");
		nameLabel.setFont(new Font("", Font.PLAIN, 18));
				
		JButton queryButton = new JButton("Query");
		queryButton.setBackground(new Color(175, 238, 238));
		queryButton.setBorderPainted(true);
		queryButton.setFocusPainted(true);
		
		name = new JTextField();
		name.setFont(new Font("", Font.PLAIN, 18));
		name.setOpaque(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		table.setBackground(new Color(250, 235, 215));
		table.setFont(new Font("", Font.PLAIN, 16));
		
		
		// need to get the header to String[] and value of every accounts to Object[][]
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"name", "loggingID", "address", "loan"
			}
		) 
				);
		scrollPane.setViewportView(table);
		
		
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uid_str = name.getText();
				int uid = Integer.valueOf(uid_str);
				
				InquiryController inquiry = new InquiryController();
				Customer selectedCustomer = inquiry.showCustomer(uid);
				
				String[][] attrs = new String[1][4];
				attrs[0][0] = selectedCustomer.getName();
				attrs[0][1] = selectedCustomer.getLoggingID();
				attrs[0][2] = selectedCustomer.getAddress();
				attrs[0][3] = selectedCustomer.getLoan().toString();
				System.out.println(attrs[0][3]);
				table.setModel(new DefaultTableModel(
						attrs,
						new String[] {
							"name", "loggingID", "address", "loan"
						}
					) 
							);
				
			}
		});
		
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(true);
		cancelButton.setFocusPainted(true);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagerSurface mainFrame = new ManagerSurface();
				mainFrame.setVisible(true);
			}
		});
		
		//

		
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
					));
		contentPane.setLayout(gl_contentPane);
	}
	
}