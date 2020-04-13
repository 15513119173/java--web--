package com.demo.dao;
import java.sql.*;
import java.util.*;

import com.demo.entity.ManagerForm;
public class ManagerDao {
	private static Connection conn;
    private static PreparedStatement pre;
    private static ResultSet rs;
	 public static int checkManager(ManagerForm managerForm) {
	        int flag = 1;
	        conn=ConnectionManager.getConn();
	        String sql = "SELECT * FROM tb_manager where name='" +
	                     managerForm.getName() + "'";
	        try {
	        	pre=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	             rs = pre.executeQuery();
	            if (rs.next()) {
	                String pwd = managerForm.getPwd();
	                if (pwd.equals(rs.getString(3))) {
	                    rs.last();
	                    int rowSum = rs.getRow();	//获取记录总数
	                    rs.first();
	                    if (rowSum!=1) {
	                        flag = 2;
	                        System.out.print("获取row的值：" + sql + rowSum);
	                    }
	                } else {
	                    flag = 2;
	                }
	            }else{
	                flag = 2;
	            }
	        } catch (SQLException ex) {
	            flag = 2;
	            System.out.println(ex.getMessage());
	        }finally{
	        	ConnectionManager.offall(conn, pre, rs);
	        }
	        return flag;
	    }
	    //添加数据
	    public static int insert(ManagerForm managerForm) {
	        String sql1="SELECT * FROM tb_manager WHERE name='"+managerForm.getName()+"'";
	        conn=ConnectionManager.getConn();
	        String sql = "";
	        int falg = 0;
	            try {
	            	pre=conn.prepareStatement(sql1);
		             rs = pre.executeQuery();
	                if (rs.next()) {
	                    falg=2;
	                } else {
	                    sql = "INSERT INTO tb_manager (name,pwd) values('" +
	                                 managerForm.getName() + "','" +
	                                 managerForm.getPwd() +
	                                 "')";
	                    pre=conn.prepareStatement(sql);
	                    falg = pre.executeUpdate();
	                    System.out.println("添加管理员信息的SQL：" + sql);
	                }
	            } catch (SQLException ex) {
	                falg=0;
	            }finally{
	            	ConnectionManager.offall(conn, pre, rs);
	            }
	        return falg;
	    }
	    //查询方法
	    public static List query(int id) {
	    	List managerList = new ArrayList();
	        ManagerForm managerForm1 = null;
	        String sql="";
	        conn=ConnectionManager.getConn();
	        if(id==0){
	            sql = "SELECT * FROM tb_manager";
	        }else{
	        	sql = "SELECT * FROM tb_manager WHERE id=" +id+ "";
	        }
	        try {
	        	pre=conn.prepareStatement(sql);
	             rs = pre.executeQuery();
	            while (rs.next()) {
	                managerForm1 = new ManagerForm();
	                managerForm1.setID(rs.getInt(1));
	                managerForm1.setName(rs.getString(2));
	                managerForm1.setPwd(rs.getString(3));
	                managerList.add(managerForm1);
	            }
	        } catch (SQLException ex) {}finally{
	        	ConnectionManager.offall(conn, pre, rs);
	        }
	        return managerList;
	    }
	    //修改管理员密码
	    public static int updatePwd(ManagerForm managerForm){
	        String sql="UPDATE tb_manager SET pwd='"+managerForm.getPwd()+"' where name='"+managerForm.getName()+"'";
	        int ret=0;
	        conn=ConnectionManager.getConn();
	        try {
				pre=conn.prepareStatement(sql);
				ret=pre.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.offtwo(conn, pre);
			}
	        System.out.println("修改管理员密码时的SQL："+sql);
	        return ret;
	    }

//	    删除数据
	        public static int delete(ManagerForm managerForm) {
	            String sql = "DELETE FROM tb_manager where id=" + managerForm.getID() +"";
	            int flag=0;
	            conn=ConnectionManager.getConn();
	            try {
					pre=conn.prepareStatement(sql);
					flag=pre.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					ConnectionManager.offtwo(conn, pre);
				}
	            return flag;
	        }
}
