package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="route")

public class Route {	
    
	private int keyRoute;
    private String destination, employee, lastName, userName;        
    private int customers;
    private int keyEmployee;
    private Connection conn;
    private Connect objC;
	    
    
    	//==========================METHODS===========================================
	public void insertRoute() {
		objC = new Connect();
		conn = objC.getConn();
					
		try {	
			String query = "INSERT INTO route (name, keyemployee)"
					+ " values ('"+destination+"', "+keyEmployee+")";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {						
		}
	}	
	
	public void updateRoute() {
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE route SET name = '"+destination+"', keyemployee = "+keyEmployee+" "								
				+ "WHERE keyroute = "+keyRoute;
		try {						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.destination=query;
		}
	}
	
	public void deleteRoute() {
		objC = new Connect();
		conn = objC.getConn();
		try {
			String query = "DELETE FROM route WHERE keyroute = "+keyRoute;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Route> listRoutes() {
		Route obj = new Route();
		ArrayList<Route> arr = new ArrayList<Route>();

		objC = new Connect();
		conn = objC.getConn();
		
		try {
			//String query = "select r.*, e.name AS employee, e.lastname AS lastname from route r join employee e USING (keyemployee)";
			String query = "select r.*, e.name AS employee, e.lastname AS lastname from route r join employee e USING (keyemployee)";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				obj = new Route();				
				obj.keyRoute = res.getInt("keyroute");								
				obj.destination = res.getString("name");
				obj.keyEmployee = res.getInt("keyemployee");
				obj.employee = res.getString("employee");
				obj.lastName = res.getString("lastname");
				arr.add(obj);
			}
			conn.close();
		}
		catch(Exception e) {

		}
		return arr;
	}

	public List<Route> listRoutesEmployee() {
		Route obj = new Route();
		ArrayList<Route> arr = new ArrayList<Route>();

		objC = new Connect();
		conn = objC.getConn();
		
		try {
			String query = "SELECT r.*, e.name AS employee, e.lastname AS lastname, count(*) "
							+ "FROM route r JOIN employee e USING (keyemployee) "
										 + "JOIN customer USING (keyroute) "
										 + "JOIN usr USING (keyuser) "
							+ "WHERE username = '" + userName + "' "
							+ "GROUP BY r.keyroute, e.name, e.lastname";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				obj = new Route();				
				obj.keyRoute = res.getInt("keyroute");								
				obj.destination = res.getString("name");
				obj.keyEmployee = res.getInt("keyemployee");
				obj.employee = res.getString("employee");
				obj.lastName = res.getString("lastname");
				obj.customers = res.getInt("count");
				arr.add(obj);
			}
			conn.close();
		}
		catch(Exception e) {

		}
		return arr;
	}
	
	@XmlElement(required=true)
	public int getKeyRoute() {
		return keyRoute;
	}

	public void setKeyRoute(int keyRoute) {
		this.keyRoute = keyRoute;
	}

	@XmlElement(required=true)
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@XmlElement(required=true)
	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}

	@XmlElement(required=true)
	public int getKeyEmployee() {
		return keyEmployee;
	}

	public void setKeyEmployee(int keyEmployee) {
		this.keyEmployee = keyEmployee;
	}	
	
	@XmlElement(required=true)
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	@XmlElement(required=true)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlElement(required=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
