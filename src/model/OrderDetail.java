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
@XmlRootElement(name="orderdetail")

public class OrderDetail
{	
    private int keyOrderDetail;    
    private int keyOrder;   
    private int keyProduct;
    private String nameProduct;
        
    private int keyCustomer;
    private String nameCustomer;
    
    private int keyEmployee;
    private String nameEmployee;
    
    private float quantity;
    
    private Connection conn;
    private Connect objC;
	    
    
    	//==========================METHODS===========================================
	public void insertOrderDetail() 
	{
		objC = new Connect();
		conn = objC.getConn();
					
		try
		{	
			String query = "INSERT INTO order_detail(keyorder, keyproduct, quantity) values ("+keyOrder+", "+keyProduct+", "+quantity+")";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) 
		{						
		}
	}
	
	public void updateOrderDetail() 
	{
		objC = new Connect();
		conn = objC.getConn();
						
		try
		{		
			String query = "UPDATE order_detail SET quantity = "+quantity										
					+ " WHERE keyorderdetail = "+keyOrderDetail+", keyorder = "+keyOrder+" AND keyproduct = "+keyProduct;
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) 
		{		
		}
	}
		
	public List<OrderDetail> listOrderDetail()
	{
		OrderDetail objHis;
		ArrayList<OrderDetail> arrHis = new ArrayList<OrderDetail>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT o.*, od.*, c.name as namec, c.lastname as lastnamec, e.name as namee, e.lastname as lastnamee, p.name as namep FROM order_detail od JOIN orders o USING(keyorder) JOIN customer c USING(keycustomer) JOIN employee e USING(keyemployee) JOIN product p USING(keyproduct) WHERE status = 'f' ORDER BY 1";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{				
				objHis = new OrderDetail();				
				objHis.keyOrderDetail = res.getInt("keyorderdetail");					
				objHis.keyOrder = res.getInt("keyorder");
				objHis.keyProduct = res.getInt("keyproduct");
				objHis.nameProduct = res.getString("namep");
				objHis.keyCustomer = res.getInt("keycustomer");
				objHis.nameCustomer = res.getString("namec") +" " + res.getString("lastnamec");
				objHis.keyEmployee = res.getInt("keyemployee");
				objHis.nameEmployee = res.getString("namee") +" " + res.getString("lastnamee");
				objHis.quantity = res.getInt("quantity");
				arrHis.add(objHis);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrHis;
	}
	
	public List<OrderDetail> orderDetail()
	{
		OrderDetail objHis;
		ArrayList<OrderDetail> arrHis = new ArrayList<OrderDetail>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT od.*, c.name as namec, c.lastname as lastnamec, e.name as namee, e.lastname as lastnamee, p.name as namep FROM order_detail od JOIN orders o USING(keyorder) JOIN customer c USING(keycustomer) JOIN employee e USING(keyemployee) JOIN product p USING(keyproduct) WHERE keyorder = " + keyOrder;	
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				objHis = new OrderDetail();
				objHis.keyOrder = res.getInt("keyorder");
				objHis.keyProduct = res.getInt("keyproduct");
				objHis.nameProduct = res.getString("namep");
				objHis.nameCustomer = res.getString("namec") +" " + res.getString("lastnamec");
				objHis.nameEmployee = res.getString("namee") +" " + res.getString("lastnamee");
				objHis.quantity = res.getInt("quantity");
				arrHis.add(objHis);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrHis;
	}
	
	
	//=========================GETTERS AND SETTERS============================================

	@XmlElement(required=true)
	public float getKeyOrderDetail() {
		return keyOrderDetail;
	}

	public void setKeyOrderDetail(int keyOrderDetail) {
		this.keyOrderDetail = keyOrderDetail;
	}
	
	@XmlElement(required=true)
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@XmlElement(required=true)
	public int getKeyOrder() {
		return keyOrder;
	}

	public void setKeyOrder(int keyOrder) {
		this.keyOrder = keyOrder;
	}

	@XmlElement(required=true)
	public int getKeyProduct() {
		return keyProduct;
	}

	public void setKeyProduct(int keyProduct) {
		this.keyProduct = keyProduct;
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
	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
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
}
