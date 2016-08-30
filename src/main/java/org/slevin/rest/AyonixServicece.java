package org.slevin.rest;
 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
@Path("/hello")
public class AyonixServicece {
 
	@Context
	private ServletContext context=null; 
	
	
	
	@GET
	@Path("/test/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/test")
	public Response getUsers(
		@QueryParam("id") int id) {
System.out.println("getUsers is called, id : " + id);
		return Response
		   .status(200)
		   .entity("getUsers is called, id : " + id).build();

	}
	
 
}