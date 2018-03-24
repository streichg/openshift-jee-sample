package net.idontrea.todo.control;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import net.idontrea.todo.model.User;

@Stateless
public class UserDAO extends GenericDAO<User>
{
	public UserDAO()
	{
		super(User.class);
	}
	
	public User findByUsername(String name)
	{
		User ret=null;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("name", name);
		ret=this.findOneResult(User.FIND_BY_USERNAME, parameters);
		
		return ret;
	}
}
