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

public class UpdateDisc extends JDialog implements ActionListener{
	private JTextField currentdisc;
	private JTextField newdisc;
	private JButton update;
	private JButton cancel;
	String discnew;

	public UpdateDisc() {
		setResizable(false);
		getContentPane().setLayout(null);
		setModal(true);
		setSize(364,255);
		setLocationRelativeTo(null);
		
		
		JLabel lblCurrentVat = new JLabel("Current Discount");
		lblCurrentVat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCurrentVat.setBounds(59, 42, 137, 20);
		getContentPane().add(lblCurrentVat);
		
		currentdisc = new JTextField();
		currentdisc.setBackground(Color.WHITE);
		currentdisc.setEditable(false);
		currentdisc.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		currentdisc.setBounds(190, 34, 46, 37);
		getContentPane().add(currentdisc);
		currentdisc.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		label.setBounds(243, 44, 26, 20);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		label_1.setBounds(243, 102, 26, 20);
		getContentPane().add(label_1);
		
		newdisc = new JTextField();
		newdisc.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		newdisc.setColumns(10);
		newdisc.setBounds(190, 92, 46, 37);
		getContentPane().add(newdisc);
		
		JLabel lblNewVat = new JLabel("New Discount");
		lblNewVat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblNewVat.setBounds(81, 103, 115, 20);
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
		newdisc.addKeyListener(new KeyAdapter() {
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
		String query="Select discnt from extras;";
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				currentdisc.setText(String.valueOf(rs.getDouble("discnt")));			
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
			discnew=(String)newdisc.getText();
			String current=(String)currentdisc.getText();
			double newDisc=Double.parseDouble(discnew);
			double old=Double.parseDouble(current);
			
			if(discnew.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else
			{
				if(newDisc<0.0 || newDisc>100.0)
				{
				JOptionPane.showMessageDialog(null, "Please enter a value between 0 - 100.");
				}
				else if (newDisc==old)
				{
				dispose();
				JOptionPane.showMessageDialog(null, "No changes were made.");
				}
				else
				{
				newDisc=Double.parseDouble(discnew);
				String updt="Update extras set discnt=?";
				try
				{
					PreparedStatement ps=cn.prepareStatement(updt);
					ps.setDouble(1,newDisc);
					ps.executeUpdate();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "Discount Rate Updated!");
				}
			}
		}
		else if(e.getSource()==cancel)
		{
			dispose();
		}
		
	}
}
