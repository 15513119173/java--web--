<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.demo.dao.*" %>
<html>
<head>
<title>�������߿���</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript" src="JS/ContentLoader.js">
</script>
</head>
<script language="javascript">
function checkForm(){
	if(form.name.value==""){
		alert("�����뿼������!");
		return false;
	}
	if(form.password1.value==""){
		alert("�������¼����!");
		return false;
	}
	if(form.password1.value.length<6 || form.password1.value.length>20){
		alert("����������벻�Ϸ�������������6λ������С�ڵ�20λ!");
		return false;
	}
	if(form.password2.value==""){
		alert("��ȷ�ϵ�¼����!");
		return false;
	}		
	if(form.password1.value!=form.password2.value){
		alert("����������ĵ�¼���벻һ�£�����������!");
		return false;
	}
	if(form.question.value==""){
		alert("��������ʾ����!");
		return false;
	}
	if(form.answer.value==""){
		alert("�����������!");
		return false;
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
              <td class="word_grey">&nbsp;<img src="Images/f_ico.gif" width="8" height="8"> ��ǰλ�ã��� <span class="word_darkGrey">����ע�� &gt;&gt;&gt;</span></td>
			  <td align="right"><img src="Images/m_ico1.gif" width="5" height="9">&nbsp;<html:link page="/index.jsp" >������ҳ</html:link>&nbsp;</td>
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
<form name="form" action="student?action=studentAdd" method="post" >
  <table width="57%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
  <tr>
    <td height="30" align="left" style="padding:5px;">����������</td>
    <td align="left">
	 <input type='text' name="name" size="20"/> *</td>
    </tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">qq���䣺</td>
    <td align="left">
	 <input type='text' name="mail" size="20"/> *</td>
    </tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;�룺</td>
    <td align="left"><input type='password' id="password1" name="pwd" size="20"/>��������6��20λ�����ֻ���ĸ��ɣ� *     </td>
    </tr>
  <tr align="center">
    <td width="16%" height="30" align="left" style="padding:5px;">ȷ�����룺</td>
    <td width="84%" align="left">
      <input type='password' id="password2" property="pwd" size="20"/> *	  </td>
    <tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
    <td align="left">
		<select name="sex">
	 	<option value="��">�� <option>
		<option value="Ů">Ů <option>
		</select>     </td>
    </tr>	
    <tr align="center">
    <td width="16%" height="30" align="left" style="padding:5px;">��ʾ���⣺</td>
    <td width="84%" align="left">
       <input type='text' name="question" size="40"/>�����ҵ����գ� *	  </td>
    </tr>
	  <tr align="center">
    <td width="16%" height="30" align="left" style="padding:5px;">����𰸣�</td>
    <td width="84%" align="left">
       <input type='text' name="answer" size="40"/> ����7��17�գ�*</td>
    </tr>
	  <tr align="center">
    <td width="16%" height="30" align="left" style="padding:5px;">ר&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</td>
    <td width="84%" align="left">
       <input type='text' name="profession" size="40"/></td>
    </tr>
	<tr align="center">
    <td width="16%" height="30" align="left" style="padding:5px;">���֤�ţ�</td>
    <td width="84%" align="left">
       <input type='text' name="cardNo" size="40"/> *</td>
    </tr>
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type='submit' id="submit" styleClass="btn_grey" value="����" onClick="return checkForm()"/>
        &nbsp;
        <input type='reset' id="reset" styleClass="btn_grey" value="ȡ��"/>
		&nbsp;
		<input type='button' id="button" styleClass="btn_grey" value="����" onClick="window.location.href='index.jsp'"/>		</td>
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
