package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.LessonDao;
import com.demo.entity.LessonForm;

public class lesson extends HttpServlet {
	private LessonDao lessonDao=new LessonDao();
	public lesson() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
		if ("lessonQuery".equals(action)) {
			lessonQuery(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
			String action = request.getParameter("action");
			System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
			if (action == null || "".equals(action)) {
				try {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if ("lessonAdd".equals(action)) {
				lessonAdd(request, response);
			} else if ("lessonDel".equals(action)) {
				lessonDel(request, response);
			}else if("selectLesson".equals(action)){
				selectLesson(request, response);
			}else if("ready".equals(action)){
				ready(request, response);
			}else{
				request.setAttribute("error", "����ʧ�ܣ�");
				try {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
//���߿���ʱѡ��γ�
	private void selectLesson(
			HttpServletRequest request, HttpServletResponse response) { 
		HttpSession session = request.getSession();
		String stu=session.getAttribute("student").toString();	//��ȡ׼��֤��
		List list=LessonDao.query(stu);	//��ѯ����������Ŀ�Ŀγ��б����������Ѿ������Ŀ�Ŀ
		try {
		if(list.size()<1){
			request.getRequestDispatcher("noenLesson.jsp").forward(request, response);
		}else{
			request.setAttribute("lessonList",list);
			request.getRequestDispatcher("selectLesson.jsp").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void ready(HttpServletRequest request, HttpServletResponse response) { 
		LessonForm lessonForm = new LessonForm();
		int id=Integer.parseInt(request.getParameter("ID"));
		lessonForm.setID(id);
		System.out.println("�γ�ID��"+lessonForm.getID());
		HttpSession session = request.getSession();
		session.setAttribute("lessonID",String.valueOf(lessonForm.getID()));	//��ѯѡ��Ŀγ�ID
		try {
			request.getRequestDispatcher("ready.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	private void lessonQuery(
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("lessonQuery", lessonDao.query(0));
		try {
			request.getRequestDispatcher("manage/lesson.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void lessonAdd(
			HttpServletRequest request, HttpServletResponse response) {
		LessonForm lessonForm = new LessonForm();
		lessonForm.setName(request.getParameter("name"));
		int ret = lessonDao.insert(lessonForm);
		System.out.println("����ֵret��" + ret);
		try{
		if (ret == 1) {
			request.getRequestDispatcher("manage/lesson_ok.jsp?para=1").forward(request, response);
		} else if (ret == 2) {
			request.setAttribute("error", "�ÿγ��Ѿ���ӣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "��ӿγ�ʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void lessonDel(
			HttpServletRequest request, HttpServletResponse response) {
		LessonForm lessonForm = new LessonForm();
		int ret = lessonDao.delete(lessonForm);
		try{
		if (ret == 0) {
			request.setAttribute("error", "ɾ���γ�ʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("lessonDel").forward(request, response);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
