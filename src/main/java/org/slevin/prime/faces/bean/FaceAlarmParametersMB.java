package org.slevin.prime.faces.bean;


import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slevin.common.v2.FaceAlarmParameters;
import org.slevin.dao.FaceAlarmParametersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="faceAlarmParametersMB")
@SessionScoped
public class FaceAlarmParametersMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private FaceAlarmParametersDao faceAlarmParametersDao;
	

	private List<FaceAlarmParameters> faceAlarmParametersList;
	
	private Date searchDate;
	
	private String searchClass;
	
	@PostConstruct
    public void init() throws Exception {
		//refreshList();
    }

	public void search() throws Exception{
		refreshList();
	}
	
	public StreamedContent getImage() throws Exception{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  String action = params.get("id");
		Long id = new Long(action);
		byte[] b = faceAlarmParametersDao.findById(id).getAlarmImage();
		StreamedContent image = new DefaultStreamedContent(new ByteArrayInputStream(b),"image/jpg");
		return image;
	}
	
	public StreamedContent getImage2(Long id) throws Exception{
//	s
		byte[] b = faceAlarmParametersDao.findById(id).getAlarmImage();
		StreamedContent image = new DefaultStreamedContent(new ByteArrayInputStream(b),"image/jpg");
		return image;
	}


	public void refreshList() throws Exception {
		faceAlarmParametersList = faceAlarmParametersDao.find(searchDate, searchClass);
		System.out.println("alarm size="+faceAlarmParametersList.size());
		
	}



	public List<FaceAlarmParameters> getFaceAlarmParametersList() {
		return faceAlarmParametersList;
	}



	public void setFaceAlarmParametersList(
			List<FaceAlarmParameters> faceAlarmParametersList) {
		this.faceAlarmParametersList = faceAlarmParametersList;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public String getSearchClass() {
		return searchClass;
	}

	public void setSearchClass(String searchClass) {
		this.searchClass = searchClass;
	}



	
	
	
	
	
}
