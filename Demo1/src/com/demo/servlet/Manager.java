package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.ManagerDao;
import com.demo.entity.ManagerForm;

public class Manager extends HttpServlet {
    private ManagerDao managerDao=new ManagerDao();
	/**
	 * Constructor of the object.
	 */
	public Manager() {
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
		String actString=action.substring(0,8);
		System.out.println("获取的查询字符串：" + actString);
		if ("managerQuery".equals(action)) {
			managerQuery(request, response);
		} else if ("queryPWD".equals(actString)) {
			String act=action.substring(8);
			System.out.println("获取的查询字符串：" + act);
			pwdQuery(request, response,Integer.parseInt(act));
		} else if ("managerD".equals(actString)) {
			String act=action.substring(10);
			System.out.println("获取的查询字符串：" + act);
			managerDel(request, response,Integer.parseInt(act));
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
			throws ServletException,IOException{
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else if ("login".equals(action)) {
			managerLogin(request, response);
		}  else if ("managerAdd".equals(action)) {
			managerAdd(request, response);
		} else if ("modifypwd".equals(action)) {
			modifypwd(request, response);
		}else{
			request.setAttribute("error", "操作失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	public void managerLogin(
			HttpServletRequest request, HttpServletResponse response) {
		ManagerForm managerForm = new ManagerForm();
		String nameString=request.getParameter("name");
		String pwsString=request.getParameter("pwd");
		managerForm.setName(nameString);
		managerForm.setPwd(pwsString);
		int ret = managerDao.checkManager(managerForm);
		System.out.print("验证结果ret的值:" + ret);
		try{
			if (ret == 2) {
				request.setAttribute("error", "您输入的管理员名称或密码错误！");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				System.out.println("111");
				HttpSession session = request.getSession();
				session.setAttribute("manager", managerForm.getName());
				request.getRequestDispatcher("StuResult?action=stuResultQuery").forward(request, response);
				System.out.println("111");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void managerQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("managerQuery", managerDao.query(0));
		ManagerForm naForm=(ManagerForm) managerDao.query(0).get(0);
		try {
			request.getRequestDispatcher("manage/manager.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void managerAdd(
			HttpServletRequest request, HttpServletResponse response) {
		ManagerForm managerForm = new ManagerForm();
		managerForm.setName(request.getParameter("name"));
		managerForm.setPwd(request.getParameter("pwd"));
		int ret = managerDao.insert(managerForm);
		System.out.println("返回值ret：" + ret);
		try{
		if (ret == 1) {
			request.getRequestDispatcher("manage/manager_ok.jsp?para=1").forward(request, response);
		} else if (ret == 2) {
			request.setAttribute("error", "该管理员信息已经添加！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "添加管理员信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void pwdQuery(
			HttpServletRequest request, HttpServletResponse response,int id) {
		System.out.print(((ManagerForm)managerDao.query(id).get(0)).getID());
		request.setAttribute("pwdQueryif", managerDao.query(id));
		try {
			request.getRequestDispatcher("manage/pwd_Modify.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void modifypwd(
			HttpServletRequest request, HttpServletResponse response) {
		ManagerForm managerForm = new ManagerForm();
		managerForm.setName(request.getParameter("name"));
		managerForm.setPwd(request.getParameter("newpwd"));
		int ret = managerDao.updatePwd(managerForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "修改管理员密码失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/pwd_ok.jsp").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void managerDel(
			HttpServletRequest request, HttpServletResponse response,int id) {
		ManagerForm managerForm = new ManagerForm();
		managerForm.setID(id);
		int ret = managerDao.delete(managerForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "删除管理员信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/manager_ok.jsp?para=3").forward(request, response);
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
