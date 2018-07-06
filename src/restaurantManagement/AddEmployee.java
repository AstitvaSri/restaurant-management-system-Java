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

public class AddEmployee extends JDialog implements ActionListener{
	private JTextField name;
	private JTextField age;
	private JTextField cntct;
	private JTextField sal;
	private JTextField jdate;
	private JTextField id;
	private JComboBox epost;
	private JButton addpost;
	private JRadioButton m;
	private JRadioButton f;
	private JButton add;
	private JButton cancel;
	public ArrayList <String> posts = new ArrayList<String>();
	public String gender;

	public AddEmployee() {
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
		
		
		addpost = new JButton("Add Post");
		addpost.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		addpost.setBounds(337, 257, 94, 32);
		getContentPane().add(addpost);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblGender.setBounds(32, 96, 70, 28);
		getContentPane().add(lblGender);
		
		m = new JRadioButton("Male");
		m.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		m.setBounds(118, 99, 109, 23);
		getContentPane().add(m);
		
		f = new JRadioButton("Female");
		f.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		f.setBounds(229, 101, 109, 23);
		getContentPane().add(f);
		
		
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(m);
		bg.add(f);
		
		
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
		
		add = new JButton("ADD");
		add.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		add.setBounds(150, 444, 89, 28);
		getContentPane().add(add);
		
		cancel = new JButton("CANCEL");
		cancel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		cancel.setBounds(278, 444, 89, 28);
		getContentPane().add(cancel);
		
		add.addActionListener(this);
		addpost.addActionListener(this);
		cancel.addActionListener(this);
		if(m.isSelected())
			gender="Male";
		else
			gender="Female";
		
		fillinfo();
		
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
	void fillinfo()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Calendar cal = Calendar.getInstance();
		String strDate=cal.getTime().toString();
		strDate=strDate.substring(0, 11);
		jdate.setText(strDate);
		
		Connection con=DBConnection.connect();
		String query="Select id from genid";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){				
				id.setText(rs.getString("id"));
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
	
		
		if(e.getSource()==add)
		{
			String chkpost=(String)epost.getSelectedItem();
			if(((name.getText()).equalsIgnoreCase(""))||((cntct.getText()).equalsIgnoreCase(""))||((m.isSelected()==false)&&(f.isSelected()==false))||(age.getText().equalsIgnoreCase(""))||(chkpost.equalsIgnoreCase("(Select)"))||((sal.getText()).equalsIgnoreCase("")))
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
			String query1="Insert into staff values(?,?,?,?,?,?,?,?)";
			String query2="Update genid set id="+idinc+";";
			
			try
			{
				PreparedStatement ps=con.prepareStatement(query1);
				PreparedStatement us=con.prepareStatement(query2);
				ps.setString(1, id.getText());
				ps.setString(2, name.getText());
				ps.setString(3, gender);
				ps.setInt(4,Integer.parseInt(age.getText()));
				ps.setString(5,cntct.getText());
				ps.setString(6,pst);
				ps.setDouble(7,Double.parseDouble(sal.getText()));
				ps.setString(8,jdate.getText());
				ps.executeUpdate();
				us.executeUpdate();
				dispose();
				JOptionPane.showMessageDialog(null,"Employee Added");
				
				
				
			}
			catch(SQLException se)
					{
				se.printStackTrace();
				JOptionPane.showMessageDialog(null,"Duplicate Entry!");
					}
			}
		}
		else if(e.getSource()==addpost)
		{
			dispose();
			new AddPost(posts);
			
		}
		else if(e.getSource()==cancel){
				dispose();
			}
		
		
		
	}
}
