<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>

<html:select property="taoTiId" name="questionsForm">
<html:options collection="taoTiList" property="ID" labelProperty="name"/>
</html:select>