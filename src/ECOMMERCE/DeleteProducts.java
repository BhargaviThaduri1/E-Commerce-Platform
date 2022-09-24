package ECOMMERCE;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class DeleteProducts{
JFrame frame1;
JTextField textbox;
JLabel label;
JButton button;
JPanel panel;
static JTable table;

String driverName = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/project1";
String userName = "root";
String password = "rootpassword";
String[] columnNames = {"ProductName", "ProductID", "ProdQuantity", "PRodutType","ProductPrice"};

public void showTableData()
{

	frame1 = new JFrame("Delete Products");
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
	
	JLabel home = new JLabel("                                                         Products List");
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
	String col4 = "";
	int i =0;
	double col5 = 0;
	try
	{ 
		Class.forName(driverName); 
		Connection con = DriverManager.getConnection(url, userName, password);
		String sql = "select * from product where seller_id = "+Global.SID+" and available = 'Y'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				col1 = rs.getString("prod_name");
				col2 = rs.getInt("prod_id");
				col3 = rs.getInt("prod_quantity");
				col4 = rs.getString("prod_type"); 
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
			SellerOperations s = new SellerOperations();
			s.f.setVisible(true);
			
	}
	else
	{
   JButton delete = new JButton("DELETE");
   delete.setBackground(new Color(255,196,12));
	JLabel l = new JLabel(" ENTER PRODUCT ID: ");
	l.setBounds(50,300,150,30);
	JTextField textField = new JTextField();
	textField.setBounds(200,300,200,30);
	frame1.add(textField);
	delete.setBounds(180,350,100,30);
	delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	delete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame1.dispose();
			int prod_id = 0;
			String id = textField.getText().toString();
			if(!id.isEmpty() && id.matches("[0-9]+")) prod_id = Integer.parseInt(id);	
			
			 
			 
		  try {
				  Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
					Statement stmt=con.createStatement();  
					String sql1 = "select*from product where prod_id = "+prod_id+" and available = 'Y'";
					ResultSet rs = stmt.executeQuery(sql1);
					
					
					 if(!id.matches("[0-9]+")) {   JOptionPane.showMessageDialog(null, "Product name should not contain characters");
					  textField.setText("");
						DeleteProducts d  = new DeleteProducts();
						d.showTableData();
					 }
					
					
					 else	 if(rs.next()) {
						stmt.executeUpdate("update product set available = 'N' where prod_id = "+prod_id+"");
						JOptionPane.showMessageDialog(null, "Product Deleted");
						DeleteProducts d  = new DeleteProducts();
						d.showTableData();
						frame1.dispose();
						
					}
					else {
						
						 JOptionPane.showMessageDialog(null, "ProductId doesn't exits");
						 DeleteProducts d = new DeleteProducts();
						 d.showTableData();
					
					}
					
			  }
			  catch(Exception ee) {System.out.println(ee);}
			
		}
		
	}
	);
	
	frame1.add(delete);
	frame1.add(l);
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   frame1.setBounds(0, 0, screenSize.width, screenSize.height);
	frame1.add(scroll);
	frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame1.setResizable(false);
	
	frame1.setVisible(true);
	}

 }


}