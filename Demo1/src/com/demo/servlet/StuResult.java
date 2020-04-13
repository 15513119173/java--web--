package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dao.QuesstionsDao;
import com.demo.dao.StuResultDao;
import com.demo.entity.QueryResultIfForm;

public class StuResult extends HttpServlet {
    private QuesstionsDao quesstionsDao=new QuesstionsDao();
    private StuResultDao stuResultDao=new StuResultDao();
	/**
	 * Constructor of the object.
	 */
	public StuResult() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if ("stuResultQuery".equals(action)) {
			stuResultQuery(request, response);
		}else if ("stuResultQueryS".equals(action)) {
			stuResultQueryS(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else if ("stuResultQuery".equals(action)) {
			stuResultQuery(request, response);
		}else if ("stuResultQueryS".equals(action)) {
			stuResultQueryS(request, response);
		}else{
			request.setAttribute("error", "操作失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	private void stuResultQuery(
			HttpServletRequest request, HttpServletResponse response) {
		QueryResultIfForm ifForm = new QueryResultIfForm();
		    if (request.getParameter("id")==null){
		    	request.setAttribute("stuResultQuery", stuResultDao.query(""));
		    }else{
				ifForm.setQueryIf(request.getParameter("queryIf"));
				ifForm.setKey(request.getParameter("key"));
				request.setAttribute("stuResultQuery", stuResultDao.query(ifForm));
		    }
			
		
		try {
			request.getRequestDispatcher("/manage/main.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void stuResultQueryS(
		HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("stuResultQuery", stuResultDao.query(request.getParameter("ID").toString()));
		try {
			request.getRequestDispatcher("stuResultQuery.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
