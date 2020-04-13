package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dao.LessonDao;
import com.demo.dao.QuesstionsDao;
import com.demo.dao.TaoTiDao;
import com.demo.entity.QuestionsForm;

public class Questions extends HttpServlet {
	private QuesstionsDao questionsDao=new QuesstionsDao();
	private LessonDao lessonDao=new LessonDao();
	private TaoTiDao taoTiDao=new TaoTiDao();
	/**
	 * Constructor of the object.
	 */
	public Questions() {
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
		if(action.length()>=20){
			actString=action.substring(0,20);
			System.out.println("获取的查询字符串：" + actString);
		}
		if ("questionsQuery".equals(action)) {
			questionsQuery(request, response);
		}else if("questionsAddQuery".equals(action)){
			questionsAddQuery(request,response);			
		}else if("questionsModifyQuery".equals(actString)){
			String act=action.substring(20);
			System.out.println("获取的查询字符串：" + act);
			questionsModifyQuery(request,response,Integer.parseInt(act));
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
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if ("questionsQuery".equals(action)) {
			questionsQuery(request, response);
		} else if ("questionsAdd".equals(action)) {
			questionsAdd(request, response);
		} else if ("questionsDel".equals(action)) {
			questionsDel(request, response);
		}else if("questionsModify".equals(action)){
			questionsModify(request,response);
		}else if("queryTaoTi".equals(action)){
			queryTaoTi(request,response);
		}else{
			request.setAttribute("error", "操作失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	private void questionsQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("questionsQuery", questionsDao.query(0));
		try {
			request.getRequestDispatcher("manage/questions.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void questionsAdd(
			HttpServletRequest request, HttpServletResponse response) {
		QuestionsForm questionsForm = new QuestionsForm();
		questionsForm.setLessonId(Integer.parseInt(request.getParameter("lessonId")));
		questionsForm.setType(request.getParameter("type"));
		questionsForm.setSubject(request.getParameter("subject"));
		questionsForm.setTaoTiId(Integer.parseInt(request.getParameter("taotiid")));
		questionsForm.setOptionA(request.getParameter("optionA"));
		questionsForm.setOptionB(request.getParameter("optionB"));
		questionsForm.setOptionC(request.getParameter("optionC"));
		questionsForm.setOptionD(request.getParameter("optionD"));
		if(request.getParameter("type").equals("单选题")){
			questionsForm.setAnswer(request.getParameter("answerArrw"));
		}else{
			String[] answer=request.getParameterValues("answerArr");
			String anwString="";
			for(int i=0;i<answer.length;i++){
				anwString+=answer[i];
			}
			System.out.println(anwString);
			questionsForm.setAnswer(anwString);
		}
		System.out.println(questionsForm.getAnswer());
		questionsForm.setNote(request.getParameter("note"));
		int ret = questionsDao.insert(questionsForm);
		System.out.print(ret+"");
		try{
		if (ret == 1) {
			request.getRequestDispatcher("manage/questions_ok.jsp?para=1").forward(request, response);
		} else if (ret == 2) {
			request.setAttribute("error", "该考试题目已经添加！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "添加考试题目失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void questionsAddQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("lessonList",lessonDao.query(-1));
		//全部包括套题的课程列表
		try {
			request.getRequestDispatcher("manage/questions_Add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void queryTaoTi(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("taoTiList",taoTiDao.queryTaoTi(Integer.parseInt(request.getParameter("id"))));	//查询指定课程的套题列表
		try {
			request.getRequestDispatcher("queryTaoTi").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void questionsModifyQuery(
			HttpServletRequest request, HttpServletResponse response,int id) {
		QuestionsForm questionsForm=(QuestionsForm)((questionsDao.query(id)).get(0));
		request.setAttribute("questionsModifyQuery", questionsForm);
		try {
			request.getRequestDispatcher("manage/questions_Modify.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void questionsModify(
			HttpServletRequest request, HttpServletResponse response) {
		QuestionsForm questionsForm = new QuestionsForm();
		questionsForm.setID(Integer.parseInt(request.getParameter("ID")));
		questionsForm.setSubject(request.getParameter("subject"));
		questionsForm.setType(request.getParameter("type"));
		questionsForm.setOptionA(request.getParameter("optionA"));
		questionsForm.setOptionB(request.getParameter("optionB"));
		questionsForm.setOptionC(request.getParameter("optionC"));
		questionsForm.setOptionD(request.getParameter("optionD"));
		if(request.getParameter("type").equals("单选题")){
			questionsForm.setAnswer(request.getParameter("answerArr"));
		}else{
			String[] answer=request.getParameterValues("answerArr");
			String anwString="";
			for(int i=0;i<answer.length;i++){
				anwString+=answer[i];
			}
			questionsForm.setAnswer(anwString);
		}
		questionsForm.setNote(request.getParameter("note"));
		int ret = questionsDao.update(questionsForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "修改考试题目失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/questions_ok.jsp?para=2").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
private void questionsDel(
			HttpServletRequest request, HttpServletResponse response) {
		QuestionsForm questionsForm = new QuestionsForm();
		questionsForm.setDelIdArray(request.getParameterValues("delIdArray"));
		int ret = questionsDao.delete(questionsForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "删除考试题目失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("manage/questions_ok.jsp?para=3").forward(request, response);
		}
		}catch (Exception e) {
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
