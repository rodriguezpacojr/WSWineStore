package controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Product;
import model.Log;
import model.Order;

@Path("/product")
public class WSProduct 
{	
	@POST
	@Path("/insertproduct/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Product insertProduct(Product objPro, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objPro.insertProduct();
			return objPro;
		}
		else 
			return null;		
	}
	
	@PUT
	@Path("/updateproduct/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProduct(Product objPro, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objPro.updateProduct();
		}
	}
	
	@PUT
	@Path("/updateavailables/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateAvailables(Product objPro, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objPro.updateAvailables();
		}
	}
	
	@DELETE
	@Path("/deleteproduct/{keyProduct}/{token}")
	public void deleteProduct(@PathParam("keyProduct") int keyProduct, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Product objPro = new Product();
			objPro.setKeyProduct(keyProduct);		
			objPro.deleteProduct();
		}		
	}
	
	@GET
	@Path("/getproduct/{keyProduct}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product product(@PathParam("keyProduct") int keyProduct, @PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Product obj = new Product();
			obj.setKeyProduct(keyProduct);
			obj.getProduct();
			return obj;
		}
		else
			return null;	
	}
	
	@GET
	@Path("/listproducts/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> listProduct(@PathParam("token") String token)
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Product objPro = new Product();
			return objPro.listProducts();
		}
		else 
			return null;
	}	
}