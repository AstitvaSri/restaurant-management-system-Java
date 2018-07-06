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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EmpUpdate extends JDialog implements ActionListener{
	private JTextField name;
	private JTextField age;
	private JTextField cntct;
	private JTextField sal;
	private JTextField jdate;
	private JTextField id;
	private JComboBox epost;
	private JButton update;
	private JButton cancel;
	public ArrayList <String> posts = new ArrayList<String>();
	public String gender;
	private JTextField gendertxt;
	String tempid;
	public EmpUpdate(String inputid) {
		tempid=inputid;
		setTitle("Update Employee Details");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setModal(true);
		setSize(500,525);
		setLocationRelativeTo(null);
		
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblName.setBounds(32, 62, 70, 14);
		getContentPane().add(lblName);
		
		name = new JTextField();
		name.setBackground(Color.WHITE);
		name.setEditable(false);
		name.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		name.setBounds(117, 53, 340, 32);
		getContentPane().add(name);
		name.setColumns(10);
		
		
		
	//******FILL POSTS LOGIC********
		
		String st="select post from posts";
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			rs.beforeFirst();
			String assignposts;
			while(rs.next())
			{
				
				assignposts=String.valueOf(rs.getString("post"));
				posts.add(assignposts);
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		
		
		
		//****************************
		
		epost = new JComboBox();
		epost.setModel(new DefaultComboBoxModel(posts.toArray(new String[posts.size()])));
		epost.setBackground(Color.WHITE);
		epost.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		epost.setBounds(118, 257, 191, 31);
		getContentPane().add(epost);
		
		JLabel lblPost = new JLabel("Post :");
		lblPost.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblPost.setBounds(32, 261, 207, 28);
		getContentPane().add(lblPost);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblGender.setBounds(32, 96, 70, 28);
		getContentPane().add(lblGender);
		
		
		
		ButtonGroup bg=new ButtonGroup();
		
		
		JLabel lblAge = new JLabel("Age : ");
		lblAge.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblAge.setBounds(32, 145, 70, 23);
		getContentPane().add(lblAge);
		
		age = new JTextField();
		age.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		age.setColumns(10);
		age.setBounds(118, 142, 51, 28);
		getContentPane().add(age);
		
		JLabel lblYears = new JLabel("Years");
		lblYears.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblYears.setBounds(179, 149, 70, 17);
		getContentPane().add(lblYears);
		
		cntct = new JTextField();
		cntct.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cntct.setColumns(10);
		cntct.setBounds(118, 194, 182, 32);
		getContentPane().add(cntct);
		
		JLabel lblContact = new JLabel("Contact : ");
		lblContact.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblContact.setBounds(32, 203, 76, 14);
		getContentPane().add(lblContact);
		
		JLabel lblSalary = new JLabel("Salary : ");
		lblSalary.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblSalary.setBounds(32, 327, 76, 23);
		getContentPane().add(lblSalary);
		
		sal = new JTextField();
		sal.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		sal.setColumns(10);
		sal.setBounds(118, 322, 182, 32);
		getContentPane().add(sal);
		
		JLabel label = new JLabel("\u20B9");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(102, 327, 15, 23);
		getContentPane().add(label);
		
		jdate = new JTextField();
		jdate.setBackground(Color.WHITE);
		jdate.setEditable(false);
		jdate.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		jdate.setColumns(10);
		jdate.setBounds(143, 381, 142, 23);
		getContentPane().add(jdate);
		
		JLabel lblJoiningDate = new JLabel("Joining Date : ");
		lblJoiningDate.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblJoiningDate.setBounds(32, 382, 109, 23);
		getContentPane().add(lblJoiningDate);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblId.setBounds(32, 19, 70, 14);
		getContentPane().add(lblId);
		
		id = new JTextField();
		id.setBackground(Color.WHITE);
		id.setEditable(false);
		id.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		id.setColumns(10);
		id.setBounds(117, 14, 110, 23);
		getContentPane().add(id);
		
		getContentPane().setLayout(null);
		
		update = new JButton("UPDATE");
		update.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		update.setBounds(150, 444, 89, 28);
		getContentPane().add(update);
		
		cancel = new JButton("CANCEL");
		cancel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		cancel.setBounds(278, 444, 89, 28);
		getContentPane().add(cancel);
		
		gendertxt = new JTextField();
		gendertxt.setBackground(Color.WHITE);
		gendertxt.setEditable(false);
		gendertxt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		gendertxt.setColumns(10);
		gendertxt.setBounds(117, 96, 60, 28);
		getContentPane().add(gendertxt);
		
		update.addActionListener(this);
		cancel.addActionListener(this);
		
		fillinfo();
		if(name.getText().equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Employee not found.");
		}
		else{
		
		//**************RESTRICTIONS*************
				age.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) {
			        	
			            char c = e.getKeyChar();
			            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
			            	
			                e.consume();
			            	}
			            }
			            	
			        
			    });
				
				cntct.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) {
			        	
			            char c = e.getKeyChar();
			            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			            	
			                e.consume();
			            	}
			            }
			            	
			        
			    });
				
				sal.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) {
			        	
			            char c = e.getKeyChar();
			            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
			            	
			                e.consume();
			            	}
			            }
			            	
			        
			    });
				//*************************************************
		
		setVisible(true);
		}
	}
	void fillinfo()
	{
		Connection con=DBConnection.connect();
		String query="Select * from staff where eid="+tempid+";";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){				
				id.setText(rs.getString("eid"));
				name.setText(rs.getString("ename"));
				gendertxt.setText(rs.getString("gender"));
				age.setText(rs.getString(String.valueOf("age")));
				cntct.setText(rs.getString("ecntct"));
				epost.setSelectedItem(rs.getString("epost"));
				sal.setText(rs.getString(String.valueOf("salary")));
				jdate.setText(rs.getString("joindt"));
				}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		if(e.getSource()==update)
		{
			String chkpost=(String)epost.getSelectedItem();
			if(((cntct.getText()).equalsIgnoreCase(""))||(age.getText().equalsIgnoreCase(""))||(chkpost.equalsIgnoreCase("(Select)"))||((sal.getText()).equalsIgnoreCase("")))
			{
				JOptionPane.showMessageDialog(null, "Some fields are empty.");
			}
			else if(((Integer.parseInt(age.getText()))>=130)||((Integer.parseInt(age.getText()))<=0)){
				JOptionPane.showMessageDialog(null, "Invalid Age!");
				age.setText("");
			}
			else
			{
			int idinc=Integer.parseInt(id.getText());
			idinc=idinc+1;
			Connection con=DBConnection.connect();
			
			String pst=epost.getSelectedItem().toString();
			String query="Update staff set age=?,ecntct=?,epost=?,salary=? where eid="+tempid+";";
			
			try{
				PreparedStatement ps=con.prepareStatement(query);
				ps.setInt(1,Integer.parseInt(age.getText()));
				ps.setString(2, cntct.getText());
				ps.setString(3, pst);
				ps.setDouble(4, Double.parseDouble(sal.getText()));
				int y=ps.executeUpdate();
				dispose();
				JOptionPane.showMessageDialog(null, "Updated");
				
			}
			catch(SQLException se)
					{
				se.printStackTrace();
					}
			}
		}
		else if(e.getSource()==cancel){
				dispose();
			}
		
	}
}