package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.StartExamDAO;
import com.demo.entity.MoreSelect;
import com.demo.entity.QuestionsForm;
import com.demo.util.ChStr;

public class StartExam extends HttpServlet {
	private StartExamDAO startExamDAO = new StartExamDAO();
	ChStr chStr = new ChStr();
	public StartExam() {
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
		if ("startExam".equals(action)) {
			startExam(request, response);
		}  else if ("showStartTime".equals(action)) {// 显示考试计时
			showStartTime(request, response);
		} else if ("showRemainTime".equals(action)) {// 显示考试时间
			showRemainTime(request, response);
		} else {
			request.setAttribute("error", "操作失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
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
		 if ("submitTestPaper".equals(action)) {
		 submitTestPaper( request, response);
	}
	}
	private void submitTestPaper(HttpServletRequest request,
			HttpServletResponse response) {
		QuestionsForm q =  new QuestionsForm();
		int sing=Integer.parseInt(request.getParameter("single"));
		int mores=Integer.parseInt(request.getParameter("more"));
		System.out.print(request.getParameter(String.valueOf("id["+0+"]")));
		String[] single = new String[sing];
		int[] singleId = new int[sing];
		String[] more = new String[mores];
		int[] moreId = new int[mores];
		String rightAnswer = "";
		float singleMark = 0;
		float moreMark = 0;
		for(int i=0;i<sing;i++){
			singleId[i]=Integer.parseInt(request.getParameter(String.valueOf("id["+i+"]")));
			single[i]=request.getParameter("answerArrS["+i+"]");
			System.out.print(singleId[i]+single[i]);
		}
		for(int i=0;i<mores;i++){
			more[i]="";
			String[] anString=request.getParameterValues("moreSelect["+i+"].answerArr");
			System.out.print(anString.toString());
			moreId[i]=Integer.parseInt(request.getParameter(String.valueOf("id["+(i+sing)+"]")));
			System.out.print(anString.length);
			for (int j=0;j<anString.length;j++){
			more[i]+=anString[j];
			}
		}
		/**************************** 统计单选题的得分 **************************************/
		
		// System.out.println(q.getID());
		System.out.println("数据的长度：" + single.length);
		float markS = 40 / (single.length);
		for (int i = 0; i < single.length; i++) {
			// 调用getRightAnswer()方法获取正确答案
			rightAnswer = startExamDAO.getRightAnswer(singleId[i]);
			System.out.println("单选数组：" + i + "********ID号" + singleId[i]
					+ "********" + single[i] + "****正确答案" + rightAnswer);
			if (rightAnswer.equals(single[i])) {
				singleMark = singleMark + markS; // 累加单选题的分数
			}
		}
		System.out.println("单选题得分：" + singleMark);
		/*********************************************************************************/
		/**************************** 统计多选题的得分 **************************************/
		System.out.println("多选数组的长度：" + more.length);
		float markM = 60 / (mores);
		String str = "";
		for (int i = 0; i < mores; i++) {
			//String[] ans = more[i].getAnswerArr();
			rightAnswer = startExamDAO.getRightAnswer(moreId[i]);
			System.out.println("多选数组：" + i + "********ID号" + moreId[i]
					+ "********" + more[i] + "****正确答案" + rightAnswer);
//			for (int j = 0; j < ans.length; j++) {
//				if (ans[j] != null)
//					str = str + ans[j] + ",";
//			}
//			if (str.length() > 1) {
//				str = str.substring(0, str.length() - 1);
//			}
			str=more[i];
			System.out.println("获取的多选题答案：" + str);
			if (rightAnswer.equals(str)) {
				moreMark = moreMark + markM; // 累加多选题的分数
			}
			str = "";
		}
		System.out.println("多选题得分：" + moreMark);
		/*********************************************************************************/
		HttpSession session = request.getSession();
		String student = session.getAttribute("student").toString();
		int lessonID = Integer.parseInt(session.getAttribute("lessonID")
				.toString()); // 课程ID
		int ret = startExamDAO.saveResult(student, lessonID, (int) Math
				.round(singleMark), (int) Math.round(moreMark));
		try{
		if (ret > 0) {
			request.setAttribute("submitTestPaperok", "试卷已提交，您本次考试的成绩为："
					+ (Math.round(singleMark) + Math.round(moreMark)) + "分！");
			request.getRequestDispatcher("submitTestPaperok.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("dealNull.jsp").forward(request, response);
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void startExam(
			HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("课程ID："+lessonForm.getID()+lessonForm.getName());
		HttpSession session = request.getSession();
		
				try {
					if (session.getAttribute("student") == null
							|| session.getAttribute("student").equals("")) {
						request.getRequestDispatcher("dealNull.jsp").forward(request, response); // 转到前台登录页面
					} else {
						String student = session.getAttribute("student").toString();
						System.out.println(student);
						// 准考证号
//						session.setAttribute("lessonID", 2);
						if (session.getAttribute("lessonID") == null
								|| session.getAttribute("lessonID").equals("")) {
							request.getRequestDispatcher("dealNull.jsp").forward(request, response); // 转到前台登录页面
						} else {
							int lessonID = Integer.parseInt(session
									.getAttribute("lessonID").toString()); // 课程ID
							// 随机抽取试题
							int questions = startExamDAO.randomGetQuestion(lessonID);
							System.out.print(questions);
							// 刚开始考试时保存考试结果
							int ret = startExamDAO.startSaveResult(student, lessonID);
							List singleQue = (List) startExamDAO.queryExam(questions, 0);
							QuestionsForm q = new QuestionsForm();
							q.setSize(singleQue.size());
							request.setAttribute("singleQue", singleQue); // 获取单选题
							List moreQue = (List) startExamDAO.queryExam(questions, 1); // 获取多选题
							q.setMoreSize(moreQue.size());
							request.setAttribute("moreQue", moreQue);
							
							session.setAttribute("startTime", new java.util.Date()
									.getTime());
					request.getRequestDispatcher("startExam.jsp").forward(request, response);
					}
					}
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	private void showStartTime(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		HttpSession session = request.getSession();
		String startTime = session.getAttribute("startTime").toString();
		long a = Long.parseLong(startTime);
		long b = new java.util.Date().getTime();
		int h = (int) Math.abs((b - a) / 3600000);
		String hour = chStr.formatNO(h, 2);
		int m = (int) (b - a) % 3600000 / 60000;
		String minute = chStr.formatNO(m, 2);
		int s = (int) ((b - a) % 3600000) % 60000 / 1000;
		String second = chStr.formatNO(s, 2);
		String time = hour + ":" + minute + ":" + second;
		System.out.print(time);
		request.setAttribute("showStartTime", time);
		request.getRequestDispatcher("showStartTime.jsp").forward(request, response);
	}

	// 计算剩余时间
	private void showRemainTime( HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String startTime = session.getAttribute("startTime").toString();
		long a = Long.parseLong(startTime);
		long b = new java.util.Date().getTime();
		long r = 20 * 60000 - (b - a - 1000);
		int h = (int) Math.abs(r / 3600000);
		String hour = chStr.formatNO(h, 2);
		int m = (int) (r) % 3600000 / 60000;
		String minute = chStr.formatNO(m, 2);
		int s = (int) ((r) % 3600000) % 60000 / 1000;
		String second = chStr.formatNO(s, 2);
		String time = hour + ":" + minute + ":" + second;
		System.out.print(time);
		request.setAttribute("showRemainTime", time);
		request.getRequestDispatcher("showRemainTime.jsp").forward(request, response);
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
