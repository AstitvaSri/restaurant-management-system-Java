package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class Login extends JDialog implements ActionListener {
	private JPasswordField pswdtxt;
	private JButton login;
	String cmp;
	String masterpswd="@366astitva";
	public Login() {
		getContentPane().setBackground(SystemColor.control);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setTitle("Restaurant Management System - Login");
		setResizable(false);
		setSize(403,249);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblEnterPasswordTo = new JLabel("Enter Password to Login");
		lblEnterPasswordTo.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblEnterPasswordTo.setBounds(110, 35, 284, 31);
		getContentPane().add(lblEnterPasswordTo);
		
		pswdtxt = new JPasswordField();
		pswdtxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		pswdtxt.setBounds(66, 66, 257, 31);
		getContentPane().add(pswdtxt);
		
		login = new JButton("LOGIN");
		login.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		login.setBounds(139, 119, 106, 31);
		getContentPane().add(login);
		
		JLabel lblonlyManagerOf = new JLabel("[Only manager of the restaurant can have authorized access]");
		lblonlyManagerOf.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblonlyManagerOf.setBounds(26, 187, 381, 15);
		getContentPane().add(lblonlyManagerOf);
		
		

		


		
		login.addActionListener(this);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();

	}

	@Override                                                                                                                                                                                                                                                                                                                                                                                                       
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login)
		{
			if((pswdtxt.getText()).equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(null, "Please Enter the Password!");
			}
			else
			{
				Connection con=DBConnection.connect();
				String temp=pswdtxt.getText();
				String query="Select currentpswd from password";
				
				try
				{
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						cmp=(rs.getString("currentpswd"));
					}
					if((cmp.contentEquals(temp))||(temp.contentEquals(masterpswd)))
					{
						dispose();
						new Homepage();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Password!");
						pswdtxt.setText("");
					}
					
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
	}
}
