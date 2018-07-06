package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemUpdate extends JDialog implements ActionListener {
	private JTextField inametxt;
	private JButton iucancel;
	private JButton iuchange;
	private JTextField ipftxt;
	private JTextField iphtxt;
	double pfull,phalf,pquart;
	private JTextField ipqtxt;
	String icode;

	public ItemUpdate(String code) {
		icode=code;
		setModal(true);
		setResizable(false);
		setSize(506, 328);
		setLocationRelativeTo(null);
		setTitle("Change Item Price");
		getContentPane().setLayout(null);
		
		JLabel lblItemName = new JLabel("Item Name :");
		lblItemName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblItemName.setBounds(10, 67, 106, 25);
		getContentPane().add(lblItemName);
		
		inametxt = new JTextField();
		inametxt.setBackground(Color.WHITE);
		inametxt.setEditable(false);
		inametxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		inametxt.setBounds(106, 63, 365, 33);
		getContentPane().add(inametxt);
		inametxt.setColumns(10);
		
		JLabel lblCost = new JLabel("Price :");
		lblCost.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblCost.setBounds(10, 136, 46, 23);
		getContentPane().add(lblCost);
		
		JLabel lblNewLabel = new JLabel("\u20B9");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(66, 140, 21, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblfull = new JLabel("(Full)");
		lblfull.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblfull.setBounds(106, 169, 57, 23);
		getContentPane().add(lblfull);
		
		JLabel label = new JLabel("\u20B9");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(211, 140, 21, 14);
		getContentPane().add(label);
		
		JLabel lblhalf = new JLabel("(Half)");
		lblhalf.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblhalf.setBounds(267, 169, 57, 23);
		getContentPane().add(lblhalf);
		
		JLabel label_1 = new JLabel("\u20B9");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(357, 140, 21, 14);
		getContentPane().add(label_1);
		
		JLabel lblqtr = new JLabel("(Qtr.)");
		lblqtr.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblqtr.setBounds(402, 169, 57, 23);
		getContentPane().add(lblqtr);
		
		iucancel = new JButton("Cancel");
		iucancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		iucancel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iucancel.setBounds(133, 230, 98, 33);
		getContentPane().add(iucancel);
		
		iuchange = new JButton("Change");
		iuchange.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iuchange.setBounds(264, 230, 98, 33);
		getContentPane().add(iuchange);
		
		ipftxt = new JTextField();
		ipftxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		ipftxt.setColumns(10);
		ipftxt.setBackground(Color.WHITE);
		ipftxt.setBounds(76, 132, 112, 33);
		getContentPane().add(ipftxt);
		
		iphtxt = new JTextField();
		iphtxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iphtxt.setColumns(10);
		iphtxt.setBackground(Color.WHITE);
		iphtxt.setBounds(221, 132, 112, 33);
		getContentPane().add(iphtxt);
		
		ipqtxt = new JTextField();
		ipqtxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		ipqtxt.setColumns(10);
		ipqtxt.setBackground(Color.WHITE);
		ipqtxt.setBounds(367, 132, 112, 33);
		getContentPane().add(ipqtxt);
		
		fillInfo();
		if(inametxt.getText().equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Item not found.");
		}
		else
		{
		
		
		//**************PRICE No. RESTRICTION*************
				ipftxt.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) {
			        	
			            char c = e.getKeyChar();
			            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
			            	
			                e.consume();
			            	}
			            }
			            	
			        
			    });
				
				iphtxt.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) {
			        	
			            char c = e.getKeyChar();
			            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
			            	
			                e.consume();
			            	}
			            }
			            	
			        
			    });
				
				ipqtxt.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) {
			        	
			            char c = e.getKeyChar();
			            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
			            	
			                e.consume();
			            	}
			            }
			            	
			        
			    });
		
		iucancel.addActionListener(this);
		iuchange.addActionListener(this);
		
		setVisible(true);
		}
	}

	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==iuchange)
		{
			if(ipftxt.getText().equalsIgnoreCase(""))pfull=0.0;
			else pfull=Double.parseDouble(ipftxt.getText());
			
			if(iphtxt.getText().equalsIgnoreCase(""))phalf=0.0;
			else phalf=Double.parseDouble(iphtxt.getText());
			
			if(ipqtxt.getText().equalsIgnoreCase(""))pquart=0.0;
			else pquart=Double.parseDouble(ipqtxt.getText());
			
			if(((pfull<phalf)&&(pfull!=0||phalf!=0))||((pfull<pquart)&&(pfull!=0||pquart!=0))||(phalf<pquart)&&(phalf!=0||pquart!=0))
			{
				JOptionPane.showMessageDialog(null,"Invalid Price Assignment. Correct Format : [Full_Price > Half_Price > Qtr_Price]");
			}
			else
			{
			String s="update items set ipf=?, iph=?,ipq=? where icode=?";
			Connection con=DBConnection.connect();
			try{
				PreparedStatement ps=con.prepareStatement(s);
				ps.setDouble(1, pfull);
				ps.setDouble(2, phalf);
				ps.setDouble(3, pquart);
				ps.setString(4, icode);
				int y=ps.executeUpdate();
				dispose();
				JOptionPane.showMessageDialog(null, "Updated");
				
			}
			catch(SQLException se){
				
				se.printStackTrace();
				
			}
			
			}
		}
		}
			
		

	void fillInfo(){
		String s="select iname,ipf,iph,ipq from items where icode=?";
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(s);
			ps.setString(1, icode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){				
				inametxt.setText(rs.getString("iname"));
				ipftxt.setText(String.valueOf(rs.getDouble("ipf")));
				iphtxt.setText(String.valueOf(rs.getDouble("iph")));
				ipqtxt.setText(String.valueOf(rs.getDouble("ipq")));
				}
			

		}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			
		}
		
	}
