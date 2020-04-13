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
	                    int rowSum = rs.getRow();	//��ȡ��¼����
	                    rs.first();
	                    if (rowSum!=1) {
	                        flag = 2;
	                        System.out.print("��ȡrow��ֵ��" + sql + rowSum);
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

	    //�������
	    public String insert(StudentForm s) {
	        String sql1="SELECT * FROM tb_student WHERE cardNo='"+s.getCardNo()+"'";
	        conn=ConnectionManager.getConn();						//ִ��SQL��ѯ���
	        String sql = "";
	        String falg = "miss";											//���ڼ�¼������Ϣ�ı���
	        String ID="";
	        System.out.println(falg);
	            try {
	            	pre=conn.prepareStatement(sql1);
		             rs = pre.executeQuery();
	                if (rs.next()) {											//������ڼ�¼
	                    falg="re";										//��ʾ������Ϣ�Ѿ�ע��
	               System.out.print("����");
	                
	                } else {
	    				/*****************�Զ�����׼��֤��***********************************************/
	                	String sql_max="SELECT max(ID) FROM tb_student";
	                	pre=conn.prepareStatement(sql_max);
	   	                rs = pre.executeQuery();			//��ѯ����׼��֤��
	            		java.util.Date date=new java.util.Date();				//ʵ����java.util.Date()��
	            		String newTime=new SimpleDateFormat("yyyyMMdd").format(date);	//��ʽ����ǰ����
	                	if(rs.next()){
	                			System.out.println("-------");
	                    		String max_ID=rs.getString(1);				//��ȡ����׼��֤��
	                    		int newId=Integer.parseInt(max_ID.substring(10,16))+1;//ȡ�����׼��֤���е����ֱ��+1
	                    		String no=chStr.formatNO(newId,6);				//�����ɵı�Ÿ�ʽ��Ϊ6λ
	                    		ID="CN"+newTime+no;						//���������׼��֤��
	                		
	                	
	                		
	                	}
	                else{											//����һ������ע��ʱ
	                		ID="CN"+newTime+"000001";	
	                		
	                		//���ɵ�һ��׼��֤��
	                	}
	                	/********************************************************************************/
	              		sql = "INSERT INTO tb_student (ID,name,pwd,sex,question,answer,profession,cardNo) values('" +
	                                 ID+ "','" +s.getName() +"','"+s.getPwd()+"','"+s.getSex()+"','"+s.getQuestion()+
	                                 "','"+s.getAnswer()+"','"+s.getProfession()+"','"+s.getCardNo()+"')";
	              		pre=conn.prepareStatement(sql);
	              		int ret= pre.executeUpdate();					//���濼��ע����Ϣ
	                    if(ret==0){
	                    	falg="miss";									//��ʾ����ע��ʧ��
	                    }else{
	                    	falg="��ϲ����ע��ɹ�!\\r���ס����׼��֤�ţ�"+ID;	//�������ɵ�׼��֤��
	                    }										
	                }
	            } catch (Exception e) {
	                falg="miss";
	                e.printStackTrace();
	                System.out.println("��ӿ�����Ϣʱ�Ĵ�����Ϣ��"+e.getMessage());	//���������ʾ��Ϣ������̨
	            }finally{
	            	ConnectionManager.offall(conn, pre, rs);//�ر����ݿ�����
	            }
	        System.out.println(falg);
	        return falg;
	    }

	    //��ѯ����
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
	    //�޸Ŀ�������
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
	        
	        System.out.println("�޸Ŀ�������ʱ��SQL��"+sql);
	        return ret;
	    }
	//�һ����루��һ����
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
	            	System.out.println("�һ����루��һ�������ֵĴ�����Ϣ��"+e.getMessage());
	            }finally{
	            	ConnectionManager.offall(conn, pre, rs);
	            }
	            return s;
	    }
	//  �һ����루�ڶ�����
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
	                		System.out.println("���룺"+pwd);
	                	}else{
	                		s.setID("");
	                	}
	                }
	            }catch(Exception e){
	            	System.out.println("�һ����루�ڶ��������ֵĴ�����Ϣ��"+e.getMessage());
	            }
	            return s;
	    }
//	    ɾ������
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
	                System.out.println("ɾ��ʱ��SQL��"+sql);
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
