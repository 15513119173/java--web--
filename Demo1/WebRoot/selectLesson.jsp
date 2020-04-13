<%@page import="java.util.List,com.demo.entity.*"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%if(session.getAttribute("student")==null){
	response.sendRedirect("../index.jsp");
}
   List list=(List)request.getAttribute("lessonList");
%>
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
              <td width="76%" class="word_grey">&nbsp;<img src="Images/f_ico.gif" width="8" height="8"> 当前位置：→ <span class="word_darkGrey">在线考试 → 选择考试课程 &gt;&gt;&gt;</span></td>
			  <td width="24%" align="right"><img src="Images/m_ico1.gif" width="5" height="9">
			    <a href="">返回首页</a>&nbsp;</td>
              </tr>
          </table></td>
        </tr>
      <tr>
        <td align="center" valign="top">
<form action="lesson?action=ready" method="post">
 <table width="100%" height="262"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="90" colspan="3" align="center" class="word_orange1">&nbsp;</td>
</tr>
  <tr>
    <td width="12%">&nbsp;</td>
    <td width="77%" align="center" valign="top">请选择考试课程：
      <select name="ID">
      <% for(int i=0;i<list.size();i++){
         LessonForm less=(LessonForm)list.get(i);
         System.out.print(less.getID()+"");
      %>
		<option id="id" name="name" value="<%=less.getID() %>"><%=less.getName() %></option>
		<%}%>
	 </select></td>
    <td width="11%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center" valign="top"><input type="submit" styleClass="btn_grey" value="确定"/>
	<input type="button" name="button" styleClass="btn_grey" value="返回" onclick="history.back(-1)"/>	</td>
    <td>&nbsp;</td>
  </tr>
</table> 
<form>
        </td>
      </tr>
    </table>
</td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
