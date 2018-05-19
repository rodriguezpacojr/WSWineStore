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
@XmlRootElement(name="employee")
public class Employee {		
	
	private int keyEmployee;
    private String name;
    private String lastName;    
    private Date bornDate;
    private String email;
    private String phone;
    private String RFC;        
    private String photo;
    private Date entryDate;    
    
    private int keyUser;    
    private String Usr;
    
    private Connection conn;
    private Connect objC;
    
	//==========================METHODS===========================================
	public void insertEmployee() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO employee (name, lastname, borndate, email, phone, rfc, entryDate, keyuser)"
				+ " values ('"+name+"', '"+lastName+"', '"+bornDate+"', '"+email+"','"+phone+"','"+RFC+"','"+photo+"' ,'"+entryDate+"',"+keyUser+")";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.name=query;
		}
	}
	
	public void updateEmployee() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE employee SET name = '"+name+"', lastname = '"+lastName+"', "
				+ "borndate = '"+bornDate+"', email = '"+email+"',"
				+ "phone = '"+phone+"', rfc = '"+RFC+"', entrydate = '"+entryDate+"' "
				+ "WHERE keyemployee = "+keyEmployee;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.name=query;
		}
	}
	
	public void deleteEmployee() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM employee WHERE keyemployee = "+keyEmployee;
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Employee> listEmployees()
	{				
		Employee obj = new  Employee();
		ArrayList<Employee> arr = new ArrayList<Employee>();		
										
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			String query = "SELECT e.*, u.username FROM employee e JOIN usr u USING(keyuser)";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
				
			//Convert all registers from query to objects
			while(res.next())
			{
				obj = new Employee();
				obj.keyEmployee = res.getInt("keyemployee");
				obj.name = res.getString("name");
				obj.lastName = res.getString("lastname");				
				obj.bornDate = res.getDate("borndate");
				obj.email = res.getString("email");
				obj.phone = res.getString("phone");
				obj.RFC = res.getString("rfc");
				obj.entryDate = res.getDate("entrydate");
				obj.Usr = res.getString("username");
				obj.keyUser = res.getInt("keyuser");
				arr.add(obj);
			}
			conn.close();
		}
		catch(Exception e)
		{	
			
		}
			return arr;		
	}
		
	//=========================GETTERS AND SETTERS============================================
    // Annotation for determinate that the attribute is part of WS' out
    @XmlElement(required=true)
	public int getKeyEmployee() {
		return keyEmployee;
	}
    
	public void setKeyEmployee(int keyEmployee) {
		this.keyEmployee = keyEmployee;
	}

	@XmlElement(required=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(required=true)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(required=true)
	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	@XmlElement(required=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(required=true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElement(required=true)
	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	@XmlElement(required=true)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@XmlElement(required=true)
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@XmlElement(required=true)
	public int getKeyUser() {
		return keyUser;
	}

	public void setKeyUser(int keyUser) {
		this.keyUser = keyUser;
	}

	@XmlElement(required=true)
	public String getUsr() {
		return Usr;
	}

	public void setUsr(String usr) {
		Usr = usr;
	}
}