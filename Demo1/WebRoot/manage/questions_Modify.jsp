<%@page import="com.demo.entity.QuestionsForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<%-- <jsp:useBean id="lesson" class="com.wgh.dao.TaoTiDAO" scope="page"/>
<jsp:useBean id="taoTi" class="com.wgh.dao.QuestionsDAO" scope="page"/> --%>
<html>
<head>
<title>�������߿��ԡ�����̨����</title>
<link href="../CSS/style.css" rel="stylesheet">
</head>
<script language="javascript">
function checkForm(){
	if(form.name.value==""){
		alert("�����뿼����Ŀ����!");form.name.focus();return false;
	}
}
function show(val){
		if(val=="��ѡ��"){
		sOption.style.display="";
		mOption.style.display="none";
	}else if(val=="��ѡ��"){
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
              <td width="78%" class="word_grey">&nbsp;��ǰλ�ã�<span class="word_darkGrey">������Ŀ���� &gt; �޸Ŀ�����Ŀ &gt;&gt;&gt;</span></td>
              <td align="right"><img src="../Images/m_ico1.gif" width="5" height="9">&nbsp;��ǰ����Ա��<%=session.getAttribute("manager")%>&nbsp;</td>
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
      <td height="30" align="left" style="padding:5px;">�����γ̣�</td>
      <td align="left"><%=bean.getLessonId() %></td>
    </tr>
    <tr>
      <td height="30" align="left" style="padding:5px;">�������⣺</td>
      <td align="left" id="subType"><%=bean.getTaoTiId() %></td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">������Ŀ��</td>
      <td width="85%" align="left"><input type="text" id="subject" size="40" name="subject" value="<%=bean.getSubject()%>"/>
	  <input type="hidden" name="ID" value="<%=bean.getID()%>"/>
        * </td>
    <tr>
    <tr>
      <td height="30" align="left" style="padding:5px;">�������ͣ�</td>
      <td align="left"><select property="type" onchange="show(this.value)" name="type">
          <option value="��ѡ��">��ѡ��</option>
          <option value="��ѡ��" >��ѡ��</option>
        </select>      </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">ѡ��A��</td>
      <td width="85%" align="left"><input type="text" id="optionA" size="40" name="optionA" value="<%=bean.getOptionA()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">ѡ��B��</td>
      <td width="85%" align="left"><input type="text" id="optionB" size="40" name="optionB" value="<%=bean.getOptionB()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">ѡ��C��</td>
      <td width="85%" align="left"><input type="text" id="optionC" size="40" name="optionC" value="<%=bean.getOptionC()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">ѡ��D��</td>
      <td width="85%" align="left"><input type="text" id="optionD" size="40" name="optionD" value="<%=bean.getOptionD()%>"/>
        * </td>
    </tr>
    <tr align="center">
      <td width="15%" height="30" align="left" style="padding:5px;">��ȷ�𰸣�</td>
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
      <td width="15%" height="30" align="left" style="padding:5px;">��ע��</td>
      <td width="85%" align="left"><input type="text" id="note" size="40" name="note"  value="<%=bean.getNote()%>"/>      </td>
    </tr>
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="����" onclick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="ȡ��"/>
        &nbsp;
        <input type="button" name="button" styleClass="btn_grey" value="����" onclick="window.location.href='Questions?action=questionsQuery'"/></td>
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
