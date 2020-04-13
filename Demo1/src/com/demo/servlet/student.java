package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.StudentDao;
import com.demo.entity.StudentForm;
import com.demo.util.Ce;

public class student extends HttpServlet {
	private StudentDao studentDAO = new StudentDao();
	public student() {
		super();
	}
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
		 String action=request.getParameter("action");
         System.out.println("获取到的请求:"+action);
		if ("modifyQuery".equals(action)) {
   			modifyQuery(request, response);
   		}else if ("studentQuery".equals(action)) {
			studentQuery(request, response);
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
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
           String action=request.getParameter("action");
           System.out.println("获取到的请求:"+action);
            if ("login".equals(action)) {
	   			studentLogin(request, response);
	   		} else if ("studentAdd".equals(action)) {
	   			studentAdd(request, response);
	   		} else if ("studentDel".equals(action)) {
	   			studentDel(request, response);
	   		}  else if ("studentModify".equals(action)) {
	   			studentModify(request, response);
	   		}else if("seekPwd1".equals(action)){
	   			seekPwd1(request,response);
	   		}else if("seekPwd2".equals(action)){
	   			seekPwd2(request,response);
	   		}else{
	   			request.setAttribute("error","您的操作有误！");		//将错误信息保存到error中
	   			request.getRequestDispatcher("error.jsp").forward(request, response);				//转到显示错误信息的页面
	   		}
	}
    public void studentLogin(HttpServletRequest request, HttpServletResponse response) {
    	String pwdString=request.getParameter("pwd");
    	String idString= request.getParameter("name");
   		StudentForm studentForm = new StudentForm();
   		studentForm.setID(idString);studentForm.setPwd(pwdString);
   		int ret = studentDAO.checkStudent(studentForm);
   		System.out.println("验证结果ret的值:" + ret);
   		try{
	   		if (ret==2) {
	   			request.setAttribute("error", "您输入的考生准考证号码或密码错误！");
	   			request.getRequestDispatcher("error.jsp").forward(request, response);
	   		} else {
	   			HttpSession session = request.getSession();
	   			session.setAttribute("student", studentForm.getID());
	   			request.getRequestDispatcher("default.jsp").forward(request, response);
	   		}
   		}catch (Exception e) {
			
		}
   	}
    private void studentAdd(HttpServletRequest request, HttpServletResponse response) {
    	System.out.print(request.getParameter("mail"));
    	try {
			Ce.getdata(request.getParameter("mail"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.print(request.getParameter("password1"));
    	StudentForm studentForm =new StudentForm();
		studentForm.setName(request.getParameter("name"));
		studentForm.setPwd(request.getParameter("pwd"));
		studentForm.setSex(request.getParameter("sex"));
		studentForm.setQuestion(request.getParameter("question"));
		studentForm.setAnswer(request.getParameter("answer"));
		studentForm.setProfession(request.getParameter("profession"));
		studentForm.setCardNo(request.getParameter("cardNo"));
		String ret = studentDAO.insert(studentForm);
		System.out.println("返回值ret：" + ret);
		try{
			
			if (ret.equals("re")) {
				request.setAttribute("error", "您已经注册，直接登录即可！");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else if(ret.equals("miss")){
				request.setAttribute("error", "注册失败！");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}else{
				request.setAttribute("ret",ret);
				request.getRequestDispatcher("student_ok.jsp").forward(request, response);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
    private void seekPwd1(
	        HttpServletRequest request, HttpServletResponse response){
		StudentForm studentForm = new StudentForm();
		studentForm.setID(request.getParameter("ID"));
		StudentForm s=studentDAO.seekPwd1(studentForm);
		request.setAttribute("seekPwd2", s);
		try{
		if(s.getID().equals("")){
			request.setAttribute("error", "您输入的准考证号不存在！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("seekPwd1.jsp").forward(request, response);	
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void seekPwd2(
	        HttpServletRequest request, HttpServletResponse response){
		StudentForm studentForm = new StudentForm();
		studentForm.setID(request.getParameter("ID"));
		System.out.print(request.getParameter("ID"));
		studentForm.setAnswer(request.getParameter("answer"));
		StudentForm s=studentDAO.seekPwd2(studentForm);
		request.setAttribute("seekPwd3", s);
		try{
		if(s.getID().equals("")){
			request.setAttribute("error", "您输入的密码提示问题的答案不正确！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("seekPwd2.jsp").forward(request, response);	
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void studentModify(
			HttpServletRequest request, HttpServletResponse response) {
		StudentForm studentForm = new StudentForm();
		studentForm.setID(request.getParameter("ID"));
		studentForm.setPwd(request.getParameter("newpwd"));
		studentForm.setSex(request.getParameter("sex"));
		studentForm.setAnswer(request.getParameter("answer"));
		studentForm.setQuestion(request.getParameter("question"));
		studentForm.setProfession(request.getParameter("profession"));
		int ret = studentDAO.update(studentForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "修改考生信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("studentM_ok.jsp").forward(request, response);	
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
// 删除考生信息
	private void studentDel(
			HttpServletRequest request, HttpServletResponse response) {
		StudentForm studentForm = new StudentForm();
		String[] bool=request.getParameterValues("delIdArray"); 
		studentForm.setDelIdArray(bool);
		int ret = studentDAO.delete(studentForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "删除考生信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/student_ok.jsp?para=3").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void modifyQuery(
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("获取的ID："+request.getParameter("ID"));
		StudentForm studentForm=(StudentForm)(studentDAO.query(request.getParameter("ID")).get(0));
		System.out.println("从Bean中获取的ID："+studentForm.getID());
		request.setAttribute("modifyQuery", studentForm);
		try {
			request.getRequestDispatcher("student_Modify.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void studentQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("studentQuery", studentDAO.query(null));
		try {
			request.getRequestDispatcher("manage/student.jsp").forward(request, response);
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
