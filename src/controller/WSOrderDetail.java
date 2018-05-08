package controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.OrderDetail;
import model.Log;

@Path("/orderdetail")
public class WSOrderDetail 
{	
	@POST
	@Path("/insertorderdetail/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderDetail insertOrderDetail(OrderDetail objOD, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objOD.insertOrderDetail();
			return objOD;
		}
		else
			return null;		
	}
		
	@GET
	@Path("/listorderdetails/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderDetail> listOrderDetail(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			OrderDetail objOD = new OrderDetail();
			return objOD.listOrderDetail();
		}
		else
			return null;		
	}	
}