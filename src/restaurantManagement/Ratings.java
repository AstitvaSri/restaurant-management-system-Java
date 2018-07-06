package restaurantManagement;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Ratings extends JDialog implements ActionListener {
	private JTextField itmname;
	private JTextField avgr;
	private JButton rdone;
	private JButton rclose;
	public JTextField nr;
	String icode;

	public Ratings(String code) {
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setEnabled(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		icode=code;
		
		setModal(true);
		setResizable(false);
		setSize(526, 340);
		setLocationRelativeTo(null);
		setTitle("Update Average Rating");
		getContentPane().setLayout(null);
		
				
		
		rclose = new JButton("Close");
		rclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		rdone = new JButton("Done");
		rdone.setBounds(149, 227, 89, 33);
		rdone.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		getContentPane().add(rdone);
		rdone.addActionListener(this);
		rclose.setBounds(309, 227, 89, 33);
		rclose.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		getContentPane().add(rclose);
		rclose.addActionListener(this);
		
		
		
		JLabel lbloutOf = new JLabel("(out of 10)");
		lbloutOf.setBounds(335, 169, 139, 25);
		lbloutOf.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		getContentPane().add(lbloutOf);
		
		JLabel lblYourRating = new JLabel("New Rating :");
		lblYourRating.setBounds(136, 168, 93, 25);
		lblYourRating.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		getContentPane().add(lblYourRating);
		
		
		
		JLabel lblItemName = new JLabel("Item Name :");
		lblItemName.setBounds(10, 67, 89, 20);
		lblItemName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		getContentPane().add(lblItemName);
		
		nr = new JTextField();
		nr.setBounds(258, 166, 48, 33);
		nr.setColumns(10);
		nr.setBackground(Color.WHITE);
		getContentPane().add(nr);
		
		JLabel lblAverageRating = new JLabel("Average Rating :");
		lblAverageRating.setBounds(106, 125, 124, 25);
		lblAverageRating.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		getContentPane().add(lblAverageRating);
		
		JLabel label_1 = new JLabel("(out of 10)");
		label_1.setBounds(335, 126, 139, 25);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		getContentPane().add(label_1);
		
		avgr = new JTextField();
		avgr.setBounds(258, 121, 43, 33);
		avgr.setEditable(false);
		avgr.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		avgr.setColumns(10);
		avgr.setBackground(Color.WHITE);
		getContentPane().add(avgr);
		
		itmname = new JTextField();
		itmname.setBounds(126, 63, 365, 33);
		itmname.setEditable(false);
		itmname.setBackground(Color.WHITE);
		itmname.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		getContentPane().add(itmname);
		itmname.setColumns(10);
		
		fillInfo();
		if(itmname.getText().equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Item not found.");
		}
		else
		{
			nr.addKeyListener(new KeyAdapter() {
		        public void keyTyped(KeyEvent e) {
		        	
		            char c = e.getKeyChar();
		            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
		            	
		                e.consume();
		            	}
		            }
		            	
		        
		    });
		
		

		setVisible(true);
		}
	}
	public static void main(String args[])
	{
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		if(ob==rdone)
		{
			if((nr.getText()==null)||((Double.parseDouble(nr.getText())<0)||((Double.parseDouble(nr.getText()))>10)))
			{
				JOptionPane.showMessageDialog(null, "Please enter a value between 0 - 10.");
			}
			else
			{
				double avg;
				if(avgr.getText()==null)
				{
					avg=0.0;
				}
				else
				{
				avg=Double.parseDouble(avgr.getText());
				}
				double newrt=Double.parseDouble(nr.getText());
				if((avg==0)&&(newrt<10))
				{
				newrt=newrt*10;
				int temp=(int)newrt;
				newrt=(double)temp/10;
				}
				else
				{
					newrt=(newrt+avg)/(double)2;
					newrt=newrt*10;
					int temp=(int)newrt;
					newrt=(double)temp/10;
				}
			
			
		
			
			String s="update items set avgrtng=? where icode=?";
			Connection con=DBConnection.connect();
			try{
				PreparedStatement ps=con.prepareStatement(s);
				ps.setDouble(1,newrt );
				ps.setString(2, icode);
				int y=ps.executeUpdate();
				String str=String.valueOf(newrt);
				dispose();
				JOptionPane.showMessageDialog(null, "Average Rating is updated to "+str);
				
				
				
				
			}
			catch(SQLException se){
				se.printStackTrace();
				
			}
			}
		}
	}
		
	
	void fillInfo(){
		String s="select iname,avgrtng from items where icode=?";
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(s);
			ps.setString(1, icode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				itmname.setText(rs.getString("iname"));
				avgr.setText(String.valueOf(rs.getDouble("avgrtng")));
				
			}
		}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			
		}
	}


