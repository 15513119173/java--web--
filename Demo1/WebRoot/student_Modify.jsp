<%@page import="java.util.List"%>
<%@page import="com.demo.entity.StudentForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<html>
<head>
<title>�������߿���</title>
<html:base />
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript" src="JS/ContentLoader.js">
</script>
</head>
<script language="javascript">
function checkForm(){
	if(form.name.value==""){
		alert("�����뿼������!");form.name.focus();return false;
	}
	if(form.oldpwd1.value==""){
		alert("�������ԭ����!");form.oldpwd1.focus();return false;
	}
	if(form.oldpwd1.value!=form.holdpwd.value){
		alert("�������ԭ���벻��ȷ������������!");form.oldpwd1.value="";
		form.oldpwd1.focus();return false;
	}
	if(form.newpwd.value==""){
		alert("�������������!");form.newpwd.focus();return false;
	}	
	if(form.newpwd1.value==""){
		alert("��ȷ��������!");form.newpwd1.focus();return false;
	}	
	if(form.newpwd.value!=form.newpwd1.value){
		alert("����������������벻һ�£�����������!");
		form.newpwd.value="";form.newpwd1.value="";
		form.newpwd.focus();return false;
	}
	if(form.question.value==""){
		alert("��������ʾ����!");form.question.focus();return false;
	}
	if(form.answer.value==""){
		alert("�����������!");form.answer.focus();return false;
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
              <td class="word_grey">&nbsp;<img src="Images/f_ico.gif" width="8" height="8"> ��ǰλ�ã��� <span class="word_darkGrey">�޸ĸ������� &gt;&gt;&gt;</span></td>
			  <td align="right"><img src="Images/m_ico1.gif" width="5" height="9">&nbsp;<a href="default.jsp" >������ҳ</a>&nbsp;</td>
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
    <td height="30" align="left" style="padding:5px;">����������</td>
    <td align="left">
	<input type="hidden" name="ID"  value="<%=bean.getID()%>"/>
	 <input type="text" name="name" size="20" readonly="true" value="<%=bean.getName()%>"/> *</td>
    </tr>
	    <tr>
    <td align="left" style="padding:5px;">ԭ&nbsp;��&nbsp;�룺</td>
    <td align="left"><input type="password" name="oldpwd1"  size="30"/> *
      <input type="hidden" name="holdpwd"  value="<%=bean.getPwd()%>"/></td>
    </tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">��&nbsp;��&nbsp;�룺</td>
    <td align="left"><input type="password" name="newpwd" size="20"/>��������6��20λ�����ֻ���ĸ��ɣ� *     </td>
    </tr>
  <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">ȷ�������룺</td>
    <td width="80%" align="left">
      <input type="password" name="newpwd1" size="20"/> *	  </td>
    <tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
    <td align="left">
		<select id="sex" name="sex">
	 	<option value="��">�� </option>
		<option value="Ů">Ů </option>
		</select>     </td>
    </tr>	
    <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">��ʾ���⣺</td>
    <td width="80%" align="left">
      <input type="text" name="question" size="40" name="modifyQuery"/>�����ҵ����գ� *	  </td>
    </tr>
	  <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">����𰸣�</td>
    <td width="80%" align="left">
      <input type="text" name="answer" size="40" name="modifyQuery"/> ����7��17�գ�*</td>
    </tr>
	  <tr align="center">
    <td width="20%" height="30" align="left" style="padding:5px;">ר&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</td>
    <td width="80%" align="left">
      <input type="text" name="="profession" size="40" name="modifyQuery"/></td>
    </tr>
	<tr align="center">
    <td width="20%" height="30" align="left"  style="padding:5px;">���֤�ţ�</td>
    <td width="80%" align="left">
      <input type="text" name="cardNo" readonly="true" size="40" value="<%=bean.getCardNo()%>"/> *</td>
    </tr>
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="����" onClick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="ȡ��"/>
&nbsp;
		<input type="button" name="button" styleClass="btn_grey" value="����" onClick="window.location.href='default.jsp'"/>
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
