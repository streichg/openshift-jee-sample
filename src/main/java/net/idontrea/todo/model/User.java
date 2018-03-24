package net.idontrea.todo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="User.findUserByEmail", query="SELECT u FROM User u WHERE u.email =:email"),
	@NamedQuery(name="User.findUserByName", query="SELECT u FROM User u WHERE u.username =:name")
})
public class User implements Serializable
{
	private static final long serialVersionUID=1;
	
	public static final String FIND_BY_USERNAME="User.findUserByName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String username;
	
	@Transient
	private String password;
	
	private String encryptedPassword;
	private Boolean enabled=true;
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable
	private List<Role> roles=new ArrayList<>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST})
	private List<Task> tasks=new ArrayList<>();
	

	public List<Task> getTasks()
	{
		return tasks;
	}

	public void setTasks(List<Task> tasks)
	{
		this.tasks = tasks;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getEncryptedPassword() 
	{
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) 
	{
		this.encryptedPassword = encryptedPassword;
	}

	public Boolean getEnabled() 
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled) 
	{
		this.enabled = enabled;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(List<Role> roles) 
	{
		this.roles = roles;
	}

	public void addRole(Role role)
	{
		if(!this.roles.contains(role))
		{
			this.roles.add(role);
		}
		
		if(!role.getUsers().contains(this))
		{
			role.getUsers().add(this);
		}
	}
	
	public void removeRole(Role role)
	{
		this.roles.remove(role);
		role.getUsers().remove(this);
	}
	
	public void addTask(Task t)
	{
		if(!this.tasks.contains(t))
		{
			this.tasks.add(t);
		}
	}
	
	public void removeTask(Task t)
	{
		this.tasks.remove(t);
	}
}
