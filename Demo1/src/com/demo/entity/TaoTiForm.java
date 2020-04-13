package com.demo.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaoTiForm {
	private int lessonId;
	public TaoTiForm(int iD, String name, int lessonId, Date joinTime) {
		super();
		this.lessonId = lessonId;
		this.joinTime = joinTime;
		this.name = name;
		ID = iD;
	}
	public TaoTiForm(){
		
	}
	private Date joinTime;
	private String name;
	private int ID;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String delIdArray="";
	private String[] nameArray=new String[0];
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getDelIdArray(){
		return delIdArray;
	}
	public void setDelIdArray(String delIdArray){
		this.delIdArray=delIdArray;
	}
	public String[] getNameArray(){
		return nameArray;
	}
	public void setNameArray(String[] nameArray){
		this.nameArray=nameArray;
	}
	public void setJoinTime(String format) {
		try {
			this.joinTime=sdf.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}