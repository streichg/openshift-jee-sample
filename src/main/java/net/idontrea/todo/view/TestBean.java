package net.idontrea.todo.view;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.idontrea.todo.control.TestDAO;
import net.idontrea.todo.model.Test;

@ManagedBean
@SessionScoped
public class TestBean implements Serializable
{
	private static final long serialVersionUID=1L;
	
	@EJB
	private TestDAO testDAO;
	
	public List<Test> getTests()
	{
		return testDAO.findAll();
	}
}
