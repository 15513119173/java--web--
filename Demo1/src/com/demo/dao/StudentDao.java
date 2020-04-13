package com.demo.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.demo.entity.StudentForm;
import com.demo.util.ChStr;

public class StudentDao {
	private  Connection conn;
    private PreparedStatement pre;
    private  ResultSet rs;
    private  ChStr chStr=new ChStr();
    private StudentForm student;
	   public int checkStudent(StudentForm studentForm) {
	        int flag = 1;
	        student=studentForm;
	        String sql = "SELECT * FROM tb_Student where ID='" +
	                     student.getID()+ "'";
	        System.out.print(sql);
	        conn=ConnectionManager.getConn();
	        try {
	        	 pre=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	             rs = pre.executeQuery();
	             System.out.print(rs);
	            if (rs.next()) {
	                String pwd = studentForm.getPwd();
	                if (pwd.equals(rs.getString(3))) {
	                    rs.last();
	                    System.out.println(rs.getString(3));
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
	        } catch (Exception ex) {
	            flag = 2;
	            System.out.println(ex.getMessage());
	        }finally{
//	        	ConnectionManager.offall(conn, pr, rs);
	        }
	        return flag;
	    }

	    //添加数据
	    public String insert(StudentForm s) {
	        String sql1="SELECT * FROM tb_student WHERE cardNo='"+s.getCardNo()+"'";
	        conn=ConnectionManager.getConn();						//执行SQL查询语句
	        String sql = "";
	        String falg = "miss";											//用于记录返回信息的变量
	        String ID="";
	        System.out.println(falg);
	            try {
	            	pre=conn.prepareStatement(sql1);
		             rs = pre.executeQuery();
	                if (rs.next()) {											//假如存在记录
	                    falg="re";										//表示考生信息已经注册
	               System.out.print("重名");
	                
	                } else {
	    				/*****************自动生成准考证号***********************************************/
	                	String sql_max="SELECT max(ID) FROM tb_student";
	                	pre=conn.prepareStatement(sql_max);
	   	                rs = pre.executeQuery();			//查询最大的准考证号
	            		java.util.Date date=new java.util.Date();				//实例化java.util.Date()类
	            		String newTime=new SimpleDateFormat("yyyyMMdd").format(date);	//格式化当前日期
	                	if(rs.next()){
	                			System.out.println("-------");
	                    		String max_ID=rs.getString(1);				//获取最大的准考证号
	                    		int newId=Integer.parseInt(max_ID.substring(10,16))+1;//取出最大准考证号中的数字编号+1
	                    		String no=chStr.formatNO(newId,6);				//将生成的编号格式化为6位
	                    		ID="CN"+newTime+no;						//组合完整的准考证号
	                		
	                	
	                		
	                	}
	                else{											//当第一个考生注册时
	                		ID="CN"+newTime+"000001";	
	                		
	                		//生成第一个准考证号
	                	}
	                	/********************************************************************************/
	              		sql = "INSERT INTO tb_student (ID,name,pwd,sex,question,answer,profession,cardNo) values('" +
	                                 ID+ "','" +s.getName() +"','"+s.getPwd()+"','"+s.getSex()+"','"+s.getQuestion()+
	                                 "','"+s.getAnswer()+"','"+s.getProfession()+"','"+s.getCardNo()+"')";
	              		pre=conn.prepareStatement(sql);
	              		int ret= pre.executeUpdate();					//保存考生注册信息
	                    if(ret==0){
	                    	falg="miss";									//表示考生注册失败
	                    }else{
	                    	falg="恭喜您，注册成功!\\r请记住您的准考证号："+ID;	//返回生成的准考证号
	                    }										
	                }
	            } catch (Exception e) {
	                falg="miss";
	                e.printStackTrace();
	                System.out.println("添加考生信息时的错误信息："+e.getMessage());	//输出错误提示信息到控制台
	            }finally{
	            	ConnectionManager.offall(conn, pre, rs);//关闭数据库连接
	            }
	        System.out.println(falg);
	        return falg;
	    }

	    //查询方法
	    public List query(String id) {
	    	List studentList = new ArrayList<StudentForm>();
	        StudentForm studentForm1 = null;
	        String sql="";
	        if(id==null ||id.equals("")){
	            sql = "SELECT * FROM tb_student ORDER BY joinTime DESC";
	        }else{
	        	sql = "SELECT * FROM tb_student WHERE id='" +id+ "'";
	        }
	        conn=ConnectionManager.getConn();
	        try {
	        	pre=conn.prepareStatement(sql);
  	             rs = pre.executeQuery();
	            while (rs.next()) {
	                studentForm1 = new StudentForm();
	                studentForm1.setID(rs.getString(1));
	                studentForm1.setName(rs.getString(2));
	                studentForm1.setPwd(rs.getString(3));
	                studentForm1.setSex(rs.getString(4));
	                studentForm1.setJoinTime(java.text.DateFormat.getDateTimeInstance().parse(rs.getString(5)));
	                studentForm1.setQuestion(rs.getString(6));
	                studentForm1.setAnswer(rs.getString(7));
	                studentForm1.setProfession(rs.getString(8));
	                studentForm1.setCardNo(rs.getString(9));
	                studentList.add(studentForm1);
	            }
	        } catch (Exception ex) {}finally{
	        	ConnectionManager.offall(conn, pre, rs);
	        }
	        return studentList;
	    }
	    //修改考生资料
	    public int update(StudentForm s){
	    	int ret=0;
	        String sql="UPDATE tb_student SET pwd='"+s.getPwd()+"',sex='"+s.getSex()+"',question='"+s.getQuestion()+"',answer='"+s.getAnswer()+"',profession='"+s.getProfession()+"' where ID='"+s.getID()+"'";
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
	        
	        System.out.println("修改考生资料时的SQL："+sql);
	        return ret;
	    }
	//找回密码（第一步）
	    public StudentForm seekPwd1(StudentForm s){
	    	String sql="SELECT * FROM tb_student WHERE ID='"+s.getID()+"'";
	    	 conn=ConnectionManager.getConn();
	            try {
	            	pre=conn.prepareStatement(sql);
					rs=pre.executeQuery();
	                if (rs.next()) {
	                    s.setID(rs.getString(1));
	                    s.setQuestion(rs.getString(6));
	                }else{
	                s.setID("");
	                }
	            }catch(Exception e){
	            	System.out.println("找回密码（第一步）出现的错误信息："+e.getMessage());
	            }finally{
	            	ConnectionManager.offall(conn, pre, rs);
	            }
	            return s;
	    }
	//  找回密码（第二步）
	    public StudentForm seekPwd2(StudentForm s){
	    	String sql="SELECT * FROM tb_student WHERE ID='"+s.getID()+"'";
	    	System.out.println("SQL"+sql);
	    	conn=ConnectionManager.getConn();
            try {
            	pre=conn.prepareStatement(sql);
				rs=pre.executeQuery();
	            if (rs.next()) {
	                	String ID=rs.getString(1);
	                	String pwd=rs.getString(3);
	                	String answer=rs.getString(7);
	                	if(answer.equals(s.getAnswer())){
	                		s.setID(ID);
	                		s.setPwd(pwd);
	                		System.out.println("密码："+pwd);
	                	}else{
	                		s.setID("");
	                	}
	                }
	            }catch(Exception e){
	            	System.out.println("找回密码（第二步）出现的错误信息："+e.getMessage());
	            }
	            return s;
	    }
//	    删除数据
	        public int delete(StudentForm studentForm) {
	        	int flag=0;
	        	String[] delId=studentForm.getDelIdArray();
	        	conn=ConnectionManager.getConn();
	        	if (delId.length>0){
	        		String id="'";
	        		for(int i=0;i<delId.length;i++){
	        			id=id+delId[i]+"','";
	        		}
	        		id=id.substring(0,id.length()-2);
	                String sql = "DELETE FROM tb_student where id in (" + id +")";
	                System.out.println("删除时的SQL："+sql);
	                try {
						pre=conn.prepareStatement(sql);
						flag = pre.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						ConnectionManager.offtwo(conn, pre);
					}
	        	}else{
	        		flag=0;
	        	}
	            return flag;
	        }
}
