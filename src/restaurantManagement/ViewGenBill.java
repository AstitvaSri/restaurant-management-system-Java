package restaurantManagement;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class ViewGenBill {
	int cnt,r,c;
	JScrollPane jsp;
	String columns[];
	String data[][];
	JButton print;
	JTable table;

	final JDialog dialog=new JDialog();
	private JTextField bn;
	private JButton close;
	String nameoftable;
	
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ViewGenBill(String btable) {
		nameoftable=btable;
		dialog.setSize(new Dimension(429, 296));
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		String st="select * from `"+nameoftable+"`;";
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(st);
			columns=new String[]{"Item","Price"};
			ResultSet rs=ps.executeQuery();
			
			rs.last();
			cnt=rs.getRow();
			rs.beforeFirst();
			data=new String[cnt][2];
			while(rs.next())
			{
				data[r][c]=String.valueOf(rs.getString("Item"));
				++c;
				data[r][c]=String.valueOf(rs.getDouble("Price"));
				++c;
				++r;
				c=0;
			}
			table=new JTable(data,columns);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			print=new JButton("PRINT");
			print.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			print.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Printed!");
					
				}
			});
			dialog.getContentPane().setLayout(null);
			print.setBounds(222,14,78,23);
			jsp.setBounds(10, 60, 409, 200);
			dialog.getContentPane().add(print);
			dialog.getContentPane().add(jsp);
			
			close = new JButton("CLOSE");
			close.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			close.setBounds(310, 14, 78, 23);
			dialog.getContentPane().add(close);
			
			JLabel lblBillNo = new JLabel("Bill No.");
			lblBillNo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
			lblBillNo.setBounds(10, 20, 46, 14);
			dialog.getContentPane().add(lblBillNo);
			
			bn = new JTextField();
			bn.setHorizontalAlignment(SwingConstants.CENTER);
			bn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			
			bn.setBackground(Color.WHITE);
			bn.setBounds(47, 17, 86, 20);
			dialog.getContentPane().add(bn);
			bn.setColumns(10);
			bn.setText(btable);
			bn.setEditable(false);
			dialog.setModal(true);
			dialog.setVisible(true);
			
		
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			JOptionPane.showMessageDialog(null, "Bill doesn't exist.");
		}
		
		
	}
	public static void main(String args[])
	{
	}
}
