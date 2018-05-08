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
    private Order Order;
    
    private int keyProduct;
    private Product Product;
    
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
			String query = "INSERT INTO order_detail (keyorder, keyproduct, quantity)"
					+ " values ("+keyOrder+", "+keyProduct+", "+quantity+")";
			
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
			String query = "SELECT * FROM order_detail";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				Product objP = new Product();
								
				objP.getObjProduct(res.getInt("keyproduct"));
				
				objHis = new OrderDetail();				
				objHis.keyOrderDetail = res.getInt("keyorderdetail");								
				objHis.keyOrder = res.getInt("keyorder");				
				objHis.Product = objP;
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
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	
		

	@XmlElement(required=true)
	public Order getOrder() {
		return Order;
	}
	public void setOrder(Order order) {
		Order = order;
	}

	@XmlElement(required=true)
	public Product getProduct() {
		return Product;
	}
	public void setProduct(Product product) {
		Product = product;
	}
}
