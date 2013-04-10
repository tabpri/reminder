package net.enclosing.reminder;

import java.util.Date;

public class Reminder {
	private String name = "";
	private Date startDate = new Date();
	private String project = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
}
