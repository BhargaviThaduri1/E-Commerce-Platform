package ECOMMERCE;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class ShowStatusProducts{
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
	String[] columnNames = {"  ProductID", "ProdName", "prodPrice", "ProdType","prodQuantity","QuantitySold"};

	public void showTableData()
	{
	frame1 = new JFrame("Status of Products");
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
	}};
	model.setColumnIdentifiers(columnNames);
	table = new JTable();
	table.setModel(model);
	table.setBounds(40,40,300,250);
	frame1.add(table);
	table.setFillsViewportHeight(true);
	JScrollPane scroll = new JScrollPane(table);
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	String col2= "";
	int col1= 0;
	double col3 = 0;
	String col4 = "";
	int i =0;
	int col5 = 0;
	int col6 = 0;
	try
	{ 
		Class.forName(driverName); 
		Connection con = DriverManager.getConnection(url, userName, password);
		String sql = "select * from product where seller_id = "+Global.SID+" and available = 'Y'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				col1 = rs.getInt("prod_id");
				col2 = rs.getString("prod_name");
				col3 = rs.getDouble("prod_price");
				col4 = rs.getString("prod_type"); 
				col5 = rs.getInt("prod_quantity");
				col6 = rs.getInt("quantitySold");
				model.addRow(new Object[]{col1, col2, col3, col4,col5,col6});
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
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   frame1.setBounds(0, 0, screenSize.width, screenSize.height);
	frame1.add(scroll);
	frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame1.setResizable(false);
	frame1.setVisible(true);
	}




 
}










