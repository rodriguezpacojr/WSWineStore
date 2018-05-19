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
import java.util.Date;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="customer")
public class Customer {
	
	private int keyCustomer;
    private String name;
    private String lastName;    
    private Date bornDate;
    private String email;
    private String phone;
    private String RFC;        
    private String photo;
    private Date entryDate;
    private double latitude;
    private double longitude;
  
    private int keyRoute;
    private String Route;                
    
    private Connection conn;
    private Connect objC;
    
	//==========================METHODS===========================================
	public void insertCustomer() {
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO customer (name, lastname, borndate, email, phone, rfc, entryDate, photo, latitude, longitude, keyroute)"
				+ " values ('"+name+"', '"+lastName+"', '"+bornDate+"', '"+email+"','"+phone+"','"+RFC+"','"+entryDate+"', '"+photo+"', "+latitude+", "+longitude+", "+keyRoute+")";
		try {					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {			
			this.name=query;
		}
	}
	
	public void updateCustomer() {
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE customer SET name = '"+name+"', "
				+ "lastname = '"+lastName+"', borndate = '"+bornDate+"', email = '"+email+"', "
				+ "phone = '"+phone+"', rfc = '"+RFC+"', entrydate = '"+entryDate+"', "
				+ "latitude = "+latitude+", longitude = "+longitude+", "
				+ "keyroute = "+keyRoute+" "
				+ "WHERE keycustomer = "+keyCustomer;
		try {						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.name=query;
		}
	}
	
	public void deleteCustomer() {
		objC = new Connect();
		conn = objC.getConn();
		try {
			String query = "DELETE FROM customer WHERE keycustomer = "+keyCustomer;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Customer> listCustomers() {				
		Customer objCu = new  Customer();;
		ArrayList<Customer> arrCus = new ArrayList<Customer>();		
										
		try {
			objC = new Connect();
			conn = objC.getConn();
			String query = "SELECT c.*, r.name AS route FROM customer c JOIN route r USING(keyroute)";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
				
			//Convert all registers from query to objects
			while(res.next()) {
				objCu = new Customer();
				objCu.keyCustomer = res.getInt("keycustomer");
				objCu.name = res.getString("name");
				objCu.lastName = res.getString("lastname");				
				objCu.bornDate = res.getDate("borndate");
				objCu.email = res.getString("email");
				objCu.phone = res.getString("phone");
				objCu.RFC = res.getString("rfc");
				objCu.entryDate = res.getDate("entrydate");
				objCu.latitude = res.getDouble("latitude");
				objCu.longitude = res.getDouble("longitude");
				objCu.keyRoute = res.getInt("keyRoute");
				objCu.Route = res.getString("route");
				
				arrCus.add(objCu);
			}
			conn.close();
		}
		catch(Exception e) {	
			
		}
		return arrCus;		
	}	
	
	public List<Customer> listCustomersRoute() {				
		Customer objCu = new  Customer();;
		ArrayList<Customer> arrCus = new ArrayList<Customer>();		
										
		try {
			objC = new Connect();
			conn = objC.getConn();
			String query = "SELECT keycustomer, name, lastname, latitude, longitude FROM customer WHERE keyroute = " + keyRoute;			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
				
			//Convert all registers from query to objects
			while(res.next()) {
				objCu = new Customer();
				objCu.keyCustomer = res.getInt("keycustomer");
				objCu.name = res.getString("name");
				objCu.lastName = res.getString("lastname");				
				objCu.latitude = res.getDouble("latitude");
				objCu.longitude = res.getDouble("longitude");
				arrCus.add(objCu);
			}
			conn.close();
		}
		catch(Exception e) {	
			
		}
		return arrCus;		
	}
	
	public void getObjCustomer(int keycustomer) {
		try {
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM customer WHERE keycustomer = "+keycustomer;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				this.keyCustomer = keycustomer;
				this.name = res.getString("name");
				this.lastName = res.getString("lastname");				
				this.bornDate = res.getDate("borndate");
				this.email = res.getString("email");								
				this.phone = res.getString("phone");
				this.RFC = res.getString("rfc");
				this.entryDate = res.getDate("entrydate");
				this.latitude = res.getDouble("latitude");
				this.longitude = res.getDouble("longitude");				
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
	}
	
	//=========================GETTERS AND SETTERS============================================
    // Annotation for determinate that the attribute is part of WS' out
    @XmlElement(required=true)
	public int getKeyCustomer() {
		return keyCustomer;
	}
    
	public void setKeyCustomer(int keyCustomer) {
		this.keyCustomer = keyCustomer;
	}
	
	@XmlElement(required = true)
	public String getname() {
		return name;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	@XmlElement(required = true)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	@XmlElement(required = true)
	public Date getBornDate() {
		return bornDate;
	}
	
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	
	@XmlElement(required = true)
	public Date getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	@XmlElement(required = true)
	public String getemail() {
		return email;
	}
	
	public void setemail(String email) {
		this.email = email;
	}
			
	
	@XmlElement(required = true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@XmlElement(required = true)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@XmlElement(required = true)
	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	@XmlElement(required = true)
	public int getKeyRoute() {
		return keyRoute;
	}

	public void setKeyRoute(int keyRoute) {
		this.keyRoute = keyRoute;
	}
	
	@XmlElement(required = true)
	public String getRoute() {
		return Route;
	}

	public void setRoute(String Route) {
		this.Route = Route;
	}
		
	@XmlElement(required = true)
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@XmlElement(required = true)
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}