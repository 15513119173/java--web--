<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="com.demo.dao.*" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<head>
<title>�������߿���</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
function check(){
alert(form.name.value);
	if (form.name.value==""||form.name.value==null){
		alert(form.name.value);
		return false;
	}
	if (form.pwd.value==""||form.pwd.value==null){
		alert("����������!");
		return false;
	}
}
</script>
</head>
<body>
<table width="778" height="266" border="0" align="center" cellpadding="0" cellspacing="0" background="Images/login_top.jpg">
  <tr>
    <td width="118" colspan="2">&nbsp;</td>
  </tr>
</table>
<table width="778" height="158"  border="0" align="center" cellpadding="0" cellspacing="0" background="Images/login_mid.jpg">
<form name="form" action="student?action=login" method="post">
          <tr>
            <td>
				  
                    <table width="100%"  border="0" cellpadding="0" cellspacing="0" bordercolorlight="#FFFFFF" bordercolordark="#D2E3E6">
                        <tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						</tr>
						<tr>
                          <td width="35%" height="30">&nbsp;</td>
                      <td width="9%" height="30">׼��֤�ţ�</td>
                      <td width="27%">
					   <input type='text' name="name"class="logininput" size="25"/>                       </td>
                      <td width="29%">&nbsp;</td>
                    </tr>
                        <tr>
                          <td height="30">&nbsp;</td>
                      <td height="30">��&nbsp;&nbsp;&nbsp;&nbsp;�룺</td>
                      <td><input type='password' name="pwd" class="logininput" size="25"/></td>
                      <td>&nbsp;</td>
                    </tr>
                        <tr>
                          <td height="31">&nbsp;</td>
                      <td height="31" colspan="2" align="left"><input type='submit' class="btn_grey" value="��¼" onClick="return check()"/>
                        &nbsp;
						<input type='reset' value="����" class="btn_grey"/>
                        <input type='button' id="button" class="btn_grey" value="ע��" onclick="document.location.href='register.jsp'"/>
                        &nbsp;
                       <input type='button' id="button" class="btn_grey" value="�һ�����" onclick="window.location.href='seekPwd.jsp'"/></td>
                      <td>&nbsp;</td>
                    </tr>
                        <tr>
                          <td height="40">&nbsp;</td>
                          <td height="31" colspan="2" align="right" valign="bottom"><a href="manage/login.jsp" class="word_orange">�����̨</a></td>
                          <td>&nbsp;</td>
                        </tr>
						<form>
                    </table> 
		    </td>
  </tr>
</table>
<table width="778" height="196" border="0" align="center" cellpadding="0" cellspacing="0" background="Images/login_top.gif">
  <tr>
    <td height="182" background="Images/login_bottom.jpg">&nbsp;</td>
  </tr>
</table>
</body>
</html>
