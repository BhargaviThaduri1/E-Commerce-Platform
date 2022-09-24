package ECOMMERCE;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateProducts{
JFrame frame, frame1;
JTextField textbox;
JLabel label;
JButton button;
JPanel panel;
static JTable table;

String driverName = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/project1";
String userName = "root";
String password = "rootpassword";
String[] columnNames = {"ProductName", "ProductID", "ProdQuantity", "ProdAge","ProductPrice"};
String prod_n,s_q,s_age,s_price=" ";
int quantity,age;
Double price;

public void showTableData()
{

	frame1 = new JFrame("Update Products");
	frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame1.setLayout(new BorderLayout()); 
	JButton back_btn;
	 Border border = BorderFactory.createLineBorder(Color.white, 1);
		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(40, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  
  
  back_btn = new JButton(imageIcon);
  back_btn.setBounds(15,5,30,30);
  back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  back_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				SellerOperations s = new SellerOperations();
				s.f.setVisible(true);
				
			}});
  
			
	frame1.add(back_btn);
	
	JLabel home = new JLabel("                                                                        Products List");
	home.setFont(new Font("Serif", Font.PLAIN, 30)); 
	frame1.add(home,BorderLayout.NORTH);
	DefaultTableModel model = new DefaultTableModel() {@Override
		    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	}
	    };
	model.setColumnIdentifiers(columnNames);

	table = new JTable();
	table.setModel(model); 
	table.setBounds(40,40,300,250);
	frame1.add(table);
	table.setFillsViewportHeight(true);
	JScrollPane scroll = new JScrollPane(table);
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

	String col1= "";
	int col2= 0;
	int col3 = 0;
	int col4 = 0;
	int i =0;
	double col5 = 0;
	try
	{ 
		Class.forName(driverName); 
		Connection con = DriverManager.getConnection(url, userName, password);
		  String sql  = ("select* from product where seller_id = "+Global.SID+" and available = 'Y'" );
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				col1 = rs.getString("prod_name");
				col2 = rs.getInt("prod_id");
				col3 = rs.getInt("prod_quantity");
				col4 = rs.getInt("prod_age"); 
				col5 = rs.getDouble("prod_price");
				model.addRow(new Object[]{col1, col2, col3, col4,col5});
				i++; 
			}
	}
	catch(Exception ex)
	{
		JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
		JOptionPane.ERROR_MESSAGE);
	}
	if(i <1)
	{
			JOptionPane.showMessageDialog(null, "No Products Found","Error",
			JOptionPane.ERROR_MESSAGE);
			frame1.dispose();
			frame1.setVisible(true);
			exit(0);
			
	}
	else
	{
		JButton next = new JButton("NEXT");
		next.setBounds(180,450,100,30);
		next.setBackground(new Color(255,196,12));
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JButton update = new JButton("UPDATE");
		update.setBackground(new Color(255,196,12));
		update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				 prod_n =(String) JOptionPane.showInputDialog("Enter name" );
				 s_q = JOptionPane.showInputDialog("Enter product Quantity");
				 s_age = JOptionPane.showInputDialog("Enter product Age");
				 s_price = JOptionPane.showInputDialog("Enter product Price"); 
		if(!s_price.isEmpty() && s_price.matches("[0-9]+")) price = Double.parseDouble(s_price);
      if(!s_q.isEmpty() && s_q.matches("[0-9]+")) quantity = Integer.parseInt(s_q);	
      if(!s_age.isEmpty() && s_age.matches("[0-9]+")) age = Integer.parseInt(s_age);
			}
			
		});
		
	update.setBounds(330,450,100,30);
	update.addActionListener(null);
	frame1.add(update);	
	JLabel l = new JLabel(" ENTER PRODUCT ID ");
	l.setBounds(50,350,150,30);
	JTextField textField = new JTextField();
	textField.setBounds(200,350,200,30);
	frame1.add(textField);
	update.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			try{  
 				Class.forName("com.mysql.cj.jdbc.Driver");  
 				Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
 				Statement stmt=con.createStatement();  
 				String id = textField.getText();
 				int prod_id  = 0;
 				 if(!id.isEmpty() && id.matches("[0-9]+")) prod_id = Integer.parseInt(id);
 				ResultSet rs = stmt.executeQuery("select*from product where prod_id = "+prod_id+" and available = 'Y'");
 				if(!id.matches("[0-9]+")) {   JOptionPane.showMessageDialog(null, "Product name should not contain characters");
 				textField.setText("");
	
				 }
				
				 if(!rs.next()) {
 	 				JOptionPane.showMessageDialog(null, "No product available with product id "+prod_id );
 				}
		else if(s_q.isEmpty())						  JOptionPane.showMessageDialog(null, "Please Enter product quantity");
		else if(!s_q.matches("[0-9]+")) 	JOptionPane.showMessageDialog(null, "Quantity should not contain characters");
		else if(s_age.isEmpty())         JOptionPane.showMessageDialog(null, "Please Enter product age");
		else if(!s_age.matches("[0-9]+")) 	JOptionPane.showMessageDialog(null, "Age should not contain characters");
		else if(s_price.isEmpty())			  JOptionPane.showMessageDialog(null, "Please Enter price");
		else if(!s_price.matches("[0-9]+")) 	JOptionPane.showMessageDialog(null, "Price should not contain characters");
 		else {
 		stmt.executeUpdate("update product set prod_name = '"+prod_n+"',prod_quantity="+quantity+",prod_age = "+age+",prod_price = "+price+" where prod_id = "+prod_id+" and seller_id = "+Global.SID+" ");
 		frame1.dispose();
 		textField.setText("");
		UpdateProducts X = new UpdateProducts();
 		X.showTableData();
 		}	
	}
	catch(Exception ee) {
 				
 			}
		}});

	frame1.add(next);
	frame1.add(l);
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 frame1.setBounds(0, 0, screenSize.width, screenSize.height);
	 frame1.setResizable(false);
	frame1.add(scroll);
	frame1.setVisible(true);
	}

}

private void exit(int i) {
	// TODO Auto-generated method stub
	
}


	



}