<%@page import="com.demo.entity.StuResultForm"%>
<%@page import="com.demo.entity.StudentForm"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<html>
<head>
<title>网络在线考试</title>
<base />
<link href="CSS/style.css" rel="stylesheet">
</head>
<body>

<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="131" background="Images/top_bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="774" height="487"  border="0" cellpadding="0" cellspacing="0" align="right">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="76%" class="word_grey">&nbsp;<img src="Images/f_ico.gif" width="8" height="8"> 当前位置：→ <span class="word_darkGrey">考生成绩查询 &gt;&gt;&gt;</span></td>
			  <td width="24%" align="right"><img src="Images/m_ico1.gif" width="5" height="9">
			    <a href="default.jsp" >返回首页</a>&nbsp;</td>
              </tr>
          </table></td>
        </tr>
      <tr>
        <td align="center" valign="top">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="84%">&nbsp;      </td>
</tr>
</table> 
<table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#67A8DB">
  <tr align="center">
    <td width="21%" height="27" bgcolor="#B2D6F1">准考证号</td>
	<td width="26%" bgcolor="#B2D6F1">所属课程</td>
	<td width="22%" bgcolor="#B2D6F1">考试时间</td>
	<td width="11%" bgcolor="#B2D6F1">单选题分数</td>
	<td width="11%" bgcolor="#B2D6F1">多选题分数</td>
    <td width="9%" bgcolor="#B2D6F1">合计分数</td>
  </tr>
	<logic:iterate id="stuResult" name="stuResultQuery" type="com.wgh.actionForm.StuResultForm" scope="request">
  <%
      List list=((List<StudentForm>)request.getAttribute("stuResultQuery"));
      for(int i=0;i<list.size();i++){
          StuResultForm bean=(StuResultForm)list.get(i);
   %>
  <tr>
    <td style="padding:5px;"><%=bean.getID() %></td>
	<td style="padding:5px;"><%=bean.getWhichLesson() %></td>
	<td align="center"><%=bean.getJoinTime() %></td>
	<td align="center"><%=bean.getResSingle() %></td>
    <td align="center"><%=bean.getResMore() %></td>
    <td align="center"><%=bean.getResTotal() %></td>
  </tr>
  <%} %>
  </logic:iterate> 
</table>
</td>
      </tr>
    </table>
</td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
