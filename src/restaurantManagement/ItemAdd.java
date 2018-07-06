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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ItemAdd extends JDialog implements ActionListener {
	private JTextField iname;
	private JComboBox itype;
	private JTextField icode;
	private JButton iacancel;
	private JButton iadone;
	private JRadioButton rVeg;
	private JRadioButton rNonveg;
	private JTextField ipf;
	private JTextField iph;
	private JTextField ipq;
	public ArrayList <String> types = new ArrayList<String>();
	public String strtyp;
	double pfull,phalf,pquart;
	private JButton ant;
	public ItemAdd() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setResizable(false);
		setTitle("Add New Item");
		setSize(515,411);
		setUndecorated(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblItem = new JLabel("Item Name :");
		lblItem.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblItem.setBounds(10, 37, 98, 23);
		getContentPane().add(lblItem);
		
		iname = new JTextField();
		iname.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iname.setBounds(125, 33, 355, 33);
		getContentPane().add(iname);
		iname.setColumns(10);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblType.setBounds(10, 161, 46, 23);
		getContentPane().add(lblType);
		
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
				types.add(assigntypes);
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		
		
		
		//****************************
		
		itype = new JComboBox();
		itype.setBackground(Color.WHITE);
		itype.setForeground(Color.BLACK);
		itype.setModel(new DefaultComboBoxModel(types.toArray(new String[types.size()])));
		itype.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		itype.setBounds(70, 157, 205, 33);
		getContentPane().add(itype);
		
		rVeg = new JRadioButton("Veg");
		rVeg.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		rVeg.setBounds(69, 200, 109, 23);
		getContentPane().add(rVeg);
		
		rNonveg = new JRadioButton("Non-veg");
		rNonveg.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		rNonveg.setBounds(182, 200, 109, 23);
		getContentPane().add(rNonveg);
		
		ButtonGroup bg=new ButtonGroup(); //Used to make sure that either male or female is selected at once
		bg.add(rVeg);
		bg.add(rNonveg);
		
		JLabel lblCost = new JLabel("Price :");
		lblCost.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblCost.setBounds(10, 251, 46, 23);
		getContentPane().add(lblCost);
		
		JLabel lblNewLabel = new JLabel("\u20B9");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(87, 255, 21, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblfull = new JLabel("(Full)");
		lblfull.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblfull.setBounds(132, 278, 57, 23);
		getContentPane().add(lblfull);
		
		JLabel label = new JLabel("\u20B9");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(231, 255, 21, 14);
		getContentPane().add(label);
		
		JLabel lblhalf = new JLabel("(Half)");
		lblhalf.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblhalf.setBounds(275, 278, 57, 23);
		getContentPane().add(lblhalf);
		
		JLabel label_1 = new JLabel("\u20B9");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(368, 255, 21, 14);
		getContentPane().add(label_1);
		
		JLabel lblqtr = new JLabel("(Qtr.)");
		lblqtr.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblqtr.setBounds(416, 278, 57, 23);
		getContentPane().add(lblqtr);
		
		iacancel = new JButton("Cancel");
		iacancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		iacancel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iacancel.setBounds(148, 323, 89, 33);
		getContentPane().add(iacancel);
		
		iadone = new JButton("Done");
		iadone.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iadone.setBounds(268, 323, 89, 33);
		getContentPane().add(iadone);
		
		JLabel lblItemCode = new JLabel("Item Code :");
		lblItemCode.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblItemCode.setBounds(10, 98, 98, 23);
		getContentPane().add(lblItemCode);
		
		icode = new JTextField();
		icode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		icode.setColumns(10);
		icode.setBounds(118, 94, 123, 33);
		getContentPane().add(icode);
		
		ipf = new JTextField();
		ipf.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		ipf.setColumns(10);
		ipf.setBounds(101, 247, 109, 33);
		getContentPane().add(ipf);
		
		iph = new JTextField();
		iph.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		iph.setColumns(10);
		iph.setBounds(244, 247, 109, 33);
		getContentPane().add(iph);
		
		ipq = new JTextField();
		ipq.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		ipq.setColumns(10);
		ipq.setBounds(378, 247, 109, 33);
		getContentPane().add(ipq);
		
		//**************PRICE No. RESTRICTION*************
		ipf.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		
		iph.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		
		ipq.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		//************************************************
		
		ant = new JButton("Add New Type");
		ant.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		ant.setBounds(295, 158, 134, 33);
		getContentPane().add(ant);
		setModal(true);
		
		iacancel.addActionListener(this);
		iadone.addActionListener(this);
		ant.addActionListener(this);
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	
	
	

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob=e.getSource();
		if(ob==ant)
		{
			dispose();
			new AddType(types);
			
		}
		if(ob==iadone){
			String checktype=(String)itype.getSelectedItem();
			String slct="(Select)";
			if((iname.getText()==null)||((rVeg.isSelected()==false)&&(rNonveg.isSelected()==false))||(icode.getText()==null)||(checktype.equalsIgnoreCase(slct))||(ipf.getText()==null)||(iph.getText()==null)||(ipq.getText()==null))
			{
				JOptionPane.showMessageDialog(null,"Some fields are empty!");
			}
			else
			{
				if(ipf.getText().equalsIgnoreCase(""))pfull=0.0;
				else pfull=Double.parseDouble(ipf.getText());
				
				if(iph.getText().equalsIgnoreCase(""))phalf=0.0;
				else phalf=Double.parseDouble(iph.getText());
				
				if(ipq.getText().equalsIgnoreCase(""))pquart=0.0;
				else pquart=Double.parseDouble(ipq.getText());
				
			if(((pfull<phalf)&&(pfull!=0))||((pfull<pquart)&&(pfull!=0)||(phalf<pquart)&&(phalf!=0)))
			{
				JOptionPane.showMessageDialog(null,"Invalid Price Assignment. Correct Format : [Full_Price > Half_Price > Qtr_Price]");
			}
			else
			{
			String name=iname.getText();
			
			String ivn=null;
			if(rVeg.isSelected())
				ivn="VEG";
			else
				ivn="NONVEG";
			
			String code=icode.getText();
			String type=itype.getSelectedItem().toString();
			
			
			
			String s="Insert into items(icode,iname,itype,ipf,iph,ipq,ivn)values(?,?,?,?,?,?,?)";
			Connection cn=DBConnection.connect();
			
			try
			{
				PreparedStatement ps=cn.prepareStatement(s);
				ps.setString(1, code);
				ps.setString(2, name);
				ps.setString(3, type);
				ps.setDouble(4,pfull);
				ps.setDouble(5,phalf);
				ps.setDouble(6,pquart);
				ps.setString(7,ivn);
				ps.executeUpdate();
				dispose();
				JOptionPane.showMessageDialog(null,"Item Added");
				
				
				
			}
			catch(SQLException se)
					{
				se.printStackTrace();
				JOptionPane.showMessageDialog(null,"Duplicate Entry!");
					}
			}
			}
			
		}
			
	}
	}

