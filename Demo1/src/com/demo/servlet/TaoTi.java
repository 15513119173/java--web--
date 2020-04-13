package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dao.LessonDao;
import com.demo.dao.TaoTiDao;
import com.demo.entity.TaoTiForm;

public class TaoTi extends HttpServlet {
    private LessonDao lessonDao=new LessonDao();
    private TaoTiDao taoTiDao=new TaoTiDao();
    private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	/**
	 * Constructor of the object.
	 */
	public TaoTi() {
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
		String actString="";
		if(action.length()>=16){
			actString=action.substring(0,16);
			System.out.println("获取的查询字符串：" + actString);
		}
		if ("taoTiQuery".equals(action)) {
			taoTiQuery(request, response);
		} else if("taoTiAddQuery".equals(action)){
			taoTiAddQuery(request,response);			
		} else if ("taoTiDel".equals(action)) {
			taoTiDel(request, response);
		}else if("taoTiModifyQuery".equals(actString)){
			String act=action.substring(16);
			System.out.println("获取的查询字符串：" + act);
			taoTiModifyQuery(request,response,Integer.parseInt(act));
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
		} else if ("taoTiAdd".equals(action)) {
			taoTiAdd(request, response);
		} else if ("taoTiDel".equals(action)) {
			taoTiDel(request, response);
		}else if("taoTiModify".equals(action)){
			taoTiModify(request,response);
		}else{
			request.setAttribute("error", "操作失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	private void taoTiQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("taoTiQuery", taoTiDao.query(0));
		try {
			request.getRequestDispatcher("manage/taoTi.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void taoTiAdd(
			HttpServletRequest request, HttpServletResponse response) {
		TaoTiForm taoTiForm = new TaoTiForm();
		taoTiForm.setName(request.getParameter("name"));
		taoTiForm.setLessonId(Integer.parseInt(request.getParameter("lessonId")));
		taoTiForm.setJoinTime(new Date());
		int ret = taoTiDao.insert(taoTiForm);
		System.out.println("返回值ret：" + ret);
		try{
		if (ret == 1) {
			request.getRequestDispatcher("manage/taoTi_ok.jsp?para=1").forward(request, response);
		} else if (ret == 2) {
			request.setAttribute("error", "该套题已经添加！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "添加套题失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void taoTiAddQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("lessonList",lessonDao.query(0));	//全部课程列表
		try {
			request.getRequestDispatcher("manage/taoTi_Add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void taoTiModifyQuery(
			HttpServletRequest request, HttpServletResponse response,int id) {
		TaoTiForm taoTiForm=(TaoTiForm)((taoTiDao.query(id)).get(0));
		request.setAttribute("taoTiModifyQuery", taoTiForm);
		request.setAttribute("lessonList",lessonDao.query(0));	//全部课程列表
		try {
			request.getRequestDispatcher("manage/taoTi_Modify.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void taoTiModify(
			HttpServletRequest request, HttpServletResponse response) {
		TaoTiForm taoTiForm = new TaoTiForm();
		taoTiForm.setID(Integer.parseInt(request.getParameter("ID")));
		taoTiForm.setName(request.getParameter("name"));
		taoTiForm.setLessonId(Integer.parseInt(request.getParameter("lessonId")));
		int ret = taoTiDao.update(taoTiForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "修改套题失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/taoTi_ok.jsp?para=2").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void taoTiDel(
			HttpServletRequest request, HttpServletResponse response) {
		TaoTiForm taoTiForm = new TaoTiForm();
		taoTiForm.setDelIdArray(request.getParameter("ID"));
		System.out.print(request.getParameter("ID"));
		int ret = taoTiDao.delete(taoTiForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "删除套题失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/taoTi_ok.jsp?para=3").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
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
