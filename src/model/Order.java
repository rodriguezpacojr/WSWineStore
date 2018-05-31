package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="order")

public class Order {	
    
	private int keyOrder;
    private String orderDate;
            
    private int keyCustomer;
    private String nameCustomer;
    
    private int keyEmployee;
    private String nameEmployee;    
    
    private Connection conn;
    private Connect objC;
	    
    
	//==========================METHODS===========================================
	public void insertOrder() {
		objC = new Connect();
		conn = objC.getConn();
					
		try {	
			String query = "INSERT INTO orders (keyorder, orderdate, keycustomer, keyemployee)"
					+ " values ("+keyOrder+", now(), "+keyCustomer+", "+keyEmployee+")";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {						
		}
	}
	
	//==========================METHODS===========================================
	public void getData() 
	{
		try 
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT keycustomer, keyroute, keyemployee, (SELECT MAX(keyorder) FROM orders) FROM customer JOIN route USING (keyroute) JOIN employee USING(keyemployee) WHERE keycustomer = "+keyCustomer;
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			if(res.next()) 
			{
				keyEmployee = res.getInt("keyemployee");
				keyOrder = res.getInt("max");
			}
		}
		catch(Exception e) {}
	}

	public List<Order> listOrdersU() {
		Order objO;
		ArrayList<Order> arrOr = new ArrayList<Order>();

		objC = new Connect();
		conn = objC.getConn();
		
		try {
			String query = "SELECT o.*, c.name as namec, c.lastname as lastnamec, e.name as namee, e.lastname as lastnamee FROM orders o JOIN customer c USING(keycustomer) JOIN employee e USING(keyemployee) WHERE status = 'f' ORDER BY 1";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				objO = new Order();				
				objO.keyOrder = res.getInt("keyorder");						
				objO.orderDate = res.getString("orderdate");
				objO.keyCustomer = res.getInt("keycustomer");				
				objO.nameCustomer = res.getString("namec") +" " + res.getString("lastnamec");
				objO.keyEmployee = res.getInt("keyemployee");
				objO.nameEmployee = res.getString("namee") +" " + res.getString("lastnamee");			
				arrOr.add(objO);
			}
			conn.close();
		}
		catch(Exception e) {

		}
		return arrOr;
	}
	
	public List<Order> listOrdersD() {
		Order objO;
		ArrayList<Order> arrOr = new ArrayList<Order>();

		objC = new Connect();
		conn = objC.getConn();
		
		try {
			String query = "SELECT o.*, c.name as namec, c.lastname as lastnamec, e.name as namee, e.lastname as lastnamee FROM orders o JOIN customer c USING(keycustomer) JOIN employee e USING(keyemployee) WHERE status = 't' ORDER BY 1";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				objO = new Order();				
				objO.keyOrder = res.getInt("keyorder");						
				objO.orderDate = res.getString("orderdate");
				objO.keyCustomer = res.getInt("keycustomer");				
				objO.nameCustomer = res.getString("namec") +" " + res.getString("lastnamec");
				objO.keyEmployee = res.getInt("keyemployee");
				objO.nameEmployee = res.getString("namee") +" " + res.getString("lastnamee");			
				arrOr.add(objO);
			}
			conn.close();
		}
		catch(Exception e) {

		}
		return arrOr;
	}
	
	//=========================GETTERS AND SETTERS============================================
	@XmlElement(required=true)		
	public int getKeyOrder() {
		return keyOrder;
	}

	public void setKeyOrder(int keyOrder) {
		this.keyOrder = keyOrder;
	}	

	@XmlElement(required=true)
	public int getKeyCustomer() {
		return keyCustomer;
	}

	public void setKeyCustomer(int keyCustomer) {
		this.keyCustomer = keyCustomer;
	}
	
	@XmlElement(required=true)
	public int getKeyEmployee() {
		return keyEmployee;
	}

	public void setKeyEmployee(int keyEmployee) {
		this.keyEmployee = keyEmployee;
	}
	
	@XmlElement(required=true)
	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	@XmlElement(required=true)
	public String getNameEmployee() {
		return nameEmployee;
	}

	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	@XmlElement(required=true)
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
