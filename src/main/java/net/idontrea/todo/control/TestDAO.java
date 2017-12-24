package net.idontrea.todo.control;

import javax.ejb.Stateless;

import net.idontrea.todo.model.Test;

@Stateless
public class TestDAO extends GenericDAO<Test>
{
	public TestDAO()
	{
		super(Test.class);
	}
}
