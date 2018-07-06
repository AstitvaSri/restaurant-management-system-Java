package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.DateTimeSyntax;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;

import javax.swing.JMenuItem;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Dimension;

import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;

public class Homepage extends JFrame implements ActionListener{
	private JMenuItem viewbill;
	private JMenuItem newbill;
	private JMenuItem vai;
	private JMenuItem idel;
	private JMenuItem cir;
	private JMenuItem cip;
	private JMenuItem inew;
	int bno;
	private JLabel lab;
	private JMenu mnBillling;
	private JLabel lblManagement;
	private JLabel lblSystem;
	private JMenuItem vic;
	private JMenuItem vrat;
	private JMenuItem vit;
	private JMenuItem vin;
	private JMenuItem vvn;
	private JMenu mnExtras;
	private JMenuItem upvat;
	private JMenuItem updis;
	private JButton reload;
	private JButton exit;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_1;
	private JMenuItem cdc;
	private JMenuItem cdn;
	private JMenuItem cdb;
	private JMenu mnNewMenu_3;
	private JMenuItem saleamnt;
	private JMenuItem tdysale;
	private JMenuItem chpswd;
	private JMenuItem aemp;
	private JMenuItem remp;
	private JMenuItem uemp;
	private JMenu mnViewEmployees;
	private JMenuItem viewempall;
	private JMenuItem viewid;
	private JMenuItem viewname;
	private JMenuItem viewcntct;
	public Homepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Homepage.class.getResource("/restaurantManagement/images/homepage.png")));
		
		setExtendedState(MAXIMIZED_BOTH);
		
		setSize(new Dimension(1378, 720));
		setTitle("Restaurant Management System");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("ITEMS");
		mnNewMenu.setBackground(Color.WHITE);
		mnNewMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mnNewMenu.setFont(new Font("Century Gothic", Font.BOLD, 18));
		menuBar.add(mnNewMenu);
		
		inew = new JMenuItem("Add New Item");
		inew.setSelectedIcon(null);
		inew.setForeground(Color.BLACK);
		inew.setBackground(SystemColor.menu);
		inew.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu.add(inew);
		
		JMenu mnUpdateItem = new JMenu("Update Item");
		mnUpdateItem.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu.add(mnUpdateItem);
		
		cip = new JMenuItem("Change Price");
		cip.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnUpdateItem.add(cip);
		
		cir = new JMenuItem("Change Rating");
		cir.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnUpdateItem.add(cir);
		
		idel = new JMenuItem("Delete Item");
		idel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu.add(idel);
		
		JMenu mnView = new JMenu("View");
		mnView.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu.add(mnView);
		
		vai = new JMenuItem("View All Items");
		vai.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnView.add(vai);
		
		JMenu mnViewItemsBy = new JMenu("View Items By...");
		mnViewItemsBy.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnView.add(mnViewItemsBy);
		
		vic = new JMenuItem("Item Code");
		vic.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnViewItemsBy.add(vic);
		
		vin = new JMenuItem("Item Name");
		vin.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnViewItemsBy.add(vin);
		
		vit = new JMenuItem("Item Type");
		vit.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnViewItemsBy.add(vit);
		
		vvn = new JMenuItem("Veg/Nonveg");
		vvn.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnViewItemsBy.add(vvn);
		
		vrat = new JMenuItem("Ratings");
		vrat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnViewItemsBy.add(vrat);
		
		mnBillling = new JMenu("BILLLING");
		mnBillling.setBackground(Color.WHITE);
		mnBillling.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		mnBillling.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnBillling.setContentAreaFilled(false);
		mnBillling.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnBillling);
		
		newbill = new JMenuItem("New Bill");
		newbill.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnBillling.add(newbill);
		
		viewbill = new JMenuItem("View Bill");
		viewbill.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnBillling.add(viewbill);
		
		JMenu mnStaff = new JMenu("STAFF");
		mnStaff.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		mnStaff.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnStaff);
		
		aemp = new JMenuItem("Add New Empployee");
		aemp.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnStaff.add(aemp);
		
		uemp = new JMenuItem("Update Employee Details");
		uemp.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnStaff.add(uemp);
		
		remp = new JMenuItem("Remove Employee");
		remp.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnStaff.add(remp);
		
		mnViewEmployees = new JMenu("View Employees...");
		mnViewEmployees.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnStaff.add(mnViewEmployees);
		
		viewempall = new JMenuItem("All");
		viewempall.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		mnViewEmployees.add(viewempall);
		
		viewid = new JMenuItem("By ID");
		viewid.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		mnViewEmployees.add(viewid);
		
		viewname = new JMenuItem("By Name");
		viewname.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		mnViewEmployees.add(viewname);
		
		viewcntct = new JMenuItem("By Contact Number");
		viewcntct.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		mnViewEmployees.add(viewcntct);
		
		mnNewMenu_2 = new JMenu("EXTRAS");
		mnNewMenu_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		mnNewMenu_2.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnNewMenu_2);
		
		mnNewMenu_1 = new JMenu("Get Customer's Details By...");
		mnNewMenu_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_2.add(mnNewMenu_1);
		
		cdb = new JMenuItem("Bill Number");
		cdb.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_1.add(cdb);
		
		cdn = new JMenuItem("Name");
		cdn.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_1.add(cdn);
		
		cdc = new JMenuItem("Contact Number");
		cdc.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_1.add(cdc);
		
		mnNewMenu_3 = new JMenu("Today's Sale...");
		mnNewMenu_3.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_2.add(mnNewMenu_3);
		
		saleamnt = new JMenuItem("Total Amount");
		saleamnt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_3.add(saleamnt);
		
		tdysale = new JMenuItem("Sale Details");
		tdysale.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnNewMenu_3.add(tdysale);
		
		mnExtras = new JMenu("SETTINGS");
		mnExtras.setFont(new Font("Century Gothic", Font.BOLD, 16));
		mnExtras.setContentAreaFilled(false);
		mnExtras.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		mnExtras.setBackground(Color.WHITE);
		menuBar.add(mnExtras);
		
		upvat = new JMenuItem("Update VAT");
		upvat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnExtras.add(upvat);
		
		updis = new JMenuItem("Update Discount");
		updis.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnExtras.add(updis);
		
		chpswd = new JMenuItem("Change Password");
		chpswd.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		mnExtras.add(chpswd);
		upvat.addActionListener(this);
		updis.addActionListener(this);
		reload = new JButton("RELOAD");
		reload.setBackground(SystemColor.control);
		reload.setForeground(SystemColor.desktop);
		reload.setFont(new Font("Century Gothic", Font.BOLD, 15));
		reload.setBounds(1252, 11, 100, 35);
		getContentPane().add(reload);
		
		exit = new JButton("EXIT");
		exit.setBackground(SystemColor.control);
		exit.setForeground(SystemColor.desktop);
		exit.setFont(new Font("Century Gothic", Font.BOLD, 15));
		exit.setBounds(1252, 57, 100, 35);
		getContentPane().add(exit);
		
		viewbill.addActionListener(this);
		newbill.addActionListener(this);
		vai.addActionListener(this);
		idel.addActionListener(this);
		cir.addActionListener(this);
		cip.addActionListener(this);
		inew.addActionListener(this);
		vic.addActionListener(this);
		vin.addActionListener(this);
		vit.addActionListener(this);
		vvn.addActionListener(this);
		vrat.addActionListener(this);
		reload.addActionListener(this);
		exit.addActionListener(this);
		cdb.addActionListener(this);;
		cdn.addActionListener(this);
		cdc.addActionListener(this);
		tdysale.addActionListener(this);
		saleamnt.addActionListener(this);
		chpswd.addActionListener(this);
		
		
		ImageIcon ii=new ImageIcon(getClass().getResource("images/bg.jpg"));
		getContentPane().setLayout(null);
		
		
		
	
		
		
		
		lblSystem = new JLabel("SYSTEM");
		lblSystem.setForeground(new Color(0, 0, 0));
		lblSystem.setFont(new Font("Dynasty", Font.PLAIN, 50));
		lblSystem.setBounds(1162, 593, 485, 93);
		getContentPane().add(lblSystem);
		
		
		
		JLabel lblNewLabel = new JLabel("RESTAURANT");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Dynasty", Font.PLAIN, 72));
		lblNewLabel.setBounds(900, 536, 485, 93);
		getContentPane().add(lblNewLabel);
		
		lblManagement = new JLabel("MANAGEMENT");
		lblManagement.setForeground(new Color(0, 0, 0));
		lblManagement.setFont(new Font("Dynasty", Font.PLAIN, 35));
		lblManagement.setBounds(906, 611, 271, 44);
		getContentPane().add(lblManagement);
		
		JLabel lab_1=new JLabel(new ImageIcon(Homepage.class.getResource("/restaurantManagement/images/bg.jpg")));
		lab_1.setPreferredSize(new Dimension(1080, 720));
		lab_1.setBounds(0,0,1400,800);
		getContentPane().add(lab_1);
		
		

		setVisible(true);
		
		aemp.addActionListener(this);
		uemp.addActionListener(this);
		remp.addActionListener(this);
		viewempall.addActionListener(this);
		viewid.addActionListener(this);
		viewname.addActionListener(this);
		viewcntct.addActionListener(this);
	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		if(ob==inew)
		{
			new ItemAdd();
		}
		else if(ob==cip)
		{
			String code=JOptionPane.showInputDialog(null,"Enter Item Code");
			if(code.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field");
			}
			else
			{
			new ItemUpdate(code);
			}
		}
		else if(ob==cir)
		{
			String code=JOptionPane.showInputDialog(null,"Enter Item Code");
		if(code.equalsIgnoreCase(""))
		{

			JOptionPane.showMessageDialog(null, "Empty Field.");
		}
		else
		{
		new Ratings(code);
		}
		}
		else if(ob==idel)
		{
			Connection cn=DBConnection.connect();
			String code=JOptionPane.showInputDialog(null,"Enter Item Code");
			if(code.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(code==null){}
			else{
			String query="Select * from items where icode LIKE '"+code+"';";
			try
			{
				PreparedStatement ps=cn.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				if(!(rs.next())){
					JOptionPane.showMessageDialog(null, "Item doesn't exist.");				
				}
				else
				{
					try
					{
						query="Delete from items where icode LIKE '"+code+"';";
						PreparedStatement ps1=cn.prepareStatement(query);
						ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Item successfully deleted.");				
						}
						
						
					
					catch(SQLException se)
							{
						se.printStackTrace();
							}
					}
				}
				
			
			catch(SQLException se)
					{
				se.printStackTrace();
					}
			}
		}
			
			

		
		else if(ob==chpswd)
		{
			new ChangePassword();
		}	
		
		else if(ob==newbill)
		{
			
			String s="Select billnum from billnumber";
			
			Connection cn=DBConnection.connect();
			try
			{
				PreparedStatement ps=cn.prepareStatement(s);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
				bno=rs.getInt("billnum");
				}
			}
			catch(SQLException se)
					{
				se.printStackTrace();
					}

			String billtable=String.valueOf(bno);
			
			new Billing(billtable);
			
		}
		else if(ob==vai)
		{
			String query="Select * from items";
			new ViewAllItems(query);
		}
		else if(ob==vic)
		{
			String code=JOptionPane.showInputDialog(null,"Enter Item Code");
			if(code.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(code==null)
			{}
			else
			{
			String query="Select * from items where icode='"+code+"';";
			new ViewAllItems(query);
			}
		}
		else if(ob==vin)
		{
			String name=JOptionPane.showInputDialog(null,"Enter Item Name");
			if(name.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(name==null){}
			else
			{
			String query="Select * from items where iname='"+name+"';";
			new ViewAllItems(query);
			}
		}
		else if(ob==vit)
		{
			new SelectType();
		}
		else if(ob==vvn)
		{
			new Veg_Nonveg();
		}
		else if(ob==vrat)
		{
			new RatingRange();
		}
		else if(ob==viewbill)
		{
			String bill=JOptionPane.showInputDialog(null,"Enter Bill Number");
			if(bill.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(bill==null){}
			else
			{
				new ViewGenBill(bill);

			}
		}
		else if(ob==upvat)
		{
			new UpdateVAT();
		}
		else if(ob==updis)
		{
			new UpdateDisc();
		}
		else if(ob==cdb)
		{
			String bnumb=JOptionPane.showInputDialog(null,"Enter Bill Number");
			if(bnumb.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(bnumb==null){}
			else
			{
			String query="Select * from billdet where billnum="+bnumb+";";
			new CustomerDetails(query);
			} 
		}
		else if(ob==cdn)
		{
			String cdname=JOptionPane.showInputDialog(null,"Enter Name");
			if(cdname.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(cdname==null){}
			else
			{
			String query="Select * from billdet where cname LIKE '"+cdname+"';";
			new CustomerDetails(query);
			} 
		}
		else if(ob==cdc)
		{
			String cnumb=JOptionPane.showInputDialog(null,"Enter Contact Number");
			if(cnumb.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(cnumb==null){}
			else
			{
			String query="Select * from billdet where cph LIKE '"+cnumb+"';";
			new CustomerDetails(query);
			} 
		}
		
		
		else if(ob==saleamnt)
		{
			
			Connection con=DBConnection.connect();
			DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
			Calendar cal = Calendar.getInstance();
			String strDate=cal.getTime().toString();
			String strDt=strDate.substring(0, 11);
			String q="Select * from billdet where datetime LIKE '"+strDt+"%'";
			double billam=0;
			try
			{
				
				
				PreparedStatement ps=con.prepareStatement(q);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{	
					billam=billam+rs.getDouble("billamount");
				}
				
				JOptionPane.showMessageDialog(null, "Today's sale amounts to Rs. "+String.valueOf(billam));
				}
			
				catch (SQLException se)
				{
					se.printStackTrace();
				}
		}
		
		
		
		
		else if(ob==tdysale)
		{
			DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
			Calendar cal = Calendar.getInstance();
			String strDate=cal.getTime().toString();
			String strDt=strDate.substring(0, 11);
			String query="Select * from billdet where datetime LIKE '"+strDt+"%'";
			new CustomerDetails(query);
			
		}
		else if(ob==exit)
		{
			
			int choose=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",null,JOptionPane.YES_NO_OPTION);
			if(choose==0)
			{
			System.exit(0);
			}
		}
		else if(ob==reload)
		{
			int choose=JOptionPane.showConfirmDialog(null, "This will RESTART the RESTAURANT MANAGEMENT SYSTEM. Are you sure to RELOAD?", null, JOptionPane.YES_NO_OPTION);
			
			if(choose==0)
			{
				dispose();
				 EventQueue.invokeLater(new Runnable() {
				        @Override
				        public void run() {
				        	
				            new Login();
				            
				        }
				    });
			}
		}
		
		
		
		
		else if(ob==aemp)
		{
			new AddEmployee();
		}
		
		
		
		else if(ob==uemp)
		{
			String str=JOptionPane.showInputDialog(null,"Enter Employee ID.");
			if(str.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(str==null){}
			else
			{
			new EmpUpdate(str);
			}
		}
		
		else if(ob==remp)
		{
			Connection cn=DBConnection.connect();
			String code=JOptionPane.showInputDialog(null,"Enter Employee ID");
			if(code.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(code==null){}
			else{
			String query="Select * from staff where eid="+code+";";
			try
			{
				PreparedStatement ps=cn.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				if(!(rs.next())){
					JOptionPane.showMessageDialog(null, "Such Employee doesn't exist.");				
				}
				else
				{
					try
					{
						query="Delete from staff where eid="+code+";";
						PreparedStatement ps1=cn.prepareStatement(query);
						ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Employee successfully removed.");				
						}
						
						
					
					catch(SQLException se)
							{

						JOptionPane.showMessageDialog(null, "Such Employee doesn't exist.");
						se.printStackTrace();
							}
					}
				}
				
			
			catch(SQLException se)
					{
				JOptionPane.showMessageDialog(null, "Such Employee doesn't exist.");
				se.printStackTrace();
					}
			}
		
		}
		
		
		else if(ob==viewempall)
		{
			String query="Select * from staff;";
			new ViewEmp(query);
 	   	}
		
		else if(ob==viewcntct)
		{
			String cnumb=JOptionPane.showInputDialog(null,"Enter Employee's Contact Number");
			if(cnumb.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(cnumb==null){}
			else
			{
			String query="Select * from staff where ecntct LIKE '"+cnumb+"';";
			new ViewEmp(query);
			} 
		}
		
		
		else if(ob==viewid)
		{
			String cnumb=JOptionPane.showInputDialog(null,"Enter Employee's ID");
			if(cnumb.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(cnumb==null){}
			else
			{
			String query="Select * from staff where eid="+cnumb+";";
			new ViewEmp(query);
			} 
		}
		
		
		else if(ob==viewname)
		{
			String cnumb=JOptionPane.showInputDialog(null,"Enter Employee's Name");
			if(cnumb.equalsIgnoreCase(""))
			{

				JOptionPane.showMessageDialog(null, "Empty Field.");
			}
			else if(cnumb==null){}
			else
			{
			String query="Select * from staff where ename LIKE '"+cnumb+"';";
			new ViewEmp(query);
			} 
		}
		
		
		
		
	}


	public JLabel getLab() {
		return lab;
	}
}
