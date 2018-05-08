package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class Log 
{
	//private int keyAccess;
	//private String userName;
	private String  token;
	//private Date startDate;
	//private Date endDate;
	
	Connect objC;
	Connection conn;
	
	@XmlElement(required = true)
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void insertLog(String userName) 
	{
		generateToken(userName);
		
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "INSERT INTO log (username, token, startdate, enddate)"
					+ " VALUES('"+userName+"', '"+token+"', now(), now() + interval '30 minutes')";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {}
	}
	
	public boolean validateToken(String token) 
	{
		boolean flag = false;		
		
		try
		{			
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM log WHERE token = '"+token+"' AND enddate > now()";
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			if(res.next()) 
			{								
				flag = true;
			}
			else 
			{
				flag = false;
			}
			conn.close();
		}catch(Exception e) 
		{}
		return flag;
	}

	private void generateToken(String userName) 
	{
		try 
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fecha = new Date();
			String cadena = dateFormat.format(fecha)+userName;

			byte[] digest = null;
			byte[] buffer = cadena.getBytes();

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();

			token = toHexadecimal(digest);
		}
		catch (NoSuchAlgorithmException ex)
		{
		}
	}
	
	private static String toHexadecimal(byte[] digest)
	{
		String hash = "";
		for(byte aux : digest) 
		{
			int b = aux & 0xff;
			if (Integer.toHexString(b).length() == 1) hash += "0";
			hash += Integer.toHexString(b);
		}
		return hash;
	}
}
