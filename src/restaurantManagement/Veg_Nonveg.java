package restaurantManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.sql.Connection;

import javax.swing.border.BevelBorder;

public class Veg_Nonveg extends JDialog implements ActionListener {
	private JRadioButton radveg;
	private JRadioButton radnonveg;
	private JButton view;
	private JButton cancel;
	String val;

	public Veg_Nonveg() {
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(275,175);
		setLocationRelativeTo(null);
		setModal(true);
		
		radveg = new JRadioButton("Veg");
		radveg.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		radveg.setBounds(85, 33, 109, 23);
		getContentPane().add(radveg);
		
		radnonveg = new JRadioButton("Nonveg");
		radnonveg.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		radnonveg.setBounds(85, 59, 109, 23);
		getContentPane().add(radnonveg);
		
		view = new JButton("View");
		view.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		view.setBounds(23, 108, 89, 23);
		getContentPane().add(view);
		
		ButtonGroup btgr=new ButtonGroup();
		btgr.add(radveg);
		btgr.add(radnonveg);
		
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		cancel.setBounds(146, 108, 89, 23);
		getContentPane().add(cancel);
		
		view.addActionListener(this);
		cancel.addActionListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==view)
		{
			if(radveg.isSelected())
			{
			val="VEG";
			}
			else if(radnonveg.isSelected())
			{
			val="NONVEG";
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select one option.");
			}
			if(val=="VEG"||val=="NONVEG")
			{
			String query="Select * from items where ivn LIKE '"+val+"';";
			dispose();
			new ViewAllItems(query);
			}
		}
		if(e.getSource()==cancel)
		{
			dispose();
		}
		
		
	}
}
