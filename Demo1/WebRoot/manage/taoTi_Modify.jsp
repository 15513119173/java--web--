<%@page import="com.demo.entity.LessonForm"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.entity.TaoTiForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<html>
<head>
<title>网络在线考试——后台管理</title>
<link href="../CSS/style.css" rel="stylesheet">
</head>
<script language="javascript">
function checkForm(){
	if(form.name.value==""){
		alert("请输入套题名称!");form.name.focus();return false;
	}
}
</script>
<body>
<%@ include file="top.jsp"%>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td width="176" align="center" valign="top" bgcolor="#FFFFFF"><%@ include file="left.jsp"%></td>
    <td width="602" valign="top" bgcolor="#FFFFFF"><table width="99%" height="487"  border="0" cellpadding="0" cellspacing="0" align="right">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="78%" class="word_grey">&nbsp;当前位置：<span class="word_darkGrey">套题信息管理 &gt; 修改套题信息 &gt;&gt;&gt;</span></td>
              <td align="right"><img src="../Images/m_ico1.gif" width="5" height="9">&nbsp;当前管理员：<%=session.getAttribute("manager")%>&nbsp;</td>
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
<form name="form" action="TaoTi?action=taoTiModify" method="post" >
  <table width="63%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
  <tr align="center">
    <%TaoTiForm bean=(TaoTiForm)request.getAttribute("taoTiModifyQuery"); %>
    <td width="27%" height="30" align="left" style="padding:5px;">套题名称：</td>
    <td width="73%" align="left">
      <input type="text" id="name" name="name" size="30" value="<%=bean.getName()%>"/>
	  <input type="hidden" id="ID" name="ID" value="<%=bean.getID()%>"/>
	  </td>
    <tr>
    <td height="30" align="left" style="padding:5px;">所属课程：</td>
    <td align="left">
	 <select  name="lessonId">
	 <%List list=(List<LessonForm>)request.getAttribute("lessonList"); 
	   for(int i=0;i<list.size();i++){
	       LessonForm be=(LessonForm)list.get(i);
	 %>
		<option value="<%=be.getID()%>"><%=be.getName() %></option>
		<%} %>
	 </select>
     </td>
    </tr>
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="保存" onclick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="取消"/>
		&nbsp;
		<input type="button" name="button" styleClass="btn_grey" value="返回" onclick="window.location.href='taoTi.do?action=taoTiQuery'"/>		</td>
    </tr>
</table>
</form>
</td>
      </tr>
    </table>
</td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
