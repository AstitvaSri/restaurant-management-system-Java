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

public class NewPassword extends JDialog implements ActionListener {
	private JPasswordField newpswd;
	private JButton change;
	String cmp;
	private JPasswordField cnfmpswd;
	public NewPassword() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setResizable(false);
		setSize(385,274);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblEnterPasswordTo = new JLabel("Enter New Password");
		lblEnterPasswordTo.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblEnterPasswordTo.setBounds(115, 10, 213, 31);
		getContentPane().add(lblEnterPasswordTo);
		
		newpswd = new JPasswordField();
		newpswd.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		newpswd.setBounds(58, 40, 257, 31);
		getContentPane().add(newpswd);
		
		change = new JButton("CHANGE");
		change.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		change.setBounds(126, 191, 106, 31);
		getContentPane().add(change);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(120, 95, 213, 31);
		getContentPane().add(lblConfirmPassword);
		
		cnfmpswd = new JPasswordField();
		cnfmpswd.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cnfmpswd.setBounds(58, 127, 257, 31);
		getContentPane().add(cnfmpswd);
		
		

		


		
		change.addActionListener(this);
		
		setVisible(true);
	}

	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==change)
		{
			if(((newpswd.getText()).equalsIgnoreCase(""))||((cnfmpswd.getText()).equalsIgnoreCase("")))
			{
				JOptionPane.showMessageDialog(null, "Empty Field!");
			}
			else if(newpswd.getText().length()<6)
			{
				JOptionPane.showMessageDialog(null, "Password must contain atleast 6 characters!");
				cnfmpswd.setText("");
			}
			else if(!(newpswd.getText()).contentEquals((cnfmpswd.getText())))
			{
				JOptionPane.showMessageDialog(null, "Password doesn't match!");
				cnfmpswd.setText("");
			}
			else
			{
				Connection con=DBConnection.connect();
				String temp=newpswd.getText();
				String query="update password set currentpswd='"+temp+"';";
				
				try
				{
					PreparedStatement ps=con.prepareStatement(query);
					ps.executeUpdate();		
					dispose();
					JOptionPane.showMessageDialog(null, "Password changed!");
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
	}
}
