package com.demo.dao;
import java.sql.*;
import java.util.*;

import com.demo.entity.QuestionsForm;
public class QuesstionsDao {
	private static Connection conn;
    private static PreparedStatement pre;
    private static ResultSet rs;
	//添加数据
    public static int insert(QuestionsForm q) {
        String sql1="SELECT * FROM tb_questions WHERE subject='"+q.getSubject()+"' AND taoTiId="+q.getTaoTiId()+"";
        conn=ConnectionManager.getConn();
        System.out.println("添加时的查询"+sql1);
        String sql = "";
        String answer="";
        int falg = 0;
            try {
            	pre=conn.prepareStatement(sql1);
	             rs = pre.executeQuery();
                if (rs.next()) {
                    falg=2;
                } else {
                	answer=q.getAnswer();
                    sql = "INSERT INTO tb_questions (subject,type,lessonId,taoTiId,optionA,optionB,optionC,optionD,answer,note) values('" +
                                 q.getSubject() + "','"+q.getType()+"',"+q.getLessonId()+","+q.getTaoTiId()+",'"+q.getOptionA()+"','"+q.getOptionB()+"','"+q.getOptionC()+"','"+q.getOptionD()+"','"+answer+"','"+q.getNote()+"')";
                    pre=conn.prepareStatement(sql);
                    falg = pre.executeUpdate();
                    System.out.println("添加考试题目时的SQL：" + sql);
              
                }
            } catch (Exception ex) {
            	System.out.print(ex.getMessage());
                falg=0;
            }finally{
					ConnectionManager.offall(conn, pre, rs);
            }
        return falg;
    }
    //查询方法
    public static List query(int id) {
    	List questionsList = new ArrayList();
        QuestionsForm questionsForm1 = null;
        String sql="";
        conn=ConnectionManager.getConn();
        if(id==0){
            sql = "SELECT * FROM tb_questions ORDER BY lessonId DESC,taoTiId DESC,type";
        }else{
        	sql = "SELECT * FROM tb_questions WHERE id=" +id+ "";
        }
        String type="";
        String answer="";
        try {
        	pre=conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                questionsForm1 = new QuestionsForm();
                questionsForm1.setID(rs.getInt(1));
                questionsForm1.setSubject(rs.getString(2));
                type=rs.getString(3);
                questionsForm1.setType(type);
                questionsForm1.setJoinTime(java.text.DateFormat.getDateTimeInstance().parse(rs.getString(4)));               
                questionsForm1.setLessonId(rs.getInt(5));
                questionsForm1.setTaoTiId(rs.getInt(6));
                questionsForm1.setOptionA(rs.getString(7));
                questionsForm1.setOptionB(rs.getString(8));
                questionsForm1.setOptionC(rs.getString(9));
                questionsForm1.setOptionD(rs.getString(10));
                if(type.equals("多选题")){
                	String[] ans=rs.getString(11).split(",");
                	questionsForm1.setAnswerArr(ans);
                }else{
                	questionsForm1.setAnswer(rs.getString(11));
                }
                questionsForm1.setNote(rs.getString(12));
                questionsList.add(questionsForm1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	ConnectionManager.offall(conn, pre, rs);
        }
        return questionsList;
    }

    //修改数据
    public static int update(QuestionsForm q){
    	String answer="";
    	int ret=0;
    	answer=q.getAnswer();
        String sql="UPDATE tb_questions SET subject='"+q.getSubject()+"',type='"+q.getType()+"',optionA='"+q.getOptionA()+"',optionB='"+q.getOptionB()+"',optionC='"+q.getOptionC()+"',optionD='"+q.getOptionD()+"',answer='"+answer+"',note='"+q.getNote()+"' where id="+q.getID()+"";
        conn=ConnectionManager.getConn();
        try {
        	pre=conn.prepareStatement(sql);
			ret = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.offtwo(conn, pre);
		}
        System.out.println("修改考试题目时的SQL："+sql);
        return ret;
    }
    //根据所属套题查询套题名称(通过jsp:useBean调用)
    public static String getTaoTi(int id){
    	String taoTiName="";
    	if(id>0){
    		String sql="SELECT * FROM tb_taoTi WHERE id="+id+"";
    		conn=ConnectionManager.getConn();
            try {
            	pre=conn.prepareStatement(sql);
	             rs = pre.executeQuery();
                if(rs.next()) {
                	taoTiName=rs.getString(2);
                }
            }  catch (Exception e) {
            	e.printStackTrace();
            }finally{
            	ConnectionManager.offall(conn, pre, rs);
            }  		
    	}
    	return taoTiName;
    }
//    删除数据
        public int delete(QuestionsForm questionsForm) {
        	int flag=0;
        	conn=ConnectionManager.getConn();
        	String[] delId=questionsForm.getDelIdArray();
        	if (delId.length>0){
        		String id="";
        		for(int i=0;i<delId.length;i++){
        			id=id+delId[i]+",";
        		}
        		id=id.substring(0,id.length()-1);
                String sql = "DELETE FROM tb_questions where id in (" + id +")";
                
	             try {
	            	 pre=conn.prepareStatement(sql);
					flag=pre.executeUpdate();
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


