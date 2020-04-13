<%@page import="com.demo.entity.QuestionsForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<%-- <jsp:useBean id="lesson" class="com.wgh.dao.TaoTiDAO" scope="page"/>
<jsp:useBean id="taoTi" class="com.wgh.dao.QuestionsDAO" scope="page"/> --%>
<html>
<head>
<title>网络在线考试——后台管理</title>
<link href="../CSS/style.css" rel="stylesheet">
</head>
<script language="javascript">
function checkForm(){
	if(form.name.value==""){
		alert("请输入考试题目名称!");form.name.focus();return false;
	}
}
function show(val){
		if(val=="单选题"){
		sOption.style.display="";
		mOption.style.display="none";
	}else if(val=="多选题"){
		sOption.style.display="none";
		mOption.style.display="";		
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
              <td width="78%" class="word_grey">&nbsp;当前位置：<span class="word_darkGrey">考试题目管理 &gt; 修改考试题目 &gt;&gt;&gt;</span></td>
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


<form name="form" action="Questions?action=questionsModify" method="post" >

	<table width="85%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
    <% QuestionsForm bean=(QuestionsForm)request.getAttribute("questionsModifyQuery"); %>
    <tr>
      <td height="30" align="left" style="padding:5px;">所属课程：</td>
      <td align="left"><%=bean.getLessonId() %></td>
    </tr>
    <tr>
      <td height="30" align="left" style="padding:5px;">所属套题：</td>
      <td align="left" id="subType"><%=bean.getTaoTiId() %></td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">考试题目：</td>
      <td width="85%" align="left"><input type="text" id="subject" size="40" name="subject" value="<%=bean.getSubject()%>"/>
	  <input type="hidden" name="ID" value="<%=bean.getID()%>"/>
        * </td>
    <tr>
    <tr>
      <td height="30" align="left" style="padding:5px;">试题类型：</td>
      <td align="left"><select property="type" onchange="show(this.value)" name="type">
          <option value="单选题">单选题</option>
          <option value="多选题" >多选题</option>
        </select>      </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">选项A：</td>
      <td width="85%" align="left"><input type="text" id="optionA" size="40" name="optionA" value="<%=bean.getOptionA()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">选项B：</td>
      <td width="85%" align="left"><input type="text" id="optionB" size="40" name="optionB" value="<%=bean.getOptionB()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">选项C：</td>
      <td width="85%" align="left"><input type="text" id="optionC" size="40" name="optionC" value="<%=bean.getOptionC()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">选项D：</td>
      <td width="85%" align="left"><input type="text" id="optionD" size="40" name="optionD" value="<%=bean.getOptionD()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">正确答案：</td>
      <td width="85%" align="left" id="sOption"><select id="answer" name="answeranswerArr">
          <option value="A">A </option>
          <option value="B">B </option>
          <option value="C">C </option>
          <option value="D">D </option>
        </select>      </td>
      <td width="85%" align="left" id="mOption" style="display:none">
	  <input type="checkbox" name="answerArr" styleClass="noborder" value="A">A</input>
	<input type="checkbox" name="answerArr" styleClass="noborder" value="B">B</input>
	<input type="checkbox" name="answerArr" styleClass="noborder" value="C">C</input>
	<input type="checkbox" name="answerArr" styleClass="noborder" value="D">D</input></td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">备注：</td>
      <td width="85%" align="left"><input type="text" id="note" size="40" name="note"  value="<%=bean.getNote()%>"/>      </td>
    </tr>
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="保存" onclick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="取消"/>
        &nbsp;
        <input type="button" name="button" styleClass="btn_grey" value="返回" onclick="window.location.href='Questions?action=questionsQuery'"/></td>
    </tr>
  </table>
  <script language="javascript">
show("<%-- <%=defaultOption%> --%>");
</script>
</form></td>
      </tr>
    </table>
</td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
