<%@page import="com.demo.entity.StudentForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<html>
<head>
<title>�������߿���</title>
<base/>
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
    <td valign="top" bgcolor="#FFFFFF"><table width="774" height="474"  border="0" cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="word_grey">&nbsp;<img src="Images/f_ico.gif" width="8" height="8"> ��ǰλ�ã��� <span class="word_darkGrey">�һ����� &gt;&gt;&gt;</span></td>
			  <td align="right"><img src="Images/m_ico1.gif" width="5" height="9">&nbsp;<link href="/index.jsp" >������ҳ</link>&nbsp;</td>
              </tr>
          </table></td>
        </tr>
      <tr>
        <td height="257" align="center" valign="top">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="84%">&nbsp;      </td>
</tr>
</table> 
 <table width="57%" height="69" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td width="17%"><img src="Images/step3.gif" width="73" height="30"></td>
     <td width="83%" class="word_orange1">�ɹ��һ�����</td>
   </tr>
 </table>
   <%StudentForm s=(StudentForm)request.getAttribute("seekPwd3"); %>
 <table width="57%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
    <tr align="center">
    <td width="22%" height="30" align="left" style="padding:5px;">׼��֤�ţ�</td>
    <td width="78%" align="left">
	  <input type="text" id="ID" size="40" name="seekPwd3" readonly="true" value="<%=s.getID()%>"/> <span class="word_orange1"> ��ֻ����</span>	  </td>
    </tr>
    <tr align="center">
    <td width="22%" height="30" align="left" style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;�룺</td>
  
    <td width="78%" align="left">
      <input type="text" id="pwd" size="40" name="seekPwd3" readonly="true" value="<%=s.getPwd()%>"/> <span class="word_orange1"> ��ֻ����</span>	  </td>
    </tr>	
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td>&nbsp;
		<input type="button" value="����" class="btn_grey" onClick="window.location.href='index.jsp'"/>		</td>
    </tr>
</table>
</td>
      </tr>
      <tr>
        <td height="141" align="right" valign="top"><img src="Images/seedPwd.gif" width="139" height="153"></td>
      </tr>
    </table>
</td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
