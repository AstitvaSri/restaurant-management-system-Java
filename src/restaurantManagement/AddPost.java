package restaurantManagement;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddPost extends JDialog implements ActionListener {
	private JTextField newpost;
	private JButton addnp;
	private JButton cancel;
	ArrayList<String> tempposts;
	public AddPost(ArrayList<String> posts){
		setResizable(false);
		tempposts=posts;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New Post");
		getContentPane().setLayout(null);

		
		newpost = new JTextField();
		newpost.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		newpost.setBounds(36, 54, 212, 32);
		getContentPane().add(newpost);
		newpost.setColumns(10);
		
		JLabel lblEnterTheNew = new JLabel("Enter the new post");
		lblEnterTheNew.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEnterTheNew.setBounds(88, 29, 129, 14);
		getContentPane().add(lblEnterTheNew);
		
		addnp = new JButton("Add");
		addnp.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		addnp.setBounds(36, 112, 89, 23);
		getContentPane().add(addnp);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cancel.setBounds(159, 112, 89, 23);
		getContentPane().add(cancel);
		
		
		
		addnp.addActionListener(this);
		cancel.addActionListener(this);
		setSize(306,215);
		setModal(true);
		setLocation(540, 290);
		setVisible(true);
	}
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addnp)
		{
			if(newpost.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Empty field!");
			}
			else
			{
				String tempstr=(newpost.getText());
				String s="Insert into posts(post) values('"+tempstr+"')";
				Connection con=DBConnection.connect();
				try{
					PreparedStatement ps=con.prepareStatement(s);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Added");
					dispose();
					new AddEmployee();
					}
				catch(SQLException se){
					
					JOptionPane.showMessageDialog(null, "Type Already Exists!");
					newpost.setText("");
					se.printStackTrace();
					
					}
				}
			}
		
			
	
		if(e.getSource()==cancel)
		{
			dispose();
			new AddEmployee();
		}
		
		
	}
}







