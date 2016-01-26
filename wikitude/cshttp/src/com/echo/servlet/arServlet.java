package com.echo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class arServlet
 */
@WebServlet("/arServlet")
public class arServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private getTargetInfoFromMysql gti;
	private String targetINfo =null ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public arServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String targetName = request.getParameter("targetName");
		System.out.println("targetName:" + targetName);
		try {
			 targetINfo = gti.getTargetInfoFromMysql(targetName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8"); 
		response.getOutputStream().write(targetINfo.getBytes());
		
	}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
