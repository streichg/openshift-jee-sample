package net.idontrea.todo.control;

import javax.ejb.Stateless;
import javax.management.relation.Role;

@Stateless
public class RoleDAO extends GenericDAO<Role>
{
	public RoleDAO()
	{
		super(Role.class);
	}
}
