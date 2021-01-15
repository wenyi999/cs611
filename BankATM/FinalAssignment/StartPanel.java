//start panel
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class StartPanel extends JFrame 
{
	private JPanel contentPane;
	public static List<User> info;
	public static List<User> list = new ArrayList<User>();

	// Main method to start
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					StartPanel frame = new StartPanel();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Login Panel
	 */
	public StartPanel() 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 481, 347);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome to our bank!"); // welcome label
		label.setFont(new Font("", Font.BOLD, 25));
		JButton managerButton = new JButton("Manager"); // manager login button
		managerButton.setBackground(new Color(255, 204, 204));
		managerButton.setForeground(Color.BLACK);
		managerButton.setFont(new Font("", Font.PLAIN, 18));
		managerButton.setBorderPainted(true);
		managerButton.setFocusPainted(true);
		
		//manager login listener
		managerButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				ManagerLoginPanel mLogin = new ManagerLoginPanel();
				mLogin.setVisible(true);
			}
		});
		
		JButton userButton = new JButton("User"); //user login button
		userButton.setBackground(new Color(204, 204, 255));
		userButton.setFont(new Font("", Font.PLAIN, 18));
		userButton.setBorderPainted(true);
		userButton.setFocusPainted(true);
		
		//user login listener
		userButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				UserLoginPanel uLogin = new UserLoginPanel();
				dispose();
				uLogin.setVisible(true);
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
				System.exit(0);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(377, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addGap(25))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(userButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(managerButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
					.addGap(186))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(100, Short.MAX_VALUE)
						.addComponent(label)
						.addGap(100))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(label)
					.addGap(70)
					.addComponent(managerButton)
					.addGap(30)
					.addComponent(userButton)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addGap(46))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
