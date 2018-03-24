package net.idontrea.todo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity

public class Task implements Serializable
{
	private static final long serialVersionUID=1;
	
	public static final String FIND_BY_USER="findTaskByUser";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String description;
	
	private DateTime due;
	private int priority;
	
	public Task()
	{
		this.due=new DateTime();
	}
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public DateTime getDue()
	{
		return due;
	}
	public void setDue(DateTime due)
	{
		this.due = due;
	}
	public void setDue(String due)
	{
		System.out.println("Date Submited: " + due);
		DateTimeFormatter formatter=DateTimeFormat.forPattern("MM/dd/yy");
		this.due=formatter.parseDateTime(due);
	}
	public int getPriority()
	{
		return priority;
	}
	public void setPriority(int priority)
	{
		if(priority>5)
			priority=5;
		else if(priority<1)
			priority=1;
		this.priority = priority;
	}
}
