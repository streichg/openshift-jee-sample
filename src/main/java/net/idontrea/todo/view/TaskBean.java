package net.idontrea.todo.view;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import net.idontrea.todo.control.TaskDAO;
import net.idontrea.todo.control.UserDAO;
import net.idontrea.todo.model.Task;
import net.idontrea.todo.model.User;

@ManagedBean
@SessionScoped
public class TaskBean implements Serializable
{
	private static final long serialVersionUID=1L;
	
	@EJB
	private TaskDAO taskDAO;
	
	@EJB
	private UserDAO userDAO;
	
	private Task task;
	private String dueString;
	
	public TaskBean()
	{
		this.task=new Task();
	}
	
	public List<Task> getUserTasks(User user)
	{
		return taskDAO.findAllByUser(user);
	}
	
	public String submit()
	{
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession) facesContext.getExternalContext().getSession(true);
		User u=(User)session.getAttribute("User");

		u.addTask(this.task);
		
		taskDAO.submitTask(this.task);
		userDAO.update(u);
		this.task=new Task();
		
		return "Task.jsf";
	}
	
	public String remove(int id)
	{
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession) facesContext.getExternalContext().getSession(true);
		User u=(User)session.getAttribute("User");

		Task remove=null;
		
		for(Task t : u.getTasks())
		{
			if(t.getId()==id)
			{
				remove=t;
			}
		}
		
		if(remove!=null)
		{
			u.getTasks().remove(remove);
			userDAO.update(u);
			taskDAO.delete(remove);
		}
		
		return "Task.jsf";
	}
	
	public void setTask(Task task)
	{
		this.task=task;
	}
	
	public Task getTask()
	{
		return this.task;
	}
	
	public void setDueString(String dueString)
	{
		DateTimeFormatter formatter=DateTimeFormat.forPattern("MM/dd/yy");
		this.task.setDue(formatter.parseDateTime(dueString));
	}
	public String getDueString()
	{
		return this.dueString;
	}
}
