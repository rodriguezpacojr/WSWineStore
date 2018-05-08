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

import model.Log;
import model.TypeProduct;

@Path("/typeproduct")
public class WSTypeProduct 
{	
	@POST
	@Path("/inserttypeproduct/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TypeProduct insertTypeProduct(TypeProduct objTP, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objTP.insertTypeProduct();
			return objTP;
		}
		else
			return null;				
	}
	
	@PUT
	@Path("/updatetypeproduct/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatetypecourse(TypeProduct objTP, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
			objTP.updateTypeProduct();
	}
	
	@DELETE
	@Path("/deletetypeproduct/{keytypeproduct}/{token}")
	public void deletetypeproduct(@PathParam("keyTypeProduct") int keyTypeProduct, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			TypeProduct objTP = new TypeProduct();
			objTP.setKeyTypeProduct(keyTypeProduct);		
			objTP.deleteTypeProduct();
		}
	}
	
	@GET
	@Path("/listtypeproducts/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TypeProduct> listtypeproduct(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			TypeProduct objTP = new TypeProduct();
			return objTP.listTypeProducts();
		}
		else
			return null;				
	}	
}