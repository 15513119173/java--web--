<%@ page contentType="text/html; charset=gb2312" language="java"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<head>
<title>网络在线考试——后台登录</title>
<link href="../CSS/style.css" rel="stylesheet">
<script language="javascript">
function check(){
	if (form.name.value==""){
		alert("请输入管理员名称!");form.name.focus();return false;
	}
	if (form.pwd.value==""){
		alert("请输入密码!");form.pwd.focus();return false;
	}	
}
</script>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#EEEEEE"><table width="464" height="294" border="0" align="center" cellpadding="0" cellspacing="0" background="../Images/m_login.jpg">
      <tr>
        <td width="157" height="153">&nbsp;</td>
        <td width="307">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td valign="top">
<form name="form" action="../Manager?action=login" method="post" focus="name" >
                      <table width="100%"  border="0" cellpadding="0" cellspacing="0" bordercolorlight="#FFFFFF" bordercolordark="#D2E3E6">
                        <tr>
                          <td width="26%" height="30">管理员名称：</td>
                      <td width="74%">
                        <input type="text" name="name" size="25"/></td>
                      </tr>
                        <tr>
                          <td height="30">管理员密码：</td>
                      <td><input type="password" name="pwd" size="25"/></td>
                      </tr>
                        <tr>
                          <td height="33" colspan="2" align="center"><input type="submit" styleClass="btn_grey" value="确定" onclick="return check()"/>
                        &nbsp;
                        <input type="reset" value="重置" styleClass="btn_grey"/>&nbsp;
						<input type="button" name="button" styleClass="btn_grey" value="关闭" onclick="window.close();"/>                        </td>
                      </tr>
              </table> 
		    </form>		
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
