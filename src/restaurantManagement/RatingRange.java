package restaurantManagement;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

public class RatingRange extends JDialog implements ActionListener{
	private JTextField start;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JTextField end;
	private JButton ratingview;
	private JButton ratingcancel;

	public RatingRange() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setSize(363,310);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		start = new JTextField();
		start.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		start.setBounds(139, 79, 66, 32);
		getContentPane().add(start);
		start.setColumns(10);
		
		JLabel lblViewItemsWhose = new JLabel("View items whose average rating ranges ");
		lblViewItemsWhose.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblViewItemsWhose.setBounds(42, 27, 288, 20);
		getContentPane().add(lblViewItemsWhose);
		
		lblFrom = new JLabel("from");
		lblFrom.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblFrom.setBounds(157, 48, 30, 20);
		getContentPane().add(lblFrom);
		
		lblTo = new JLabel("to");
		lblTo.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblTo.setBounds(162, 119, 37, 20);
		getContentPane().add(lblTo);
		
		end = new JTextField();
		end.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		end.setColumns(10);
		end.setBounds(139, 151, 66, 32);
		getContentPane().add(end);
		
		ratingview = new JButton("View");
		ratingview.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ratingview.setBounds(42, 229, 89, 23);
		getContentPane().add(ratingview);
		
		ratingcancel = new JButton("Cancel");
		ratingcancel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ratingcancel.setBounds(216, 229, 89, 23);
		getContentPane().add(ratingcancel);
		
		JLabel lblleaveBlankFor = new JLabel("(Leave ending value blank for a specific rating)");
		lblleaveBlankFor.setBounds(44, 194, 288, 20);
		getContentPane().add(lblleaveBlankFor);
		
		start.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		end.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!='.')) {
	            	
	                e.consume();
	            	}
	            }
	            	
	        
	    });
		
		ratingview.addActionListener(this);
		ratingcancel.addActionListener(this);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ratingview)
		{
			double sp,ep;
			String init=(String)start.getText();
			String halt=(String)end.getText();
			if(init.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(null, "Starting range can't be empty!");
			}
			else{
				//*********************************************
				if(halt.equalsIgnoreCase(""))
				{
					sp=Double.parseDouble(init);
					ep=sp;
				}
				else
				{
					sp=Double.parseDouble(init);
					ep=Double.parseDouble(halt);
				}
				if(sp<0||sp>10||ep<0||ep>10)
				{
					JOptionPane.showMessageDialog(null, "Values can range from 0-10");
					start.setText("");
					end.setText("");
				}
				else if(sp>ep)
				{
					JOptionPane.showMessageDialog(null, "Starting Value should not be smaller than Ending Value.");
					start.setText("");
					end.setText("");
				}
				else
				{
				String query="Select * from items where avgrtng>="+sp+"AND avgrtng<="+ep+";";
				dispose();
				new ViewAllItems(query);
				}
			}
			//*******************************************************
		}
		if(e.getSource()==ratingcancel)
		{
			dispose();
				
			}
		}
		
	}

