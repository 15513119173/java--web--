<%@page import="java.util.ArrayList"%>
<%@page import="java.text.Normalizer.Form"%>
<%@page import="com.demo.dao.TaoTiDao"%>
<%@page import="com.demo.entity.TaoTiForm"%>
<%@page import="com.demo.entity.LessonForm"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<html>
<head>
<title>�������߿��ԡ�����̨����</title>
<link href="../CSS/style.css" rel="stylesheet">
<script language="javascript" src="../JS/AjaxRequest.js">
</script>
</head>
<script language="javascript">

function F_getTaoTi(val){
	var loader=new net.AjaxRequest("Questions?action=queryTaoTi&id="+val+"&nocache="+new Date().getTime(),deal,onerror,"GET");
}
function onerror(){
	alert("������");
}
function deal(h){
	whichTaoTi.innerHTML=this.req.responseText;
}
function checkForm(){
	if(form.subject.value==""){
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
<body onLoad="F_getTaoTi(questionsForm.lessonId.value)">
<%@ include file="top.jsp"%>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td width="176" align="center" valign="top" bgcolor="#FFFFFF"><%@ include file="left.jsp"%></td>
    <td width="602" valign="top" bgcolor="#FFFFFF"><table width="99%" height="487"  border="0" cellpadding="0" cellspacing="0" align="right">
      <tr>
        <td height="30" bgcolor="#EEEEEE" class="tableBorder_thin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="78%" class="word_grey">&nbsp;��ǰλ�ã�<span class="word_darkGrey">������Ŀ���� &gt; ��ӿ�����Ŀ &gt;&gt;&gt;</span></td>
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
<form name="form" action="Questions?action=questionsAdd" method="post" >
  <table width="85%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
  <tr>
    <td height="30" align="left" style="padding:5px;">�����γ̣�</td>
    <td align="left">
	 <select name="lessonId" onchange="F_getTaoTi(this.value)">
		<%
		 
		List list=(List<LessonForm>)request.getAttribute("lessonList");
		List list2=new ArrayList();
		    for(int i=0;i<list.size();i++){
		       LessonForm bean=(LessonForm)list.get(0);
		       list2=TaoTiDao.queryTaoTi(bean.getID());
		 %>
		 <option value="<%=bean.getID()%>"><%=bean.getName() %></option>
		 <%} %>
	 </select>
     </td>
    </tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">�������⣺</td>
    
    <td align="left" id="whichTaoTi">
        <select name="taotiid">
        <%
      for(int i=0;i<list2.size();i++){
           
                TaoTiForm be=(TaoTiForm)list2.get(i);
    	%>
           <option value="<%=be.getID()%>"><%=be.getName() %></option>
          <%} %>
        </select>
     </td>
    </tr>
  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">������Ŀ��</td>
    <td width="85%" align="left">
      <input type="text" name="subject" size="40"/> *
	  </td>
    <tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">�������ͣ�</td>
    <td align="left">
	 <select name="type" onchange="show(this.value)">
		<option value="��ѡ��">��ѡ��</option>
		<option value="��ѡ��">��ѡ��</option>
	 </select>
     </td>
    </tr>	
    <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">ѡ��A��</td>
    <td width="85%" align="left">
      <input type="text" name="optionA" size="40"/> *
	  </td>
    </tr>
	  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">ѡ��B��</td>
    <td width="85%" align="left">
      <input type="text" name="optionB" size="40"/> *
	  </td>
    </tr>
	  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">ѡ��C��</td>
    <td width="85%" align="left">
      <input type="text" name="optionC" size="40"/> *
	  </td>
    </tr>
  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">ѡ��D��</td>
    <td width="85%" align="left">
      <input type="text" name="optionD" size="40"/> *
	  </td>
    </tr>
    <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">��ȷ�𰸣�</td>
    <td width="85%" align="left" id="sOption">
      <select name="answerArrw">
		<option value="A">A </option>
		<option value="B">B </option>
		<option value="C">C </option>
		<option value="D">D </option>		
	 </select>
	  </td>	
    <td width="85%" align="left" id="mOption" style="display:none">
 	<input type="checkbox" name="answerArr" styleClass="noborder" value="A">A</input>
	<input type="checkbox" name="answerArr" styleClass="noborder" value="B">B</input>
	<input type="checkbox" name="answerArr" styleClass="noborder" value="C">C</input>
	<input type="checkbox" name="answerArr" styleClass="noborder" value="D">D</input>
	  </td>		  
    </tr>
	
	  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">��ע��</td>
    <td width="85%" align="left">
      <input type="text" name="note" size="40"/>
	  </td>
    </tr>	
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="����" onclick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="ȡ��"/>
		&nbsp;
		<input type="button" name="button" styleClass="btn_grey" value="����" onclick="window.location.href='Questions?action=questionsQuery'"/>		</td>
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
