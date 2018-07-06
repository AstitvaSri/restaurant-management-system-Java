package restaurantManagement;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.text.Position;

public class AddType extends JDialog implements ActionListener {
	private JTextField newtype;
	private JButton addnt;
	private JButton cancel;
	ArrayList<String> temptypes;
	public AddType(ArrayList<String> types){
		setResizable(false);
		temptypes=types;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New Type");
		getContentPane().setLayout(null);

		
		newtype = new JTextField();
		newtype.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		newtype.setBounds(36, 54, 212, 32);
		getContentPane().add(newtype);
		newtype.setColumns(10);
		
		JLabel lblEnterTheNew = new JLabel("Enter the new type");
		lblEnterTheNew.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEnterTheNew.setBounds(88, 29, 129, 14);
		getContentPane().add(lblEnterTheNew);
		
		addnt = new JButton("Add");
		addnt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		addnt.setBounds(36, 112, 89, 23);
		getContentPane().add(addnt);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cancel.setBounds(159, 112, 89, 23);
		getContentPane().add(cancel);
		
		
		
		addnt.addActionListener(this);
		cancel.addActionListener(this);
		setSize(306,215);
		setModal(true);
		setLocation(540, 290);
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
	}

	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addnt)
		{
			if(newtype.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Empty field!");
			}
			else
			{
				String tempstr=(newtype.getText());
				String s="Insert into types(itmtyp) values('"+tempstr+"')";
				Connection con=DBConnection.connect();
				try{
					PreparedStatement ps=con.prepareStatement(s);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Added");
					dispose();
					new ItemAdd();
					}
				catch(SQLException se){
					
					JOptionPane.showMessageDialog(null, "Type Already Exists!");
					newtype.setText("");
					se.printStackTrace();
					
					}
				}
			}
		
			
	
		if(e.getSource()==cancel)
		{
			dispose();
			new ItemAdd();
		}
		
		
	}
}







