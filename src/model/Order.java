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
    private Date orderDate;
            
    private int keyCustomer;
    private Customer Customer;
    
    private int keyEmployee;
    private Employee Employee;        
    
    private Connection conn;
    private Connect objC;
	    
    
	//==========================METHODS===========================================
	public void insertOrder() {
		objC = new Connect();
		conn = objC.getConn();
					
		try {	
			String query = "INSERT INTO orders (keyorder, orderdate, keycustomer, keyemployee)"
					+ " values ("+keyOrder+", '"+orderDate+"',"+keyCustomer+", "+keyEmployee+")";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {						
		}
	}
		
		
	public List<Order> listOrders() {
		Order objO;
		ArrayList<Order> arrOr = new ArrayList<Order>();

		objC = new Connect();
		conn = objC.getConn();
		
		try {
			String query = "SELECT * FROM orders";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				Customer objCu = new Customer(); 
				Employee objEm = new Employee();
												
				objCu.getObjCustomer(res.getInt("keycustomer"));
				
				objO = new Order();				
				objO.keyOrder = res.getInt("keyorder");				
				objO.orderDate = res.getDate("orderdate");
				objO.Customer = objCu;
				objO.Employee = objEm;				
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
	public Customer getCustomer() {
		return Customer;
	}
	public void setCustomer(Customer customer) {
		Customer = customer;
	}
	
	@XmlElement(required=true)
	public Employee getEmployee() {
		return Employee;
	}
	public void setEmployee(Employee employee) {
		Employee = employee;
	}
}
