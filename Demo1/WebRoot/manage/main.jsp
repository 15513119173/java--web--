<%@page import="com.demo.entity.StuResultForm"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>�������߿��ԡ�����̨����</title>
<link href="../CSS/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="top.jsp"%>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td width="176" align="center" valign="top" bgcolor="#FFFFFF"><%@ include file="left.jsp"%></td>
    <td width="602" valign="top" bgcolor="#FFFFFF"><table width="99%" height="487"  border="0" cellpadding="0" cellspacing="0" align="right">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="78%" class="word_grey">&nbsp;��ǰλ�ã�<span class="word_darkGrey">�����ɼ���ѯ  &gt;&gt;&gt;</span></td>
              <td align="right"><img src="./Images/m_ico1.gif" width="5" height="9">&nbsp;��ǰ����Ա��<%=session.getAttribute("manager")%>&nbsp;</td>
              </tr>
          </table></td>
        </tr>
      <tr>
      <tr>
        <td align="center" valign="top">
        <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
		<form action="StuResult?action=stuResultQuery" method="post">	
          <tr>
            <td align="left" valign="middle">&nbsp;��ѯ������
	          <select name="queryIf">
		<option value="stuId">׼��֤��</option>
		<option value="whichLesson">���Կγ�</option>
		<option value="convert(varchar(30), joinTime,21)">����ʱ��</option>
	 </select>
	 &nbsp;�ؼ��֣�
	 <input type="text" name="key"/>&nbsp;&nbsp;
	 <input type="submit" name="submit" styleClass="btn_grey" value="��ѯ"/></td>
          </tr>
		  </form>
        </table>
				
		<table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#67A8DB">
  <tr align="center">
    <td width="21%" height="27" bgcolor="#B2D6F1">׼��֤��</td>
	<td width="26%" bgcolor="#B2D6F1">�����γ�</td>
	<td width="22%" bgcolor="#B2D6F1">����ʱ��</td>
	<td width="11%" bgcolor="#B2D6F1">��ѡ�����</td>
	<td width="11%" bgcolor="#B2D6F1">��ѡ�����</td>
    <td width="9%" bgcolor="#B2D6F1">�ϼƷ���</td>
  </tr>
	<logic:iterate id="stuResult" name="stuResultQuery" type="com.wgh.actionForm.StuResultForm" scope="request">
  <% List list=(List<StuResultForm>)request.getAttribute("stuResultQuery");
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
    </table></td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
