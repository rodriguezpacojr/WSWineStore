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
@XmlRootElement(name="product")

public class Product {	
    
	private int keyProduct;
    private String name;
    private double ml;
    private String color;
    private String taste;    
    private int stock;
    private int availables;
    private double salesPrice;    
    
    private int keyTypeProduct;
     
    private String tp;
    
    private Connection conn;
    private Connect objC;	    

	//==========================METHODS===========================================
	public void insertProduct() {
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO product (name, ml, color, taste, stock, salesprice, keytypeproduct, availables)"
				+ " values ('"+name+"', "+ml+", '"+color+"', '"+taste+"', "+stock+", "+salesPrice+", "+keyTypeProduct+", "+availables+")";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.name=query;
		}
	}
	
	public void updateProduct() {
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE product SET name = '"+name+"', ml = "+ml+", "
				+ "color = '"+color+"', taste = '"+taste+"', stock = "+stock+", "
				+ "salesprice = "+salesPrice+", keytypeproduct = "+keyTypeProduct+" "								
				+ "WHERE keyproduct = "+keyProduct;
		try {						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.name=query;
		}
	}
	
	public void updateStock() {
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE product SET stock = (SELECT stock from product WHERE keyproduct = "+keyProduct+") - "+stock+" WHERE keyproduct = "+keyProduct;
		try {						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.name=query;
		}
	}
	
	public void updateAvailables() {
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE product SET availables = (SELECT availables from product WHERE keyproduct = "+keyProduct+") + ("+availables+") WHERE keyproduct = "+keyProduct;
		try {						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.name=query;
		}
	}
	
	public void deleteProduct() {
		objC = new Connect();
		conn = objC.getConn();
		try {
			String query = "DELETE FROM product WHERE keyproduct = "+keyProduct;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
	
	public void getProduct() 
	{
		try 
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM product WHERE keyproduct = "+keyProduct;
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			if(res.next()) 
			{
				name = res.getString("name");
				ml = res.getDouble("ml");
				color = res.getString("color");
			    taste = res.getString("taste");    
			    stock = res.getInt("stock");
			    availables = res.getInt("availables");
			    salesPrice = res.getDouble("salesprice");
			    keyTypeProduct = res.getInt("keytypeproduct");
			}
		}
		catch(Exception e) {}
	}
		
	public List<Product> listProducts() {
		Product objPro;
		ArrayList<Product> arrPro = new ArrayList<Product>();

		objC = new Connect();
		conn = objC.getConn();
		
		try {
			String query = "SELECT p.*, tp.name as tp FROM product p JOIN type_product tp USING (keytypeproduct) order by 1";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				objPro = new Product();
				objPro.keyProduct = res.getInt("keyproduct");
				objPro.name = res.getString("name");				
				objPro.ml = res.getDouble("ml");
				objPro.color = res.getString("color");
				objPro.taste = res.getString("taste");
				objPro.stock = res.getInt("stock");
				objPro.salesPrice = res.getDouble("salesprice");
				objPro.tp = res.getString("tp");
				objPro.availables= res.getInt("availables");
				objPro.keyTypeProduct = res.getInt("keytypeproduct");
				arrPro.add(objPro);
			}
			conn.close();
		}
		catch(Exception e) {
		}
		return arrPro;
	}
	
	public void getObjProduct(int keyproduct) {
		try {
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM product WHERE keyproduct = "+keyproduct;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next()) {
				TypeProduct objTP = new TypeProduct();
				objTP.getObjTypeProduct(res.getInt("keytypeproduct"));
				
				this.keyProduct = keyproduct;
				this.name = res.getString("name");				
				this.ml = res.getDouble("ml");
				this.color = res.getString("color");
				this.taste = res.getString("taste");
				this.stock = res.getInt("stock");
				this.salesPrice = res.getDouble("salesprice");				
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
	}
	
	
	//=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)		
	public int getKeyProduct() {
		return keyProduct;
	}
	public void setKeyProduct(int keyProduct) {
		this.keyProduct = keyProduct;
	}

	@XmlElement(required=true)
	public String getName() {
		return name;
	}

	@XmlElement(required=true)
	public double getMl() {
		return ml;
	}

	@XmlElement(required=true)
	public String getColor() {
		return color;
	}

	@XmlElement(required=true)
	public String getTaste() {
		return taste;
	}

	@XmlElement(required=true)
	public int getStock() {
		return stock;
	}

	@XmlElement(required=true)
	public double getSalesPrice() {
		return salesPrice;
	}

	@XmlElement(required=true)
	public int getKeyTypeProduct() {
		return keyTypeProduct;
	}

	@XmlElement(required=true)
	public String getTp() {
		return tp;
	}
	
	@XmlElement(required=true)
	public int getAvailables() {
		return availables;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMl(double ml) {
		this.ml = ml;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public void setKeyTypeProduct(int keyTypeProduct) {
		this.keyTypeProduct = keyTypeProduct;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public void setAvailables(int availables) {
		this.availables = availables;
	}	
}
