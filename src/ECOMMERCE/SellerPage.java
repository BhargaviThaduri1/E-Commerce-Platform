package ECOMMERCE;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.event.*;
import java.sql.*;

public class SellerPage  implements ActionListener{

	private JPanel panel;
	JTextField textField;
	private JPasswordField passwordField;
    private JButton b1,b2,b3;
    JButton back_btn;
    JFrame f;

	public SellerPage() {
      f = new JFrame("SellerPage"); 
     f.setTitle("Seller");
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  f.setBounds(0, 0, screenSize.width, screenSize.height);
     f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     f.setResizable(false);
     
     
		
    panel = new JPanel();
	panel.setBounds(0, 0, screenSize.width, screenSize.height);
	
	panel.setLayout(null);
	
	
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
				f.dispose();
				
				HOME h = new HOME();
				h.f.setVisible(true);
				h.homePage();   
				
				
				
			}
     	
     });
		back_btn.setBackground(Color.white);
		panel.add(back_btn);

	JLabel l1 = new JLabel("SELLERID ");
	l1.setBounds(530, 200, 120, 50);
	l1.setFont(new Font("Serif", Font.PLAIN, 20));  
	panel.add(l1);

	JLabel l2 = new JLabel("PASSWORD ");
	l2.setBounds(530, 270, 150, 24);
	l2.setFont(new Font("Serif", Font.PLAIN, 20));  
	panel.add(l2);

	textField = new JTextField();
	textField.setBounds(650, 210, 170, 30);
	textField.setFont(new Font("Serif", Font.BOLD, 20)); 
	panel.add(textField);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(650, 270, 170, 30);
	passwordField.setFont(new Font("Serif", Font.BOLD, 20)); 
	panel.add(passwordField);

	JLabel l3 = new JLabel("");
	l3.setBounds(377, 79, 46, 34);
	panel.add(l3);

	JLabel l4 = new JLabel("");
	l4.setBounds(377, 124, 46, 50);
	panel.add(l3);

	b1 = new JButton("Login");
	b1.addActionListener(this);
	b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));         
	b1.setForeground(new Color(46, 139, 87));
	b1.setBackground(new Color(250, 250, 210));
	b1.setBounds(610, 330, 113, 39);
	panel.add(b1);
		
   b2 = new JButton("SignUp");
	b2.addActionListener(this);
	b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	b2.setForeground(new Color(139, 69, 19));
	b2.setBackground(new Color(255, 235, 205));
	b2.setBounds(740, 330, 113, 39);
	panel.add(b2);
	
	 imageIcon = new ImageIcon("C:\\EcommerceImages\\shop2.jpg"); 
	 image = imageIcon.getImage(); // transform it 
	 newimg = image.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	imageIcon = new ImageIcon(newimg); 
   JLabel l = new JLabel(imageIcon);
   l.setBounds(0,0,screenSize.width, screenSize.height);
   panel.add(l); 
	
	f.add(panel);
	f.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent ae){
          if(ae.getSource() == b1){
        	  int d_id=0;
		      try {
		    	  	Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
					Statement stmt=con.createStatement();  
					  String sell_id = textField.getText();
					  int id = Integer.parseInt(sell_id);
					  Global g = new Global();
					
					  char[] a = passwordField.getPassword();
					  String p = new String(a);
					  DatabaseMetaData dbm = con.getMetaData();
						ResultSet tables = dbm.getTables(null, null, "seller", null);
						if(tables.next()) {
						ResultSet rs1 = stmt.executeQuery("select*from seller");
						
						ResultSetMetaData rsmd = (ResultSetMetaData) rs1.getMetaData();
						int count_sellers = rsmd.getColumnCount();
						if(count_sellers >=1) {
							if(p!=" " && sell_id!=" ") {
							String sql = "select*from seller where seller_id = "+id+" and seller_password ='"+p+"'"; 
							ResultSet rs = stmt.executeQuery(sql);
							if(rs.next()) {
								g.SID = rs.getInt("seller_id");
							
								new SellerOperations().f.setVisible(true);
								f.dispose();
							}
						else JOptionPane.showMessageDialog(null, "InValid Login");
					  }
					}
					else JOptionPane.showMessageDialog(null, "Seller Entries Doesn't exists ");
					
						}
						else {
							JOptionPane.showMessageDialog(null, "Your ENTRY/TABLE Doesn't exists ");
						}
								
		       } 
		      catch (Exception e2) {
                 e2.printStackTrace();
		      }
		     
		    
			
		    }
           if(ae.getSource()==b2) {
        	    
        	  f.dispose();
        	   new SellerSignUp().setVisible(true);
           }
            
        }
    public static void main(String[] args) {
    	SellerPage s = new SellerPage();
    	s.f.setVisible(true);
    }
   
    
  
}