package ECOMMERCE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

class DisplayProducts{
	
	DisplayProducts(){
		
		
	}
	public JPanel getProudctsPanel(String prodType,String gender) {
	      JPanel panel = new JPanel();
	      panel.setBounds(0,0,1536,864);
	      panel.setLayout(new GridLayout(8,3,4,4));
	      try {
	  		Class.forName("com.mysql.cj.jdbc.Driver");
	  		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",	"rootpassword");
	  		Statement stmt = con.createStatement();
	  		DatabaseMetaData dbm = con.getMetaData();
	  		ResultSet tables = dbm.getTables(null, null, "product", null);
	  		ResultSet rs1;
	  		ResultSet rs2 = null;

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
			  if(gender.equals("")) {
				 rs1 = stmt.executeQuery("select count(prod_name) from product  where prod_name like '" + ("" +prodType + "%")+"' or  prod_name like '" + ("%" +prodType + "%")+"'  ");
			 }
			 else rs1 = stmt.executeQuery("select count(*) from product where prod_type = '"+prodType+"' and gender = '"+gender+"' ");
			 int count = 0;
			
					if (rs1.next()) {
						count = rs1.getInt(1);
					}
					
					  if(gender.equals(""))  rs2 = stmt.executeQuery("select * from product where prod_name like '" + ("%" +prodType + "%")+"'  ");
					  else rs2 = stmt.executeQuery("select*from product where prod_type = '"+prodType+"' and gender = '"+gender+"' and available='Y'");
					  JPanel p =null;
					  
					  
					if(count>0) {
					
					 for(int i=0;i<count;i++) { //starting of for loop
						p = new JPanel();
						p.setLayout( new BorderLayout( ));
					 
					 	 p.setBackground(Color.pink);
						 String prod_type = " ";
						 String prod_gender = " ";
						 BufferedImage myPicture = null;
						 
						   if (rs2.next()) {
						
							int product_id = rs2.getInt(2);
							Global.PID = rs2.getInt(2);
							JLabel l1 = new JLabel("NAME- " + rs2.getString(1));
					
							l1.setBounds(60, 40, 400, 30);
					   		l1.setFont(new Font("Verdana", Font.PLAIN, 23));
						    p.add(l1,BorderLayout.LINE_START);
						  //  System.out.println(rs2.getString(1));
						    
						   

							JLabel l2 = new JLabel("PRICE- " + String.valueOf(rs2.getInt(7))+"/-");
							l2.setFont(new Font("Verdana", Font.PLAIN, 23));
							l2.setBounds(60,90,400,30);
							p.add(l2,BorderLayout.LINE_START);  
							

							prod_type = rs2.getString(4);
							prod_gender = rs2.getString(8);	
							

							JLabel l3 = new JLabel("          Type- "+prod_type);
							l3.setFont(new Font("Verdana", Font.PLAIN, 18));
							l3.setBounds(60,80,200,30);
							p.add(l3,BorderLayout.LINE_START);  
							
							
							
							 JButton add_to_cart = new JButton("Add to cart");
							 p.add(add_to_cart,BorderLayout.WEST);
							
							
							 add_to_cart.setBackground(new Color(255,196,12));
							 add_to_cart.setBounds(50,180,150,40);
							 
							 
							 
//							 JButton add_to_wishlist = new JButton("Add to wishlist");
//							 p.add(add_to_wishlist,BorderLayout.WEST);
//							 add_to_wishlist.setBackground(new Color(255,196,12));
//							 add_to_wishlist.setBounds(230,180,150,40);
							 
							 if (prod_type.equals("Indian Wear") && prod_gender.equals("Female")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\Top1.jpg"));
							 } else if (prod_type.equals("Indian Wear") && prod_gender.equals("Male")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\KurtaMen.jpg"));
							 } else if (prod_type.equals("Western Wear") && prod_gender.equals("Female")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\WesternWear.jpg"));
							 } else if (prod_type.equals("Western Wear") && prod_gender.equals("Male")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\WesternMen.jpg"));
							 } else if (prod_type.equals("Sports") && prod_gender.equals("Female")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\Sports.jpg"));
							 } else if (prod_type.equals("Sports") && prod_gender.equals("Male")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\SportsMen.jpg"));
							 } else if (prod_type.equals("Plus Size") && prod_gender.equals("Female")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\PlusSize.jpg"));
							 } else if (prod_type.equals("Plus Size") && prod_gender.equals("Male")) {
								 myPicture = ImageIO.read(new File("C:\\Users\\lokesh\\PlusSizeMen.jpg"));
							 }
							 Image newImage = myPicture.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
							
						
							JLabel picLabel = new JLabel(new ImageIcon(newImage));
						
						   	p.add(picLabel,BorderLayout.LINE_END);
						   	
						 
						  
						 
						  add_to_cart.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									try {
										Class.forName("com.mysql.cj.jdbc.Driver");
										Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root", "rootpassword");
										Statement stmt = con.createStatement();
										DatabaseMetaData dbm = con.getMetaData();
										
											ResultSet rs = stmt.executeQuery("select count(*) from cart");
											int count = 0;
											while (rs.next()) {
												count = rs.getInt(1);
											}

											if (count >= 0) {
												int prod_quantity = 0;
												ResultSet rs2 = stmt.executeQuery("select prod_quantity from product where prod_id = "	+ product_id + "");

												if (rs2.next()) {
													prod_quantity = rs2.getInt(1);
												}
//				        						checking if the product already exists in the cart
//				        					   if exists then we increment quantity
												ResultSet rs3 = stmt.executeQuery(
														"select*from cart where prod_id = " + product_id
																+ " and customer_id = " + Global.ID + "");

												if (rs3.next()) {
													int quantity = rs3.getInt(2);
													int reply = JOptionPane.showConfirmDialog(null,
															"Product already exists in cart.Would you like to increase the quantity? ",
															"delete", JOptionPane.YES_NO_OPTION);
													if (reply == JOptionPane.YES_OPTION) {
														if (quantity < prod_quantity) {
															quantity += 1;
															stmt.executeUpdate(
																	"update cart set prod_quantity = "
																			+ (quantity) + "  where prod_id = "
																			+ product_id + " and  customer_id = "+Global.ID+" ");

														} else {
															JOptionPane.showMessageDialog(null, "Out of stock");
														}
													} 

												}

												else {
													int i = 0;
													if (prod_quantity > 0) {
														String sql = "INSERT INTO cart(prod_id,prod_quantity,customer_id) VALUES ("
																+ product_id + "," + (i + 1) + "," + Global.ID
																+ " )";
														i++;
														prod_quantity--;
														stmt.executeUpdate(sql);
														JOptionPane.showMessageDialog(null, "product added");
													} else {
														JOptionPane.showMessageDialog(null, "OUT OF STOCK");
													}
												}
											}
									}

									
									

									catch (Exception ee) {
										System.out.println(ee);
									}
								
								}
							}); 	
						  
					     
					 }
					
					 panel.add(p);
					} //ending of for loop
					}
					else {
						
						JLabel l = new JLabel("NO PRODUCTS FOUND ");
						l.setBounds(60, 30, 500, 30);
						l.setForeground(Color.red);
						l.setFont(new Font("Serif", Font.BOLD, 30));
						panel.add(l);

//						JLabel l1 = new JLabel("Wanted to be a seller? THEN SIGNUP AS A SELLER ");
//						l1.setBounds(110, 80, 1000, 30);
//						l1.setForeground(Color.orange);
//						l1.setFont(new Font("Serif", Font.BOLD, 30));
//						panel.add(l1);
						
						
					}
			}
			
				   
	      }catch(Exception e) {}
	      return panel;
	   }
		
	  
	}
		  


	      