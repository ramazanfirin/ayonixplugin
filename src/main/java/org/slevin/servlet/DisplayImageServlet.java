package org.slevin.servlet;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slevin.common.FaceAlarmParameters;
import org.slevin.dao.FaceAlarmParametersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 

 
// /image?id=123

public class DisplayImageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Autowired
	private FaceAlarmParametersDao faceAlarmParametersDao;
 
  public DisplayImageServlet() {
      super();
  }
 
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

	  		Long id = null;
      
          id = Long.parseLong(request.getParameter("id"));
      
          FaceAlarmParameters a = null;
          
		try {
			 WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
			   FaceAlarmParametersDao faceAlarmParametersDao = (FaceAlarmParametersDao)webApplicationContext.getBean("faceAlarmParametersService");
			a = faceAlarmParametersDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
//          response.setHeader("Content-Type", "image/jpg");
//        
//          response.setHeader("Content-Length", String.valueOf(person.getImageData().length));
////        
//          response.setHeader("Content-Disposition", "inline; filename=\"" + person.getImageFileName() + "\"");
 
          // Write image data to Response.
		
		response.setContentType("image/jpg");
		
          response.getOutputStream().write(a.getAlarmImage());
          response.getOutputStream().flush();
      
  }
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }
 
}