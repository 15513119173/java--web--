<%@page import="java.util.List"%>
<%@page import="com.demo.entity.QuestionsForm"%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>

<html>
<head>
<title>�������߿��ԡ�����̨����</title>
<link href="../CSS/style.css" rel="stylesheet">
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
              <td width="78%" class="word_grey">&nbsp;��ǰλ�ã�<span class="word_darkGrey">������Ŀ����  &gt;&gt;&gt;</span></td>
              <td align="right"><img src="./Images/m_ico1.gif" width="5" height="9">&nbsp;��ǰ����Ա��<%=session.getAttribute("manager")%>&nbsp;</td>
              </tr>
          </table></td>
        </tr>
      <tr>
        <td align="center" valign="top">
<table width="96%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="87%" height="27" align="right"><img src="./Images/add.gif" width="19" height="18">&nbsp;</td>
    <td width="13%"><a href="Questions?action=questionsAddQuery">��ӿ�����Ŀ</a> </td>
  </tr>
</table>
<table width="96%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#67A8DB">
<form name="questionsForm" action="Questions?action=questionsDel" method="post">
  <tr align="center" bgcolor="#A8D8FC">
	<td width="18%" height="30" bgcolor="#B2D6F1">�����γ�</td>
    <td width="28%" bgcolor="#B2D6F1">��������</td>
	<td width="33%" bgcolor="#B2D6F1">������Ŀ</td>
	<td width="9%" bgcolor="#B2D6F1">��������</td>
	<td width="6%" bgcolor="#B2D6F1">�޸�</td>
	<td width="6%" bgcolor="#B2D6F1">ѡ��</td>
  </tr>
	<logic:iterate id="questions" name="questionsQuery" type="com.wgh.actionForm.QuestionsForm" scope="request">
	<bean:define id="lessonID" name="questions" property="lessonId" type="Integer"/>
	<bean:define id="taoTiID" name="questions" property="taoTiId" type="Integer"/>
  <%List list=((List<QuestionsForm>)request.getAttribute("questionsQuery"));
    for(int i=0;i<list.size();i++){
       QuestionsForm bean=(QuestionsForm)list.get(i);
   %>
  <tr>
  	<td style="padding:5px;"><%=bean.getLessonId() %></td>
    <td style="padding:5px;"><%=bean.getTaoTiId() %></td>
	<td align="center"><%=bean.getSubject() %></td>
	<td align="center"><%=bean.getType() %></td>	
	<td align="center"><a href="Questions?action=questionsModifyQuery<%=bean.getID()%>">�޸�</a></td>
    <td align="center"><input name="delIdArray" type="checkbox" class="noborder" value="<%=bean.getID()%>"/></td>
  </tr>
  <%} %>
  </logic:iterate> 
</table>
<table width="94%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
	<td width="60%" height="24">&nbsp;</td>
	<td width="40%" align="right"><input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(questionsForm.delIdArray,questionsForm.checkbox)">
	  [ȫѡ/��ѡ] [<a style="color:red;cursor:hand;" onClick="checkdel(questionsForm.delIdArray,questionsForm)">ɾ��</a>]
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
