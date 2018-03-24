package net.idontrea.todo.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import net.idontrea.todo.model.Task;
import net.idontrea.todo.model.User;

@Stateless
public class TaskDAO extends GenericDAO<Task>
{
	public TaskDAO()
	{
		super(Task.class);
	}
	
	public List<Task> findAllByUser(User user)
	{
		List<Task> ret=null;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("owner", user);
		ret=this.findAll(Task.FIND_BY_USER, parameters, 10);
		
		return ret;
	}
	
	public Task submitTask(Task t)
	{
		Task ret=null;
		if(this.find(t.getId())==null)
		{
			ret=this.create(t);
		}
		else
		{
			ret=this.update(t);
		}
		
		return ret;
	}
}
