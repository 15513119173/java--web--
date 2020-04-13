<%@page import="java.util.List"%>
<%@page import="com.demo.entity.StudentForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<html>
<head>
<title>网络在线考试</title>
<html:base />
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript" src="JS/ContentLoader.js">
</script>
</head>
<script language="javascript">
function checkForm(){
	if(form.name.value==""){
		alert("请输入考生姓名!");form.name.focus();return false;
	}
	if(form.oldpwd1.value==""){
		alert("请输入的原密码!");form.oldpwd1.focus();return false;
	}
	if(form.oldpwd1.value!=form.holdpwd.value){
		alert("您输入的原密码不正确，请重新输入!");form.oldpwd1.value="";
		form.oldpwd1.focus();return false;
	}
	if(form.newpwd.value==""){
		alert("请输入的新密码!");form.newpwd.focus();return false;
	}	
	if(form.newpwd1.value==""){
		alert("请确认新密码!");form.newpwd1.focus();return false;
	}	
	if(form.newpwd.value!=form.newpwd1.value){
		alert("您两次输入的新密码不一致，请重新输入!");
		form.newpwd.value="";form.newpwd1.value="";
		form.newpwd.focus();return false;
	}
	if(form.question.value==""){
		alert("请输入提示问题!");form.question.focus();return false;
	}
	if(form.answer.value==""){
		alert("请输入问题答案!");form.answer.focus();return false;
	}
}
</script>
<body>
<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="131" background="Images/top_bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="774" height="487"  border="0" cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="word_grey">&nbsp;<img src="Images/f_ico.gif" width="8" height="8"> 当前位置：→ <span class="word_darkGrey">修改个人资料 &gt;&gt;&gt;</span></td>
			  <td align="right"><img src="Images/m_ico1.gif" width="5" height="9">&nbsp;<a href="default.jsp" >返回首页</a>&nbsp;</td>
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
<form name="form" action="student?action=studentModify" method="post" >
<%  StudentForm bean=(StudentForm)request.getAttribute("modifyQuery");
	 %>
  <table width="57%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
  <tr>
    <td height="30" align="left" style="padding:5px;">考生姓名：</td>
    <td align="left">
	<input type="hidden" name="ID"  value="<%=bean.getID()%>"/>
	 <input type="text" name="name" size="20" readonly="true" value="<%=bean.getName()%>"/> *</td>
    </tr>
	    <tr>
    <td align="left" style="padding:5px;">原&nbsp;密&nbsp;码：</td>
    <td align="left"><input type="password" name="oldpwd1"  size="30"/> *
      <input type="hidden" name="holdpwd"  value="<%=bean.getPwd()%>"/></td>
    </tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">新&nbsp;密&nbsp;码：</td>
    <td align="left"><input type="password" name="newpwd" size="20"/>（密码由6到20位的数字或字母组成） *     </td>
    </tr>
  <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">确认新密码：</td>
    <td width="80%" align="left">
      <input type="password" name="newpwd1" size="20"/> *	  </td>
    <tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
    <td align="left">
		<select id="sex" name="sex">
	 	<option value="男">男 </option>
		<option value="女">女 </option>
		</select>     </td>
    </tr>	
    <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">提示问题：</td>
    <td width="80%" align="left">
      <input type="text" name="question" size="40" name="modifyQuery"/>（如我的生日） *	  </td>
    </tr>
	  <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">问题答案：</td>
    <td width="80%" align="left">
      <input type="text" name="answer" size="40" name="modifyQuery"/> （如7月17日）*</td>
    </tr>
	  <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">专&nbsp;&nbsp;&nbsp;&nbsp;业：</td>
    <td width="80%" align="left">
      <input type="text" name="="profession" size="40" name="modifyQuery"/></td>
    </tr>
	<tr align="center">
    <td width="20%" height="30" align="left"  style="padding:5px;">身份证号：</td>
    <td width="80%" align="left">
      <input type="text" name="cardNo" readonly="true" size="40" value="<%=bean.getCardNo()%>"/> *</td>
    </tr>
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="保存" onClick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="取消"/>
&nbsp;
		<input type="button" name="button" styleClass="btn_grey" value="返回" onClick="window.location.href='default.jsp'"/>
		</td>
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
