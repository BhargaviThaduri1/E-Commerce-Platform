package ECOMMERCE;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class CustomerSignUp extends JFrame implements ActionListener {
	JLabel l1, l2, l3, l4, l5, l6, l7,l8,l9,l10;
   JTextField tf1, tf2, tf3, tf4, tf5,tf6,tf7,tf8;  
   JButton create, clear,back_btn;  
   JPasswordField p1;
   JComboBox c1;
   CustomerSignUp(){  
   setTitle("Customer Signup ");  
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   setBounds(0, 0, screenSize.width, screenSize.height);
   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   setLayout(null); 
   getContentPane().setBackground(new Color(152,251,152));
   setBackground(new Color(255,69,0));
   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
   Border border = BorderFactory.createLineBorder(Color.white, 1);
 	ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png");
 	Image image = imageIcon.getImage(); 
 	Image newimg = image.getScaledInstance(40, 50,  java.awt.Image.SCALE_SMOOTH);
 	imageIcon = new ImageIcon(newimg);  
   back_btn = new JButton(imageIcon);
   back_btn.setBounds(40,30,30,30);
   back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   back_btn.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
   dispose();
   new LoginPage().setVisible(true);
	}        	
   });
	back_btn.setBackground(Color.white);
	add(back_btn);
   l1 = new JLabel("Create Your Account"); 
   l1.setForeground(Color.red);  
   l1.setFont(new Font("Serif", Font.BOLD, 30));       
   l2 = new JLabel("FirstName "); 
   l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l3 = new JLabel("Second Name  ");  
   l3.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l4 = new JLabel("ID ");  
   l4.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l5 = new JLabel("Password "); 
   l5.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l6 = new JLabel("Email ID "); 
   l6.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l7 = new JLabel("Phone No ");  
   l7.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l8 = new JLabel("Alternate Phone No ");
   l8.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l9 = new JLabel("Address  ");
   l9.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   l10 = new JLabel("Gender ");
   l10.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
   String s1[] = {"Choose","female","male"};
   c1 = new JComboBox(s1);
   c1.setBounds(500,439,80,20);
   c1.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent event) {
   if (event.getStateChange() == ItemEvent.SELECTED) {
   try{
   String gender=c1.getSelectedItem().toString();
   tf8.setText(gender);
   tf8.setEditable(false);
   }
   catch (Exception ex) {
   ex.printStackTrace();
   }
   }
   }
   });
   add(c1);  
   tf1 = new JTextField();
   tf1.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf2 = new JTextField();
   tf2.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf3 = new JTextField();
   tf3.setFont(new Font("Serif", Font.PLAIN, 17)); 
   p1 = new JPasswordField();
   p1.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf4 = new JTextField(); 
   tf4.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf5 = new JTextField();  
   tf5.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf6 = new JTextField(); 
   tf6.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf7 = new JTextField(); 
   tf7.setFont(new Font("Serif", Font.PLAIN, 17)); 
   tf8 = new JTextField(); 
   tf8.setFont(new Font("Serif", Font.PLAIN, 17)); 
   create = new JButton("Create");
   create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   create.setBackground(new Color(255,196,12)); 
   clear = new JButton("Clear");  
   clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   clear.setBackground(new Color(255,196,12));
   create.addActionListener(this);  
   clear.addActionListener(this);  
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
   tf1.setBounds(600, 100, 200, 30);  
   tf2.setBounds(600, 140, 200, 30); 
   tf3.setBounds(600,190,200,30);    
   p1.setBounds(600, 230, 200, 30);      
   tf4.setBounds(600,270,200,30);     
   tf5.setBounds(600, 320, 200, 30); 
   tf6.setBounds(600,360,200,30);
   tf7.setBounds(600,400,200,30); 
   tf8.setBounds(600,440,200,30);
   create.setBounds(450, 490, 100, 30);  
   clear.setBounds(580, 490, 100, 30);  
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
   add(create);  
   add(clear);  
   setVisible(true);
   }
	@Override
	public void actionPerformed(ActionEvent e){
	try{  
	Class.forName("com.mysql.cj.jdbc.Driver");  
	Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
	Statement stmt=con.createStatement();  
	if(e.getSource()==create) {
   String first_name = tf1.getText();
   String second_name = tf2.getText();
	int id = 0;
	String d_id = tf3.getText().toString();
	if(!d_id.isEmpty() && d_id.matches("[0-9]+")) id = Integer.parseInt(tf3.getText());	
	char[] e1 = p1.getPassword();
	String password = new String(e1);
	String email = tf4.getText();
	String phone_no = tf5.getText(); 
	String alternate_phone = tf6.getText();
	String address = tf7.getText();
	String gender =  tf8.getText();
   String sql = "select	*	from customer where customer_id = "+id+"";
	ResultSet rs = stmt.executeQuery(sql);
	String choose = "Choose";
	if(rs.next())						 JOptionPane.showMessageDialog(this,"UserId already exists","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(first_name.isEmpty())  JOptionPane.showMessageDialog(this,"Please Enter First Name","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(d_id.isEmpty())        JOptionPane.showMessageDialog(this,"Please Enter UserID","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(d_id.length()!=4) 		 JOptionPane.showMessageDialog(this,"UserId should be of length 4","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(!d_id.matches("[0-9]+"))    JOptionPane.showMessageDialog(this,"Id should not contain characters ","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(password.isEmpty())    JOptionPane.showMessageDialog(this,"Enter Password ","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(email.isEmpty())       JOptionPane.showMessageDialog(this,"Please Enter Email ","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(phone_no.isEmpty())    JOptionPane.showMessageDialog(this,"Please Enter Phone Number","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(phone_no.length()!=10) JOptionPane.showMessageDialog(this,"Phone Number should be of length 10","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(phone_no.isEmpty())    JOptionPane.showMessageDialog(this,"Please Enter Phone Number","Warining message",JOptionPane.WARNING_MESSAGE);
   else if(address.isEmpty())     JOptionPane.showMessageDialog(this,"Please Enter Address","Warining message",JOptionPane.WARNING_MESSAGE);
   else if(gender.isEmpty())      JOptionPane.showMessageDialog(this,"Gender should not be NULL","Warining message",JOptionPane.WARNING_MESSAGE);
	else if(gender.equals(choose))        JOptionPane.showMessageDialog(this,"Gender should not be ChooseL","Warining message",JOptionPane.WARNING_MESSAGE);
	else {
	String sql1 = "insert into customer values('"+first_name+"', '"+second_name+"' ,"+id+",'"+password+"','"+email+"','"+phone_no+"','"+alternate_phone+"','"+address+"','"+gender+"' ) ";
	stmt.executeUpdate(sql1);
	JOptionPane.showMessageDialog(this, "Account Created");
	dispose();
	new LoginPage().setVisible(true);
	}	
	}
	else if(e.getSource()==clear) {
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
