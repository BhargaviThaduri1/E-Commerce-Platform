package ECOMMERCE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Order {
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
	String[] columnNames = { "ProductID", "ProductName", "ProdQuantity", "ProductPrice", "Time" };

	public void showTableData() {
		frame1 = new JFrame("Orders");
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setLayout(new BorderLayout());
		JButton back_btn;
		Border border = BorderFactory.createLineBorder(Color.white, 1);
		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(40, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);

		back_btn = new JButton(imageIcon);
		back_btn.setBounds(15, 5, 30, 30);
		back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				HOME h = new HOME();
				h.homePage();

			}
		});

		frame1.add(back_btn);

		JLabel home = new JLabel("                                                              Your Orders List");
		home.setFont(new Font("Serif", Font.PLAIN, 30));
		frame1.add(home, BorderLayout.NORTH);
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model);
		table.setBounds(40, 40, 300, 250);
		frame1.add(table);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		int col1 = 0;
		String col2 = "";
		int col3 = 0;
		double col4 = 0;
		Timestamp col5;
		try {
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(url, userName, password);
			String sql = "select * from Orders where customer_id = " + Global.ID + "";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				col1 = rs.getInt("prod_id");
				col2 = rs.getString("prod_name");
				col3 = rs.getInt("prod_quantity");
				col4 = rs.getDouble("prod_price");
				col5 = rs.getTimestamp("o_time");
				model.addRow(new Object[] { col1, col2, col3, col4, col5 });
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame1.setBounds(0, 0, screenSize.width, screenSize.height);
		frame1.add(scroll);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setResizable(false);

		frame1.setVisible(true);
	}

}