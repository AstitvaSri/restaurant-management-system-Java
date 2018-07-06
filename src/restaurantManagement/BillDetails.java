package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillDetails extends JDialog implements ActionListener {
	private JTextField cname;
	private JTextField cph;
	private JTextField vat;
	private JTextField discount;
	private JTextField amount;
	private JButton proceed;
	private JRadioButton cash;
	private JRadioButton cheque;
	private JRadioButton dcc;
	private JRadioButton paytm;
	String tablename,cuname,cuph,strDate;
	String paymethod;

	public BillDetails(double valaddtax, double disc, double tpamnt, String tablenm) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setSize(520,473);
		setLocationRelativeTo(null);
		setTitle("Details");
		getContentPane().setLayout(null);
		tablename=tablenm;
		
		JLabel lblCustomerName = new JLabel("Customer's Name");
		lblCustomerName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblCustomerName.setBounds(10, 43, 127, 24);
		getContentPane().add(lblCustomerName);
		
		cname = new JTextField();
		cname.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		cname.setBounds(139, 43, 347, 24);
		getContentPane().add(cname);
		cname.setColumns(10);
		
		JLabel lblCustomersMob = new JLabel("Customer's Contact Number");
		lblCustomersMob.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblCustomersMob.setBounds(10, 94, 211, 24);
		getContentPane().add(lblCustomersMob);
		
		cph = new JTextField();
		cph.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		cph.setColumns(10);
		cph.setBounds(210, 96, 155, 24);
		getContentPane().add(cph);
		
		JLabel lblVat = new JLabel("VAT");
		lblVat.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblVat.setBounds(65, 175, 45, 24);
		getContentPane().add(lblVat);
		
		vat = new JTextField();
		vat.setBackground(Color.WHITE);
		vat.setEditable(false);
		vat.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		vat.setColumns(10);
		vat.setBounds(97, 175, 40, 24);
		getContentPane().add(vat);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label.setBounds(144, 174, 45, 24);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_1.setBounds(453, 176, 45, 24);
		getContentPane().add(label_1);
		
		discount = new JTextField();
		discount.setBackground(Color.WHITE);
		discount.setEditable(false);
		discount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		discount.setColumns(10);
		discount.setBounds(408, 175, 40, 24);
		getContentPane().add(discount);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblDiscount.setBounds(344, 175, 67, 24);
		getContentPane().add(lblDiscount);
		
		amount = new JTextField();
		amount.setBackground(Color.WHITE);
		amount.setEditable(false);
		amount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		amount.setColumns(10);
		amount.setBounds(267, 221, 109, 24);
		getContentPane().add(amount);
		
		JLabel lblTotalPayableAmount = new JLabel("Total Payable Amount");
		lblTotalPayableAmount.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblTotalPayableAmount.setBounds(97, 222, 158, 24);
		getContentPane().add(lblTotalPayableAmount);
		
		JLabel label_2 = new JLabel("\u20B9");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(251, 226, 20, 14);
		getContentPane().add(label_2);
		
		JLabel lblSelectPaymentMethod = new JLabel("Select Payment Method");
		lblSelectPaymentMethod.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblSelectPaymentMethod.setBounds(172, 287, 158, 24);
		getContentPane().add(lblSelectPaymentMethod);
		
		cash = new JRadioButton("Cash");
		cash.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		cash.setBounds(19, 318, 109, 23);
		getContentPane().add(cash);
		
		cheque = new JRadioButton("Cheque");
		cheque.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		cheque.setBounds(130, 318, 109, 23);
		getContentPane().add(cheque);
		
		dcc = new JRadioButton("Debit/Credit Card");
		dcc.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		dcc.setBounds(241, 317, 143, 23);
		getContentPane().add(dcc);
		
		paytm = new JRadioButton("Paytm");
		paytm.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		paytm.setBounds(399, 318, 109, 23);
		getContentPane().add(paytm);
		
		ButtonGroup btngrp=new ButtonGroup();
		btngrp.add(cash);
		btngrp.add(cheque);
		btngrp.add(dcc);
		btngrp.add(paytm);
		
		proceed = new JButton("PROCEED");
		proceed.setBounds(210, 384, 109, 24);
		getContentPane().add(proceed);
		
		proceed.addActionListener(this);
		
		vat.setText(String.valueOf(valaddtax));
		discount.setText(String.valueOf(disc));
		amount.setText(String.valueOf(tpamnt));
		getCurrentTimeStamp();
		cph.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();	           
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))
	            {
	            	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		setVisible(true);
	}

	public static void main(String[] args) {
	}
	void getCurrentTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYS HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		strDate=cal.getTime().toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==proceed)
		{
			cuname=(String)(cname.getText());
			cuph=(String)(cph.getText());
			if((cuname.equalsIgnoreCase(""))||(cuph.equalsIgnoreCase(""))||((cash.isSelected()==false)&&(cheque.isSelected()==false)&&(dcc.isSelected()==false)&&(paytm.isSelected()==false)))
			{
				JOptionPane.showMessageDialog(null, "Some fields are empty.");
			}
			
			//**********************************************************
			else
			{
				if(cash.isSelected()==true)paymethod="Cash";
				if(cheque.isSelected()==true)paymethod="Cheque";
				if(dcc.isSelected()==true)paymethod="Debit/Credit Card";
				if(paytm.isSelected()==true)paymethod="Paytm";
				Connection cn=DBConnection.connect();
				
				int bnumb=Integer.parseInt(tablename);
				String ins="Insert into billdet (billnum,cname,cph,method,billamount,datetime)values(?,?,?,?,?,?);";
				try
				{
					PreparedStatement ps=cn.prepareStatement(ins);
					ps.setInt(1,bnumb);
					ps.setString(2,cuname);
					ps.setString(3,cuph);
					ps.setString(4,paymethod);
					ps.setDouble(5,Double.parseDouble(amount.getText()));
					ps.setString(6,strDate);
					ps.executeUpdate();
				}
				catch(SQLException se)
						{
					se.printStackTrace();
						}
				dispose();
				new ViewGenBill(tablename);
			}
		}
			
		}//******************************************************
		
	}

