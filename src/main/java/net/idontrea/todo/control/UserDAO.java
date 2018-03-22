package net.idontrea.todo.control;

import javax.xml.registry.infomodel.User;

public class UserDAO extends GenericDAO<User>
{
	public UserDAO()
	{
		super(User.class);
	}
}
