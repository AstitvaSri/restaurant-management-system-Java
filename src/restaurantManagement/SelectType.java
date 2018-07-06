package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

public class SelectType extends JFrame implements ActionListener{
	private JButton typev;
	private JComboBox typelist;
	private JButton typec;
	public ArrayList <String> FillTypes = new ArrayList<String>();

	public SelectType() {
		setResizable(false);
		setTitle("Select Type");
		setType(Type.UTILITY);
		setSize(265,174);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		//******FILL TYPES LOGIC********
		
				String st="select itmtyp from types";
				Connection con=DBConnection.connect();
				try
				{
					PreparedStatement ps=con.prepareStatement(st);
					ResultSet rs=ps.executeQuery();
					rs.beforeFirst();
					String assigntypes;
					while(rs.next())
					{
						
						assigntypes=String.valueOf(rs.getString("itmtyp"));
						FillTypes.add(assigntypes);
					}
				}
				catch (SQLException se)
				{
					se.printStackTrace();
				}
				
				
				
				//****************************
		
		typelist = new JComboBox();
		typelist.setBackground(Color.WHITE);
		typelist.setModel(new DefaultComboBoxModel(FillTypes.toArray(new String[FillTypes.size()])));
		typelist.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		typelist.setBounds(51, 26, 186, 33);
		getContentPane().add(typelist);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblType.setBounds(10, 28, 42, 24);
		getContentPane().add(lblType);
		
		typev = new JButton("View");
		typev.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		typev.setBounds(51, 98, 89, 23);
		getContentPane().add(typev);
		
		typec = new JButton("Cacel");
		typec.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		typec.setBounds(148, 98, 89, 23);
		getContentPane().add(typec);
		
		typev.addActionListener(this);
		typec.addActionListener(this);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==typev)
		{
			String type=(String)typelist.getSelectedItem();
			if(type.equalsIgnoreCase("(Select)"))
			{
				JOptionPane.showMessageDialog(null, "Please select an option.");
			}
			else
			{
				String query="Select * from items where itype LIKE '"+type+"';";
				new ViewAllItems(query);
			}
		}
		if(e.getSource()==typec)
		{
			dispose();
		}
		
	}
}
