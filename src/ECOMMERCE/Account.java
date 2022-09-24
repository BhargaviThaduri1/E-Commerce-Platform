package ECOMMERCE;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class Account extends JFrame implements ActionListener{
    JLabel l1, l2, l3, l4, l5, l6, l7,l8,l9,l10;
    JTextField tf1, tf2, tf3, tf4, tf5,tf6,tf7,tf8;  
    JButton edit_btn, delete_btn,update_btn,btn4,back_btn;  
    JPasswordField p1;
    JComboBox c1;
    Account()  
    {  

   	 
   	 
    	  setBackground(new Color(169, 169, 169));	
    	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   	  setBounds(0, 0, screenSize.width, screenSize.height);
        setLayout(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        this.getContentPane().setBackground(new Color(152,251,152));
        setTitle("Your Account");  
        l1 = new JLabel("  Your Account Details");  
        l1.setForeground(Color.red);  
        l1.setFont(new Font("Serif", Font.BOLD, 30));  
        
        l2 = new JLabel("FirstName ");  
        l3 = new JLabel("Second Name ");  
        l4 = new JLabel("ID ");  
        l5 = new JLabel("Password "); 
        l6 = new JLabel("Email ID "); 
        l7 = new JLabel("Phone No ");  
        l8 = new JLabel("Alternate Phone No ");
        l9 = new JLabel("Address ");
        l10 = new JLabel("Gender ");
        
      
       
        tf1 = new JTextField(); 
     
      
        tf1.setEditable(false);
        tf2 = new JTextField();  //l3
        tf2.setEditable(false);
        tf3 = new JTextField();  //l4
        tf3.setEditable(false);
        p1 = new JPasswordField(); //l5 
        p1.setEditable(false);
        tf4 = new JTextField();  //l6
        tf4.setEditable(false);
        tf5 = new JTextField();  //l7
        tf5.setEditable(false);
        tf6 = new JTextField(); //l8
        tf6.setEditable(false);
        tf7 = new JTextField(); //l9
        tf7.setEditable(false);
        tf8 = new JTextField(); //l10
        tf8.setEditable(false);
        
        
        Border border = BorderFactory.createLineBorder(Color.white, 1);
  		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
  		Image image = imageIcon.getImage(); // transform it 
  		Image newimg = image.getScaledInstance(40, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
  		imageIcon = new ImageIcon(newimg);  
       
       back_btn = new JButton(imageIcon);
       back_btn.setBounds(40,30,30,30);
       back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       back_btn.addActionListener(new ActionListener() {

  			@Override
  			public void actionPerformed(ActionEvent e) {
  				dispose();
  				
  				HOME h = new HOME();
  				h.f.setVisible(true);
  				h.homePage();   
  				
  				
  				
  			}
       	
       });
        
       String s1[] = {"Choose","female","male"};
       
       
       c1 = new JComboBox(s1);
       c1.setBounds(500,439,80,20);
       c1.addItemListener(new ItemListener()
       {
            public void itemStateChanged(ItemEvent event)
            {
                 if (event.getStateChange() == ItemEvent.SELECTED)
                 {
                      try
                      {
                           String no=c1.getSelectedItem().toString();
                           tf8.setText(no);
                           tf8.setEditable(false);
                      }
                      catch (Exception ex)
                      {
                           ex.printStackTrace();
                      }
                 }
            }
       });
       add(c1);
        
        edit_btn = new JButton("Edit");  
        delete_btn = new JButton("Delete");  
        
        edit_btn.addActionListener(this);  
        delete_btn.addActionListener(this);  
        update_btn = new JButton("Update");
        update_btn.addActionListener(this);
      
        
        
        l1.setBounds(437, 30, 400, 30);  
        l2.setBounds(437, 100, 200, 30);  
        l3.setBounds(437, 140, 200, 30);  
        l4.setBounds(437, 180, 200, 30);  
        l5.setBounds(437, 230, 200, 30);  
        l6.setBounds(437, 275, 200, 30);  
        l7.setBounds(437, 315, 200, 30);  
        l8.setBounds(437,355,200,30);
        l9.setBounds(437,393,200,30);
        l10.setBounds(437,435,200,30);
        
       
       
        l2.setFont(new Font("Serif", Font.BOLD, 20)); 
       
        l3.setFont(new Font("Serif", Font.BOLD, 20)); 
        
        
        l4.setFont(new Font("Serif", Font.BOLD, 20)); 
        
     

        l5.setFont(new Font("Serif", Font.BOLD, 20)); 
        
      
        l6.setFont(new Font("Serif", Font.BOLD, 20)); 
       
       
        l7.setFont(new Font("Serif", Font.BOLD, 20)); 
       
        
       
        l8.setFont(new Font("Serif", Font.BOLD, 20)); 
     
      
        l9.setFont(new Font("Serif", Font.BOLD, 20)); 
        l10.setFont(new Font("Serif", Font.BOLD, 20)); 
       
        
      
        tf1.setFont(new Font("Serif", Font.PLAIN, 20));
       
        tf2.setFont(new Font("Serif", Font.PLAIN, 20)); 
      
     
        p1.setFont(new Font("Serif", Font.PLAIN, 20)); 
     
        tf3.setFont(new Font("Serif", Font.PLAIN, 20)); 
       
        tf4.setFont(new Font("Serif", Font.PLAIN, 20)); 
       
    
        tf5.setFont(new Font("Serif", Font.PLAIN, 20)); 
       
        
       
        tf6.setFont(new Font("Serif", Font.PLAIN, 20)); 
      
        
        tf7.setFont(new Font("Serif", Font.PLAIN, 20)); 
     
    
        tf8.setFont(new Font("Serif", Font.PLAIN, 20)); 
        
        tf1.setBounds(650, 100, 300, 30);  
        tf8.setEditable(false);
        tf2.setBounds(650, 140, 300, 30); 
        tf3.setBounds(650,190,300,30);
       
        p1.setBounds(650, 230, 300, 30);  
       
        tf4.setBounds(650,270,300,30);
        
        tf5.setBounds(650, 320, 300, 30); 
        tf6.setBounds(650,360,300,30);
        tf7.setBounds(650,400,300,30);
    
        tf8.setBounds(650,440,300,30);
        
      
       
        edit_btn.setBounds(440, 540, 100, 30);  
        delete_btn.setBounds(690,540,100,30);
        update_btn.setBounds(560, 540, 100, 30);  
        edit_btn.setBackground(new Color(255,196,12));
        update_btn.setBackground(new Color(255,196,12));
        delete_btn.setBackground(new Color(255,196,12));
        
        //getting the values of the current user
        Global g = new Global();
        int id = g.ID;
        try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
			Statement stmt=con.createStatement();  
			String sql = "select*from customer where customer_id = "+ id+"";
			ResultSet rs =  stmt.executeQuery(sql);
			if(rs.next()) {
				String first = rs.getString("customer_first_name");
				tf1.setText(first);
				String last = rs.getString("customer_last_name");
				tf2.setText(last);
				int c_id = rs.getInt("customer_id");
				tf3.setText(String.valueOf(c_id));
				
				String s = rs.getString("customer_password");
				p1.setText(s);
				
				String email = rs.getString("customer_email");
				tf4.setText(email);
				
				String phoneno = rs.getString("customer_phoneno");
				tf5.setText(phoneno);
				
				String alternateno = rs.getString("customer_alternate_no");
				tf6.setText(alternateno);
				
				String add = rs.getString("customer_address");
				tf7.setText(add);
				
				String gender = rs.getString("customer_gender");
				tf8.setText(gender);
				
				
				
			}
		
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        
        add(l1);  
        add(l2); 
        add(l3); 
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(l10);
        
        
        add(tf1);  
        add(tf2);
        add(p1);  
        add(tf3);
        add(tf4);
        add(tf5);
        add(tf6);
        add(tf7);
        add(tf8);
       
        
       
        add(edit_btn);  
        add(delete_btn);  
        add(back_btn);
        add(update_btn);
        
        setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
	int clicks =0;
					try {	
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
					Statement stmt=con.createStatement();  
					Global g = new Global();
					int id = g.ID;
				
					if(e.getSource()==edit_btn) {
						clicks ++;
						delete_btn.setEnabled(false);
						tf1.setEditable(true);
						tf2.setEditable(true);
						p1.setEditable(true);
						tf4.setEditable(true);
						tf5.setEditable(true);
						tf6.setEditable(true);
						tf7.setEditable(true);
						tf8.setEditable(true);
						delete_btn.setEnabled(false);
					
					}
					String choose = "Choose";
					if(e.getSource()==update_btn) {
						if(tf8.getText().equals(choose)) {
							JOptionPane.showMessageDialog(null, "Gender should not be Choose");
						}
						else {
						String sql = "update customer set customer_first_name = '"+tf1.getText()+"', customer_last_name = '"+tf2.getText()+"',customer_password = '"+p1.getText()+"',customer_email = '"+ tf4.getText()+"',customer_phoneno='"+tf5.getText()+"', customer_alternate_no = '"+tf6.getText()+"',customer_address='"+tf7.getText()+"',customer_gender='"+tf8.getText()+"'"	 +" where customer_id = "+id+"";
						stmt.executeUpdate(sql);				
						JOptionPane.showMessageDialog(null, "Account Updated");
						delete_btn.setEnabled(true);
						}
						
						
					}
					
				
					
					else if(e.getSource()==delete_btn) {
						int reply = JOptionPane.showConfirmDialog(null, "Are you really want to delete account", "delete", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							String sql = "delete from customer where customer_id = '"+id+"'";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Your accunt Deleted");
							setVisible(false); 
							
							dispose();
							new LoginPage().setVisible(true);
							
						} 
						else {
						  new Account().setVisible(true);
						}
						
					}
				}
				catch(Exception ee) {
					System.out.println(ee);
				}
					
		
		
		}
}