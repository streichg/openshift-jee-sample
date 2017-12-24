package net.idontrea.todo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Test implements Serializable
{
	private static final long serialVersionUID=1;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private long value;

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public long getValue() 
	{
		return value;
	}

	public void setValue(long value) 
	{
		this.value = value;
	}
}