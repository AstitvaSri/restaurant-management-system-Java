package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Billing extends JDialog implements ActionListener {
	private JTextField itemcode;
	private JTextField itname;
	private JTextField priceqtxt;
	private JTextField pricehtxt;
	private JTextField priceftxt;
	private JButton getdet;
	private JSpinner quantq;
	private JSpinner quantf;
	private JButton a2b;
	private JButton gtb;
	private JButton discard;
	String itmc;
	private JSpinner quanth;
	double Total=0.0;
	String tablename;
	double vat,discount;
	double vatcost,discountcost;

	public int add=0;
	

	public Billing(String billtable) {
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		tablename= new String(billtable);
		setResizable(false);
		setTitle("Billing");
		getContentPane().setLayout(null);
		setSize(490, 588);
		
		JLabel lblEnterItemCode = new JLabel("Enter Item Code :");
		lblEnterItemCode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblEnterItemCode.setBounds(69, 36, 136, 26);
		getContentPane().add(lblEnterItemCode);
		
		itemcode = new JTextField();
		itemcode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		itemcode.setBounds(215, 35, 125, 29);
		getContentPane().add(itemcode);
		itemcode.setColumns(10);
		
		getdet = new JButton("Get Details");
		getdet.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		getdet.setBounds(177, 73, 110, 26);
		getContentPane().add(getdet);
		
		JLabel lblItemName = new JLabel("Item Name : ");
		lblItemName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblItemName.setBounds(10, 155, 93, 26);
		getContentPane().add(lblItemName);
		
		itname = new JTextField();
		itname.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		itname.setBackground(Color.WHITE);
		itname.setEditable(false);
		itname.setBounds(110, 155, 344, 26);
		getContentPane().add(itname);
		itname.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price : ");
		lblPrice.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblPrice.setBounds(10, 206, 57, 26);
		getContentPane().add(lblPrice);
		
		priceqtxt = new JTextField();
		priceqtxt.setBackground(Color.WHITE);
		priceqtxt.setEditable(false);
		priceqtxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		priceqtxt.setColumns(10);
		priceqtxt.setBounds(110, 205, 93, 29);
		getContentPane().add(priceqtxt);
		
		pricehtxt = new JTextField();
		pricehtxt.setBackground(Color.WHITE);
		pricehtxt.setEditable(false);
		pricehtxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		pricehtxt.setColumns(10);
		pricehtxt.setBounds(238, 205, 93, 29);
		getContentPane().add(pricehtxt);
		
		priceftxt = new JTextField();
		priceftxt.setBackground(Color.WHITE);
		priceftxt.setEditable(false);
		priceftxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		priceftxt.setColumns(10);
		priceftxt.setBounds(361, 205, 93, 29);
		getContentPane().add(priceftxt);
		
		JLabel lblAddQuantity = new JLabel("Add \r\nQuantity :");
		lblAddQuantity.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblAddQuantity.setBounds(10, 320, 113, 43);
		getContentPane().add(lblAddQuantity);
		
		JLabel lblquart = new JLabel("(Quart.)");
		lblquart.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblquart.setBounds(132, 247, 57, 26);
		getContentPane().add(lblquart);
		
		JLabel lblHalf = new JLabel("(Half)");
		lblHalf.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblHalf.setBounds(265, 245, 57, 26);
		getContentPane().add(lblHalf);
		
		JLabel lblfull = new JLabel("(Full)");
		lblfull.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblfull.setBounds(397, 245, 57, 26);
		getContentPane().add(lblfull);
		
		quantq = new JSpinner();
		quantq.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		quantq.setBounds(133, 333, 56, 20);
		getContentPane().add(quantq);
		
		quanth = new JSpinner();
		quanth.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		quanth.setBounds(265, 331, 56, 20);
		getContentPane().add(quanth);
		
		quantf = new JSpinner();
		quantf.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		quantf.setBounds(386, 331, 56, 20);
		getContentPane().add(quantf);
		
		JLabel label = new JLabel("(Quart.)");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		label.setBounds(132, 367, 57, 26);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("(Half)");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		label_1.setBounds(274, 364, 57, 26);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("(Full)");
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		label_2.setBounds(397, 365, 57, 26);
		getContentPane().add(label_2);
		
		a2b = new JButton("Add to Bill");
		a2b.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		a2b.setBounds(45, 471, 113, 34);
		getContentPane().add(a2b);
		
		gtb = new JButton("Get the Bill");
		gtb.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		gtb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		gtb.setBounds(180, 472, 107, 34);
		getContentPane().add(gtb);
		
		discard = new JButton("Discard");
		discard.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		discard.setBounds(307, 471, 107, 34);
		getContentPane().add(discard);
		setLocationRelativeTo(null);
		
		getdet.addActionListener(this);
		a2b.addActionListener(this);
		gtb.addActionListener(this);
		discard.addActionListener(this);
		setVisible(true);
		
	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con=DBConnection.connect();
		Connection cn=DBConnection.connect();
		
		if(e.getSource()==getdet)
		{ 
			String inputcode=(String)itemcode.getText();			
			if(inputcode.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else
			{
			try
			{

				itmc=itemcode.getText();
				String s="select iname,ipf,iph,ipq from items where icode=?";
				PreparedStatement ps=con.prepareStatement(s);
				ps.setString(1, itmc);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					itname.setText(rs.getString("iname"));
					priceftxt.setText(String.valueOf(rs.getDouble("ipf")));
					pricehtxt.setText(String.valueOf(rs.getDouble("iph")));
					priceqtxt.setText(String.valueOf(rs.getDouble("ipq")));
					
				}
				}
				catch (SQLException se)
				{
					se.printStackTrace();
				}
			}
			}
		if(e.getSource()==a2b)
		{
			int qq,qh,qf;
			qq=(Integer)quantq.getValue();
			qh=(Integer)quanth.getValue();
			qf=(Integer)quantf.getValue();
				if(((qq==0&&qh==0&&qf==0)||(qq<0||qh<0||qf<0))||((qq==0||Double.parseDouble(priceqtxt.getText())==0.0)&&(qh==0||Double.parseDouble(pricehtxt.getText())==0.0)&&(qf==0||Double.parseDouble(priceftxt.getText())==0.0)))
				{

					JOptionPane.showMessageDialog(null, "Invalid Entries.");
					quantq.setValue(0);
					quanth.setValue(0);
					quantf.setValue(0);
				}
				else
				{
						
			String s="Create table `"+tablename+"`(Item varchar(40),Price double);";
			//add=1;
			
			try
			{
				PreparedStatement ps=cn.prepareStatement(s);
				ps.executeUpdate();
				
				
			}
			catch(SQLException se)
					{
				se.printStackTrace();
					}
			
			
			
			
			
			
			
			
			
				
					
					double rateq=Double.parseDouble(priceqtxt.getText());
					double rateh=Double.parseDouble(pricehtxt.getText());
					double ratef=Double.parseDouble(priceftxt.getText());
					double sumq=qq*rateq;
					double sumh=qh*rateh;
					double sumf=qf*ratef;
					if(sumq!=0)
					{
						String billstr=itname.getText() + " (" + String.valueOf(qq) + " Qtr.)";
						Total=Total+sumq;

						//*******TABLE OPERATION****************
						String sq="Insert into `"+tablename+"` values(?,?);";
						try
						{
							PreparedStatement ps=cn.prepareStatement(sq);
							ps.setString(1, billstr);
							ps.setDouble(2, sumq);
							ps.executeUpdate();
							
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						//***************************************
					}
					if(sumh!=0)
					{
						String billstr=itname.getText() + " (" + String.valueOf(qh) + " Half)";
						Total=Total+sumh;
						
						//*******TABLE OPERATION****************
						String sh="Insert into `"+tablename+"` values(?,?);";
						try
						{
							PreparedStatement ps=cn.prepareStatement(sh);
							ps.setString(1, billstr);
							ps.setDouble(2, sumh);
							ps.executeUpdate();
							
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						//***************************************
						
					}
					if(sumf!=0)
					{
						String billstr=itname.getText() + " (" + String.valueOf(qf) + " Full)";
						Total=Total+sumf;
						
						//*******TABLE OPERATION****************
						String sf="Insert into `"+tablename+"` values(?,?);";
						try
						{
							PreparedStatement ps=cn.prepareStatement(sf);
							ps.setString(1, billstr);
							ps.setDouble(2, sumf);
							ps.executeUpdate();
							
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						//***************************************
						
					}
					JOptionPane.showMessageDialog(null, "Item(s) added to bill.");
					add=1;
					itemcode.setText("");
					itname.setText("");
					priceqtxt.setText("0.0");
					pricehtxt.setText("0.0");
					priceftxt.setText("0.0");
					quantq.setValue(0);
					quanth.setValue(0);
					quantf.setValue(0);
					
					
					
				}
			}
			
		

		if(e.getSource()==gtb)
		{
			if(Total==0)
			{
			JOptionPane.showMessageDialog(null, "No item is added.");	
			}
			else
			{	
				int choose=JOptionPane.showConfirmDialog(null, "Once you proceed, you can't discard. Are you sure?",null,JOptionPane.YES_NO_OPTION);
				
					if(choose==0){
						dispose();
						
						
						
						
						
						
						
						//*******TABLE OPERATION-TOTAL****************
						
						String s="select tax,discnt from extras;";
						try
						{
							PreparedStatement ps=con.prepareStatement(s);
							ResultSet rs=ps.executeQuery();
							while(rs.next()){
								vat=(rs.getDouble("tax"));
								discount=(rs.getDouble("discnt"));
								
							}
						}
							catch (SQLException se)
							{
								se.printStackTrace();
							}
						
						
						//**************VAT INSERTION***********
						vatcost=(vat/100)*Total;
						
						String st="Insert into `"+tablename+"` values(?,?);";
						try
						{
							PreparedStatement ps=cn.prepareStatement(st);
							ps.setString(1, "VAT");
							ps.setDouble(2, vatcost);
							ps.executeUpdate();
							
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						//**************DISCOUNT INSERTION***********
						Total= Total + vatcost;
						discountcost=(discount/100)*Total;

						String st1="Insert into `"+tablename+"` values(?,?);";
						try
						{
							PreparedStatement ps=cn.prepareStatement(st1);
							ps.setString(1, "DISCOUNT");
							ps.setDouble(2, discountcost);
							ps.executeUpdate();
							
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						
						
						//***************TOTAL INSERTION***********************

						Total= Total - discountcost;
						
						
						String st2="Insert into `"+tablename+"` values(?,?);";
						try
						{
							PreparedStatement ps=cn.prepareStatement(st2);
							ps.setString(1, "TOTAL");
							ps.setDouble(2, Total);
							ps.executeUpdate();
							
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						//***************************************
						
						
						
						
						
						
						//*****UPDATE BILLNUMBER************
						int bno=Integer.parseInt(tablename);
						bno++;
						String snew="Update billnumber set billnum=?";
						try
						{
							PreparedStatement ps=cn.prepareStatement(snew);
							ps.setInt(1, bno);
							ps.executeUpdate();
							
						}
						catch(SQLException se)
								{
							se.printStackTrace();
								}
						//**********************************
						new BillDetails(vat,discount,Total,tablename);
						
					}//choose closed
					
				}
				
				
			}//changes***************
			
		
		
		if(e.getSource()==discard)
		{
			dispose();
			
			//*******TABLE OPERATION****************
			
			if(add==1){
			String str="Drop table `"+tablename+"`;";
			String strdel="Update billnumber set billnum = ?";

			try
			{
				PreparedStatement ps=cn.prepareStatement(str);
				ps.executeUpdate();
				PreparedStatement ps2=cn.prepareStatement(strdel);
				ps2.setInt(1, (Integer.parseInt(tablename)));
				ps2.executeUpdate();
				JOptionPane.showMessageDialog(null, "Bill Discarded");
				
				
			}
			catch(SQLException se)
					{
				se.printStackTrace();
					}
			}
			//***************************************
			

		}
		}
}
	

		
		




