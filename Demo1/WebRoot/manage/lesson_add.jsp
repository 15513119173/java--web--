<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>

<html>
<head>
<title>��ӿγ�</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../CSS/style.css" rel="stylesheet">
</head>
<script language="javascript">
function check(){
	if(form.name.value==""){
		alert("������γ�����!");form.name.focus();return;
	}
	form.submit();
}
</script>
<body>
<table width="292" height="175" border="0" cellpadding="0" cellspacing="0" background="../Images/subBG.jpg">
  <tr>
    <td valign="top"><table width="100%" height="175" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="12%" height="47">&nbsp;</td>
        <td width="85%" valign="bottom"><table width="86%" height="31" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td class="word_white">��ӿγ�</td>
          </tr>
        </table></td>
        <td width="3%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><table width="100%" height="106"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="106" align="center" valign="middle">
	<form action="../lesson?action=lessonAdd" method="post" name="form">
	<table height="77"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="67" height="30" align="center">�γ����ƣ�</td>
        <td width="181">
          <input type="text" name="name" size="25"/></td>
      </tr>
      <tr>
        <td height="47" align="center">&nbsp;</td>
        <td> <input type="button" property="button" styleClass="btn_grey" value="����" onclick="check()"/>
&nbsp;
<input type="button" name="button" styleClass="btn_grey" value="�ر�" onclick="window.close();"/></td>
      </tr>
    </table>
	</form></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
