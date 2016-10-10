package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slevin.common.v2.Clazz;
import org.slevin.common.v2.IpCamera;
import org.slevin.dao.AfidDao;
import org.slevin.dao.ClazzDao;
import org.slevin.dao.IpCameraDao;
import org.slevin.threads.CameraThread;
import org.slevin.threads.capture.BaseCapture;
import org.slevin.threads.capture.CaptureThreadBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;





@Component(value="ipCameraMB")
@ApplicationScoped
public class IpCameraMB extends BaseMB implements Serializable {
	
	//ExecutorService executorService = Executors.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	List<CameraThread> cameraThreadList = new ArrayList<CameraThread>();
    Map<String,BaseCapture> threadMap = new HashMap<String, BaseCapture>();
   
   
	
    Vector<byte[]> afidDatabase = new Vector<byte[]>();
    
	@Autowired
	private IpCameraDao itemDao;
	
	@Autowired
	private AfidDao afidDao;
	
	@Autowired
	private ClazzDao clazzDao;
	
	
	private List<IpCamera> itemList;
	private IpCamera item = new IpCamera();
	@PostConstruct
    public void init() throws Exception {
		List<IpCamera> list= itemDao.findAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			IpCamera ipCamera = (IpCamera) iterator.next();
			itemDao.updateStatus(ipCamera.getId(), "STOPPED");
		}
		
		refreshList();
		refreshAfids();
    
	}
	
	public List<Clazz> getClazzList() throws Exception{
		return clazzDao.findAll();
	}
	
	public void updateDatatable(){
		
	}
	
	public BaseCapture getCameraThread(String cameraName){
		return threadMap.get(cameraName);
	}
	
	public WebApplicationContext getContext(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return context;
	}
	
	public void startCamera(){
		BaseCapture thread = getCameraThread(item.getName());
		if(thread!=null){
			thread.requestStop();
		}	
		BaseCapture cameraThread = CaptureThreadBuilder.getThread(item, getContext(), sdk);

		cameraThread.start();
		threadMap.put(item.getName(), cameraThread);
		addMessage("camera started");
	}
	
	public void stopCamera(){
		BaseCapture thread = getCameraThread(item.getName());
		thread.requestStop();
		addMessage("camera stop");
	}
	

	
	public void startStopCamera(){
//		if(item.getStatus().equals("STOP") || item.getStatus().equals(""))
//			startCamera(item);
//		else if(item.getStatus().equals("RUNNING"))	
//			stopCamera(item);
	}

	public void create() throws Exception{
		item = new IpCamera();
		item.setName("");
		System.out.println("create");
	}
	
	public void merge() throws Exception{
		//clazz.setName(name);
		itemDao.merge(item);
		refreshList();
		addMessage("Islem Tamamlandi");
	}
	
	public void delete() throws Exception{
		itemDao.remove(item.getId());
		refreshList();
		addMessage("Islem Tamamlandi");
	}


	public void refreshList() throws Exception {
		itemList = itemDao.findAll();
		System.out.println("alarm size="+itemList.size());
		
	}
	
	public void refreshAfids() throws Exception{
		List<byte[]> a = afidDao.loadAfidData();
		afidDatabase.clear();
		afidDatabase.addAll(a);
	}
	
	

	public List<IpCamera> getItemList() {
		return itemList;
	}

	public void setItemList(List<IpCamera> itemList) {
		this.itemList = itemList;
	}

	public IpCamera getItem() {
		return item;
	}

	public void setItem(IpCamera item) {
		this.item = item;
	}
	public Vector<byte[]> getAfidDatabase() {
		return afidDatabase;
	}
	public void setAfidDatabase(Vector<byte[]> afidDatabase) {
		this.afidDatabase = afidDatabase;
	}








	
	
	
	
}
