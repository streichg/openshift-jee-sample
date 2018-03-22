package net.idontrea.todo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable
{
	private static final long serialVersionUID=1;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String name;
	private String des;
	
	public String getDes()
	{
		return des;
	}

	public void setDes(String des)
	{
		this.des = des;
	}

	@ManyToMany
	@JoinTable
	private List<User> users=new ArrayList<>();

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public List<User> getUsers() 
	{
		return users;
	}

	public void setUsers(List<User> users) 
	{
		this.users = users;
	}
	
	public void addUser(User user)
	{
		if(!this.users.contains(user))
		{
			this.users.add(user);
		}
		
		if(!user.getRoles().contains(this))
		{
			user.getRoles().add(this);
		}
	}
	
	public void removeUser(User user)
	{
		this.users.remove(user);
		user.getRoles().remove(this);
	}
}
