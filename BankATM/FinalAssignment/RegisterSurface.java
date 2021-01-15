// sign in panel

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class RegisterSurface extends JFrame 
{

	private JPanel contentPane;
	private JTextField name;
	private JTextField loggingID;
	private JTextField address;
	private JPasswordField password;
	private JPasswordField confirmPassword;



	public RegisterSurface() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("", Font.PLAIN, 12));
		
		JLabel idLabel = new JLabel("Login ID");
		nameLabel.setFont(new Font("", Font.PLAIN, 12));
		
		JLabel addressLabel = new JLabel("Address");
		nameLabel.setFont(new Font("", Font.PLAIN, 12));
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("", Font.PLAIN, 12));
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setFont(new Font("", Font.PLAIN, 12));
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBackground(new Color(175, 238, 238));
		confirmButton.setBorderPainted(false);
		confirmButton.setFocusPainted(false);
		
		name = new JTextField();
		name.setFont(new Font("", Font.PLAIN, 18));
		name.setOpaque(false);
		
		loggingID = new JTextField();
		loggingID.setFont(new Font("", Font.PLAIN, 18));
		loggingID.setOpaque(false);
		
		address = new JTextField();
		address.setFont(new Font("", Font.PLAIN, 18));
		address.setOpaque(false);
		
		password = new JPasswordField();
		password.setFont(new Font("", Font.PLAIN, 18));
		password.setOpaque(false);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setFont(new Font("", Font.PLAIN, 18));
		confirmPassword.setOpaque(false);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(false);
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserLoginPanel().setVisible(true);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordLabel, Alignment.TRAILING)
						.addComponent(confirmPasswordLabel, Alignment.TRAILING)
						.addComponent(nameLabel, Alignment.TRAILING)
						.addComponent(idLabel, Alignment.TRAILING)
						.addComponent(addressLabel, Alignment.TRAILING))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(confirmButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cancelButton))
						.addComponent(confirmPassword, Alignment.TRAILING)
						.addComponent(password, Alignment.TRAILING)
						.addComponent(address, Alignment.TRAILING)
						.addComponent(loggingID, Alignment.TRAILING)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addContainerGap(7, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(loggingID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(idLabel))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(addressLabel))
					.addGap(10)	
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmPasswordLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmButton)
						.addComponent(cancelButton))
					.addGap(15))
		);
		//name.addKeyListener(new PasswordText());
		password.addKeyListener(new PasswordText());
		confirmPassword.addKeyListener(new PasswordText());
		confirmButton.addActionListener(new confirmButtonHandler(name, loggingID ,password, confirmPassword, address));
		contentPane.setLayout(gl_contentPane);
	}
	
	// numbers only
	public class PasswordText extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			int key=e.getKeyChar();
			
			if((!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)) )
			{
				e.consume();
			}
		}
	}
	
	// confirm listener
	public class confirmButtonHandler implements ActionListener{
		private JTextField name;
		private JTextField address;
		private JTextField loggingID;
		private JPasswordField password;
		private JPasswordField confirmPassword;
		private int sameChar = 0;
		
		public confirmButtonHandler(JTextField name,JTextField logging_id,JPasswordField newPassword,JPasswordField confirmPassword,JTextField address_text) {
			this.name = name;
			this.loggingID = logging_id;
			this.password = newPassword;
			this.confirmPassword = confirmPassword;
			this.address = address_text;
		}
		
		

		// Register for a new user
		public void actionPerformed(ActionEvent e) 
		{
			
			//Todo
			LoginController logincontroller = new LoginController();
			
			String customer_name = name.getText();
			String logging_Id = loggingID.getText();
			String password_1 = new String(password.getPassword());
			String password_2 = new String(confirmPassword.getPassword());
			String address_str = address.getText();
			
			if(password_1.equals(password_2)) {
				int flag = logincontroller.Signup(customer_name, logging_Id, password_1, 0, 0, address_str);
				if(flag==0) {
					JOptionPane.showMessageDialog(null,"the logging ID has already been used!","message",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null,"the 2 passwords don't match!","message",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}
			
	}
}
