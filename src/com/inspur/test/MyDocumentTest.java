package com.inspur.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.openkm.frontend.client.OKMException;
import com.openkm.frontend.client.bean.GWTDocument;
import com.openkm.frontend.client.widget.filebrowser.GWTFilter;
import com.openkm.servlet.frontend.DocumentServlet;

/**
 * Servlet implementation class MyDocumentTest
 */
@WebServlet("*.Test")
public class MyDocumentTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDocumentTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("***********DOGET***********");
		HttpSession session = request.getSession();
		String path = "/okm:root/New folder";
		DocumentServlet servlet = new DocumentServlet();
		try
		{
			List<GWTDocument> docList = servlet.getChilds(path, new HashMap<String,GWTFilter>());
			session.setAttribute("docList", docList);
			
		} catch (OKMException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		response.sendRedirect("fileList.jsp");
        //request.getRequestDispatcher("/NewFile.jsp ").forward(request,response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("***********DOPOST***********");
	}

}
