package org.slevin.prime.faces.bean;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slevin.common.v2.Afid;
import org.slevin.common.v2.Person;
import org.slevin.common.v2.PersonCategory;
import org.slevin.dao.AfidDao;
import org.slevin.dao.PersonCategoryDao;
import org.slevin.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ayonix.AynxFace;
import ayonix.AynxImage;



@Component(value="personManagementMB")
@SessionScoped
public class PersonManagementMB extends BaseMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private PersonDao itemDao;
	
	@Autowired
	private AfidDao afidDao;
	
	@Autowired
	private PersonCategoryDao personCategoryDao;
	

	private List<Person> itemList;
	private Person item = new Person();
	private String name;
	private String surname;
	private UploadedFile file;
	
	private Afid selectedAfid;
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }
	
	public List<PersonCategory> getPersonCategoryList() throws Exception{
		return personCategoryDao.findAll();
	}
	
	public void deleteAfid() throws Exception{
		afidDao.removeAfid(selectedAfid.getId());
		addMessage("silme islemi tamamlandi");
		item = itemDao.findById(item.getId());
	}


	public void createPerson() throws Exception{
		try {
			itemDao.persist(item);
			byte[] a =file.getContents();
			File file1 = new File(getImagePath()+"//"+item.getId());
			if(!file1.mkdir()){
				addMessage("directory create error");
				return;
			}
			
			File file3 = new File(getImagePath()+"//"+item.getId()+"//alarmImages");
			if(!file3.mkdir()){
				addMessage("directory create error");
				return;
			}
			
			File file2 = new File(getImagePath()+"//"+item.getId()+"//"+file.getFileName());
			if(!file2.createNewFile()){
				addMessage("file create error");
				return;
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file2);
			fileOutputStream.write(a);
			fileOutputStream.close();
			
			item.setMugshot("/"+item.getId()+"/"+file.getFileName());
			itemDao.merge(item);
			
			addMessage("Person created");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(e.getMessage());
			itemDao.remove(item.getId());
		} 
	}

	public void handleFileUpload(FileUploadEvent event) throws Exception {
		
		File file2 = new File(getImagePath()+"//"+item.getId()+"//"+event.getFile().getFileName());
		if(file2.exists()){
			addMessage("Dosya Mevcut");
			return;
		}
		if(!file2.createNewFile()){
			addMessage("file create error");
			return;
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file2);
		fileOutputStream.write(event.getFile().getContents());
		fileOutputStream.close();
		
		item.setMugshot("/"+item.getId()+"/"+event.getFile().getFileName());
		itemDao.merge(item);
		
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String saveToFile(Long id,UploadedFile uploadedFile) throws IOException, Exception{
		String name="//"+id+"//"+uploadedFile.getFileName();
		File file2 = new File(getImagePath()+name);
		if(file2.exists()){
			addMessage("Dosya Mevcut");
			throw new Exception("Dosya Mevcut");
		}
		
		if(!file2.createNewFile()){
			addMessage("file create error");
			throw new Exception("file create error");
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file2);
		fileOutputStream.write(uploadedFile.getContents());
		fileOutputStream.close();
		return name;
	}
	
	public void handleFileUploadImageList(FileUploadEvent event) throws Exception {
		try {
			Afid afid= new Afid();
			String fileName = saveToFile(item.getId(), event.getFile());
			afid.setImageLocation(fileName);
			afid.setPerson(item);
			AynxImage img = sdk.LoadImage(event.getFile().getContents());
			AynxFace[] faces = sdk.DetectFaces(img);
			AynxFace face = faces[0];
			sdk.PreprocessFace(face);
			byte[] query = sdk.CreateAfid(face);
			afid.setAfid(query);
			afidDao.persist(afid);
			item=itemDao.findById(item.getId());
			addMessage("resim Yüklendi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}	
	public void create() throws Exception{
		item = new Person();
		item.setName("");
		item.setSurname(surname);
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
		//System.out.println("alarm size="+enrollPersonList.size());
		
	}



	public List<Person> getItemList() {
		return itemList;
	}



	public void setItemList(List<Person> itemList) {
		this.itemList = itemList;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public UploadedFile getFile() {
		return file;
	}



	public void setFile(UploadedFile file) {
		this.file = file;
	}



	public Person getItem() {
		return item;
	}



	public void setItem(Person item) {
		this.item = item;
	}


	public Afid getSelectedAfid() {
		return selectedAfid;
	}


	public void setSelectedAfid(Afid selectedAfid) {
		this.selectedAfid = selectedAfid;
	}



	



	

	
	
	
	
}
