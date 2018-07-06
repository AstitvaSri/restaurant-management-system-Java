package restaurantManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateVAT extends JDialog implements ActionListener{
	private JTextField currentvat;
	private JTextField newvat;
	private JButton update;
	private JButton cancel;
	String taxnew;

	public UpdateVAT() {
		setResizable(false);
		getContentPane().setLayout(null);
		setModal(true);
		setSize(364,255);
		setLocationRelativeTo(null);
		
		
		JLabel lblCurrentVat = new JLabel("Current VAT");
		lblCurrentVat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCurrentVat.setBounds(70, 44, 93, 20);
		getContentPane().add(lblCurrentVat);
		
		currentvat = new JTextField();
		currentvat.setBackground(Color.WHITE);
		currentvat.setEditable(false);
		currentvat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		currentvat.setBounds(172, 34, 46, 37);
		getContentPane().add(currentvat);
		currentvat.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		label.setBounds(225, 44, 26, 20);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		label_1.setBounds(225, 102, 26, 20);
		getContentPane().add(label_1);
		
		newvat = new JTextField();
		newvat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		newvat.setColumns(10);
		newvat.setBounds(172, 92, 46, 37);
		getContentPane().add(newvat);
		
		JLabel lblNewVat = new JLabel("New VAT");
		lblNewVat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblNewVat.setBounds(93, 103, 93, 20);
		getContentPane().add(lblNewVat);
		
		update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		update.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		update.setBounds(70, 164, 102, 29);
		getContentPane().add(update);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cancel.setBounds(182, 164, 102, 29);
		getContentPane().add(cancel);
		
		update.addActionListener(this);
		cancel.addActionListener(this);
		
		fillInfo();
		newvat.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		
		setVisible(true);
		
	}
	public static void main(String args[]){}
	
	void fillInfo()
	{
		String query="Select tax from extras;";
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				currentvat.setText(String.valueOf(rs.getDouble("tax")));			
			}
		}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==update)
		{
			
			Connection cn=DBConnection.connect();
			taxnew=(String)newvat.getText();
			String current=(String)currentvat.getText();
			double newTax=Double.parseDouble(taxnew);
			double old=Double.parseDouble(current);
			if(taxnew.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else 
			{
				if(newTax<0.0 || newTax>100.0)
				{
				JOptionPane.showMessageDialog(null, "Please enter a value between 0 - 100.");
				}
				else if(newTax==old)
				{
				dispose();
				JOptionPane.showMessageDialog(null, "No changes were made.");
				}
				else
				{
				newTax=Double.parseDouble(taxnew);
				String updt="Update extras set tax=?";
				try
				{
					PreparedStatement ps=cn.prepareStatement(updt);
					ps.setDouble(1,newTax);
					ps.executeUpdate();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "VAT Updated!");
			}
			}
		}
		else if(e.getSource()==cancel)
		{
			dispose();
		}
		
	}
}
