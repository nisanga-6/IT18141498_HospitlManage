package com;

import com.user;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userAPI
 */
@WebServlet("/userAPI")
public class userAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	user userObj =new user();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = userObj.insertUser(request.getParameter("username"),request.getParameter("name"),request.getParameter("password"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = userObj.updateUser(paras.get("hiduserIDReg").toString(),paras.get("username").toString(),paras.get("name").toString(),paras.get("password").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		System.out.println(paras.get("userid").toString());
		
		String output = userObj.deleteUser(paras.get("userid").toString());
		
		response.getWriter().write(output);
		// TODO Auto-generated method stub
	}
	
	
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	
	System.out.println(p[0]);
	
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
		e.printStackTrace();
	 }
	return map;
	}


}
