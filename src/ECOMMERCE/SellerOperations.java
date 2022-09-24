//UPDATING THE PRODUCTS....
package ECOMMERCE;
//import ECOMMERCE.updateProudct;
import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class SellerOperations  implements ActionListener{
	JButton add,delete,update,status;
	JLabel l;
	JFrame f;
	JButton back_btn;
   
	SellerOperations(){
		f = new JFrame();
		f.setTitle("Operations");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds(0, 0, screenSize.width, screenSize.height);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(null);
		Global g = new Global();
		
		 
		 Border border = BorderFactory.createLineBorder(Color.white, 1);
		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
	   Image newimg = image.getScaledInstance(40, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  
	     
	     back_btn = new JButton(imageIcon);
	     back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	     back_btn.setBounds(40,30,30,30);
        back_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new SellerPage().f.setVisible(true);
				
			}
        	
        });
		back_btn.setBackground(Color.white);
		f.add(back_btn);
		
		 l = new JLabel("YOUR ID IS "+g.SID);
		 l.setForeground(Color.black);  
	    l.setFont(new Font("Serif", Font.BOLD, 25));  
	    l.setBounds(575,125,200,30);
	   JLabel l1 = new JLabel("1.");
	   l1.setBounds(570,200,100,30);
	   l1.setFont(new Font("Serif", Font.BOLD, 27));  
	   l1.setForeground(Color.white);
	   f.add(l1);
		add = new JButton("ADD PRODUCT");
		add.setBackground(new Color(245,0,0));
		add.setForeground(Color.white);
		add.addActionListener(this);
		add.setBounds(600,200,180,40);
		add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		f.add(add);
		JLabel l2 = new JLabel("2.");
	   l2.setBounds(570,270,100,30);
	   l2.setFont(new Font("Serif", Font.BOLD, 27));  
	   l2.setForeground(Color.white);
	   f.add(l2);
	   
		delete = new JButton("DELETE PRODUCT");
		delete.addActionListener(this);
		delete.setBackground(new Color(245,0,0));
		delete.setForeground(Color.white);
		delete.setBounds(600,270,180,40);
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		f.add(delete);
		
		JLabel l3 = new JLabel("3.");
	   l3.setBounds(570,340,100,30);
	   l3.setFont(new Font("Serif", Font.BOLD, 27));  
	   l3.setForeground(Color.white);
	   f.add(l3);
		
		update = new JButton("UPDATE PRODUCT");
		update.setBackground(new Color(245,0,0));
		update.setForeground(Color.white);
		update.addActionListener(this);
		update.setBounds(600,340,180,40);
		update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		f.add(update);
		f.add(l);
		JLabel l4 = new JLabel("4.");
	   l4.setBounds(570,410,100,30);
	   l4.setFont(new Font("Serif", Font.BOLD, 27));  
	   l4.setForeground(Color.white);
	   f.add(l4);
		status = new JButton("STATUS OF PRODUCTS");
		status.setBackground(new Color(245,0,0));
		status.addActionListener(this);
		status.setForeground(Color.white);
		status.setBounds(600,410,180,40);
		status.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		f.add(status);
		 imageIcon = new ImageIcon("C:\\EcommerceImages\\sellerOperations.jpg"); 
		 image = imageIcon.getImage(); // transform it 
		 newimg = image.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg); 
	   JLabel l = new JLabel(imageIcon);
	   l.setBounds(0,0,screenSize.width, screenSize.height);
	   f.getContentPane().add(l);
		f.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		A a = new A();
		if(e.getSource()==add) {
			f.getContentPane().removeAll();
			f.repaint();
			
			a.add();
			
		}
		else if(e.getSource()==update) {
			f.dispose();
			a.update();
		}
		else if(e.getSource()==delete) {
			f.dispose();
			a.delete();
		}
		else if(e.getSource()==status) {
			f.dispose();
			ShowStatusProducts s = new ShowStatusProducts();
			s.showTableData();
		}
		
	}
	public class A {
		JLabel l1, l2, l4, l5, l6, l7,l8,l9,l10;
	    JTextField tf1, tf2,  tf4, tf5,tf6,tf7,tf8;  
	    JButton btn1, btn2,btn3,btn4;  
	    JComboBox c1,c2;
	    JButton back_btn;
	void add() {	
		
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	f.setBounds(0, 0, screenSize.width, screenSize.height);
   f.setLayout(null);  
   f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
   f.setTitle("Adding Products");  
   f.getContentPane().setBackground(new Color(245, 183, 207));
   Border border = BorderFactory.createLineBorder(Color.white, 1);
	ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
	Image image = imageIcon.getImage(); // transform it 
   Image newimg = image.getScaledInstance(40, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	imageIcon = new ImageIcon(newimg);  
   back_btn = new JButton(imageIcon);
   back_btn.setBounds(40,30,30,30);
   back_btn.addActionListener(new ActionListener() {
   @Override
	public void actionPerformed(ActionEvent e) {
	f.dispose();
	new SellerOperations().f.setVisible(true);
	}   	
   });
	back_btn.setBackground(Color.white);
	back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   back_btn.setBorder(border);
	f.add(back_btn);
	 l1 = new JLabel("Enter Following Details of The Product to be Added");  
    l1.setFont(new Font("Serif", Font.PLAIN, 20));  
	 l2 = new JLabel("PRODUCT NAME ");   
    tf2 = new JTextField();  
    tf2.setFont(new Font("Serif", Font.PLAIN, 20)); 
	 l4 = new JLabel("QUANTITY ");  
	 tf4 = new JTextField();
	 tf4.setFont(new Font("Serif", Font.PLAIN, 20)); 
	 l5 = new JLabel("TYPE  ");
	 String s1[] = {"Choose","Indian Wear","Western Wear","Sports","Plus Size"};
	 c1 = new JComboBox(s1);
	 c1.setBounds(135, 243, 100, 20);  
		        c1.addItemListener(new ItemListener()
		        {
		             public void itemStateChanged(ItemEvent event)
		             {
		                  if (event.getStateChange() == ItemEvent.SELECTED)
		                  {
		                       try
		                       {
		                            String no=c1.getSelectedItem().toString();
		                            tf5.setText(no);
		                            tf5.setEditable(false);
		                       }
		                       catch (Exception ex)
		                       {
		                            ex.printStackTrace();
		                       }
		                  }
		             }
		        });
		        
		       f.add(c1);
		        tf5 = new JTextField();  //l3
		        tf5.setFont(new Font("Serif", Font.PLAIN, 20)); 
		        l6 = new JLabel("AGE ");
		        tf6 = new JTextField();
		        tf6.setFont(new Font("Serif", Font.PLAIN, 20)); 
		        l7 = new JLabel("PRODUCT PRICE ");
		        tf7 = new JTextField();
		        tf7.setFont(new Font("Serif", Font.PLAIN, 20)); 
		        btn1 = new JButton(" ADD ");  
		        btn1.setForeground(new Color(139, 69, 19));
		        btn1.setBackground(new Color(255,196,12));
		       
		        btn1.addActionListener(new ActionListener(){  
		        	public void actionPerformed(ActionEvent e){  
		        		try{  
		        				Class.forName("com.mysql.cj.jdbc.Driver");  
		        				Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
		        				Statement stmt=con.createStatement();  
		        				String prod_name = tf2.getText();
		        				
		        			
		        				int prod_quantity = 0,prod_age=0;
		        				double prod_price=0;
		        				
		        				String prod_type = tf5.getText();
		        				String a = tf6.getText();
		        				String q = tf4.getText();
		        				
		        				String price = tf7.getText();
		        			 if(!price.isEmpty() && price.matches("[0-9]+")) prod_price = Integer.parseInt(price);
		        			if(!q.isEmpty() && q.matches("[0-9]+")) prod_quantity = Integer.parseInt(q);	
		        			if(!a.isEmpty() && q.matches("[0-9]+")) prod_age = Integer.parseInt(q);	
		        				 
		        				String gender = tf8.getText();
		        				DatabaseMetaData dbm = con.getMetaData();
		        				ResultSet tables = dbm.getTables(null, null, "product", null);
		        				
		        				if (!tables.next()) {
		        					String sql1 = "create table product "+" (prod_name varchar(1000) NOT NULL,"+"prod_id int,"+" prod_quantity int NOT NULL,"+" prod_type varchar(300) NOT NULL,"+"prod_age int NOT NULL ,"+" seller_id int ,"+"prod_price double NOT NULL,"+" gender varchar(10) NOT NULL,"+" available varchar(10) NULL,"+" quantitySold int NULL)";
		        					String sql2= "alter table  product ADD CONSTRAINT product_sellerid_fk FOREIGN KEY(seller_id) REFERENCES seller(seller_id)";
		        					String sql3 = "alter table product ADD CONSTRAINT product_pid_s_id_PK primary key(prod_id,seller_id) ";
		        					stmt.executeUpdate(sql1);
		        					stmt.executeUpdate(sql2);
		        					stmt.executeUpdate(sql3);
		        				  
		        				}
		        				else {
		        						int prod_id = 0;
		        						ResultSet rs =stmt.executeQuery("select count(*) from product");
		        						if(rs.next()) {
		        							prod_id = rs.getInt(1);
		        						}
		        						prod_id = prod_id+1;
		        						String available = "Y";
		        						int quantitysold = 0;
		        						String choo = "Choose";
		        						boolean flag = onlyDigits(prod_name,prod_name.length());
		        						boolean flag1 = onlyDigits(price,price.length());
		        						
		        						if(prod_name.isEmpty() ) {
		        							
		        							JOptionPane.showMessageDialog(null, "Enter product Name");
		        							
		        						}
		        						else if(flag)       JOptionPane.showMessageDialog(null, "Product name should not contain only digits");
		        						else if(q.isEmpty())						  JOptionPane.showMessageDialog(null, "Please Enter product quantity");
		        						else if(!q.matches("[0-9]+")) 	JOptionPane.showMessageDialog(null, "Quantity should not contain characters");
		        						else if(prod_type.isEmpty()) 			JOptionPane.showMessageDialog(null, "Please enter prodType");
		        						else if(a.isEmpty())         JOptionPane.showMessageDialog(null, "Please Enter product age");
		        						else if(!a.matches("[0-9]+")) 	JOptionPane.showMessageDialog(null, "Age should not contain characters");
		        						else if(price.isEmpty())			  JOptionPane.showMessageDialog(null, "Please Enter price");
		        						else if(!price.matches("[0-9]+")) 	JOptionPane.showMessageDialog(null, "Price should not contain characters");
		        						
		        						else if(gender.isEmpty())          JOptionPane.showMessageDialog(null, "Please Enter gender");
		        					else { 
		        					
//		        		
//		        						
		        					String sql = "insert into product values('"+prod_name+"',"+prod_id+","+prod_quantity+",'"+prod_type+"',"+prod_age+","+Global.SID+","+prod_price+",'"+gender+"','"+available+"',"+quantitysold+")";
		        				   stmt.executeUpdate(sql);
		        					JOptionPane.showMessageDialog(null, "Product Added");
		        					f.dispose();
		        					new SellerOperations().f.setVisible(true);
		        					}
		        					
		        					
		        				}
		        	          
		        	          
		        		}
		        		catch(Exception ee) { System.out.println(ee);}
		        	          
		        	         
		        	}  
		        	});  
		        l8 = new JLabel("GENDER ");
		        tf8 = new JTextField();
		        tf8.setFont(new Font("Serif", Font.PLAIN, 20)); 
		        String s2[] = {"Choose","Female","Male"};
		        c2 = new JComboBox(s2);
		        c2.setBounds(150, 473, 70, 20);  
		        c2.addItemListener(new ItemListener()
		        {
		             public void itemStateChanged(ItemEvent event)
		             {
		                  if (event.getStateChange() == ItemEvent.SELECTED)
		                  {
		                       try
		                       {
		                            String no=c2.getSelectedItem().toString();
		                            tf8.setText(no);
		                            tf8.setEditable(false);
		                       }
		                       catch (Exception ex)
		                       {
		                            ex.printStackTrace();
		                       }
		                  }
		             }
		        });
		      f. add(c2);
		        
		        l1.setBounds(110, 30, 500, 30);  
		        l2.setBounds(80,110, 200, 30);  
		        l4.setBounds(80,170, 200, 30);  
		        l5.setBounds(80,240, 200, 30);  
		        l6.setBounds(80,310,200,30);
		        l7.setBounds(80,390,200,30);
		        l8.setBounds(90,470,200,30);
		        
		        tf2.setBounds(300, 110, 200,40); 
		        tf4.setBounds(300,170,200,40);
		        tf5.setBounds(300,240,200,40);
		        tf6.setBounds(300,310,200,40);
		        tf7.setBounds(300,390,200,40);
		        tf8.setBounds(300,460,200,40);
		        
		        btn1.setBounds(90, 530, 100, 30);  
		        btn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        
		       f. add(l1);  
		       f. add(l2); 
		     
		       f. add(l4);
		       f. add(l5);
		       f.add(l6);
		       f.add(l7);
		       f.add(l8);
   
		      
		       f. add(tf2);
		         
		       
		       f. add(tf4);
		       f.add(tf5);
		       f.add(tf5);
		       f.add(tf6);
		       f.add(tf7);
		       f.add(tf8);
		       
		       f. add(btn1);  
		       f. setVisible(true);
		}
	   boolean onlyDigits(String s,int n) {
	   	for(int i=0;i<n;i++) {
	   		if(!Character.isDigit(s.charAt(i))) {
	   			return false;
	   		}
	   	}
	   	return true;
	   }
		void delete() {
			DeleteProducts d = new DeleteProducts();
			d.showTableData();
		}

		
		void update() {
			UpdateProducts a = new UpdateProducts();
			a.showTableData();
		}
		
		
		
		
		
	}
	
}