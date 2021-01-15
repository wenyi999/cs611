// change password panel

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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JPasswordField confirmPassword;
	private JLabel samePassword;



	public ChangePassword(Customer customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel oldPasswordLabel = new JLabel("Old Password");
		oldPasswordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JLabel newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JLabel confirmPasswordLabel = new JLabel("Confirm New Password");
		confirmPasswordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBackground(new Color(175, 238, 238));
		confirmButton.setBorderPainted(false);
		confirmButton.setFocusPainted(false);
		
		oldPassword = new JPasswordField();
		oldPassword.setFont(new Font("", Font.PLAIN, 18));
		oldPassword.setOpaque(false);
		
		newPassword = new JPasswordField();
		newPassword.setFont(new Font("", Font.PLAIN, 18));
		newPassword.setOpaque(false);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setFont(new Font("", Font.PLAIN, 18));
		confirmPassword.setOpaque(false);
		
		samePassword = new JLabel("Same Password");
		samePassword.setForeground(Color.RED);
		samePassword.setVisible(false);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(false);
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserSurface usersurface = new UserSurface(customer);
				usersurface.setVisible(true);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(newPasswordLabel)
						.addComponent(confirmPasswordLabel)
						.addComponent(oldPasswordLabel))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(confirmButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cancelButton))
						.addComponent(confirmPassword, Alignment.TRAILING)
						.addComponent(newPassword, Alignment.TRAILING)
						.addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(samePassword)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(oldPasswordLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(newPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPasswordLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmPasswordLabel)
						.addComponent(samePassword))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmButton)
						.addComponent(cancelButton))
					.addGap(30))
		);
		oldPassword.addKeyListener(new PasswordText());
		newPassword.addKeyListener(new PasswordText());
		confirmPassword.addKeyListener(new PasswordText());
		confirmButton.addActionListener(new confirmButtonHandler(customer,oldPassword,newPassword,confirmPassword));
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
		private Customer customer;
		private JPasswordField oldPassword;
		private JPasswordField newPassword;
		private JPasswordField confirmPassword;
		private int sameChar = 0;
		
		public confirmButtonHandler(Customer currentCustomer,JPasswordField oldPassword,JPasswordField newPassword,JPasswordField confirmPassword) {
			this.customer = currentCustomer;
			this.oldPassword = oldPassword;
			this.newPassword = newPassword;
			this.confirmPassword = confirmPassword;
		}
		
		
		// method for changing password
		public void actionPerformed(ActionEvent e) {
			String oldpassword = new String(oldPassword.getPassword());
			String newpassword = new String(newPassword.getPassword());
			String confirmpassword = new String(confirmPassword.getPassword());
			
			if(oldpassword.equals(customer.getPassword())) {
				
				if(newpassword.equals(confirmpassword)) {
					
					LoginController logincontrol = new LoginController();
					logincontrol.changePassword(customer.getLoggingID(), newpassword);
					JOptionPane.showMessageDialog(null,"the password has been changed!","message",JOptionPane.ERROR_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(null,"the 2 new passwords don't match","message",JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null,"the old password is wrong!","message",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
//			for(int i = 0;i < StartPanel.list.size();i++)
//			{
//				if(UserLoginPanel.userAccount.equals(StartPanel.list.get(i).getLoggingID()))
//				{
//					StringBuilder password = new StringBuilder(StartPanel.list.get(i).getPassword());
//					password.replace(StartPanel.list.get(i).getPassword().length()-1, StartPanel.list.get(i).getPassword().length(), "");
//					if(new String(oldPassword.getPassword()).equals(new String(password.toString())))
//					{
//						if(new String(newPassword.getPassword()).equals(new String(confirmPassword.getPassword())))
//						{
//							sameChar = 0;
//							char[] mimaChar = new String(newPassword.getPassword()).toCharArray();
//							for(int j = 0;j < new String(newPassword.getPassword()).length();j++)
//							{
//								samePassword.setVisible(false);
//								if(mimaChar[j] == mimaChar[0])
//									sameChar++;
//							}
//							System.out.println(sameChar);
//							if(new String(newPassword.getPassword()).equals(new String(oldPassword.getPassword())) || new String(newPassword.getPassword()).length() != 6 || sameChar % 6 == 0)
//							{
//								JOptionPane.showMessageDialog(null,"New password should not be same as the old and must be 6 digits!","ERROR",JOptionPane.ERROR_MESSAGE);
//							}
//							else
//							{
//								dispose();
//								JOptionPane.showMessageDialog(null,"Success!","message",JOptionPane.INFORMATION_MESSAGE);
//								StartPanel.list.get(i).setPassword(new String(newPassword.getPassword())+"0");
//							}
//						}
//						else
//						{
//							samePassword.setVisible(true);
//						}
//					}
//					else
//					{
//						JOptionPane.showMessageDialog(null,"Wrong password!","ERROR",JOptionPane.ERROR_MESSAGE);
//					}
//					break;
//				}
//			}
//			StartPanel.userFile.saveFile(StartPanel.list);
		}
	}
}