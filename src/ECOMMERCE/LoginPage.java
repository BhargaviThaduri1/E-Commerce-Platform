package ECOMMERCE;
// cd C:\Program Files\MySQL\MySQL Server 8.0\bin
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class LoginPage extends JFrame implements ActionListener{
	private JPanel panel;
	JTextField textField;
	private JPasswordField passwordField;
   private JButton login,signup;
   public LoginPage() {         
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(0, 0, screenSize.width, screenSize.height);
	setTitle("LoginPage");
   panel = new JPanel();
   panel.setBounds(0,0,screenSize.width, screenSize.height);
	panel.setLayout(null);
	try {
   Class.forName("com.mysql.cj.jdbc.Driver");  
 	Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
 	Statement stmt=con.createStatement(); 
	DatabaseMetaData dbm = con.getMetaData();
	ResultSet tables = dbm.getTables(null, null, "customer", null);
 	if (!tables.next()) {	
  	String sql = "create table customer "+" (customer_first_name varchar(50) NOT NULL, "+" customer_last_name varchar(50),"+" customer_id int(100) PRIMARY KEY, "+" customer_password varchar(50) NOT NULL,"+" customer_email varchar(30) NOT NULL,"+" customer_phoneno varchar(50) NOT NULL,"+" customer_alternate_no varchar(50) ,"+" customer_address varchar(50) NOT NULL,"+" customer_gender varchar(10) NOT NULL  )"; 
  	stmt.executeUpdate(sql);
   }
   }
   catch(Exception e) {};
	
	JLabel l1 = new JLabel("USERID  ");
	l1.setBounds(530, 200, 93, 50);
	l1.setFont(new Font("Serif", Font.PLAIN, 20));  
	panel.add(l1);
	JLabel l2 = new JLabel("PASSWORD  ");
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
	login = new JButton("Login");
	login.addActionListener(this);
	login.setFont(new Font("Serif", Font.BOLD, 20)); 
	login.setBackground(new Color(255,196,12));
	login.setBounds(610, 330, 113, 39);
	login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	panel.add(login);	
   signup = new JButton("SignUp");
   signup.setFont(new Font("Serif", Font.BOLD, 20)); 
	signup.addActionListener(this);
	
	signup.setBackground(new Color(255,196,12));
	signup.setBounds(740, 330, 113, 39);
	signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	panel.add(signup);
	ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\loginPagePicture.jpg"); 
	Image image = imageIcon.getImage(); // transform it 
	Image newimg = image.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	imageIcon = new ImageIcon(newimg); 
   JLabel l = new JLabel(imageIcon);
   l.setBounds(0,0,screenSize.width, screenSize.height);
   panel. add(l);  
   setContentPane(panel);

   
	}
   public void actionPerformed(ActionEvent ae){
   if(ae.getSource() == login){
	int count = 0;
   try {
   Class.forName("com.mysql.cj.jdbc.Driver");  
	Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
	Statement stmt=con.createStatement(); 
	DatabaseMetaData dbm = con.getMetaData();
	ResultSet tables = dbm.getTables(null, null, "customer", null);
	if (tables.next()) {	
	ResultSet rs = stmt.executeQuery("select count(*) from customer" );
	if(rs.next())  count = rs.getInt(1);		
	}
	else {
	String sql = "create table customer "+" (customer_first_name varchar(50) NOT NULL, "+" customer_last_name varchar(50),"+" customer_id int(100) PRIMARY KEY, "+" customer_password varchar(50) NOT NULL,"+" customer_email varchar(30) NOT NULL,"+" customer_phoneno varchar(50) NOT NULL,"+" customer_alternate_no varchar(50) ,"+" customer_address varchar(50) NOT NULL,"+" customer_gender varchar(10) NOT NULL  )";
	stmt.executeUpdate(sql);
	}
	if(count>=1) {
   String name = textField.getText();
	int id = Integer.parseInt(name);
	char[] a = passwordField.getPassword();
	String p = new String(a);
	if(p!=" " && name!=" ") {
	String sql1 = "select * from customer where customer_id = "+id+" and customer_password ='"+p+"'"; 
	ResultSet rs1 = stmt.executeQuery(sql1);
	if(rs1.next()) {
	Global.ID=rs1.getInt("customer_id");
	Global.CName = rs1.getString("customer_first_name");
	dispose();
	HOME s = new HOME();
	s.f.setVisible(true);
	s.homePage();
	}
	else JOptionPane.showMessageDialog(this, "Invalid Login","Warning Message",JOptionPane.WARNING_MESSAGE);
	}
	else JOptionPane.showMessageDialog(this, "Invalid Login","Warning Message",JOptionPane.WARNING_MESSAGE);
	} 
	else JOptionPane.showMessageDialog(this, "Your Entry Doesn't exits..Please Signup","Warning Message",JOptionPane.WARNING_MESSAGE);	
   }
   catch (Exception e2) {
   e2.printStackTrace();
   }    
   }
   else  if(ae.getSource()==signup) {
   dispose();             
   new CustomerSignUp().setVisible(true);
   }
  }
  public static void main(String[] args) {
  new LoginPage().setVisible(true);
  }

}