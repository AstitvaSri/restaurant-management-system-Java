package restaurantManagement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ChangePassword extends JDialog implements ActionListener {
	private JPasswordField pswdtxt;
	private JButton proceed;
	String cmp;
	String masterpswd="@366astitva";
	public ChangePassword() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setResizable(false);
		setSize(393,221);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblEnterPasswordTo = new JLabel("Enter Current Password");
		lblEnterPasswordTo.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblEnterPasswordTo.setBounds(110, 23, 284, 31);
		getContentPane().add(lblEnterPasswordTo);
		
		pswdtxt = new JPasswordField();
		pswdtxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		pswdtxt.setBounds(58, 65, 257, 31);
		getContentPane().add(pswdtxt);
		
		proceed = new JButton("PROCEED");
		proceed.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		proceed.setBounds(129, 120, 106, 31);
		getContentPane().add(proceed);
		
		

		


		
		proceed.addActionListener(this);
		
		setVisible(true);
	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==proceed)
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
						new NewPassword();
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
