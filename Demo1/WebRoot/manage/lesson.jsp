<%@page import="com.demo.entity.LessonForm"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>�������߿��ԡ�����̨����</title>
<link href="./CSS/style.css" rel="stylesheet">
<script language="javascript">
function CheckAll(elementsA,elementsB){
	for(i=0;i<elementsA.length;i++){
		elementsA[i].checked = true;
	}
	if(elementsB.checked ==false){
		for(j=0;j<elementsA.length;j++){
			elementsA[j].checked = false;
		}
	}
}
//�ж��û��Ƿ�ѡ����Ҫɾ���ļ�¼������ǣ�����ʾ���Ƿ�ɾ������������ʾ����ѡ��Ҫɾ���ļ�¼��
function checkdel(delid,formname){
	var flag = false;
	for(i=0;i<delid.length;i++){
		if(delid[i].checked){
			flag = true;
			break;
		}
	}
	if(!flag){
		alert("��ѡ��Ҫɾ���ļ�¼��");
		return false;
	}else{
			if(confirm("ȷ��Ҫɾ����")){
				formname.submit();
		}
	}
}
</script>

</head>
<body>
<%@ include file="top.jsp"%>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td width="176" align="center" valign="top" bgcolor="#FFFFFF"><%@ include file="left.jsp"%></td>
    <td width="602" valign="top" bgcolor="#FFFFFF"><table width="99%" height="487"  border="0" cellpadding="0" cellspacing="0" align="right">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="78%" class="word_grey">&nbsp;��ǰλ�ã�<span class="word_darkGrey">�γ���Ϣ����  &gt;&gt;&gt;</span></td>
              <td align="right"><img src="./Images/m_ico1.gif" width="5" height="9">&nbsp;��ǰ����Ա��<%=session.getAttribute("manager")%>&nbsp;</td>
              </tr>
          </table></td>
        </tr>
      <tr>
        <td align="center" valign="top">
<table width="96%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="90%" height="27" align="right"><img src="./Images/add.gif" width="19" height="18">&nbsp;</td>
    <td width="10%"><a href="#" onClick="window.open('manage/lesson_add.jsp','','width=292,height=175')">��ӿγ�</a> </td>
  </tr>
</table>
<table width="96%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#67A8DB">
<form name="lessonForm" action="lesson.do?action=lessonDel" method="post">
  <tr align="center" bgcolor="#A8D8FC">
    <td width="11%" height="26" bgcolor="#B2D6F1">ID</td>
    <td width="54%" bgcolor="#B2D6F1">�γ�����</td>
	<td width="27%" bgcolor="#B2D6F1">����ʱ��</td>
	<td width="8%" bgcolor="#B2D6F1">ѡ��</td>
  </tr>
	<logic:iterate id="lesson" name="lessonQuery" type="com.wgh.actionForm.LessonForm" scope="request">
  <% List list=((List<LessonForm>)request.getAttribute("lessonQuery"));
     for(int i=0;i<list.size();i++){
          LessonForm less=(LessonForm)list.get(i);
   %>
  <tr>
 	<td align="center"><%=less.getID() %></td> 
    <td style="padding:5px;"><%=less.getName() %></td>
	<td align="center"><%=less.getJoinTime() %></td>
    <td align="center"><input name="delIdArray" type="checkbox" class="noborder" value="<%=less.getID()%>"/></td>
  </tr>
  <%} %>
  </logic:iterate> 
</table>
<table width="94%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
	<td width="60%" height="24">&nbsp;</td>
	<td width="40%" align="right"><input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(lessonForm.delIdArray,lessonForm.checkbox)">
	  [ȫѡ/��ѡ] [<a style="color:red;cursor:hand;" onClick="checkdel(lessonForm.delIdArray,lessonForm)">ɾ��</a>]
	  <div id="ch">
		<input name="delid" type="checkbox" class="noborder" value="0">
	  </div></td>
	<!--��ch���ڷ������ص�checkbox�ؼ�����Ϊ������ֻ��һ��checkbox�ؼ�ʱ��Ӧ��javascript�����length����ֵΪundefine-->
	<script language="javascript">ch.style.display="none";</script>
  </tr>
  </form>
</table>
</td>
      </tr>
    </table></td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
