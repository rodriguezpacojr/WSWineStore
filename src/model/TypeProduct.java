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
@XmlRootElement(name="typeproduct")

public class TypeProduct
{	
    private int keyTypeProduct;
    private String name;     
    
    private Connection conn;
    private Connect objC;
	    

		//==========================METHODS===========================================
	public void insertTypeProduct() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO type_product (name)"
				+ " values ('"+name+"')";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.name=query;
		}
	}
	
	public void updateTypeProduct() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE type_product SET name = '"+name+"' WHERE keytypeproduct = "+keyTypeProduct;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.name=query;
		}
	}
	
	public void deleteTypeProduct() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM type_product WHERE keytypeproduct = "+keyTypeProduct;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<TypeProduct> listTypeProducts()
	{
		TypeProduct objCou;
		ArrayList<TypeProduct> arrCou = new ArrayList<TypeProduct>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM type_product order by 1";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				objCou = new TypeProduct();
				objCou.keyTypeProduct = res.getInt("keytypeproduct");
				objCou.name= res.getString("name");											
				arrCou.add(objCou);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrCou;
	}
	
	public void getObjTypeProduct(int keytypeproduct)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM type_product WHERE keytypeproduct = "+keytypeproduct;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{							
				this.keyTypeProduct = keytypeproduct;
				this.name = res.getString("name");							
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	//=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)		
	public int getKeyTypeProduct() {
		return keyTypeProduct;
	}

	public void setKeyTypeProduct(int keyTypeProduct) {
		this.keyTypeProduct = keyTypeProduct;
	}

	@XmlElement(required=true)
	public String getNameTypeProduct() {
		return name;
	}

	public void setNameTypeProduct(String nameTypeProduct) {
		this.name = nameTypeProduct;
	}
}
