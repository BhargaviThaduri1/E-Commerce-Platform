package ECOMMERCE;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.lang.String.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
public class CustomerCart {
	JFrame f;
	
	JButton add;
	CustomerCart(){
	
		f = new JFrame("Your cart");
		JPanel panel = new JPanel();
		panel.setBounds(600,200,500,550);
		f.setBounds(600,200,500,550);
        f.setVisible(false);
       
		
		try{  
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=(Connection) DriverManager.getConnection(  "jdbc:mysql://localhost:3306/project1","root","rootpassword");  
		Statement stmt=con.createStatement(); 
	    String[] colName = {"PRODNAME","QUANTITY ","PRICE   ","TOTAL PRICE"};
	    int count = 0;
	    int prod_id = 0;
	    
	    int id = Global.ID;
		ResultSet rs1 = stmt.executeQuery("select count(*) from cart where customer_id="+Global.ID+"");
		 if(rs1.next()) {
	    	 count = rs1.getInt(1);
		}
		 
	     if(count<=0) {
	    	 JOptionPane.showMessageDialog(null, "No products in cart","Warning message",JOptionPane.WARNING_MESSAGE);
	     }
	     else {
	
	     	
	    	 String data[][] = new String[count][6];
	    	
	    	 int i=0;
	    	 double price = 0.0;
	    	 
	    	 ResultSet rs = stmt.executeQuery("select prod_name,prod_price from product where prod_id in (select prod_id from cart where customer_id="+Global.ID+")");
	    	 while(rs.next()) {
	    		    data[i][0] = rs.getString(1);
			    	 price= rs.getDouble(2);
			    	data[i][2] = Double.toString(price);		    	
			    	i++;
	    	 }
	    i=0;
	    Double tot_price = 0.0;
	    ResultSet rs31 = stmt.executeQuery("select prod_quantity from cart where customer_id= "+Global.ID+"");
	    while(rs31.next()) {
	    	int pq = rs31.getInt(1);
	        data[i][1] = Integer.toString(pq);
	        String s = data[i][2];
	        Double price1 = Double.parseDouble(s);
	        data[i][3] =Double.toString(price1*pq);
	        tot_price+=price1*pq;
	    	i++;
	    
		
		
	     }
	    JTable jt=new JTable(data,colName);  
	    jt.setBounds(400,40,600,500);          
	    JScrollPane sp=new JScrollPane(jt);    
	    JLabel l1 = new JLabel("Your id is "+Global.ID);
	    panel.add(l1);
	    panel.add(sp);
	    JLabel sub_tot = new JLabel("SubTotal("+""+count+")"+"items");
	    panel.add(sub_tot);
	    JLabel tot = new JLabel("Total:"+tot_price);
	    panel.add(tot);
	    JButton proceed_to_buy = new JButton("Proceed to buy");
	    proceed_to_buy.setBackground(new Color(255,196,12));
	    proceed_to_buy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    proceed_to_buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				f.dispose();
				BuyPage b = new BuyPage();
				
				
			}
	    	
	    });
	    panel.add(proceed_to_buy);
	    
	    f.add(panel);
	    f.setVisible(true);    
	    }
		}
		catch(Exception e) {
		System.out.println(e);
		}
	}
 public static void main(String[] args) {
	 new CustomerCart().f.setVisible(true);
 }
	
}

