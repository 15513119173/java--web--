<%@page import="java.util.ArrayList"%>
<%@page import="java.text.Normalizer.Form"%>
<%@page import="com.demo.dao.TaoTiDao"%>
<%@page import="com.demo.entity.TaoTiForm"%>
<%@page import="com.demo.entity.LessonForm"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<html>
<head>
<title>网络在线考试——后台管理</title>
<link href="../CSS/style.css" rel="stylesheet">
<script language="javascript" src="../JS/AjaxRequest.js">
</script>
</head>
<script language="javascript">

function F_getTaoTi(val){
	var loader=new net.AjaxRequest("Questions?action=queryTaoTi&id="+val+"&nocache="+new Date().getTime(),deal,onerror,"GET");
}
function onerror(){
	alert("出错了");
}
function deal(h){
	whichTaoTi.innerHTML=this.req.responseText;
}
function checkForm(){
	if(form.subject.value==""){
		alert("请输入考试题目名称!");form.name.focus();return false;
	}
}
function show(val){
		if(val=="单选题"){
		sOption.style.display="";
		mOption.style.display="none";
	}else if(val=="多选题"){
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
              <td width="78%" class="word_grey">&nbsp;当前位置：<span class="word_darkGrey">考试题目管理 &gt; 添加考试题目 &gt;&gt;&gt;</span></td>
              <td align="right"><img src="../Images/m_ico1.gif" width="5" height="9">&nbsp;当前管理员：<%=session.getAttribute("manager")%>&nbsp;</td>
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
    <td height="30" align="left" style="padding:5px;">所属课程：</td>
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
    <td height="30" align="left" style="padding:5px;">所属套题：</td>
    
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
    <td width="15%" height="30" align="left" style="padding:5px;">考试题目：</td>
    <td width="85%" align="left">
      <input type="text" name="subject" size="40"/> *
	  </td>
    <tr>
    <tr>
    <td height="30" align="left" style="padding:5px;">试题类型：</td>
    <td align="left">
	 <select name="type" onchange="show(this.value)">
		<option value="单选题">单选题</option>
		<option value="多选题">多选题</option>
	 </select>
     </td>
    </tr>	
    <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">选项A：</td>
    <td width="85%" align="left">
      <input type="text" name="optionA" size="40"/> *
	  </td>
    </tr>
	  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">选项B：</td>
    <td width="85%" align="left">
      <input type="text" name="optionB" size="40"/> *
	  </td>
    </tr>
	  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">选项C：</td>
    <td width="85%" align="left">
      <input type="text" name="optionC" size="40"/> *
	  </td>
    </tr>
  <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">选项D：</td>
    <td width="85%" align="left">
      <input type="text" name="optionD" size="40"/> *
	  </td>
    </tr>
    <tr align="center">
    <td width="15%" height="30" align="left" style="padding:5px;">正确答案：</td>
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
    <td width="15%" height="30" align="left" style="padding:5px;">备注：</td>
    <td width="85%" align="left">
      <input type="text" name="note" size="40"/>
	  </td>
    </tr>	
    <tr>
      <td height="65" align="left" style="padding:5px;">&nbsp;</td>
      <td><input type="submit" name="submit" styleClass="btn_grey" value="保存" onclick="return checkForm()"/>
        &nbsp;
        <input type="reset" name="reset" styleClass="btn_grey" value="取消"/>
		&nbsp;
		<input type="button" name="button" styleClass="btn_grey" value="返回" onclick="window.location.href='Questions?action=questionsQuery'"/>		</td>
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
