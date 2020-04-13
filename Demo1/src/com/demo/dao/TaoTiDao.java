package com.demo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.demo.entity.TaoTiForm;

public class TaoTiDao {
     private static Connection conn;
     private static PreparedStatement pre;
     private static ResultSet resultSet;
     public static int insert(TaoTiForm tati){
    	 int a=0;
    	 conn=ConnectionManager.getConn();
    	 String sql="insert into tb_TaoTi (Name,LessonID) values('"+tati.getName()+"','"+tati.getLessonId()+"')";
    	 try {
			pre=conn.prepareStatement(sql);
			a=pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.offtwo(conn, pre);
		}
    	 return a;
     }
     public static List query(int id) {
     	List taoTiList = new ArrayList();
     	conn=ConnectionManager.getConn();
         TaoTiForm taoTiForm1 = null;
         String sql="";
         if(id==0){
             sql = "SELECT * FROM tb_taoTi ORDER BY lessonId DESC";
         }else{
         	sql = "SELECT * FROM tb_taoTi WHERE id=" +id+ "";
         }
         try {
        	 pre=conn.prepareStatement(sql);
             resultSet = pre.executeQuery();
             while (resultSet.next()) {
                 taoTiList.add(new TaoTiForm(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),java.text.DateFormat.getDateTimeInstance().parse(resultSet.getString(4))));
             }
         } catch (Exception ex) {
        	 ex.getMessage();
         }finally{
        	 ConnectionManager.offall(conn, pre, resultSet);
         }
         return taoTiList;
     }
     public static List queryTaoTi(int lessonId){
     	List taoTiList = new ArrayList();
     	conn=ConnectionManager.getConn();
         TaoTiForm taoTiForm1 = null;
         String sql="SELECT * FROM tb_taoTi WHERE lessonId="+lessonId+"";
         System.out.println("queryTaoTi:"+sql);
         try {
        	 pre=conn.prepareStatement(sql);
             resultSet = pre.executeQuery();
             while (resultSet.next()) {
                 taoTiList.add(new TaoTiForm(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),java.text.DateFormat.getDateTimeInstance().parse(resultSet.getString(4))));
             }
         } catch (Exception ex) {}finally{
        	 ConnectionManager.offall(conn, pre, resultSet);
         }
         return taoTiList;
     }
     public static int update(TaoTiForm taoTiForm){
    	 conn=ConnectionManager.getConn();
    	 int ret=0;
         String sql="UPDATE tb_taoTi SET name='"+taoTiForm.getName()+"',lessonId="+taoTiForm.getLessonId()+" where id="+taoTiForm.getID()+"";
         try {
			pre=conn.prepareStatement(sql);
			ret=pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.offtwo(conn, pre);
		}
         System.out.println("修改套题时的SQL："+sql);
         return ret;
     }
     public static String getLesson(int id){
     	String lessonName="";
     	conn=ConnectionManager.getConn();
     	if(id>0){
     		String sql="SELECT * FROM tb_lesson WHERE id="+id+"";
     		try {
				pre=conn.prepareStatement(sql);
				resultSet=pre.executeQuery();
				if(resultSet.next()) {
                    lessonName=resultSet.getString(2);
                }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.offall(conn, pre, resultSet);
			}
     	}
     	return lessonName;
     }
     public int delete(TaoTiForm taoTiForm) {
     	int flag=0;
     	conn=ConnectionManager.getConn();
     	String id=taoTiForm.getDelIdArray();
     	System.out.print(id);
             String sql = "DELETE FROM tb_TaoTi where ID =" + id ;
             try {
            	 System.out.print(id);
				pre=conn.prepareStatement(sql);
				flag = pre.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
             ConnectionManager.offtwo(conn, pre);
			}
     	
         return flag;
     }
}
