package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
   private static Connection c=null;
   
   public static Connection getConn(){
	   
	   try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 c=DriverManager.getConnection("jdbc:sqlserver://192.168.1.4:1433;DatabaseName=db_netExam","sa","sa");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	   return c;
   }
   public static void offall(Connection conn,PreparedStatement pre,ResultSet res){
	   try {
		   res.close();
		   pre.close();
		   conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   public static void offtwo(Connection conn,PreparedStatement pre){
	   try {
		   pre.close();
		   conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
}
