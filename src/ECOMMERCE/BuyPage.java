package ECOMMERCE;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class BuyPage implements ActionListener {
	JButton con;
	JFrame f;
	JButton order, back_btn;
	JCheckBox c1, c2, c3;
	String payment_through = " ";

	BuyPage() {

		f = new JFrame("buy");
		f.setVisible(true);
		String cname = " ", caddress = " ", cphoneno = " ", clastname = " ";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
					"rootpassword");
			Statement stmt = con.createStatement();
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet rs = stmt.executeQuery(
					"select customer_first_name,customer_last_name,customer_address,customer_phoneno from customer where customer_id = "
							+ Global.ID + "");
			if (rs.next()) {
				cname = rs.getString(1);
				clastname = rs.getString(2);
				caddress = rs.getString(3);
				cphoneno = rs.getString(4);
			}
			f.getContentPane().setBackground(Color.white);
			f.setBounds(600, 200, 500, 550);
			f.setLayout(null);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Border border = BorderFactory.createLineBorder(Color.white, 1);
			ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(40, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			imageIcon = new ImageIcon(newimg);

			back_btn = new JButton(imageIcon);
			back_btn.setBounds(40, 30, 30, 30);
			back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			back_btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					new CustomerCart();
				}
			});
			back_btn.setBackground(Color.white);
			f.add(back_btn);
			JLabel name = new JLabel();
			name.setText(cname + " " + clastname);
			name.setBounds(90, 40, 600, 30);
			name.setForeground(Color.black);
			name.setFont(new Font("Arial ", Font.BOLD, 20));
			f.add(name);

			JLabel phone = new JLabel();
			phone.setText(cphoneno);
			phone.setBounds(100, 65, 100, 30);
			f.add(phone);

			JLabel address = new JLabel();
			address.setBounds(100, 80, 600, 30);
			address.setText(caddress);
			f.add(address);

			JLabel state = new JLabel();
			state.setBounds(100, 90, 400, 40);
			state.setText("India");
			f.add(state);

			JButton deliver = new JButton("Deliver to the address");

			deliver.setBounds(80, 150, 200, 30);
			deliver.setForeground(Color.white);
			deliver.setBackground(new Color(255, 196, 12));
			f.add(deliver);

			JButton edit_btn = new JButton("Edit");
			edit_btn.setBounds(80, 190, 70, 30);
			edit_btn.setBackground(Color.lightGray);
			f.add(edit_btn);
			edit_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					f.repaint();
					JLabel add = new JLabel("Enter your new Address ");
					add.setBounds(80, 230, 200, 30);
					f.add(add);
					JTextField tf1 = new JTextField();
					tf1.setBounds(220, 230, 200, 30);
					f.add(tf1);
					JButton save = new JButton("Save");
					save.setBounds(150, 280, 100, 30);
					save.setBackground(new Color(255, 196, 12));
					f.add(save);
					save.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							add.setEnabled(false);
							tf1.setEnabled(false);
							save.setEnabled(false);
							f.dispose();

							String newAdd = tf1.getText();

							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection con = (Connection) DriverManager
										.getConnection("jdbc:mysql://localhost:3306/project1", "root", "rootpassword");
								Statement stmt = con.createStatement();
								if (!newAdd.isEmpty()) {
									stmt.executeUpdate("update customer set customer_address = '" + newAdd
											+ "' where customer_id = " + Global.ID + "");
								}
								new BuyPage();

							} catch (Exception ee) {
							}

						}

					});
				}
			});

			deliver.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					JFrame frame = new JFrame();
					frame.setBounds(600, 200, 500, 550);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setLayout(null);
					Border border = BorderFactory.createLineBorder(Color.white, 1);
					ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a
																															// imageIcon
					Image image = imageIcon.getImage(); // transform it
					Image newimg = image.getScaledInstance(40, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					imageIcon = new ImageIcon(newimg);

					back_btn = new JButton(imageIcon);
					back_btn.setBounds(40, 30, 30, 30);
					back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					back_btn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							f.setVisible(true);
						}

					});
					back_btn.setBackground(Color.white);
					frame.add(back_btn);

					frame.setTitle("Payment Method");
					frame.getContentPane().setBackground(Color.white);
					ButtonGroup bgroup = new ButtonGroup();
					JLabel heading = new JLabel();
					heading.setText("PAYMENT DETAILS");
					heading.setBounds(35, 55, 200, 30);
					heading.setForeground(Color.red);
					heading.setFont(new Font("Serif", Font.BOLD, 20));
					frame.add(heading);

					c1 = new JCheckBox("Pay on delivary");
					c1.setBounds(50, 100, 300, 30);
					Font newCheckBoxFont = new Font(c1.getFont().getName(), c1.getFont().getStyle(), 22);
					c1.setFont(newCheckBoxFont);
					bgroup.add(c1);
					frame.add(c1);
					c2 = new JCheckBox("Netbanking");
					String s[] = { "Choose an Option", "Axis Bank", "Bank of India", "Kotak Bank" };
					JComboBox combo = new JComboBox(s);
					combo.setBounds(80, 190, 140, 30);
					frame.add(combo);

					c2.setBounds(50, 150, 300, 30);
					c2.setFont(newCheckBoxFont);
					bgroup.add(c2);
					frame.add(c2);

					c3 = new JCheckBox("UP!/Netbanking");
					c3.setBounds(50, 230, 300, 30);
					c3.setFont(newCheckBoxFont);
					bgroup.add(c3);
					frame.add(c3);

					order = new JButton("ORDER");
					order.setBackground(new Color(255, 196, 12));
					order.setBounds(90, 280, 120, 40);
					frame.add(order);
					order.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection con = (Connection) DriverManager
										.getConnection("jdbc:mysql://localhost:3306/project1", "root", "rootpassword");
								Statement stmt = con.createStatement();
								DatabaseMetaData dbm = con.getMetaData();
								ResultSet tables = dbm.getTables(null, null, "Orders", null);
								if (!tables.next()) {

									String sql = "create table Orders" + "(customer_id int ," + " prod_id int,"
											+ "prod_name varchar(30)," + "prod_quantity varchar(50),"
											+ "prod_price double NOT NULL," + " o_time timestamp NOT NULL)";
									stmt.executeUpdate(sql);
									String sql1 = "alter table Orders add primary key(customer_id,prod_id,o_time)";
									stmt.executeUpdate(sql1);
									stmt.executeUpdate(
											"alter table  Orders ADD CONSTRAINT order_prodID FOREIGN KEY(prod_id) REFERENCES product(prod_id)");
								}

							} catch (Exception ee) {
							}
							;

							frame.getContentPane().removeAll();
							frame.repaint();
							frame.getContentPane().setBackground(Color.white);

							frame.setBounds(600, 200, 500, 550);
							frame.setLayout(null);
							JLabel l = new JLabel("Your Order Has been Placed");
							l.setFont(new Font("Serif", Font.BOLD, 15));
							l.setBounds(120, 30, 300, 40);
							frame.add(l);
							JLabel l2 = new JLabel("Thanks For Shopping With Us...");
							l2.setFont(new Font("Serif", Font.BOLD, 15));
							l2.setBounds(120, 90, 300, 40);
							frame.add(l2);
							frame.setVisible(true);

							back_btn.setBounds(40, 30, 30, 30);
							back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							back_btn.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									frame.dispose();

								}

							});
							back_btn.setBackground(Color.white);
							JButton exit = new JButton("EXIT");
							exit.setBounds(160, 200, 100, 30);
							exit.setBackground(new Color(255, 196, 12));
							exit.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									frame.dispose();

								}

							});
							frame.add(exit);
							frame.add(back_btn);

							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection con = (Connection) DriverManager
										.getConnection("jdbc:mysql://localhost:3306/project1", "root", "rootpassword");
								Statement stmt = con.createStatement();
								DatabaseMetaData dbm = con.getMetaData();
								ResultSet rs1 = stmt
										.executeQuery("select count(*) from cart where customer_id = " + Global.ID + "");
								int count = 0;
								if (rs1.next()) {
									count = rs1.getInt(1);
								}

								int[] soldQuantity = new int[count];
								String sql2 = "select quantitySold from product where prod_id in(select prod_id from cart where customer_id = "
										+ Global.ID + ")";

								ResultSet rs2 = stmt.executeQuery(sql2);

								int i = 0;
								while (rs2.next() && i < count) {
									soldQuantity[i] = rs2.getInt(1);
									i++;
								}

								int[] available = new int[count];
								String[] prod_names = new String[count];
								int[] prod_price = new int[count];
								ResultSet rs5 = stmt.executeQuery(
										"select prod_quantity,prod_name,prod_price from product where prod_id in(select prod_id from cart where customer_id ="
												+ Global.ID + ")");

								i = 0;
								while (rs5.next() && i < count) {
									available[i] = rs5.getInt(1);
									prod_names[i] = rs5.getString(2);
									prod_price[i] = rs5.getInt(3);
									i++;
								}

								ResultSet rs3 = stmt.executeQuery(
										"select prod_quantity from cart where customer_id = " + Global.ID + " order by prod_id");
								int[] prod_quantity = new int[count];
								i = 0;
								while (rs3.next() && i < count) {
									prod_quantity[i] = rs3.getInt(1);
									i++;
								}
								ResultSet rs = stmt.executeQuery(
										"select prod_id from cart  where customer_id = " + Global.ID + " order by prod_id");
								i = 0;
								int[] prod_id = new int[count];
								while (rs.next() && i < count) {
									prod_id[i] = rs.getInt(1);
									i++;
								}
								for (int i1 = 0; i1 < count; i1++) {
									soldQuantity[i1] = soldQuantity[i1] + prod_quantity[i1];
									available[i1] = (available[i1] - prod_quantity[i1]);

								}

								String[] times = new String[count];
								for (int i1 = 0; i1 < count; i1++) {
									String sql = "update product set quantitySold = " + soldQuantity[i1] + " where prod_id = "
											+ prod_id[i1] + "";
									stmt.executeUpdate(sql);
									stmt.executeUpdate("update product set prod_quantity = " + available[i1]
											+ " where prod_id = " + prod_id[i1] + "");
									ResultSet rs4 = stmt.executeQuery("select now()");
									String s1 = null;
									if (rs4.next()) {
										s1 = rs4.getString(1);
									}

									String sql1 = "insert into Orders values(" + Global.ID + "," + prod_id[i1] + ",'"
											+ prod_names[i1] + "'," + prod_quantity[i1] + "," + prod_price[i1] + ",'" + s1 + "')";
									stmt.executeUpdate(sql1);

								}
								stmt.executeUpdate("delete from cart where customer_id = " + Global.ID + "");
							} catch (Exception ee) {
							}
						}

					});
					frame.setVisible(true);

				}

			});

		} catch (Exception e) {
		}
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (c1.isSelected()) {
			payment_through = "Pay on delivery";

		} else if (c2.isSelected()) {

		} else if (c3.isSelected()) {

		}

	}

}
