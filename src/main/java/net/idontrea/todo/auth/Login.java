package net.idontrea.todo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet
{
	private static final long serialVersionUID=1L;
	
	public Login()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doLogin(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doLogin(request, response);
	}
	
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		String redirectURL;
		
		session.setAttribute("originator",  request.getHeader("Referer"));
		
		if(session.getAttribute("originator")==null)
		{
			redirectURL="index.jsf";
		}
		else
		{
			redirectURL=session.getAttribute("originator").toString();
		}
		
		if(((request.getRemoteUser()==null)) && (request.getParameter("j_username")!=null))
		{	
			try
			{
				request.login((String)request.getParameter("j_username"), (String)request.getParameter("j_password"));
			}
			catch(ServletException e)
			{
				e.printStackTrace();
			}
			response.sendRedirect(response.encodeRedirectURL(redirectURL));
		}
		else
		{
			response.sendRedirect(response.encodeRedirectURL(redirectURL));
		}
	}
}