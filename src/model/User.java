package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="user")

public class User
{	 
	private int keyUser, count;
	private String userName;
	private String password;
	private String token;
	private String role;
	private String table;	
	
    private Connection conn;
    private Connect objC;
	
	public void count() 
	{
		try 
		{
			objC = new Connect();
			conn = objC.getConn();
	
			String query = "SELECT count(*) FROM " + table;
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			if(res.next()) 
			{
				
				count = res.getInt("count");
			}
			else 
			{
				count = 0;
			}
		}
		catch(Exception e) {}
	}
	
	//==========================METHODS===========================================
	public void validateUser() 
	{
		try 
		{
			objC = new Connect();
			conn = objC.getConn();
						
			String query = "SELECT * FROM usr WHERE username = '"+userName+"' AND "
						+ "password = '"+password+"'";
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			if(res.next()) 
			{
				Log objB = new Log();
				objB.insertLog(userName);
				token = objB.getToken();
				role = res.getString("role");
			}
			else 
			{
				token = "Access Denied";
			}
		}
		catch(Exception e) {}
	}
	
	public void getObjUsr(int keyUser)
	{    	
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM usr WHERE keyuser = "+keyUser;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				this.setKeyUser(keyUser);
				this.setUserName(res.getString("username"));																
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}	
	
	
	public List<User> listUsers()
	{				
		User obj = new  User();
		ArrayList<User> arr = new ArrayList<User>();		
										
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			String query = "SELECT keyuser,username FROM usr WHERE role != 'admin' AND keyuser NOT IN (SELECT keyuser FROM employee WHERE keyuser IS NOT NULL)";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
				
			//Convert all registers from query to objects
			while(res.next())
			{
				obj = new User();
				obj.keyUser= res.getInt("keyuser");
				obj.userName= res.getString("username");
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
	@XmlElement(required=true)
    public int getKeyUser() {
		return keyUser;
	}	
	
	@XmlElement(required = true)
	public String getToken() {
		return token;
	}
	
	@XmlElement(required = true)
	public String getUserName() {
		return userName;
	}
		
	@XmlElement(required = true)
	public String getRole() {
		return role;
	}		

	@XmlElement(required = true)
	public String getTable() {
		return table;
	}
		
	@XmlElement(required = true)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setKeyUser(int keyUser) {
		this.keyUser = keyUser;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}