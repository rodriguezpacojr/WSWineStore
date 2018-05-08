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

import model.Employee;
import model.Log;

@Path("/employee")
public class WSEmployee 
{	
	@POST
	@Path("/insertemployee/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee insertEmployee(Employee objE, @PathParam("token") String token) 
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			objE.insertEmployee();
			return objE;
		}
		else
			return null;
	}
	
	@PUT
	@Path("/updateemployee/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmployee(Employee objE, @PathParam("token") String token) 
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))		
			objE.updateEmployee();
	}
	
	@DELETE
	@Path("/deleteemployee/{keyEmployee}/{token}")
	public void deleteEmployee(@PathParam("keyEmployee") int keyEmployee, @PathParam("token") String token) 
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			Employee objE = new Employee();
			objE.setKeyEmployee(keyEmployee);		
			objE.deleteEmployee();
		}
	}
	
	@GET
	@Path("/listemployees/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> listEmployee(@PathParam("token") String token)
	{			
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			Employee objE = new Employee();			
			return objE.listEmployees();
		}
		else
			return null;		
	}	
}