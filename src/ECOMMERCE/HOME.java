package ECOMMERCE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HOME {
	JFrame f;
	JMenu cate, account, search_menu;
	JTextField tf1;
	JButton home_btn, search_btn, seller_signup_btn, cart_btn;
	JMenuItem your_account, your_wishlist, your_orders, sign_up, your_cart;
	JMenuItem western_wear_women, indian_wear_women, sports_women, plusSize_women;
	JMenuItem western_wear_men, indian_wear_men, sports_men, plusSize_men, log_out;
	JButton add_to_cart, add_to_wishList;
	String gender_of_product, prodType;
	JPanel panel;
	JLabel deliver_to;

	public HOME() {
		f = new JFrame();
	}

	void homePage() {
		JMenuBar mb = new JMenuBar();
		mb.setBackground(Color.white);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
					"rootpassword");
			Statement stmt = con.createStatement();
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables1 = dbm.getTables(null, null, "cart", null);
			if (!tables1.next()) {
				String sql = "create table cart " + " (prod_id int , " + " prod_quantity int ,"
						+ "customer_id  int NOT NULL )";
				stmt.executeUpdate(sql);
				stmt.executeUpdate("ALTER TABLE cart add primary key(prod_id, customer_id)");
				stmt.executeUpdate(
						"Alter table cart ADD CONSTRAINT cart_productid FOREIGN KEY(prod_id) REFERENCES product(prod_id)");
				stmt.executeUpdate(
						"Alter table cart ADD CONSTRAINT cart_customerID FOREIGN KEY(customer_id) REFERENCES customer(customer_id)");
			}
		} catch (Exception e) {
		}
		;
		f.setTitle("Home Page");
		f.setVisible(true);
		home_btn = new JButton("Home");
		home_btn.setBackground(Color.white);
		home_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HOME h = new HOME();
				f.dispose();
				h.f.setVisible(true);
				h.homePage();
			}
		});
		home_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		home_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				home_btn.setBackground(Color.orange);
			}

			public void mouseExited(MouseEvent evt) {
				home_btn.setBackground(Color.white);
			}
		});
		mb.add(home_btn);
		cate = new JMenu("Categories");
		western_wear_women = new JMenuItem("Western Wear Women");
		indian_wear_women = new JMenuItem("Indian Wear Women ");
		sports_women = new JMenuItem("Sports Wear Women ");
		plusSize_women = new JMenuItem("PlusSize Women");
		western_wear_men = new JMenuItem("Western Wear Men");
		indian_wear_men = new JMenuItem("Indian Wear Men ");
		sports_men = new JMenuItem("Sports Wear Men ");
		plusSize_men = new JMenuItem("PlusSize Men");
		cate.add(western_wear_women);
		cate.add(indian_wear_women);
		cate.add(sports_women);
		cate.add(plusSize_women);
		cate.add(western_wear_men);
		cate.add(indian_wear_men);
		cate.add(sports_men);
		cate.add(plusSize_men);
		mb.add(cate);
		seller_signup_btn = new JButton("Seller_SignUp");
		seller_signup_btn.setBackground(Color.white);
		seller_signup_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new SellerPage().f.setVisible(true);
			}
		});
		seller_signup_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Changing the cursor when hovered
																												// to home button
		seller_signup_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				seller_signup_btn.setBackground(Color.ORANGE);
			}

			public void mouseExited(MouseEvent evt) {
				seller_signup_btn.setBackground(Color.white);
			}
		});
		mb.add(seller_signup_btn);
		deliver_to = new JLabel("   Deliver to  " + Global.CName + "   ");
		mb.add(deliver_to);
		JLabel search = new JLabel("Search  ");
		mb.add(search);
		tf1 = new JTextField();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		tf1.setBorder(border);
		tf1.setForeground(Color.black);
		tf1.setFont(new Font("Serif", Font.PLAIN, 15));
		tf1.setText(" Type something here");
		tf1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf1.setText("");
			}
		});
		mb.add(tf1);
		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\search.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);

		search_btn = new JButton(imageIcon);
		search_btn.setBackground(Color.white);

		search_btn.setBorder(border);

		mb.add(new JSeparator());
		mb.add(search_btn);
		account = new JMenu("Account and Lists");
		your_account = new JMenuItem("    Your Account");
		your_orders = new JMenuItem("    Your Orders");
		your_orders.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int col = 0;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
							"rootpassword");
					Statement stmt = con.createStatement();
					ResultSet rs2 = stmt.executeQuery("select count(*) from Orders");
					if (rs2.next()) {
						col = rs2.getInt(1);
					}
					if (col > 0) {
						f.dispose();
						Order o = new Order();
						o.showTableData();
					} else
						JOptionPane.showMessageDialog(null, "No Order History", "Warning Message",
								JOptionPane.WARNING_MESSAGE);
				} catch (Exception ee) {
				}
			}
		});
		account.add(your_orders);
		account.add(your_account);
		your_account.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new Account().setVisible(true);

			}
		});
		mb.add(account);
		log_out = new JMenuItem("    Logout");
		account.add(log_out);
		log_out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new LoginPage().setVisible(true);

			}

		});
		cart_btn = new JButton("Cart");
		cart_btn.setBackground(Color.white);
		cart_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cart_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cart_btn.setBackground(Color.orange);
			}

			public void mouseExited(MouseEvent evt) {
				cart_btn.setBackground(Color.white);
			}
		});

		cart_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int col = 0;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
							"rootpassword");
					Statement stmt = con.createStatement();
					DatabaseMetaData dbm = con.getMetaData();
					ResultSet rs = stmt.executeQuery("select count(*)from cart where customer_id = " + Global.ID + " ");
					if (rs.next())
						col = rs.getInt(1);
					if (col != 0) {
						new CustomerCart();
					} else
						JOptionPane.showMessageDialog(null, "No products in cart");
				} catch (Exception ee) {
					System.out.println(ee);
				}
			}
		});
		mb.add(cart_btn);
		f.setJMenuBar(mb);
		panel = createPanel();
		f.add(BorderLayout.CENTER, new JScrollPane(panel));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds(0, 0, screenSize.width, screenSize.height);
		western_wear_women.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Western Wear";
				String gender = "Female";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		western_wear_men.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Western Wear";
				String gender = "Male";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		indian_wear_women.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Indian Wear";
				String gender = "Female";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		indian_wear_men.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Indian Wear";
				String gender = "Male";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		sports_men.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Sports";
				String gender = "Male";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		sports_women.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Sports";
				String gender = "Female";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		plusSize_women.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Plus Size";
				String gender = "Female";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		plusSize_men.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = "Plus Size";
				String gender = "Male";
				f.getContentPane().removeAll();
				f.repaint();
				DisplayProducts d = new DisplayProducts();
				JPanel p1 = d.getProudctsPanel(prodType, gender);
				f.add(BorderLayout.CENTER, new JScrollPane(p1));
				f.setVisible(true);
			}
		});
		search_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prodType = tf1.getText().toString();
				String gender = "";
				if (!(prodType.equals("") || prodType.equals(" Type something here"))) {
					f.getContentPane().removeAll();
					f.repaint();
					DisplayProducts d = new DisplayProducts();
					JPanel p1 = d.getProudctsPanel(prodType, gender);
					f.add(BorderLayout.CENTER, new JScrollPane(p1));
					f.setVisible(true);
					JOptionPane.showMessageDialog(null, "Finished Loading.....");
				}
			}
		});
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1536, 864);
		panel.setLayout(new GridLayout(10, 4, 4, 4));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
					"rootpassword");
			Statement stmt = con.createStatement();
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "product", null);
			if (!tables.next()) {
				JLabel l = new JLabel("NO PRODUCTS FOUND ");
				l.setBounds(60, 30, 500, 30);
				l.setForeground(Color.red);
				l.setFont(new Font("Serif", Font.BOLD, 30));
				panel.add(l);
				JLabel l1 = new JLabel("Wanted to be a seller? THEN SIGNUP AS A SELLER ");
				l1.setBounds(110, 80, 1000, 30);
				l1.setForeground(Color.orange);
				l1.setFont(new Font("Serif", Font.BOLD, 30));
				panel.add(l1);
			} else {
				ResultSet rs1 = stmt.executeQuery("select count(*) from product where available = 'Y'");
				int count = 0;
				if (rs1.next()) {
					count = rs1.getInt(1);
				}
				ResultSet rs2 = stmt.executeQuery("select*from product where available = 'Y' ");
				JPanel p = null;
				for (int i = 0; i < count; i++) { // starting of for loop
					p = new JPanel();
					p.setLayout(new BorderLayout());
					p.setBackground(Color.pink);
					String prod_type = " ";
					String prod_gender = " ";
					BufferedImage myPicture = null;
					String name = "";
					if (rs2.next()) {
						int product_id = rs2.getInt(2);
						prod_type = rs2.getString(4);
						prod_gender = rs2.getString(8);
						Global.PID = rs2.getInt(2);
						JLabel l1 = new JLabel("NAME: \n" + rs2.getString(1));
						name = rs2.getString(1);
						l1.setBounds(60, 40, 900, 30);
						l1.setFont(new Font("Verdana", Font.PLAIN, 23));
						p.add(l1, BorderLayout.LINE_START);
						JLabel l2 = new JLabel("Price: " + String.valueOf(rs2.getInt(7)) + " /-");
						l2.setFont(new Font("Verdana", Font.PLAIN, 23));
						l2.setBounds(60, 80, 600, 30);
						p.add(l2, BorderLayout.LINE_START);
						JLabel l3 = new JLabel("          Type- " + prod_type);
						l3.setFont(new Font("Verdana", Font.PLAIN, 18));
						l3.setBounds(60, 80, 200, 30);
						p.add(l3, BorderLayout.LINE_START);
						JButton add_to_cart = new JButton("Add to cart");
						p.add(add_to_cart, BorderLayout.WEST);
						add_to_cart.setBackground(new Color(255, 196, 12));
						add_to_cart.setBounds(50, 180, 150, 40);
						if (prod_type.equals("Indian Wear") && prod_gender.equals("Female")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\Top1.jpg"));
						} else if (prod_type.equals("Indian Wear") && prod_gender.equals("Male")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\KurtaMen.jpg"));
						} else if (prod_type.equals("Western Wear") && prod_gender.equals("Female")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\WesternWear.jpg"));
						} else if (prod_type.equals("Western Wear") && prod_gender.equals("Male")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\WesternMen.jpg"));
						} else if (prod_type.equals("Sports") && prod_gender.equals("Female")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\Sports.jpg"));
						} else if (prod_type.equals("Sports") && prod_gender.equals("Male")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImagesSportsMen.jpg"));
						} else if (prod_type.equals("Plus Size") && prod_gender.equals("Female")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\PlusSize.jpg"));
						} else if (prod_type.equals("Plus Size") && prod_gender.equals("Male")) {
							myPicture = ImageIO.read(new File("C:\\EcommerceImages\\PlusSizeMen.jpg"));
						}
						Image newImage = myPicture.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
						JLabel picLabel = new JLabel(new ImageIcon(newImage));
						p.add(picLabel, BorderLayout.LINE_END);
						add_to_cart.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									Class.forName("com.mysql.cj.jdbc.Driver");
									Connection con = (Connection) DriverManager
											.getConnection("jdbc:mysql://localhost:3306/project1", "root", "rootpassword");
									Statement stmt = con.createStatement();
									DatabaseMetaData dbm = con.getMetaData();
									ResultSet rs = stmt.executeQuery("select count(*) from cart");
									int count = 0;
									while (rs.next()) {
										count = rs.getInt(1);
									}
									if (count >= 0) {
										int prod_quantity = 0;
										int i = 0;
										ResultSet rs2 = stmt.executeQuery(
												"select prod_quantity,prod_name from product where prod_id = " + product_id + "");
										String name = "";
										if (rs2.next()) {
											prod_quantity = rs2.getInt(1);
											name = rs2.getString(2);
										}
										ResultSet rs3 = stmt.executeQuery("select*from cart where prod_id = " + product_id
												+ " and customer_id = " + Global.ID + "");
										if (rs3.next()) {
											int quantity = rs3.getInt(2);
											int reply = 0;
											if (prod_quantity > 0) {
												if (quantity < prod_quantity)
													reply = JOptionPane.showConfirmDialog(null, name
															+ "  already exists in cart.Would you like to increase the quantity? ",
															"delete", JOptionPane.YES_NO_OPTION);
												else
													JOptionPane.showMessageDialog(null, "No products available");
												if (reply == JOptionPane.YES_OPTION) {
													if (quantity < prod_quantity) {
														quantity += 1;
														stmt.executeUpdate(
																"update cart set prod_quantity = " + (quantity) + "  where prod_id = "
																		+ product_id + " and  customer_id = " + Global.ID + " ");
													}
												}
											} else
												JOptionPane.showMessageDialog(null, "No products available");
										} else {
											if (prod_quantity > 0) {
												String sql = "insert into cart(prod_id,prod_quantity,customer_id) VALUES ("
														+ product_id + "," + (i + 1) + "," + Global.ID + " )";
												i++;
												prod_quantity--;
												stmt.executeUpdate(sql);
												JOptionPane.showMessageDialog(null, name + " Added to Cart");
											} else
												JOptionPane.showMessageDialog(null, "No products available");
										}
									}
								} catch (Exception ee) {
									System.out.println(ee);
								}
							}
						});
					}
					panel.add(p);
				}
			}
		} catch (Exception e) {
		}
		return panel;
	}

}