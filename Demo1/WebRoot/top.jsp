<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>

<%if(session.getAttribute("student")==null){
	response.sendRedirect("../index.jsp");
}%>