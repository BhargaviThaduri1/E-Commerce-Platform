package ECOMMERCE;
import javax.swing.*;
import javax.swing.border.Border;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class SellerSignUp extends JFrame implements ActionListener {
	JLabel l1, l2, l3, l4, l5, l6, l7,l8,l9,l10;
    JTextField tf1, tf2, tf3, tf4, tf5,tf6,tf7,tf8;  
    JButton btn1, btn2,btn4,back_btn;  
    JPasswordField p1;
    JComboBox c1;
    SellerSignUp()  
    {  
    	setBackground(new Color(169, 169, 169));	
    	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  	  setBounds(0, 0, screenSize.width, screenSize.height);
  	getContentPane().setBackground(new Color(152,251,152));
        setLayout(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setTitle("SignUp Form");  
        

        Border border = BorderFactory.createLineBorder(Color.white, 1);
     	ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png");// load the image to a imageIcon
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
   				new SellerPage().f.setVisible(true);
   			
   			}
        	
        });
   		back_btn.setBackground(Color.white);
   		add(back_btn);
        
        l1 = new JLabel("Create Account as a Seller");  
        l1.setForeground(Color.BLACK);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        
        l2 = new JLabel("SELLER NAME");  
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 15));  
        l3 = new JLabel("SELLER ID");  
        l3.setFont(new Font("Times New Roman", Font.PLAIN, 15));  
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 15));  
        l4 = new JLabel("SELLER PASSWORD"); 
        l4.setFont(new Font("Times New Roman", Font.PLAIN, 15));  
  
        l5 = new JLabel("ADDRESS ");
        l5.setFont(new Font("Times New Roman", Font.PLAIN, 15));  
         
        tf1 = new JTextField();  //l2
        tf2 = new JTextField();  //l3
        p1 = new JPasswordField(); //l4
        tf3 = new JTextField();  //l5
        tf4= new JTextField();  //l6
        
        
        btn1 = new JButton("Create");  
        btn2 = new JButton("Clear");  
        btn1.addActionListener(this);  
        btn2.addActionListener(this);  
       
    
        l1.setBounds(120, 30, 400, 30);  
        l2.setBounds(80, 80, 200, 30);  
        l3.setBounds(80, 120, 200, 30);  
        l4.setBounds(80, 162, 200, 30);  
        l5.setBounds(80, 220, 200, 30);  
       
       
        tf1.setBounds(300, 70, 200, 40);  
        tf2.setBounds(300, 115, 200, 40); 
       
        p1.setBounds(300, 160, 200, 40);  
        tf3.setBounds(300,210,200,40);
        tf4.setBounds(300,250,200,40);
        
        
        btn1.setBounds(50, 300, 100, 30);  
        btn1.setBackground(new Color(255,196,12)); 
        btn2.setBounds(170, 300, 100, 30);  
        btn2.setBackground(new Color(255,196,12)); 
      
      
        add(l1);  
        add(l2); 
        add(l3); 
        add(l4);
        add(l5);
       
       
        add(tf1);  
        add(tf2);
        add(p1);  
        add(tf3);
      
       
        
       
        add(btn1);  
        add(btn2);  
        
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
			Statement stmt=con.createStatement();  
		 if(e.getSource()==btn1) {
			      String s_name = tf1.getText().toString();
					String s_id = tf2.getText();
					int id =0;
					if(!s_id.isEmpty() && s_id.matches("[0-9]+")) id = Integer.parseInt(tf2.getText().toString());	
					char[] e1 = p1.getPassword();
				    String password = new String(e1);
				    String address = tf3.getText();
				    DatabaseMetaData dbm = con.getMetaData();
				  ResultSet tables = dbm.getTables(null, null, "seller", null);
				
				if (!tables.next()) {
				 	// String sql = "create table customer "+" (customer_first_name varchar(50) NOT NULL, "+" customer_last_name varchar(50),"+" customer_id int(100) PRIMARY KEY, "+" customer_password varchar(50) NOT NULL,"+" customer_email varchar(30) NOT NULL,"+" customer_phoneno varchar(50) NOT NULL,"+" customer_alternate_no varchar(50) ,"+" customer_address varchar(50) NOT NULL,"+" customer_gender varchar(10)   )";
					 String sql = "create table seller "+" (seller_name varchar(50) NOT NULL,"+" seller_id int PRIMARY KEY,"+" seller_password varchar(30),"+" seller_address varchar(20))";
			         stmt.executeUpdate(sql);  
			  
				}
				else {
					ResultSet rs1 = stmt.executeQuery("select*from seller");
							
					ResultSetMetaData rsmd = (ResultSetMetaData) rs1.getMetaData();
					int count_sellers = rsmd.getColumnCount();
				if(count_sellers >0) {
					String sql = "select*from seller where seller_id = "+id+"";
					ResultSet rs = stmt.executeQuery(sql);
				
						if(rs.next()) JOptionPane.showMessageDialog(this,"SellerId already exists","Warining message",JOptionPane.WARNING_MESSAGE);
						else if(s_name.isEmpty())  			JOptionPane.showMessageDialog(this,"Please Enter Name ","Warining message",JOptionPane.WARNING_MESSAGE);
						else if(!s_id.matches("[0-9]+"))    JOptionPane.showMessageDialog(this,"Id should not contain characters ","Warining message",JOptionPane.WARNING_MESSAGE);
						else if(password.isEmpty()  ) 		JOptionPane.showMessageDialog(this,"Enter password","Warining message",JOptionPane.WARNING_MESSAGE);
						else if(address.isEmpty()  ) 			JOptionPane.showMessageDialog(this,"Enter Address","Warining message",JOptionPane.WARNING_MESSAGE);
						else {
							String sql1 = "insert into seller values('"+s_name+"', "+id+" ,'"+password+"','"+address+"') ";
							stmt.executeUpdate(sql1);
							JOptionPane.showMessageDialog(this, "Account Created");
							dispose();
							new SellerPage().f.setVisible(true);
						}
						
					}
					
				}
				
				
		
		}
		
		else if(e.getSource()==btn2) {
			tf1.setText("" );
			tf2.setText("" );
			tf3.setText("" );
			tf4.setText("" );
			tf5.setText("" );
			tf6.setText("" );
			 p1.setText("");
			tf7.setText("" );
			tf8.setText("" );
		}
		
	
		}
	
		
		catch(Exception ee){ System.out.println(ee);}  
		
		
		
	}
	
}
