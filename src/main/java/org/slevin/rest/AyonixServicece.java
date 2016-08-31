package org.slevin.rest;
 
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slevin.common.Notification;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dao.NotificationDao;
import org.slevin.dto.AlarmHistoryDto;
import org.slevin.servlet.TwilioUtil;
import org.slevin.util.Constants;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.twilio.sdk.Twilio;
 
@Path("/hello")
public class AyonixServicece {
 
	@Context
	private ServletContext context=null; 
	
	
	
	@GET
	@Path("/ayonix/newinsert")
	public Response getMsg() throws Exception {
 
		Calendar c = Calendar.getInstance();
		Date endDate = c.getTime();
		//c.roll(Calendar.MINUTE, -10);
		c.roll(Calendar.DAY_OF_YEAR, -1);
		Date startDate = c.getTime();
		
		ServletContext  servletContext =(ServletContext) context;
    	BeanFactory context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		AlarmHistoryDao alarmHistoryService= (AlarmHistoryDao)context.getBean("alarmHistoryService");
		

		List<AlarmHistoryDto> list =alarmHistoryService.findAlarmHistory(startDate, endDate, null);
	
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			AlarmHistoryDto alarmHistoryDto = (AlarmHistoryDto) iterator.next();
			notifyForParent(alarmHistoryDto);
			notifyForWelcome(alarmHistoryDto);
		}
		
		return Response.status(200).build();
 
	}
	
	public void notifyForParent(AlarmHistoryDto alarmHistoryDto) throws Exception{
		ServletContext  servletContext =(ServletContext) context;
    	BeanFactory context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		NotificationDao notificationService= (NotificationDao)context.getBean("notificationService");
		
		Notification notification = notificationService.findNotification(alarmHistoryDto.getAlarmHistory().getId(), Constants.NOTIFICATION_NOTIFY_PARENT);
		if(notification!=null)
			return;
		
		String telno=findPhoneNumber(alarmHistoryDto,1);
		if(StringUtils.isEmpty(telno))
			return;
		
		String message = Constants.USER_PARENT_MESSAGE.replace("{0}", alarmHistoryDto.getEnrollPerson().getName());
		message = message.replace("{1}", (new Date()).toString());
		Notification notification2 = createNotification(alarmHistoryDto, Constants.NOTIFICATION_NOTIFY_PARENT,telno);
		notification2.setMessage(message);
		try {
			notification2.setResult(TwilioUtil.sendSms(telno, message));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notificationService.persist(notification2);
	}
	
	public void notifyForWelcome(AlarmHistoryDto alarmHistoryDto) throws Exception{
		ServletContext  servletContext =(ServletContext) context;
    	BeanFactory context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		NotificationDao notificationService= (NotificationDao)context.getBean("notificationService");
		
		Notification notification = notificationService.findNotification(alarmHistoryDto.getAlarmHistory().getId(), Constants.NOTIFICATION_INSERT_NEW);
		if(notification!=null)
			return;
		
		String telno=findPhoneNumber(alarmHistoryDto,0);
		if(StringUtils.isEmpty(telno))
			return;
		
		Notification notification2 = createNotification(alarmHistoryDto, Constants.NOTIFICATION_INSERT_NEW,telno);
		notification2.setMessage(Constants.USER_NOTIFICATION_MESSAGE);
		try {
			notification2.setResult(TwilioUtil.sendSms(telno, Constants.USER_NOTIFICATION_MESSAGE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notificationService.persist(notification2);
	}
	
	public String findPhoneNumber(AlarmHistoryDto alarmHistoryDto,int index){
		if(StringUtils.isEmpty(alarmHistoryDto.getEnrollPerson().getAlarmEmailValue()))
			return null;
		
		String[] s = alarmHistoryDto.getEnrollPerson().getAlarmEmailValue().split(",");
		String a = s[index];
		return a.replace("phone:", "");
	}
	
	public Notification createNotification(AlarmHistoryDto alarmHistoryDto,String purpose,String telNo){
		Notification notification2 = new Notification();
		notification2.setAlarmId(alarmHistoryDto.getAlarmHistory().getId());
		notification2.setDate(new Date());
		notification2.setPurpose(purpose);
		notification2.setUserid(alarmHistoryDto.getEnrollPerson().getId());
		notification2.setUsername(alarmHistoryDto.getEnrollPerson().getName()+" "+alarmHistoryDto.getEnrollPerson().getSurname());
		notification2.setTelno(telNo);
		
		return notification2;
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